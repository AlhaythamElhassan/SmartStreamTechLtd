package domain;

public class Person {
	int Person_ID;
	String First_Name,Last_Name,Street,City;
	public Person(int id, String firstName, String lastName, String street,
		String city) {
		setPerson_ID(id);
		setFirst_Name(firstName);
		setLast_Name(lastName);
		setStreet(street);
		setCity(city);
	}
	public int getPerson_ID() {
		return Person_ID;
	}
	public void setPerson_ID(int person_ID) {
		Person_ID = person_ID;
	}
	public String getFirst_Name() {
		return First_Name;
	}
	public void setFirst_Name(String first_Name) {
		First_Name = first_Name;
	}
	public String getLast_Name() {
		return Last_Name;
	}
	public void setLast_Name(String last_Name) {
		Last_Name = last_Name;
	}
	public String getStreet() {
		return Street;
	}
	public void setStreet(String street) {
		Street = street;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public String toString(){
		return ( getPerson_ID() +
				"," + 
				getFirst_Name() +
				"," +
				getLast_Name() +
				"," +
				getStreet() +
				"," + 
				getCity());
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Person hansen = new Person(1,"Ola","Hansen","Timoteivn","Sandnes");
		System.out.println(hansen.toString());
	}

}
