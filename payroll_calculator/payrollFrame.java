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
//  File Name: payrollFrame.java
//  Compile: javac payrollFrame.java
//  Purpose: This class defines the graphical user interface. This class is called from the testPayroll class.

//********** MAIN CODE AREA **********************************************************************
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.BorderFactory;
import java.util.regex.Matcher;

public class payrollFrame extends JFrame
{
  private JLabel programLabel;
  private JLabel companyLabel;
  private JLabel systemLabel;
  private JLabel employeeLabel1;
  private JLabel employeeLabel2;
  private JLabel employeeLabel3;
  private JTextField textField1;
  private JTextField textField2;
  private JTextField textField3;
  private JLabel hourLabel;
  private JLabel rateLabel;
  private JLabel payLabel1;
  private JLabel payLabel2;
  private JLabel payLabel3;
  private JLabel payLabel4;
  private JLabel payLabel5;
  private JLabel payLabel6;
  private JPanel panel1;
  private JPanel panel2;
  private JPanel panel3;
  private JPanel panel4;
  private JPanel panel5;
  private JButton claerButton;
  private JButton computeButton;
  private JButton quitButton;
  private String employeeName = "-";
  private String regularString = "0.00";
  private String overtimeString ="0.00";
  private String grossString = "0.00";
  private Double regularPay = 0.0;
  private Double overtimePay = 0.0;
  private Double grossPay = 0.0;
  private Double hours = 0.0;
  private Double rate = 0.0;
  private arithmeticoperations artm;

