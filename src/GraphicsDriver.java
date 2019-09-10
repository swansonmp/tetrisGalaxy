import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.*;

public class GraphicsDriver implements Runnable {
	// board state
	public int[][] board = new int[49][49];
	public int[] activeSet = new int[9];
	public int score = 0;
	public int next = 0;
	private int lines = 0;
	
	// objects
	Game g;
	public Window visual;
	Configure config;
	JFrame frame = new FrameWorker("Tetris Galaxy");
	
	// misc
	Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
	int windowed;
	int[] panelPos = { ((int) size.getHeight() / 2) - (700 / 2), (int) (size.getWidth() / 2 - (738 / 2)) };
	public boolean start = false;
	boolean[][][] numericals = new boolean[10][5][7];

	HashMap<String, BufferedImage> sprites = new HashMap<String, BufferedImage>();
	
	public GraphicsDriver() {
		super();
		try { config = new Configure(); } catch (Exception e) { }
		windowed = config.screenOption;
		if (windowed == 0)
		{
		frame.setSize(size);
		frame.setUndecorated(true);
		frame.repaint();
		}
		else
		{
			frame.setSize(700, 738);
		}
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initSprites();
	}

	public void initSprites() {
		File folder = new File("sprites");
		File[] folderCont = folder.listFiles();
		for (int i = 0; i < folderCont.length; i++) {
			try {
				sprites.put(folderCont[i].getName(), ImageIO.read(folderCont[i]));
			} catch (IOException e) {
				System.err.println("ImageIO Failure!");
			}
		}
		frame.setIconImage(sprites.get("Icon.png"));
	}

	public void passIn(int[][] in, int[] active, int[] score, Bag bag) {
		board = in;
		activeSet = active;
		this.score = score[0];
		lines = score[1];
		this.next = bag.peak();
		visual.repaint();
	}

	public void end() {
		frame.remove(visual);
		GameOver go = new GameOver();
		go.passInScore(score);
		frame.add(go);
		if(windowed == 0) go.setLocation(panelPos[1], panelPos[0]);
		frame.repaint();
		go.runIt();
		System.exit(0);
	}

	public void run() {

		Intro in = new Intro();
		frame.add(in);
		if(windowed == 0) in.setLocation(panelPos[1], panelPos[0]);
		frame.repaint();
		in.runIt();
		frame.remove(in);
		start = true;
		visual = new Window();
		frame.add(visual);
		if(windowed == 0)
		visual.setLocation(panelPos[1], panelPos[0]);
		frame.repaint();

	}

	public boolean startIt() {
		return start;
	}

	@SuppressWarnings("serial")
	public class Window extends JPanel {

		public Window() {
			super();
			readInNumerical();
			this.setSize(700, 700);
			this.setVisible(true);

		}

