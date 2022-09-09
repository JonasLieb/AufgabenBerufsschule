package main.java.jol.aufgaben.arrays.zusatz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.java.jol.aufgaben.arrays.zusatz.util.swing.CellPanel;

public class GameOfLife extends JFrame {
	private static final long serialVersionUID = 1L;

	private CellPanel cellPanel;
	private JLabel infoLabel1;
	private JLabel infoLabel2;
	private JPanel infoPanel;
	private JPanel mainPanel;

	private boolean play;
	private Timer timer;
	private TimerTask task;

	public static void main(String[] args) {
		GameOfLife g = new GameOfLife(100);
		g.setVisible(true);
	}

	public GameOfLife(int sideLength) {
		initGui(sideLength);
	}

	private void initGui(int sideLength) {
		infoLabel1 = new JLabel("Press 'e' to show the next generation of cells");
		infoLabel2 = new JLabel("Press 'p' to play or pause the game");

		cellPanel = new CellPanel(sideLength);

		infoPanel = new JPanel();
		infoPanel.setLayout(new GridLayout(0, 1));
		infoPanel.add(infoLabel1);
		infoPanel.add(infoLabel2);

		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(infoPanel, BorderLayout.NORTH);
		mainPanel.add(cellPanel, BorderLayout.CENTER);

		this.setTitle("Game Of Life");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(50, 50, 500, 530);
		this.setResizable(false);
		this.add(mainPanel);
		this.addKeyListener(getKeyListener());
	}

	private KeyAdapter getKeyListener() {
		return new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == 'e' && !play)
					nextRound();
				if (e.getKeyChar() == 'p')
					playPause();
			}
		};
	}

	private void playPause() {
		play = !play;
		if (play) {
			timer = new Timer();
			task = new TimerTask() {
				public void run() {
					nextRound();
				}
			};
			long delay = 500; // 0.5 Sekunden
			timer.schedule(task, 0, delay);
		} else {
			timer.cancel();
			timer = null;
		}
	}

	public void nextRound() {
		cellPanel.updateCells();
		repaint();
	}
}
