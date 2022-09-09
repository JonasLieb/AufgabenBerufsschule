package main.java.jol.aufgaben.verzweigungen;

import main.java.jol.util.Utilities;

public class LS01_Zahlenumwandlung {
	private static final String[] ONES = { "null", "eins", "zwei", "drei", "vier", "fünf", "sechs", "sieben", "acht",
			"neun" };

	public static void main(String[] args) {
		// input
		int num = Utilities.getInt("Geben Sie einen nachkommalosen Wert an, um diesen in einen Text umzuwandeln: ",
				"Dieser Wert war fehlerhaft. Bitte versuchen Sie es erneut: ");
		// processing + output
		printNumToString(num);
	}

	/**
	 * will show a String containing the numbers german name (only 0 - 9)
	 * 
	 * @param i the months index
	 * @return
	 */
	private static void printNumToString(int i) {
		try {
			System.out.println("Als Text lautet der eingegebene Wert " + i + " wie folgt: " + ONES[i]);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Die angegebene Zahl " + i + " ist nicht im Bereich von 0 bis 9 enthalten.");
		}
	}
}
