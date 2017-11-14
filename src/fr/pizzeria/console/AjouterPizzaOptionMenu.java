package fr.pizzeria.console;

import java.util.NoSuchElementException;
import java.util.Scanner;

import fr.pizzeria.dao.PizzaDaoImpl;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class AjouterPizzaOptionMenu extends OptionMenu {
	private PizzaDaoImpl dao;

	public AjouterPizzaOptionMenu(PizzaDaoImpl dao) {
		this.dao = dao;
		this.libelle = "2) Ajouter une nouvelle pizza";
	}

	public boolean execute() throws SavePizzaException {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Veuillez saisir le code :");
		String code = sc.nextLine();
		if (code.trim().equals("")) {
			throw new SavePizzaException("Le code ne doit pas être vide");				
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
			throw new SavePizzaException("Catégorie incorrecte");
		}
		dao.saveNewPizza(new Pizza(code, nom, prix, categ));
		return true;
	}
	
}
