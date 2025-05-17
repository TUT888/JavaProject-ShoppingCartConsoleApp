package shoppingcart.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import shoppingcart.dto.Shop;

public class ShopService {
	private static final String SHOP_FILE_PATH = "src/shoppingcart/db/shop/shop.txt";
	
	public ArrayList<Shop> getAllShops() {
		ArrayList<Shop> shopList = new ArrayList<Shop>();
		
		try {
			// Read from text file
			File myObj = new File(SHOP_FILE_PATH);
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
	
	public Shop getShopByIndex(int index) {
		ArrayList<Shop> shopList = getAllShops();
		
		if (index < 0 || index > shopList.size()) {
			System.out.print("Invalid shop index");
			return null;
		}
		return shopList.get(index);
	}
}
