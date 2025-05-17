package shoppingcart.dto;

public class Customer {
	public String id;
	public String password;
	public String rank;

	public Customer() {
		// TODO Auto-generated constructor stub
	}

	public Customer(String id, String password, String rank) {
		this.id = id;
		this.password = password;
		this.rank = rank;
	}
}