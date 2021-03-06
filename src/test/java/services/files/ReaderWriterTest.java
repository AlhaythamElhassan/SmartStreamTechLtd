package services.files;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import domain.Person;

@DisplayName(value = "Testing ReadWriter Unit")
public class ReaderWriterTest {
	/**
	 * This Test is a Test case that if a ReaderWriter object is 
	 * instantiated using given exist fileName 
	 * Then the return object of calling object method readFile
	 * with no parameters will be ArrayList
	 *  of type Person that is not null.
	 */
	@Test
	public void testreadFileReturnNotNullObject() {
		// The return type is an ArrayList of Person
		ArrayList<Object> outputRecords = new ArrayList<Object>();
		// The input is a file name 
		String inputFile = "src/main/resources/Person.data";
		// instantiate a ReadWriter object using inputFile String
		FileReader readerWriter = new FileReader(inputFile);
		outputRecords = readerWriter.readFile();
		// now application have to assert that the output an
		// ArrayList that is not null 
		assertNotNull(outputRecords);
	}
	@Test
	public void mapLineToRecordConverstCsvToPersonObject() {
		String line = "1,Ola,Hansen,Timoteivn,Sandnes";
		String expectedOutPut = "1,'Ola','Hansen','Timoteivn','Sandnes'";
		FileReader readerWriter = new FileReader();
		Person person = readerWriter.mapLineToRecord(line);
		assertEquals(person.toString(), expectedOutPut);
	}
	@Test
	public void testreadFileIsReturningTheRightData() {
		// The return type is an ArrayList of Person
		ArrayList<Object> outputRecords = new ArrayList<Object>();
		// The input is a file name 
		String inputFile = "src/main/resources/Person.data";
		// instantiate a ReadWriter object using inputFile String
		FileReader readerWriter = new FileReader(inputFile);
		outputRecords = readerWriter.readFile();
		assertEquals(outputRecords.get(0).toString(), "1,'Ola','Hansen','Timoteivn','Sandnes'");
		assertEquals(outputRecords.get(1).toString(), "2,'Tove','Svendson','Borgvn','Stavanger'");
		assertEquals(outputRecords.get(2).toString(), "3,'Kari','Pettersen','Storgt','Stavanger'");
	}
	@Test
	public void TestThatsetFileNameIsSettingTheField() {
		String fileName = "test";
		FileReader readerWriter = new FileReader();
		readerWriter.setFileName(fileName);
		assertEquals(fileName, readerWriter.getFileName());
	}
}
