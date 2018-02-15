package services.files;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import domain.Order;
import domain.Person;

/**
 * This component is responsible from reading data from files Person.data and
 * Order.data
 * 
 * @author alhaytham
 *
 */
public class FileReader {

	private String fileName;

	public static void main(String[] args) {
		String inputDataFile = "src/main/resources/Person.data";
		FileReader fileReaderWriter = new FileReader(inputDataFile);
		ArrayList<Object> personRecords = fileReaderWriter.readFile();
		for (Object person : personRecords)
			System.out.println(person.toString());
	}

	public FileReader(String inputFile) {
		super();
		setFileName(inputFile);
	}

	public FileReader() {
		super();
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return ArrayList of records found in the file
	 */
	public ArrayList<Object> readFile() {
		ArrayList<Object> records = new ArrayList<Object>();
		Scanner in;
		try {
			in = new Scanner(new FileInputStream(this.fileName), "UTF-8");
			in.nextLine();
			while (in.hasNext()) {
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

	/**
	 * Map a given line using , as a delimiter to fields
	 * 
	 * @param line
	 *            is a string that represent the record
	 * @return Person an instant of type Person or null
	 */
	public Person mapLineToRecord(String line) {
		String[] fields = line.split("\\,");
		if (fields.length > 1) {
			Person person = new Person(Integer.parseInt(fields[0]), fields[1], fields[2], fields[3], fields[4]);
			return person;
		}
		return null;
	}

	public ArrayList<Object> readFile(String delimiter) {
		ArrayList<Object> records = new ArrayList<Object>();
		try {
			Scanner in = new Scanner(new FileInputStream(this.fileName), "UTF-8");
			in.nextLine();
			while (in.hasNext()) {
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

	/**
	 * Map a given line using a given delimiter to an Order
	 * 
	 * @param line
	 *            is a string that represent the record
	 * @return Order an instance of type Order or null
	 */

	private Order mapLineToRecord(String line, String delimiter) {
		String[] fields = line.split(delimiter);
		if (fields.length > 1) {
			return new Order(Integer.parseInt(fields[0]), Integer.parseInt(fields[1]), Integer.parseInt(fields[2]));
		}
		return null;
	}
}
