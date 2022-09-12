package main.java.jol.aufgaben.schleifen.aufg8;

import main.java.jol.util.Utilities;

public class LS01_Quadrat {

	public static void main(String[] args) {
		printQuadrat();
	}

	public static void printQuadrat() {
		int num = Utilities.getInt("Geben Sie eine Seitenlänge für das Quadrat an",
				"Eingegebener Wert war ungültig. versuchen Sie es erneut.", 0, Integer.MAX_VALUE);
		printQuadrat(num, num, false);
	}

	public static void printQuadrat(int width, int height, boolean isFilled) {
		for (int row = 0; row < height; row++) {
			System.out.println();
			for (int col = 0; col < width; col++) {
				if (isFilled || row == 0 || col == 0 || row == height - 1 || col == width - 1)
					System.out.print(" *");
				else
					System.out.print("  ");

			}
		}
	}
}
