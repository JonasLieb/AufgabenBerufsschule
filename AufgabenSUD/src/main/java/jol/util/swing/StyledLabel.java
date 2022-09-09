package main.java.jol.util.swing;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class StyledLabel extends JLabel {
	private static final long serialVersionUID = 1L;

	private static final Color BACKGROUND_COLOR = new Color(50, 50, 50);
	private static final Color FOREGROUND_COLOR = new Color(255, 255, 255);

	public StyledLabel(String text) {
		super(text);
		style();
	}

	private void style() {
		this.setOpaque(true);
		this.setBackground(BACKGROUND_COLOR);
		this.setForeground(FOREGROUND_COLOR);
		this.setFont(new Font("", Font.BOLD, 30));
		this.setFocusable(false);
	}
}
