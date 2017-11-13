package fr.pizzeria.console;

import java.util.Scanner;

import fr.pizzeria.model.Pizza;

public class PizzeriaAdminConsoleApp {
	
	static boolean running = true;
	static Scanner sc = new Scanner(System.in);
	static Pizza[] pizzas = new Pizza[100];


	public static void main(String[] args) {
		
		pizzas[0] = new Pizza("PEP", "Pepperoni", 12.5);
		pizzas[1] = new Pizza("MAR", "Margharita", 14);
		pizzas[2] = new Pizza("REIN", "La Reine", 11.5);
		pizzas[3] = new Pizza("FRO", "La 4 Fromages", 12);
		pizzas[4] = new Pizza("CAN", "La cannibale", 12.5);
		pizzas[5] = new Pizza("SAV", "La savoyarde", 13);
		pizzas[6] = new Pizza("ORI", "L'orientale", 13.5);
		pizzas[7] = new Pizza("IND", "L'indienne", 14);
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
			listPizzas();
			break;
		case "2": 
			System.out.println("Ajout d'une nouvelle pizza");
			addPizza();
			break;
		case "3":
			System.out.println("Mise à jour d'une pizza");
			updatePizza();
			break;
		case "4": 
			System.out.println("Suppression d'une pizza");
			deletePizza();
			break;
		case "99": 
			System.out.println("Au revoir !");
			running = false;
			break;
		default:
			System.out.println("Veuillez entrer une option valide.");
		}
	}
	
	public static void listPizzas() { //Option 1
		for (int i=0; i < pizzas.length; i++) {
			if (pizzas[i] != null) {				
				System.out.println(pizzas[i].toString());
			}
		}
	}
	
	public static void addPizza() { //Option 2
		System.out.println("Veuillez saisir le code :");
		String code = sc.nextLine();
		System.out.println("Veuillez saisir le nom (sans espace) :");
		String nom = sc.nextLine();
		System.out.println("Veuillez saisir le prix :");
		double prix = Double.valueOf(sc.nextLine());
		pizzas[Pizza.getNbPizzas()] = new Pizza(code, nom, prix);
	}
	
	public static void updatePizza() { //Option 3
		System.out.println("Entrez le code de la pizza que vous voulez modifier :");
		listPizzas();
		String cible = sc.nextLine();
		boolean found = false;
		for (int i=0; i<pizzas.length; i++) {
			if (pizzas[i] != null && pizzas[i].getCode().equals(cible)) {
				System.out.println("Veuillez saisir le code :");
				String code = sc.nextLine();
				System.out.println("Veuillez saisir le nom (sans espace) :");
				String nom = sc.nextLine();
				System.out.println("Veuillez saisir le prix :");
				double prix = Double.valueOf(sc.nextLine());
				pizzas[i].setCode(code);
				pizzas[i].setNom(nom);
				pizzas[i].setPrix(prix);
				found = true;
				break;
			}
		}
		if (!found) {
			System.out.println("Pizza non trouvée");
		}
	}
	
	public static void deletePizza() { //Option 4
		System.out.println("Entrez le code de la pizza que vous voulez supprimer :");
		listPizzas();
		String cible = sc.nextLine();
		for(int i=0; i<pizzas.length; i++) {
			if (pizzas[i] != null && pizzas[i].getCode().equals(cible)) {
				for (int j=i; j<pizzas.length-1; j++) {
					if (pizzas[j+1] != null) {
						pizzas[j] = pizzas[j+1];
					} else {
						pizzas[Pizza.getNbPizzas()-1].remove();
						pizzas[j] = null;
						break;
					}
				}
				break;
			}
		}
		
	}
}
