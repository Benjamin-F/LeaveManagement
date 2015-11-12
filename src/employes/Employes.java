package employes;

import java.util.HashMap;
import java.util.Map;

public class Employes {
	
	private Map <String, String> employe;
	
	/**
	 * Constructeur
	 */
	public Employes(){
		super();
		employe = new HashMap<String, String>();
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
		else 
			return true;
	}
}
