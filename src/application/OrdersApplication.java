package application;

import java.util.ArrayList;

import services.files.ReaderWriter;
import domain.Person;

public class OrdersApplication {
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

}


