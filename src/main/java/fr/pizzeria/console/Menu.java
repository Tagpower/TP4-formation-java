package main.java.fr.pizzeria.console;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

import main.java.fr.pizzeria.dao.IPizzaDao;
import main.java.fr.pizzeria.dao.PizzaDaoImpl;
import main.java.fr.pizzeria.dao.db.PizzaDaoBaseImpl;
import main.java.fr.pizzeria.exception.SavePizzaException;
import main.java.fr.pizzeria.exception.StockageException;
import main.java.fr.pizzeria.model.CategoriePizza;
import main.java.fr.pizzeria.model.Pizza;

public class Menu {
	
	private Map<Integer, OptionMenu> actions;
	private IPizzaDao dao;
	


	public Menu() {
		//dao = new PizzaDaoImpl();
		dao = new PizzaDaoBaseImpl();
		int lineNumber = 0;

		try { //Lecture du fichier de données
			BufferedReader buf = new BufferedReader(new FileReader("pizza.csv"));
			
			String line = "";
			StringTokenizer st = null;
			
			while((line = buf.readLine()) != null) { //Lecture ligne par ligne
				lineNumber++;
                st = new StringTokenizer(line, ";");
				
                if (line.trim().equals("")) {
                	continue;
                }
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
		actions.put(10, new QuitterOptionMenu()); //Quitter

	}
	
	//TODO sauvegarder le fichier
	public void sauvegarderPizzas() {
		BufferedWriter buf = null;
		FileWriter fw = null;
		
		try {
			fw = new FileWriter("pizza.csv");
			buf = new BufferedWriter(fw);
			
			for (Pizza p : dao.listAllPizzas()) {
				String line = "";
				line += p.getCode() + ";" + p.getNom() + ";" + p.getPrix() + ";" + p.getCategorie().toString() + "\n";
				buf.write(line);
			}					
			
			System.out.println("Fichier sauvegardé.");
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (buf != null) {
					buf.close();
				}
				if (fw != null) {
					fw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
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
		sauvegarderPizzas();
	}
	
	

}
