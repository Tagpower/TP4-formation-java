package main.java.fr.pizzeria.dao.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import main.java.fr.pizzeria.dao.IPizzaDao;
import main.java.fr.pizzeria.exception.DeletePizzaException;
import main.java.fr.pizzeria.exception.SavePizzaException;
import main.java.fr.pizzeria.exception.StockageException;
import main.java.fr.pizzeria.exception.UpdatePizzaException;
import main.java.fr.pizzeria.model.CategoriePizza;
import main.java.fr.pizzeria.model.Pizza;


public class PizzaDaoBaseImpl implements IPizzaDao {
	
	private List<Pizza> pizzas;
	private ResourceBundle resource;
	private Connection conn;
	PreparedStatement stat;
	
	public PizzaDaoBaseImpl() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");	
			this.pizzas = new ArrayList<Pizza>();
			//conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/pizza", "root", "");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e.getMessage()); //faudrait remplacer par une exception qui hérite de RuntimeException
		}
	}
	
	public Connection getConnection() throws SQLException {
		if (conn == null || conn.isClosed()) {
			this.resource = ResourceBundle.getBundle("jdbc");
			conn = DriverManager.getConnection("" + resource.getString("protocol") + ":" 
												  + resource.getString("subprotocol") + "://"
												  + resource.getString("host") + ":"
												  + resource.getString("port") + "/"
												  + resource.getString("database"), resource.getString("user"), resource.getString("password") );

		}
		return conn;
	}

	public List<Pizza> listAllPizzas() {
		
		ArrayList<Pizza> result = new ArrayList<>();
		
		try {
			conn = getConnection();
			stat = conn.prepareStatement("SELECT * FROM pizza");

			ResultSet rs = stat.executeQuery();		
			
			//Parcours du set de résultats
			while (rs.next()) {
				result.add(new Pizza(rs.getInt("id"),
									 rs.getString("code"),
									 rs.getString("nom"),
									 rs.getDouble("prix"),
									 CategoriePizza.fromInt(rs.getInt("categorie"))));				
			}
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return result;
	}

	@Override
	public boolean saveNewPizza(Pizza p) throws SavePizzaException {
		boolean added = false;
		try {			
			conn = getConnection();

			stat = conn.prepareStatement("INSERT INTO pizza (code, nom, prix, categorie) VALUES (?, ?, ?, ?)");
			stat.setString(1, p.getCode());
			stat.setString(2, p.getNom());
			stat.setDouble(3, p.getPrix());
			stat.setInt(4, p.getCategorie().ordinal()+1); //Categorie
			
			added = (stat.executeUpdate() > 0);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return added;
	}


	@Override
	public Pizza findPizza(String codePizza) {
		Pizza result = null;
		
		try {
			conn = getConnection();

			stat = conn.prepareStatement("SELECT * FROM pizza WHERE code LIKE ?");
			stat.setString(1, codePizza);
			
			ResultSet rs = stat.executeQuery();		
			
			//Parcours du set de résultats
			if (rs.next()) {
				result = new Pizza(rs.getInt("id"),
						 rs.getString("code"),
						 rs.getString("nom"),
						 rs.getDouble("prix"),
						 CategoriePizza.fromInt(rs.getInt("categorie")));			
			}
		
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
				
		return result;
	}
	
	@Override
	public boolean updatePizza(String codePizza, Pizza p) throws UpdatePizzaException {
		boolean updated = false;
		try {
			conn = getConnection();

			stat = conn.prepareStatement("UPDATE pizza SET code=?, nom=?, prix=?, categorie=? WHERE code LIKE ?");
			stat.setString(1, p.getCode());
			stat.setString(2, p.getNom());
			stat.setDouble(3, p.getPrix());
			stat.setInt(4, p.getCategorie().ordinal()+1); //Categorie
			stat.setString(5, codePizza);
			
			updated = (stat.executeUpdate() > 0);		

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return updated;
	}

	@Override
	public boolean deletePizza(String codePizza) throws DeletePizzaException {
		boolean removed = false;
		try {
			conn = getConnection();

			stat = conn.prepareStatement("DELETE FROM pizza WHERE code LIKE ?");
			stat.setString(1, codePizza);
			
			removed = (stat.executeUpdate() > 0);		

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeConnection();
		}
		return removed;
	}
	
	public void closeConnection() {
		try {
			stat.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

  
	
	
	
}
