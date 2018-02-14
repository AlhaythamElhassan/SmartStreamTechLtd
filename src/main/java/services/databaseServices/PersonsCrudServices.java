package services.databaseServices;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import org.hamcrest.core.IsInstanceOf;

import database.Database;
import domain.Person;
import services.files.ReaderWriter;

public class PersonsCrudServices implements CrudServices {
	
	Database database;
	
	public Database getDatabase() {
		return this.database;
	}

	public void setDatabase(Database database) {
		this.database = database;
	}

	public static void main(String[] args) {
		PersonsCrudServices services = new PersonsCrudServices();
		String inputDataFile = "src/main/resources/Person.data";
		ReaderWriter fileReaderWriter = new ReaderWriter(inputDataFile);
		ArrayList<Person> personRecords = fileReaderWriter.readFile();
		
		services.setDatabase(new Database());
		// connect to the db 
		services.getDatabase().connectToDb();
		// get database statement
//		services.createTable("Persons","PersonId int primary key, firstName varchar(20), lastName varchar(20), Street varchar(20), City varchar(20)");
		services.interstIntoTable("Persons", personRecords);
//		services.dropTable("Persons");
		
		
	}

	@Override
	public boolean createTable(String tableName, String properties) {
		boolean executed = false ;
		String sqlOperation = "create table " + tableName + "(" +  properties + ")";
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

	@Override
	public boolean interstIntoTable(String tableName, ArrayList records) {
		boolean isInserted = false;
		try {
			this.getDatabase().getStatement().execute(null);
			isInserted = true; 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isInserted;
	}

	@Override
	public ArrayList findAll(String tableName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean dropTable(String tableName) {
		boolean isDropped = false;
		System.out.println("Persons Crud services is trying to drop table " + tableName);
		try {
			this.getDatabase().getStatement().execute("drop table " + tableName);
			isDropped = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Person Crud services dropped table " + tableName + " is dropped = " + isDropped);
		return isDropped;
	}

}
