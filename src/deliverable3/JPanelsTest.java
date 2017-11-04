/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deliverable3;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 *
 * @author jhonata
 */

public class JPanelsTest extends JFrame implements ActionListener {
    
    private JButton registerB,loginB ;
    private JFrame frame2;
    private JPanel authenticationP,registerP,loginP,parentP;
    
    final static String AUTHENTICATION = "AUTHENTICATION";
    final static String REGISTER = "REGISTER";
     final static String LOGIN = "LOGIN";
    public JPanelsTest()  {
        
        int windowWidth = 400;
        int windowHeight = 150;
        
       /* JFrame aWindow = new JFrame("This is the Window Title");

        aWindow.setBounds(50, 100, windowWidth, windowHeight);
        aWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        aWindow.setVisible(true);
        
        JFrame frame = new JFrame();
        frame.setTitle("My First Swing Application");
        frame.setBounds(50, 100, windowWidth, windowHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel label = new JLabel("Welcome", JLabel.CENTER);
        frame.add(label);
        frame.pack();
        frame.setVisible(true);
        
        //JFrame.setDefaultLookAndFeelDecorated(true);*/
        frame2 = new JFrame();
        parentP = new JPanel(new CardLayout());
        frame2.addWindowListener(new WindowEventHandler());
        frame2.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //parentP.setLayout(new CardLayout());
        
        
        authenticationP = new JPanel(new GridLayout(2,1));
        registerB = new JButton("Register"); 
        registerB.setActionCommand(REGISTER);
        registerB.addActionListener(this);
        authenticationP.add(registerB);
        loginB = new JButton("Login");
        loginB.setActionCommand(LOGIN);
        loginB.addActionListener(this);
        authenticationP.add(loginB);
        
        parentP.add(authenticationP,AUTHENTICATION);
        
        
        registerP = new JPanel(new GridLayout(5,2));
       
        
        registerP.add(new JLabel("User Name:"));
        registerP.add(new JTextField());
        registerP.add(new JLabel("Password:"));
        registerP.add(new JTextField());
        registerP.add(new JLabel("First Name:"));
        registerP.add(new JTextField());
        registerP.add(new JLabel("Last Name:"));
        registerP.add(new JTextField());
        registerP.add(new JButton("Register"));
        
        parentP.add(registerP,REGISTER);
        
        loginP = new JPanel(new GridLayout(3,2));
         
        loginP.add(new JLabel("User Name:"));
        loginP.add(new JTextField());
        loginP.add(new JLabel("Password:"));
        loginP.add(new JTextField());
        loginP.add(new JButton("Login"));
        
        
        parentP.add(loginP,LOGIN);
        int frameWidth = 200;
        int frameHeight = 100;
        frame2.add(parentP);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame2.setBounds((int) screenSize.getWidth() - frameWidth, 0, frameWidth, frameHeight);
        frame2.setVisible(true);
    }
    
    
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        CardLayout c1 = (CardLayout)(parentP.getLayout());
        if(source.equals(registerB)){
            //System.err.println(e.getActionCommand());
            c1.show(parentP,e.getActionCommand());
            //switchCard(2);
            
        }
        if(source.equals(loginB)){
            
            c1.show(parentP,e.getActionCommand());
           
            
        }
        
        
    }
    
}
