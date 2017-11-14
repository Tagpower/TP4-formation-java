package fr.pizzeria.console;

import java.util.Scanner;

import fr.pizzeria.dao.PizzaDaoImpl;
import fr.pizzeria.dao.PizzaNotFoundException;
import fr.pizzeria.model.Pizza;

public class ModifierPizzaOptionMenu extends OptionMenu {
	
	private PizzaDaoImpl dao;
	
	public ModifierPizzaOptionMenu(PizzaDaoImpl dao) {
		this.dao = dao;
	}
	
	public void execute() {
		System.out.println("Entrez le code de la pizza que vous voulez modifier :");
		for (Pizza p : dao.listAllPizzas()) {
			if (p != null) {				
				System.out.println(p.toString());
			}
		}
		Scanner sc = new Scanner(System.in);
		String cible = sc.nextLine();
		boolean found = false;
		Pizza pizz;
		try {
			pizz = dao.findPizza(cible);
			if (pizz != null) {			
				System.out.println("Veuillez saisir le code :");
				String code = sc.nextLine();
				System.out.println("Veuillez saisir le nom (sans espace) :");
				String nom = sc.nextLine();
				System.out.println("Veuillez saisir le prix :");
				double prix = Double.valueOf(sc.nextLine());
				dao.updatePizza(cible, new Pizza(code, nom, prix));
				found = true;
			}
		} catch (PizzaNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