		public void paint(Graphics g) {
			g.setColor(Color.BLACK);
			g.clearRect(0, 0, 700, 800);
			g.drawImage(sprites.get("Background.png"), 0, 0, this);
			g.drawImage(sprites.get("Circle.png"), 100, 100, this);
			g.drawImage(sprites.get("Ayy.png"), 300, 50, this);
			g.drawImage(sprites.get("Score.png"), 30, 650, this);
			g.drawImage(getFullTetro(next), 25, 75, this);
			for (int i = 0; i < 49; i++)
				for (int j = 0; j < 49; j++) {
					if (board[i][j] != 0) {
						g.drawImage(getTetro(board[i][j]), 100 + i * 10, 100 + j * 10, this);
					}
				}
			
			//draws ghost only if valid location
			if (activeSet[9] != -1) {
				//System.out.println(activeSet[9]);
				int augx = 0;
				int augy = 0;
				switch (activeSet[10])
				{
				case 0:
					augy = activeSet[9] - 1;
					break;
				case 1:
					augx = -activeSet[9] + 1;
					break;
				case 2:
					augy = -activeSet[9] + 1;
					break;
				case 3:
					augx = activeSet[9] - 1;
					break;
				default:
				}
				for (int i = 0; i < 8; i += 2)
				{
					g.drawImage(getTetro(activeSet[8] + 8), 100 + (activeSet[i] + augx) * 10, 100 + (activeSet[i + 1] + augy) * 10, this);
				}
			}
			for (int i = 0; i < 8; i += 2)
				g.drawImage(getTetro(activeSet[8]), 100 + activeSet[i] * 10, 100 + activeSet[i + 1] * 10, this);
			String scrStrng = Integer.toString(score);
			for (int i = 0; i < scrStrng.length(); i++)
				drawNumerical(scrStrng.charAt(i) - 48, i, g, 170, 650);
			//Number of lines cleared, to be drawn.
			String linesStrng = Integer.toString(lines);
			for (int i = 0; i < linesStrng.length(); i++)
				drawNumerical(linesStrng.charAt(i) - 48, i, g, 550, 20);
			//Level achieved, to be drawn.
			String lvlStrng = Integer.toString((lines / 10));
			for (int i = 0; i < lvlStrng.length(); i++)
				drawNumerical(lvlStrng.charAt(i) - 48, i, g, 550, 70);

		}

		public Image getTetro(int color) {
			color %= 17;
			switch (color) {
			case 1:
				return sprites.get("Tetrimino0.png"); //center piece
			case 2:
				return sprites.get("Tetrimino3.png"); //line piece
			case 3:
				return sprites.get("Tetrimino4.png"); //lblock
			case 4:
				return sprites.get("Tetrimino5.png"); //jblock
			case 5:
				return sprites.get("Tetrimino7.png"); //square
			case 6:
				return sprites.get("Tetrimino2.png"); //z block
			case 7:
				return sprites.get("Tetrimino6.png"); //t block
			case 8:
				return sprites.get("Tetrimino1.png"); // s blockzz
			case 9:
				return sprites.get("Ghost0.png"); //center?
			case 10:
				return sprites.get("Ghost3.png");  //line ghost
			case 11:
				return sprites.get("Ghost4.png"); // line
			case 12:
				return sprites.get("Ghost5.png");
			case 13:
				return sprites.get("Ghost7.png");
			case 14:
				return sprites.get("Ghost2.png");
			case 15:
				return sprites.get("Ghost6.png");
			case 16:
				return sprites.get("Ghost1.png");
			default:
				return null;
			}
		}
			
			public Image getFullTetro(int color) {
				color %= 7;
				switch (color) {
				case 0:
					return sprites.get("FullTetro0.png");
				case 2:
					return sprites.get("FullTetro2.png");
				case 3:
					return sprites.get("FullTetro3.png");
				case 4:
					return sprites.get("FullTetro4.png");
				case 5:
					return sprites.get("FullTetro5.png");
				case 6:
					return sprites.get("FullTetro6.png");
				case 1:
					return sprites.get("FullTetro1.png");
				default:
					return null;
				}
		}

		public void readInNumerical() {
			File numbers = new File("sprites/Numericals.txt");
			try {
				Scanner in = new Scanner(numbers);

				for (int x = 0; x < 10; x++) {
					for (int y = 0; y < 5; y++) {
						String cur = in.nextLine();
						for (int z = 0; z < 7; z++) {
							if (cur.charAt(z) == '1')
								numericals[x][y][z] = true;
						}
					}
					if (in.hasNextLine())
						in.nextLine();
				}
			} catch (Exception e) {
			}
			;
		}

		public void drawNumerical(int to, int pos, Graphics g, int x, int y) {
			Random rand = new Random();
			for (int i = 0; i < 5; i++)
				for (int j = 0; j < 7; j++) {
					if (numericals[to][i][j]) {
						Color c = getColor(rand.nextInt(11));
						g.setColor(c);
						g.fillRect(x + (pos * 40) + (j * 5), y + (i * 5), 5, 5);
					}
				}
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
}
