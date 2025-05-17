package shoppingcart.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import shoppingcart.dto.Rank;
import shoppingcart.dto.Shop;

public class RankService {
	private static final String DB_PATH = "src/shoppingcart/db/";
	
	public ArrayList<Rank> getAllRanks(Shop shop) {
		String filepath = DB_PATH + shop.dbPath + "/rank.txt";
		
		ArrayList<Rank> rankList = new ArrayList<Rank>();
		
		try {
			// Read from text file
			File myObj = new File(filepath);
			Scanner myReader = new Scanner(myObj);
			myReader.nextLine();
			
			// Read row by row
			while (myReader.hasNextLine()) {
				String textRow = myReader.nextLine();
				String[] data = textRow.split(",");
				rankList.add(new Rank(data[0], data[1], Integer.parseInt(data[2]), Integer.parseInt(data[3])));
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
		
		return rankList;
	}
	
	public Rank getRankByName(Shop shop, String name) {
		ArrayList<Rank> rankList = getAllRanks(shop);
		
		for (Rank rank : rankList) {
			if (rank.name.equals(name)) {
				return rank;
			}
		}
		return null;
	}
	
	public double applyDiscount(Shop shop, Rank rank, double productPrice) {
		double shippingDiscount = shop.shippingPrice * rank.shippingDiscount / 100;
		double productDiscount = productPrice * rank.productDiscount / 100;
		
		return shippingDiscount + productDiscount;
	}
}
