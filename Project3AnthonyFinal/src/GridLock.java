import java.util.*;
import java.io.*;
import java.lang.Math.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/*
	Gridlock contains all subclasses
	for use by the game.  This includes the board,
	and the lines in the board
							*/
public class GridLock
{
	int launchedFromLauncher = 0; //for avoiding game frezze, do not get rid of this!!!
	int counter = 0;
	board initialBoard; //for restarting
	
	/*
		Structure containing instances of board might be interesting
	*/
	ArrayList<board> boards = new ArrayList<board>();	
	
	board currentBoard = null;
	int selectedCars = 0;
	/*
		Variable for board width/length for use by other classes/methods
	*/
	int boardSize = 6;

	/*
		Declare the grid here, and we can use inheiritance to access it in other classes
	*/
	JButton[][] grid = new JButton[boardSize][boardSize]; //allocate the size of grid
	
	
	ImageIcon selectedCarImage = new ImageIcon("Images/selectedCarIcon.jpg");
		
	/*
		Print method to make our lives easier
	*/
	public void printf(String s)
	{
		System.out.print(s);
	}


	/*
		Create a new car object.  Car title will be a character, and the orientation will be set 
		upon creating by looking at the space next to the first occurence of a character
		to see if the car continues left<->right or up<->down.  length grows as more "letters"
		of the car are encountered while reading the map file.
		
		line is the row the car is found in.  index is the column
		*/
	public car makeCar(char c, String orient, int line, int index) 
	{
		car cc = new car();
		cc.carTitle = c;
		cc.orientation = orient;
		cc.length = 1;

		cc.startX = line;
		cc.startY = index;

		cc.selectedState = 0;
		
		return cc;
	}
	
	

	/* 
		Class for a line on the board, includes row number and a string
	*/
	public class boardLine	//each line of the board, so we can keep track of row numbers
	{			//more easily
		int rowNumber;
		String rowLine;
	}
	
	public void solveBoard(board c){
		//first generate every possible board configuration using bfs
		Queue<board> queue = new LinkedList<board>();
		board newConfig = new board();
			newConfig.cars = new ArrayList<car>();
			for(car ccc : c.cars)
				newConfig.cars.add(ccc);
			newConfig.boardLines = new ArrayList<boardLine>();
				for(boardLine bbb : c.boardLines)
					newConfig.boardLines.add(bbb);
			Random generatorCar = new Random(); 
				
				for(int carSelect = 0; carSelect < newConfig.cars.size();carSelect++){
								//try every move, for every car, form every board in the
								//list using the queue.  if the config we get is
								//a victory, use that queue state to solve.
							
							car cee = newConfig.cars.get(carSelect);
						
						
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
													if(newConfig.getCarByCoordinates(i+1,jay) != null
													   && newConfig.getCarByCoordinates(i+1,jay) != cee)
													{
														//printf("found a car\n");
													   canMakeMove = 0;
													   
													    cee.selectedState = 0;	
														//selectedCars = 0;
													   
														/*if(cee.length == 2)
														{
															grid[cee.startX-1][cee.startY-1].setIcon(null);
															grid[cee.endX-1][cee.endY-1].setIcon(null);

														}
														else
														{
															grid[cee.startX-1][cee.startY-1].setIcon(null);
															grid[cee.middleX-1][cee.middleY-1].setIcon(null);
															grid[cee.endX-1][cee.endY-1].setIcon(null);
														}*/
													   
													}   
											}
										}
										else if(j < cee.startY-1)
										{
											for(int jay = (j+1); jay <= (cee.endY);jay++)
											{
													if(newConfig.getCarByCoordinates(i+1,jay) != null
													   && newConfig.getCarByCoordinates(i+1,jay) != cee)
													{
													   canMakeMove = 0;
													   
														cee.selectedState = 0;	
														//selectedCars = 0;
													   
														/*if(cee.length == 2)
														{
															grid[cee.startX-1][cee.startY-1].setIcon(null);
															grid[cee.endX-1][cee.endY-1].setIcon(null);

														}
														else
														{
															grid[cee.startX-1][cee.startY-1].setIcon(null);
															grid[cee.middleX-1][cee.middleY-1].setIcon(null);
															grid[cee.endX-1][cee.endY-1].setIcon(null);
														}*/
													   
													}   
											}
										}
										else{cee.selectedState = 0; continue;}
										
