package shoppingcart.dto;

public class CartItem extends Product {
	public int quantity;

	public CartItem() {
		// TODO Auto-generated constructor stub
	}

	public CartItem(String name, int price) {
		super(name, price);
	}

	public CartItem(Product product, int quantity) {
		this.name = product.name;
		this.price = product.price;
		this.quantity = quantity;
	}
}