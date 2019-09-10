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
public class GameOver extends JPanel {
	boolean[][] draw = new boolean[11][34];
	File sprite = new File("sprites/GameOver.txt");
	int shift = 0;
	int score = 0;
	Image img;
	boolean halt = true;
	Scanner in;

	public GameOver() {
		setSize(700, 700);
		setVisible(true);
		try {
			img = ImageIO.read(new File("sprites/GameOver.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void passInScore(int score)
	{
		this.score = score;
	}

	public void runIt() {
		try {
			in = new Scanner(sprite);
		} catch (FileNotFoundException e) { System.out.println("Ah!");
		}
		loadInDraw();
		long start = System.currentTimeMillis() + 6 * 1000;
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
			for (int j = 0; j < 34; j++) {
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
					g.fillRect(400 + i * 10, 100 + j * 10, 10, 10);
					g.setColor(Color.WHITE);
					g.drawImage(img, 0, 0, this);
					g.drawString("Your Score is " + score, 300, 150);

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
