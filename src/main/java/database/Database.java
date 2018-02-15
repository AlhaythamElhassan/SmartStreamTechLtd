package database;

import java.sql.*;

/**
 * @author alhaytham This component is responsible from connecting The program
 *         to the database Before instantiating this class one have to make sure
 *         database service is running or otherwise the program will quite with
 *         a message and this statement is one of the test cases
 */
public class Database {

	Statement statement;
	String dbName = "Orders";
	int portNumber = 1527;
	private String url = "jdbc:derby://localhost:" + portNumber + "/" + dbName + ";create=true";
	private String userName = "app";
	private String password = "seceret";

	public static void main(String[] args) {
		System.out.println("Logging form class database.Database");
		Database ordersDb = new Database();
		ordersDb.connectToDb();
	}

	public int getPortNumber() {
		return portNumber;
	}

	public void setPortNumber(int portNumber) {
		this.portNumber = portNumber;
	}

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

	/**
	 * This method connect to the database instant and set the statement field to a
	 * newly created Statement or exit the application with a message
	 */
	public void connectToDb() {
		Connection dbConn;
		try {
			dbConn = getConnection();
			try {
				this.statement = dbConn.createStatement();
				System.out.println("connected successfully to database" + getDbName());
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
	 * Gets a connection to a database using url, userName and password fields of
	 * this class
	 * 
	 * @return the database connection
	 * @throws SQLException
	 *             if couldn't get the connection instant
	 */
	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(this.url, this.userName, this.password);
	}

}
