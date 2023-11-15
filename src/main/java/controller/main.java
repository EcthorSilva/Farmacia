package controller;

import com.formdev.flatlaf.themes.FlatMacLightLaf;
import javax.swing.UIManager;
import view.TelaLogin;

/**
 * Classe principal que inicia a aplicação.
 * Configura o visual do Java Swing e exibe a tela de login.
 * 
 * @author Ectho
 */
public class main {
    public static void main(String[] args) {
        /**
        * Tenta configurar o Look and Feel (LaF) do JavaSwing para o tema FlatMacLightLaf.
        * 
        * Se tudo der certo ele aplicará o novo visual a nossa aplicação. Caso contrário ele irá imprimir uma mensagem de erro.
        * 
        * @throws Exception Se ocorrer uma exceção ao configurar o Look and Feel.
        */
        try {
            UIManager.setLookAndFeel(new FlatMacLightLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        
        System.out.println("Carregando...");
        System.out.println("Pronto!");
        
        TelaLogin login = new TelaLogin(); // Instancia
        login.setLocationRelativeTo(null); // Faz a tela login abrir no centro
        login.setVisible(true);
    }
}