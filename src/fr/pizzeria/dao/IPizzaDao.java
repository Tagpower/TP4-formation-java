package fr.pizzeria.dao;


import java.util.List;

import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.Pizza;

public interface IPizzaDao {

	List<Pizza> listAllPizzas();
	
	Pizza findPizza(String codePizza) throws StockageException;
	
	boolean saveNewPizza(Pizza p) throws SavePizzaException;
	
	boolean updatePizza(String codePizza, Pizza p) throws UpdatePizzaException;
	
	boolean deletePizza(String codePizza) throws DeletePizzaException;
	
}
