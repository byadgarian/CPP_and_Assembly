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
//  File Name: Computations.java
//  Compile: javac Computations.java
//  Purpose: This class performs mathematical operations to calculate Δx and Δy.

//********** MAIN CODE AREA **********************************************************************
public class computations       //mathematical computations class
{
    private double Δx;
    private double Δy;
    private double lineLength;

    public double calcDeltaX(double lineX1, double lineX2, double lineY1, double lineY2, double ballSpeed)
    {
        lineLength = Math.sqrt(Math.pow((lineX2 - lineX1), 2) + Math.pow((lineY2 - lineY1), 2));
        Δx = ballSpeed * (lineX2 - lineX1) / lineLength;
        return Δx;
    }

    public double calcDeltaY(double lineX1, double lineX2, double lineY1, double lineY2, double ballSpeed)
    {
        lineLength = Math.sqrt(Math.pow((lineX2 - lineX1), 2) + Math.pow((lineY2 - lineY1), 2));
        Δy = ballSpeed * (lineY2 - lineY1) / lineLength;
        return Δy;
    }
}   //end of the computations class