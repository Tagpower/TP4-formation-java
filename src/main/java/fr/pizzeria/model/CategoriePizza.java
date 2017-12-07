package main.java.fr.pizzeria.model;

public enum CategoriePizza {
	
	VIANDE("Viande"), POISSON("Poisson"), SANS_VIANDE("Sans viande");
	
	private String categorie;
	
	public static CategoriePizza fromInt(int x) {
		switch (x) {
		case 1:
			return VIANDE;
		case 2:
			return POISSON;
		case 3:
			return SANS_VIANDE;
		}
		return null;
	}
	
	public static int toInt(String s) {
		switch (s) {
		case "Viande":
			return 1;
		case "Poisson":
			return 2;
		case "Sans viande":
			return 3;
		}
		return 0;
	}
	
	private CategoriePizza(String s) {
		categorie = s;
	}
	
	String getCategorie() {
		return this.categorie;
	}
	
}