  public payrollFrame() //constructor
  {
    super("Payroll Frame");
    setTitle("Pyroll System");
    setLayout(new GridBagLayout());
    GridBagConstraints constraint = new GridBagConstraints();
    constraint.gridx = 1;
    constraint.gridy = 1;
    constraint.weightx = 1;
    constraint.weighty = 1;
    constraint.fill = GridBagConstraints.BOTH;

    //---------- Panel 1 ----------------------------------------------------------------------
    panel1 = new JPanel();
    panel1.setLayout(new GridBagLayout());
    //GridBagConstraints constraint2 = new GridBagConstraints();
    //panel1.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    add(panel1, constraint);

    //---------- Panel 1 / Object 1 ----------------------------------------------------------------------
    programLabel = new JLabel("Program 1");
    programLabel.setForeground(Color.black);
    panel1.add(programLabel);
    
    //---------- Panel 2 ----------------------------------------------------------------------
    constraint.gridy = 2;
    panel2 = new JPanel();
    panel2.setLayout(new GridBagLayout());
    GridBagConstraints constraint3 = new GridBagConstraints();
    //panel2.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    panel2.setBackground(Color.pink);
    add(panel2, constraint);

    //---------- Panel 2 / Object 1 ----------------------------------------------------------------------
    companyLabel = new JLabel("Yadgarian Travel Agency");
    companyLabel.setForeground(Color.black);
    constraint3.gridx = 1;
    constraint3.gridy = 1;
    constraint3.ipady = 30;
    panel2.add(companyLabel, constraint3);

    //---------- Panel 2 / Object 2 ----------------------------------------------------------------------
    systemLabel = new JLabel("Payroll System");
    systemLabel.setForeground(Color.black);
    constraint3.gridx = 1;
    constraint3.gridy = 2;
    panel2.add(systemLabel, constraint3);

    //---------- Panel 3 ----------------------------------------------------------------------
    constraint.gridy = 3;
    panel3 = new JPanel();
    panel3.setLayout(new GridBagLayout());
    GridBagConstraints constraint4 = new GridBagConstraints();
    constraint4.ipadx = 10;
    constraint4.ipady = 10;
    constraint4.weightx = 1;
    constraint4.weighty = 1;
    constraint4.fill = GridBagConstraints.HORIZONTAL;
    panel3.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    panel3.setBackground(Color.blue);
    add(panel3, constraint);
    
    //---------- Panel 3 / Object 1 ----------------------------------------------------------------------
    employeeLabel1 = new JLabel("Employee Name:");
    employeeLabel1.setForeground(Color.black);
    constraint4.gridx = 1;
    constraint4.gridy = 1;
    panel3.add(employeeLabel1, constraint4);

    //---------- Panel 3 / Object 2 ----------------------------------------------------------------------
    textField1 = new JTextField(10);
    constraint4.gridx = 2;
    constraint4.gridy = 1;
    panel3.add(textField1, constraint4);

    //---------- Panel 3 / Object 3 ----------------------------------------------------------------------
    hourLabel = new JLabel("Hours Worked:");
    hourLabel.setForeground(Color.black);
    constraint4.gridx = 1;
    constraint4.gridy = 2;
    panel3.add(hourLabel, constraint4);

    //---------- Panel 3 / Object 4 ----------------------------------------------------------------------
    textField2 = new JTextField(10);
    constraint4.gridx = 2;
    constraint4.gridy = 2;
    panel3.add(textField2, constraint4);

    //---------- Panel 3 / Object 5 ----------------------------------------------------------------------
    rateLabel = new JLabel("Hourly Pay Rate:");
    rateLabel.setForeground(Color.black);
    constraint4.gridx = 1;
    constraint4.gridy = 3;
    panel3.add(rateLabel, constraint4);

    //---------- Panel 3 / Object 6 ----------------------------------------------------------------------
    textField3 = new JTextField(10);
    constraint4.gridx = 2;
    constraint4.gridy = 3;
    panel3.add(textField3, constraint4);

    //---------- Panel 4 ----------------------------------------------------------------------
    constraint.gridy = 4;
    panel4 = new JPanel();
    panel4.setLayout(new GridBagLayout());
    GridBagConstraints constraint5 = new GridBagConstraints();
    constraint5.ipadx = 10;
    constraint5.ipady = 10;
    constraint5.weightx = 1;
    constraint5.weighty = 1;
    constraint5.fill = GridBagConstraints.HORIZONTAL;
    panel4.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    panel4.setBackground(Color.green);
    add(panel4, constraint);

    //---------- Panel 4 / Object 1 ----------------------------------------------------------------------
    employeeLabel2 = new JLabel("Name of the Employee:");
    employeeLabel2.setForeground(Color.black);
    constraint5.gridx = 1;
    constraint5.gridy = 1;
    panel4.add(employeeLabel2, constraint5);

    //---------- Panel 4 / Object 2 ----------------------------------------------------------------------
    employeeLabel3 = new JLabel(employeeName);
    constraint5.gridx = 2;
    constraint5.gridy = 1;
    panel4.add(employeeLabel3, constraint5);

    //---------- Panel 4 / Object 3 ----------------------------------------------------------------------
    payLabel1 = new JLabel("Regular Pay:");
    payLabel1.setForeground(Color.black);
    constraint5.gridx = 1;
    constraint5.gridy = 2;
    panel4.add(payLabel1, constraint5);

    //---------- Panel 4 / Object 4 ----------------------------------------------------------------------
    payLabel2 = new JLabel(regularString);
    constraint5.gridx = 2;
    constraint5.gridy = 2;
    panel4.add(payLabel2, constraint5);

    //---------- Panel 4 / Object 5 ----------------------------------------------------------------------
    payLabel3 = new JLabel("Overtime Pay:");
    payLabel3.setForeground(Color.black);
    constraint5.gridx = 1;
    constraint5.gridy = 3;
    panel4.add(payLabel3, constraint5);

    //---------- Panel 4 / Object 6 ----------------------------------------------------------------------
    payLabel4 = new JLabel(overtimeString);
    constraint5.gridx = 2;
    constraint5.gridy = 3;
    panel4.add(payLabel4, constraint5);

    //---------- Panel 4 / Object 7 ----------------------------------------------------------------------
    payLabel5 = new JLabel("Gross Pay:");
    payLabel5.setForeground(Color.black);
    constraint5.gridx = 1;
    constraint5.gridy = 4;
    panel4.add(payLabel5, constraint5);

    //---------- Panel 4 / Object 8 ----------------------------------------------------------------------
    payLabel6 = new JLabel(grossString);
    constraint5.gridx = 2;
    constraint5.gridy = 4;
    panel4.add(payLabel6, constraint5);

    //---------- Panel 5 ----------------------------------------------------------------------
    constraint.gridy = 5;
    panel5 = new JPanel();
    panel5.setLayout(new GridBagLayout());
    GridBagConstraints constraint6 = new GridBagConstraints();
    constraint6.ipadx = 10;
    constraint6.weightx = 1;
    panel5.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    panel5.setBackground(Color.yellow);
    add(panel5, constraint);

    //---------- Panel 5 / Object 1 ----------------------------------------------------------------------
    claerButton = new JButton("Clear");
    constraint6.gridx = 1;
    constraint6.gridy = 1;
    panel5.add(claerButton, constraint6);

    //---------- Panel 5 / Object 2 ----------------------------------------------------------------------
    computeButton = new JButton("Compute");
    constraint6.gridx = 2;
    constraint6.gridy = 1;
    panel5.add(computeButton, constraint6);

    //---------- Panel 5 / Object 3 ----------------------------------------------------------------------
    quitButton = new JButton("Quit");
    constraint6.gridx = 3;
    constraint6.gridy = 1;
    panel5.add(quitButton, constraint6);

    //---------- Buttons, Arithmetic Function & Frame Location ----------------------------------------------------------------------
    buttonHandler apHandler = new buttonHandler();
    claerButton.addActionListener(apHandler);
    computeButton.addActionListener(apHandler);
    quitButton.addActionListener(apHandler);
    artm = new arithmeticoperations();
    setLocationRelativeTo(null);
  } //end of constructor

