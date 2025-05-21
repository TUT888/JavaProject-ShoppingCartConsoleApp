package shoppingcart.service;

import shoppingcart.common.Storage;
import shoppingcart.dto.CartItem;
import shoppingcart.dto.Product;

public class ShoppingCartService {
	public void showCart() {
		if (Storage.cart.items.size() == 0) {
			System.out.println("Your shopping cart is empty.");
			return;
		}
		
		System.out.println("Your shopping cart includes:");
		for (int i=0; i<Storage.cart.items.size(); i++) {
			CartItem item = Storage.cart.items.get(i);
			double total = item.price * item.quantity;
			System.out.println((i+1) + ". " + item.name + " - " + item.price + " aud" + " : " + item.quantity + " ea => " + total + " aud");
		}
	}
	
	public boolean addProductToCart(Product product, int quantity) {
		if (product == null) {
			System.out.println("Product does not exists, can not add to cart");
			return false;
		}
		
		CartItem item = findProductInCart(product);
		if (item != null) {
			item.quantity += quantity;
		} else {
			Storage.cart.items.add(new CartItem(product, quantity));
		}
		return true;
	}

	public CartItem findProductInCart(Product product) {
		for (CartItem item : Storage.cart.items) {
			if (item.name.equals(product.name)) {
				return item;
			}
		}
		return null;
	}
	
	public double getTotalProductPriceInCart() {
		double totalProductPrice = 0.0;
		
		for (int i=0; i<Storage.cart.items.size(); i++) {
			CartItem item = Storage.cart.items.get(i);
			totalProductPrice += item.price * item.quantity;
		}
		return totalProductPrice;
	}
}