package beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class connection_db {
	public static Connection connecter() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch(ClassNotFoundException e){}
		
		String url = "jdbc:mysql://localhost:3306/gestion_scolaire";

		String utilisateur = "root";
		String motDePasse = "";
		Connection connexion = null;
		try {
			connexion = DriverManager.getConnection(url, utilisateur, motDePasse);
		} catch (SQLException e) {
		} finally {
			
		}
		return connexion;
	}
}
