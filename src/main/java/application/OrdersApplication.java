package application;

import java.util.ArrayList;

import domain.Person;
import services.files.ReaderWriter;


public class OrdersApplication {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String dataInputFile = "src/main/resources/Person.data";
		ReaderWriter fileReaderWriter = new ReaderWriter(dataInputFile);
		ArrayList<Person> personRecords = fileReaderWriter.readFile();
		for (Person person: personRecords)
			System.out.println(person.toString());
	}

}


