package shoppingcart.service.shopA;

import shoppingcart.service.interfaces.AuthenResultHandler;

public class ShopAAuthenResultHandler implements AuthenResultHandler {
	@Override
	public void handleSuccessfulLogin(String id) {
		// TODO Auto-generated method stub
		System.out.println("A new email sent: login successful!");
	}

	@Override
	public void handleFailLogin(String id) {
		// TODO Auto-generated method stub
		System.out.println("A new email sent: login failed!");
	}
}
