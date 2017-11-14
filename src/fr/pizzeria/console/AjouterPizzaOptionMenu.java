package fr.pizzeria.console;

import java.util.NoSuchElementException;
import java.util.Scanner;

import fr.pizzeria.dao.PizzaDaoImpl;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.model.Pizza;

public class AjouterPizzaOptionMenu extends OptionMenu {
	private PizzaDaoImpl dao;

	public AjouterPizzaOptionMenu(PizzaDaoImpl dao) {
		this.dao = dao;
		this.libelle = "2) Ajouter une nouvelle pizza";
	}

	public boolean execute() throws SavePizzaException {
		
		Scanner sc = new Scanner(System.in);
		try {
			System.out.println("Veuillez saisir le code :");
			String code = sc.nextLine();
			System.out.println("Veuillez saisir le nom (sans espace) :");
			String nom = sc.nextLine();
			System.out.println("Veuillez saisir le prix :");
			double prix = Double.valueOf(sc.nextLine());			
			dao.saveNewPizza(new Pizza(code, nom, prix));
		} catch (SavePizzaException e) {
			System.out.println(e.getMessage());
		}
		return true;
	}
	
}
