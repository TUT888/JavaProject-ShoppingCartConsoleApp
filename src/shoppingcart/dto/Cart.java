package shoppingcart.dto;

import java.util.ArrayList;

public class Cart {
	public ArrayList<CartItem> items;
	
	public Cart() {
		items = new ArrayList<CartItem>();
	}
}