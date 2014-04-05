import java.util.*;
import java.io.*;
import java.lang.Math.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/*
	Our board will be an arraylist of strings to represent what is on the board, 
	we should use these strings to find our
	car objects and judge when the player wins
							*/
public class board extends GridLock
{
	String boardNumber;
	ArrayList<boardLine> boardLines;
	ArrayList<car> cars;

	public car getCar(char c)
	{
		for(car cc : cars)
			if(cc.carTitle == c) return cc;

		return null;
	}
	
	public car getCarByCoordinates(int x, int y)
	{
		for(car cc : cars)
		{
			if(cc.length == 2)
			{
				if((cc.startX == (x) && cc.startY == (y))
				|| (cc.endX == (x) && cc.endY == (y)))
				{
					return cc;
				}
			}
			else
			{
				if((cc.startX == (x) && cc.startY == (y))
				|| (cc.endX == (x) && cc.endY == (y))
				|| (cc.middleX == (x) && cc.middleY == (y)))
				{
					return cc;
				}
			}
		}
		
		return null;
	}
	
	public car getSelectedCar()
	{
		for(car cc : cars)
		{
			if(cc.selectedState == 1)
				return cc;
		}
		return null;
	}

	public void printBoard()
	{
		for(boardLine bl : boardLines) //test and see if we initialized our board successfully
		{
			printf(bl.rowNumber + ": " + bl.rowLine + "\n");
		}

		printf("\n");

		for(car cc : cars)	//we need to show the covered "squares" of each car, plus extra info
		{
			if(cc.length == 2)
				printf("Car " + cc.carTitle + ", length: " + cc.length + ", orientation: " + cc.orientation + ", Start(" + cc.startX + "," + cc.startY + "), End(" + cc.endX + "," + cc.endY + ")\n");
			else if(cc.length == 3)
				printf("Car " + cc.carTitle + ", length: " + cc.length + ", orientation: " + cc.orientation + ", Start(" + cc.startX + "," + cc.startY + "), Middle(" + cc.middleX + "," + cc.middleY + "), End(" + cc.endX + "," + cc.endY + ")\n");
		}
		
		printf("	boardsize: " + boardSize+"\n");
	}
}
