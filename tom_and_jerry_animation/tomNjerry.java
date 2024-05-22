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
//  File Name: tomNjerry.java
//  Compile: javac tomNjerry.java
//  Purpose: This is the top level class. This class activates the graphical user interface.

//********** NOTES **********************************************************************
//  This program has a completely fluid layout, meaning you can adjust the size of the main frame (window) to your liking
//  before pressing the Clear or Start button, and the program should run just as good for any reasonable frame size.
//  I also made sure this program does not accept negative speeds for Tom and Jerry, but it still accepts negative angles.

//********** MAIN CODE AREA **********************************************************************
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Toolkit;

public class tomNjerry      //driver class
{    
    public static void main(String[] args)
    {
        JFrame mainFrame = new tomNjerryUI();
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