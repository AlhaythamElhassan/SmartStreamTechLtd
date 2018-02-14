package services.databaseServices;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import org.hamcrest.core.IsInstanceOf;

import database.Database;
import domain.Order;
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
		ArrayList<Object> retrievedRecords; 
		
		services.setDatabase(new Database());
		// connect to the db 
		services.getDatabase().connectToDb();
		// get database statement
		services.createTable("Persons","PersonId int primary key, firstName varchar(20), lastName varchar(20), Street varchar(20), City varchar(20)");
		services.interstIntoTable("Persons", personRecords);
		retrievedRecords = services.findAll("Persons");
		retrievedRecords.forEach(record -> {
			System.out.println(record.toString());
		});
		services.dropTable("Persons");
		
		
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
			System.out.println("Trying to insert a records into table " + tableName);
			for(Object record: records) {
				String sqlOp = "insert into "
						+ tableName +
						" values ("
						+ record.toString()
						+ ")";
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

	@Override
	public ArrayList<Object> findAll(String tableName) {
		boolean finddAllOpStatus = false;
		ArrayList<Object> records = new ArrayList<Object>();
		try {
			System.out.println("Trying to find all records in table " + tableName);
			ResultSet resultSet = this.getDatabase().getStatement().executeQuery("select * from " + tableName);
			ResultSetMetaData metaData = resultSet.getMetaData();
			int columnCount = metaData.getColumnCount();
			while(resultSet.next()) {
				if (tableName == "Persons") {
					records.add(new Person(
							Integer.parseInt(resultSet.getObject(1).toString()),
							resultSet.getObject(2).toString(),
							resultSet.getObject(3).toString(),
							resultSet.getObject(4).toString(),
							resultSet.getObject(5).toString()));
				}
				if (tableName == "Orders") {
					records.add(new Order(
							Integer.parseInt(resultSet.getObject(1).toString()),
							Integer.parseInt(resultSet.getObject(2).toString()),
							Integer.parseInt(resultSet.getObject(3).toString())
					));
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
