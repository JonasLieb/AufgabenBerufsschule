package main.java.jol.aufgaben.arrays.zusatz.util.swing;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JPanel;

import main.java.jol.aufgaben.arrays.zusatz.util.Position;

public class CellPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private static final int STANDARD_SIZE = 100;
	private static final int GAME_START_ALIVE_CELLS_COUNT = 1000;
	private Cell[][] cells;

	public CellPanel(int sideLength) {
		if (sideLength <= 0)
			sideLength = STANDARD_SIZE;
		cells = new Cell[sideLength][sideLength];
		initField(sideLength);
		initGui(sideLength);
	}

	private void initField(int sideLength) {
		// 8 true-Zellen ermitteln:
		ArrayList<Position> list = new ArrayList<Position>();
		int x;
		int y;
		Position p;
		while (list.size() < Math.min(GAME_START_ALIVE_CELLS_COUNT, Math.pow(sideLength, 2))) {
			x = ThreadLocalRandom.current().nextInt(0, sideLength);
			y = ThreadLocalRandom.current().nextInt(0, sideLength);
			p = new Position(x, y);
			if (!list.contains(p))
				list.add(p);
		}

		// echtes Füllen unseres Spielfeldes
		Position currentPos;
		for (int curY = 0; curY < cells.length; curY++) {
			for (int curX = 0; curX < cells.length; curX++) {
				currentPos = new Position(curY, curX);
				if (list.contains(currentPos)) {
					cells[curY][curX] = new Cell(true, curX, curY);
				} else {
					cells[curY][curX] = new Cell(false, curX, curY);
				}
			}
		}
	}

	private void initGui(int sideLength) {
		this.setLayout(new GridLayout(sideLength, sideLength));
		for (int curY = 0; curY < cells.length; curY++) {
			for (int curX = 0; curX < cells.length; curX++) {
				this.add(cells[curY][curX]);
			}
		}
	}

	public void updateCells() {
		// Zellen auf Basis des Feldes aktualisieren
		Cell[][] cellsKopie = cells.clone();
		cellsKopie = new Cell[cells.length][cells[0].length];
		for (int y = 0; y < cellsKopie.length; y++) {
			for (int x = 0; x < cellsKopie[y].length; x++) {
				cellsKopie[y][x] = new Cell(cells[y][x].isAlive(), x, y);
			}
		}

		for (int curY = 0; curY < cellsKopie.length; curY++) {
			for (int curX = 0; curX < cellsKopie.length; curX++) {
				cells[curY][curX].updateState(cellsKopie);
			}
		}
	}
}
