package shoppingcart.service.shopC;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

import shoppingcart.common.Storage;
import shoppingcart.service.interfaces.AuthenResultHandler;

public class ShopCAuthenResultHandler implements AuthenResultHandler {
	@Override
	public void handleSuccessfulLogin(String id) {
		// TODO Auto-generated method stub
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
			 
			myWriter.write("[New login] UserID: " + id + ", login time: " + LocalDateTime.now().toString() + "\n");
			myWriter.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
	    }
	}

	@Override
	public void handleFailLogin(String id) {
		// TODO Auto-generated method stub
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
			 
			myWriter.write("[Invalid login] UserID: " + id + ", login time: " + LocalDateTime.now().toString() + "\n");
			myWriter.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
	    }
	}
}