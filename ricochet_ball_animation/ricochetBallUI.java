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
//  File Name: ricochetBallUI.java
//  Compile: javac ricochetBallUI.java
//  Purpose: This class defines the graphical user interface. This class is called from the ricochetBall class.

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

public class ricochetBallUI extends JFrame     //main UI class
{
   private double ballSpeedPixPerSec;
   private double ballSpeedPixPerTic;
   private JPanel panel1;
   private ricochetBallPanel rectangle;    //this is panel2 (goes between panel1 and panel3)
   private JPanel panel3;
   private JPanel miniPanel;
   private computations compute;    //mathematical calculations class
   private JLabel programLabel1;
   private JLabel refreshRateLabel;
   private JLabel speedLabel;
   private JLabel directionLabel;
   private JLabel locationLable;
   private JLabel xLable;
   private JLabel yLable;
   private JTextField refreshRateTextField;
   private JTextField speedTextField;
   private JTextField directionTextField;
   private JTextField xTextField;
   private JTextField yTextField;
   private JButton clearButton;
   private JButton startButton;
   private JButton quitButton;
   private Timer refreshClock;
   private Timer motionClock;
   private double refreshFreq;             //repaints in one second (Hz)
   private int refreshPeriod;              //how long each refresh takes (ms)
   private double motionFreq = 100.00;     //movements in one second (Hz)
   private int motionPeriod;               //how long each movement takes (ms)
   private ButtonHandler btnHandler;
   private ClockHandler clkHandler;
   private double Δx;
   private double Δy;
   private double direction;
   private double PanelCenterX;
   private double PanelCenterY;
   private double panelHeight;
   private double panelWidth;

   public ricochetBallUI()    //main UI constructor
   {
      super("Ricochet Ball");
      setLayout(new GridBagLayout());
      GridBagConstraints constraint0 = new GridBagConstraints();

      //---------- Panel 1 ----------------------------------------------------------------------
      constraint0.weightx = 1;
      constraint0.weighty = 0;
      constraint0.fill = GridBagConstraints.BOTH;
      constraint0.gridx = 1;
      constraint0.gridy = 1;
      panel1 = new JPanel();
      panel1.setLayout(new GridBagLayout());
      GridBagConstraints constraint1 = new GridBagConstraints();
      constraint1.ipady = 30;
      panel1.setBackground(Color.green);
      add(panel1, constraint0);

      //---------- Panel 1 / Object 1 ----------------------------------------------------------------------
      programLabel1 = new JLabel("Ricochet Ball by Brian Y");
      programLabel1.setForeground(Color.black);
      constraint1.gridx = 1;
      constraint1.gridy = 1;
      panel1.add(programLabel1, constraint1);

      //---------- Panel 2 ----------------------------------------------------------------------
      constraint0.weightx = 1;
      constraint0.weighty = 1;
      constraint0.gridx = 1;
      constraint0.gridy = 2;
      rectangle = new ricochetBallPanel();
      rectangle.setLayout(new GridBagLayout());
      rectangle.setBackground(Color.black);
      add(rectangle, constraint0);

      //---------- Panel 3 ----------------------------------------------------------------------
      constraint0.weightx = 1;
      constraint0.weighty = 0;
      constraint0.gridx = 1;
      constraint0.gridy = 3;
      panel3 = new JPanel();
      panel3.setLayout(new GridBagLayout());
      GridBagConstraints constraint3 = new GridBagConstraints();
      //constraint3.ipadx = 10;
      //constraint3.ipady = 20;
      constraint3.weightx = 1;
      //constraint3.weighty = 1;
      panel3.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
      panel3.setBackground(Color.green);
      add(panel3, constraint0);

      //---------- Panel 3 / Object 1 ----------------------------------------------------------------------
      clearButton = new JButton("Clear");
      constraint3.gridx = 1;
      constraint3.gridy = 1;
      panel3.add(clearButton, constraint3);

      //---------- Panel 3 / Object 2 ----------------------------------------------------------------------
      startButton = new JButton("Start");
      constraint3.gridx = 2;
      constraint3.gridy = 1;
      panel3.add(startButton, constraint3);

      //---------- Panel 3 / Object 3 ----------------------------------------------------------------------
      quitButton = new JButton(" Quit ");
      constraint3.gridx = 3;
      constraint3.gridy = 1;
      panel3.add(quitButton, constraint3);

      //---------- Panel 3 / Object 4 ----------------------------------------------------------------------
      locationLable = new JLabel("Ball Location");
      constraint3.gridx = 4;
      constraint3.gridy = 1;
      //constraint3.ipady = 30;
      panel3.add(locationLable, constraint3);
      //constraint3.ipady = 0;

      //---------- Panel 3 / Object 5 ----------------------------------------------------------------------
      miniPanel = new JPanel();
      constraint3.gridx = 4;
      constraint3.gridy = 2;
      miniPanel.setLayout(new GridBagLayout());
      GridBagConstraints miniConstraint = new GridBagConstraints();
      miniConstraint.ipadx = 10;
      //miniConstraint.ipady = 20;
      //miniConstraint.weightx = 1;
      //miniConstraint.weighty = 1;
      miniPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
      //miniPanel.setBackground
      panel3.add(miniPanel, constraint3);

      xLable = new JLabel("X =");
      miniConstraint.gridx = 1;
      miniConstraint.gridy = 1;
      miniPanel.add(xLable, miniConstraint);

      xTextField = new JTextField(7);
      miniConstraint.gridx = 2;
      miniConstraint.gridy = 1;
      miniPanel.add(xTextField, miniConstraint);
      xTextField.setText("0");

      yLable = new JLabel("Y =");
      miniConstraint.gridx = 1;
      miniConstraint.gridy = 2;
      miniPanel.add(yLable, miniConstraint);

      yTextField = new JTextField(7);
      miniConstraint.gridx = 2;
      miniConstraint.gridy = 2;
      miniPanel.add(yTextField, miniConstraint);
      yTextField.setText("0");

      //---------- Panel 3 / Object 6 ----------------------------------------------------------------------
      refreshRateLabel = new JLabel("Refresh Rate (Hz)");
      refreshRateLabel.setBackground(Color.black);
      constraint3.gridx = 1;
      constraint3.gridy = 3;
      panel3.add(refreshRateLabel, constraint3);

      //---------- Panel 3 / Object 7 ----------------------------------------------------------------------
      speedLabel = new JLabel("Speed (Pix/Sec)");
      speedLabel.setBackground(Color.black);
      constraint3.gridx = 2;
      constraint3.gridy = 3;
      panel3.add(speedLabel, constraint3);

      //---------- Panel 3 / Object 8 ----------------------------------------------------------------------
      directionLabel = new JLabel("Direction (Deg)");
      directionLabel.setBackground(Color.black);
      constraint3.gridx = 3;
      constraint3.gridy = 3;
      panel3.add(directionLabel, constraint3);

      //---------- Panel 3 / Object 9 ----------------------------------------------------------------------
      refreshRateTextField = new JTextField(10);
      constraint3.gridx = 1;
      constraint3.gridy = 4;
      panel3.add(refreshRateTextField, constraint3);
      refreshRateTextField.setText("120.0");

      //---------- Panel 3 / Object 10 ----------------------------------------------------------------------
      speedTextField = new JTextField(10);
      constraint3.gridx = 2;
      constraint3.gridy = 4;
      panel3.add(speedTextField, constraint3);
      speedTextField.setText("80.0");

      //---------- Panel 3 / Object 11 ----------------------------------------------------------------------
      directionTextField = new JTextField(10);
      constraint3.gridx = 3;
      constraint3.gridy = 4;
      panel3.add(directionTextField, constraint3);
      directionTextField.setText("45.0");

      //---------- Buttons & Clocks ----------------------------------------------------------------------
      btnHandler = new ButtonHandler();
      startButton.addActionListener(btnHandler);
      quitButton.addActionListener(btnHandler);
      clearButton.addActionListener(btnHandler);
      clkHandler = new ClockHandler();
      motionPeriod = (int)Math.round(1000/motionFreq);
      motionClock = new Timer(motionPeriod, clkHandler);

      //---------- Final Steps ----------------------------------------------------------------------
      compute = new computations();
      setVisible(true);
   }  //end of main UI constructor

