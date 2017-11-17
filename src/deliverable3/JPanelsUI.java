/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deliverable3;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

/**
 *
 * @author jhonata
 */

public class JPanelsUI extends JFrame implements ActionListener {
    
    private JButton registerB, loginB, registerConfirmB, loginConfirmB, hostB, joinB,cancelHostB,joinOKB,leaderBoardB,joinBackB,leaderBoardBackB;
    private JFrame frame;
    private JComboBox<String> dropDown;
    private JScrollPane scrollPane;
    private JTable leageList;
    private JPanel authenticationP, registerP, loginP, parentP, matchmakingSelectionP,hostingGameP,joinSelectionP,gameBoardP,leaderBoardP;
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
    final static String LEADERBOARD = "LEADERBOARD";
    
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
        
        createAutentificationP();
        
        createRegisterP();
        
        createLoginP();
        
        createMatchMakingSelectP();
        
       
        createHostP();
        
        createJoinP();
        
        createGameBoardP();
        
        createLeaderBoardP();
        // Game board page
       
        
        
        
        int frameWidth = 200;
        int frameHeight = 100;
        frame.add(parentP);
        //Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        //frame.setBounds((int) screenSize.getWidth() - frameWidth, 0, frameWidth, frameHeight);
        frame.setVisible(true);
    }
    private void createAutentificationP(){
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
    }
    private void createRegisterP(){
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
        
    }
    private void createLoginP(){
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
    }
    private void createMatchMakingSelectP(){
        matchmakingSelectionP = new JPanel(new GridLayout(3,1));
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
        leaderBoardB = new JButton("Leader board");
        matchmakingSelectionP.add(leaderBoardB);
        leaderBoardB.setActionCommand(LEADERBOARD);
        leaderBoardB.addActionListener(this);
    }
    private void createLeaderBoardP(){
        leaderBoardP = new JPanel(new GridLayout(2,1));
        parentP.add(leaderBoardP,LEADERBOARD);
        
        leaderBoardBackB = new JButton("Back");
        leaderBoardP.add(leaderBoardBackB);
        leaderBoardBackB.setAlignmentX(Component.CENTER_ALIGNMENT);
        leaderBoardBackB.setActionCommand(MATCHMAKINGSELECTION);
        leaderBoardBackB.addActionListener(this);
        
        
        
    }
    private void createHostP(){
         // Hosting a game Panel
        hostingGameP = new JPanel(new GridLayout(2,1));
        parentP.add(hostingGameP,HOSTINGGAMEPANEL);
        // Hosting fields
        hostingGameP.add(new JLabel("Waiting for next Player"));
      
    }
    private void createJoinP(){
          
        //Select which game to join Panel
        joinSelectionP = new JPanel();
        joinSelectionP.setLayout(new BoxLayout(joinSelectionP, BoxLayout.Y_AXIS));
        parentP.add(joinSelectionP);
        parentP.add(joinSelectionP,JOINSELECTION);
        
        JLabel lbl = new JLabel("Select one of the possible choices and click OK");
        lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
        joinSelectionP.add(lbl);
        
        joinBackB = new JButton("Back");
        joinSelectionP.add(joinBackB);
        joinBackB.setAlignmentX(Component.CENTER_ALIGNMENT);
        joinBackB.setActionCommand(MATCHMAKINGSELECTION);
        joinBackB.addActionListener(this);
        
        
    }
    private void createGameBoardP(){
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
        else if(source.equals(joinBackB)){
            c1.show(parentP, e.getActionCommand());
            joinSelectionP.remove(dropDown);
            joinSelectionP.remove(joinOKB);
        }
        else if(source.equals(leaderBoardBackB)){
            c1.show(parentP, e.getActionCommand());
            leaderBoardP.remove(scrollPane);
        }
        else if (source.equals(joinOKB)){
            
            String gameSelected = (String)dropDown.getSelectedItem();
            String tempArr[] = gameSelected.split(",");
            if(control.joinSelectedGame(Integer.parseInt(tempArr[0]))){
                c1.show(parentP, e.getActionCommand());
                 joinSelectionP.remove(dropDown);
                 joinSelectionP.remove(joinOKB);
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
        else if(source.equals(leaderBoardB)){
            
            String myInfo[] = control.getLeageTable();
            int val = 0;
            int num = 0;
            boolean inList = false;
            ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();
            for(int i = 0 ; i < myInfo.length;i++){
                String temp[] = myInfo[i].split(",");
                val  = Integer.parseInt(temp[3]);
                
                if(list.size() > 0){
                   // System.out.println(list.size());
                   for (int c = 1 ;c <= 2;c++){
                       for(int index = 0 ; index <list.size();index++ ){
                           if(list.get(index).get(0).compareTo(temp[c])== 0)
                               inList = true;
                           
                       }
                       if(!inList){
                           ArrayList<String> tempList1 = new ArrayList<String>();
                           tempList1.add(temp[c]);
                           tempList1.add("0");
                           tempList1.add("0");
                           tempList1.add("0");
                           list.add(tempList1);
                       }
                       inList= false;
                   }
                    for(int j = 0 ; j < list.size();j++ ){
                        
                        if(val == 1){
                           
                            if(list.get(j).get(0).compareTo(temp[1])== 0){
                                    
                                    num = Integer.parseInt(list.get(j).get(1));
                                    list.get(j).set(1,"" + (num + 1) );
                                    
                            

                            }
                            else if(list.get(j).get(0).compareTo(temp[2])== 0){
                                num = Integer.parseInt(list.get(j).get(2));
                                list.get(j).set(2,"" + (num + 1) );
                            }

                        }
                        else if(val == 2){
                            if(list.get(j).get(0).compareTo(temp[1])== 0){
                                    
                                    num = Integer.parseInt(list.get(j).get(2));
                                    list.get(j).set(2,"" + (num + 1) );
                                    
                            

                            }
                            else if(list.get(j).get(0).compareTo(temp[2])== 0){
                                num = Integer.parseInt(list.get(j).get(1));
                                list.get(j).set(1,"" + (num + 1) );
                            }
                        }
                        else if(val == 3){
                            if(list.get(j).get(0).compareTo(temp[1])== 0){
                                    
                                    num = Integer.parseInt(list.get(j).get(3));
                                    list.get(j).set(3,"" + (num + 1) );
                                    
                            

                            }
                            else if(list.get(j).get(0).compareTo(temp[2])== 0){
                                num = Integer.parseInt(list.get(j).get(3));
                                list.get(j).set(3,"" + (num + 1) );
                            }
                        }
                    }
                }
                else{
                    
                    ArrayList<String> tempList1 = new ArrayList<String>();
                    ArrayList<String> tempList2 = new ArrayList<String>();
                    // val  = Integer.parseInt(temp[3]);
                    if(val == 1){
                        tempList1.add(temp[val]);
                        tempList1.add("1");
                        tempList1.add("0");
                        tempList1.add("0");
                        
                        tempList2.add(temp[val + 1]);
                        tempList2.add("0");
                        tempList2.add("1");
                        tempList2.add("0");
                    }
                    else if(val == 2){
                        tempList1.add(temp[val -1 ]);
                        tempList1.add("0");
                        tempList1.add("1");
                        tempList1.add("0");
                        
                        tempList2.add(temp[val]);
                        tempList2.add("1");
                        tempList2.add("0");
                        tempList2.add("0");
                    }
                    else if(val == 3){
                        tempList1.add(temp[val -2]);
                        tempList1.add("0");
                        tempList1.add("0");
                        tempList1.add("1");
                        
                        tempList2.add(temp[val - 1]);
                        tempList2.add("0");
                        tempList2.add("0");
                        tempList2.add("1");
                    }
                    if(val!= 0){
                        list.add((ArrayList<String>) tempList1.clone());
                        list.add((ArrayList<String>) tempList2.clone());
                        
                    }
                    
                }
                
                
                
               
            }
            sort(list,0,list.size()-1);
           
            String colNames[] = {"Player name" ,"Wins","Losses" ,"Draws"};
            String [][] finalArr = new String[list.size()][4];
            for(int i = 0; i < list.size();i++){
                
               //finalArr[i] =  (String[]) list.get(i).toArray();
                for(int j = 0 ; j< list.get(0).size();j++){
                    //result += list.get(i).get(j) + "    ";
                    finalArr[i][j] = list.get(i).get(j);
                }
                
            }
            leageList = new JTable(finalArr, colNames);
            scrollPane = new JScrollPane(leageList);
            leageList.setFillsViewportHeight(true);
            
            leaderBoardP.add(scrollPane);
            c1.show(parentP,  e.getActionCommand());
            
        }
    }
    private void sort(ArrayList<ArrayList<String>> list,int low,int high){
        if (low < high)
        {
            /* pi is partitioning index, arr[pi] is 
              now at right place */
            int pi = partition(list, low, high);
 
            // Recursively sort elements before
            // partition and after partition
            sort(list, low, pi-1);
            sort(list, pi+1, high);
        }
    }
     private int partition(ArrayList<ArrayList<String>> list,int low,int high){
        int pivot = Integer.parseInt(list.get(high).get(1)); 
        int i = (low-1); // index of smaller element
        for (int j=low; j<high; j++)
        {
            // If current element is smaller than or
            // equal to pivot
            if (Integer.parseInt(list.get(j).get(1)) >= pivot)
            {
                i++;
 
                // swap arr[i] and arr[j]
                /*double temp = Integer.parseInt(list.get(i).get(1));
                resultOfFitness.set(i, resultOfFitness.get(j));
                resultOfFitness.set(j,temp)  ;*/
                
                
                ArrayList<String> tempList = list.get(i);
                list.set(i, list.get(j));
                list.set(j, tempList);
                
            }
        }
 
        // swap arr[i+1] and arr[high] (or pivot)
      /* double temp = resultOfFitness.get(i+1);
        resultOfFitness.set(i+1,resultOfFitness.get(high));
        resultOfFitness.set(high,temp);*/
        
         ArrayList<String> tempList = list.get(i+1);
         list.set(i+1, list.get(high));
         list.set(high, tempList);
         
         
        return i+1;
    }
    
}
