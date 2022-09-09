package main.java.jol.aufgaben.arrays.zusatz.util.swing;

import java.awt.Color;

import javax.swing.JPanel;

public class Cell extends JPanel {
	private static final long serialVersionUID = 1L;

	int posX;
	int posY;
	private static final Color ALIVE_BACKGROUND = Color.GREEN;
	private static final Color DEAD_BACKGROUND = Color.WHITE;
	private static final int LOWER_LIVING_LIMIT = 2;
	private static final int UPPER_LIVING_LIMIT = 3;

	public Cell(boolean alive, int x, int y) {
		this.posX = x;
		this.posY = y;
		this.setToolTipText("X:" + x + " Y: " + y);
		setState(alive);
	}

	private void setState(boolean alive) {
		if (alive)
			setBackground(ALIVE_BACKGROUND);
		else
			setBackground(DEAD_BACKGROUND);
	}

	public boolean isAlive() {
		return getBackground().equals(ALIVE_BACKGROUND);
	}

	public void updateState(Cell[][] field) {
		// TODO: Nachbarkonstellationen durchgehen und auf dieser Basis die Zelle ändern
		boolean nextState = false;
		int livingNeighbours = getAliveNeighboursCount(field, posX, posY);

		if (isAlive()) {
			if (livingNeighbours < LOWER_LIVING_LIMIT || livingNeighbours > UPPER_LIVING_LIMIT) {
				// Einsamkeit und Überbevölkerung:
				nextState = false;
			} else {
				// Gute bedingungen:
				nextState = true;
			}
		}

		if (!isAlive()) {
			if (livingNeighbours == 3)
				nextState = true;
		}

		setState(nextState);
	}

	private int getAliveNeighboursCount(Cell[][] field, int x, int y) {
		if (x == 0 && y == 38)
			System.out.println("");

		int minX = x - 1;
		int maxX = minX + 3;
		int minY = y - 1;
		int maxY = minY + 3;
		int count = 0;

		// Falsche Werte abfangen
		if (minX < 0)
			minX = 0;
		if (minY < 0)
			minY = 0;
		if (maxX > field.length - 1)
			maxX = field.length - 1;
		if (maxY > field[0].length - 1)
			maxY = field[0].length - 1;

		for (int curY = minY; curY < maxY; curY++) {
			for (int curX = minX; curX < maxX; curX++) {
				if (field[curY][curX].isAlive() && (curX != x || curY != y)) {
					count++;
				}
			}
		}
		return count;
	}
}
