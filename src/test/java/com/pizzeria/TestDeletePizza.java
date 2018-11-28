package com.pizzeria;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import classe.Pizza;
import dao.PizzaMemDao;
import execption.DeletePizzaException;

public class TestDeletePizza {

	PizzaMemDao testMemDao = new PizzaMemDao();
	ArrayList<Pizza> testAllPizzas = testMemDao.findAllPizzas();
	@Before
	public void setup() {
		testMemDao = new PizzaMemDao();
		testAllPizzas = testMemDao.findAllPizzas();
	}
	@Test (expected = DeletePizzaException.class)
	public void testDeletePizzaException() throws DeletePizzaException {
		testMemDao.deletePizza(null);
	}
	@Test
	public void testDeletePizza() {
		try {
			testMemDao.deletePizza("PEP");
		} catch (DeletePizzaException e) {
			System.out.println("testDeletePizza n'a pas fonctionné");
		}
	}
	@Test
	public void testDeletePizzaCheckCollection() {
		int pizzasSize = testAllPizzas.size();
		try {
			testMemDao.deletePizza("PEP");
			
			assertEquals(pizzasSize-1, testAllPizzas.size());
			
		} catch (DeletePizzaException e) {
			System.out.println("testDeletePizza n'a pas fonctionné");
		}
	}

}
