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
	
	Statement statement;
	String dbName = "Orders";
	private String url = "jdbc:derby://localhost:1527/" + dbName + ";create=true";
	private String userName = "app";
	private String password = "secetet";
	
	public Statement getStatement() throws SQLException {
		return this.getConnection().createStatement();
	}

	public void setStatement(Statement statement) {
		this.statement = statement;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
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
				this.statement = dbConn.createStatement();
			} catch (SQLException e) {
				System.out.println("Internal SQL error");
			}
		} catch (SQLException e1) {
			System.out.println("could not connect to dataase system is exiting ...");
			System.out.println("Please make sure that the databse server is running");
			System.exit(0);
		}
	}
/**
 * Gets a connection
 * @return the database connection
 * @throws SQLException 
 */
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(this.url,this.userName, this.password);
	}

}
