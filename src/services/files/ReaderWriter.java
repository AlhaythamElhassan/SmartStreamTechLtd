package services.files;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import domain.*;
 

public class ReaderWriter {
	
	private String fileName;



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
		ReaderWriter fileReaderWriter = new ReaderWriter();
		fileReaderWriter.setFileName("RequirementsAndgivenData/Person.data");
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
	@SuppressWarnings("resource")
	public ArrayList<Person> readFile() {
		ArrayList<Person> records = new ArrayList<Person>();
		Scanner in;
		try { 	
			in = new Scanner(
					new FileInputStream(this.fileName), "UTF-8");
			in.nextLine();
			while(in.hasNext()){
				String line = in.nextLine();
				String[] fields = line.split("\\,");
				if(fields.length > 1){
					Person person = new Person(Integer.parseInt(fields[0]),
							fields[1],
							fields[2],
							fields[3],
							fields[4]);
					records.add(person);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Error reading File " + this.fileName);
			e.printStackTrace();
		}
		return records;
	}
}

