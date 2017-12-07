package main.java.fr.pizzeria.dao;


import java.util.List;

import main.java.fr.pizzeria.exception.DeletePizzaException;
import main.java.fr.pizzeria.exception.SavePizzaException;
import main.java.fr.pizzeria.exception.StockageException;
import main.java.fr.pizzeria.exception.UpdatePizzaException;
import main.java.fr.pizzeria.model.Pizza;

public interface IPizzaDao {

	List<Pizza> listAllPizzas();
	
	Pizza findPizza(String codePizza); 
	
	boolean saveNewPizza(Pizza p) throws SavePizzaException;
	
	boolean updatePizza(String codePizza, Pizza p) throws UpdatePizzaException;
	
	boolean deletePizza(String codePizza) throws DeletePizzaException;
	
}
