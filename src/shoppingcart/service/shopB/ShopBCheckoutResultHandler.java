package shoppingcart.service.shopB;

import shoppingcart.service.interfaces.CheckoutResultHandler;

public class ShopBCheckoutResultHandler implements CheckoutResultHandler {
	@Override
	public void handleSuccessfulCheckout(double finalPrice) {
		// TODO Auto-generated method stub
		System.out.println("LOG message: total price is " + finalPrice + " aud");
	}
}
