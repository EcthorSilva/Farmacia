package controller;

import com.formdev.flatlaf.themes.FlatMacLightLaf;
import javax.swing.UIManager;
import view.TelaLogin;

public class main {
    public static void main(String[] args) {
        // Muda o visual do Java Swing
        try {
            UIManager.setLookAndFeel(new FlatMacLightLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        
        System.out.println("Carregando...");
        System.out.println("Pronto!");
        
        TelaLogin login = new TelaLogin(); // Instancia
        login.setVisible(true);
    }
}