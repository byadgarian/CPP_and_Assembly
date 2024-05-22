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
//  File Name: ricochetBall.java
//  Compile: javac ricochetBall.java
//  Purpose: This is the top level class. This class activates the graphical user interface.

//********** MAIN CODE AREA **********************************************************************
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Toolkit;

public class ricochetBall        //driver class
{    
    public static void main(String[] args)
    {
        JFrame mainFrame = new ricochetBallUI();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int)Math.round(screenSize.getWidth());
        int screenHeight = (int)Math.round(screenSize.getHeight());
        int frameWidth = (int)Math.round(screenWidth * 0.9);
        int frameHight = (int)Math.round(screenHeight * 0.85);
        mainFrame.setSize(frameWidth, frameHight);
        mainFrame.setLocation((int)Math.round(screenWidth/2) - frameWidth/2, (int)Math.round(screenHeight/2) - frameHight/2);
        // mainFrame.setLocationRelativeTo(null);
        mainFrame.setResizable(true);
        mainFrame.setVisible(true);
    }   //end of the main function
}   //end of the driver class