//****************************************************************************************************************************
//Program name: Tom & Jerry                                                                                                  *
//This program shows a small disk that bounces back from the 4 walls of a rectangular panel and a bigger disk that follows   *
//the smaller disk. The user initiates the animation at a given angle for the smaller disk and with a given speed for each   *
//disk. The animation continues until the bigger disk catches up with the smaller disk.                                      *
//Copyright (C) 2021 Brian Y.                                                                                                *
//****************************************************************************************************************************

//********** AUTHOR INFORMATION **********************************************************************
//  Author Name: Brian Y
//  Author E-mail: ***

//********** PROGRAM INFORMATION **********************************************************************
//  Program name: Tom & Jerry
//  Purpose: This program shows a small disk that bounces back from the 4 walls of a rectangular panel and a bigger disk that
//  follows the smaller disk. The user initiates the animation at a given angle for the smaller disk and with a given speed for
//  each disk. The animation continues until the bigger disk catches up with the smaller disk.
//  Programming Language: Java
//  Files: tomNjerry.java, tomNjerryPanel.java, tomNjerryUI.java, Computations.java and run.sh
//  Date Project Began: 04/05/2021
//  Date of Last Update: 04/18/2021
//  Status: Finished (testing completed)
//  Base Test System: WSL with Ubunto, openjdk-14-jdk compiler, and VcXsrv (remote GUI for windows 10)

//********** FILE INFORMATION **********************************************************************
//  File Name: tomNjerryPanel.java
//  Compile: javac tomNjerryPanel.java
//  Purpose: This class defines a special pannel which is an extension of JPanel and is used to contain the graphics.
//           This class is called from the tomNjerryUI class.

//********** MAIN CODE AREA **********************************************************************
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Color;

public class tomNjerryPanel extends JPanel     //rectangle panel class
{
     private computations compute;             //mathematical calculations class
     private double jerryRadious = 7.0;
     private double jerryDiameter = 2.0 * jerryRadious;
     private double tomRadious = 14.0;
     private double tomDiameter = 2.0 * tomRadious;
     private double jerryCenterX;
     private double jerryCenterY;
     private double tomCenterX;
     private double tomCenterY;
     private int jerryIntCenterX;
     private int jerryIntCenterY;
     private int tomIntCenterX;
     private int tomIntCenterY;
     private double jerrySpeedPixPerTic;
     private double tomSpeedPixPerTic;
     private double jerryΔx;
     private double jerryΔy;
     private double tomΔx;
     private double tomΔy;
     private double tomAndjerryDistance;
     private double distanceFromNorthWall;
     private double distanceFromSouthWall;
     private double distanceFromWesthWall;
     private double distanceFromEasthWall;
     private double panelHeight;
     private double panelWidth;
     private boolean showDiamond = false;

     public tomNjerryPanel()    //rectangle panel constructor
     {
          compute = new computations();
          setVisible(true);
     }    //end of the rectangle panel constructor

     public void paintComponent(Graphics graphics)     //JPanel member funciton
     {
          super.paintComponent(graphics);
          if(showDiamond)
          {
               graphics.setColor(Color.black);         //line color (invisible)
               graphics.drawLine(1528,400,864,50);     //invisible lines to eliminate jitter (I don't know why lack of these drawings causes jitter but I'm assuming it has to do with increased taime lapse between repaints)
               graphics.drawLine(864,50,200,400);
               graphics.drawLine(200,400,864,725);
               graphics.drawLine(864,725,1528,400);
               graphics.setColor(Color.yellow);         //Jerry's color
               graphics.fillOval(jerryIntCenterX, jerryIntCenterY, (int)Math.round(jerryDiameter), (int)Math.round(jerryDiameter));
               graphics.setColor(Color.white);          //Tom's color
               graphics.fillOval(tomIntCenterX, tomIntCenterY, (int)Math.round(tomDiameter), (int)Math.round(tomDiameter));
          }
     }    //end of the paintComponent function

