package application;

import java.util.ArrayList;

import database.Database;
import domain.Order;
import domain.Person;
import services.Filtering.FilteringServices;
import services.databaseServices.PersonsCrudServices;
import services.files.ReaderWriter;


public class OrdersApplication {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PersonsCrudServices services = new PersonsCrudServices();
		FilteringServices filteringServices = new FilteringServices(); 
		String inputDataFile = "src/main/resources/Person.data";
		ReaderWriter fileReaderWriter = new ReaderWriter(inputDataFile);
		ArrayList<Person> personRecords = fileReaderWriter.readFile();
		fileReaderWriter.setFileName("src/main/resources/Order.data");
		ArrayList<Order> ordersRecords = fileReaderWriter.readFile("\\|");
		ArrayList<Object> retrievedPersonRecords,
						retrievedOrderRecords,
						personsWithAtLeastOneOrder,
						ordersWithFirstNameOfCorrespondingPerson;
		
		services.setDatabase(new Database());
		// connect to the db 
		services.getDatabase().connectToDb();
		// create table Persons
		System.out.println("########################################################");
		System.out.println("Creating Tables");
		System.out.println("########################################################");
		services.createTable("Persons","PersonId int primary key, firstName varchar(20), lastName varchar(20), Street varchar(20), City varchar(20)");
		// create table orders 
		services.createTable("Orders", "ORDER_ID int primary key, ORDER_NO int, PERSON_ID int");
		System.out.println("########################################################");
		System.out.println("Finished creating tables");
		System.out.println("########################################################");
		// insert into table Persons
		System.out.println("########################################################");
		System.out.println("########################################################");
		services.interstIntoTable("Persons", personRecords);
		// insert into table Orders
		services.interstIntoTable("Orders", ordersRecords);
		System.out.println("########################################################");
		System.out.println("########################################################");
		// retrieve records from table Persons
		retrievedPersonRecords = services.findAll("Persons");
		// retrieve records from table Orders
		retrievedOrderRecords = services.findAll("Orders");
		System.out.println("########################################################");
		System.out.println("########################################################");
		// Printing Person records 
		retrievedPersonRecords.forEach(record -> {
			System.out.println(record.toString());
		});
		// Printing Order records
		retrievedOrderRecords.forEach(order -> {
			System.out.println(order.toString());
		});
		System.out.println("########################################################");
		System.out.println("Persons with at least one Order");
		System.out.println("########################################################");
		personsWithAtLeastOneOrder = filteringServices.getPersonsWithAtLeastOneOrder(retrievedPersonRecords, retrievedOrderRecords);
		if(personsWithAtLeastOneOrder != null) {
			personsWithAtLeastOneOrder.forEach(person -> {
				System.out.println(person.toString());
			});
		} else {
			System.out.println("No person with at least one order found");
		}
		System.out.println("########################################################");
		System.out.println("All Orders with First Name of the corresponding person");
		System.out.println("########################################################");
		ordersWithFirstNameOfCorrespondingPerson = filteringServices.getOrdersWithFirstNameOfCorrespondingPerson(retrievedPersonRecords, retrievedOrderRecords);
		if (!ordersWithFirstNameOfCorrespondingPerson.isEmpty()) {
			ordersWithFirstNameOfCorrespondingPerson.forEach(order -> {
				System.out.println(order.toString());
			});
		} else {
			System.out.println("No order found");
		}
		System.out.println("########################################################");
		System.out.println("########################################################");
		services.dropTable("Persons");
		services.dropTable("Orders");
		System.out.println("########################################################");
		System.out.println("########################################################");

	}

}


