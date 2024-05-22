//********** PROGRAM INFORMATION **********************************************************************
//  Program Name: Earth
//  Student Name: Brian Y
//  Student E-mail: ***

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
import java.lang.System.*;              //system messages for debugging

public class earthUI extends JFrame     //main UI class
{
   private JPanel panel1;
   private earthPanel rectangle;        //this is panel2 (goes between panel1 and panel3)
   private JPanel panel3;
   private JPanel miniPanel;
   private JLabel programLabel;
   private JLabel earthSpeedLabel;
   private JLabel coordinateLable;
   private JLabel xLable;
   private JLabel yLable;
   private JTextField earthSpeedTextField;
   private JTextField xTextField;
   private JTextField yTextField;
   private ButtonHandler btnHandler;
   private JButton pauseButton;
   private JButton startButton;
   private JButton quitButton;
   private ClockHandler clkHandler;
   private Timer refreshClock;
   private Timer earthMotionClock;
   private double refreshFreq = 120.00;         //repaints in one second (Hz)
   private double earthMotionFreq = 60.00;     //movements in one second (Hz)
   private int refreshPeriod;                   //how long each refresh takes (ms)
   private int earthMotionPeriod;               //how long each movement takes (ms)
   private computations compute;                //mathematical calculations class
   private double earthSpeedPixPerSec;
   private double earthSpeedPixPerTic;
   private double Δt;
   private double orbitRadious = 200.0;
   private double panelHeight;
   private double panelWidth;
   private boolean pauseFlag = false;

   public earthUI()    //main UI constructor
   {
      super("Earth");
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
      programLabel = new JLabel("Earth by Brian Y");
      programLabel.setForeground(Color.black);
      constraint1.gridx = 1;
      constraint1.gridy = 1;
      panel1.add(programLabel, constraint1);

      //---------- Panel 2 ----------------------------------------------------------------------
      constraint0.weightx = 1;
      constraint0.weighty = 1;
      constraint0.gridx = 1;
      constraint0.gridy = 2;
      rectangle = new earthPanel();
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
      constraint3.weightx = 1;
      panel3.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
      panel3.setBackground(Color.green);
      add(panel3, constraint0);

      //---------- Panel 3 / Object 1 ----------------------------------------------------------------------
      pauseButton = new JButton("Pause");
      constraint3.gridx = 1;
      constraint3.gridy = 1;
      panel3.add(pauseButton, constraint3);

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
      coordinateLable = new JLabel("Coordinates");
      constraint3.gridx = 4;
      constraint3.gridy = 1;
      panel3.add(coordinateLable, constraint3);

      //---------- Panel 3 / Object 5 ----------------------------------------------------------------------
      miniPanel = new JPanel();
      constraint3.gridx = 4;
      constraint3.gridy = 2;
      miniPanel.setLayout(new GridBagLayout());
      GridBagConstraints miniConstraint = new GridBagConstraints();
      miniConstraint.ipadx = 10;
      miniPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
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

      //blank

      //---------- Panel 3 / Object 7 ----------------------------------------------------------------------
      earthSpeedLabel = new JLabel("Earth's Speed (Pix/Sec)");
      earthSpeedLabel.setBackground(Color.black);
      constraint3.gridx = 2;
      constraint3.gridy = 3;
      panel3.add(earthSpeedLabel, constraint3);

      //---------- Panel 3 / Object 8 ----------------------------------------------------------------------

      //blank

      //---------- Panel 3 / Object 9 ----------------------------------------------------------------------

      //blank

      //---------- Panel 3 / Object 10 ----------------------------------------------------------------------
      earthSpeedTextField = new JTextField(10);
      constraint3.gridx = 2;
      constraint3.gridy = 4;
      panel3.add(earthSpeedTextField, constraint3);
      earthSpeedTextField.setText("200.0");

      //---------- Panel 3 / Object 11 ----------------------------------------------------------------------

      //blank

      //---------- Buttons & Clocks ----------------------------------------------------------------------
      btnHandler = new ButtonHandler();
      clkHandler = new ClockHandler();
      startButton.addActionListener(btnHandler);
      quitButton.addActionListener(btnHandler);
      pauseButton.addActionListener(btnHandler);
      refreshPeriod = (int)Math.round(1000/refreshFreq);
      earthMotionPeriod = (int)Math.round(1000/earthMotionFreq);
      refreshClock = new Timer(refreshPeriod, clkHandler);
      earthMotionClock = new Timer(earthMotionPeriod, clkHandler);

      //---------- Final Steps ----------------------------------------------------------------------
      compute = new computations();
      setVisible(true);
   }  //end of the constructor

   private class ButtonHandler implements ActionListener
   {
      public void actionPerformed(ActionEvent event)     //member function of the implemented class
      {
         if(event.getSource() == startButton)
         {
            if (pauseFlag ==  false)
            {
               panelWidth = rectangle.getWidth();
               panelHeight = rectangle.getHeight();
               earthSpeedPixPerSec = Double.parseDouble(earthSpeedTextField.getText());
               if(earthSpeedPixPerSec < 0)
               {
                  earthSpeedPixPerSec = Math.abs(earthSpeedPixPerSec);
                  earthSpeedTextField.setText(String.valueOf(earthSpeedPixPerSec));
               }
               earthSpeedPixPerTic = earthSpeedPixPerSec/earthMotionFreq;
               Δt = compute.calcΔt(orbitRadious, earthSpeedPixPerTic);
               rectangle.initialize(panelWidth, panelHeight, Δt);
               refreshClock.start();
               earthMotionClock.start();
            }
            else
            {
               refreshClock.start();
               earthMotionClock.start();
            }
         }
         else if(event.getSource() == pauseButton)
         {
            refreshClock.stop();
            earthMotionClock.stop();
            pauseFlag = true;
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
         else if(event.getSource() == earthMotionClock)
         {
            rectangle.earthMove();
            xTextField.setText(String.valueOf((int)Math.round(rectangle.getEarthCenterX())));
            yTextField.setText(String.valueOf((int)Math.round(rectangle.getEarthCenterY())));
         }
      }
   }  //end of ClockHandler class (nested as private class)
}  //end of the UI class