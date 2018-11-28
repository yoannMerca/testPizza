package classe;

import java.util.ArrayList;

public class Pizzeria  {
	
	
	public static void displayAllPizza(ArrayList<Pizza> pizzas) {

		for (Pizza pizza : pizzas) {
			System.out.println(pizza.toString());
		}
	}
	
}
