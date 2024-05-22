//****************************************************************************************************************************
//Program name: "Payroll System".  This program calculates regular pay, overtime pay and gross pay of and employee base on   *
//user-entered data using a simple UI with three action buttons. Copyright (C) 2021 Brian Y.                                 *
//****************************************************************************************************************************

//********** AUTHOR INFORMATION **********************************************************************
//  Author Name: Brian Y
//  Author E-mail: ***

//********** PROGRAM INFORMATION **********************************************************************
//  Program name: Payroll System
//  Programming Language: Java
//  Files: testPayroll.java, payrollFrame.java, arithmeticOperations.java and run.sh
//  Date Project Began: 02/01/2021
//  Date of Last Update: 02/11/2021
//  Status: Finished; testing completed.
//  Purpose: This program demonstrate the design of a simple GUI (graphical user interface) where the only implemented functions are 
//  addition, multiplication, subtraction and division of doubles. Also, this program demonstrates the use of multiple source files as one program. 
//  Feature: If no values or wrong values are entered into the input boxes then the word "Error" is shown in fron of the corresponding label.
//  Base Test System: WSL with Ubunto and openjdk-14-jdk, and VcXsrv for remote graphical represention in windows 

//********** FILE INFORMATION **********************************************************************
//  File Name: testPayroll.java
//  Compile: javac testPayroll.java
//  This is the top level class. This class activates the graphical user interface.

//********** MAIN CODE AREA **********************************************************************
import javax.swing.JFrame;

public class testPayroll
{
  public static void main(String[] args)
    {
      payrollFrame mainFrame = new payrollFrame();
      mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      mainFrame.setSize(400, 550);
      mainFrame.setVisible(true);
      mainFrame.setResizable(false);
    } //end of main
} //end of testPayroll class