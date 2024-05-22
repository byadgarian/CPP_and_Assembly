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
//  File Name: DiamondUI.java
//  Compile: javac DiamondUI.java
//  Purpose: This class defines the graphical user interface. This class is called from the Diamond class.

//********** MAIN CODE AREA **********************************************************************
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.Timer;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.BorderFactory;

public class diamondUI extends JFrame     //main UI class
{
   private double ballSpeedPixPerSec;
   private double ballSpeedPixPerTik;
   private JPanel panel1;
   private diamondPanel diamond;    //this is panel2 (goes between panel1 and panel3)
   private JPanel panel3;
   private computations compute;    //mathematical calculations class
   private JLabel programLabel1;
   private JLabel speedLabel;
   private JTextField speedTextField;
   private JButton startButton;
   private JButton quitButton;
   private Timer refreshClock;
   private Timer motionClock;
   private double refreshFreq = 120.00;    //repaints in one second (Hz)
   private int refreshPeriod;              //how long each refresh takes (ms)
   private double motionFreq = 100.00;     //movements in one second (Hz)
   private int motionPeriod;               //how long each movement takes (ms)
   private ButtonHandler btnHandler;
   private ClockHandler clkHandler;
   private double lineLength;
   private double Δx;
   private double Δy;
   private double L1x1 = 1528.0;    //hard-coded line coordinates (same as start and end ball coordinates)
   private double L1y1 = 400.0;
   private double L1x2 = 864.0;
   private double L1y2 = 50.0;
   private double L2x1 = 864.0;
   private double L2y1 = 50.0;
   private double L2x2 = 200.0;
   private double L2y2 = 400.0;
   private double L3x1 = 200.0;
   private double L3y1 = 400.0;
   private double L3x2 = 864.0;
   private double L3y2 = 725.0;
   private double L4x1 = 864.0;
   private double L4y1 = 725.0;
   private double L4x2 = 1528.0;
   private double L4y2 = 400.0;
   private int activeLine = 1;
   private boolean endOfCurrentLine;

   public diamondUI()    //main UI constructor
   {
      super("Diamond Animation");
      setLayout(new GridBagLayout());
      GridBagConstraints constraint1 = new GridBagConstraints();

      //---------- Panel 1 ----------------------------------------------------------------------
      constraint1.weightx = 1;
      constraint1.weighty = 0;
      constraint1.fill = GridBagConstraints.BOTH;
      constraint1.gridx = 1;
      constraint1.gridy = 1;
      panel1 = new JPanel();
      panel1.setLayout(new GridBagLayout());
      GridBagConstraints constraint2 = new GridBagConstraints();
      constraint2.ipady = 30;
      panel1.setBackground(Color.green);
      add(panel1, constraint1);

      //---------- Panel 1 / Object 1 ----------------------------------------------------------------------
      programLabel1 = new JLabel("Diamond Animation by Brian Y");
      programLabel1.setForeground(Color.black);
      constraint2.gridx = 1;
      constraint2.gridy = 1;
      panel1.add(programLabel1, constraint2);

      //---------- Panel 2 ----------------------------------------------------------------------
      constraint1.weightx = 1;
      constraint1.weighty = 1;
      constraint1.gridx = 1;
      constraint1.gridy = 2;
      diamond = new diamondPanel();
      diamond.setLayout(new GridBagLayout());
      diamond.setBackground(Color.black);
      add(diamond, constraint1);

      //---------- Panel 3 ----------------------------------------------------------------------
      constraint1.weightx = 1;
      constraint1.weighty = 0;
      constraint1.gridx = 1;
      constraint1.gridy = 3;
      panel3 = new JPanel();
      panel3.setLayout(new GridBagLayout());
      GridBagConstraints constraint4 = new GridBagConstraints();
      constraint4.ipadx = 10;
      constraint4.weightx = 1;
      panel3.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
      panel3.setBackground(Color.green);
      add(panel3, constraint1);

      //---------- Panel 3 / Object 1 ----------------------------------------------------------------------
      startButton = new JButton("Start");
      constraint4.gridx = 1;
      constraint4.gridy = 1;
      panel3.add(startButton, constraint4);

      //---------- Panel 3 / Object 2 ----------------------------------------------------------------------
      speedLabel = new JLabel("Speed (Pix/Sec):");
      speedLabel.setBackground(Color.black);
      constraint4.weightx = 0;
      constraint4.gridx = 2;
      constraint4.gridy = 1;
      panel3.add(speedLabel, constraint4);

      //---------- Panel 3 / Object 3 ----------------------------------------------------------------------
      speedTextField = new JTextField(10);
      constraint4.gridx = 3;
      constraint4.gridy = 1;
      panel3.add(speedTextField, constraint4);
      speedTextField.setText("80.0");

      //---------- Panel 3 / Object 4 ----------------------------------------------------------------------
      quitButton = new JButton("    Quit    ");
      constraint4.weightx = 1;
      constraint4.gridx = 4;
      constraint4.gridy = 1;
      panel3.add(quitButton, constraint4);

      //---------- Buttons, Clocks ----------------------------------------------------------------------
      btnHandler = new ButtonHandler();
      startButton.addActionListener(btnHandler);
      quitButton.addActionListener(btnHandler);
      clkHandler = new ClockHandler();
      refreshPeriod = (int)Math.round(1000/refreshFreq);    //each refresh takes this long
      refreshClock = new Timer(refreshPeriod, clkHandler);  //the clock ticking interval is set to refreshPeriod (repaint time interval)
      motionPeriod = (int)Math.round(1000/refreshFreq);     //each repaint takes this long (ms)
      motionClock = new Timer(motionPeriod, clkHandler);    //the clock ticking interval is set to motionPeriod (movement time interval)

      //---------- Final Steps ----------------------------------------------------------------------
      compute = new computations();
      //setLocationRelativeTo(null);      //location set based on screen resolution in diamond.java
      setVisible(true);
      diamond.initialize(Δx, Δy, L1x1, L1y1, L1x2, L1y2);  //intital paint (no movement yet; Δx and Δy are zero)
   }  //end of diamondUI constructor

