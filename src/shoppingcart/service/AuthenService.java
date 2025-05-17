package shoppingcart.service;

import java.util.ArrayList;

import shoppingcart.dto.Customer;

public class AuthenService {
	private CustomerService customerService = new CustomerService();
	
	public Customer login(String id, String password) {
		ArrayList<Customer> customerList = customerService.getAllCustomers();
		
		for (Customer c : customerList) {
			if (c.id.equals(id) && c.password.equals(password)) {
				return c;
			}
		}
		return null;
	}
}