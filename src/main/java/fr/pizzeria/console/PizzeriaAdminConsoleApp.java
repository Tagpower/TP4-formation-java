package main.java.fr.pizzeria.console;

public class PizzeriaAdminConsoleApp {
	
	//static Menu menu;
	static MenuBDD menu;
	
	public static void main(String[] args) {
		
		//menu = new Menu();
		menu = new MenuBDD();
		menu.afficher();
		
	}
}
