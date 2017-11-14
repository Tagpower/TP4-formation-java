package fr.pizzeria.console;

import java.util.Scanner;

import fr.pizzeria.model.Pizza;
import fr.pizzeria.dao.PizzaDaoImpl;

public class PizzeriaAdminConsoleApp {
	
	static Menu menu;

	public static void main(String[] args) {
		
		menu = new Menu();
		menu.afficher();
		
	}
}
