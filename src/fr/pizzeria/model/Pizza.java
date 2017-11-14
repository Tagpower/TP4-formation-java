package fr.pizzeria.model;

public class Pizza {
	private int id;
	private String code;
	private String nom;
	private double prix;
	
	private static int nbPizzas;
	
	public Pizza(String code, String nom, double prix) {
		this.id = nbPizzas++;
		this.code = code;
		this.nom = nom;
		this.prix = prix;
	}
	
	public static int getNbPizzas() {
		return nbPizzas;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}
	
	public Pizza remove() {
		nbPizzas--;
		return null;
	}
	
	public String toString() {
		return "" + this.code + " -> " + this.nom + " (" +  this.prix + "€)";
	}
}
