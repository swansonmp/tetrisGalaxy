import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Intro extends JPanel {
	boolean[][] draw = new boolean[11][43];
	File sprite = new File("sprites/TeemSteem.txt");
	File welcome = new File("sprites/Welcome.txt");
	int shift = 0;
	Image[] images = new Image[2];
	boolean halt = true;
	Scanner in;

	public Intro() {
		setSize(700, 700);
		setVisible(true);
		try {
			images[0] = ImageIO.read(new File("sprites/Credits.png"));
			images[1] = ImageIO.read(new File("sprites/HowTo.png"));
		} catch (IOException e) {
		}
	}

	public void runIt() {
		try {
			in = new Scanner(sprite);
		} catch (FileNotFoundException e) {
		}
		loadInDraw();
		long start = System.currentTimeMillis() + 6 * 1000;
		while (System.currentTimeMillis() < start) {
			repaint();
			waitin(250);
		}
		try {
			in = new Scanner(welcome);
		} catch (FileNotFoundException e) {
		}
		loadInWelcome();
		start = System.currentTimeMillis() + 6000;
		while (System.currentTimeMillis() < start) {
			repaint();
			waitin(250);
		}
	}

	public void waitin(long ms) {
		long wt = System.currentTimeMillis() + ms;
		while (System.currentTimeMillis() < wt) {
		}
	}

	public void loadInDraw() {
		for (int i = 0; i < 11; i++) {
			String next = in.nextLine();
			for (int j = 0; j < 43; j++) {
				if (next.charAt(j) == '1')
					draw[i][j] = true;
			}
		}
	}

	public void loadInWelcome() {
		draw = new boolean[11][52];
		for (int i = 0; i < 11; i++) {
			String next = in.nextLine();
			for (int j = 0; j < 52; j++) {
				if (next.charAt(j) == '1')
					draw[i][j] = true;
			}
		}
	}

	public void paint(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 700, 700);
		for (int i = 0; i < draw[0].length; i++)
			for (int j = 0; j < draw.length; j++) {
				if (draw[j][i]) {
					g.setColor(getColor((j + i + shift) % 11));
					g.fillRect(100 + i * 10, 100 + j * 10, 10, 10);
					g.setColor(Color.WHITE);
					if (draw[0].length == 43)
						g.drawImage(images[0], 0, 300, this);
				else
					g.drawImage(images[1], 0, 300, this);

			}
			}
		shift++;
	}

	public Color getColor(int in) {
		switch (in) {
		case 1:
			return Color.BLUE;
		case 2:
			return Color.CYAN;
		case 3:
			return Color.GREEN;
		case 4:
			return Color.MAGENTA;
		case 5:
			return Color.ORANGE;
		case 6:
			return Color.PINK;
		case 7:
			return Color.RED;
		case 8:
			return Color.YELLOW;
		case 9:
			return Color.magenta;
		case 10:
			return Color.orange;
		case 0:
			return Color.pink;
		default:
			return null;
		}
	}
}