										if(canMakeMove == 1)
										{
											//printf("Can move in this row/column\n");
											
											String newLine ="";
											
											////printf("   "+newLine+"\n");
											
											/*if(cee.length == 2)
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
											}*/
											

											
											if(j > cee.endY-1)
											{
												//printf("moved to the right\n");
												//squaresMoved += (j-(cee.endY-1));
												
												newLine += newConfig.boardLines.get(i).rowLine.substring(0,2*(cee.startY-1));
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
													newLine += newConfig.boardLines.get(i).rowLine.substring((2*j+2), newConfig.boardLines.get(i).rowLine.length());
													newLine += " ";
													//printf("new: " + newLine +"\n");
													newConfig.boardLines.get(i).rowLine = newLine;
													//updateMoveCounter(squaresMoved);
											}
											else if(j < cee.startY-1)
											{
												//printf("moved to the left\n");
												//squaresMoved += ((cee.startY-1)-j);
												newLine += newConfig.boardLines.get(i).rowLine.substring(0,2*j);
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
													newLine += newConfig.boardLines.get(i).rowLine.substring(newLine.length(), newConfig.boardLines.get(i).rowLine.length());
													newLine += " ";
													//printf("new: " + newLine +"\n");
													newConfig.boardLines.get(i).rowLine = newLine;
													//printf("was changed: " + newConfig.boardLines.get(i).rowLine+"\n");
													//updateMoveCounter(squaresMoved);
											}
											
											cee.selectedState = 0;	
											//selectedCars = 0;
											
											newConfig.boardLines.get(i).rowLine = newLine;
											//newConfig.printBoard();
											//paintCars(newConfig);
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
										
											/*if(cee.length == 2)
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
											}*/
										
										if(i < (cee.startX-1))
										{
											//printf("Moving up\n");
											
											int canMove = 1;
											for(boardLine bl : newConfig.boardLines)
											{
												if(bl.rowNumber >= (i+1) && bl.rowNumber <= (cee.endX))
												{
													if(newConfig.getCarByCoordinates(bl.rowNumber,j+1) != null
													   && newConfig.getCarByCoordinates(bl.rowNumber,j+1) != cee)
													   canMove = 0;
												}
											}
											
											if(canMove == 1)
											{
												//squaresMoved += ((cee.startX-1)-i);
												for(boardLine bl : newConfig.boardLines)
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
												//updateMoveCounter(squaresMoved);
											}
											
											//newConfig.printBoard();
				
											     
											cee.selectedState = 0;	
											//selectedCars = 0;
											
											
											//paintCars(newConfig);
										}
										else if(i>cee.endX-1)
										{
											//printf("Moving down\n");
											int canMove = 1;
											for(boardLine bl : newConfig.boardLines)
											{
												if(bl.rowNumber <= (i+1) && bl.rowNumber >= (cee.startX))
												{
													if(newConfig.getCarByCoordinates(bl.rowNumber,j+1) != null
													   && newConfig.getCarByCoordinates(bl.rowNumber,j+1) != cee)
													   canMove = 0;
												}
											}
											
											if(canMove == 1)
											{
												//squaresMoved += (i-(cee.endX-1));
												for(boardLine bl : newConfig.boardLines)
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
												//updateMoveCounter(squaresMoved);
											}
											
											//newConfig.printBoard();
											
											cee.selectedState = 0;	
											break;
											//selectedCars = 0;
											
											//paintCars(newConfig);
										}
										else{cee.selectedState = 0; continue;}
									}
									else
									{

										/*if(cee.length == 2)
										{
											grid[cee.startX-1][cee.startY-1].setIcon(null);
											grid[cee.endX-1][cee.endY-1].setIcon(null);

										}
										else
										{
											grid[cee.startX-1][cee.startY-1].setIcon(null);
											grid[cee.middleX-1][cee.middleY-1].setIcon(null);
											grid[cee.endX-1][cee.endY-1].setIcon(null);
										}*/
										cee.selectedState = 0;	
										//selectedCars = 0;
										
									}
									
									}else continue;
								}
							}
				newConfig.printBoard();
				car isWin = newConfig.getCar('P');
				if(isWin.endY == 6)
				{
					printf("Awesome: " + counter+ "\n");
				}
				else
				{
				counter++;
					solveBoard(newConfig);
				}
	}

		/*
		Over here we will read a txt file to create our "map" in the game
		read it line by line, we will insert an extra character to be our "exit point"
		that we will be able to use to determine when the car has made it out of the gridlock
												*/

	public board makeBoard(String fileName){ //read file line by line, takes string as parameter
		String line = null; //initialize string to represent line in file

		board b = new board(); //create a new board instance

		b.boardLines = new ArrayList<boardLine>(); //initialize its list of lines
		b.cars = new ArrayList<car>();	//initialize the board's list of cars
		boards.add(b);	//add the board to list 
		
		try { //try
		    // FileReader reads text files in the default encoding.
		    FileReader fileReader = 
		        new FileReader(fileName); //fileReader instance

		    // Always wrap FileReader in BufferedReader.
		    BufferedReader bufferedReader = 
		        new BufferedReader(fileReader);
		    
			int i = 1; //for finding the row a car is present in
		    while((line = bufferedReader.readLine()) != null){ //go through each line again, this time
		    												 
		        //line = line.trim(); // remove leading and trailing whitespace
		        if (!line.equals("")) // don't write out blank lines
		        {
				boardLine bl = new boardLine(); //new board line

				bl.rowLine = line;	//set it's line

				bl.rowNumber = i;	//set its row number
					i++;
				
				b.boardLines.add(bl);	//add it to the board's set of lines
	
				/* 	Detect cars in the board.
					Scan each character in a line and see if we detect a letter that is not 'x' which
					will represent a car.  We will see if the car goes r<->l or u<->d.
					if the letter we encounter corresponds to a car that already exists we will
					increment that car's length, otherwise we create a new car object  
				*/
				int ind = 0; //index for calculating column where car is present
				for(int rex = 0; rex < line.length(); rex++)
				{
					if(line.charAt(rex) != 'x' && line.charAt(rex) != ' ')
					{
						ind++;
						if(b.getCar(line.charAt(rex)) != null)
						{
							//printf("Car " + line.charAt(rex) + " already exists.");

							car cz = b.getCar(line.charAt(rex));
							cz.length++;
							if(cz.length == 2 || cz.length == 3)
							{
								if(cz.length == 3)
								{
									cz.middleX = cz.endX;
									cz.middleY = cz.endY;
								}

								cz.endX = bl.rowNumber;
								cz.endY = ind;
							}							
						}
						else    
						{
							//printf("Car " + line.charAt(rex) + " doesn't exists.");


							if (line.charAt(rex+2) == line.charAt(rex))	//if we encounter a matching
							{						//char right next to the first char
								car cx = makeCar(line.charAt(rex),"Horizontal", bl.rowNumber, ind);
								b.cars.add(cx);		//we know in this case the car is L<->R
								//printf("Made car " + cx.carTitle + " at "+ bl.rowNumber+ind+"\n");
							}
							else
							{
								car cx = makeCar(line.charAt(rex),"Vertical", bl.rowNumber, ind);
								b.cars.add(cx);	//else, the car is U<->D, no 1-square cars
								//printf("Made car " + cx.carTitle + " at "+ bl.rowNumber+ind+"\n");
							}
						}
					}
					else if(line.charAt(rex) == 'x') ind++;
				}

		        }
		    }
		    bufferedReader.close();	
		}
		catch(FileNotFoundException ex) { //catch exception for file not there
		    printf(
		        "Unable to open file '" + 
		        fileName + "' [FILE NOT FOUND]\n");
		    return null;				
		}
		catch(IOException ex) { //catch exception for file corrupted
		    printf(
		        "Error reading file '" 
		        + fileName + "'\n");	
			return null;				
		    // Or we could just do this: 
		    // ex.printStackTrace();
		}
		
		boardSize = b.boardLines.size();
		initialBoard = b;
		return b;
	}


	public static void main(String[] args)
	{
		if(args.length == 1)
		{
			GridLock game = new GridLock(); //a new instance of gridlock
			
			
			board b1 = game.makeBoard(args[0]);	//make a new board for the first map
			b1.boardNumber = args[0].substring(5,args[0].length());
			
			game.printf("Board\n----------------------\n");//print the board's information
			b1.printBoard();
			gridLockGUI gui1 = new gridLockGUI();
			gui1.createGUI(b1);
			game.currentBoard = b1;
			//game.solveBoard(game.currentBoard);
		}
		else
		{
			System.out.println("Program usage: GridLock <map file>");
		}
	}

}
