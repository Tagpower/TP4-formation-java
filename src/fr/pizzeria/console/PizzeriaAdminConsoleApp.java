package fr.pizzeria.console;

import java.util.Scanner;

import fr.pizzeria.model.Pizza;
import fr.pizzeria.dao.PizzaDaoImpl;

public class PizzeriaAdminConsoleApp {
	
	static boolean running = true;
	static Scanner sc = new Scanner(System.in);
	//static Pizza[] pizzas = new Pizza[100];
	static PizzaDaoImpl pizzas;


	public static void main(String[] args) {
		
		pizzas = new PizzaDaoImpl();
		
		
		pizzas.saveNewPizza(new Pizza("PEP", "Pepperoni", 12.5));
		pizzas.saveNewPizza(new Pizza("MAR", "Margharita", 14));
		pizzas.saveNewPizza(new Pizza("REIN", "La Reine", 11.5));
		pizzas.saveNewPizza(new Pizza("FRO", "La 4 Fromages", 12));
		pizzas.saveNewPizza(new Pizza("CAN", "La cannibale", 12.5));
		pizzas.saveNewPizza(new Pizza("SAV", "La savoyarde", 13));
		pizzas.saveNewPizza(new Pizza("ORI", "L'orientale", 13.5));
		pizzas.saveNewPizza(new Pizza("IND", "L'indienne", 14));
		while (running) {
			callMenu();
		}
		
	}

	
	public static void callMenu() {
		System.out.println("\n\n***** Pizzeria Administration *****\n"
				+ "1) Lister les pizzas\n"
				+ "2) Ajouter une nouvelle pizza\n"
				+ "3) Mettre à jour une pizza\n"
				+ "4) Supprimer une pizza\n"
				+ "99) Sortir");
		
		String choix = sc.nextLine();
		
		switch(choix) {
		case "1": 
			System.out.println("Liste des pizzas");
			//listPizzas();
			new ListerPizzasOptionMenu(pizzas).execute();
			break;
		case "2": 
			System.out.println("Ajout d'une nouvelle pizza");
			//addPizza();
			new AjouterPizzaOptionMenu(pizzas).execute();
			break;
		case "3":
			System.out.println("Mise à jour d'une pizza");
			//updatePizza();
			new ModifierPizzaOptionMenu(pizzas).execute();
			break;
		case "4": 
			System.out.println("Suppression d'une pizza");
			//deletePizza();
			new SupprimerPizzaOptionMenu(pizzas).execute();
			break;
		case "99": 
			System.out.println("Au revoir !");
			running = false;
			break;
		default:
			System.out.println("Veuillez entrer une option valide.");
		}
	}
}
