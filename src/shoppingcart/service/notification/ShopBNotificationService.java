package shoppingcart.service.notification;

public class ShopBNotificationService extends NotificationService {
	@Override
	public void sendLoginNotification() {
		System.out.println("LOG message: login successful!");
	}

	@Override
	public void sendCheckoutNotification(double finalPrice) {
		System.out.println("LOG message: total price is " + finalPrice + " aud");
	}
}
