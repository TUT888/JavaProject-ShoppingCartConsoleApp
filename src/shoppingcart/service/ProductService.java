package shoppingcart.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import shoppingcart.common.Storage;
import shoppingcart.dto.Product;

public class ProductService {
	public ArrayList<Product> getAllProducts() {
		String filepath = Storage.DB_PATH + Storage.currentShop.dbPath + "/product.txt";
		
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
	
	public void showProductList() {
		ArrayList<Product> productList = getAllProducts();
		
		if (productList.size() == 0) {
			System.out.println("There aren't any product in shop");
			return;
		}
		
		for (int i=0; i<productList.size(); i++) {
			Product product = productList.get(i);
			System.out.println((i+1) + ". " + product.name + " - " + product.price + " aud");
		}
	}
	
	public Product getProductByIndex(int index) {
		ArrayList<Product> productList = getAllProducts();
		
		if (index < 0 || index > productList.size()) {
			System.out.println("Invalid product id");
			return null;
		}
		return productList.get(index);
	}
}
