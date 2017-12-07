package main.java.fr.pizzeria.console;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import main.java.fr.pizzeria.dao.IPizzaDao;
import main.java.fr.pizzeria.dao.PizzaDaoImpl;
import main.java.fr.pizzeria.dao.db.PizzaDaoBaseImpl;
import main.java.fr.pizzeria.exception.SavePizzaException;
import main.java.fr.pizzeria.exception.StockageException;
import main.java.fr.pizzeria.model.CategoriePizza;
import main.java.fr.pizzeria.model.Pizza;

public class MenuBDD {
	
	private Map<Integer, OptionMenu> actions;
	private IPizzaDao dao;
	


	public MenuBDD() {
		//dao = new PizzaDaoImpl();
		dao = new PizzaDaoBaseImpl();		

		actions = new HashMap<Integer, OptionMenu>();
		actions.put(1, new ListerPizzasOptionMenu(dao));
		actions.put(2, new AjouterPizzaOptionMenu(dao));
		actions.put(3, new ModifierPizzaOptionMenu(dao));
		actions.put(4, new SupprimerPizzaOptionMenu(dao));
		actions.put(10, new QuitterOptionMenu()); //Quitter

	}
	
	
	public void afficher() {
		Scanner sc = new Scanner(System.in);
		boolean continuer = true;
		
		while(continuer) {
			
			System.out.println("\n\n***** Pizzeria Administration *****");
			for (OptionMenu o : actions.values()) {
				if (o != null) {
					System.out.println(o.getLibelle());					
				}
			}
			try {
				int choix = Integer.parseInt(sc.nextLine());
				if (actions.get(choix) != null) {
					continuer = actions.get(choix).execute();				
				} else {
					System.out.println("Veuillez entrer une option valide.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Veuillez entrer une option valide.");
			} catch (StockageException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	

}
