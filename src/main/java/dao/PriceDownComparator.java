package dao;

import java.util.Comparator;

import classe.Pizza;

public class PriceDownComparator implements Comparator<Pizza> {
	public PriceDownComparator(){
		
	}

	///trie par orde decroissant 
	public int compare(Pizza p1, Pizza p2) {
		
		int result = p2.getPrice().compareTo(p1.getPrice());
		 
	  	return result;
	}
}
