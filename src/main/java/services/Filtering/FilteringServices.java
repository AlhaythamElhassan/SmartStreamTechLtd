package services.Filtering;

import java.util.ArrayList;
import java.util.HashMap;

import domain.Order;
import domain.Person;

public class FilteringServices {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public ArrayList<Object> getPersonsWithAtLeastOneOrder(ArrayList<Object> persons,
			ArrayList<Object> ordres) {
		// Finding all Persons that have order/s
		ArrayList<Object> personsHaveAtLeastAnOrder = new ArrayList<Object>();
		// loop over the orders 
		ordres.forEach(order -> {
			int personId = ((Order) order).getPersonID();
			// find the person in persons 
			Person person = findPersonById(personId, persons);
			// make sure the person is not null
			if (person != null) {
				//make sure person is not in the personHaveAtLeastAnOrder filtered list
				if (!foundInList(person, personsHaveAtLeastAnOrder)) {
					// add the person to the filtered list
					personsHaveAtLeastAnOrder.add(person);
				}
			}
		});
		return personsHaveAtLeastAnOrder;
	}

	private boolean foundInList(Person person, ArrayList<Object> personsHaveAtLeastAnOrder) {
		boolean found = false;
		for(Object p: personsHaveAtLeastAnOrder) {
			if (((Person) p).getPerson_ID() == person.getPerson_ID())
				found = true; 
		}
		return found;
	}

	private Person findPersonById(int personId, ArrayList<Object> persons) {
		Person person = null ;
		for (Object p: persons) {
			if(((Person) p).getPerson_ID() == personId)
				person = (Person) p;
		}
		return person;
	}

	public ArrayList<Object> getOrdersWithFirstNameOfCorrespondingPerson(ArrayList<Object> persons,
			ArrayList<Object> orders) {
		ArrayList<Object> filteredRecords = new ArrayList<Object>();
		for (Object order: orders) {
			Person p = findPersonById(((Order) order).getPersonID(), persons);
			if(p != null) {
				filteredRecords.add(order.toString() + " " + p.getFirst_Name());
			} else {
				filteredRecords.add(order.toString() + " " + "Person info not available");
			}
		}
		return filteredRecords;
	}

}
