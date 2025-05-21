package shoppingcart.service.shopC;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

import shoppingcart.common.Storage;
import shoppingcart.service.interfaces.CheckoutResultHandler;

public class ShopCCheckoutResultHandler implements CheckoutResultHandler {
	@Override
	public void handleSuccessfulCheckout(double finalPrice) {
		// TODO Auto-generated method stub
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
