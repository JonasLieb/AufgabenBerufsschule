package main.java.jol.aufgaben.arrays.zusatz.util;

public class Position {
	public int x;
	public int y;

	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Position) {
			Position p = (Position) obj;
			if (p.x == x && p.y == y)
				return true;

		}
		return false;
	}
}
