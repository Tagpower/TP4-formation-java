package main.java.fr.pizzeria.console;

import java.util.Scanner;

import main.java.fr.pizzeria.dao.IPizzaDao;
import main.java.fr.pizzeria.dao.PizzaDaoImpl;
import main.java.fr.pizzeria.exception.SavePizzaException;
import main.java.fr.pizzeria.model.CategoriePizza;
import main.java.fr.pizzeria.model.Pizza;



public class AjouterPizzaOptionMenu extends OptionMenu {
	private IPizzaDao dao;

	public AjouterPizzaOptionMenu(IPizzaDao dao) {
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
		if (dao.findPizza(code) != null) {
			throw new SavePizzaException("Il existe déjà une pizza dont le code est " + code);
		}
		System.out.println("Veuillez saisir le nom (sans espace) :");
		String nom = sc.nextLine();
		System.out.println("Veuillez saisir le prix :");
		double prix = Double.valueOf(sc.nextLine());
		if (prix <= 0) {
			throw new SavePizzaException("Le prix ne peut pas être négatif");
		}
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
