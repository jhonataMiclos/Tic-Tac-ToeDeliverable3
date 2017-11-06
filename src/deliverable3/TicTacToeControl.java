/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deliverable3;

import javax.swing.*;

/**
 *
 * @author Vilius
 */
public class TicTacToeControl implements Runnable {
    
    TTTWebService webService;
    JPanelsUI ui;
    
    private String username;
    private int gameState;
    private int playerAutoKey;
    private int gameAutoKey;
    private int playerNum;
    private int currentPlayerNum;
    
    public TicTacToeControl() {
        currentPlayerNum = 0;
        gameState = -2;     // -2 means not looking for enemy and not playing
        
        webService = new TTTWebService();
        ui = new JPanelsUI(this);
    }
    
    @Override
    public void run() {
        while(true) {
            // Waiting for a player to join the hosted game
            if (gameState == -1) {
                String result = webService.getGameState(gameAutoKey);
                
                if (result.matches("(-)?\\d+")) {
                    if (result.equals("0")) {
                        gameState = 0;
                        currentPlayerNum = playerNum;
                        ui.switchPage("GAMEBOARDPANEL");
                    } else {
                        try {
                            Thread.sleep(1000);
                        } catch(Exception e) {
                            System.out.println(e.getMessage());
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, result);
                }
            } 
            // Enemy's turn
            else if (currentPlayerNum != playerNum) {
                String result = webService.getBoard(gameAutoKey);
                if (result.startsWith("ERROR")) {
                    if (result.equals("ERROR-DB")) {
                        JOptionPane.showMessageDialog(null, result);
                    }
                } else {
                    String[] splitResult = result.split("\n");
                    if (splitResult.length > 0) {
                        splitResult[splitResult.length - 1].replaceAll("\\{", "");
                        splitResult[splitResult.length - 1].replaceAll("\\}", "");
                        String[] mostRecentMove = splitResult[splitResult.length - 1].split(", ");
                        
                        if (mostRecentMove.equals("playerNum")) {
                            try {
                                Thread.sleep(1000);
                            } catch(Exception e) {
                                System.out.println(e.getMessage());
                            }
                        }
                        else {
                            currentPlayerNum = playerNum;
                            // TODO Check winner
                        }
                    }
                }
                
                
            }
            try {
                Thread.sleep(1000);
            } catch(Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    public boolean registerUser(String username, String password, String fName, String lName) {
        String result = webService.register(username, password, fName, lName);
        
        // If it's an integer (autokey from users table)
        if (result.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "Successfully registered! Get ready for the most anticipated game of the year!!");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "REGISTER-" + result);
            return false;
        }
    }
    
    public boolean loginUser(String username, String password) {
        this.username = username;
        playerAutoKey = webService.login(username, password);
        
        if (playerAutoKey == 0) {
            JOptionPane.showMessageDialog(null, "Couldn't Log in");
            return false;
        } else {
            return true;
        }
    }
    
    public boolean hostGame(){
       String result = webService.newGame(playerAutoKey);
       
       if (result.matches("\\d+")) {
            gameAutoKey = Integer.parseInt(result);
            gameState = -1;
            playerNum = 1;
            currentPlayerNum = 1;
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "HOST-" + result);
            return false;
        
        }
    }
    
    public String[] getAllGamesOpen(){
        String temp = webService.showOpenGames();
        String tempArr[] = temp.split("\n");
        return tempArr;
    }
    
    public boolean joinSelectedGame(int gameId){
        String result = webService.joinGame(playerAutoKey, gameId);
        
        if(result.matches("1")){
            gameAutoKey= gameId;
            playerNum = 2;
            gameState = 0;
            return true;
            
        }else{
            JOptionPane.showMessageDialog(null, "JOIN-" + result);
            return false;
        }
    }
    
    public void clickSquare(int x, int y) {
        if (currentPlayerNum == playerNum) {
            String result = webService.checkSquare(x, y, gameAutoKey);
            if (result.equals("0")) {
                result = webService.takeSquare(x, y, gameAutoKey, playerAutoKey);
                
                if (result.equals("1")) {
                    ui.colorSquare(x, y, playerNum);
                    currentPlayerNum = playerNum == 1 ? 2 : 1;
                }
                else {
                    JOptionPane.showMessageDialog(null, result);
                }
            }
            else if (result.equals("1")) {
                JOptionPane.showMessageDialog(null, "The square is already taken");
            }
            else {
                JOptionPane.showMessageDialog(null, result);
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "It's not your turn");
        }
    }
}
