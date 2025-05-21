package shoppingcart.service.notification;

import shoppingcart.common.Storage;

public abstract class NotificationService {
	// DESIGN PATTERN FACTORY
	public static NotificationService getNotificationService() {
		switch (Storage.currentShop.dbPath) {
			case "shopA":
				return new ShopANotificationService();
			case "shopB":
				return new ShopBNotificationService();
			case "shopC":
				return new ShopCNotificationService();
		}
		return null;
	}
	
	public abstract void sendLoginNotification();
	public abstract void sendCheckoutNotification(double finalPrice);
}
