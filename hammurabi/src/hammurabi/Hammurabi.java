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
	int acresChange = 0;
	double harvest = 0;
	double populationDeath = 0;
	double populationBirth = 0;
	double plagueDeath = 0;
	double ratStorageEaten = 0;
	double acresPrice = 25;
	Scanner sc = new Scanner(System.in);
	private void runGame() {

		while (!finished) {
			year = year + 1;
			
			printStatus();
			getOrders();
			calculateEffects();
			calculateCatastroph();
			printEffects();
		}

	}
	private void printStatus() {
		System.out.println("Year = " + year);
		System.out.println("Population = " + population);
		System.out.println("Acres = " + acres);
		System.out.println("Storage = " + storage + " bushels");
		System.out.println("The price for one acres right now is " + acresPrice);
	}
	
	private void getOrders() {
		boolean valid = false;
		do {
			System.out.println("How many acres do you want to buy?");
			acresChange = sc.nextInt();
			System.out.println("How much do you want to feed to your population?");
			feed = sc.nextInt();
			System.out.println("How many aceres do you want to seed, with bushels?");
			seed = sc.nextInt();

			valid = true;
			if(acresChange > acres){
				System.out.println("You only have " + acres + " acres");
				valid = false;
			}
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
		System.out.println();
	}
	
	private void calculateEffects() {
		acres	+= acresChange;
		storage += -acresChange * acresPrice;
		storage -= feed;
		storage -= seed;
		harvest = seed * (Math.random() * 4 + 1);
		storage += harvest;
		harvest = Math.round(harvest*100)/100.0; 
		int populationDelta = feed / FEED_PER_PERSON - population;
		if(populationDelta < 0){
			populationDeath = -populationDelta;
		} else {
			populationDeath = 0;
		}
		
			population -= populationDeath;
			populationBirth = population * (Math.random() * 0.1 + 0.05);
			population += populationBirth;
		acresPrice = 100 * (Math.random() * 0.1 + 0.15);
		acresPrice = Math.round(acresPrice*100)/100.0; 
	}
	private void calculateCatastroph() {
		if(Math.random() < 0.1){
			plagueDeath = population * (Math.random() * 0.1 + 0.4);
			plagueDeath = Math.round(plagueDeath*100)/100.0; 
			population -= plagueDeath;
		} else {
			plagueDeath = 0;
		}
		if(Math.random() < 0.5){
			ratStorageEaten = storage * (Math.random() * 0.05 + 0.15);
			ratStorageEaten = Math.round(ratStorageEaten*100)/100.0; 
			storage -= ratStorageEaten;
		}
		else ratStorageEaten = 0;
	}
	
	private void printEffects() {
		System.out.println("This year your harvest was " + harvest + " bushels" );
		System.out.println(populationDeath + " people starved to death");
		System.out.println(populationBirth + " people were born");
		if(plagueDeath > 0){
			System.out.println("The plague killed " + plagueDeath + " people");			
		}
		if(ratStorageEaten > 0){
			System.out.println("Rats have eaten " + ratStorageEaten + " bushels of your storage");
		}
		
	}



	public static void main(String[] args) {
		Hammurabi hammurabi = new Hammurabi();
		hammurabi.runGame();
	}

}
