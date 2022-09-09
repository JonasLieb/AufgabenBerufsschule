package main.java.jol.aufgabenmethoden;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import main.java.jol.util.swing.StyledButton;
import main.java.jol.util.swing.StyledLabel;

public class Taschenrechner extends JFrame {

	public static void main(String[] args) {
		Taschenrechner tr = new Taschenrechner();
		tr.setVisible(true);
	}

	private static final long serialVersionUID = 1L;

	// GUI components
	private StyledButton[] buttons = new StyledButton[10];
	private JPanel upperPanel;
	private JPanel buttonPanel;
	private JPanel calculationPanel;
	private StyledLabel label;
	private StyledButton plusButton;
	private StyledButton multButton;
	private StyledButton minusButton;
	private StyledButton divButton;
	private StyledButton calcButton;
	private StyledButton clearAllButton;
	private StyledButton commaButton;
	private StyledButton openBracketsButton;
	private StyledButton closeBracketsButton;
	private StyledButton clearLastCharButton;

	/**
	 * constructor for the Taschenrechner
	 */
	public Taschenrechner() {
		initGui();
	}

	/**
	 * Builds the whole GUI that is used later on
	 */
	private void initGui() {
		this.setTitle("Taschenrechner für dummies");
		this.setLayout(new BorderLayout());
		this.setBounds(100, 50, 400, 500);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);

		label = new StyledLabel("");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setVerticalAlignment(SwingConstants.CENTER);
		label.setHorizontalTextPosition(SwingConstants.LEFT);
		label.setVerticalTextPosition(SwingConstants.CENTER);
		label.setPreferredSize(new Dimension(Integer.MAX_VALUE, 100));

		upperPanel = new JPanel();
		upperPanel.add(label);
		upperPanel.setBackground(Color.BLACK);

		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(4, 3));
		calculationPanel = new JPanel();
		calculationPanel.setLayout(new GridLayout(0, 2));

		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = new StyledButton(String.valueOf(i), label);
		}

		plusButton = new StyledButton("+", label);
		minusButton = new StyledButton("-", label);
		multButton = new StyledButton("*", label);
		divButton = new StyledButton("/", label);
		calcButton = new StyledButton("=");
		calcButton.addActionListener(a -> {
			try {
				label.setText(String.valueOf(eval(label.getText())));
			} catch (Exception e) {
				label.setText("Error");
			}
		});
		clearAllButton = new StyledButton("C");
		clearAllButton.addActionListener(a -> {
			label.setText("");
		});
		commaButton = new StyledButton(".", label);
		openBracketsButton = new StyledButton("(", label);
		closeBracketsButton = new StyledButton(")", label);
		clearLastCharButton = new StyledButton("CE");
		clearLastCharButton.addActionListener(a -> {
			String s = label.getText().trim();
			label.setText(s.substring(0, s.length() - 1));
		});

		addButtons();

		this.add(upperPanel, BorderLayout.NORTH);
		this.add(buttonPanel, BorderLayout.CENTER);
		this.add(calculationPanel, BorderLayout.EAST);
	}

	/**
	 * Determins where and in wich order the buttons are added
	 */
	private void addButtons() {
		buttonPanel.add(buttons[7]);// 7
		buttonPanel.add(buttons[8]);// 8
		buttonPanel.add(buttons[9]);// 9
		buttonPanel.add(buttons[4]);// 4
		buttonPanel.add(buttons[5]);// 5
		buttonPanel.add(buttons[6]);// 6
		buttonPanel.add(buttons[1]);// 1
		buttonPanel.add(buttons[2]);// 2
		buttonPanel.add(buttons[3]);// 3
		buttonPanel.add(commaButton);
		buttonPanel.add(buttons[0]);// 0
		buttonPanel.add(clearAllButton);

		calculationPanel.add(plusButton);
		calculationPanel.add(minusButton);
		calculationPanel.add(multButton);
		calculationPanel.add(divButton);
		calculationPanel.add(openBracketsButton);
		calculationPanel.add(closeBracketsButton);
		calculationPanel.add(clearLastCharButton);
		calculationPanel.add(calcButton);
	}

	/**
	 * handles the actual calculation
	 * 
	 * @param str the given arithmetic task
	 * @return the calcultions result
	 */
	public static double eval(final String str) {
		return new Object() {
			int pos = -1, ch;

			void nextChar() {
				ch = (++pos < str.length()) ? str.charAt(pos) : -1;
			}

			boolean eat(int charToEat) {
				while (ch == ' ')
					nextChar();
				if (ch == charToEat) {
					nextChar();
					return true;
				}
				return false;
			}

			double parse() {
				nextChar();
				double x = parseExpression();
				if (pos < str.length())
					throw new RuntimeException("Unexpected: " + (char) ch);
				return x;
			}

			double parseExpression() {
				double x = parseTerm();
				for (;;) {
					if (eat('+'))
						x += parseTerm(); // addition
					else if (eat('-'))
						x -= parseTerm(); // subtraction
					else
						return x;
				}
			}

			double parseTerm() {
				double x = parseFactor();
				for (;;) {
					if (eat('*'))
						x *= parseFactor(); // multiplication
					else if (eat('/'))
						x /= parseFactor(); // division
					else
						return x;
				}
			}

			double parseFactor() {
				if (eat('+'))
					return +parseFactor(); // unary plus
				if (eat('-'))
					return -parseFactor(); // unary minus

				double x;
				int startPos = this.pos;
				if (eat('(')) { // parentheses
					x = parseExpression();
					if (!eat(')'))
						throw new RuntimeException("Missing ')'");
				} else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
					while ((ch >= '0' && ch <= '9') || ch == '.')
						nextChar();
					x = Double.parseDouble(str.substring(startPos, this.pos));
				} else if (ch >= 'a' && ch <= 'z') { // functions
					while (ch >= 'a' && ch <= 'z')
						nextChar();
					String func = str.substring(startPos, this.pos);
					if (eat('(')) {
						x = parseExpression();
						if (!eat(')'))
							throw new RuntimeException("Missing ')' after argument to " + func);
					} else {
						x = parseFactor();
					}
					if (func.equals("sqrt"))
						x = Math.sqrt(x);
					else if (func.equals("sin"))
						x = Math.sin(Math.toRadians(x));
					else if (func.equals("cos"))
						x = Math.cos(Math.toRadians(x));
					else if (func.equals("tan"))
						x = Math.tan(Math.toRadians(x));
					else
						throw new RuntimeException("Unknown function: " + func);
				} else {
					throw new RuntimeException("Unexpected: " + (char) ch);
				}

				if (eat('^'))
					x = Math.pow(x, parseFactor()); // exponentiation

				return x;
			}
		}.parse();
	}
}
