package shoppingcart.service.notification;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

import shoppingcart.common.Storage;

public class ShopCNotificationService extends NotificationService {
	@Override
	public void sendLoginNotification() {
		String filepath = Storage.DB_PATH + Storage.currentShop.dbPath + "/log/loginLog.txt";
		
		try {
			File fileObj = new File(filepath);
			
			FileWriter myWriter;
			if (fileObj.exists()) {
				myWriter = new FileWriter(fileObj, true);
			} else {
				fileObj.createNewFile();
				myWriter = new FileWriter(filepath);
			}
			 
			myWriter.write("[New login] UserID: " + Storage.customer.id + ", login time: " + LocalDateTime.now().toString() + "\n");
			myWriter.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
	    }
	}

	@Override
	public void sendCheckoutNotification(double finalPrice) {
		String filepath = Storage.DB_PATH + Storage.currentShop.dbPath + "/log/checkoutLog.txt";
		
		try {
			File fileObj = new File(filepath);
			
			FileWriter myWriter;
			if (fileObj.exists()) {
				myWriter = new FileWriter(fileObj, true);
			} else {
				fileObj.createNewFile();
				myWriter = new FileWriter(filepath);
			}

			myWriter.write("[New checkout] UserID: " + Storage.customer.id + ", Total: " + finalPrice + ", Checkout time: " + LocalDateTime.now().toString() + "\n");
			myWriter.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
	    }
	}
}