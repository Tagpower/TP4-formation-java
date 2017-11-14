package fr.pizzeria.console;

import java.util.Scanner;

import fr.pizzeria.dao.PizzaDaoImpl;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.Pizza;

public class ModifierPizzaOptionMenu extends OptionMenu {
	
	private PizzaDaoImpl dao;
	
	public ModifierPizzaOptionMenu(PizzaDaoImpl dao) {
		this.dao = dao;
		libelle = "3) Mettre à jour une pizza";
	}
	
	public boolean execute() throws UpdatePizzaException {
		System.out.println("Entrez le code de la pizza que vous voulez modifier :");
		for (Pizza p : dao.listAllPizzas()) {
			if (p != null) {				
				System.out.println(p.toString());
			}
		}
		Scanner sc = new Scanner(System.in);
		String cible = sc.nextLine();
		boolean found = false;
		if (dao.findPizza(cible) != null) {			
			System.out.println("Veuillez saisir le code :");
			String code = sc.nextLine();
			System.out.println("Veuillez saisir le nom (sans espace) :");
			String nom = sc.nextLine();
			System.out.println("Veuillez saisir le prix :");
			double prix = Double.valueOf(sc.nextLine());
			found = dao.updatePizza(cible, new Pizza(code, nom, prix));
			//found = true;
		} else {
			throw new UpdatePizzaException("La pizza " + cible + " n'existe pas");
		}
		return true;
	}

}
