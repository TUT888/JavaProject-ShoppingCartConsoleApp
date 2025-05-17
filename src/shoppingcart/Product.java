package shoppingcart;

public class Product {
	String name;
	double price;
	
	public Product(String name, double price) {
		this.name = name;
		this.price = price;
	}
	
	public String getInfo() {
		return name + " - " + price + " aud";
	}
}