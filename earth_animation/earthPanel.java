//********** PROGRAM INFORMATION **********************************************************************
//  Program Name: Earth
//  Student Name: Brian Y
//  Student E-mail: ***

//********** MAIN CODE AREA **********************************************************************
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Color;

public class earthPanel extends JPanel     //rectangle panel class
{
     private computations compute;         //mathematical calculations class
     private double earthRadious = 10.0;
     private double sunRadious = 30.0;
     private double orbitRadious = 200.0;
     private double earthDiameter = 2.0 * earthRadious;
     private double sunDiameter = 2.0 * sunRadious;
     private double earthCenterX;
     private double earthCenterY;
     private double sunCenterX;
     private double sunCenterY;
     private int earthIntCenterX;
     private int earthIntCenterY;
     private int sunIntCenterX;
     private int sunIntCenterY;
     private double t = 0;
     private double Δt;
     private double panelHeight;
     private double panelWidth;
     private boolean showRectangle = false;

     public earthPanel()    //rectangle panel constructor
     {
          setVisible(true);
     }    //end of the rectangle panel constructor

     public void paintComponent(Graphics graphics)     //JPanel member funciton
     {
          super.paintComponent(graphics);
          if(showRectangle)
          {
               graphics.setColor(Color.black);         //line color (invisible)
               graphics.drawLine(1528,400,864,50);     //invisible lines to eliminate jitter (I don't know why lack of these drawings causes jitter but I'm assuming it has to do with increased taime lapse between repaints)
               graphics.drawLine(864,50,200,400);
               graphics.drawLine(200,400,864,725);
               graphics.drawLine(864,725,1528,400);
               graphics.setColor(Color.green);         //earth's color
               graphics.fillOval(earthIntCenterX, earthIntCenterY, (int)Math.round(earthDiameter), (int)Math.round(earthDiameter));
               graphics.setColor(Color.yellow);        //sun's color
               graphics.fillOval(sunIntCenterX, sunIntCenterY, (int)Math.round(sunDiameter), (int)Math.round(sunDiameter));
          }
     }    //end of the paintComponent function

     public void initialize(double width, double height, double Delta_t)     //new member function added to the extended class
     {
          panelWidth = width;
          panelHeight = height;
          sunCenterX = panelWidth/2;
          sunCenterY = panelHeight/2;
          earthCenterX = sunCenterX + orbitRadious;
          earthCenterY = sunCenterY;
          Δt = Delta_t;
          
          earthIntCenterX = (int)Math.round(earthCenterX);
          earthIntCenterY = (int)Math.round(earthCenterY);
          sunIntCenterX = (int)Math.round(sunCenterX);
          sunIntCenterY = (int)Math.round(sunCenterY);

          showRectangle = true;
     }    //end of the initialize function

     public void earthMove()     //new member function added to the extended class
     {
          t -= Δt;
          earthCenterX = sunCenterX + (orbitRadious * Math.cos(t));
          earthCenterY = sunCenterY + (orbitRadious * Math.sin(t));
          earthIntCenterX = (int)Math.round(earthCenterX);
          earthIntCenterY = (int)Math.round(earthCenterY);
     }    //end of the earthMove function

     public double getEarthCenterX()     //new member function added to the extended class
     {
          return earthCenterX;
     }

     public double getEarthCenterY()     //new member function added to the extended class
     {
          return earthCenterY;
     }
}