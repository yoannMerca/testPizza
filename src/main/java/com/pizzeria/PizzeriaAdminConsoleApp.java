package com.pizzeria;

import java.util.Collections;
import java.util.Scanner;

import classe.CategoriePIzzaEnum;
import classe.Pizza;
import classe.Pizzeria;
import dao.CodeUpComparator;
import dao.PizzaMemDao;
import dao.PriceDownComparator;
import execption.DeletePizzaException;
import execption.SavePizzaException;
import execption.UpdatePizzaException;


public class PizzeriaAdminConsoleApp {

	
	static Scanner scan = new Scanner(System.in);
	
	
	
	public static void main(String[] args) {
		PizzaMemDao myPizzas = new PizzaMemDao();
	
		//pour mettre fin a la boucle 
		boolean end = false;
		/*
		 * methode pour afficher le menu 
		 */
	
		System.out.println(Pizza.displayMenu());

		
		/**
		 * boucle tant pour afficher le menu tant que l'utilisateur ne tape pas 99 
		 * */
		//scan.nextLine();
		while(!end) {
			System.out.println("----------------------");
			int value = scan.nextInt();
			switch (value) {
			/**
			 * affiche la liste des pizzas
			 */
			case 1:
				System.out.println("----------------------");
				System.out.println("tri par code croissant tapez 1\n\rtri par prix ordre decroissant tapez 2");
				scan.nextLine();
				int choice = scan.nextInt();
				if(choice==1) {
					//tri par ordre croissant le prix des pizzas
					 Collections.sort(myPizzas.findAllPizzas(), new CodeUpComparator());
					
				}else {
					//tri par ordre decroissant le prix des pizzas
					Collections.sort(myPizzas.findAllPizzas(), new PriceDownComparator());
				}
				System.out.println("Liste des pizzas:");
				Pizzeria.displayAllPizza(myPizzas.findAllPizzas());
				System.out.println(Pizza.displayMenu());
				break;
			/*
			 * 
			 * Ajoute une nouvelle pizza
			 * 
			 */
			case 2:
				/*  Veuillez saisir le code :
					Veuillez saisir le nom (sans espace) :
					Veuillez saisir le prix :
				*/
				System.out.println("----------------------");
				System.out.println("Ajout d’une nouvelle pizza");
				
				scan.nextLine();
				System.out.println("Veuillez saisir le code");
				String code = scan.nextLine();
				System.out.println("Veuillez saisir le nom (sans espace) :");
				String name = scan.nextLine();
				System.out.println("Veuillez le prix:");
				Double price = Double.valueOf(scan.nextLine());
				System.out.println("Veuillez saisir la categorie \n\r"
						+ "° tapez 1 pour Frommage \r\n"
						+ "° 2 pour Viande \r\n"
						+ "° 3 pour Poisson \r\n"
						+ "° 4 pour Autre \r\n\"");
				
				int categorie = scan.nextInt();
				CategoriePIzzaEnum cat = CategoriePIzzaEnum.getEnum(categorie);
				Pizza newPizza = new Pizza(code, name, price, cat);
				
				try {
					myPizzas.saveNewPizza(newPizza);
				} catch (SavePizzaException e) {
					
					System.err.println(e);
				}
				System.out.println(Pizza.displayMenu());
				break;
			/*
			 * 
			 * Mise a jour d'une pizza	
			 */
			case 3:
				
				System.out.println("----------------------");
				System.out.println("Mise à jour d’une pizza");
				Pizzeria.displayAllPizza(myPizzas.findAllPizzas());
				scan.nextLine();
				System.out.println("Veuillez saisir le code de la pizza à modifier");
				String oldCode = scan.nextLine();
				
				System.out.println("Veuillez saisir le nouveau code");
				code = scan.nextLine();
				System.out.println("Veuillez saisir le nouveau nom (sans espace) :");
				name = scan.nextLine();
				System.out.println("Veuillez le nouveau prix:");
				price = Double.valueOf(scan.nextLine());
				System.out.println("Veuillez saisir la categorie \n\r"
						+ "° tapez 1 pour Frommage \r\n"
						+ "° 2 pour Viande \r\n"
						+ "° 3 pour Poisson \r\n"
						+ "° 4 pour Autre \r\n\"");
				int catego = scan.nextInt();
				CategoriePIzzaEnum cate = CategoriePIzzaEnum.getEnum(catego);
				Pizza pizz = new Pizza(code, name, price, cate);
				try {
					myPizzas.updatePizza(oldCode,pizz);
				} catch (UpdatePizzaException e) {
				
					System.err.println(e);
				}
			
				System.out.println(Pizza.displayMenu());
				break;
			/*
			 * 
			 * Supprime une pizza
			 */
			case 4:
				System.out.println("----------------------");
				System.out.println("Suppression d’une pizza");
				Pizzeria.displayAllPizza(myPizzas.findAllPizzas());
				scan.nextLine();
				System.out.println("Veuillez saisir le code de la pizza à modifier");
				code = scan.nextLine();
				
				try {
					myPizzas.deletePizza(code);
				} catch (DeletePizzaException e) {
					System.err.println(e);
				}
				System.out.println(Pizza.displayMenu());
				break;	
			/*
			 * 
			 * Fin de la boucle (valeur par defaut)
			 */
			case 99:
			default:
				System.out.println("----------------------");
				System.out.println("Au revoir");
				end =true;
				break;
			}
			
		}
	}

}
