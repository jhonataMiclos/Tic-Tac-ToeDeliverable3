/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package deliverable3;

/**
 *
 * @author J Murphy
 */


import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

class WindowEventHandler extends WindowAdapter {
    public void windowClosing(WindowEvent evt) {
        System.exit(0);
    }
}

public class CreatingWindow {

    /**
     * @param args the command line arguments
     * 
     */
    
    public static void main(String[] args) {
        
       JPanelsTest t = new JPanelsTest();
    }
}