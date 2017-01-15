package hammurabi;

import java.util.Scanner;

public class Hammurabi {
	public static final int FEED_PER_PERSON = 20;
	int year = 0;
	int population = 100;
	int acres = 1000;
	int storage = 2800;
	boolean finished = false;
	int feed = 0;
	int seed = 0;
	double harvest = 0;
	double populationDeath = 0;
	double populationBirth = 0;
	Scanner sc = new Scanner(System.in);
	private void runGame() {

		while (!finished) {
			year = year + 1;
			
			printStatus();
			getOrders();
			calculateEffects();
			printEffects();
		}

	}
	private void printStatus() {
		System.out.println();
		System.out.println("Year = " + year);
		System.out.println("Population = " + population);
		System.out.println("Acres = " + acres);
		System.out.println("Storage = " + storage + " bushels");
	}
	
	private void getOrders() {
		boolean valid = false;
		do {
			System.out.println("How much do you want to feed to your population?");
			feed = sc.nextInt();
			System.out.println("How many aceres do you want to seed, with bushels?");
			seed = sc.nextInt();

			valid = true;
			if(seed + feed > storage){
				System.out.println("Your storage has only " + storage + " bushels.");
				valid = false;
			}
			if(seed > acres){
				System.out.println("You have only " + acres + " acres.");
				valid = false;
			}
			if(seed > population * 10){
				System.out.println("You can only sow " + population * 10 + " bushels");	
				valid = false;
			}
		} while(!valid);
		
	}
	
	private void calculateEffects() {
		storage -= feed;
		storage -= seed;
		harvest = seed * (Math.random() * 6 + 2);
		storage += harvest;
		
		int populationDelta = feed / FEED_PER_PERSON - population;
		if(populationDelta < 0){
			populationDeath = -populationDelta;
		} else {
			populationDeath = 0;
		}
		
		population -= populationDeath;
		populationBirth = population * (Math.random() * 0.1 + 0.04);
		population += populationBirth;
	}
	
	private void printEffects() {
		System.out.println("This year your harvest was " + harvest + " bushels" );
		System.out.println(populationDeath + " people starved to death");
		System.out.println(populationBirth + " people were born");
	}



	public static void main(String[] args) {
		Hammurabi hammurabi = new Hammurabi();
		hammurabi.runGame();
	}

}
