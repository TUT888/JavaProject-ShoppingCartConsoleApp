package shoppingcart.service;

import shoppingcart.common.Storage;
import shoppingcart.service.interfaces.AuthenResultHandler;
import shoppingcart.service.interfaces.CheckoutResultHandler;
import shoppingcart.service.shopA.ShopAAuthenResultHandler;
import shoppingcart.service.shopA.ShopACheckoutResultHandler;
import shoppingcart.service.shopB.ShopBAuthenResultHandler;
import shoppingcart.service.shopB.ShopBCheckoutResultHandler;
import shoppingcart.service.shopC.ShopCAuthenResultHandler;
import shoppingcart.service.shopC.ShopCCheckoutResultHandler;

public class ResultHandlingService {
	// DESIGN PATTERN FACTORY
	public static AuthenResultHandler getAuthenResultHandler() {
		switch (Storage.currentShop.dbPath) {
			case "shopA":
				return new ShopAAuthenResultHandler();
			case "shopB":
				return new ShopBAuthenResultHandler();
			case "shopC":
				return new ShopCAuthenResultHandler();
		}
		return null;
	}

	public static CheckoutResultHandler getCheckoutResultHandler() {
		switch (Storage.currentShop.dbPath) {
			case "shopA":
				return new ShopACheckoutResultHandler();
			case "shopB":
				return new ShopBCheckoutResultHandler();
			case "shopC":
				return new ShopCCheckoutResultHandler();
		}
		return null;
	}
}
