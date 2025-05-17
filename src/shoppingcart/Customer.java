package shoppingcart;

public class Customer {
	String username;
	String password;
	Rank rank;
	
	public Customer(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public Customer(String username, String password, Rank rank) {
		this.username = username;
		this.password = password;
		this.rank = rank;
	}
}