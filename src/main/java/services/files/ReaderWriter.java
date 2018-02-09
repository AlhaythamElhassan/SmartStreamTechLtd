package main.java.services.files;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import main.java.domain.*;
 

public class ReaderWriter {
	
	private String fileName;



	public ReaderWriter(String inputFile) {
		setFileName(inputFile);
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
		String inputDataFile = "RequirementsAndgivenData/Person.data";
		ReaderWriter fileReaderWriter = new ReaderWriter(inputDataFile);
		ArrayList<Person> personRecords = fileReaderWriter.readFile();
		for (Person person: personRecords)
			System.out.println(person.toString());
	}
	/**
	 * 
	 * @return 
	 * @return 
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

	private Person mapLineToRecord(String line) {
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
}

