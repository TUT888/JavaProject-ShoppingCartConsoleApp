package shoppingcart.common;

import shoppingcart.dto.Cart;
import shoppingcart.dto.Customer;
import shoppingcart.dto.Shop;

public class Storage {
	public static final String DB_PATH = "src/shoppingcart/db/";
	public static Shop currentShop = null;
	public static Cart cart = null;
	public static Customer customer = null;
}