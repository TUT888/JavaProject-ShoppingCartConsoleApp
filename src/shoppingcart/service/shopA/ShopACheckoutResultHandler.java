package shoppingcart.service.shopA;

import shoppingcart.service.interfaces.CheckoutResultHandler;

public class ShopACheckoutResultHandler implements CheckoutResultHandler {
	@Override
	public void handleSuccessfulCheckout(double finalPrice) {
		// TODO Auto-generated method stub
		System.out.println("A new email sent: total price is " + finalPrice + " aud");
	}
}
