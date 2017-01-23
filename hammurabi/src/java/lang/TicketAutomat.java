package java.lang;

import java.util.Scanner;


public class TicketAutomat {

	int balance;
	int total;
	int destinationNumber;
	int travellclass;
	double price = 0;
	boolean finished = false;
	Scanner sc = new Scanner(System.in);
	private void runAutomat() {

		while (!finished) {
			printStatus();
			getOrders();
			calculateEffects();
			printEffects();
		}
	} 

	private void printStatus() {
		System.out.println("Welcome, your postcode right now is 1000, the bigger the difference between your desitnation and current location, the price will get higher. ");
	}

	private void getOrders() {
		boolean valid = false;
		do {
			System.out.println("Destination postcode?");
			destinationNumber = sc.nextInt();
			System.out.println("Do you want to travell with 1st, 2nd Class or half price? (answer with: 1, 2 or 1/2)");
			travellclass = sc.nextInt();
			
			valid = true;
			
			if(destinationNumber < 0){
				System.out.println("Your destination postcode can't be negative!");
				valid = false;
			}

		} while (!valid);
	}

	private void calculateEffects() {
		if(destinationNumber < 2000 && destinationNumber > 0){
			price = 10;
		}
		else if(destinationNumber < 3000 && destinationNumber > 2000){
			price = 15;
		}
		else if(destinationNumber < 4000 && destinationNumber > 3000){
			price = 20;
		}
		else if(destinationNumber < 5000 && destinationNumber > 4000){
			price = 25;
		}
		else if(destinationNumber < 6000 && destinationNumber > 5000){
			price = 30;
		}
		else if(destinationNumber < 7000 && destinationNumber > 6000){
			price = 35;
		}
		else if(destinationNumber < 8000 && destinationNumber > 7000){
			price = 40;
		}
		
		if(travellclass == 1){
			price = price * 2;
		}
		else if(travellclass == 2){
			price = price * 1;
		}
		else if(travellclass == 0.5){
			price = price * 0.5;
		}
		balance += price;
	}

	private void printEffects() {

	}

	public void reset() {
		balance = 0;
	}

	public void insertCoin(int value) {
		balance = balance + value;
	}
	public static void main(String[] args) {
		TicketAutomat ticketAutomat = new TicketAutomat();
		hammurabi.runAutomat();
	}
}
