package shoppingcart.service.notification;

public class ShopANotificationService extends NotificationService {
	@Override
	public void sendLoginNotification() {
		System.out.println("A new email sent: login successful!");
	}

	@Override
	public void sendCheckoutNotification(double finalPrice) {
		System.out.println("A new email sent: total price is " + finalPrice + " aud");
	}
}
