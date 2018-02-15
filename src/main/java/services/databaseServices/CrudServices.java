package services.databaseServices;

import java.util.ArrayList;

import database.Database;

/**
 * An interface to decouple Database component from the rest of the application
 * 
 * @author alhaytham
 *
 */
public interface CrudServices {
	public boolean createTable(String tableName, String properties);

	public boolean interstIntoTable(String tableName, ArrayList<Object> records);

	public ArrayList<Object> findAll(String tableName);

	public boolean dropTable(String tableName);

	public Database getDatabase();

	public void setDatabase(Database database);
}
