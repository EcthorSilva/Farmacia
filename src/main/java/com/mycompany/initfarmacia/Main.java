
package com.mycompany.initfarmacia;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.UIManager;


public class Main {

    public static void main(String[] args) {
        InitLogin login = new InitLogin();
        login.setVisible(true);
        
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        
    }
}
