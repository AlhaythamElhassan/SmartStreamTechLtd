package domain;

public class Order {
	
	int OrderID, PersonID, OrderNumber;
	
	public Order(int orderID, int orderNumber, int personID){
		setOrderID(orderID);
		setPersonID(personID);
		setOrderNumber(orderNumber);
		}
	public int getOrderID() {
		return OrderID;
	}
	public void setOrderID(int orderID) {
		OrderID = orderID;
	}
	public int getPersonID() {
		return PersonID;
	}
	public void setPersonID(int personID) {
		PersonID = personID;
	}
	public int getOrderNumber() {
		return OrderNumber;
	}
	public void setOrderNumber(int orderNumber) {
		OrderNumber = orderNumber;
	}
	public String toString(){
		return (getOrderID() +
				"," +
				getOrderNumber() +
				"," +
				getPersonID());
	}
	
	public static void main(String[] args) {
		Order order = new Order(10,2000, 1);
		System.out.println(order.toString());
	}
		

}
