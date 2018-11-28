package dao;


import java.util.ArrayList;
import java.util.Arrays;

import classe.CategoriePIzzaEnum;
import classe.Pizza;
import execption.DeletePizzaException;
import execption.SavePizzaException;
import execption.StockageException;
import execption.UpdatePizzaException;

public class PizzaMemDao implements IPizzaDao {

	Pizza pizza1 = new Pizza("PEP", "Pépéroni", 12.50 , CategoriePIzzaEnum.AUTRE );
	Pizza pizza2 = new Pizza("MAR", "Margherita", 14.00,CategoriePIzzaEnum.AUTRE);
	Pizza pizza3 = new Pizza("REIN", "La Reine", 11.50, CategoriePIzzaEnum.AUTRE);
	Pizza pizza4 = new Pizza("FRO", "La 4 fromages", 12.00 ,CategoriePIzzaEnum.FROMAGE);
	Pizza pizza5 = new Pizza("CAN", "La cannibale", 12.50 , CategoriePIzzaEnum.VIANDE);
	Pizza pizza6 = new Pizza("ORI", "L'orientale", 13.50, CategoriePIzzaEnum.VIANDE);
	Pizza pizza7 = new Pizza("IND", "L'indienne", 14.00 ,CategoriePIzzaEnum.AUTRE);
	String code;
	String name;
	Double price;
	final int  CODE_LONG = 4;
	final int PRICE_MAX = 20;
	final int PRICE_MIN = 5;
	
	Pizza[] piz = { pizza1, pizza2, pizza3, pizza4, pizza5, pizza6, pizza7 };
	
	

	ArrayList<Pizza> pizzas = new ArrayList<Pizza>(Arrays.asList(piz));
	

	/**
	 *  retoune un arraylist de pizza
	 */
	public ArrayList<Pizza> findAllPizzas() {
		return pizzas;
	}
	/**
	 * Methode retournerun pizza selon son code
	 */
	public Pizza findPizzaByCode(String codePizza) {
		Pizza pizz = null;
		for (Pizza pizza : pizzas) {
			if(pizza.getCode().equals(codePizza)) {
				pizz  = pizza;
			}
		}
		return pizz;
	}
	
	/**
	 * Methode pour update une nouvelle pizza
	 */
	public void updatePizza(String codePizza, Pizza pizza) throws UpdatePizzaException {
		Pizza pizz = findPizzaByCode(codePizza);
		try {
			
			if(checkMyPizza(pizza) == true && checkMyPizza(pizz) == true) {
				pizz.setCode(pizza.getCode());
				pizz.setDesignation(pizza.getDesignation());
				pizz.setPrice(pizza.getPrice());
				pizz.setCategorie(pizza.getCategorie());
			}
		} catch (StockageException e) {
			
			throw new UpdatePizzaException(e.getMessage() +" l'update n'a pas abouti ");
		}
		
	}

	/**
	 * Methode pour delete une  pizza
	 */
	public void deletePizza(String codePizza) throws DeletePizzaException{	
		try {
			Pizza pizz = findPizzaByCode(codePizza);
			if(checkMyPizza(pizz) == true) {
				pizzas.remove(pizz);
			}
		} catch (StockageException e) {
			throw new DeletePizzaException(e.getMessage() +" la suppression n'a pas abouti ");
		}
		
	}
	/**
	 * Methode pour save une nouvelle pizza
	 */
	public void saveNewPizza(Pizza pizza) throws SavePizzaException{
			try {
				if(checkMyPizza(pizza) == true) {
					pizzas.add(pizza);
				}
			} catch (StockageException e) {
				throw new SavePizzaException(e.getMessage() +" l'ajout n'a pas abouti ");
			}
	}
	
	/**
	 * Methode pour savoir si une pizza existe
	 */
	public boolean pizzaExists(String codePizza) {
		boolean exist = false;
		if(findPizzaByCode(codePizza)!=null) {
			exist = true;
		}
	return exist;
	}
	
	
	/**
	 * Methode qui verifie l'integrité de ma pizza avant le crud et nous renvoi un essage d'erreur
	 * @param piz la pizza à vefifier
	 * @return si ma pizza est ok pour le crud
	 * @throws StockageException
	 */
	public boolean checkMyPizza(Pizza piz) throws StockageException{
		boolean ifOk= true;
		String message = "";
		
		if(piz == null) {
			message += "la pizza n'existe pas !";
		}else {
			if(piz.getCode().trim().length()> CODE_LONG) {
				message += "le code de la pizza est trop long, \n\r";
			}if(piz.getPrice()> PRICE_MAX) {
				message += "le prix de la pizza est trop grand, \n\r";
			}if(piz.getPrice()< PRICE_MIN) {
				message += "le prix de la pizza est trop petit, \n\r";
			}if(piz.getCategorie()==null) {
				message += "la categorie est nulle, \n\r";
			}
		}
		
		if(message.length()>0 && message != null) {
			ifOk = false;
			throw  new StockageException(message);
		}
			return ifOk;
	}

}
