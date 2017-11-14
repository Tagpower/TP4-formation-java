package fr.pizzeria.console;

import fr.pizzeria.dao.PizzaDaoImpl;
import fr.pizzeria.model.Pizza;

public class ListerPizzasOptionMenu extends OptionMenu {
	private PizzaDaoImpl dao;
	
	public ListerPizzasOptionMenu(PizzaDaoImpl dao) {
		this.dao = dao;
	}
	
	public void execute() {
		for (Pizza p : dao.listAllPizzas()) {
			if (p != null) {				
				System.out.println(p.toString());
			}
		}
	}
	
}
