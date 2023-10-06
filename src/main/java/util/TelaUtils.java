package util;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import view.TelaLogin;

public class TelaUtils {
    // Faz com que a nova tela abra no mesmo lugar que a anterior
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
    
    // validação para o logout
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