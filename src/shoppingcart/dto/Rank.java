package shoppingcart.dto;

public class Rank {
	public String name;
	public String description;
	public int productDiscount;
	public int shippingDiscount;
	
	public Rank(String name, String description, int productDiscount, int shippingDiscount) {
		this.name = name;
		this.description = description;
		this.productDiscount = productDiscount;
		this.shippingDiscount = shippingDiscount;
	}
}