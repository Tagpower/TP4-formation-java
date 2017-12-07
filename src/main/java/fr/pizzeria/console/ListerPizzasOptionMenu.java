package main.java.fr.pizzeria.console;

import main.java.fr.pizzeria.dao.PizzaDaoImpl;
import main.java.fr.pizzeria.model.Pizza;

public class ListerPizzasOptionMenu extends OptionMenu {
	private PizzaDaoImpl dao;
	
	public ListerPizzasOptionMenu(PizzaDaoImpl dao) {
		this.dao = dao;
		libelle = "1) Lister les pizzas";
	}
	
	public boolean execute() {
		for (Pizza p : dao.listAllPizzas()) {
			if (p != null) {				
				System.out.println(p.toString());
			}
		}
		return true;
	}
	
}
