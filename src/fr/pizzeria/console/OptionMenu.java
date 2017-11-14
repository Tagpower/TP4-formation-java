package fr.pizzeria.console;

import fr.pizzeria.exception.StockageException;

public abstract class OptionMenu {
	
	String libelle;

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public abstract boolean execute() throws StockageException;
	
}
