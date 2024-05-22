//****************************************************************************************************************************
//Program name: Ricochet Ball                                                                                                *
//This program shows a disk that bounces back from the 4 walls of a rectangular panel. The user initiates the animation at   *
//the given angle and with the given speed. The animation continues infinitely until paused or stopped by the user.          *
//Copyright (C) 2021 Brian Y.                                                                                                *
//****************************************************************************************************************************

//********** AUTHOR INFORMATION **********************************************************************
//  Author Name: Brian Y
//  Author E-mail: ***

//********** PROGRAM INFORMATION **********************************************************************
//  Program name: Ricochet Ball
//  Purpose: This program shows a disk that bounces back from the 4 walls of a rectangular panel. The user initiates the
//  animation at the given angle and with the given speed. The animation continues infinitely until paused or stopped by the user.
//  Programming Language: Java
//  Files: ricochetBall.java, ricochetBallPanel.java, ricochetBallUI.java, Computations.java and run.sh
//  Date Project Began: 03/27/2021
//  Date of Last Update: 03/28/2021
//  Status: Finished (testing completed)
//  Base Test System: WSL with Ubunto, openjdk-14-jdk compiler, and VcXsrv (remote GUI in windows 10)

//********** FILE INFORMATION **********************************************************************
//  File Name: ricochetBallPanel.java
//  Compile: javac ricochetBallPanel.java
//  Purpose: This class defines a special pannel which is an extension of JPanel and is used to contain the graphics.
//           This class is called from the ricochetBallUI class.

//********** MAIN CODE AREA **********************************************************************
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Color;

public class ricochetBallPanel extends JPanel     //ricochetBall panel class
{
     private double ballRadious = 7.0;
     private double ballDiameter = 2.0 * ballRadious;
     private double ballCenterX;
     private double ballCenterY;
     private double newBallCenterX;
     private double newBallCenterY;
     private int IntNewBallCenterX;
     private int IntNewBallCenterY;
     private double Δx;
     private double Δy;
     private double distanceFromNorthWall;
     private double distanceFromSouthWall;
     private double distanceFromWesthWall;
     private double distanceFromEasthWall;
     private double panelHeight;
     private double panelWidth;
     private double ballSpeedPixPerTic;
     private boolean showDiamond = false;

     public ricochetBallPanel()    //ricochetBall panel constructor
     {
          setVisible(true);
     }    //end of the ricochetBall panel constructor

     public void paintComponent(Graphics graphics)     //JPanel member funciton
     {
          super.paintComponent(graphics);
          if(showDiamond)
          {
               graphics.setColor(Color.black);         //line color (invisible)
               graphics.drawLine(1528,400,864,50);     //invisible lines to eliminate jitter (I don't know why lack of these drawings causes jitter but I'm assuming it has to do with increased time lapse between repaints)
               graphics.drawLine(864,50,200,400);
               graphics.drawLine(200,400,864,725);
               graphics.drawLine(864,725,1528,400);
               graphics.setColor(Color.white);         //ball color
               graphics.fillOval(IntNewBallCenterX, IntNewBallCenterY, (int)Math.round(ballDiameter), (int)Math.round(ballDiameter));
          }
     }    //end of the paintComponent function

     public void initialize(double width, double height, double startX, double startY, double deltaX, double deltaY, double ballSpeed)  //new member function added to the extended class
     {
          panelHeight = height;
          panelWidth = width;
          ballCenterX = startX;
          ballCenterY = startY;
          Δx = deltaX;
          Δy = deltaY;
          ballSpeedPixPerTic = ballSpeed;

          distanceFromNorthWall = ballCenterY;
          distanceFromSouthWall = panelHeight - ballCenterY;
          distanceFromWesthWall = ballCenterX;
          distanceFromEasthWall = panelWidth - ballCenterX;
          // newBallCenterX = ballCenterX - ballRadious;  //for the corner of the ball
          // newBallCenterY = ballCenterY - ballRadious;
          newBallCenterX = ballCenterX;
          newBallCenterY = ballCenterY;
          IntNewBallCenterX = (int)Math.round(newBallCenterX);
          IntNewBallCenterY = (int)Math.round(newBallCenterY);

          showDiamond = true;
     }    //end of the initialize function

     public void moveBall()     //new member function added to the extended class
     {
          if(Δy > 0 & Δy > distanceFromSouthWall)           //alternatively use ballSpeedPixPerTic
          {
               ballCenterY = panelHeight - ballRadious;
               Δy = -Δy;
          }
          else if(Δy < 0 & Δy > distanceFromNorthWall)      //alternatively use ballSpeedPixPerTic
          {
               ballCenterY = 0 + ballRadious;
               Δy = -Δy;
          }
          else if(Δx > 0 & Δx > distanceFromEasthWall)      //alternatively use ballSpeedPixPerTic
          {
               ballCenterX = panelWidth - ballRadious;
               Δx = -Δx;
          }
          else if(Δx < 0 & Δx > distanceFromWesthWall)      //alternatively use ballSpeedPixPerTic
          {
               ballCenterX = 0 + ballRadious;
               Δx = -Δx;
          }
          else
          {
               ballCenterX += Δx;
               ballCenterY += Δy;
          }
          // newBallCenterX = ballCenterX - ballRadious;    //for the corner of the ball
          // newBallCenterY = ballCenterY - ballRadious;
          newBallCenterX = ballCenterX;
          newBallCenterY = ballCenterY;
          IntNewBallCenterX = (int)Math.round(newBallCenterX);
          IntNewBallCenterY = (int)Math.round(newBallCenterY);

          distanceFromNorthWall = ballCenterY;
          distanceFromSouthWall = panelHeight - ballCenterY;
          distanceFromWesthWall = ballCenterX;
          distanceFromEasthWall = panelWidth - ballCenterX;
     }    //end of the moveBall function
     
     public double getBallCenterX()     //new member function added to the extended class
     {
          return ballCenterX;
     }
     
     public double getBallCenterY()     //new member function added to the extended class
     {
          return ballCenterY;
     }
}    //end of the ricochetBall panel class