   private class ButtonHandler implements ActionListener
   {
      public void actionPerformed(ActionEvent event)     //member function of the implemented class
      {
         if(event.getSource() == startButton)
         {
            if(startButton.getText() == "Pause")
            {
               refreshClock.stop();
               motionClock.stop();
               startButton.setText("Resume");
            }
            else if(startButton.getText() == "Resume")
            {
               refreshClock.start();
               motionClock.start();
               startButton.setText("Pause");
            }
            else
            {
               ballSpeedPixPerSec = Double.parseDouble(speedTextField.getText());
               ballSpeedPixPerTik = ballSpeedPixPerSec/motionFreq;
               Δx = compute.calcDeltaX(L1x1, L1x2, L1y1, L1y2, ballSpeedPixPerTik);
               Δy = compute.calcDeltaY(L1x1, L1x2, L1y1, L1y2, ballSpeedPixPerTik);
               diamond.initialize(Δx, Δy, L1x1, L1y1, L1x2, L1y2);

               refreshClock.start();
               motionClock.start();
               startButton.setText("Pause");
            }
         }
         else if(event.getSource() == quitButton)
         {
            System.exit(0);
         }
      }
   }   //end of ButtonHandler class (nested as private class in diamondUI)

   private class ClockHandler implements ActionListener
   {
      public void actionPerformed(ActionEvent event)     //member function of the implemented class
      {
         endOfCurrentLine = false;
         if(event.getSource() == refreshClock)
         {
            diamond.repaint();
         }
         else if(event.getSource() == motionClock)
         {
            endOfCurrentLine = diamond.moveBall();

            if (endOfCurrentLine)
            {
               activeLine++;
               if(activeLine == 2)
               {
                  motionClock.stop();
                  Δx = compute.calcDeltaX(L2x1, L2x2, L2y1, L2y2, ballSpeedPixPerTik);
                  Δy = compute.calcDeltaY(L2x1, L2x2, L2y1, L2y2, ballSpeedPixPerTik);
                  diamond.initialize(Δx, Δy, L2x1, L2y1, L2x2, L2y2);
                  motionClock.start();
               }
               else if(activeLine == 3)
               {
                  motionClock.stop();
                  Δx = compute.calcDeltaX(L3x1, L3x2, L3y1, L3y2, ballSpeedPixPerTik);
                  Δy = compute.calcDeltaY(L3x1, L3x2, L3y1, L3y2, ballSpeedPixPerTik);
                  diamond.initialize(Δx, Δy, L3x1, L3y1, L3x2, L3y2);
                  motionClock.start();
               }
               else if(activeLine == 4)
               {
                  motionClock.stop();
                  Δx = compute.calcDeltaX(L4x1, L4x2, L4y1, L4y2, ballSpeedPixPerTik);
                  Δy = compute.calcDeltaY(L4x1, L4x2, L4y1, L4y2, ballSpeedPixPerTik);
                  diamond.initialize(Δx, Δy, L4x1, L4y1, L4x2, L4y2);
                  motionClock.start();
               }
               else if(activeLine > 4)
               {
                  refreshClock.stop();
                  motionClock.stop();
                  activeLine = 1;
                  startButton.setText("Start");
               }
            }
         }
      }
   }  //end of ClockHandler class (nested as private class in diamondUI)
}  //end of diamondUI class
