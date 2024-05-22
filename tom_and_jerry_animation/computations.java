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
//  File Name: Computations.java
//  Compile: javac Computations.java
//  Purpose: This class performs mathematical operations.

//********** MAIN CODE AREA **********************************************************************
public class computations       //mathematical computations class
{
    private double jerryΔx;
    private double jerryΔy;
    private double tomΔx;
    private double tomΔy;
    private double θ;
    private double distance;
    private double distance1;
    private double distance2;

    public double jerryCalcDeltaX(double angle, double jerrySpeed)
    {
        θ =  Math.toRadians(angle);
        jerryΔx = Math.cos(θ) * jerrySpeed;
        return jerryΔx;
    }

    public double jerryCalcDeltaY(double angle, double jerrySpeed)
    {
        θ =  Math.toRadians(angle);
        jerryΔy = Math.sin(θ) * jerrySpeed;
        return jerryΔy;
    }

    public double tomCalcDeltaX(double tomSpeed, double tomCenterX, double tomCenterY, double jerryCenterX,
                                double jerryCenterY)
    {
        distance = Math.sqrt(Math.pow((tomCenterX - jerryCenterX), 2) + Math.pow((tomCenterY - jerryCenterY), 2));
        tomΔx = tomSpeed * (jerryCenterX - tomCenterX)/distance;
        return tomΔx;
    }

    public double tomCalcDeltaY(double tomSpeed, double tomCenterX, double tomCenterY, double jerryCenterX,
                                double jerryCenterY)
    {
        distance = Math.sqrt(Math.pow((tomCenterX - jerryCenterX), 2) + Math.pow((tomCenterY - jerryCenterY), 2));
        tomΔy = tomSpeed * (jerryCenterY - tomCenterY)/distance;
        return tomΔy;
    }

    public double distance(double tomCenterX, double tomCenterY, double jerryCenterX, double jerryCenterY)
    {
        distance = Math.sqrt(Math.pow((jerryCenterX - tomCenterX), 2) + Math.pow((jerryCenterY - tomCenterY), 2));
        distance = distance - 14.0 - 7.0;   //14 = the radious of Tom, 7 = the radious of Jerry
        return distance;
    }
}   //end of the computations class