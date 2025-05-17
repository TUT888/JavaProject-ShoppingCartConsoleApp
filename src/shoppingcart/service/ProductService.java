package shoppingcart.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import shoppingcart.dto.Product;
import shoppingcart.dto.Shop;

public class ProductService {
	private static final String DB_PATH = "src/shoppingcart/db/";
	
	public ArrayList<Product> getAllProducts(Shop shop) {
		String filepath = DB_PATH + shop.dbPath + "/product.txt";
		
		ArrayList<Product> productList = new ArrayList<Product>();
		
		try {
			// Read from text file
			File myObj = new File(filepath);
			Scanner myReader = new Scanner(myObj);
			myReader.nextLine();
			
			// Read row by row
			while (myReader.hasNextLine()) {
				String textRow = myReader.nextLine();
				String[] data = textRow.split(",");
				productList.add(new Product(data[0], Integer.parseInt(data[1])));
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		
		return productList;
	}
	
	public Product getProductByIndex(Shop shop, int index) {
		ArrayList<Product> productList = getAllProducts(shop);
		
		if (index < 0 || index > productList.size()) {
			System.out.println("Invalid product id");
			return null;
		}
		return productList.get(index);
	}
}
