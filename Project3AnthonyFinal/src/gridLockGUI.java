import java.util.*;
import java.io.*;
import java.lang.Math.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class gridLockGUI extends GridLock
{
JFrame solvingFrame = new JFrame("The monkeys are solving...");
	int turnCount = 0;
	JFrame frame;
	ArrayList<JButton> buttons = new ArrayList<JButton>();
	int firstClick = 0; //for use by the timer, set to 1 when the player makes a first move so we know to start counting
	int sizeOfBoard;
	
		//here is where we be counting up
	int timeAccumulated = 0;
	int squaresMoved = 0;
	JLabel movesLabel = new JLabel("Squares");
	JButton moveCounter = new JButton();
	
	
	//menu bar for the game frame
	JMenuBar bar = new JMenuBar();
		
	//menus to go into the menu bar
	JMenu gameMenu = new JMenu("Game");
	JMenu aboutMenu = new JMenu("About");

	//we put buttons into the menu bar because I am lazy and already know buttons
	JMenuItem restartButton = new JMenuItem("Restart");
	
	JButton solveButton = new JButton("MONKEY SOLVE");
		
	//exit button, about, restart buttons
	JMenuItem exitButton = new JMenuItem("Exit       "); //set good size
		
	JMenuItem closeIt = new JMenuItem("CloseIt");
		
	JMenuItem aboutButton = new JMenuItem("About");
	JButton nextBoardButton = new JButton("Hint");
	JButton bufferZone = new JButton(" ");
	
	//windows that may open during running of the game
	JFrame aboutFrame = new JFrame("About: Grid Lock");
	
	
	JFrame winFrame = new JFrame("You Win!");
	JButton continueButton = new JButton("\n   CONTINUE   \n");
	
	
	
	String nextBoard = "";
			JOptionPane aboutDialog = new JOptionPane();
			String aboutText = "Move your player (red) car out of the board."
													+ "\nDo this by moving the other cards out of the way."
													+ "\nIf you get stuck, press the 'solve' button to "
													+ "\nsolve the current board configuration."
													+ "\nGood luck, and don't get Grid Locked!";
		
		JButton firstButton = new JButton();
		public void generateAboutFrame()
		{
		
			  
			
				
				
				aboutFrame.setLocationRelativeTo(null); 
				
			
			
			firstButton.setText(aboutText);
			firstButton.addActionListener(new MouseClickHandler());
			//this is our about window.  It is small enough so to save time (really it did save time to do
			//it this way) I just made a new lable for the window for each line
			
			/*
			JLabel aboutLabel = new JLabel("Move your player (red) car out of the board.");
			JLabel aboutLabel2 = new JLabel("Do this by moving the other cards out of the way.");
			JLabel aboutLabel3 = new JLabel("If you get stuck, press the 'solve' button to ");
			JLabel aboutLabel4 = new JLabel("solve the current board configuration.");
			JLabel aboutLabel5 = new JLabel("Good luck, and don't get Grid Locked!");
			
			aboutLabels.add(aboutLabel);
			aboutLabels.add(aboutLabel2);
			aboutLabels.add(aboutLabel3);
			aboutLabels.add(aboutLabel4);
			aboutLabels.add(aboutLabel5);*/
			
			
			aboutFrame.getContentPane().add(firstButton);
			
			aboutFrame.setSize(1600,480);
			//aboutFrame.setVisible(true);
		}
		
		public void showAboutFrame()
		{
				aboutFrame.setVisible(true);
		}
		
		public void exitGame()
		{
			System.exit(0);
		}
	
	public ActionListener taskPerformer = new ActionListener() { //handler for the cuckoo clock
		public void actionPerformed(ActionEvent e) {	//action performed
			if(timeAccumulated++ > 0) {
				//timerLabel.setText(String.valueOf(timeAccumulated));
				realTimer.setText(String.valueOf(timeAccumulated));	//time accumulates and the timer counts UP
			}
		}
	};
	javax.swing.Timer timer = new javax.swing.Timer(1000, taskPerformer);
		//label to go onto the "timer"
	//JLabel timerLabel = new JLabel();
	JLabel timerLabel = new JLabel("Time");
	//yes, our timer is a button as well
	JButton realTimer = new JButton(Integer.toString(0));
	
	
	public void updateMoveCounter(int moves)
	{
		moveCounter.setText(Integer.toString(squaresMoved));
	}
	
	public void createGUI(board b) //method will display our GUI
	{
							winFrame.setLayout(new GridLayout(3, 1));
							winFrame.getContentPane().add(continueButton);
			//determine the next board to load if we win
							if(b.boardNumber.equals("map01.txt"))
								nextBoard = "map02.txt";
							else if(b.boardNumber.equals("map02.txt"))
								nextBoard = "map06.txt";
							else if(b.boardNumber.equals("map06.txt"))
								nextBoard = "map03.txt";
							else if(b.boardNumber.equals("map03.txt"))
								nextBoard = "map04.txt";		
							else if(b.boardNumber.equals("map04.txt"))
								nextBoard = "map05.txt";	
							else if(b.boardNumber.equals("map05.txt"))
								nextBoard = "map07.txt";
							else if(b.boardNumber.equals("map07.txt"))
								nextBoard = "map08.txt";
							else if(b.boardNumber.equals("map08.txt"))
								nextBoard = "map09.txt";
							else if(b.boardNumber.equals("map09.txt"))
								nextBoard = "map10.txt";
							else nextBoard = "";
	
	
		sizeOfBoard = b.boardLines.size();
	
		frame = new JFrame("Java Grid Lock [ board " + b.boardNumber + " ]"); //frame for game
		//set the layout of our grid of buttons to the size of the board specified by the cmd arguments
		frame.setLayout(new GridLayout(grid.length, grid.length));

		//set the menu bar up
		frame.setJMenuBar(bar);
		bar.add(gameMenu);
		bar.add(aboutMenu);
		bar.add(bufferZone);
		bar.add(timerLabel);
		bar.add(realTimer);
		realTimer.setContentAreaFilled(false);
		
		bar.add(movesLabel);
		bar.add(moveCounter);
		moveCounter.setContentAreaFilled(false);
		updateMoveCounter(squaresMoved);
		
		bar.add(nextBoardButton);
		bar.add(solveButton);
		
		bufferZone.setEnabled(false);
		
		//add the buttons to the grid
		for(int x = 0; x < grid.length; x++)
		{
			for(int y = 0; y < grid.length; y++)
			{
				grid[x][y]=new JButton();  
				
				buttons.add(grid[x][y]);
				grid[x][y].setContentAreaFilled( false );
				if(!(x == 2 && y == 5)) grid[x][y].setBorderPainted(false);
				frame.add(grid[x][y]); //adds button to grid
				
			}
		}
		
		ArrayList<JMenuItem> menuItems = new ArrayList<JMenuItem>();
			
		
		menuItems.add(restartButton);
		menuItems.add(exitButton);
		
		menuItems.add(aboutButton);
		buttons.add(solveButton);
		buttons.add(continueButton);
		buttons.add(nextBoardButton);
		
		aboutMenu.add(aboutButton);
		
		//aboutMenu.add(closeIt);
			//closeIt.addActionListener (new MouseClickHandler());
			
		gameMenu.add(restartButton);
		gameMenu.add(exitButton);
		
		for(JMenuItem jm : menuItems)
			jm.addActionListener(new MouseClickHandler());
		
		/*
			Handle mouse clicks
		*/
		for(JButton jb : buttons)
			jb.addMouseListener (new MouseClickHandler()); //add the listener
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 	frame.setSize(480,500);

		generateAboutFrame();
		
		//set the frame ot visible, and let's start playing!
		//frame.pack();
		frame.setLocationRelativeTo(null); 
		frame.setVisible(true);
		paintCars(b);
		currentBoard = b;
	}
	
	public void showAboutDialog(){
		aboutDialog.showMessageDialog(frame, aboutText);
	}
	
	public void paintCars(board b)
	{
		for(int x = 0; x < boardSize; x++)
		{
			for(int y = 0; y < boardSize; y++)
			{
					if(b.boardLines.get(x).rowLine.charAt(2*y) != 'x'
					&& b.boardLines.get(x).rowLine.charAt(2*y) == 'P')
					{
						grid[x][y].setBackground(Color.RED);
						grid[x][y].setOpaque(true);
					}
					else if(b.boardLines.get(x).rowLine.charAt(2*y) != 'x'
					&& b.boardLines.get(x).rowLine.charAt(2*y) == 'A')
					{
						grid[x][y].setBackground(Color.GREEN);
						grid[x][y].setOpaque(true);
					}
					else if(b.boardLines.get(x).rowLine.charAt(2*y) != 'x'
					&& b.boardLines.get(x).rowLine.charAt(2*y) == 'B')
					{
						grid[x][y].setBackground(Color.BLUE);
						grid[x][y].setOpaque(true);
					}
					else if(b.boardLines.get(x).rowLine.charAt(2*y) != 'x'
					&& b.boardLines.get(x).rowLine.charAt(2*y) == 'C')
					{
						grid[x][y].setBackground(Color.YELLOW);
						grid[x][y].setOpaque(true);
					}
					else if(b.boardLines.get(x).rowLine.charAt(2*y) != 'x'
					&& b.boardLines.get(x).rowLine.charAt(2*y) == 'D')
					{
						grid[x][y].setBackground(Color.ORANGE);
						grid[x][y].setOpaque(true);
					}
					else if(b.boardLines.get(x).rowLine.charAt(2*y) != 'x' 
					&& b.boardLines.get(x).rowLine.charAt(2*y) == 'E')
					{
						grid[x][y].setBackground(Color.PINK);
						grid[x][y].setOpaque(true);
					}
					else if(b.boardLines.get(x).rowLine.charAt(2*y) != 'x' /////////////
					&& b.boardLines.get(x).rowLine.charAt(2*y) == 'F')
					{
						grid[x][y].setBackground(Color.CYAN);
						grid[x][y].setOpaque(true);
					}
					else if(b.boardLines.get(x).rowLine.charAt(2*y) != 'x'
					&& b.boardLines.get(x).rowLine.charAt(2*y) == 'G')
					{
						grid[x][y].setBackground(Color.DARK_GRAY);
						grid[x][y].setOpaque(true);
					}
					else if(b.boardLines.get(x).rowLine.charAt(2*y) != 'x'
					&& b.boardLines.get(x).rowLine.charAt(2*y) == 'H')
					{
						grid[x][y].setBackground(new Color(0,128,128));
						grid[x][y].setOpaque(true);
					}
					else if(b.boardLines.get(x).rowLine.charAt(2*y) != 'x'
					&& b.boardLines.get(x).rowLine.charAt(2*y) == 'I')
					{
						grid[x][y].setBackground(new Color(138,43,226));
						grid[x][y].setOpaque(true);
					}
					else if(b.boardLines.get(x).rowLine.charAt(2*y) != 'x'
					&& b.boardLines.get(x).rowLine.charAt(2*y) == 'J')
					{
						grid[x][y].setBackground(Color.BLACK);
						grid[x][y].setOpaque(true);
					}
					else if(b.boardLines.get(x).rowLine.charAt(2*y) != 'x'
					&& b.boardLines.get(x).rowLine.charAt(2*y) == 'K')
					{
						grid[x][y].setBackground(Color.GRAY);
						grid[x][y].setOpaque(true);
					}
					else if(b.boardLines.get(x).rowLine.charAt(2*y) != 'x'
					&& b.boardLines.get(x).rowLine.charAt(2*y) == 'L')
					{
						grid[x][y].setBackground(Color.LIGHT_GRAY);
						grid[x][y].setOpaque(true);
					}		
					else if(b.boardLines.get(x).rowLine.charAt(2*y) != 'x'
					&& b.boardLines.get(x).rowLine.charAt(2*y) == 'M')
					{
						grid[x][y].setBackground(new Color(165,42,42));
						grid[x][y].setOpaque(true);
					}						
					
					////////////////////////////////////////////////////////////
			}
		}
		
	}
	car cee = null;
	car formerCee = null;
	
	private class MouseClickHandler extends MouseAdapter implements ActionListener
	{
		public void actionPerformed (ActionEvent e)
		{
						
			if(e.getSource() == aboutButton)
			{
				showAboutDialog();
			}
			
			if(e.getSource() == exitButton)
			{
					try
					{
					/*Pass a system command to restart program using the already supplied arguments from the initial startup*/							
					java.lang.Runtime.getRuntime().exec(new String[] { "java", "gridLockLauncher"});
					
					launchedFromLauncher = 1;
					
					System.exit(0);
					}
					catch(Exception f) //catch an exception, shut down everything
					{
						System.out.println("Something went wrong with restarting");
						System.exit(0);
					}
				exitGame();
			}
			
			if(e.getSource() == restartButton )
			{
							aboutFrame.setVisible(false);
							aboutFrame.dispose();
							frame.setVisible(false);
							frame.dispose();
					try
					{
					/*Pass a system command to restart program using the already supplied arguments from the initial startup*/							
					java.lang.Runtime.getRuntime().exec(new String[] { "java", "GridLock", "Maps/"+currentBoard.boardNumber});
					
					//launchedFromLauncher = 1;
					
					//System.exit(0);
					}
					catch(Exception f) //catch an exception, shut down everything
					{
						System.out.println("Something went wrong with restarting");
						System.exit(0);
					}
			}
			
			if(e.getSource() == firstButton)
			{
				aboutFrame.setVisible(false);
			}
			
			
		}
		
		public void doHint()
		{
			for(int carSelect = 0; carSelect < currentBoard.cars.size();carSelect++){
								//try every move, for every car, form every board in the
								//list using the queue.  if the config we get is
								//a victory, use that queue state to solve.
							
							car cee = currentBoard.cars.get(carSelect);
						
						
						Random generatori = new Random(); 
						//int i = 0;
						//int i;
						Random generatorj = new Random(); 
						//int j = 0;

								//printf("Moving " + cee.carTitle + " to " + i + " " + j +"\n");
								
								if(cee.orientation.equals("Horizontal"))
								{
								int i = cee.startX-1;
								int j=generatorj.nextInt(6);
									if(!(j >= cee.startY-1 && j<=cee.endY-1))
									{	
									//printf("H\n");
									if(i == (cee.startX-1))
									{
										int canMakeMove = 1;
										//check if we have a car in the way
										if(j > cee.endY-1) //to the right
										{
											for(int jay = cee.endY; jay <= (j+1);jay++)
											{
													if(currentBoard.getCarByCoordinates(i+1,jay) != null
													   && currentBoard.getCarByCoordinates(i+1,jay) != cee)
													{
														//printf("found a car\n");
													   canMakeMove = 0;
													   
													    cee.selectedState = 0;	
														selectedCars = 0;
													   
														if(cee.length == 2)
														{
															grid[cee.startX-1][cee.startY-1].setIcon(null);
															grid[cee.endX-1][cee.endY-1].setIcon(null);

														}
														else
														{
															grid[cee.startX-1][cee.startY-1].setIcon(null);
															grid[cee.middleX-1][cee.middleY-1].setIcon(null);
															grid[cee.endX-1][cee.endY-1].setIcon(null);
														}
													   
													}   
											}
										}
										else if(j < cee.startY-1)
										{
											for(int jay = (j+1); jay <= (cee.endY);jay++)
											{
													if(currentBoard.getCarByCoordinates(i+1,jay) != null
													   && currentBoard.getCarByCoordinates(i+1,jay) != cee)
													{
													   canMakeMove = 0;
													   
														cee.selectedState = 0;	
														selectedCars = 0;
													   
														if(cee.length == 2)
														{
															grid[cee.startX-1][cee.startY-1].setIcon(null);
															grid[cee.endX-1][cee.endY-1].setIcon(null);

														}
														else
														{
															grid[cee.startX-1][cee.startY-1].setIcon(null);
															grid[cee.middleX-1][cee.middleY-1].setIcon(null);
															grid[cee.endX-1][cee.endY-1].setIcon(null);
														}
													   
													}   
											}
										}
										else{cee.selectedState = 0; continue;}
										
										if(canMakeMove == 1)
										{
											//printf("Can move in this row/column\n");
											
											String newLine ="";
											
											////printf("   "+newLine+"\n");
											
											if(cee.length == 2)
											{
												grid[cee.startX-1][cee.startY-1].setIcon(null);
												grid[cee.endX-1][cee.endY-1].setIcon(null);
												
													grid[cee.startX-1][cee.startY-1].setBackground(null);
													grid[cee.startX-1][cee.startY-1].setContentAreaFilled( false );
													grid[cee.startX-1][cee.startY-1].setOpaque(false);
													grid[cee.endX-1][cee.endY-1].setBackground(null);
													grid[cee.endX-1][cee.endY-1].setContentAreaFilled( false );
													grid[cee.endX-1][cee.endY-1].setOpaque(false);
											}
											else
											{
												grid[cee.startX-1][cee.startY-1].setIcon(null);
												grid[cee.middleX-1][cee.middleY-1].setIcon(null);
												grid[cee.endX-1][cee.endY-1].setIcon(null);
												
													grid[cee.startX-1][cee.startY-1].setBackground(null);
													grid[cee.startX-1][cee.startY-1].setContentAreaFilled( false );
													grid[cee.startX-1][cee.startY-1].setOpaque(false);
													grid[cee.middleX-1][cee.middleY-1].setBackground(null);
													grid[cee.middleX-1][cee.middleY-1].setContentAreaFilled( false );
													grid[cee.middleX-1][cee.middleY-1].setOpaque(false);
													grid[cee.endX-1][cee.endY-1].setBackground(null);
													grid[cee.endX-1][cee.endY-1].setContentAreaFilled( false );
													grid[cee.endX-1][cee.endY-1].setOpaque(false);
											}
											

											
											if(j > cee.endY-1)
											{
												//printf("moved to the right\n");
												squaresMoved += (j-(cee.endY-1));
												
												newLine += currentBoard.boardLines.get(i).rowLine.substring(0,2*(cee.startY-1));
												//printf("new start: " + newLine + "\n");
												
												for(int d = 0; (d)<(j-(cee.endY-1));d++)
													newLine += "x ";
												
												for(int d = 0; d < cee.length; d++)
												{
													newLine += (cee.carTitle + " ");
												}
												
												if(cee.length == 3)
												{
													cee.endY = j+1;
													cee.middleY = j;
													cee.startY = j-1;
												}
												else
												{
													cee.endY = j+1;
													cee.startY = j;
												}
													newLine += currentBoard.boardLines.get(i).rowLine.substring((2*j+2), currentBoard.boardLines.get(i).rowLine.length());
													newLine += " ";
													//printf("new: " + newLine +"\n");
													currentBoard.boardLines.get(i).rowLine = newLine;
													updateMoveCounter(squaresMoved);
											}
											else if(j < cee.startY-1)
											{
												//printf("moved to the left\n");
												squaresMoved += ((cee.startY-1)-j);
												newLine += currentBoard.boardLines.get(i).rowLine.substring(0,2*j);
												//printf("new start: " + newLine + "\n");
												
												for(int d = 0; d < cee.length; d++)
												{
													newLine += (cee.carTitle + " ");
												}
												
												for(int d = 0; (d)<((cee.startY-1)-j);d++)
													newLine += "x ";
												
												if(cee.length == 3)
												{
													cee.endY = j+3;
													cee.middleY = j+2;
													cee.startY = j+1;
												}
												else
												{
													cee.endY = j+2;
													cee.startY = j+1;
												}
													newLine += currentBoard.boardLines.get(i).rowLine.substring(newLine.length(), currentBoard.boardLines.get(i).rowLine.length());
													newLine += " ";
													//printf("new: " + newLine +"\n");
													currentBoard.boardLines.get(i).rowLine = newLine;
													//printf("was changed: " + currentBoard.boardLines.get(i).rowLine+"\n");
													updateMoveCounter(squaresMoved);
											}
											
											cee.selectedState = 0;	
											selectedCars = 0;
											
											currentBoard.boardLines.get(i).rowLine = newLine;
											//currentBoard.printBoard();
											paintCars(currentBoard);
											break;
										}
									}
									}
									else continue;
								}
								else if(cee.orientation.equals("Vertical"))
								{
									int j = cee.startY-1;
									int i = generatori.nextInt(6);
									if(!(i >= cee.startX-1 && i <= cee.endX-1))
									{
									if(j == (cee.startY-1))
									{
										//printf("Can move in this row/column\n");
										
											if(cee.length == 2)
											{
												grid[cee.startX-1][cee.startY-1].setIcon(null);
												grid[cee.endX-1][cee.endY-1].setIcon(null);
												
													grid[cee.startX-1][cee.startY-1].setBackground(null);
													grid[cee.startX-1][cee.startY-1].setContentAreaFilled( false );
													grid[cee.startX-1][cee.startY-1].setOpaque(false);
													grid[cee.endX-1][cee.endY-1].setBackground(null);
													grid[cee.endX-1][cee.endY-1].setContentAreaFilled( false );
													grid[cee.endX-1][cee.endY-1].setOpaque(false);
											}
											else
											{
												grid[cee.startX-1][cee.startY-1].setIcon(null);
												grid[cee.middleX-1][cee.middleY-1].setIcon(null);
												grid[cee.endX-1][cee.endY-1].setIcon(null);
												
													grid[cee.startX-1][cee.startY-1].setBackground(null);
													grid[cee.startX-1][cee.startY-1].setContentAreaFilled( false );
													grid[cee.startX-1][cee.startY-1].setOpaque(false);
													grid[cee.middleX-1][cee.middleY-1].setBackground(null);
													grid[cee.middleX-1][cee.middleY-1].setContentAreaFilled( false );
													grid[cee.middleX-1][cee.middleY-1].setOpaque(false);
													grid[cee.endX-1][cee.endY-1].setBackground(null);
													grid[cee.endX-1][cee.endY-1].setContentAreaFilled( false );
													grid[cee.endX-1][cee.endY-1].setOpaque(false);
											}
										
										if(i < (cee.startX-1))
										{
											//printf("Moving up\n");
											
											int canMove = 1;
											for(boardLine bl : currentBoard.boardLines)
											{
												if(bl.rowNumber >= (i+1) && bl.rowNumber <= (cee.endX))
												{
													if(currentBoard.getCarByCoordinates(bl.rowNumber,j+1) != null
													   && currentBoard.getCarByCoordinates(bl.rowNumber,j+1) != cee)
													   canMove = 0;
												}
											}
											
											if(canMove == 1)
											{
												squaresMoved += ((cee.startX-1)-i);
												for(boardLine bl : currentBoard.boardLines)
												{
													if(bl.rowNumber >= (i+1) && bl.rowNumber <= (cee.endX))
													{
														String newLine = "";
														////printf("editing : " + bl.rowLine + "\n");
														newLine+= bl.rowLine.substring(0,2*j);
														if((bl.rowNumber) >= (i+1) && (bl.rowNumber)<=((i+1)+(cee.length-1)))
															newLine+=(cee.carTitle+" ");
														else
															newLine +="x ";
															
														//printf(newLine+"\n");
														newLine+=bl.rowLine.substring(newLine.length(),bl.rowLine.length());
														//printf(newLine+"\n");
														bl.rowLine = newLine;
													}
												}
												if(cee.length == 3)
												{
													cee.endX = i+3;
													cee.middleX = i+2;
													cee.startX = i+1;
												}
												else
												{
													cee.endX = i+2;
													cee.startX = i+1;
												}
												updateMoveCounter(squaresMoved);
											}
											
											//currentBoard.printBoard();
				
											     
											cee.selectedState = 0;	
											selectedCars = 0;
											
											
											paintCars(currentBoard);
										}
										else if(i>cee.endX-1)
										{
											//printf("Moving down\n");
											int canMove = 1;
											for(boardLine bl : currentBoard.boardLines)
											{
												if(bl.rowNumber <= (i+1) && bl.rowNumber >= (cee.startX))
												{
													if(currentBoard.getCarByCoordinates(bl.rowNumber,j+1) != null
													   && currentBoard.getCarByCoordinates(bl.rowNumber,j+1) != cee)
													   canMove = 0;
												}
											}
											
											if(canMove == 1)
											{
												squaresMoved += (i-(cee.endX-1));
												for(boardLine bl : currentBoard.boardLines)
												{
													if(bl.rowNumber <= (i+1) && bl.rowNumber >= (cee.startX))
													{
														String newLine = "";
														//printf("editing : " + bl.rowLine + "\n");
														newLine+= bl.rowLine.substring(0,2*j);
														if((bl.rowNumber) <= (i+1) && (bl.rowNumber)>=((i+1)-(cee.length-1)))
															newLine+=(cee.carTitle+" ");
														else
															newLine +="x ";
															
														//printf(newLine+"\n");
														newLine+=bl.rowLine.substring(newLine.length(),bl.rowLine.length());
														//printf(newLine+"\n");
														bl.rowLine = newLine;
													}
												}
												if(cee.length == 3)
												{
													cee.endX = i+1;
													cee.middleX = i;
													cee.startX = i-1;
												}
												else
												{
													cee.endX = i+1;
													cee.startX = i;
												}
												updateMoveCounter(squaresMoved);
											}
											
											currentBoard.printBoard();
											
											cee.selectedState = 0;
											selectedCars = 0;
											
											paintCars(currentBoard);
											break;
											
										}
										else{cee.selectedState = 0; continue;}
									}
									else
									{

										if(cee.length == 2)
										{
											grid[cee.startX-1][cee.startY-1].setIcon(null);
											grid[cee.endX-1][cee.endY-1].setIcon(null);

										}
										else
										{
											grid[cee.startX-1][cee.startY-1].setIcon(null);
											grid[cee.middleX-1][cee.middleY-1].setIcon(null);
											grid[cee.endX-1][cee.endY-1].setIcon(null);
										}
										cee.selectedState = 0;	
										selectedCars = 0;
										
									}
									
									}else continue;
								}
							}
		}
		
		public void solve()
		{
		
								solvingFrame.setLayout(new GridLayout(1, 1));
					JLabel solvingLabel = new JLabel("The monkeys are working...");
					
					solvingFrame.getContentPane().add(solvingLabel);
					solvingFrame.setSize(250,100);
					solvingFrame.setLocationRelativeTo(null); 
					solvingFrame.setAlwaysOnTop(true);
					solvingFrame.setVisible(true);
								int win = 0;
					
					int unsolveableFlag = 0;
					ArrayList<car> foundCars = new ArrayList<car>();
					
					for(int rex = 0; rex < 6; rex++)
					{
						if(currentBoard.getCarByCoordinates(rex+1,6) == null)
						{
							unsolveableFlag = 1;
						}
						else{
							if(!(foundCars.contains(currentBoard.getCarByCoordinates(rex+1,6))))
								foundCars.add(currentBoard.getCarByCoordinates(rex+1,6));
						}
					}
					if(foundCars.size() > 2){unsolveableFlag = 1;}
					if(unsolveableFlag == 0)
					{
						JOptionPane unsolveableFrame = new JOptionPane("This board cannot be solved");
						unsolveableFrame.showMessageDialog(frame, "This board cannot be solved.");
					}
					else{
					while(win == 0)
					{
						int goodMove = 1;
						while(goodMove == 1){
						Random generatorCar = new Random();
						
							int randCar = generatorCar.nextInt(currentBoard.cars.size());
							cee = currentBoard.cars.get(randCar);
						
						
						Random generatori = new Random(); 
						int i = generatori.nextInt(6);
						//int i;
						Random generatorj = new Random(); 
						int j = generatorj.nextInt(6);
						//int j;
						
						printf(String.valueOf(i) + " " + String.valueOf(j)+"\n");
						
						if(cee.orientation.equals("Horizontal")){
							
							i = cee.startX-1;
							j = generatorj.nextInt(6);
							
							if(cee.length == 3){
								if(j == cee.startY-1 || j == cee.middleY-1 || j == cee.endY-1 || currentBoard.getCarByCoordinates(i+1, j+1)!=null)
									goodMove = 0;}
							else{if(j == cee.startY-1  || j == cee.endY-1 || currentBoard.getCarByCoordinates(i+1, j+1)!=null)
									goodMove = 0;
									}
					
						}
						else if(cee.orientation.equals("Vertical"))
						{
							j = cee.startY-1;
							i  = generatori.nextInt(6);
							
							if(cee.length == 3){
								if(i == cee.startX-1 || i == cee.middleX-1 || i == cee.endX-1|| currentBoard.getCarByCoordinates(i+1, j+1)!=null)
									goodMove = 0;}
							else{if(i == cee.startX-1  || i == cee.endX-1|| currentBoard.getCarByCoordinates(i+1, j+1)!=null)
									goodMove = 0;
									}
						}
						
						
						if(currentBoard.getCarByCoordinates(i+1, j+1) != null)
						{
							cee = currentBoard.getCarByCoordinates(i+1, j+1);
							printf("Selected car '" + cee.carTitle + "' \n");
							if(cee.selectedState == 0 && selectedCars == 0)
							{
								if(cee.length == 2)
								{
									grid[cee.startX-1][cee.startY-1].setIcon(selectedCarImage);
									grid[cee.endX-1][cee.endY-1].setIcon(selectedCarImage);
								}
								else
								{
									grid[cee.startX-1][cee.startY-1].setIcon(selectedCarImage);
									grid[cee.middleX-1][cee.middleY-1].setIcon(selectedCarImage);
									grid[cee.endX-1][cee.endY-1].setIcon(selectedCarImage);
								}
								cee.selectedState = 1;
								selectedCars = 1;
							}
							else if(cee.selectedState == 0 && selectedCars == 1)
							{	
								for(int x=0;x<boardSize;x++)
								{
									for(int y=0;y<boardSize;y++)
									{
										if(currentBoard.getCarByCoordinates(x+1, y+1) != null && currentBoard.getCarByCoordinates(x+1, y+1).selectedState == 1)
											formerCee = currentBoard.getCarByCoordinates(x+1, y+1);
									}
								}
								
								printf("currently selected car: " + cee.carTitle + "\n");
								printf("formerly selected car: " + formerCee.carTitle + "\n");
								
								if(cee.length == 2)
								{
									grid[cee.startX-1][cee.startY-1].setIcon(selectedCarImage);
									grid[cee.endX-1][cee.endY-1].setIcon(selectedCarImage);
								}
								else
								{
									grid[cee.startX-1][cee.startY-1].setIcon(selectedCarImage);
									grid[cee.middleX-1][cee.middleY-1].setIcon(selectedCarImage);
									grid[cee.endX-1][cee.endY-1].setIcon(selectedCarImage);
								}
								cee.selectedState = 1;
								selectedCars = 1;
								
								if(formerCee.length == 2)
								{
									grid[formerCee.startX-1][formerCee.startY-1].setIcon(null);
									grid[formerCee.endX-1][formerCee.endY-1].setIcon(null);
								}
								else
								{
									grid[formerCee.startX-1][formerCee.startY-1].setIcon(null);
									grid[formerCee.middleX-1][formerCee.middleY-1].setIcon(null);
									grid[formerCee.endX-1][formerCee.endY-1].setIcon(null);
								}
								
								formerCee.selectedState = 0;	
								
							}
							else if(cee.selectedState == 1 && selectedCars == 1)
							{
								if(cee.length == 2)
								{
									grid[cee.startX-1][cee.startY-1].setIcon(null);
									grid[cee.endX-1][cee.endY-1].setIcon(null);
								}
								else
								{
									grid[cee.startX-1][cee.startY-1].setIcon(null);
									grid[cee.middleX-1][cee.middleY-1].setIcon(null);
									grid[cee.endX-1][cee.endY-1].setIcon(null);
								}

								cee.selectedState = 0;	
								selectedCars = 0;
							}
						}
						else
						{
							//Here we will handle moving the car
							if(selectedCars == 1)
							{
								cee = currentBoard.getSelectedCar();
								//moving a horizontal on top, vertical below
								if(cee.orientation.equals("Horizontal"))
								{
									if(i == (cee.startX-1))
									{
										int canMakeMove = 1;
										//check if we have a car in the way
										if(j > cee.endY-1) //to the right
										{
											for(int jay = cee.endY; jay <= (j+1);jay++)
											{
													if(currentBoard.getCarByCoordinates(i+1,jay) != null
													   && currentBoard.getCarByCoordinates(i+1,jay) != cee)
													{
													   canMakeMove = 0;
													   
													    cee.selectedState = 0;	
														selectedCars = 0;
													   
														if(cee.length == 2)
														{
															grid[cee.startX-1][cee.startY-1].setIcon(null);
															grid[cee.endX-1][cee.endY-1].setIcon(null);

														}
														else
														{
															grid[cee.startX-1][cee.startY-1].setIcon(null);
															grid[cee.middleX-1][cee.middleY-1].setIcon(null);
															grid[cee.endX-1][cee.endY-1].setIcon(null);
														}
													   
													}   
											}
										}
										else
										{
											for(int jay = (j+1); jay <= (cee.endY);jay++)
											{
													if(currentBoard.getCarByCoordinates(i+1,jay) != null
													   && currentBoard.getCarByCoordinates(i+1,jay) != cee)
													{
													   canMakeMove = 0;
													   
														cee.selectedState = 0;	
														selectedCars = 0;
													   
														if(cee.length == 2)
														{
															grid[cee.startX-1][cee.startY-1].setIcon(null);
															grid[cee.endX-1][cee.endY-1].setIcon(null);

														}
														else
														{
															grid[cee.startX-1][cee.startY-1].setIcon(null);
															grid[cee.middleX-1][cee.middleY-1].setIcon(null);
															grid[cee.endX-1][cee.endY-1].setIcon(null);
														}
													   
													}   
											}
										}
										
										if(canMakeMove == 1)
										{
											printf("Can move in this row/column\n");
											
											String newLine ="";
											
											//printf("   "+newLine+"\n");
											
											if(cee.length == 2)
											{
												grid[cee.startX-1][cee.startY-1].setIcon(null);
												grid[cee.endX-1][cee.endY-1].setIcon(null);
												
													grid[cee.startX-1][cee.startY-1].setBackground(null);
													grid[cee.startX-1][cee.startY-1].setContentAreaFilled( false );
													grid[cee.startX-1][cee.startY-1].setOpaque(false);
													grid[cee.endX-1][cee.endY-1].setBackground(null);
													grid[cee.endX-1][cee.endY-1].setContentAreaFilled( false );
													grid[cee.endX-1][cee.endY-1].setOpaque(false);
											}
											else
											{
												grid[cee.startX-1][cee.startY-1].setIcon(null);
												grid[cee.middleX-1][cee.middleY-1].setIcon(null);
												grid[cee.endX-1][cee.endY-1].setIcon(null);
												
													grid[cee.startX-1][cee.startY-1].setBackground(null);
													grid[cee.startX-1][cee.startY-1].setContentAreaFilled( false );
													grid[cee.startX-1][cee.startY-1].setOpaque(false);
													grid[cee.middleX-1][cee.middleY-1].setBackground(null);
													grid[cee.middleX-1][cee.middleY-1].setContentAreaFilled( false );
													grid[cee.middleX-1][cee.middleY-1].setOpaque(false);
													grid[cee.endX-1][cee.endY-1].setBackground(null);
													grid[cee.endX-1][cee.endY-1].setContentAreaFilled( false );
													grid[cee.endX-1][cee.endY-1].setOpaque(false);
											}
											

											
											if(j > cee.endY-1)
											{
												printf("moved to the right\n");
												squaresMoved += (j-(cee.endY-1));
												
												newLine += currentBoard.boardLines.get(i).rowLine.substring(0,2*(cee.startY-1));
												printf("new start: " + newLine + "\n");
												
												for(int d = 0; (d)<(j-(cee.endY-1));d++)
													newLine += "x ";
												
												for(int d = 0; d < cee.length; d++)
												{
													newLine += (cee.carTitle + " ");
												}
												
												if(cee.length == 3)
												{
													cee.endY = j+1;
													cee.middleY = j;
													cee.startY = j-1;
												}
												else
												{
													cee.endY = j+1;
													cee.startY = j;
												}
													newLine += currentBoard.boardLines.get(i).rowLine.substring((2*j+2), currentBoard.boardLines.get(i).rowLine.length());
													newLine += " ";
													printf("new: " + newLine +"\n");
													currentBoard.boardLines.get(i).rowLine = newLine;
													updateMoveCounter(squaresMoved);
											}
											else if(j < cee.startY-1)
											{
												printf("moved to the left\n");
												squaresMoved += ((cee.startY-1)-j);
												newLine += currentBoard.boardLines.get(i).rowLine.substring(0,2*j);
												printf("new start: " + newLine + "\n");
												
												for(int d = 0; d < cee.length; d++)
												{
													newLine += (cee.carTitle + " ");
												}
												
												for(int d = 0; (d)<((cee.startY-1)-j);d++)
													newLine += "x ";
												
												if(cee.length == 3)
												{
													cee.endY = j+3;
													cee.middleY = j+2;
													cee.startY = j+1;
												}
												else
												{
													cee.endY = j+2;
													cee.startY = j+1;
												}
													newLine += currentBoard.boardLines.get(i).rowLine.substring(newLine.length(), currentBoard.boardLines.get(i).rowLine.length());
													newLine += " ";
													printf("new: " + newLine +"\n");
													currentBoard.boardLines.get(i).rowLine = newLine;
													
													updateMoveCounter(squaresMoved);
											}
											
											cee.selectedState = 0;	
											selectedCars = 0;
											
											currentBoard.boardLines.get(i).rowLine = newLine;
											currentBoard.printBoard();
											paintCars(currentBoard);
										}
									}
									else
									{
										if(cee.length == 2)
										{
											grid[cee.startX-1][cee.startY-1].setIcon(null);
											grid[cee.endX-1][cee.endY-1].setIcon(null);

										}
										else
										{
											grid[cee.startX-1][cee.startY-1].setIcon(null);
											grid[cee.middleX-1][cee.middleY-1].setIcon(null);
											grid[cee.endX-1][cee.endY-1].setIcon(null);
										}
										cee.selectedState = 0;	
										selectedCars = 0;
									}
								}
								else
								{
									if(j == (cee.startY-1))
									{
										printf("Can move in this row/column\n");
										
											if(cee.length == 2)
											{
												grid[cee.startX-1][cee.startY-1].setIcon(null);
												grid[cee.endX-1][cee.endY-1].setIcon(null);
												
													grid[cee.startX-1][cee.startY-1].setBackground(null);
													grid[cee.startX-1][cee.startY-1].setContentAreaFilled( false );
													grid[cee.startX-1][cee.startY-1].setOpaque(false);
													grid[cee.endX-1][cee.endY-1].setBackground(null);
													grid[cee.endX-1][cee.endY-1].setContentAreaFilled( false );
													grid[cee.endX-1][cee.endY-1].setOpaque(false);
											}
											else
											{
												grid[cee.startX-1][cee.startY-1].setIcon(null);
												grid[cee.middleX-1][cee.middleY-1].setIcon(null);
												grid[cee.endX-1][cee.endY-1].setIcon(null);
												
													grid[cee.startX-1][cee.startY-1].setBackground(null);
													grid[cee.startX-1][cee.startY-1].setContentAreaFilled( false );
													grid[cee.startX-1][cee.startY-1].setOpaque(false);
													grid[cee.middleX-1][cee.middleY-1].setBackground(null);
													grid[cee.middleX-1][cee.middleY-1].setContentAreaFilled( false );
													grid[cee.middleX-1][cee.middleY-1].setOpaque(false);
													grid[cee.endX-1][cee.endY-1].setBackground(null);
													grid[cee.endX-1][cee.endY-1].setContentAreaFilled( false );
													grid[cee.endX-1][cee.endY-1].setOpaque(false);
											}
										
										if(i < (cee.startX-1))
										{
											printf("Moving up\n");
											
											int canMove = 1;
											for(boardLine bl : currentBoard.boardLines)
											{
												if(bl.rowNumber >= (i+1) && bl.rowNumber <= (cee.endX))
												{
													if(currentBoard.getCarByCoordinates(bl.rowNumber,j+1) != null
													   && currentBoard.getCarByCoordinates(bl.rowNumber,j+1) != cee)
													   canMove = 0;
												}
											}
											
											if(canMove == 1)
											{
												squaresMoved += ((cee.startX-1)-i);
												for(boardLine bl : currentBoard.boardLines)
												{
													if(bl.rowNumber >= (i+1) && bl.rowNumber <= (cee.endX))
													{
														String newLine = "";
														//printf("editing : " + bl.rowLine + "\n");
														newLine+= bl.rowLine.substring(0,2*j);
														if((bl.rowNumber) >= (i+1) && (bl.rowNumber)<=((i+1)+(cee.length-1)))
															newLine+=(cee.carTitle+" ");
														else
															newLine +="x ";
															
														//printf(newLine+"\n");
														newLine+=bl.rowLine.substring(newLine.length(),bl.rowLine.length());
														printf(newLine+"\n");
														bl.rowLine = newLine;
													}
												}
												if(cee.length == 3)
												{
													cee.endX = i+3;
													cee.middleX = i+2;
													cee.startX = i+1;
												}
												else
												{
													cee.endX = i+2;
													cee.startX = i+1;
												}
												updateMoveCounter(squaresMoved);
											}
											
											currentBoard.printBoard();
				
											     
											cee.selectedState = 0;	
											selectedCars = 0;
											
											
											paintCars(currentBoard);
										}
										else
										{
											printf("Moving down\n");
											int canMove = 1;
											for(boardLine bl : currentBoard.boardLines)
											{
												if(bl.rowNumber <= (i+1) && bl.rowNumber >= (cee.startX))
												{
													if(currentBoard.getCarByCoordinates(bl.rowNumber,j+1) != null
													   && currentBoard.getCarByCoordinates(bl.rowNumber,j+1) != cee)
													   canMove = 0;
												}
											}
											
											if(canMove == 1)
											{
												squaresMoved += (i-(cee.endX-1));
												for(boardLine bl : currentBoard.boardLines)
												{
													if(bl.rowNumber <= (i+1) && bl.rowNumber >= (cee.startX))
													{
														String newLine = "";
														//printf("editing : " + bl.rowLine + "\n");
														newLine+= bl.rowLine.substring(0,2*j);
														if((bl.rowNumber) <= (i+1) && (bl.rowNumber)>=((i+1)-(cee.length-1)))
															newLine+=(cee.carTitle+" ");
														else
															newLine +="x ";
															
														//printf(newLine+"\n");
														newLine+=bl.rowLine.substring(newLine.length(),bl.rowLine.length());
														printf(newLine+"\n");
														bl.rowLine = newLine;
													}
												}
												if(cee.length == 3)
												{
													cee.endX = i+1;
													cee.middleX = i;
													cee.startX = i-1;
												}
												else
												{
													cee.endX = i+1;
													cee.startX = i;
												}
												updateMoveCounter(squaresMoved);
											}
											
											currentBoard.printBoard();
											
											cee.selectedState = 0;	
											selectedCars = 0;
											
											paintCars(currentBoard);
										}
									}
									else
									{

										if(cee.length == 2)
										{
											grid[cee.startX-1][cee.startY-1].setIcon(null);
											grid[cee.endX-1][cee.endY-1].setIcon(null);

										}
										else
										{
											grid[cee.startX-1][cee.startY-1].setIcon(null);
											grid[cee.middleX-1][cee.middleY-1].setIcon(null);
											grid[cee.endX-1][cee.endY-1].setIcon(null);
										}
										cee.selectedState = 0;	
										selectedCars = 0;
										
									}
								}
								
							}
							
						}
						
								car isWin = currentBoard.getCar('P');
								if(isWin != null && isWin.endY==(currentBoard.boardSize))
								{
									timer.stop();
									
									JLabel winEndLabel = new JLabel("  Time: CHEATED");
									winFrame.getContentPane().add(winEndLabel);
									JLabel winEndLabel2 = new JLabel("	Squares Moved: " + moveCounter.getText() + "  ");
									winFrame.getContentPane().add(winEndLabel2);
									winFrame.setSize(200,200);
									winFrame.setLocationRelativeTo(null); 
									
									winFrame.setVisible(true);
									 winFrame.setAlwaysOnTop(true);
									winFrame.toFront();
									
									solvingFrame.setVisible(false);
									solvingFrame.dispose();

									goodMove = 0;
									win = 1;
								}
							}	
					}	
				}	
		}
	
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
			
			if(e.getSource() == aboutButton && s.equals("Left Mouse Click"))
				showAboutFrame();
				
			if(e.getSource() == exitButton && s.equals("Left Mouse Click"))
			{
					try
					{
					/*Pass a system command to restart program using the already supplied arguments from the initial startup*/							
					java.lang.Runtime.getRuntime().exec(new String[] { "java", "gridLockLauncher"});
					
					launchedFromLauncher = 1;
					
					System.exit(0);
					}
					catch(Exception f) //catch an exception, shut down everything
					{
						System.out.println("Something went wrong with restarting");
						System.exit(0);
					}
				exitGame();
			}
			
			if(e.getSource() == restartButton && s.equals("Left Mouse Click"))
			{
							aboutFrame.setVisible(false);
							aboutFrame.dispose();
							frame.setVisible(false);
							frame.dispose();
					try
					{
					/*Pass a system command to restart program using the already supplied arguments from the initial startup*/							
					java.lang.Runtime.getRuntime().exec(new String[] { "java", "GridLock", "Maps/"+currentBoard.boardNumber});
					
					//launchedFromLauncher = 1;
					
					//System.exit(0);
					}
					catch(Exception f) //catch an exception, shut down everything
					{
						System.out.println("Something went wrong with restarting");
						System.exit(0);
					}
			}		

			if((e.getSource() == continueButton && s.equals("Left Mouse Click")))
			{
				winFrame.setVisible(false);
				winFrame.dispose();					
				aboutFrame.setVisible(false);
				aboutFrame.dispose();
				frame.setVisible(false);
				frame.dispose();
				if(currentBoard.boardNumber.equals("map10.txt"))
				{
					try
					{
					/*Pass a system command to restart program using the already supplied arguments from the initial startup*/							
					java.lang.Runtime.getRuntime().exec(new String[] { "java", "gridLockLauncher"});
					
					launchedFromLauncher = 1;
					
					System.exit(0);
					}
					catch(Exception f) //catch an exception, shut down everything
					{
						System.out.println("Something went wrong with restarting");
						System.exit(0);
					}
					exitGame();
				}
				else 
				{
					try
					{
					
					/*Pass a system command to restart program using the already supplied arguments from the initial startup*/							
					java.lang.Runtime.getRuntime().exec(new String[] { "java", "GridLock", "Maps/"+nextBoard});
					
					//launchedFromLauncher = 1;
					
					//System.exit(0);
					}
					catch(Exception f) //catch an exception, shut down everything
					{
						System.out.println("Something went wrong with restarting");
						System.exit(0);
					}
				}
			}
			
			else if((e.getSource() == solveButton || e.getSource() == nextBoardButton) && s.equals("Left Mouse Click"))
			{
					//JOptionPane solvingPane = new JOptionPane("Wuss");
					//aboutDialog.showMessageDialog(frame, "You are a wuss");
					

					
					if(e.getSource() == solveButton)
						solve();
					else if(e.getSource() == nextBoardButton)
						doHint();
			}
			

			/*
				Trying to get a car object, so when it's clicked, we can make it the active car for 
				moving around the board.
			*/
			
			//printf(boardSize+"\n");
						
			for(int i = 0; i < boardSize; i++)
			{
				for(int j = 0; j < boardSize; j++)
				{
					if(e.getSource() == grid[i][j] && s.equals("Left Mouse Click"))
					{
						if(firstClick == 0) {	//start the timer if this is the first click
							timer.start(); firstClick = 1;
						}
					
						if(currentBoard.getCarByCoordinates(i+1, j+1) != null)
						{
							cee = currentBoard.getCarByCoordinates(i+1, j+1);
							printf("Selected car '" + cee.carTitle + "' \n");
							if(cee.selectedState == 0 && selectedCars == 0)
							{
								if(cee.length == 2)
								{
									grid[cee.startX-1][cee.startY-1].setIcon(selectedCarImage);
									grid[cee.endX-1][cee.endY-1].setIcon(selectedCarImage);
								}
								else
								{
									grid[cee.startX-1][cee.startY-1].setIcon(selectedCarImage);
									grid[cee.middleX-1][cee.middleY-1].setIcon(selectedCarImage);
									grid[cee.endX-1][cee.endY-1].setIcon(selectedCarImage);
								}
								cee.selectedState = 1;
								selectedCars = 1;
							}
							else if(cee.selectedState == 0 && selectedCars == 1)
							{	
								for(int x=0;x<boardSize;x++)
								{
									for(int y=0;y<boardSize;y++)
									{
										if(currentBoard.getCarByCoordinates(x+1, y+1) != null && currentBoard.getCarByCoordinates(x+1, y+1).selectedState == 1)
											formerCee = currentBoard.getCarByCoordinates(x+1, y+1);
									}
								}
								
								printf("currently selected car: " + cee.carTitle + "\n");
								printf("formerly selected car: " + formerCee.carTitle + "\n");
								
								if(cee.length == 2)
								{
									grid[cee.startX-1][cee.startY-1].setIcon(selectedCarImage);
									grid[cee.endX-1][cee.endY-1].setIcon(selectedCarImage);
								}
								else
								{
									grid[cee.startX-1][cee.startY-1].setIcon(selectedCarImage);
									grid[cee.middleX-1][cee.middleY-1].setIcon(selectedCarImage);
									grid[cee.endX-1][cee.endY-1].setIcon(selectedCarImage);
								}
								cee.selectedState = 1;
								selectedCars = 1;
								
								if(formerCee.length == 2)
								{
									grid[formerCee.startX-1][formerCee.startY-1].setIcon(null);
									grid[formerCee.endX-1][formerCee.endY-1].setIcon(null);
								}
								else
								{
									grid[formerCee.startX-1][formerCee.startY-1].setIcon(null);
									grid[formerCee.middleX-1][formerCee.middleY-1].setIcon(null);
									grid[formerCee.endX-1][formerCee.endY-1].setIcon(null);
								}
								
								formerCee.selectedState = 0;	
								
							}
							else if(cee.selectedState == 1 && selectedCars == 1)
							{
								if(cee.length == 2)
								{
									grid[cee.startX-1][cee.startY-1].setIcon(null);
									grid[cee.endX-1][cee.endY-1].setIcon(null);
								}
								else
								{
									grid[cee.startX-1][cee.startY-1].setIcon(null);
									grid[cee.middleX-1][cee.middleY-1].setIcon(null);
									grid[cee.endX-1][cee.endY-1].setIcon(null);
								}

								cee.selectedState = 0;	
								selectedCars = 0;
							}
						}
						else
						{
							//Here we will handle moving the car
							if(selectedCars == 1)
							{
								cee = currentBoard.getSelectedCar();
								//moving a horizontal on top, vertical below
								if(cee.orientation.equals("Horizontal"))
								{
									if(i == (cee.startX-1))
									{
										int canMakeMove = 1;
										//check if we have a car in the way
										if(j > cee.endY-1) //to the right
										{
											for(int jay = cee.endY; jay <= (j+1);jay++)
											{
													if(currentBoard.getCarByCoordinates(i+1,jay) != null
													   && currentBoard.getCarByCoordinates(i+1,jay) != cee)
													{
													   canMakeMove = 0;
													   
													    cee.selectedState = 0;	
														selectedCars = 0;
													   
														if(cee.length == 2)
														{
															grid[cee.startX-1][cee.startY-1].setIcon(null);
															grid[cee.endX-1][cee.endY-1].setIcon(null);

														}
														else
														{
															grid[cee.startX-1][cee.startY-1].setIcon(null);
															grid[cee.middleX-1][cee.middleY-1].setIcon(null);
															grid[cee.endX-1][cee.endY-1].setIcon(null);
														}
													   
													}   
											}
										}
										else
										{
											for(int jay = (j+1); jay <= (cee.endY);jay++)
											{
													if(currentBoard.getCarByCoordinates(i+1,jay) != null
													   && currentBoard.getCarByCoordinates(i+1,jay) != cee)
													{
													   canMakeMove = 0;
													   
														cee.selectedState = 0;	
														selectedCars = 0;
													   
														if(cee.length == 2)
														{
															grid[cee.startX-1][cee.startY-1].setIcon(null);
															grid[cee.endX-1][cee.endY-1].setIcon(null);

														}
														else
														{
															grid[cee.startX-1][cee.startY-1].setIcon(null);
															grid[cee.middleX-1][cee.middleY-1].setIcon(null);
															grid[cee.endX-1][cee.endY-1].setIcon(null);
														}
													   
													}   
											}
										}
										
										if(canMakeMove == 1)
										{
											printf("Can move in this row/column\n");
											
											String newLine ="";
											
											//printf("   "+newLine+"\n");
											
											if(cee.length == 2)
											{
												grid[cee.startX-1][cee.startY-1].setIcon(null);
												grid[cee.endX-1][cee.endY-1].setIcon(null);
												
													grid[cee.startX-1][cee.startY-1].setBackground(null);
													grid[cee.startX-1][cee.startY-1].setContentAreaFilled( false );
													grid[cee.startX-1][cee.startY-1].setOpaque(false);
													grid[cee.endX-1][cee.endY-1].setBackground(null);
													grid[cee.endX-1][cee.endY-1].setContentAreaFilled( false );
													grid[cee.endX-1][cee.endY-1].setOpaque(false);
											}
											else
											{
												grid[cee.startX-1][cee.startY-1].setIcon(null);
												grid[cee.middleX-1][cee.middleY-1].setIcon(null);
												grid[cee.endX-1][cee.endY-1].setIcon(null);
												
													grid[cee.startX-1][cee.startY-1].setBackground(null);
													grid[cee.startX-1][cee.startY-1].setContentAreaFilled( false );
													grid[cee.startX-1][cee.startY-1].setOpaque(false);
													grid[cee.middleX-1][cee.middleY-1].setBackground(null);
													grid[cee.middleX-1][cee.middleY-1].setContentAreaFilled( false );
													grid[cee.middleX-1][cee.middleY-1].setOpaque(false);
													grid[cee.endX-1][cee.endY-1].setBackground(null);
													grid[cee.endX-1][cee.endY-1].setContentAreaFilled( false );
													grid[cee.endX-1][cee.endY-1].setOpaque(false);
											}
											

											
											if(j > cee.endY-1)
											{
												printf("moved to the right\n");
												squaresMoved += (j-(cee.endY-1));
												
												newLine += currentBoard.boardLines.get(i).rowLine.substring(0,2*(cee.startY-1));
												printf("new start: " + newLine + "\n");
												
												for(int d = 0; (d)<(j-(cee.endY-1));d++)
													newLine += "x ";
												
												for(int d = 0; d < cee.length; d++)
												{
													newLine += (cee.carTitle + " ");
												}
												
												if(cee.length == 3)
												{
													cee.endY = j+1;
													cee.middleY = j;
													cee.startY = j-1;
												}
												else
												{
													cee.endY = j+1;
													cee.startY = j;
												}
													newLine += currentBoard.boardLines.get(i).rowLine.substring((2*j+2), currentBoard.boardLines.get(i).rowLine.length());
													newLine += " ";
													printf("new: " + newLine +"\n");
													currentBoard.boardLines.get(i).rowLine = newLine;
													updateMoveCounter(squaresMoved);
											}
											else if(j < cee.startY-1)
											{
												printf("moved to the left\n");
												squaresMoved += ((cee.startY-1)-j);
												newLine += currentBoard.boardLines.get(i).rowLine.substring(0,2*j);
												printf("new start: " + newLine + "\n");
												
												for(int d = 0; d < cee.length; d++)
												{
													newLine += (cee.carTitle + " ");
												}
												
												for(int d = 0; (d)<((cee.startY-1)-j);d++)
													newLine += "x ";
												
												if(cee.length == 3)
												{
													cee.endY = j+3;
													cee.middleY = j+2;
													cee.startY = j+1;
												}
												else
												{
													cee.endY = j+2;
													cee.startY = j+1;
												}
													newLine += currentBoard.boardLines.get(i).rowLine.substring(newLine.length(), currentBoard.boardLines.get(i).rowLine.length());
													newLine += " ";
													printf("new: " + newLine +"\n");
													currentBoard.boardLines.get(i).rowLine = newLine;
													
													updateMoveCounter(squaresMoved);
											}
											
											cee.selectedState = 0;	
											selectedCars = 0;
											
											currentBoard.boardLines.get(i).rowLine = newLine;
											currentBoard.printBoard();
											paintCars(currentBoard);
										}
									}
									else
									{
										if(cee.length == 2)
										{
											grid[cee.startX-1][cee.startY-1].setIcon(null);
											grid[cee.endX-1][cee.endY-1].setIcon(null);

										}
										else
										{
											grid[cee.startX-1][cee.startY-1].setIcon(null);
											grid[cee.middleX-1][cee.middleY-1].setIcon(null);
											grid[cee.endX-1][cee.endY-1].setIcon(null);
										}
										cee.selectedState = 0;	
										selectedCars = 0;
									}
								}
								else
								{
									if(j == (cee.startY-1))
									{
										printf("Can move in this row/column\n");
										
											if(cee.length == 2)
											{
												grid[cee.startX-1][cee.startY-1].setIcon(null);
												grid[cee.endX-1][cee.endY-1].setIcon(null);
												
													grid[cee.startX-1][cee.startY-1].setBackground(null);
													grid[cee.startX-1][cee.startY-1].setContentAreaFilled( false );
													grid[cee.startX-1][cee.startY-1].setOpaque(false);
													grid[cee.endX-1][cee.endY-1].setBackground(null);
													grid[cee.endX-1][cee.endY-1].setContentAreaFilled( false );
													grid[cee.endX-1][cee.endY-1].setOpaque(false);
											}
											else
											{
												grid[cee.startX-1][cee.startY-1].setIcon(null);
												grid[cee.middleX-1][cee.middleY-1].setIcon(null);
												grid[cee.endX-1][cee.endY-1].setIcon(null);
												
													grid[cee.startX-1][cee.startY-1].setBackground(null);
													grid[cee.startX-1][cee.startY-1].setContentAreaFilled( false );
													grid[cee.startX-1][cee.startY-1].setOpaque(false);
													grid[cee.middleX-1][cee.middleY-1].setBackground(null);
													grid[cee.middleX-1][cee.middleY-1].setContentAreaFilled( false );
													grid[cee.middleX-1][cee.middleY-1].setOpaque(false);
													grid[cee.endX-1][cee.endY-1].setBackground(null);
													grid[cee.endX-1][cee.endY-1].setContentAreaFilled( false );
													grid[cee.endX-1][cee.endY-1].setOpaque(false);
											}
										
										if(i < (cee.startX-1))
										{
											printf("Moving up\n");
											
											int canMove = 1;
											for(boardLine bl : currentBoard.boardLines)
											{
												if(bl.rowNumber >= (i+1) && bl.rowNumber <= (cee.endX))
												{
													if(currentBoard.getCarByCoordinates(bl.rowNumber,j+1) != null
													   && currentBoard.getCarByCoordinates(bl.rowNumber,j+1) != cee)
													   canMove = 0;
												}
											}
											
											if(canMove == 1)
											{
												squaresMoved += ((cee.startX-1)-i);
												for(boardLine bl : currentBoard.boardLines)
												{
													if(bl.rowNumber >= (i+1) && bl.rowNumber <= (cee.endX))
													{
														String newLine = "";
														//printf("editing : " + bl.rowLine + "\n");
														newLine+= bl.rowLine.substring(0,2*j);
														if((bl.rowNumber) >= (i+1) && (bl.rowNumber)<=((i+1)+(cee.length-1)))
															newLine+=(cee.carTitle+" ");
														else
															newLine +="x ";
															
														//printf(newLine+"\n");
														newLine+=bl.rowLine.substring(newLine.length(),bl.rowLine.length());
														printf(newLine+"\n");
														bl.rowLine = newLine;
													}
												}
												if(cee.length == 3)
												{
													cee.endX = i+3;
													cee.middleX = i+2;
													cee.startX = i+1;
												}
												else
												{
													cee.endX = i+2;
													cee.startX = i+1;
												}
												updateMoveCounter(squaresMoved);
											}
											
											currentBoard.printBoard();
				
											     
											cee.selectedState = 0;	
											selectedCars = 0;
											
											
											paintCars(currentBoard);
										}
										else
										{
											printf("Moving down\n");
											int canMove = 1;
											for(boardLine bl : currentBoard.boardLines)
											{
												if(bl.rowNumber <= (i+1) && bl.rowNumber >= (cee.startX))
												{
													if(currentBoard.getCarByCoordinates(bl.rowNumber,j+1) != null
													   && currentBoard.getCarByCoordinates(bl.rowNumber,j+1) != cee)
													   canMove = 0;
												}
											}
											
											if(canMove == 1)
											{
												squaresMoved += (i-(cee.endX-1));
												for(boardLine bl : currentBoard.boardLines)
												{
													if(bl.rowNumber <= (i+1) && bl.rowNumber >= (cee.startX))
													{
														String newLine = "";
														//printf("editing : " + bl.rowLine + "\n");
														newLine+= bl.rowLine.substring(0,2*j);
														if((bl.rowNumber) <= (i+1) && (bl.rowNumber)>=((i+1)-(cee.length-1)))
															newLine+=(cee.carTitle+" ");
														else
															newLine +="x ";
															
														//printf(newLine+"\n");
														newLine+=bl.rowLine.substring(newLine.length(),bl.rowLine.length());
														printf(newLine+"\n");
														bl.rowLine = newLine;
													}
												}
												if(cee.length == 3)
												{
													cee.endX = i+1;
													cee.middleX = i;
													cee.startX = i-1;
												}
												else
												{
													cee.endX = i+1;
													cee.startX = i;
												}
												updateMoveCounter(squaresMoved);
											}
											
											currentBoard.printBoard();
											
											cee.selectedState = 0;	
											selectedCars = 0;
											
											paintCars(currentBoard);
										}
									}
									else
									{

										if(cee.length == 2)
										{
											grid[cee.startX-1][cee.startY-1].setIcon(null);
											grid[cee.endX-1][cee.endY-1].setIcon(null);

										}
										else
										{
											grid[cee.startX-1][cee.startY-1].setIcon(null);
											grid[cee.middleX-1][cee.middleY-1].setIcon(null);
											grid[cee.endX-1][cee.endY-1].setIcon(null);
										}
										cee.selectedState = 0;	
										selectedCars = 0;
										
									}
								}
								
							}
							
						}
						
								car isWin = currentBoard.getCar('P');
								if(isWin != null && isWin.endY==(currentBoard.boardSize))
								{
									timer.stop();
									JLabel winEndLabel = new JLabel("  Time: " + realTimer.getText() + " sec.");
									winFrame.getContentPane().add(winEndLabel);
									JLabel winEndLabel2 = new JLabel("	Squares Moved: " + moveCounter.getText() + "  ");
									winFrame.getContentPane().add(winEndLabel2);
									winFrame.setSize(200,200);
									winFrame.setLocationRelativeTo(null); 
									winFrame.setVisible(true);
								}
						
					}
				}
			}
		
		}
	}
	
}
