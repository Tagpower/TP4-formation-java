package main.java.fr.pizzeria.console;

public class QuitterOptionMenu extends OptionMenu {

	public QuitterOptionMenu() {
		libelle = "10) Sortir";
	}

	@Override
	public boolean execute() {
		System.out.println("Au revoir !");
		return false;
	}

}
