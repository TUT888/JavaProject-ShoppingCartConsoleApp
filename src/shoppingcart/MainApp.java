package shoppingcart;

import java.util.Scanner;

// ALL things related to console, ex print option, get user input, etc. SHOULD BE PUT IN MAIN
// Main can use the service classes to handle the operation needed (authentication, ... )
// Console app: only show direction (same same with routing...)

public class MainApp {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		Shop generalShop = new Shop("General Store", initDatabaseForGeneralShop());
		Shop stationaryStore = new Shop("Stationary Store", initDatabaseForStationaryStore());
		
		// Print menu
		boolean isContinue = true;
		do {
			System.out.println("\nSelect a shop to browse");
			System.out.println("==============================");
			System.out.println("| 0. Exit                    |");
			System.out.println("| 1. General Store           |");
			System.out.println("| 2. Stationary Store        |");
			System.out.println("==============================");
			System.out.print("Enter your option: ");
			
			int opt = Integer.parseInt(sc.nextLine());
			switch (opt) {
				case 0:
					isContinue = false;
					break;
				case 1:
					generalShop.startShopping();
					break;
				case 2:
					stationaryStore.startShopping();
					break;
				default:
					System.out.println("Invalid option");
			}
		} while (isContinue);
		sc.close();
		System.out.println("Program closed.");
	}
	
	public static Database initDatabaseForGeneralShop() {
		Database db = new Database();
		db.addProduct(new Product("Flower", 3));
		db.addProduct(new Product("Toy", 5));
		db.addProduct(new Product("Phone", 100));
		db.addProduct(new Product("Chair", 25));
		
		db.addRank(new Rank("Silver", "50% discount in shipping", 0, 50));
		db.addRank(new Rank("Gold", "2% discount in product", 2, 0));
		db.addRank(new Rank("Diamond", "3% discount in product", 3, 0));
		
		db.addCustomer(new Customer("user1", "pw1", db.rankDB.get(0)));
		db.addCustomer(new Customer("user2", "pw2", db.rankDB.get(1)));
		db.addCustomer(new Customer("user3", "pw3", db.rankDB.get(2)));
		
		db.setShippingPrice(5);
		return db;
	}
	

	public static Database initDatabaseForStationaryStore() {
		Database db = new Database();
		db.addProduct(new Product("Pencil", 3));
		db.addProduct(new Product("Ruler", 5));
		db.addProduct(new Product("Notebook", 18));
		db.addProduct(new Product("Watercolor", 36));
		db.addProduct(new Product("Calculator", 68));
		
		db.addRank(new Rank("Basic", "20% discount in shipping", 0, 20));
		db.addRank(new Rank("Premium", "5% discount in product and 25% in shipping", 5, 25));
		
		db.addCustomer(new Customer("user1", "pw1", db.rankDB.get(0)));
		db.addCustomer(new Customer("user2", "pw2", db.rankDB.get(1)));

		db.setShippingPrice(8);
		return db;
	}
}
