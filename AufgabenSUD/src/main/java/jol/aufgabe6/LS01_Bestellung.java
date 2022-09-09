package main.java.jol.aufgabe6;

import main.java.jol.util.Utilities;

public class LS01_Bestellung {
	private static final double HIGH_CARRIAGE_COST = 5.5;
	private static final double NORMAL_CARRIAGE_COST = 3;

	public static void main(String[] args) {
		// input
		int amount = Utilities.getInt("Geben sie eine Bestellmenge ein: ",
				"Dieser Wert war unzulässig. Versuchen Sie es erneut: ");
		int pricePerPiece = Utilities.getInt("Geben sie einen Stückpreis ein: ",
				"Dieser Wert war unzulässig. Versuchen Sie es erneut: ");

		// processing
		int fullPriceWithoutCarriage = getPriceWithoutCarriage(amount, pricePerPiece);
		double fullPrice = getPrice(fullPriceWithoutCarriage);

		// output
		System.out.println("Der Gesamtpreis mit Transportkosten beträgt " + fullPrice + "€.");
	}

	/**
	 * calculates the order-price without carriage
	 * 
	 * @param amount        the amount of the bought article
	 * @param pricePerPiece the price of the bought article
	 * @return the orders price without carriage
	 */
	private static final int getPriceWithoutCarriage(int amount, int pricePerPiece) {
		return amount * pricePerPiece;
	}

	/**
	 * calculates the order-price with carriage
	 * 
	 * @param priceWithoutCarriage the price of the whole order without carriage
	 * @return the orders price with carriage
	 */
	private static final double getPrice(int priceWithoutCarriage) {
		// catch unexpected values
		if (priceWithoutCarriage < 0)
			return 0;

		double carriage = 0;
		if (priceWithoutCarriage < 100)
			carriage = HIGH_CARRIAGE_COST;
		else if (priceWithoutCarriage < 200)
			carriage = NORMAL_CARRIAGE_COST;

		return priceWithoutCarriage + carriage;

	}
}
