package shoppingcart.service;

import java.util.ArrayList;

import shoppingcart.dto.Customer;
import shoppingcart.dto.Shop;

public class AuthenService {
	private CustomerService customerService = new CustomerService();
	
	public Customer login(Shop shop, String id, String password) {
		ArrayList<Customer> customerList = customerService.getAllCustomers(shop);
		
		for (Customer c : customerList) {
			if (c.id.equals(id) && c.password.equals(password)) {
				return c;
			}
		}
		return null;
	}
}