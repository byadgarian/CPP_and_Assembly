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
//  File Name: Computations.java
//  Compile: javac Computations.java
//  Purpose: This class performs mathematical operations to calculate Δx and Δy.

//********** MAIN CODE AREA **********************************************************************
public class computations       //mathematical computations class
{
    private double Δx;
    private double Δy;
    private double θ;

    public double calcDeltaX(double angle, double ballSpeed)
    {
        θ =  Math.toRadians(angle);
        Δx = Math.cos(θ) * ballSpeed;
        return Δx;
    }

    public double calcDeltaY(double angle, double ballSpeed)
    {
        θ =  Math.toRadians(angle);
        Δy = Math.sin(θ) * ballSpeed;
        return Δy;
    }
}   //end of the computations class