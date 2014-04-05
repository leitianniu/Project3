import java.util.*;
import java.io.*;
import java.lang.Math.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class gridLockLauncher extends GridLock
{
	JFrame launcherFrame = new JFrame("Grid Lock: Select a board");
		
	
	JFrame aboutFrame = new JFrame("About: Grid Lock");
	
	JButton[][] grid = new JButton[1][10];
	
	JButton exitLauncher = new JButton("Exit");
	JButton launcherAboutButton = new JButton("About");
	JButton fillButton = new JButton("                           ");
		
	JMenuBar topBar = new JMenuBar();
	
	String mapPath = "";
		
	public static void main(String[] args)
	{
		gridLockLauncher gll = new gridLockLauncher();
		gll.generateAboutFrame();
		gll.displayGUI();
	}
	
		public void generateAboutFrame()
		{
			ArrayList<JLabel> aboutLabels = new ArrayList<JLabel>();
			
			//this is our about window.  It is small enough so to save time (really it did save time to do
			//it this way) I just made a new lable for the window for each line
			aboutFrame.setLayout(new GridLayout(5, 1));
			JLabel aboutLabel = new JLabel("Move your player (red) car out of the board.");
			JLabel aboutLabel2 = new JLabel("Do this by moving the other cards out of the way.");
			JLabel aboutLabel3 = new JLabel("If you get stuck, press the 'hint' button to ");
			JLabel aboutLabel4 = new JLabel("reveal the next move you should make.");
			JLabel aboutLabel5 = new JLabel("Good luck, and don't get Grid Locked!");
			
			aboutLabels.add(aboutLabel);
			aboutLabels.add(aboutLabel2);
			aboutLabels.add(aboutLabel3);
			aboutLabels.add(aboutLabel4);
			aboutLabels.add(aboutLabel5);
			
			for(JLabel jl : aboutLabels)
				aboutFrame.add(jl);
			
			aboutFrame.pack();
		}
		
	public void displayGUI()
	{
		launcherFrame.setLayout(new GridLayout(10,1));
		launcherFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		launcherFrame.setJMenuBar(topBar);
		
		launcherAboutButton.addMouseListener (new MouseClickHandler()); //add the listener
		exitLauncher.addMouseListener (new MouseClickHandler()); //add the listener
		
		topBar.add(exitLauncher);
		topBar.add(launcherAboutButton);
		topBar.add(fillButton);
			fillButton.setEnabled(false);
			
			for(int y = 0; y < 1; y++)
			{
				for(int x = 0; x < 10; x++)
				{
					grid[y][x] = new JButton();
					grid[y][x].addMouseListener (new MouseClickHandler()); //add the listener
					launcherFrame.getContentPane().add(grid[y][x]); //adds button to grid
				}
			}
		
			grid[0][0].setText("[EASY] Board 1");
			grid[0][1].setText("[EASY] Board 2");
			grid[0][2].setText("[EASY] Board 3");
			grid[0][3].setText("[MEDIUM] Board 4");
			grid[0][4].setText("[MEDIUM] Board 5");
			grid[0][5].setText("[MEDIUM] Board 6");
			grid[0][6].setText("[MEDIUM] Board 7");
			grid[0][7].setText("[HARD] Board 8");
			grid[0][8].setText("[HARD] Board 9");
			grid[0][9].setText("[EXPERT] Board 10");
			
		launcherFrame.pack();
		launcherFrame.setLocationRelativeTo(null); 
		launcherFrame.setVisible(true);
	}
	
	private class MouseClickHandler extends MouseAdapter
	{
		public void mouseClicked (MouseEvent e)
	   {
		String s = "";

		if (SwingUtilities.isLeftMouseButton(e))
		s = "Left Mouse Click";
		else if (SwingUtilities.isRightMouseButton(e))
		s = "Right Mouse Click";
		else if (SwingUtilities.isMiddleMouseButton(e))
		s = "Middle Mouse Click";   
	
		/*s = s + "\nSHIFT_MASK: " + e.isShiftDown();
		s = s + "\nCTRL_MASK: " + e.isControlDown();
		s = s + "\nMETA_MASK: " + e.isMetaDown();
		s = s + "\nALT_MASK: " + e.isAltDown();*/
	
		
		
			for(int y = 0; y < 1; y++)
			{
				for(int x = 0; x < 10; x++)
				{
					if(e.getSource() == grid[y][x] && s.equals("Left Mouse Click"))
					{
							if(grid[y][x].getText().equals("[EASY] Board 1"))
								mapPath = "Maps/map01.txt";
							else if(grid[y][x].getText().equals("[EASY] Board 2"))
								mapPath = "Maps/map02.txt";
							else if(grid[y][x].getText().equals("[EASY] Board 3"))
								mapPath = "Maps/map06.txt";
							else if(grid[y][x].getText().equals("[MEDIUM] Board 4"))
								mapPath = "Maps/map03.txt";		
							else if(grid[y][x].getText().equals("[MEDIUM] Board 5"))
								mapPath = "Maps/map04.txt";	
							else if(grid[y][x].getText().equals("[MEDIUM] Board 6"))
								mapPath = "Maps/map05.txt";	
							else if(grid[y][x].getText().equals("[MEDIUM] Board 7"))
								mapPath = "Maps/map07.txt";
							else if(grid[y][x].getText().equals("[HARD] Board 8"))
								mapPath = "Maps/map08.txt";
							else if(grid[y][x].getText().equals("[HARD] Board 9"))
								mapPath = "Maps/map09.txt";
							else if(grid[y][x].getText().equals("[EXPERT] Board 10"))
								mapPath = "Maps/map10.txt";
							else mapPath = "";
							
							try
							{
								System.out.println(mapPath);
							/*Pass a system command to restart program using the already supplied arguments from the initial startup*/							
							java.lang.Runtime.getRuntime().exec(new String[] { "java", "GridLock", mapPath});
							
							launchedFromLauncher = 1;
							
							System.exit(0);
							}
							catch(Exception f) //catch an exception, shut down everything
							{
								System.out.println("Something went wrong with restarting");
								System.exit(0);
							}
						
					}
				}
			}
			
			if(e.getSource() == launcherAboutButton && s.equals("Left Mouse Click"))
			{
				aboutFrame.setLocationRelativeTo(null); 
				aboutFrame.setVisible(true);
									
			}
			
			if(e.getSource() == exitLauncher && s.equals("Left Mouse Click")) System.exit(0);
			
		}
	}
}