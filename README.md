# LeaveManagement

## Déploiement
Afin de récupérer notre projet nous devons cloner le répertoire github.

<pre><code> git clone https://github.com/Benjamin-F/LeaveManagement.git
</code></pre>

Une fois le répertoire cloné effectuer une importation sous eclipse (import from git project).

Le projet tourne sous JBoss 7.1 donc sous jdk 7 maximum.
Dans le menu "Properties" d'éclipse configurer le java BuildPath:

+ Jboss 7.1 runtime
+ JRE system library 7

Pour démarrer le projet, il faut lancer JBoss  et déployer le projet.

## Principe de base

L'URL de la page de connexion est la suivante:
http://localhost:(your_JBoss7.1_port_number)/LeaveManagement/Employes/index.jsp

Le login et le mot de passe par défaut sont, respectivement, "coucou" et "hello".

Si les identifiants ne sont pas valides vous serez redirigés sur la page d'erreurs. Sinon, la redirection s'effectuera correctement vers la page de gestion des demandes de congés.
Il vous suffit alors d'entrer une date au format indiqué. Vous serez très vite fixé sur le statut de votre demande.

## Connexion

Concernant la gestion de la connexion, il existe deux méthodes:
+ Session
+ Session avec cookies (cocher "se souvenir de moi")

Dans le cas de la session simple vous serez, en cas d'inactivité, déconnecté après 50 secondes.  Si vous avez autorisé les cookies, ils revalideront automatiquement la session pendant 4 jours.


Le formulaire de la page `index.jsp` appelle le servlet `AuthentificationServlet`qui va:
+ vérifier les informations de connexion
+ si informations valides créer une session
+ si demande générer les cookies

```java
String login = request.getParameter("login");
String pwd = request.getParameter("pwd");

Boolean credential = myEmployes.checkCredentials(login, pwd);
HttpSession session = request.getSession();

/*On vérifie si l'utilisateur souhaite des cookies*/
if (request.getParameter(this.COOKIES_YES_OR_NO) != null) {
  /* Récupération de la date courante */
  Date dt = Calendar.getInstance().getTime();
  /* Formatage de la date et conversion en texte */
  SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_DATE);
  String dateConnexion = dateFormat.format(dt);

  /* Création du cookie, et ajout à la réponse HTTP */

  setCookie(response, this.COOKIE_CONNEXION_PWD, pwd, MAX_AGE_COOKIES);
  setCookie(response, this.COOKIE_CONNEXION_LOGIN, login, MAX_AGE_COOKIES);
} else {

  /*age 0 pour un cookie le détruit*/
  setCookie(response, this.COOKIE_CONNEXION_PWD, "", 0);
  setCookie(response, this.COOKIE_CONNEXION_LOGIN, "", 0);
}

/*On vérifie les infos de connexions*/
if (credential) {
  session.setAttribute(this.ATT_SESSION_USER, login);
  session.setMaxInactiveInterval(50);

  response.sendRedirect("/LeaveManagement/Conges/demandeConge.jsp");

} else {
  session.setAttribute(this.ATT_SESSION_USER, null);
  response.sendRedirect("/LeaveManagement/Employes/error.jsp");

}


```

## Déconnexion

Pour se déconnecter, il suffit simplement de cliquer sur "logout".
Le servlet `DeconnexionServlet` sera appelé. Il détruit la session et les éventuels cookies.


```java

protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

  /*age 0 pour un cookie le détruit*/
  setCookie(response, this.COOKIE_CONNEXION_PWD, "", 0);
  setCookie(response, this.COOKIE_CONNEXION_LOGIN, "", 0);

    /*Récupération et destruction de la session en cours*/
      HttpSession session = request.getSession();
      session.invalidate();


      /*Redirection vers l'index*/
      response.sendRedirect( "/LeaveManagement/Employes/index.jsp" );
}


```


## Redirection

Afin d'éviter un accès non-authentifié, nous avons un implémenté un système de filtre sur les pages contenues dans `WebContent/Conges` et redirige vers la page d'authentification. Le filtre a été implémenté dans `RestrictionFilter` et configuré sur `web.xml`.

`RestrictionFilter.java`
```java

public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
   HttpServletRequest request = (HttpServletRequest) req;

      HttpServletResponse response = (HttpServletResponse) res;
      HttpSession session= request.getSession();
  if (session.getAttribute(ATT_SESSION_USER)==null && AuthentificationServlet.getCookieValue(request, this.COOKIE_CONNEXION_LOGIN)==null) {
    response.sendRedirect("/LeaveManagement/Employes/index.jsp");
  }
  else{
    if(session.getAttribute(ATT_SESSION_USER)==null){
      String login = AuthentificationServlet.getCookieValue(request, this.COOKIE_CONNEXION_LOGIN);
      session.setAttribute(this.ATT_SESSION_USER, login);
      session.setMaxInactiveInterval(50);
    }
    chain.doFilter(request, response);
  }
}

```

`web.xml`

``` xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns="http://java.sun.com/xml/ns/javaee" xmlns:web="http://java.sun.com/xml/ns/javaee"
	xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd"
	id="WebApp_ID" version="3.0">
	<display-name>LeaveManagement</display-name>
	<welcome-file-list>
		<welcome-file>Employes/index.jsp</welcome-file>
	</welcome-file-list>
	<filter>
		<filter-name>RestrictionFilter</filter-name>
		<filter-class>controller.RestrictionFilter</filter-class>
	</filter>
	<filter-mapping>
		<filter-name>RestrictionFilter</filter-name>
		<url-pattern>/Conges/*</url-pattern>
	</filter-mapping>
</web-app>

```

## Beans

Les Beans sont `Employes` et `Conges`

Pour `Conges` nous avons repris le code proposé.

Nous avons défini un singleton `Employes` contenant une Map reliant login et mot de passe (hashé) ainsi que trois fonctions:
+ addEmploye

```java
public void addEmploye(String userName, String pwd){
  try {
    employe.put(userName, this.hashPassword(pwd));
  } catch (NoSuchAlgorithmException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
  } catch (UnsupportedEncodingException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
  }
}

```
+ checkCredentials

```java
public Boolean checkCredentials(String userName, String pwd){
  byte[] pass = employe.get(userName);
  byte[] hashpwd=null;
  try {
    hashpwd=this.hashPassword(pwd);
  } catch (NoSuchAlgorithmException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
  } catch (UnsupportedEncodingException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
  }

  if(Arrays.equals(pass,hashpwd))
  return true;
  else
  return false;

}

```
+ hashPassword

```java
public byte[] hashPassword(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
  MessageDigest digest = MessageDigest.getInstance("SHA-256");
  digest.reset();
  //On update le hash en utilisant une chaine de charactère spécifique
  digest.update("bytedigestbfeg".getBytes());
  byte[] input = digest.digest(password.getBytes("UTF-8"));
  //On boucle pour ralentir les hackers
  for (int i = 0; i < 200; i++) {
    digest.reset();
    input = digest.digest(input);
  }
  return input;
}
```
