/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package deliverable3;

import javax.swing.*;
import javax.xml.ws.WebServiceRef;
import tictactoews.TicTacToeWebService;
import tictactoews.TicTacToeWebService_Service;

/**
 *
 * @author Vilius
 */
public class TicTacToeControl implements Runnable {
    
    @WebServiceRef(wsdlLocation="http://localhost:8080/"+"WebApplication/TicTacToeWebService?wsdl")
            static TicTacToeWebService_Service web;
    
    TicTacToeWebService portid;
    
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
        
        web = new TicTacToeWebService_Service();
        portid = web.getTicTacToeWebServicePort();
        
        ui = new JPanelsUI(this);
    }
    
    @Override
    public void run() {
        while(true) {
            // Waiting for a player to join the hosted game
            if (gameState == -1) {
                String result = portid.getGameState(gameAutoKey);
                
                if (result.matches("(-)?\\d+")) {
                    if (result.equals("0")) {
                        gameState = 0;
                        currentPlayerNum = playerNum;
                        ui.switchPage("GAMEBOARDPANEL");
                        ui.turnIndicatorColor(currentPlayerNum, playerNum);
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
                String result = portid.getBoard(gameAutoKey);
                if (result.startsWith("ERROR")) {
                    if (result.equals("ERROR-DB")) {
                        JOptionPane.showMessageDialog(null, result);
                    }
                } else {
                    String[] splitResult = result.split("\n");
                    
                    if (splitResult.length > 0) {
                        splitResult[splitResult.length - 1].replaceAll("\\{", "");
                        splitResult[splitResult.length - 1].replaceAll("\\}", "");
                        String[] mostRecentMove = splitResult[splitResult.length - 1].split(",");
                        
                        if (mostRecentMove[0].equals(Integer.toString(playerAutoKey))) {
                            try {
                                Thread.sleep(1000);
                            } catch(Exception e) {
                                System.out.println(e.getMessage());
                            }
                        }
                        else {
                            int x, y, enemyPlayerNum;
                            x = Integer.parseInt(mostRecentMove[1]);
                            y = Integer.parseInt(mostRecentMove[2]);
                            enemyPlayerNum = playerNum == 1 ? 2 : 1;
                            
                            ui.colorSquare(x, y, enemyPlayerNum);
                            currentPlayerNum = playerNum;
                            ui.turnIndicatorColor(currentPlayerNum, playerNum);
                            checkGameOver();
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
        String result = portid.register(username, password, fName, lName);
        
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
        playerAutoKey = portid.login(username, password);
        
        if (playerAutoKey == -1) {
            JOptionPane.showMessageDialog(null, "Couldn't Log in");
            return false;
        } else {
            return true;
        }
    }
    
    public boolean hostGame(){
       String result = portid.newGame(playerAutoKey);
       
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
        String temp = portid.showOpenGames();
        String tempArr[] = temp.split("\n");
        return tempArr;
    }
    
    public boolean joinSelectedGame(int gameId){
        String result = portid.joinGame(playerAutoKey, gameId);
        
        if(result.matches("1")){
            gameAutoKey= gameId;
            playerNum = 2;
            currentPlayerNum = 1;
            gameState = 0;
            ui.turnIndicatorColor(currentPlayerNum, playerNum);
            return true;
            
        }else{
            JOptionPane.showMessageDialog(null, "JOIN-" + result);
            return false;
        }
    }
    
    public void clickSquare(int x, int y) {
        if (currentPlayerNum == playerNum) {
            String result = portid.checkSquare(x, y, gameAutoKey);
            if (result.equals("0")) {
                result = portid.takeSquare(x, y, gameAutoKey, playerAutoKey);
                
                if (result.equals("1")) {
                    ui.colorSquare(x, y, playerNum);
                    
                    // Set currentPlayerNum to enemy's playerNum
                    currentPlayerNum = playerNum == 1 ? 2 : 1;
                    ui.turnIndicatorColor(currentPlayerNum, playerNum);
                    checkGameOver();
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
    
    public void reset() {
        gameState = -2;
        ui.reset();
    }
    
    public void checkGameOver() {
        boolean gameOver = false;
        String result = portid.checkWin(gameAutoKey);
        System.out.println("in checkGameOver(); result = " + result);
        
        if (result.startsWith("ERROR")) {
            JOptionPane.showMessageDialog(null, result);
        }
        else {
            int winType = Integer.parseInt(result);
            int enemyPlayerNum = playerNum == 1 ? 2 : 1;
            portid.setGameState(gameAutoKey, winType);
            
            if (winType == playerNum) {
                gameOver = true;
                ui.turnIndicatorColor(currentPlayerNum, playerNum);
                JOptionPane.showMessageDialog(null, "Game over. You won!");
            }
            else if (winType == enemyPlayerNum) {
                gameOver = true;
                ui.turnIndicatorColor(currentPlayerNum, playerNum);
                JOptionPane.showMessageDialog(null, "Game over. You lost!");
            }
            else if (winType == 3) {
                gameOver = true;
                ui.turnIndicatorColor(currentPlayerNum, playerNum);
                JOptionPane.showMessageDialog(null, "Game over. It's a draw!");
            }
        }
        
        if (gameOver) {
            reset();
            ui.switchPage("MATCHMAKINGSELECTION");
        }
    }
}
