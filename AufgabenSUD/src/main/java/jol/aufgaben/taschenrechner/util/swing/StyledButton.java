package main.java.jol.aufgaben.taschenrechner.util.swing;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JLabel;

public class StyledButton extends JButton {
	private static final long serialVersionUID = 1L;

	private static final Color BACKGROUND_COLOR = new Color(50, 50, 50);
	private static final Color FOREGROUND_COLOR = new Color(255, 255, 255);

	public StyledButton(String name, JLabel l) {
		this(name);
		this.addActionListener(a -> {
			l.setText(l.getText() + ((StyledButton) a.getSource()).getText());
		});
	}

	public StyledButton(String name) {
		super(name);
		style();
	}

	private void style() {
		this.setBackground(BACKGROUND_COLOR);
		this.setForeground(FOREGROUND_COLOR);
		this.setFont(new Font("", Font.BOLD, 30));
		this.setFocusable(false);
	}
}