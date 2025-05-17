package shoppingcart;

import java.util.ArrayList;

public class Database {
	ArrayList<Customer> customerDB = new ArrayList<Customer>();
	ArrayList<Product> productDB = new ArrayList<Product>();
	ArrayList<Rank> rankDB = new ArrayList<Rank>();
	double shippingPrice;
	
	public void setShippingPrice(double shippingPrice) {
		this.shippingPrice = shippingPrice;
	}
	
	public void addProduct(Product product) {
		productDB.add(product);
	}
	
	public void addCustomer(Customer customer) {
		customerDB.add(customer);
	}
	
	public void addRank(Rank rank) {
		rankDB.add(rank);
	}
}
