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
	private String url = "jdbc:derby://localhost:1527/Orders;create=true";
	private String userName = "app";
	private String password = "secetet";
	public static void main(String[] args) {
		System.out.println("Logging form class database.Database");
		Database ordersDb = new Database();
		ordersDb.connectToDb();
	}
	
	public void connectToDb() {
		Connection dbConn; 
		try {
			dbConn = getConnection(); 
			try {
				Statement stat = dbConn.createStatement();
//				stat.execute("USE Orders");
				stat.execute("create table Persons(Personid int, firstName varchar(20))");
				
				//stat.executeUpdate("DROP TABLE Persons");
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("SQL exception");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
		}
	}
/**
 * Gets a connection properties specified in the file database.properties.
 * @return the database connection
 * @throws SQLException 
 */
	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection(this.url,this.userName, this.password);
	}

}