   private class ButtonHandler implements ActionListener
   {
      public void actionPerformed(ActionEvent event)     //member function of the implemented class
      {
         if(event.getSource() == clearButton)
         {  
            PanelCenterX = rectangle.getWidth() / 2;
            PanelCenterY = rectangle.getHeight() / 2;
            rectangle.initialize(panelWidth, panelHeight, PanelCenterX, PanelCenterY, Δx, Δy, ballSpeedPixPerTic);
            rectangle.repaint();
            xTextField.setText(String.valueOf((int)Math.round(rectangle.getBallCenterX())));
            yTextField.setText(String.valueOf((int)Math.round(rectangle.getBallCenterY())));
            refreshClock.stop();
            motionClock.stop();
            startButton.setText("Start");
         }
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
               panelWidth = rectangle.getWidth() - 14;      //-14 pixels to offset the hidden part of the pannel
               panelHeight = rectangle.getHeight() - 14;    //-14 pixels to offset the hidden part of the pannel
               PanelCenterX = panelWidth / 2;
               PanelCenterY = panelHeight / 2;
               direction = -Double.parseDouble(directionTextField.getText());
               ballSpeedPixPerSec = Double.parseDouble(speedTextField.getText());
               ballSpeedPixPerTic = ballSpeedPixPerSec/motionFreq;
               Δx = compute.calcDeltaX(direction, ballSpeedPixPerTic);
               Δy = compute.calcDeltaY(direction, ballSpeedPixPerTic);
               rectangle.initialize(panelWidth, panelHeight, PanelCenterX, PanelCenterY, Δx, Δy, ballSpeedPixPerTic);
               
               refreshFreq = Double.parseDouble(refreshRateTextField.getText());
               refreshPeriod = (int)Math.round(1000/refreshFreq);
               refreshClock = new Timer(refreshPeriod, clkHandler);
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
   }   //end of ButtonHandler class (nested as private class in ricochetBallUI)

   private class ClockHandler implements ActionListener
   {
      public void actionPerformed(ActionEvent event)     //member function of the implemented class
      {
         if(event.getSource() == refreshClock)
         {
            rectangle.repaint();
         }
         else if(event.getSource() == motionClock)
         {
            rectangle.moveBall();
            xTextField.setText(String.valueOf((int)Math.round(rectangle.getBallCenterX())));
            yTextField.setText(String.valueOf((int)Math.round(rectangle.getBallCenterY())));
         }
      }
   }  //end of ClockHandler class (nested as private class in ricochetBallUI)
}  //end of main UI class
