package shoppingcart.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import shoppingcart.common.Storage;
import shoppingcart.dto.Customer;

public class CustomerService {
	public ArrayList<Customer> getAllCustomers() {
		String filepath = Storage.DB_PATH + Storage.currentShop.dbPath + "/customer.txt";
		
		ArrayList<Customer> customerList = new ArrayList<Customer>();
		
		try {
			// Read from text file
			File myObj = new File(filepath);
			Scanner myReader = new Scanner(myObj);
			myReader.nextLine();
			
			// Read row by row
			while (myReader.hasNextLine()) {
				String textRow = myReader.nextLine();
				String[] data = textRow.split(",");
				customerList.add(new Customer(data[0], data[1], data[2]));
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		
		return customerList;
	}
}
