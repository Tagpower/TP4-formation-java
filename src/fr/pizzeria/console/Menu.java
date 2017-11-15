package fr.pizzeria.console;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;


import fr.pizzeria.dao.PizzaDaoImpl;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.StockageException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class Menu {
	
	private Map<Integer, OptionMenu> actions;
	private PizzaDaoImpl dao;
	


	public Menu() {
		dao = new PizzaDaoImpl();
		int lineNumber = 0;
		
//		try {
//			dao.saveNewPizza(new Pizza("PEP", "Pepperoni", 12.5, CategoriePizza.VIANDE));
//			dao.saveNewPizza(new Pizza("MAR", "Margharita", 14, CategoriePizza.VIANDE));
//			dao.saveNewPizza(new Pizza("REIN", "La Reine", 11.5, CategoriePizza.VIANDE));
//			dao.saveNewPizza(new Pizza("FRO", "La 4 Fromages", 12, CategoriePizza.SANS_VIANDE));
//			dao.saveNewPizza(new Pizza("CAN", "La cannibale", 12.5, CategoriePizza.VIANDE));
//			dao.saveNewPizza(new Pizza("SAV", "La savoyarde", 13, CategoriePizza.VIANDE));
//			dao.saveNewPizza(new Pizza("ORI", "L'orientale", 13.5, CategoriePizza.VIANDE));
//			dao.saveNewPizza(new Pizza("IND", "L'indienne", 14, CategoriePizza.VIANDE));
//		} catch (SavePizzaException e) {
//			e.printStackTrace();
//		}
		
		try { //Lecture du fichier de données
			BufferedReader buf = new BufferedReader(new FileReader("pizza.csv"));
			
			String line = "";
			StringTokenizer st = null;
			
			while((line = buf.readLine()) != null) { //Lecture ligne par ligne
				lineNumber++;
                st = new StringTokenizer(line, ";");
				
				String code = st.nextToken();
				String nom = st.nextToken();
				double prix = 0.0;
				try {
					prix = Double.parseDouble(st.nextToken()); //TODO Empêcher les prix négatifs
				} catch (NumberFormatException e) {
					System.out.println("Ligne " + lineNumber + ": Le prix n'est pas un nombre valide");
				}
				String categ_string = st.nextToken();
				
				CategoriePizza categ = CategoriePizza.VIANDE; //Par défaut, VIANDE
				for (CategoriePizza c : CategoriePizza.values()) {
					if (c.toString().equals(categ_string)) {
						categ = c; //Affecter la catégorie entrée
					}
				}
				
				dao.saveNewPizza(new Pizza(code, nom, prix, categ));
			}
			
			
			
		} catch (FileNotFoundException e) {
			System.out.println("Le fichier de données est absent.");
		} catch (SavePizzaException e) {
			System.out.println("Ligne " + lineNumber + ": " + e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
		

		actions = new HashMap<Integer, OptionMenu>();
		actions.put(1, new ListerPizzasOptionMenu(dao));
		actions.put(2, new AjouterPizzaOptionMenu(dao));
		actions.put(3, new ModifierPizzaOptionMenu(dao));
		actions.put(4, new SupprimerPizzaOptionMenu(dao));
		actions.put(99, new QuitterOptionMenu()); //Quitter

	}
	
	
	
	public void afficher() {
		Scanner sc = new Scanner(System.in);
		boolean continuer = true;
		
		while(continuer) {
			
			System.out.println("\n\n***** Pizzeria Administration *****");
			for (OptionMenu o : actions.values()) {
				if (o != null) {
					System.out.println(o.getLibelle());					
				}
			}
			try {
				int choix = Integer.parseInt(sc.nextLine());
				if (actions.get(choix) != null) {
					continuer = actions.get(choix).execute();				
				} else {
					System.out.println("Veuillez entrer une option valide.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Veuillez entrer une option valide.");
			} catch (StockageException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	

}
