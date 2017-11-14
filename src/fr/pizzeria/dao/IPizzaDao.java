package fr.pizzeria.dao;

import fr.pizzeria.model.Pizza;

public interface IPizzaDao {

	Pizza[] listAllPizzas();
	
	Pizza findPizza(String codePizza) throws PizzaNotFoundException;
	
	boolean saveNewPizza(Pizza p);
	
	boolean updatePizza(String codePizza, Pizza p);
	
	boolean deletePizza(String codePizza);
	
}
