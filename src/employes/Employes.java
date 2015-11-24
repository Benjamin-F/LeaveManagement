package employes;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;




public class Employes {

	private static Employes instance = null;
	private Map <String, byte[]> employe;


	/**
	* Constructeur
	*/
	private Employes(){
		super();
		employe = new HashMap<String, byte[]>();
	}

	/**
	* @return une instance de Employe
	*/
	public static Employes instance(){
		if(instance==null)
		instance = new Employes();
		return instance;
	}

	/**
	* Ajoute un employé
	* @param userName
	* @param pwd
	*/
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

	/**
	* Vérification userName et pwd
	* @param userName
	* @param pwd
	* @return boolean
	*/
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
	/**
	*
	* @param password
	* @return Le mot de passe sous forme de hash
	* @throws NoSuchAlgorithmException
	* @throws UnsupportedEncodingException
	*/
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
}
