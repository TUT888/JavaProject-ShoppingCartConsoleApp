package shoppingcart;

import java.util.ArrayList;
import java.util.Scanner;

import shoppingcart.dto.Cart;
import shoppingcart.dto.CartItem;
import shoppingcart.dto.Customer;
import shoppingcart.dto.Product;
import shoppingcart.dto.Rank;
import shoppingcart.dto.Shop;
import shoppingcart.service.AuthenService;
import shoppingcart.service.ProductService;
import shoppingcart.service.RankService;
import shoppingcart.service.ShopService;
import shoppingcart.service.ShoppingCartService;

public class Main {
	static Shop shop;
	static Cart cart;
	static Customer customer;
	static Scanner sc = new Scanner(System.in);
	
	private static final int OPT_EXIT = 0;
	private static final int OPT_VIEW_CART = 1;
	private static final int OPT_VIEW_RANK = 2;
	private static final int OPT_ADD_PRODUCT = 3;
	private static final int OPT_CHECKOUT = 4;

	private static ShoppingCartService cartService = new ShoppingCartService();
	private static AuthenService authenService = new AuthenService();
	private static ProductService productService = new ProductService();
	private static ShopService shopService = new ShopService();
	private static RankService rankService = new RankService();

	public static void main(String[] args) {
		// Choose shop
		selectShop();
				
		// Login
		boolean isLoggedin;
		do {
			isLoggedin = doLogin();
		} while (!isLoggedin);
		
		// Shopping
		boolean isOpen;
		do {
			isOpen = openShopMenu();
		} while (isOpen);
	}
	
	public static void selectShop() {
		System.out.println("\nSelect a shop to browse");
		showShopList();
		
		System.out.print("Enter your option: ");
		int opt = Integer.parseInt(sc.nextLine());
		
		shop = shopService.getShopByIndex(opt-1);
	}
	
	public static boolean doLogin() {
		System.out.println("------------------------------");
		System.out.print("| ID: ");
		String userID = sc.nextLine();
		System.out.print("| Password: ");
		String userPassword = sc.nextLine();
		System.out.println("------------------------------");
		
		customer = authenService.login(shop, userID, userPassword);
		if (customer == null) {
			System.out.println("Invalid username/password");
			return false;
		}
		cart = new Cart();
		
		return true;
	}
	
	private static boolean openShopMenu() {
		System.out.println("\nWelcome to " + shop.name);
		System.out.println("------------------------------");
		System.out.println("| 0. Quit                    |");
		System.out.println("| 1. View Cart               |");
		System.out.println("| 2. View Rank               |");
		System.out.println("| 3. View and add product    |");
		System.out.println("| 4. Checkout                |");
		System.out.println("------------------------------");
		System.out.print("Enter your option: ");
		String input = sc.nextLine();
		int opt = Integer.parseInt(input);
		
		switch (opt) {
			case OPT_EXIT:
				return false;
			case OPT_VIEW_CART:
				showCart();
				break;
			case OPT_VIEW_RANK:
				showRankList();
				System.out.println("Your current rank: " + customer.rank);
				break;
			case OPT_ADD_PRODUCT:
				showProductList();
				
				System.out.print("Select a product: ");
				int selectedOpt = Integer.parseInt(sc.nextLine());
				Product selectedProduct = productService.getProductByIndex(shop, selectedOpt-1);
				
				System.out.print("Enter quantity: ");
				int quantity = Integer.parseInt(sc.nextLine());
				cartService.addToCart(cart, selectedProduct, quantity);
				
				break;
			case OPT_CHECKOUT:
				checkOut();
				return false;
			default:
				System.out.println("Invalid option, please try again.");
		}
		return true;
	}
	
	public static void checkOut() {
		if (cart.items.size() == 0) {
			System.out.println("Your shopping cart is empty.");
			return;
		}
		double totalProductPrice = cartService.getTotalProductPrice(cart);
		
		System.out.println("\nCHECKOUT");
		System.out.println("------------------------------");
		
		// Show all products in cart
		showCart();
		
		System.out.println("Ship: " + shop.shippingPrice + " aud");
		System.out.println("=> Total price: " + (totalProductPrice + shop.shippingPrice));
		System.out.println();
		
		// Show discount
		Rank customerRank = rankService.getRankByName(shop, customer.rank);
		double discount = rankService.applyDiscount(shop, customerRank, totalProductPrice);
		
		System.out.println("Rank promotion: " + customerRank.name + " - " + customerRank.description);
		System.out.println("=> Total discount: -" + discount + " aud");
		System.out.println();
		
		// Show final price
		double finalPrice = totalProductPrice + shop.shippingPrice - discount;
		System.out.println("Final price: " + finalPrice + " aud");
		
		System.out.println("------------------------------");
		System.out.println("Check out successful!");
		
		cart = null;
	}
	
	// Show list
	public static void showShopList() {
		ArrayList<Shop> shopList = shopService.getAllShops();
		
		for (int i=0; i<shopList.size(); i++) {
			Shop shop = shopList.get(i);
			System.out.println((i+1) + ". " + shop.name);
		}
	}
	
	public static void showProductList() {
		ArrayList<Product> productList = productService.getAllProducts(shop);
		
		if (productList.size() == 0) {
			System.out.println("There aren't any product in shop");
			return;
		}
		
		for (int i=0; i<productList.size(); i++) {
			Product product = productList.get(i);
			System.out.println((i+1) + ". " + product.name + " - " + product.price + " aud");
		}
	}
	
	public static void showRankList() {
		ArrayList<Rank> rankList = rankService.getAllRanks(shop);
		
		System.out.println("Available ranks and promotions:");
		for (int i=0; i<rankList.size(); i++) {
			Rank rank = rankList.get(i);
			System.out.println((i+1) + ". " + rank.name + ": " + rank.description);
		}
	}
	
	public static void showCart() {
		if (cart.items.size() == 0) {
			System.out.println("Your shopping cart is empty.");
			return;
		}
		
		System.out.println("Your shopping cart includes:");
		for (int i=0; i<cart.items.size(); i++) {
			CartItem item = cart.items.get(i);
			double total = item.price * item.quantity;
			System.out.println((i+1) + ". " + item.name + " - " + item.price + " aud" + " : " + item.quantity + " ea => " + total + " aud");
		}
	}
}