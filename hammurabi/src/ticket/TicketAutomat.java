package ticket;

import java.lang.String;
import java.lang.System;
import java.util.Scanner;

public class TicketAutomat {

	int balance;
	int total;
	int destinationNumber;
	int travellclass;
	double price = 0;
	double payment = 0;
	boolean finished = false;
	double returnMoney = 0;
	Scanner sc = new Scanner(System.in);

	private void runAutomat() {

		while (!finished) {
			printStatus();
			getOrders();
			calculateEffects();
			payTicket();
			printEffects();
		}
	}

	private void printStatus() {
		System.out.println(
				"Welcome, your postcode right now is 1000, the bigger the difference between your desitnation and current location, the price will get higher. ");
	}

	private void getOrders() {
		boolean valid = false;
		do {
			System.out.println("Destination postcode?");
			destinationNumber = sc.nextInt();
			valid = true;

			if (destinationNumber < 0) {
				System.out.println("Your destination postcode can't be negative!");
				valid = false;

			} else {
				System.out.println(
						"Do you want to travell with 1st, 2nd Class or 3rd class? (answer with: 1, 2 or 3)");
				travellclass = sc.nextInt();
			}

		} while (!valid);
	}

	private void calculateEffects() {
		if (destinationNumber < 2000 && destinationNumber > 0) {
			price = 10;
		} else if (destinationNumber <= 3000 && destinationNumber > 2000) {
			price = 15;
		} else if (destinationNumber <= 4000 && destinationNumber > 3000) {
			price = 20;
		} else if (destinationNumber <= 5000 && destinationNumber > 4000) {
			price = 25;
		} else if (destinationNumber <= 6000 && destinationNumber > 5000) {
			price = 30;
		} else if (destinationNumber <= 7000 && destinationNumber > 6000) {
			price = 35;
		} else {
			price = 40;
		}

		if (travellclass == 1) {
			price = price * 2;
		} else if (travellclass == 2) {
			price = price * 1;
		} else if (travellclass == 3) {
			price = price * 0.5;
		}
		balance += price;
	}

	private void payTicket() {
		System.out.println("The price for your ticket is " + price);
		System.out.println("You can pay with the following values: 0.10, 0.20, 0.50, 1, 2, 5, 10, 20, 50, 100");
		payment = sc.nextDouble();
		do {
			if (price > 0) {
				price = price - payment;
				System.out.println("You still have to pay " + price);
				payment = sc.nextDouble();
			}
		} while (price > 0);
	}

	public void reset() {
		balance = 0;
	}

	private void printEffects() {
		boolean valid = false;
		do {
			valid = true;
			if (payment < price) {
				System.out.println("You need to pay " + (price - payment) + "more");
				valid = false;
			} else if (payment > price) {
				System.out.println("You are getting " + (payment - price) + " back");
				returnMoney = payment - price;
			}
			

		} while (!valid);
		System.out.println("Heres your ticket to " + destinationNumber);
		finished = true;
	}

	public static void main(String[] args) {
		TicketAutomat ticketAutomat = new TicketAutomat();
		ticketAutomat.runAutomat();
	}
}
