package fr.pizzeria.dao;

import java.util.ArrayList;
import java.util.List;

import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.Pizza;

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
		if (findPizza(p.getCode()) != null) {
			throw new SavePizzaException("Il existe déjà une pizza dont le code est " + p.getCode());
		}
		
		if (p.getPrix() > 0) {
			pizzas.add(p);
			//pizzas[Pizza.getNbPizzas()] = p;
		} else {
			throw new SavePizzaException("Le prix ne peut pas être négatif");
		}
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
					throw new UpdatePizzaException("Ilexiste déjà une pizza dont le code est " + pizza.getCode());
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
				p = p.remove();
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
