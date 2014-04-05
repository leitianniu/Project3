import java.util.*;
import java.io.*;
import java.lang.Math.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/*
	Car class
*/	
public class car extends gridLockGUI
{
	char carTitle;		//A,B,C...or P for player's car
	String orientation; //vertical or horizontal
	int length; 	   //the length of the car

	int startX;	//for the coordinates of the car
	int startY;

	int middleX;	//for a car of length 3
	int middleY;

	int endX;
	int endY;

	int selectedState;
	
	Color colorOfCar;
}
