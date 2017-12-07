package main.java.fr.pizzeria.model;
import java.lang.reflect.Field;

/**
 * Classe représentant une pizza proposée par la pizzeria.
 * 
 * @author ETY0004
 *
 */

public class Pizza {
	private int id;
	@ToString(uppercase=true)
	private String code;
	@ToString
	private String nom;
	@ToString
	private CategoriePizza categorie;
	@ToString
	private double prix;
	private static int nbPizzas;
	
	public Pizza(String code, String nom, double prix, CategoriePizza categ) {
		this.id = nbPizzas++;
		this.code = code;
		this.nom = nom;
		this.categorie = categ;
		this.prix = prix;
	}
	
	public Pizza(int id, String code, String nom, double prix, CategoriePizza categ) {
		this.id = id;
		this.code = code;
		this.nom = nom;
		this.categorie = categ;
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
	
	public CategoriePizza getCategorie() {
		return categorie;
	}

	public void setCategorie(CategoriePizza categorie) {
		this.categorie = categorie;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		Class<Pizza> obj = Pizza.class;
		try {
			for (Field f : this.getClass().getDeclaredFields()) {
				if (f.isAnnotationPresent(ToString.class)) {
					String res = "";
					switch(f.getName()) {
					case "id":
						res += f.get(this) + ":";
						break;
					case "code":
						res += f.get(this) + " -> ";
						break;
					case "nom":
						res += f.get(this) + " ";
						break;
					case "categorie":
						res += "- " + categorie.getCategorie() + " ";
						break;
					case "prix":
						res += "(" + f.get(this) + " €) ";
						break;
					default:
						res += f.get(this)+ " ";
						break; 
					}
					if (f.getAnnotation(ToString.class).uppercase()) {
						sb.append(res.toUpperCase());
					} else {
						sb.append(res);						
					}
				}
			}
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return sb.toString();
		
		
		//return "" + this.code + " -> " + this.nom + " - " + this.categorie.getCategorie() + " (" +  this.prix + " €)";
	}
}
