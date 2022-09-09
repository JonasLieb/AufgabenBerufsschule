package main.java.jol.aufgabe6;

import main.java.jol.util.Utilities;

public class LS01_Kindergeld {
	// constants
	private static final int HIGHEST_LOW_INCOME = 45000;
	private static final int[] LOW_INCOME_KINDERGELD = { 70, 130, 220, 240 };
	private static final int[] HIGH_INCOME_KINDERGELD = { 70, 70, 140, 140 };

	public static void main(String[] args) {
		// input
		double income = Utilities.getDouble("Geben Sie ihr Jährliches Einkommen ein: ",
				"Dieser Wert war leider unzulässig. Versuchen Sie es erneut: ");
		int children = Utilities.getInt("Geben Sie die Anzahl Ihrer Kinder ein: ",
				"Dieser Wert war leider unzulässig. Versuchen Sie es erneut: ");

		// processing
		int kindergeld = getKindergeld(income, children);

		// output
		System.out.println("Kindergeld: " + kindergeld);
	}

	/**
	 * will calculate the 'Kindergeld' based on income and count of children
	 * 
	 * @param income   the yearly income
	 * @param children the amount of children
	 * @return
	 */
	private static int getKindergeld(double income, int children) {
		// many programers prefer to set '{' after every if / else / else if even if
		// they are unnecessary. I do prefer placing them only if needed:

		// check if incoming values are valid
		if (children <= 0)
			return 0;

		int ret = 0;
		if (income < HIGHEST_LOW_INCOME)
			for (int i = 0; i < children; i++) {
				if (i < LOW_INCOME_KINDERGELD.length)
					ret += LOW_INCOME_KINDERGELD[i];
				else
					ret += LOW_INCOME_KINDERGELD[i - 1];
			}
		else
			for (int i = 0; i < children; i++) {
				if (i < HIGH_INCOME_KINDERGELD.length)
					ret += HIGH_INCOME_KINDERGELD[i];
				else
					ret += HIGH_INCOME_KINDERGELD[i - 1];
			}

		return ret;
	}
}
