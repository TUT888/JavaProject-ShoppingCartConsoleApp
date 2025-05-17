package shoppingcart;

public class Rank {
	String name;
	String description;
	int productDiscount;
	int shippingDiscount;
	
	public Rank(String name, String description, int productDiscount, int shippingDiscount) {
		this.name = name;
		this.description = description;
		this.productDiscount = productDiscount;
		this.shippingDiscount = shippingDiscount;
	}
	
	public String getInfo() {
		return name + ": " + description;
	}
	
	public double applyDiscount(double productPrice, double shippingPrice) {
		return (shippingPrice * shippingDiscount / 100) + (productPrice * productDiscount / 100);
	}
}
