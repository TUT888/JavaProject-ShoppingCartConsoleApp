package shoppingcart.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import shoppingcart.common.Storage;
import shoppingcart.dto.Rank;

public class RankService {
	public ArrayList<Rank> getAllRanks() {
		String filepath = Storage.DB_PATH + Storage.currentShop.dbPath + "/rank.txt";
		
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
	
	public void showRankList() {
		ArrayList<Rank> rankList = getAllRanks();
		
		System.out.println("Available ranks and promotions:");
		for (int i=0; i<rankList.size(); i++) {
			Rank rank = rankList.get(i);
			System.out.println((i+1) + ". " + rank.name + ": " + rank.description);
		}
	}
	
	public Rank getRankByName(String name) {
		ArrayList<Rank> rankList = getAllRanks();
		
		for (Rank rank : rankList) {
			if (rank.name.equals(name)) {
				return rank;
			}
		}
		return null;
	}
	
	public double applyDiscount(Rank rank, double productPrice) {
		double shippingDiscount = Storage.currentShop.shippingPrice * rank.shippingDiscount / 100;
		double productDiscount = productPrice * rank.productDiscount / 100;
		
		return shippingDiscount + productDiscount;
	}
}
