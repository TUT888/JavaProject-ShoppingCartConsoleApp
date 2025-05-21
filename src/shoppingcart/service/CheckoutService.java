package shoppingcart.service;

import shoppingcart.common.Storage;
import shoppingcart.dto.Rank;

public class CheckoutService {
	private static ShoppingCartService cartService = new ShoppingCartService();
	private static RankService rankService = new RankService();
	
	public void checkOut() {
		if (Storage.cart.items.size() == 0) {
			System.out.println("Your shopping cart is empty.");
			return;
		}
		double totalProductPrice = cartService.getTotalProductPriceInCart();
		
		System.out.println("\nCHECKOUT");
		System.out.println("------------------------------");
		
		// Show all products in cart
		cartService.showCart();
		
		double shippingPrice = Storage.currentShop.shippingPrice;
		
		System.out.println("Ship: " + shippingPrice + " aud");
		System.out.println("=> Total price: " + (totalProductPrice + shippingPrice));
		System.out.println();
		
		// Show discount
		Rank customerRank = rankService.getRankByName(Storage.customer.rank);
		double discount = rankService.applyDiscount(customerRank, totalProductPrice);
		
		System.out.println("Rank promotion: " + customerRank.name + " - " + customerRank.description);
		System.out.println("=> Total discount: -" + discount + " aud");
		System.out.println();
		
		// Show final price
		double finalPrice = totalProductPrice + Storage.currentShop.shippingPrice - discount;
		System.out.println("Final price: " + finalPrice + " aud");
		
		System.out.println("------------------------------");
		System.out.println("Check out successful!");

		Storage.cart = null;
		
		// Handling checkout result
		ResultHandlingService.getCheckoutResultHandler().handleSuccessfulCheckout(finalPrice);
	}
}
