package test.java.services.files;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import main.java.domain.Person;
import main.java.services.files.ReaderWriter;

import org.junit.Test;


public class ReaderWriterTest {
	/**
	 * This Test is a Test case that if a ReaderWriter object is 
	 * instantiated using given exist fileName 
	 * Then the return object of calling object method readFile
	 * with no parameters will be ArrayList
	 *  of type Person that is not null.
	 */
	@Test
	public void testreadFile() {
		// The return type is an ArrayList of Person
		ArrayList<Person> outputRecords = new ArrayList<Person>();
		// The input is a file name 
		String inputFile = "RequirementsAndgivenData/Person.data";
		// instantiate a ReadWriter object using inputFile String
		ReaderWriter readerWriter = new ReaderWriter(inputFile);
		outputRecords = readerWriter.readFile();
		// now application have to assert that the output an
		// ArrayList that is not null 
		System.out.println(outputRecords.size());
	}
	
	@Test
	public void myFirstTest() {
		assertEquals(2, 1 + 1);
	}

}
