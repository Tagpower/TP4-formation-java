package fr.pizzeria.dao;

import fr.pizzeria.model.Pizza;

public class PizzaDaoImpl implements IPizzaDao {
	
	private Pizza[] pizzas;
	
	public PizzaDaoImpl() {
		this.pizzas = new Pizza[100];
	}

	public Pizza[] listAllPizzas() {
		return pizzas;
	}

	@Override
	public boolean saveNewPizza(Pizza p) {
		pizzas[Pizza.getNbPizzas()] = p;
		return false;
	}


	@Override
	public Pizza findPizza(String codePizza) throws PizzaNotFoundException {
		for (Pizza p : pizzas) {
			if (p != null && p.getCode().equals(codePizza)) {
				return p;
			}
		}
		throw new PizzaNotFoundException("La pizza " + codePizza + " n'existe pas");
	}
	
	@Override
	public boolean updatePizza(String codePizza, Pizza p) {
		boolean found = false;
		for(int i=0; i<pizzas.length; i++) {
			if (pizzas[i] != null && pizzas[i].getCode().equals(codePizza)) {
				pizzas[i].setCode(p.getCode());
				pizzas[i].setNom(p.getNom());
				pizzas[i].setPrix(p.getPrix());
				found = true;
				break;
			}
		}
		if (!found) {
			System.out.println("Pizza non trouvée");
		}
		return found;
	}

	@Override
	public boolean deletePizza(String codePizza) {
		boolean found = false;
		for(int i=0; i<pizzas.length; i++) {
			if (pizzas[i] != null && pizzas[i].getCode().equals(codePizza)) {
				for (int j=i; j<pizzas.length-1; j++) {
					if (pizzas[j+1] != null) {
						pizzas[j] = pizzas[j+1];
					} else {
						pizzas[Pizza.getNbPizzas()-1].remove();
						pizzas[j] = null;
						break;
					}
				}
				found = true;
				break;
			}
		}
		if (!found) {
			System.out.println("Pizza non trouvée");
		}
		return found;
	}

  
	
	
	
}
