package shoppingcart.service.shopB;

import shoppingcart.service.interfaces.AuthenResultHandler;

public class ShopBAuthenResultHandler implements AuthenResultHandler {
	@Override
	public void handleSuccessfulLogin(String id) {
		// TODO Auto-generated method stub
		System.out.println("LOG message: login successful!");
	}

	@Override
	public void handleFailLogin(String id) {
		// TODO Auto-generated method stub
		System.out.println("LOG message: login failed!");
	}
}
