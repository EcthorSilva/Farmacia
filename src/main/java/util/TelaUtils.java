package util;

/**
 * Classe utilitaria para gerenciar o posicionamento das telas
 * 
 * @author Ectho
 */

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import view.TelaLogin;

public class TelaUtils {
    
    
    /**
    * Esta função faz com que a nova tela abra no mesmo lugar que a tela atual e fecha a tela atual.
    * 
    * @param telaAtual A tela atual que será fechada.
    * @param novaTela  A nova tela que será aberta.
    */
    public static void abrirNovaTela(JFrame telaAtual, JFrame novaTela) {
        
        

        // Obtém a posição da tela atual
        int x = telaAtual.getLocation().x;
        int y = telaAtual.getLocation().y;
        
        // Fecha a tela atual
        telaAtual.dispose();
        
        // Configura a posição da nova tela em relação à posição da tela anterior
        novaTela.setLocation(x, y);
        
        // Exibe a nova tela
        novaTela.setVisible(true);
    }
    
    /**
    * Realiza o logout exibindo uma mensagem de confirmação.
    * Se o usuário confirmar, abre a tela de login.
    * 
    * @param telaAtual A tela atual que será fechada após o logout.
    */
    public static void logout(JFrame telaAtual){
        int opt = JOptionPane.showConfirmDialog(null,
                "Deseja realmente sair do aplicativo?",
                "Selecione uma Opção",
                JOptionPane.OK_CANCEL_OPTION);
        
        if (opt == JOptionPane.OK_OPTION) {
            TelaLogin TelaLogin = new TelaLogin();
            abrirNovaTela(telaAtual, TelaLogin);
        }
    }
}