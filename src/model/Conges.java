package model;

public class Conges {	
	private static Conges instance = null;
	private Boolean jours [] ;
	
	/**
	* Constructeur public
	*/
	private Conges(){
		//Tableau de congés
		jours = new Boolean [365];
		
		//Init à true
		for (int i=0; i<jours.length; i++)
			jours[i]=Boolean.TRUE;
	}
	
	/**
	 * Instance
	* @return une instance de Conge
	*/
	public static Conges instance(){
		if(instance==null)
			instance = new Conges();
		return instance;
	}
	
	
	/**
	 * Vérifie la dispo du jour
	* @param jour du congé
	* @return vrai si le jour est dispo false si le jour est pris ou la valeur de jour n'est pas valide
	*/
	public boolean verifierJour(int jour){
		//Si jour 'existant'
		if((jour<365) && (jour>=0))
			//Retourne dispo du jour
			return jours[jour].booleanValue();
		
		//Sinon, Faux
		return Boolean.FALSE;
	}
	
	/**
	* @param jour du congé
	* la fonction permet de poser un jour de congé
	*/
	public void poserJour(int jour){
		//Set à FALSE
		jours[jour]=Boolean.FALSE;
	}
}
