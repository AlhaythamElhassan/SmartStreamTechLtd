package services.files;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import domain.Order;
import domain.Person;
 

public class ReaderWriter {
	
	private String fileName;



	public ReaderWriter(String inputFile) {
		super();
		setFileName(inputFile);
	}



	public ReaderWriter() {
		super();
	}



	public String getFileName() {
		return fileName;
	}



	public void setFileName(String fileName) {
		this.fileName = fileName;
	}



	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String inputDataFile = "src/main/resources/Person.data";
		ReaderWriter fileReaderWriter = new ReaderWriter(inputDataFile);
		ArrayList<Person> personRecords = fileReaderWriter.readFile();
		for (Person person: personRecords)
			System.out.println(person.toString());
	}
	/** 
	 * @return ArrayList of records found in the file
	 */
	public ArrayList<Person> readFile() {
		ArrayList<Person> records = new ArrayList<Person>();
		Scanner in;
		try { 	
			in = new Scanner(
					new FileInputStream(this.fileName), "UTF-8");
			in.nextLine();
			while(in.hasNext()){
				Person personRecord = mapLineToRecord(in.nextLine());
				if (personRecord != null)
					records.add(personRecord);
			}
			// close Scanner 
			in.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error reading File " + this.fileName);
			e.printStackTrace();
		}
		return records;
	}

	public Person mapLineToRecord(String line) {
		String[] fields = line.split("\\,");
		if(fields.length > 1){
			Person person = new Person(Integer.parseInt(fields[0]),
					fields[1],
					fields[2],
					fields[3],
					fields[4]);
			return person;
		}
		return null;
	}
	
	public ArrayList<Order> readFile(String delimiter) {
		ArrayList<Order> records = new ArrayList<Order>();
		try {
			Scanner in = new Scanner(new FileInputStream(this.fileName), "UTF-8");
			in.nextLine();
			while(in.hasNext()) {
				Order orderRecord = mapLineToRecord(in.nextLine(), delimiter);
				if (orderRecord != null)
					records.add(orderRecord);
			}
			in.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error reading File " + this.fileName);
			e.printStackTrace();
		}
		return records;
		
	}



	private Order mapLineToRecord(String line, String delimiter) {
		String[] fields = line.split(delimiter);
		if(fields.length > 1) {
			return new Order(Integer.parseInt(fields[0]), Integer.parseInt(fields[1]), Integer.parseInt(fields[2]));
		}
		return null;
	}
}
