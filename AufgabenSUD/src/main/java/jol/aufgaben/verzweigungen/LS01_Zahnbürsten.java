package main.java.jol.aufgaben.verzweigungen;

import main.java.jol.util.Utilities;

public class LS01_Zahnbürsten {
	private static final int STANDARD_PRICE_CT = 250;
	private static final char CATEGORY_SOFT = 'w';
//	private static final char CATEGORY_MEDIUM = 'm';
	private static final char CATEGORY_HARD = 'h';

	private static final char HANDLE_KIDS = 'k';
	private static final char HANDLE_NORMAL = 'n';
	private static final char HANDLE_BIG = 'g';

	public static void main(String[] args) {
		// input
		char category = Utilities.getChar(
				"Geben Sie die Kategorie an, welche Ihre Zahnbürsten haben sollen.\nZulässig sind hier 'w' (weich), 'm' (mittel) und 'h' (hart): ",
				"Der angegebene Wert war unzulässig. bitte versuchen Sie es erneut: ");
		char handleType = Utilities.getChar(
				"Geben Sie den Grifftypen an, welchen Ihre Zahnbürsten haben sollen.\nZulässig sind hier 'k' (kinder), 'n' (normal) und 'g' (groß): ",
				"Der angegebene Wert war unzulässig. bitte versuchen Sie es erneut: ");
		int orderQantity = Utilities.getInt(
				"Geben Sie an, welche Menge der angegebenen Zahnbürstenart Sie bestellen möchten: ",
				"Dieser Wert war unzulässig. Versuchen Sie es erneut: ");

		// processing
		double discount = getProcentualDiscount(orderQantity, category, handleType);
		int pricePerPieceCT = getPricePerPieceInCT(discount);
		int priceWithDiscount = getFullPriceInCT(orderQantity, discount);

		// output
		System.out.println("Die Ermäßigung für Ihre Bestellung beträgt " + discount + "%.");
		System.out.println("Der Stückpreis für Ihre Bestellung beträgt " + ((double) (pricePerPieceCT) / 100) + "€.");
		System.out
				.println("Der Gesamtpreis für Ihre Bestellung beträgt " + ((double) (priceWithDiscount) / 100) + "€.");
	}

	/**
	 * calculates the orders dicsount
	 * 
	 * @param orderQantity number of ordered pieces
	 * @param category     the toothbrushs bristles hardness
	 * @param handleType   the toothbrushs handle-size
	 * @return the orders discount
	 */
	private static double getProcentualDiscount(int orderQantity, char category, char handleType) {
		// In this method one could also use an switch. I do prefer the if-else cascade.

		// generel reduction
		double discount = 0;
		if (orderQantity > 500)
			discount += 5;
		else if (orderQantity > 20000)
			discount += 10;

		// further reduction:
		if (handleType == HANDLE_KIDS)
			if (category == CATEGORY_SOFT)
				discount += 5;
			else
				discount += 2;
		else if (category == CATEGORY_SOFT && (handleType == HANDLE_NORMAL || handleType == HANDLE_BIG))
			discount += 1;

		// less reduction:
		if (category == CATEGORY_HARD)
			if (handleType == HANDLE_NORMAL) {
				discount -= 2.5;
			} else if (handleType == HANDLE_NORMAL)
				discount -= 5;

		return discount;
	}

	/**
	 * calculates the price per piece if the discount is substracted
	 * 
	 * @param discount the procentual discount of the order
	 * @return the discounted price per piece
	 */
	private static final int getPricePerPieceInCT(double discount) {
		return (int) (STANDARD_PRICE_CT - (STANDARD_PRICE_CT * (discount / 100)));
	}

	/**
	 * calculates the orders full price id the discout is substracted
	 * 
	 * @param orderQantity number of ordered pieces
	 * @param discount     procentual discount per piece
	 * @return the orders full price
	 */
	private static final int getFullPriceInCT(int orderQantity, double discount) {
		int pricePerPiece = getPricePerPieceInCT(discount);
		return orderQantity * pricePerPiece;
	}
}
