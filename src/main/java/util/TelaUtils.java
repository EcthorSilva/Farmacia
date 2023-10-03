package util;

import javax.swing.JFrame;

public class TelaUtils {
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
}