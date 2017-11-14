package fr.pizzeria.console;

public class QuitterOptionMenu extends OptionMenu {

	public QuitterOptionMenu() {
		libelle = "99) Sortir";
	}

	@Override
	public boolean execute() {
		System.out.println("Au revoir !");
		return false;
	}

}
