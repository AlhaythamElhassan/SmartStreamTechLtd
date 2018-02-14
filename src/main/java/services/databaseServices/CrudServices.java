package services.databaseServices;

import java.util.ArrayList;

public interface CrudServices {
	public boolean createTable(String tableName, String properties);
	public boolean interstIntoTable(String tableName, ArrayList records);
	public ArrayList<Object> findAll(String tableName);
	public boolean dropTable(String tableName);
}
