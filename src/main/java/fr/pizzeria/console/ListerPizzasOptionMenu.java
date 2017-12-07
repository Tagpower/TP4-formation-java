package main.java.fr.pizzeria.console;

import main.java.fr.pizzeria.dao.IPizzaDao;
import main.java.fr.pizzeria.dao.PizzaDaoImpl;
import main.java.fr.pizzeria.model.Pizza;

public class ListerPizzasOptionMenu extends OptionMenu {
	private IPizzaDao dao;
	
	public ListerPizzasOptionMenu(IPizzaDao dao) {
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
