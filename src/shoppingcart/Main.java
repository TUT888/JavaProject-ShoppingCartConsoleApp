package shoppingcart;

import java.util.Scanner;

import shoppingcart.common.Storage;
import shoppingcart.dto.Cart;
import shoppingcart.dto.Product;
import shoppingcart.service.AuthenService;
import shoppingcart.service.CheckoutService;
import shoppingcart.service.ProductService;
import shoppingcart.service.RankService;
import shoppingcart.service.ShopService;
import shoppingcart.service.ShoppingCartService;

public class Main {
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
	private static CheckoutService checkoutService = new CheckoutService();

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
		shopService.showShopList();
		
		System.out.print("Enter your option: ");
		int opt = Integer.parseInt(sc.nextLine());
		
		Storage.currentShop = shopService.getShopByIndex(opt-1);
	}
	
	public static boolean doLogin() {
		System.out.println("------------------------------");
		System.out.print("| ID: ");
		String userID = sc.nextLine();
		System.out.print("| Password: ");
		String userPassword = sc.nextLine();
		System.out.println("------------------------------");
		
		Storage.customer = authenService.login(userID, userPassword);
		if (Storage.customer == null) {
			System.out.println("Invalid username/password");
			return false;
		}
		Storage.cart = new Cart();
		
		return true;
	}
	
	private static boolean openShopMenu() {
		System.out.println("\nWelcome to " + Storage.currentShop.name);
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
				cartService.showCart();
				break;
			case OPT_VIEW_RANK:
				rankService.showRankList();
				System.out.println("Your current rank: " + Storage.customer.rank);
				break;
			case OPT_ADD_PRODUCT:
				productService.showProductList();
				
				System.out.print("Select a product: ");
				int selectedOpt = Integer.parseInt(sc.nextLine());
				Product selectedProduct = productService.getProductByIndex(selectedOpt-1);
				
				System.out.print("Enter quantity: ");
				int quantity = Integer.parseInt(sc.nextLine());
				cartService.addProductToCart(selectedProduct, quantity);
				
				break;
			case OPT_CHECKOUT:
				checkoutService.checkOut();
				return false;
			default:
				System.out.println("Invalid option, please try again.");
		}
		return true;
	}
}