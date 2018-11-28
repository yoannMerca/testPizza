package dao;

import java.util.Comparator;

import classe.Pizza;

public class CodeUpComparator implements Comparator<Pizza> {
	public CodeUpComparator(){
		
	}
	
// trie le prix des pizzas par orde croissant
	
	
	public int compare(Pizza p1, Pizza p2) {
		int result = p1.getCode().compareTo(p2.getCode());
	  	return result;
	}
}
