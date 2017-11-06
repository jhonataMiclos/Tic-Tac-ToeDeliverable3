/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deliverable3;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author jhonata
 */

public class JPanelsUI extends JFrame implements ActionListener {
    
    private JButton registerB, loginB, registerConfirmB, loginConfirmB, hostB, joinB,cancelHostB,joinOKB;
    private JFrame frame;
    private JComboBox<String> dropDown;
    private JPanel authenticationP, registerP, loginP, parentP, matchmakingSelectionP,hostingGameP,joinSelectionP,gameBoardP;
    private JTextField regUsername, regPassword, regFName, regLName, logUsername, logPassword;
    private JLabel curPlayerID;
    private TicTacToeControl control;
    private JButton[] squares;
    
    final static String AUTHENTICATION = "AUTHENTICATION";
    final static String REGISTER = "REGISTER";
    final static String LOGIN = "LOGIN";
    final static String MATCHMAKINGSELECTION = "MATCHMAKINGSELECTION";
    final static String HOSTINGGAMEPANEL = "HOSTINGGAMEPANEL";
    final static String GAMEBOARDPANEL = "GAMEBOARDPANEL";
    final static String JOINSELECTION = "JOINSELECTION";
    
    public JPanelsUI(TicTacToeControl control)  {
        this.control = control;
        
        int windowWidth = 500;
        int windowHeight = 300;
        
        frame = new JFrame();
        frame.setBounds(300, 200, windowWidth, windowHeight);
        
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
        hostB.setActionCommand(HOSTINGGAMEPANEL);
        hostB.addActionListener(this);
        joinB = new JButton("Join a game");
        matchmakingSelectionP.add(joinB);
        joinB.setActionCommand(JOINSELECTION);
        joinB.addActionListener(this);
        
        // Hosting a game Panel
        hostingGameP = new JPanel(new GridLayout(2,1));
        parentP.add(hostingGameP,HOSTINGGAMEPANEL);
        // Hosting fields
        hostingGameP.add(new JLabel("Waiting for next Player"));
        cancelHostB = new JButton("Cancel hosting");
        hostingGameP.add(cancelHostB);
        cancelHostB.addActionListener(this);
        
        //Select which game to join Panel
        joinSelectionP = new JPanel();
        joinSelectionP.setLayout(new BoxLayout(joinSelectionP, BoxLayout.Y_AXIS));
        parentP.add(joinSelectionP);
        parentP.add(joinSelectionP,JOINSELECTION);
        
        JLabel lbl = new JLabel("Select one of the possible choices and click OK");
        lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        joinSelectionP.add(lbl);
        
        
        // Game board page
        gameBoardP = new JPanel(new GridLayout(1,2));
        parentP.add(gameBoardP,GAMEBOARDPANEL);
        
        // Game board page fields
        JPanel gameBoard = new JPanel();
        //gameBoard.setBounds(new Rectangle(parentP.getBounds()));
        gameBoard.setLayout(new GridLayout(3,3));
        squares = new JButton[9];
        for(int i=0;i<9;i++) {
            squares[i] = new JButton(" ");
            squares[i].setBackground(Color.WHITE);
            squares[i].addActionListener(this);
            gameBoard.add(squares[i]);
        }
        
        JPanel menu = new JPanel();
        menu.setLayout(new GridLayout(3,1));
        
        curPlayerID = new JLabel("", SwingConstants.CENTER);
        curPlayerID.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        
        menu.add(curPlayerID);
        
        gameBoardP.add(menu);
        gameBoardP.add(gameBoard, BorderLayout.CENTER);
        
        
        
        int frameWidth = 200;
        int frameHeight = 100;
        frame.add(parentP);
        //Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //frame.setBounds((int) screenSize.getWidth() - frameWidth, 0, frameWidth, frameHeight);
        frame.setVisible(true);
    }
    
    public void switchPage(String page) {
        CardLayout c1 = (CardLayout)(parentP.getLayout());
        c1.show(parentP, page);
    }
    
    public void turnIndicatorColor(int currentPlayerNum, int playerNum) {
        if (currentPlayerNum == 1) {
            curPlayerID.setForeground(Color.GREEN);
        } else if (currentPlayerNum == 2) {
            curPlayerID.setForeground(Color.RED);
        } else {
            curPlayerID.setForeground(Color.WHITE);
        }
        
        if (currentPlayerNum == playerNum) {
            curPlayerID.setText("Your turn");
        }
        else {
            curPlayerID.setText("Opponent's turn");
        }
    }
    
    public void colorSquare(int x, int y, int playerNum) {
        int squareNum = x + y * 3;
        
        if (playerNum == 1) {
            squares[squareNum].setBackground(Color.GREEN);
        } else if (playerNum == 2) {
            squares[squareNum].setBackground(Color.RED);
        } else {
            squares[squareNum].setBackground(Color.WHITE);
        }
    }
    
    public void reset() {
        for (int i = 0; i < squares.length; i++) {
            squares[i].setBackground(Color.WHITE);
        }
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
        else if (source.equals(hostB)){
            if(control.hostGame()){
                c1.show(parentP, e.getActionCommand());
            }
        }
        else if (source.equals(joinB)){
            String [] choices ;
            
            choices = control.getAllGamesOpen();
            dropDown = new JComboBox<String>(choices);
            dropDown.setMaximumSize(dropDown.getPreferredSize()); // added code
            dropDown.setAlignmentX(Component.CENTER_ALIGNMENT);

            joinSelectionP.add(dropDown);
            
            joinOKB = new JButton("OK");
            joinOKB.setAlignmentX(Component.CENTER_ALIGNMENT); // added code
            joinSelectionP.add(joinOKB);
            joinOKB.setActionCommand(GAMEBOARDPANEL);
            joinOKB.addActionListener(this);
            c1.show(parentP,  e.getActionCommand());
        }
        else if (source.equals(joinOKB)){
            
            String gameSelected = (String)dropDown.getSelectedItem();
            String tempArr[] = gameSelected.split(",");
            if(control.joinSelectedGame(Integer.parseInt(tempArr[0]))){
                c1.show(parentP, GAMEBOARDPANEL);
            }
        }
        else if(source.equals(squares[0])) {
            control.clickSquare(0, 0);
        }
        else if(source.equals(squares[1])) {
            control.clickSquare(1, 0);
        }
        else if(source.equals(squares[2])) {
            control.clickSquare(2, 0);
        }
        else if(source.equals(squares[3])) {
            control.clickSquare(0, 1);
        }
        else if(source.equals(squares[4])) {
            control.clickSquare(1, 1);
        }
        else if(source.equals(squares[5])) {
            control.clickSquare(2, 1);
        }
        else if(source.equals(squares[6])) {
            control.clickSquare(0, 2);
        }
        else if(source.equals(squares[7])) {
            control.clickSquare(1, 2);
        }
        else if(source.equals(squares[8])) {
            control.clickSquare(2, 2);
        }
    }
    
}
