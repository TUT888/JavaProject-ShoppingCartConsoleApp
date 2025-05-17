package shoppingcart.dto;

public class Shop {
	public int id;
	public String name;
	public String dbPath;
	public double shippingPrice;
	
	public Shop(int id, String name, String dbPath, double shippingPrice) {
		this.id = id;
		this.name = name;
		this.dbPath = dbPath;
		this.shippingPrice = shippingPrice;
	}
}
