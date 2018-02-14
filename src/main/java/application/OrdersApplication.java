package application;

import java.util.ArrayList;

import database.Database;
import domain.Person;
import services.databaseServices.PersonsCrudServices;
import services.files.ReaderWriter;


public class OrdersApplication {
	/**
	 * @param args
	 */
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

}


