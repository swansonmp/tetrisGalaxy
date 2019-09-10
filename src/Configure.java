import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Configure 
{
	//static fields, used with the main method.
	static JFrame frame;
	static String[] screenOpsArr = {"FullScreen", "Windowed"};
	static JComboBox<String> screenOpContainer;
	static JButton[] SavExit = new JButton[2];
	
	//object fields, used when the config is created.
	int screenOption;
	
	public static void main(String[] args) throws FileNotFoundException
	{
		frame = getFrame();	
	}
	
	public static JFrame getFrame() throws FileNotFoundException
	{
		JFrame m = new JFrame("Configure Galactic Tetris");
		JPanel mn = new JPanel();
		m.add(mn);
		Configure c = new Configure();
		m.setSize(300, 100);
		mn.setSize(m.getHeight(), m.getWidth());
		m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		screenOpContainer = new JComboBox<String>(screenOpsArr);
		screenOpContainer.setEditable(false);
		screenOpContainer.setSelectedIndex(c.screenOption);
		mn.add(screenOpContainer);
		
		SavExit[0] = new JButton("Save");
		SavExit[0].addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { 
			try {
			Write();
			} catch (FileNotFoundException e1) {
			e1.printStackTrace();
			} 
			}});
		
		SavExit[1] = new JButton("Exit");
		SavExit[1].addActionListener(new ActionListener() { public void actionPerformed(ActionEvent e) { System.exit(0);}});
		
		mn.add(SavExit[0]);
		mn.add(SavExit[1]);
		mn.setVisible(true);
		m.setVisible(true);
		return m;
	}
	
	public static void Write() throws FileNotFoundException
	{
		File cfg = new File("./config/cfg.txt");
		PrintWriter out = new PrintWriter(cfg);
		out.println("SETTINGS:");
		out.println(screenOpContainer.getSelectedIndex() + "_" + screenOpContainer.getSelectedItem());
		out.close();
	}
	
	public Configure() throws FileNotFoundException
	{
		Read();
	}
	
	public void Read() throws FileNotFoundException
	{
		Scanner in = new Scanner(new File("./config/cfg.txt"));
		in.nextLine();
		screenOption = in.nextLine().charAt(0) - 48;
	}
}
