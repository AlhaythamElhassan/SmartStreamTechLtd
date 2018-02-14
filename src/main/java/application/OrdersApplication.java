package application;

import java.util.ArrayList;

import database.Database;
import domain.Order;
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
		fileReaderWriter.setFileName("src/main/resources/Order.data");
		ArrayList<Order> ordersRecords = fileReaderWriter.readFile("\\|");
		ArrayList<Object> retrievedPersonRecords, retrievedOrderRecords; 
		
		services.setDatabase(new Database());
		// connect to the db 
		services.getDatabase().connectToDb();
		// create table Persons
		services.createTable("Persons","PersonId int primary key, firstName varchar(20), lastName varchar(20), Street varchar(20), City varchar(20)");
		// create table orders 
		services.createTable("Orders", "ORDER_ID int primary key, ORDER_NO int, PERSON_ID int");
		// insert into table Persons
		services.interstIntoTable("Persons", personRecords);
		// insert into table Orders
		services.interstIntoTable("Orders", ordersRecords);
		// retrieve records from table Persons
		retrievedPersonRecords = services.findAll("Persons");
		// retrieve records from table Orders
		retrievedOrderRecords = services.findAll("Orders");
		// Printing Person records 
		retrievedPersonRecords.forEach(record -> {
			System.out.println(record.toString());
		});
		// Printing Order records
		retrievedOrderRecords.forEach(order -> {
			System.out.println(order.toString());
		});
		services.dropTable("Persons");
		services.dropTable("Orders");
	}

}


