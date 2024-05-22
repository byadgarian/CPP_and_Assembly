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
//  File Name: tomNjerryUI.java
//  Compile: javac tomNjerryUI.java
//  Purpose: This class defines the graphical user interface. This class is called from the tomNjerry class.

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
import java.lang.System.*;                  //system messages for debugging

public class tomNjerryUI extends JFrame     //main UI class
{
   private JPanel panel1;
   private tomNjerryPanel rectangle;        //this is panel2 (goes between panel1 and panel3)
   private JPanel panel3;
   private JPanel miniPanel;
   private JLabel programLabel;
   private JLabel tomSpeedLabel;
   private JLabel jerrySpeedLabel;
   private JLabel jerryDirectionLabel;
   private JLabel distanceLable;
   private JLabel distLable;
   private JTextField tomSpeedTextField;
   private JTextField jerrySpeedTextField;
   private JTextField jerryDirectionTextField;
   private JTextField distTextField;
   private ButtonHandler btnHandler;
   private JButton clearButton;
   private JButton startButton;
   private JButton quitButton;
   private ClockHandler clkHandler;
   private Timer refreshClock;
   private Timer jerryMotionClock;
   private Timer tomMotionClock;
   private double refreshFreq = 120.00;         //repaints in one second (Hz)
   private double jerryMotionFreq = 100.00;     //movements in one second (Hz)
   private double tomMotionFreq = 100.00;       //movements in one second (Hz)
   private int refreshPeriod;                   //how long each refresh takes (ms)
   private int jerryMotionPeriod;               //how long each movement takes (ms)
   private int tomMotionPeriod;                 //how long each movement takes (ms)
   private computations compute;                //mathematical calculations class
   private double tomSpeedPixPerSec;
   private double jerrySpeedPixPerSec;
   private double tomSpeedPixPerTic;
   private double jerrySpeedPixPerTic;
   private double jerryDirection;
   private double jerryΔx;
   private double jerryΔy;
   private double tomΔx;
   private double tomΔy;
   private double tomAndjerryDistance;
   private double PanelCenterX;
   private double PanelCenterY;
   private double panelHeight;
   private double panelWidth;

   public tomNjerryUI()    //main UI constructor
   {
      super("Tom & Jerry");
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
      programLabel = new JLabel("Tom & Jerry by Brian Y");
      programLabel.setForeground(Color.black);
      constraint1.gridx = 1;
      constraint1.gridy = 1;
      panel1.add(programLabel, constraint1);

      //---------- Panel 2 ----------------------------------------------------------------------
      constraint0.weightx = 1;
      constraint0.weighty = 1;
      constraint0.gridx = 1;
      constraint0.gridy = 2;
      rectangle = new tomNjerryPanel();
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
      distanceLable = new JLabel("Distance (Pix)");
      constraint3.gridx = 4;
      constraint3.gridy = 1;
      //constraint3.ipady = 30;
      panel3.add(distanceLable, constraint3);
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

      distLable = new JLabel("Dist =");
      miniConstraint.gridx = 1;
      miniConstraint.gridy = 1;
      miniPanel.add(distLable, miniConstraint);

      distTextField = new JTextField(7);
      miniConstraint.gridx = 2;
      miniConstraint.gridy = 1;
      miniPanel.add(distTextField, miniConstraint);
      distTextField.setText("0");
      //distTextField.setText("");

      //---------- Panel 3 / Object 6 ----------------------------------------------------------------------
      tomSpeedLabel = new JLabel("Tom's Speed (Pix/Sec)");
      tomSpeedLabel.setBackground(Color.black);
      constraint3.gridx = 1;
      constraint3.gridy = 3;
      panel3.add(tomSpeedLabel, constraint3);

      //---------- Panel 3 / Object 7 ----------------------------------------------------------------------
      jerrySpeedLabel = new JLabel("Jerry's Speed (Pix/Sec)");
      jerrySpeedLabel.setBackground(Color.black);
      constraint3.gridx = 2;
      constraint3.gridy = 3;
      panel3.add(jerrySpeedLabel, constraint3);

      //---------- Panel 3 / Object 8 ----------------------------------------------------------------------
      jerryDirectionLabel = new JLabel("Jerry's Direction (Deg)");
      jerryDirectionLabel.setBackground(Color.black);
      constraint3.gridx = 3;
      constraint3.gridy = 3;
      panel3.add(jerryDirectionLabel, constraint3);

      //---------- Panel 3 / Object 9 ----------------------------------------------------------------------
      tomSpeedTextField = new JTextField(10);
      constraint3.gridx = 1;
      constraint3.gridy = 4;
      panel3.add(tomSpeedTextField, constraint3);
      tomSpeedTextField.setText("500.0");
      //tomSpeedTextField.setText("");

      //---------- Panel 3 / Object 10 ----------------------------------------------------------------------
      jerrySpeedTextField = new JTextField(10);
      constraint3.gridx = 2;
      constraint3.gridy = 4;
      panel3.add(jerrySpeedTextField, constraint3);
      jerrySpeedTextField.setText("1000.0");
      //jerrySpeedTextField.setText("");

      //---------- Panel 3 / Object 11 ----------------------------------------------------------------------
      jerryDirectionTextField = new JTextField(10);
      constraint3.gridx = 3;
      constraint3.gridy = 4;
      panel3.add(jerryDirectionTextField, constraint3);
      jerryDirectionTextField.setText("45.0");
      //jerryDirectionTextField.setText("");

      //---------- Buttons & Clocks ----------------------------------------------------------------------
      btnHandler = new ButtonHandler();
      clkHandler = new ClockHandler();
      startButton.addActionListener(btnHandler);
      quitButton.addActionListener(btnHandler);
      clearButton.addActionListener(btnHandler);
      refreshPeriod = (int)Math.round(1000/refreshFreq);
      jerryMotionPeriod = (int)Math.round(1000/jerryMotionFreq);
      tomMotionPeriod = (int)Math.round(1000/tomMotionFreq);
      refreshClock = new Timer(refreshPeriod, clkHandler);
      jerryMotionClock = new Timer(jerryMotionPeriod, clkHandler);
      tomMotionClock = new Timer(tomMotionPeriod, clkHandler);

      //---------- Final Steps ----------------------------------------------------------------------
      compute = new computations();
      setVisible(true);
   }  //end of the constructor