     public void initialize(double width, double height, double jerryStartX, double jerryStartY, double jerryDeltaX,
                            double jerryDeltaY, double jerrySpeed, double tomDeltaX, double tomDeltaY, double tomSpeed)     //new member function added to the extended class
     {
          panelHeight = height;
          panelWidth = width;
          jerryCenterX = jerryStartX;
          jerryCenterY = jerryStartY;
          tomCenterX = 0;
          tomCenterY = 0;
          jerrySpeedPixPerTic = jerrySpeed;
          tomSpeedPixPerTic = tomSpeed;
          jerryΔx = jerryDeltaX;
          jerryΔy = jerryDeltaY;
          tomΔx = tomDeltaX;
          tomΔy = tomDeltaY;
          
          jerryIntCenterX = (int)Math.round(jerryCenterX);
          jerryIntCenterY = (int)Math.round(jerryCenterY);
          tomIntCenterX = (int)Math.round(tomCenterX);
          tomIntCenterY = (int)Math.round(tomCenterY);

          distanceFromNorthWall = jerryCenterY;
          distanceFromSouthWall = panelHeight - jerryCenterY;
          distanceFromWesthWall = jerryCenterX;
          distanceFromEasthWall = panelWidth - jerryCenterX;
          tomAndjerryDistance = compute.distance(tomCenterX, tomCenterY, jerryCenterX, jerryCenterY);

          showDiamond = true;
     }    //end of the initialize function

     public void jerryMove()     //new member function added to the extended class
     {
          if(jerryΔy > 0 & jerryΔy > distanceFromSouthWall)
          {
               jerryCenterY = panelHeight - jerryRadious;
               jerryΔy = -jerryΔy;
          }
          else if(jerryΔy < 0 & jerryΔy > distanceFromNorthWall)
          {
               jerryCenterY = 0 + jerryRadious;
               jerryΔy = -jerryΔy;
          }
          else if(jerryΔx > 0 & jerryΔx > distanceFromEasthWall)
          {
               jerryCenterX = panelWidth - jerryRadious;
               jerryΔx = -jerryΔx;
          }
          else if(jerryΔx < 0 & jerryΔx > distanceFromWesthWall)
          {
               jerryCenterX = 0 + jerryRadious;
               jerryΔx = -jerryΔx;
          }
          else
          {
               jerryCenterX += jerryΔx;
               jerryCenterY += jerryΔy;
          }

          jerryIntCenterX = (int)Math.round(jerryCenterX);
          jerryIntCenterY = (int)Math.round(jerryCenterY);

          distanceFromNorthWall = jerryCenterY;
          distanceFromSouthWall = panelHeight - jerryCenterY;
          distanceFromWesthWall = jerryCenterX;
          distanceFromEasthWall = panelWidth - jerryCenterX;
     }    //end of the jerryMove function

     public void tomMove()     //new member function added to the extended class
     {    
          if (tomSpeedPixPerTic > getDistance())
          {
               tomΔx = ((jerryCenterX - tomCenterX) * getDistance()) / (getDistance() - 14.0 - 7.0);
               tomΔy = ((jerryCenterY - tomCenterY) * getDistance()) / (getDistance() - 14.0 - 7.0);
          }
          else
          {
               tomΔx = compute.tomCalcDeltaX(tomSpeedPixPerTic, tomCenterX, tomCenterY, jerryCenterX, jerryCenterY);
               tomΔy = compute.tomCalcDeltaY(tomSpeedPixPerTic, tomCenterX, tomCenterY, jerryCenterX, jerryCenterY);
          }
          tomCenterX += tomΔx;
          tomCenterY += tomΔy;
          tomIntCenterX = (int)Math.round(tomCenterX);
          tomIntCenterY = (int)Math.round(tomCenterY);
     }    //end of the tomMove function

     // public void tomMoveSpecial()     //new member function added to the extended class
     // {
     //      tomΔx = ((jerryCenterX - tomCenterX) * getDistance()) / (getDistance() - 14.0 - 7.0);
     //      tomΔy = ((jerryCenterY - tomCenterY) * getDistance()) / (getDistance() - 14.0 - 7.0);
     //      tomCenterX += tomΔx;
     //      tomCenterY += tomΔy;
     //      tomIntCenterX = (int)Math.round(tomCenterX);
     //      tomIntCenterY = (int)Math.round(tomCenterY);
     // }    //end of the tomMove function

     public double getDistance()     //new member function added to the extended class
     {
          tomAndjerryDistance = compute.distance(tomCenterX, tomCenterY, jerryCenterX, jerryCenterY);
          return tomAndjerryDistance;
     }
}    //end of the getDistance class