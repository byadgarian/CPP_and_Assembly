//********** PROGRAM INFORMATION **********************************************************************
//  Program Name: Earth
//  Student Name: Brian Y
//  Student E-mail: ***

//********** NOTES **********************************************************************
//I tried the 20 second test but it seems like the pics/sec speed is relative and depends on the clock frequency, so
//I get different rotation speeds for different clock frequencies even though my orbit radious is the same.

//********** MAIN CODE AREA **********************************************************************
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Toolkit;

public class earth      //driver class
{    
    public static void main(String[] args)
    {
        JFrame mainFrame = new earthUI();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int)Math.round(screenSize.getWidth());
        int screenHeight = (int)Math.round(screenSize.getHeight());
        int frameWidth = (int)Math.round(screenWidth * 0.9);
        int frameHight = (int)Math.round(screenHeight * 0.85);
        mainFrame.setSize(frameWidth, frameHight);
        mainFrame.setLocation((int)Math.round(screenWidth/2) - frameWidth/2, (int)Math.round(screenHeight/2) - frameHight/2);
        // mainFrame.setLocationRelativeTo(null);
        mainFrame.setResizable(true);
        mainFrame.setVisible(true);
    }   //end of the main function
}   //end of the driver class