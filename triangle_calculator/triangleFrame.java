//********** STUDENT INFORMATION **********************************************************************
//  Student Name: Brian Y
//  Student E-mail: ***

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

public class triangleFrame extends JFrame
{
  private JLabel programLabel1;
  private JLabel programLabel2;
  private JLabel programLabel3;
  private JLabel triangleLabel1;
  private JLabel triangleLabel2;
  private JLabel triangleLabel3;
  private JLabel triangleLabel4;
  private JTextField textField1;
  private JTextField textField2;
  private JTextField textField3;
  private JTextField textField4;
  private JPanel panel1;
  private JPanel panel2;
  private JPanel panel3;
  private JButton computeButton;
  private JButton claerButton;
  private JButton quitButton;
  private String hypotenuseString = "";
  private String areaString ="";
  private Double side1 = 0.0;
  private Double side2 = 0.0;
  private Double hypotenuse = 0.0;
  private Double area = 0.0;
  private arithmeticoperations arthm;

  public triangleFrame() //constructor
  {
    super("Triangle");
    setTitle("Triangle Calculator");
    setLayout(new GridBagLayout());
    GridBagConstraints constraint1 = new GridBagConstraints();
    constraint1.weightx = 1;
    constraint1.weighty = 1;
    constraint1.fill = GridBagConstraints.BOTH;
    
    //---------- Panel 1 ----------------------------------------------------------------------
    constraint1.gridx = 1;
    constraint1.gridy = 1;
    panel1 = new JPanel();
    panel1.setLayout(new GridBagLayout());
    GridBagConstraints constraint2 = new GridBagConstraints();
    constraint2.ipady = 30;
    panel1.setBackground(Color.red);
    add(panel1, constraint1);

    //---------- Panel 1 / Object 1 ----------------------------------------------------------------------
    programLabel1 = new JLabel("Welcome to Triangle Computations");
    programLabel1.setForeground(Color.black);
    constraint2.gridx = 1;
    constraint2.gridy = 1;
    panel1.add(programLabel1, constraint2);

    //---------- Panel 1 / Object 2 ----------------------------------------------------------------------
    programLabel2 = new JLabel("Programmed by Brian Y");
    programLabel2.setForeground(Color.black);
    constraint2.gridx = 1;
    constraint2.gridy = 2;
    panel1.add(programLabel2, constraint2);

    //---------- Panel 1 / Object 3 ----------------------------------------------------------------------
    programLabel3 = new JLabel("All triangles are right triangle");
    programLabel3.setForeground(Color.black);
    constraint2.gridx = 1;
    constraint2.gridy = 3;
    panel1.add(programLabel3, constraint2);

    //---------- Panel 2 ----------------------------------------------------------------------
    constraint1.gridx = 1;
    constraint1.gridy = 2;
    panel2 = new JPanel();
    panel2.setLayout(new GridBagLayout());
    GridBagConstraints constraint3 = new GridBagConstraints();
    constraint3.ipadx = 10;
    constraint3.ipady = 10;
    constraint3.weightx = 1;
    constraint3.weighty = 1;
    constraint3.fill = GridBagConstraints.HORIZONTAL;
    panel2.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    panel2.setBackground(Color.green);
    add(panel2, constraint1);
    
    //---------- Panel 2 / Object 1 ----------------------------------------------------------------------
    triangleLabel1 = new JLabel("Side 1:");
    triangleLabel1.setForeground(Color.black);
    constraint3.gridx = 1;
    constraint3.gridy = 1;
    panel2.add(triangleLabel1, constraint3);

    //---------- Panel 2 / Object 2 ----------------------------------------------------------------------
    textField1 = new JTextField(10);
    constraint3.gridx = 2;
    constraint3.gridy = 1;
    panel2.add(textField1, constraint3);

    //---------- Panel 2 / Object 3 ----------------------------------------------------------------------
    triangleLabel2 = new JLabel("Side 2:");
    triangleLabel2.setForeground(Color.black);
    constraint3.gridx = 1;
    constraint3.gridy = 2;
    panel2.add(triangleLabel2, constraint3);

    //---------- Panel 2 / Object 4 ----------------------------------------------------------------------
    textField2 = new JTextField(10);
    constraint3.gridx = 2;
    constraint3.gridy = 2;
    panel2.add(textField2, constraint3);

    //---------- Panel 2 / Object 5 ----------------------------------------------------------------------
    triangleLabel3 = new JLabel("Hypotenuse:");
    triangleLabel3.setForeground(Color.black);
    constraint3.gridx = 1;
    constraint3.gridy = 3;
    panel2.add(triangleLabel3, constraint3);

    //---------- Panel 2 / Object 6 ----------------------------------------------------------------------
    textField3 = new JTextField(10);
    constraint3.gridx = 2;
    constraint3.gridy = 3;
    panel2.add(textField3, constraint3);

    //---------- Panel 2 / Object 7 ----------------------------------------------------------------------
    triangleLabel4 = new JLabel("Area:");
    triangleLabel4.setForeground(Color.black);
    constraint3.gridx = 1;
    constraint3.gridy = 4;
    panel2.add(triangleLabel4, constraint3);

    //---------- Panel 2 / Object 8 ----------------------------------------------------------------------
    textField4 = new JTextField(10);
    constraint3.gridx = 2;
    constraint3.gridy = 4;
    panel2.add(textField4, constraint3);

    //---------- Panel 3 ----------------------------------------------------------------------
    constraint1.gridx = 1;
    constraint1.gridy = 3;
    panel3 = new JPanel();
    panel3.setLayout(new GridBagLayout());
    GridBagConstraints constraint4 = new GridBagConstraints();
    constraint4.ipadx = 10;
    constraint4.weightx = 1;
    panel3.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    panel3.setBackground(Color.red);
    add(panel3, constraint1);

    //---------- Panel 3 / Object 1 ----------------------------------------------------------------------
    computeButton = new JButton("Compute");
    constraint4.gridx = 1;
    constraint4.gridy = 1;
    panel3.add(computeButton, constraint4);

    //---------- Panel 3 / Object 2 ----------------------------------------------------------------------
    claerButton = new JButton("   Clear   ");
    constraint4.gridx = 2;
    constraint4.gridy = 1;
    panel3.add(claerButton, constraint4);

    //---------- Panel 3 / Object 3 ----------------------------------------------------------------------
    quitButton = new JButton("    Quit    ");
    constraint4.gridx = 3;
    constraint4.gridy = 1;
    panel3.add(quitButton, constraint4);

    //---------- Buttons, Arithmetic Function & Frame Location ----------------------------------------------------------------------
    buttonHandler apHandler = new buttonHandler();
    computeButton.addActionListener(apHandler);
    claerButton.addActionListener(apHandler);
    quitButton.addActionListener(apHandler);
    arthm = new arithmeticoperations();
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
        textField4.setText("");
      }

      if (event.getSource() == computeButton)
      {
        side1 = Double.parseDouble(textField1.getText());
        side2 = Double.parseDouble(textField2.getText());

        if (side1 >= 0)
        {
          hypotenuse = arthm.squareRoot(arthm.add(arthm.power(side1, 2), arthm.power(side2, 2)));
          hypotenuseString = String.format("%4.2f", hypotenuse);
          textField3.setText(hypotenuseString);

          area = arthm.devide(arthm.multiply(side1, side2), 2);
          areaString = String.format("%4.2f", area);
          textField4.setText(areaString);
        }
        else
        {
          textField3.setText("Error");
          textField4.setText("Error");
        }
      }

      if (event.getSource() == quitButton)
      {
        System.exit(0);
      }
    }  
  }
} //end of triangleFrame class