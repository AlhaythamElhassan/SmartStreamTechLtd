package database;

import java.sql.*;

/**
 * 
 * @author alhaytham
 * Before instantiating this class one have to make sure database 
 * service is running or otherwise the program will quite with a 
 * message and this statement is one of the test cases
 */
public class Database {
	String dbName = "Orders";
	private String url = "jdbc:postgresql:orders";
	private String userName = "SamrtStreamt";
	private String password = "S12345ltd";
	public static void main(String[] args) {
		System.out.println("Logging form class database.Database");

	}
	
	public void connectToDb() {
		Connection dbConn; 
		try {
			dbConn = getConnection(); 
			try {
				Statement stat = dbConn.createStatement();
				stat.executeUpdate("CREATE TABLE Persons ("
						+ "PersonId INT,"
						+ "FirstName VARCHAR(255),"
						+ "LastName VARCHAR(255),"
						+ "Street VARCHAR(255),"
						+ "City VARCHAR(255)"
						+ ");");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
/**
 * Gets a connection properties specified in the file database.properties.
 * @return the database connection
 * @throws SQLException 
 */
	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url,userName, password);
	}

}
