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
//  File Name: Diamond.java
//  Compile: javac Diamond.java
//  Purpose: This is the top level class. This class activates the graphical user interface.

//********** MAIN CODE AREA **********************************************************************
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Toolkit;

public class diamond        //driver class
{    
    public static void main(String[] args)
    {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        JFrame mainFrame = new diamondUI();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int screenWidth = (int)Math.round(screenSize.getWidth());
        int screenHeight = (int)Math.round(screenSize.getHeight());
        int frameWidth = (int)Math.round(screenWidth * 0.9);
        int frameHight = (int)Math.round(screenHeight * 0.85);
        mainFrame.setSize(frameWidth, frameHight);
        //mainFrame.setSize(1440, 810);     //for hard-coded frame dimensions
        mainFrame.setLocation((int)Math.round(screenWidth/2) - frameWidth/2, (int)Math.round(screenHeight/2) - frameHight/2);
        mainFrame.setResizable(true);
        mainFrame.setVisible(true);
    }   //end of the main function
}   //end of the Diamond class