   private class ButtonHandler implements ActionListener
   {
      public void actionPerformed(ActionEvent event)     //member function of the implemented class
      {
         if(event.getSource() == clearButton)
         {  
            PanelCenterX = rectangle.getWidth() / 2;
            PanelCenterY = rectangle.getHeight() / 2;
            rectangle.initialize(panelWidth, panelHeight, PanelCenterX, PanelCenterY, jerryΔx, jerryΔy, jerrySpeedPixPerTic,
                                 tomΔx, tomΔy, tomSpeedPixPerTic);
            rectangle.repaint();
            distTextField.setText(String.valueOf((int)Math.round(rectangle.getDistance())));
            tomSpeedTextField.setText("500.0");
            jerrySpeedTextField.setText("1000.0");
            jerryDirectionTextField.setText("45.0");
            // distTextField.setText("");
            // tomSpeedTextField.setText("");
            // jerrySpeedTextField.setText("");
            // jerryDirectionTextField.setText("");
            refreshClock.stop();
            jerryMotionClock.stop();
            tomMotionClock.stop();
            startButton.setText("Start");
            startButton.setEnabled(true);
         }
         if(event.getSource() == startButton)
         {
            if(startButton.getText() == "Pause")
            {
               refreshClock.stop();
               jerryMotionClock.stop();
               tomMotionClock.stop();
               startButton.setText("Resume");
            }
            else if(startButton.getText() == "Resume")
            {
               refreshClock.start();
               jerryMotionClock.start();
               tomMotionClock.start();
               startButton.setText("Pause");
            }
            else
            {  
               panelWidth = rectangle.getWidth() - 14;      //-14 pixels to offset the hidden part of the pannel
               panelHeight = rectangle.getHeight() - 14;    //-14 pixels to offset the hidden part of the pannel
               PanelCenterX = rectangle.getWidth() / 2;
               PanelCenterY = rectangle.getHeight() / 2;
               jerryDirection = -Double.parseDouble(jerryDirectionTextField.getText());
               jerrySpeedPixPerSec = Double.parseDouble(jerrySpeedTextField.getText());
               if(jerrySpeedPixPerSec < 0)
               {
                  jerrySpeedPixPerSec = Math.abs(jerrySpeedPixPerSec);
                  jerrySpeedTextField.setText(String.valueOf(jerrySpeedPixPerSec));
               }
               jerrySpeedPixPerTic = jerrySpeedPixPerSec/jerryMotionFreq;
               tomSpeedPixPerSec = Double.parseDouble(tomSpeedTextField.getText());
               if(tomSpeedPixPerSec < 0)
               {
                  tomSpeedPixPerSec = Math.abs(tomSpeedPixPerSec);
                  tomSpeedTextField.setText(String.valueOf(tomSpeedPixPerSec));
               }
               tomSpeedPixPerTic = tomSpeedPixPerSec/tomMotionFreq;
               jerryΔx = compute.jerryCalcDeltaX(jerryDirection, jerrySpeedPixPerTic);
               jerryΔy = compute.jerryCalcDeltaY(jerryDirection, jerrySpeedPixPerTic);
               tomΔx = 0;
               tomΔy = 0;
               rectangle.initialize(panelWidth, panelHeight, PanelCenterX, PanelCenterY, jerryΔx, jerryΔy, jerrySpeedPixPerTic,
                                    tomΔx, tomΔy, tomSpeedPixPerTic);
               refreshClock.start();
               jerryMotionClock.start();
               tomMotionClock.start();
               startButton.setText("Pause");
            }
         }
         else if(event.getSource() == quitButton)
         {
            System.exit(0);
         }
      }
   }   //end of ButtonHandler class (nested as private class)

   private class ClockHandler implements ActionListener
   {
      public void actionPerformed(ActionEvent event)     //member function of the implemented class
      {
         if(event.getSource() == refreshClock)
         {
            rectangle.repaint();
         }
         else if(event.getSource() == jerryMotionClock)
         {
            rectangle.jerryMove();
         }
         else if(event.getSource() == tomMotionClock)
         {
            rectangle.tomMove();
            rectangle.repaint();
            tomAndjerryDistance = rectangle.getDistance();
            distTextField.setText(String.valueOf((int)Math.round(tomAndjerryDistance)));
            if (tomAndjerryDistance <= 0)
            {
               refreshClock.stop();
               jerryMotionClock.stop();
               tomMotionClock.stop();
               startButton.setText("Start");
               startButton.setEnabled(false);
            }
         }
      }
   }  //end of ClockHandler class (nested as private class)
}  //end of the UI class