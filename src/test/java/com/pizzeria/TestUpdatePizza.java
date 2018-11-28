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

	@Before
	public void setup() {
		testMemDao = new PizzaMemDao();
		testAllPizzas = testMemDao.findAllPizzas();
	}

	@Test

	public void monPremierTest() {
		Pizza piz = null;
		assertNull("test si null", piz);
	}

	@Test
	public void testFindAllPizza() {
		assertNotNull("test si null findAllPizza retourne bien un tableau de type pizza non null", testAllPizzas);
	}

	@Test
	public void testUpdatePizzaNullNull() {
		try {
			testMemDao.updatePizza(null, null);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Test(expected = UpdatePizzaException.class)
	public void testUpdatePizzaCodeNullTestException() throws UpdatePizzaException {
		Pizza piz = new Pizza("PLOP", "maPIzza", 12.5, CategoriePIzzaEnum.AUTRE);
		testMemDao.updatePizza(null, piz);
	}

	@Test(expected = UpdatePizzaException.class)
	public void testUpdatePizzaPizzaNullTestException() throws UpdatePizzaException {

		testMemDao.updatePizza("PEP", null);
	}

	@Test
	public void testUpdatePizzaPizzaNull() {
		try {
			Pizza piz = null;
			testMemDao.updatePizza("PEP", piz);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Test
	public void testUpdatePizza() {
		try {
			Pizza piz = new Pizza("PLOP", "maPIzza", 18.2, CategoriePIzzaEnum.AUTRE);
			testMemDao.updatePizza("PEP", piz);
			System.out.println("pizz ajouté");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	@Rule
	public ExpectedException exception = ExpectedException.none();

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
