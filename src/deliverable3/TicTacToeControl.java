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
public class TicTacToeControl {
    
    TTTWebService webService;
    JPanelsUI ui;
    
    private String username;
    private String autoKey;
    
    public TicTacToeControl() {
        webService = new TTTWebService();
        ui = new JPanelsUI(this);
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
        int autoKey = webService.login(username, password);
        
        if (autoKey == 0) {
            JOptionPane.showMessageDialog(null, "Couldn't Log in");
            return false;
        } else {
            return true;
        }
    }
}
