package services.databaseServices;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.Database;
import domain.Order;
import domain.Person;
import services.files.FileReader;

public class RecordsCrudServices implements CrudServices {

	Database database;

	public static void main(String[] args) {
		RecordsCrudServices services = new RecordsCrudServices();
		String inputDataFile = "src/main/resources/Person.data";
		FileReader fileReaderWriter = new FileReader(inputDataFile);
		ArrayList<Object> personRecords = fileReaderWriter.readFile();
		ArrayList<Object> retrievedRecords;

		services.setDatabase(new Database());
		// connect to the database
		services.getDatabase().connectToDb();
		// get database statement
		services.createTable("Persons",
				"PersonId int primary key, firstName varchar(20), lastName varchar(20), Street varchar(20), City varchar(20)");
		services.interstIntoTable("Persons", personRecords);
		retrievedRecords = services.findAll("Persons");
		retrievedRecords.forEach(record -> {
			System.out.println(record.toString());
		});
		services.dropTable("Persons");
	}

	public Database getDatabase() {
		return this.database;
	}

	public void setDatabase(Database database) {
		this.database = database;
	}

	/**
	 * Create a table
	 * 
	 * @param tableName
	 *            name of the table to be created
	 * @param properties
	 *            properties of the table fields
	 * @return return true if table created false otherwise
	 */
	@Override
	public boolean createTable(String tableName, String properties) {
		boolean executed = false;
		String sqlOperation = "create table " + tableName + "(" + properties + ")";
		System.out.println("Persons Crud services is trying to create table " + tableName);
		try {
			// create the table
			getDatabase().getStatement().execute(sqlOperation);
			executed = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Persons Crud services table " + tableName + " is created = " + executed);
		return executed;
	}

	/**
	 * @param tableName
	 *            name of the table to insert record/s into
	 * @param records
	 *            the records to be inserted
	 */
	@Override
	public boolean interstIntoTable(String tableName, ArrayList<Object> records) {
		boolean isInserted = false;
		try {
			System.out.println("Trying to insert a records into table " + tableName);
			for (Object record : records) {
				String sqlOp = "insert into " + tableName + " values (" + record.toString() + ")";
				this.getDatabase().getStatement().execute(sqlOp);
			}
			isInserted = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("records insertion status is " + isInserted);
		return isInserted;
	}

	/**
	 * find all record in a given table
	 * 
	 * @param tableName
	 *            name of the table
	 * @return ArrayList of the records found in the table
	 */
	@Override
	public ArrayList<Object> findAll(String tableName) {
		boolean finddAllOpStatus = false;
		ArrayList<Object> records = new ArrayList<Object>();
		try {
			System.out.println("Trying to find all records in table " + tableName);
			ResultSet resultSet = this.getDatabase().getStatement().executeQuery("select * from " + tableName);
			while (resultSet.next()) {
				if (tableName == "Persons") {
					records.add(new Person(Integer.parseInt(resultSet.getObject(1).toString()),
							resultSet.getObject(2).toString(), resultSet.getObject(3).toString(),
							resultSet.getObject(4).toString(), resultSet.getObject(5).toString()));
				}
				if (tableName == "Orders") {
					records.add(new Order(Integer.parseInt(resultSet.getObject(1).toString()),
							Integer.parseInt(resultSet.getObject(2).toString()),
							Integer.parseInt(resultSet.getObject(3).toString())));
				}
			}
			finddAllOpStatus = true;
		} catch (SQLException e) {
			System.out.println("error in finding records from table  " + tableName);
			e.printStackTrace();
		}
		System.out.println("find all operation status is " + finddAllOpStatus);
		return records;
	}

	/**
	 * Drop table
	 * 
	 * @param tableName
	 *            name of the table to be dropped
	 */
	@Override
	public boolean dropTable(String tableName) {
		boolean isDropped = false;
		System.out.println("Persons Crud services is trying to drop table " + tableName);
		try {
			this.getDatabase().getStatement().execute("drop table " + tableName);
			isDropped = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Person Crud services dropped table " + tableName + " is dropped = " + isDropped);
		return isDropped;
	}

}