  private class buttonHandler implements ActionListener
  {
    public void actionPerformed(ActionEvent event)
    {
      if (event.getSource() == claerButton)
      {
        textField1.setText("");
        textField2.setText("");
        textField3.setText("");
        employeeLabel3.setText("-");
        payLabel2.setText("0.00");
        payLabel4.setText("0.00");
        payLabel6.setText("0.00");
      }

      if (event.getSource() == computeButton)
      {
        employeeName = textField1.getText();
        employeeLabel3.setText(employeeName);

        if (textField2.getText().matches("\\d*\\.\\d*") && textField3.getText().matches("\\d*\\.\\d*"))
        {
          hours = Double.parseDouble(textField2.getText());
          rate = Double.parseDouble(textField3.getText());

          if (rate >= 0 && hours >= 0 && hours <= 168)
          {
            payLabel2.setText("0.00");
            payLabel4.setText("0.00");
            payLabel6.setText("0.00");
            
            regularPay = artm.multiply(hours, rate);
            regularString = String.format("%4.2f", regularPay);
            payLabel2.setText(regularString);

            if (hours > 40)
            {
              hours = artm.subtract(hours, 40.0);
              rate = artm.multiply(rate, 1.5);
              overtimePay = artm.multiply(hours, rate);
              overtimeString = String.format("%4.2f", overtimePay);
              payLabel4.setText(overtimeString);
            }

            grossPay = artm.add(regularPay, overtimePay);
            grossString = String.format("%4.2f", grossPay);
            payLabel6.setText(grossString);
          }
          else
          {
            payLabel2.setText("Error");
            payLabel4.setText("Error");
            payLabel6.setText("Error");
          }
        }
        else 
        {
          payLabel2.setText("Error");
          payLabel4.setText("Error");
          payLabel6.setText("Error");
        }
      }

      if (event.getSource() == quitButton)
      {
        System.exit(0);
      }
    }  
  }
} //end of payrollFrame class