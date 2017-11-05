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

public class JPanelsUI extends JFrame implements ActionListener {
    
    private JButton registerB, loginB, registerConfirmB, loginConfirmB, hostB, joinB;
    private JFrame frame;
    private JPanel authenticationP, registerP, loginP, parentP, matchmakingSelectionP;
    private JTextField regUsername, regPassword, regFName, regLName, logUsername, logPassword;
    TicTacToeControl control;
    
    final static String AUTHENTICATION = "AUTHENTICATION";
    final static String REGISTER = "REGISTER";
    final static String LOGIN = "LOGIN";
    final static String MATCHMAKINGSELECTION = "MATCHMAKINGSELECTION";
    
    public JPanelsUI(TicTacToeControl control)  {
        this.control = control;
        
        int windowWidth = 400;
        int windowHeight = 400;
        
        frame = new JFrame();
        frame.setSize(windowWidth, windowHeight);
        
        parentP = new JPanel(new CardLayout());
        frame.addWindowListener(new WindowEventHandler());
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        authenticationP = new JPanel(new GridLayout(2,1));
        parentP.add(authenticationP,AUTHENTICATION);
        
        // Authentication page fields
        registerB = new JButton("Register"); 
        registerB.setActionCommand(REGISTER);
        registerB.addActionListener(this);
        authenticationP.add(registerB);
        loginB = new JButton("Login");
        loginB.setActionCommand(LOGIN);
        loginB.addActionListener(this);
        authenticationP.add(loginB);
        
        
        
        registerP = new JPanel(new GridLayout(5,2));
        parentP.add(registerP,REGISTER);
        
        // Register page fields
        registerP.add(new JLabel("Username:"));
        regUsername = new JTextField();
        registerP.add(regUsername);
        registerP.add(new JLabel("Password:"));
        regPassword = new JTextField();
        registerP.add(regPassword);
        registerP.add(new JLabel("First Name:"));
        regFName = new JTextField();
        registerP.add(regFName);
        registerP.add(new JLabel("Last Name:"));
        regLName = new JTextField();
        registerP.add(regLName);
        registerConfirmB = new JButton("Register");
        registerConfirmB.setActionCommand(AUTHENTICATION);
        registerConfirmB.addActionListener(this);
        registerP.add(registerConfirmB);
        
        
        
        loginP = new JPanel(new GridLayout(3,2));
        parentP.add(loginP,LOGIN);
        
        // Login page fields
        loginP.add(new JLabel("User Name:"));
        logUsername = new JTextField();
        loginP.add(logUsername);
        loginP.add(new JLabel("Password:"));
        logPassword = new JTextField();
        loginP.add(logPassword);
        loginConfirmB = new JButton("Login");
        loginConfirmB.setActionCommand(MATCHMAKINGSELECTION);
        loginConfirmB.addActionListener(this);
        loginP.add(loginConfirmB);
        
        
        matchmakingSelectionP = new JPanel(new GridLayout(2,1));
        parentP.add(matchmakingSelectionP,MATCHMAKINGSELECTION);
        
        // Matchmaking selection page fields
        hostB = new JButton("Host a game");
        matchmakingSelectionP.add(hostB);
        hostB.addActionListener(this);
        joinB = new JButton("Join a game");
        matchmakingSelectionP.add(joinB);
        joinB.addActionListener(this);
        
        
        int frameWidth = 200;
        int frameHeight = 100;
        frame.add(parentP);
        //Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //frame.setBounds((int) screenSize.getWidth() - frameWidth, 0, frameWidth, frameHeight);
        frame.setVisible(true);
    }
    
    
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        CardLayout c1 = (CardLayout)(parentP.getLayout());
        
        if(source.equals(registerB)){
            c1.show(parentP, e.getActionCommand());
        }
        else if (source.equals(registerConfirmB)) {
            String username = regUsername.getText();
            String password = regPassword.getText();
            String fName = regFName.getText();
            String lName = regLName.getText();
            
            if (control.registerUser(username, password, fName, lName)) {
                c1.show(parentP, e.getActionCommand());
            }
        }
        else if(source.equals(loginB)){ 
            c1.show(parentP, e.getActionCommand()); 
        }
        else if (source.equals(loginConfirmB)) {
            String username = logUsername.getText();
            String password = logPassword.getText();
            
            if (control.loginUser(username, password)) {
                c1.show(parentP, e.getActionCommand());
            }
        }
        
        
    }
    
}
