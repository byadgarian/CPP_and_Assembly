//****************************************************************************************************************************
//Program name: Diamond Animation                                                                                            *
//This program shows a disk that moves along a diamond-shaped path at a speed given by the user. It features one             *
//start/pause/resume button, a text field to enter the speed in pix/second, and a quit button.                               *
//Copyright (C) 2021 Brian Y.                                                                                                *
//****************************************************************************************************************************

//********** AUTHOR INFORMATION **********************************************************************
//  Author Name: Brian Y
//  Author E-mail: ***

//********** PROGRAM INFORMATION **********************************************************************
//  Program name: Diamond Animation
//  Programming Language: Java
//  Files: Diamond.java, DiamondPanel.java, DiamondUI.java, Computations.java and run.sh
//  Date Project Began: 02/20/2021
//  Date of Last Update: 03/03/2021
//  Status: Finished (testing completed)
//  Base Test System: WSL with Ubunto, openjdk-14-jdk compiler, and VcXsrv for remote GUI in windows

//********** FILE INFORMATION **********************************************************************
//  File Name: DiamondPanel.java
//  Compile: javac DiamondPanel.java
//  Purpose: This class defines a special pannel which is an extension of JPanel and is used to contain the graphics.
//           This class is called from the DiamondUI class.

//********** MAIN CODE AREA **********************************************************************
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Color;

public class diamondPanel extends JPanel     //diamond panel class
{
     private double ballRadious = 7.0;
     private double ballDiameter = 2.0 * ballRadious;
     private double ballCenterX;
     private double ballCenterY;
     private double newBallCenterX;
     private double newBallCenterY;
     private int IntNewBallCenterX;
     private int IntNewBallCenterY;
     private double x1, y1, x2, y2;
     private int i1,j1,i2,j2;
     private double Δx;
     private double Δy;
     private double distanceFromEnd;
     private double distanceMoved;
     private boolean endOfLine;
     private boolean showDiamond = false;

     public diamondPanel()    //diamond panel constructor
     {
          setVisible(true);
     }    //end of the dimond panel constructor

     public void paintComponent(Graphics graphics)     //JPanel member funciton
     {
          super.paintComponent(graphics);
          if(showDiamond)
          {
               graphics.setColor(Color.green);         //line color
               graphics.drawLine(1528,400,864,50);     //hard-coded line coordinates (same as start and end ball coordinates)
               graphics.drawLine(864,50,200,400);
               graphics.drawLine(200,400,864,725);
               graphics.drawLine(864,725,1528,400);
               graphics.setColor(Color.white);         //ball color
               graphics.fillOval(IntNewBallCenterX, IntNewBallCenterY, (int)Math.round(ballDiameter), (int)Math.round(ballDiameter));
          }
     }    //end of the paintComponent function

     public void initialize(double deltaX, double deltaY, double startX, double startY, double endX, double endY)  //new member function added to the extended class
     {
          x1 = startX;
          y1 = startY;
          x2 = endX;
          y2 = endY;
          Δx = deltaX;
          Δy = deltaY;
          ballCenterX = x1;
          ballCenterY = y1;
          distanceMoved = Math.sqrt(Δx*Δx + Δy*Δy);
          newBallCenterX = ballCenterX - ballRadious;
          newBallCenterY = ballCenterY - ballRadious;
          IntNewBallCenterX = (int)Math.round(newBallCenterX);
          IntNewBallCenterY = (int)Math.round(newBallCenterY);
          distanceFromEnd = Math.sqrt(Math.pow(ballCenterX - x2, 2) + Math.pow(ballCenterY - y2, 2));
          showDiamond = true;
     }    //end of the initialize function

     public boolean moveBall()     //new member function added to the extended class
     {
          endOfLine = false;
          if(distanceFromEnd > distanceMoved)
          {
               ballCenterX += Δx;
               ballCenterY += Δy;
          }
          else
          {
               ballCenterX = x2;
               ballCenterY = y2;
               endOfLine = true;
          }
          newBallCenterX = ballCenterX - ballRadious;
          newBallCenterY = ballCenterY - ballRadious;
          IntNewBallCenterX = (int)Math.round(newBallCenterX);
          IntNewBallCenterY = (int)Math.round(newBallCenterY);
          distanceFromEnd = Math.sqrt(Math.pow(ballCenterX - x2, 2) + Math.pow(ballCenterY - y2, 2));
          return endOfLine;
     }    //end of the moveBall function
}    //end of the diamondPanel class
