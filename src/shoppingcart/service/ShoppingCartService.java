package shoppingcart.service;

import shoppingcart.dto.Cart;
import shoppingcart.dto.CartItem;
import shoppingcart.dto.Product;

public class ShoppingCartService {
	public boolean addToCart(Cart cart, Product product, int quantity) {
		if (product == null) {
			System.out.println("Product does not exists, can not add to cart");
			return false;
		}
		
		CartItem item = findProduct(cart, product);
		if (item != null) {
			item.quantity += quantity;
		} else {
			cart.items.add(new CartItem(product, quantity));
		}
		return true;
	}

	public CartItem findProduct(Cart cart, Product product) {
		for (CartItem item : cart.items) {
			if (item.name.equals(product.name)) {
				return item;
			}
		}
		return null;
	}
	
	public double getTotalProductPrice(Cart cart) {
		double totalProductPrice = 0.0;
		
		for (int i=0; i<cart.items.size(); i++) {
			CartItem item = cart.items.get(i);
			totalProductPrice += item.price * item.quantity;
		}
		return totalProductPrice;
	}
}