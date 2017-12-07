package main.java.fr.pizzeria.model;

public enum CategoriePizza {
	
	VIANDE("Viande"), POISSON("Poisson"), SANS_VIANDE("Sans viande");
	
	private String categorie;
	
	private CategoriePizza(String s) {
		categorie = s;
	}
	
	String getCategorie() {
		return this.categorie;
	}
	
}
