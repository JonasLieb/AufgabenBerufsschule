package main.java.jol.aufgaben.methoden.aufg3;

public class MainTemperaturCSV {
	public static void main(String[] args) {
		System.out.println(getCelsiusFromFarenheit(86));
		System.out.println(getFarenheitFromCelsius(30));
		System.out.println(getKelvinFromCelsius(30));
	}

	/**
	 * converts the given temperature from farenheit to celsius
	 * 
	 * @param farenheit the given temperature in farenheit
	 * @return the temperature in celsius
	 */
	public static double getCelsiusFromFarenheit(double farenheit) {
		return (farenheit - 32) / 1.8;
	}

	/**
	 * converts the given temperature from celsius to farenheit
	 * 
	 * @param celsius the given temperature in celsius
	 * @return the temperature in farenheit
	 */
	public static double getFarenheitFromCelsius(double celsius) {
		return celsius * 1.8 + 32;
	}

	/**
	 * converts the given temperature from celsius to kelvin
	 * 
	 * @param celsius the given temperature in celsius
	 * @return the temperature in kelvin
	 */
	public static double getKelvinFromCelsius(double celsius) {
		return celsius + 273.15;
	}
}
