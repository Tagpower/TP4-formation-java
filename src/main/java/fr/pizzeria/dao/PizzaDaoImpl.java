package main.java.fr.pizzeria.dao;

import java.util.ArrayList;
import java.util.List;

import main.java.fr.pizzeria.exception.DeletePizzaException;
import main.java.fr.pizzeria.exception.SavePizzaException;
import main.java.fr.pizzeria.exception.UpdatePizzaException;
import main.java.fr.pizzeria.model.Pizza;


public class PizzaDaoImpl implements IPizzaDao {
	
	private List<Pizza> pizzas;
	
	public PizzaDaoImpl() {
		this.pizzas = new ArrayList<Pizza>();
	}

	public List<Pizza> listAllPizzas() {
		return pizzas;
	}

	@Override
	public boolean saveNewPizza(Pizza p) throws SavePizzaException {
		pizzas.add(p);
		return true;
	}


	@Override
	public Pizza findPizza(String codePizza) {
		for (Pizza p : pizzas) {
			if (p != null && p.getCode().equals(codePizza)) {
				return p;
			}
		}
		return null;
	}
	
	@Override
	public boolean updatePizza(String codePizza, Pizza pizza) throws UpdatePizzaException {
		boolean found = false;
		for(Pizza p : pizzas) {
			if (p.getCode().equals(codePizza)) {
				if (findPizza(pizza.getCode()) != null) {
					throw new UpdatePizzaException("Il existe déjà une pizza dont le code est " + pizza.getCode());
				}
				p.setCode(pizza.getCode());
				p.setNom(pizza.getNom());
				p.setPrix(pizza.getPrix());
				found = true;
				break;
			}
		}
		if (!found) {
			throw new UpdatePizzaException("La pizza " + codePizza + "n'existe pas");
		}
		return found;
	}

	@Override
	public boolean deletePizza(String codePizza) throws DeletePizzaException {
		boolean found = false;
		for(Pizza p : pizzas) {
			if (p.getCode().equals(codePizza)) {
				pizzas.remove(p);
				found = true;
				break;
			}
		}
		if (!found) {
			throw new DeletePizzaException("La pizza " + codePizza + " n'existe pas");
		}
		return found;
	}

  
	
	
	
}
