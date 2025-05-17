package shoppingcart;

public class CartItem extends Product {
	int quantity;
	
	public CartItem(String name, double price, int quantity) {
		super(name, price);
		this.quantity = quantity;
	}
	
	public double getTotal() {
		return price * quantity;
	}
	
	@Override
	public String getInfo() {
		return super.getInfo() + " : " + quantity + " ea => " + getTotal() + " aud";
	}
}
