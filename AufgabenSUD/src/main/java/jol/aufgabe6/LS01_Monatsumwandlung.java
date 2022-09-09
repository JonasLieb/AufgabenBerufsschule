package main.java.jol.aufgabe6;

import main.java.jol.util.Utilities;

public class LS01_Monatsumwandlung {
	private static final String[] MONTHS = { "Januar", "Februar", "März", "April", "Mai", "Juni", "Juli", "August",
			"September", "Oktober", "November", "Dezember" };

	public static void main(String[] args) {
		// input
		int num = Utilities.getInt("Geben Sie an, den wievielten Monat Sie als Text sehen möchten: ",
				"Der eingegebene Wert war fehlerhaft. Bitte versuchen Sie es erneut: ");
		// processing + output
		showNumToMonth(num);
	}

	/**
	 * will print a german sentence containing the months (with index i) german name
	 * 
	 * @param i the months index
	 * @return
	 */
	private static void showNumToMonth(int i) {
		try {
			System.out.println("Der " + i + ". Monat ist der " + MONTHS[i - 1]);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Da es nur 12 Monate gibt, können auch nur Zahlen von 1-12 einen Monat angeben. Der " + i
					+ ". Monat existiert nicht.");
		}
	}
}
