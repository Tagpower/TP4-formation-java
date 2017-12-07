package main.java.fr.pizzeria.console;

import java.util.Scanner;

import main.java.fr.pizzeria.dao.IPizzaDao;
import main.java.fr.pizzeria.dao.PizzaDaoImpl;
import main.java.fr.pizzeria.exception.UpdatePizzaException;
import main.java.fr.pizzeria.model.CategoriePizza;
import main.java.fr.pizzeria.model.Pizza;

public class ModifierPizzaOptionMenu extends OptionMenu {
	
	private IPizzaDao dao;
	
	public ModifierPizzaOptionMenu(IPizzaDao dao) {
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
			if (code.trim().equals("")) {
				throw new UpdatePizzaException("Le code ne doit pas être vide");
			}
			System.out.println("Veuillez saisir le nom (sans espace) :");
			String nom = sc.nextLine();
			System.out.println("Veuillez saisir le prix :");
			double prix = Double.valueOf(sc.nextLine());
			System.out.println("Veuillez saisir la catégorie de la pizza :\n(1=Viande, 2=Poisson, 3=Sans viande)");
			CategoriePizza categ;
			switch (Integer.parseInt(sc.nextLine())) {
			case 1:
				categ = CategoriePizza.VIANDE;
				break;
			case 2:
				categ = CategoriePizza.POISSON;
				break;
			case 3:
				categ = CategoriePizza.SANS_VIANDE;
				break;
			default:
				throw new UpdatePizzaException("Catégorie incorrecte");
			}
			found = dao.updatePizza(cible, new Pizza(code, nom, prix, categ));
			//found = true;
		} else {
			throw new UpdatePizzaException("La pizza " + cible + " n'existe pas");
		}
		return true;
	}

}
