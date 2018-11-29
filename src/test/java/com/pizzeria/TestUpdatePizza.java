package com.pizzeria;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import classe.CategoriePIzzaEnum;
import classe.Pizza;
import dao.PizzaMemDao;
import execption.UpdatePizzaException;

public class TestUpdatePizza {
	PizzaMemDao testMemDao = new PizzaMemDao();
	ArrayList<Pizza> testAllPizzas = testMemDao.findAllPizzas();

	/**Création de la liste de pizza pour les tests
	 * Qui sera réinitiliser à chaque test
	 */
	@Before
	public void setup() {
		testMemDao = new PizzaMemDao();
		testAllPizzas = testMemDao.findAllPizzas();
	}

	/**
	 * Essaie de test, pour savoir si la pizza est bien null
	 * si elle n'est pas initiliser.
	 */
	@Test
	public void monPremierTest() {
		Pizza piz = null;
		assertNull("test si null", piz);
	}

	/**
	 * Test pour savoir si la méthode testFindAllPizza renvoie bien un tableau
	 * de Pizza non null.
	 */
	@Test
	public void testFindAllPizza() {
		assertNotNull("test si null findAllPizza retourne bien un tableau de type pizza non null", testAllPizzas);
	}

	/**
	 * @throws UpdatePizzaException est renvoyé car la pizza est nul
	 * 
	 */
	@Test (expected = UpdatePizzaException.class)
	public void testUpdatePizzaNullNull() throws UpdatePizzaException {
			testMemDao.updatePizza(null, null);
	}

	/**
	 * testUpdatePizzaCodeNullTestException
	 * @throws UpdatePizzaException renvoie une exception
	 * l'ancien code est null.
	 */
	@Test(expected = UpdatePizzaException.class)
	public void testUpdatePizzaCodeNullTestException() throws UpdatePizzaException {
		Pizza piz = new Pizza("PLOP", "maPIzza", 12.5, CategoriePIzzaEnum.AUTRE);
		testMemDao.updatePizza(null, piz);
	}

	/**
	 * testUpdatePizzaPizzaNullTestException
	 * @throws UpdatePizzaException est retrouné car la pizza est null
	 */
	@Test(expected = UpdatePizzaException.class)
	public void testUpdatePizzaPizzaNullTestException() throws UpdatePizzaException {

		testMemDao.updatePizza("PEP", null);
	}

//	@Test
//	public void testUpdatePizzaPizzaNull() {
//		try {
//			Pizza piz = null;
//			testMemDao.updatePizza("PEP", piz);
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
//
//	}

		/**
		 * Création de la pizza effectué avec succès
		 * @throws UpdatePizzaException est l'excpetion qui devrait être levé
		 */
	@Test (expected = Test.None.class)
	public void testUpdatePizza() throws UpdatePizzaException {
			Pizza piz = new Pizza("PLOP", "maPIzza", 18.2, CategoriePIzzaEnum.AUTRE);
			testMemDao.updatePizza("PEP", piz);


	}

	/**
	 * Test du message renvoyé par le DataControl si le prix de la pizza est trop grand
	 */
	@Test
	public void testUpdatePizzaCodeNullTestExceptionPriceUP() {
		Pizza piz = new Pizza("PLOP", "maPIzza", 820.2, CategoriePIzzaEnum.AUTRE);
		try {
			testMemDao.updatePizza("PEP", piz);
		} catch (UpdatePizzaException e) {
			// exception.expectMessage("le prix de la pizza est trop grand,");
			assert (e.getMessage().contains("le prix de la pizza est trop grand,"));
			System.out.println(e.getMessage());
		}

	}
	
	/**
	 * Test du message renvoyé par le DataControl si le prix de la pizza est trop petite
	 */
	@Test
	public void testUpdatePizzaCodeNullTestExceptionPriceLow() {
		Pizza piz = new Pizza("PLOP", "maPIzza", -820.2, CategoriePIzzaEnum.AUTRE);
		try {
			testMemDao.updatePizza("PEP", piz);
		} catch (UpdatePizzaException e) {
			// exception.expectMessage("le prix de la pizza est trop grand,");
			assert (e.getMessage().contains("le prix de la pizza est trop petit,"));
			System.out.println(e.getMessage());
		}

	}

	/**
	 * Test du message renvoyé par le DataControl si le nouveau code de la pizza
	 * est supérieur à 4 caractères
	 */
	@Test
	public void testUpdatePizzaCodeNullTestExceptionCode() {
		Pizza piz = new Pizza("PLOPYY", "maPIzza", 18.2, CategoriePIzzaEnum.AUTRE);
		try {
			testMemDao.updatePizza("PEP", piz);
		} catch (UpdatePizzaException e) {
			// exception.expectMessage("le prix de la pizza est trop grand,");
			assert (e.getMessage().contains("le code de la pizza est trop long,"));
			System.out.println(e.getMessage());
		}

		/**
		 * Test du message renvoyé par le DataControl pour indiquer que 
		 * la catégorie est null
		 */
	}
	@Test
	public void testUpdatePizzaCodeNullTestExceptionCategorie() {
		Pizza piz = new Pizza("PLO", "maPIzza", 18.2, null);
		try {
			testMemDao.updatePizza("PEP", piz);
		} catch (UpdatePizzaException e) {
			System.out.println(e.getMessage());
			assert (e.getMessage().contains("la categorie est nulle,"));
			System.out.println(e.getMessage());
		}
		
	/**
	 * Création d'une secondz Liste, qui sera une copie de la liste des pizzas avant l'update
	 * Update d'une pizza valide puis vérification si les deux listes ne sont pas 
	 * identiques
	 */
	}
	@Test
	public void testUpdatePizzaCheckCollection() {
		ArrayList<Pizza> pizzasOld = new ArrayList<Pizza>();
		for (Pizza pizza : testAllPizzas) {
			pizzasOld.add(new Pizza(pizza));
		}
		//Pizza pizzasOld = new Pizza("PEP", "Pépéroni", 12.5, CategoriePIzzaEnum.AUTRE);
		//Pizza pizzasOld = new Pizza(testAllPizzas.get(0));
		Pizza piz = new Pizza("PEP", "maPIzza", 18.2, CategoriePIzzaEnum.AUTRE);
		try {
			testMemDao.updatePizza("PEP", piz);
			//System.out.println(pizzasOld);
			//System.err.println(testAllPizzas.get(0));
			//assertFalse(pizzasOld.equals(testAllPizzas.get(0)));
			assertFalse(Arrays.equals(pizzasOld.toArray(), testAllPizzas.toArray()));
		} catch (UpdatePizzaException e) {
			System.out.println("testDeletePizza n'a pas fonctionné");
		}
	}

}
