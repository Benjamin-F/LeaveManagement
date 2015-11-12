package employes;

import java.util.HashMap;
import java.util.Map;

public class Employes {
	
	private static Employes instance = null;
	private Map <String, String> employe;
	
	/**
	 * Constructeur
	 */
	private Employes(){
		super();
		employe = new HashMap<String, String>();
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
		employe.put(userName, pwd);
	}
	
	/**
	 * Vérification userName et pwd
	 * @param userName
	 * @param pwd
	 * @return boolean
	 */
	public Boolean checkCredentials(String userName, String pwd){
		String pass = employe.get(userName);
		
		if(pass == null)
			return false;
		else{
			if(pass.equals(pwd))
				return true;
			else
				return false;
		}
	}
}
