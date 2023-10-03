package controller;

import view.TelaLogin;

public class main {
    public static void main(String[] args) {
        System.out.println("Carregando...");
        System.out.println("Pronto!");
        
        TelaLogin login = new TelaLogin(); // Instancia
        login.setVisible(true);
    }
}