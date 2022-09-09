package main.java.jol.aufgaben.verzweigungen;

import main.java.jol.util.Utilities;

public class LS01_BMI {

	public static void main(String[] args) {
		// input
		int weight = Utilities.getInt("Bitte geben Sie ihr Gewicht in kg an: ",
				"Dieser Wert war leider unzulässig, versuchen Sie es erneut: ");
		int sizeInCM = Utilities.getInt("Bitte geben Sie ihre Größe in cm an: ",
				"Dieser Wert war leider unzulässig, versuchen Sie es erneut: ");

		// processing
		String result = getResultMessage(getBMI(weight, sizeInCM));

		// output
		System.out.println(result);
	}

	/**
	 * Builds a fitting output string based on the BMI
	 * 
	 * @param bmi the given BMI
	 * @return a message fitting the BMI
	 */
	private static String getResultMessage(double bmi) {
		String s = "Ihr BMI beträgt " + bmi + ". ";
		if (bmi < 18.5)
			s += "Sie haben Untergeicht. Kalorienvorgabe: 3000";
		else if (bmi < 25)
			s += "Sie haben Normalgewicht. Kalorienvorgabe: 2000";
		else if (bmi < 30)
			s += "Sie haben leichtes Übergeicht. Kalorienvorgabe: 1800";
		else if (bmi < 40)
			s += "Sie haben starkes Übergewicht. Kalorienvorgabe: 1200";
		else
			s += "Sie haben extremes Übergewicht. Kalorienvorgabe: 700";
		return s;
	}

	/**
	 * Determins the BMI based on weight and size in centimeters
	 * 
	 * @param weightInKg the given weight in kilogram
	 * @param sizeInCm   the given size in centimeters
	 * @return the BMI
	 */
	public static double getBMI(int weightInKg, int sizeInCm) {
		return getBMI(weightInKg, (double) (sizeInCm) / 100);
	}

	/**
	 * Determins the BMI based on weight and size in meters
	 * 
	 * @param weightInKg the given weight in kilogram
	 * @param sizeInM    the given size in meters
	 * @return
	 */
	public static double getBMI(int weightInKg, double sizeInM) {
		return (double) (weightInKg) / (Math.pow((double) sizeInM, 2));
	}
}
