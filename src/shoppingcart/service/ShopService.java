package shoppingcart.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import shoppingcart.common.Storage;
import shoppingcart.dto.Shop;

public class ShopService {
	public ArrayList<Shop> getAllShops() {
		String filepath = Storage.DB_PATH + "shop/shop.txt";
		
		ArrayList<Shop> shopList = new ArrayList<Shop>();
		
		try {
			// Read from text file
			File myObj = new File(filepath);
			Scanner myReader = new Scanner(myObj);
			myReader.nextLine();
			
			// Read row by row
			while (myReader.hasNextLine()) {
				String textRow = myReader.nextLine();
				String[] data = textRow.split(",");
				shopList.add(new Shop(Integer.parseInt(data[0]), data[1], data[2], Double.parseDouble(data[3])));
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		
		return shopList;
	}

	public void showShopList() {
		ArrayList<Shop> shopList = getAllShops();
		
		for (int i=0; i<shopList.size(); i++) {
			Shop shop = shopList.get(i);
			System.out.println((i+1) + ". " + shop.name);
		}
	}
	
	public Shop getShopByIndex(int index) {
		ArrayList<Shop> shopList = getAllShops();
		
		if (index < 0 || index > shopList.size()) {
			System.out.print("Invalid shop index");
			return null;
		}
		return shopList.get(index);
	}
}
