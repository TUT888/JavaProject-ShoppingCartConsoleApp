package shoppingcart;

import java.util.Scanner;

public class Shop {
	String shopName;
	Database database = new Database();
	ShoppingCart cart; // có thể xài chung

	Scanner sc = new Scanner(System.in); // để bên main
	
	public Shop() {	}
	
	public Shop(String shopName, Database database) {
		this.shopName = shopName;
		this.database = database;
	}
	
	public void startShopping() {
		// Login
		Customer currentCustomer;
		// while (doLogin() == null);
		do {
			currentCustomer = doLogin();
			if (currentCustomer == null) {
				System.out.println("Invalid username/password");
			}
		} while (currentCustomer == null);
		
		// Shopping
		boolean continueShopping;
		do {
			continueShopping = useShoppingMenu(currentCustomer);
		} while (continueShopping);
		System.out.println("Shopping at " + shopName + " ended.");
	}
	
	public Customer doLogin() {
		System.out.println("------------------------------");
		System.out.print("| ID: ");
		String username = sc.nextLine();
		System.out.print("| Password: ");
		String password = sc.nextLine();
		System.out.println("------------------------------");
		
		for (Customer customer : database.customerDB) {
			if ((username.equals(customer.username)) && (password.equals(customer.password))) {
				cart = new ShoppingCart();
				return customer;
			}
		}
		return null;
	}
	
	public boolean useShoppingMenu(Customer customer) {
		// Có thể để bên ngoài luôn, thường constant đi theo class, còn trong nội bộ function thì chỉ variable
		final int OPT_EXIT = 0;
		final int OPT_VIEW_CART = 1;
		final int OPT_VIEW_RANK = 2;
		final int OPT_ADD_PRODUCT = 3;
		final int OPT_CHECKOUT = 4;
		
		System.out.println("\nWelcome to our " + shopName);
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
				// Hoặc là trong main console, hoặc là trong cart (cái nào cũng ok)
				// => tùy vào cái scope của yêu cầu, nếu chỉ in thôi thì để trong ShoppingCart 
				// => nếu cần thêm logic tính toán thêm nữa (vd như checkout) thì ở main
				cart.showItems();
				break;
			case OPT_VIEW_RANK:
				showRankList();
				System.out.println("Your current rank: " + customer.rank.name);
				break;
			case OPT_ADD_PRODUCT:
				showProductList();
				selectProductToCart();
				break;
			case OPT_CHECKOUT:
				checkOut(customer);
				break;
			default:
				System.out.println("Invalid option, please try again.");
		}
		return true;
	}
	
	public void showProductList() {		
		for (int i=0; i<database.productDB.size(); i++) {
			System.out.println("\t" + (i+1) + ". " + database.productDB.get(i).getInfo());
		}
	}
	
	// Q: Có nên chia service theo database kg? vd bỏ nó chung 1 package
	// => Service nên theo package của service (???) Xem lại recording 10/5/2025
	
	// Q: Xài nhiều service như vậy thì có gom service lên đầu luôn kg
	// Có thể để hết service làm attribute của class luôn, như cái scanner (nhưng nhớ là đúng scope)
	
	// ĐÔI LÚC CÓ MẤY CÁI CLASS CHỈ CHỨA
	// - ATTRIBUT -> DATA: vd như customer (ng ta gọi là POJO - Plain Old Java Object)
	// - FUNCTIONS -> SERVICE: gọi là service class, tạo ra để phục vụ cho 1 logic gì đó, xài class attribute
	// 		Vd: AuthenticationService
	
	// nextInt & nextLine => nếu xài nextInt trước nextLine, thì nó sẽ lấy số 1, xong còn dư \n cũ ở trước đó => error "" for input string
	// => tiêu hủy dấu enter bằng cách gọi thêm nextLine() trước dòng scanner trước đó của mình
	
	// doAddProductToCart
	public void selectProductToCart() {
		// Get user input (product)
		System.out.print("Select a product: ");
		int productIndex = Integer.parseInt(sc.nextLine()) - 1;
		if (productIndex < 0 || productIndex > database.productDB.size()) {
			System.out.print("Invalid product option");
			return;
		}
		Product selectedProduct = database.productDB.get(productIndex);
		
		// Get user input (quantity)
		System.out.print("Enter quantity for " + selectedProduct.getInfo() + ": ");
		int quantity = Integer.parseInt(sc.nextLine());

		// ĐEM CẢ ĐỐNG DƯỚI ĐÂY LÀM 1 FUNCTION BÊN CART => cart.addToCart(product, quantity)
		// Q: Việc pass params kg liên quan lắm đến cart (cái mà nên là CartItem), thì pass vô có sao kg (về logic)?
		// => Trong trường hợp này mình convert sang đc, và nó cũng có liên quan tí nên kg sao
		// => Nếu cho chặt chẽ hơn, thì move hết function sang ShoppingCartService, thì nó kg còn vấn đề gì
		// Trong cái cart service đó, mình phải pass luôn shopping cart vào, ex: cart.addToCart(cart, product, quantity)
		
		// Check if the product existed in cart
		// Yes -> update quantity, No -> add new item
		CartItem item = cart.findProduct(selectedProduct);
		if (item != null) {
			item.quantity += quantity;
			System.out.println("Updated quantity.");
		} else {
			cart.addItem(new CartItem(selectedProduct.name, selectedProduct.price, quantity));
			System.out.println("Added product to your cart.");
		}
	}
	
	public void showRankList() {
		System.out.println("Available ranks and promotions:");
		for (int i=0; i<database.rankDB.size(); i++) {
			System.out.println("\t" + (i+1) + ". " + database.rankDB.get(i).getInfo());
		}
	} // file txt cho database
	
	public void checkOut(Customer customer) {
		double totalProductPrice = cart.getTotalPrice();
		if (totalProductPrice==0) {
			System.out.println("You don't have any item in shopping cart.");
		} else {
			System.out.println("\nCHECKOUT");
			System.out.println("------------------------------");
			// Show all products in cart
			cart.showItems();
			System.out.println("Ship: " + database.shippingPrice + " aud");
			System.out.println("=> Total price: " + (totalProductPrice + database.shippingPrice));
			System.out.println();
			// Calculate discount based on customer rank
			double discount = customer.rank.applyDiscount(totalProductPrice, database.shippingPrice);
			System.out.println("Rank promotion: " + customer.rank.getInfo());
			System.out.println("=> Total discount: -" + discount + " aud");
			System.out.println();
			// Calculate final price
			double finalPrice = totalProductPrice + database.shippingPrice - discount;
			System.out.println("Final price: " + finalPrice + " aud");
			System.out.println("------------------------------");
			System.out.println("Check out successful!");
			
			cart.clear();
		}
	}
}
