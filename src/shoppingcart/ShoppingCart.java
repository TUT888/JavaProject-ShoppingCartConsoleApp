package shoppingcart;

import java.util.ArrayList;

public class ShoppingCart {
	ArrayList<CartItem> cartItems = new ArrayList<CartItem>();
	
	public void addItem(CartItem item) {
		cartItems.add(item);
	}
	
	public void showItems() {
		if (cartItems.size()==0) {
			System.out.println("Your shopping cart is empty.");
		} else {
			System.out.println("Your shopping cart includes:");
			for (int i=0; i<cartItems.size(); i++) {
				CartItem item = cartItems.get(i);
				System.out.println((i+1) + ". " + item.getInfo());
			}
		}
	}
	
	public CartItem findProduct(Product product) {
		for (CartItem item : cartItems) {
			if (item.name.equals(product)) {
				return item;
			}
		}
		return null;
	}
	
	public double getTotalPrice() {
		double totalProductPrice = 0.0;
		for (int i=0; i<cartItems.size(); i++) {
			CartItem item = cartItems.get(i);
			totalProductPrice += item.getTotal();
		}
		return totalProductPrice;
	}
	
	public void clear() {
		cartItems.clear();
	}
}
