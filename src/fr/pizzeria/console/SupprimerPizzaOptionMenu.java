package fr.pizzeria.console;

import java.util.Scanner;

import fr.pizzeria.dao.PizzaDaoImpl;
import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.model.Pizza;

public class SupprimerPizzaOptionMenu extends OptionMenu {

	private PizzaDaoImpl dao;
	
	public SupprimerPizzaOptionMenu(PizzaDaoImpl dao) {
		this.dao = dao;
		libelle = "4) Supprimer une pizza";
	}
	
	public boolean execute() throws DeletePizzaException {
		System.out.println("Entrez le code de la pizza que vous voulez supprimer :");
		for (Pizza p : dao.listAllPizzas()) {
			if (p != null) {				
				System.out.println(p.toString());
			}
		}
		Scanner sc = new Scanner(System.in);
		String cible = sc.nextLine();
		return dao.deletePizza(cible);
	}
	
}
