package dao;


import java.util.ArrayList;

import classe.Pizza;
import execption.DeletePizzaException;
import execption.SavePizzaException;
import execption.UpdatePizzaException;

public interface IPizzaDao {
	ArrayList<Pizza> findAllPizzas();

	void saveNewPizza(Pizza pizza) throws SavePizzaException;

	void updatePizza(String codePizza, Pizza pizza) throws UpdatePizzaException;

	void deletePizza(String codePizza) throws DeletePizzaException;

	Pizza findPizzaByCode(String codePizza);

	boolean pizzaExists(String codePizza);
}
