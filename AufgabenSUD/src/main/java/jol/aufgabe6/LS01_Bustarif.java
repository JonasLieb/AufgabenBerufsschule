package main.java.jol.aufgabe6;

import main.java.jol.util.Utilities;

public class LS01_Bustarif {
	private static final int SMALL_RANGE_CT_PRICE = 28;
	private static final int MEDIUM_RANGE = 20;
	private static final int MEDIUM_RANGE_CT_PRICE = 25;
	private static final int HIGH_RANGE = 50;
	private static final int HIGH_RANGE_CT_PRICE = 20;

	public static void main(String[] args) {
		// input
		int km = Utilities.getInt("Bitte geben Sie die gefahrene Distanz in km an: ",
				"Dieser Wert ist ungültig. Versuchen Sie es erneut: ");

		// processing
		double price = ((double) getPrice(km)) / 100;

		// output
		System.out.println("Zu zahlender Preis für " + km + " km: " + price + "€.");
	}

	/**
	 * start method for the recursively processing of "getPriceRekursiv"
	 * 
	 * @param rangeInKm the driven kilometers
	 * @return price of the driven kilometers
	 */
	public static int getPrice(int rangeInKm) {
		return getPriceRekursiv(rangeInKm, 0);
	}

	/**
	 * Determins the price recursively based on the driven range in km
	 * 
	 * @param leftOverKm   kilometers, that arent calculated yet
	 * @param priceTillNow price of the already calculated kilometers
	 * @return the price of the whole journey
	 */
	private static int getPriceRekursiv(int leftOverKm, int priceTillNow) {
		int i;
		if (leftOverKm > HIGH_RANGE) {
			i = getKMAbove(HIGH_RANGE, leftOverKm) + 1;
			return getPriceRekursiv(leftOverKm - i, priceTillNow + i * HIGH_RANGE_CT_PRICE);
		} else if (leftOverKm > MEDIUM_RANGE) {
			i = getKMAbove(MEDIUM_RANGE, leftOverKm) + 1;
			return getPriceRekursiv(leftOverKm - i, priceTillNow + i * MEDIUM_RANGE_CT_PRICE);
		} else
			return priceTillNow + SMALL_RANGE_CT_PRICE * leftOverKm;
	}

	/**
	 * Determins the amount of kilometers from leftOverKm that are above range
	 * 
	 * @param range      the amount of kilometers from leftOverKm we have already
	 *                   calculated
	 * @param leftOverKm the amount of kilometers that has not been calculated yet
	 * @return
	 */
	private static int getKMAbove(int range, int leftOverKm) {
		return -1 * (range - leftOverKm);
	}
}
