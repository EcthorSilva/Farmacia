/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Funcionario;

/**
 * Resposnavel para fazer o insert no banco de dados
 * 
 * @author Ecthor
 */
public class FuncionarioDAO {
    // variaveis para acessar o banco
    private static String url = "jdbc:mysql://localhost:3306/farmacia";
    private static String login = "root";
    private static String senha = "root";
    
    // CREATE
    public static boolean salvar(Funcionario obj){
        boolean retorno = false;
        
        // instanciando objeto para realizar a conexão
        Connection conexao = null;
        // 
        PreparedStatement comandoSQL = null;
        
        try {
            // Passo1 - carregar o driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Passo2 - abrir a conexão com o banco
            conexao = DriverManager.getConnection(url, login, senha);
            // Passo3 - Preparar o comando SQL
            comandoSQL = conexao.prepareStatement("INSERT INTO Funcionario (nomeCompleto, dataNascimento, sexo, cpf, cargo, salario, horarioTrabalho) VALUES (?, ?, ?, ?, ?, ?, ?)",
        PreparedStatement.RETURN_GENERATED_KEYS);

            // pega o Nome
            comandoSQL.setString(1, obj.getNome());
            // pega o Data de nascimento
            comandoSQL.setString(2, obj.getDataNascimento());
            // pega o Sexo
            comandoSQL.setString(3, obj.getSexo());
            // pega o CPF
            comandoSQL.setString(4, obj.getCpf());
            // pega o Cargo
            comandoSQL.setString(5, obj.getCargo());
            // pega o Salario
            comandoSQL.setDouble(6, obj.getSalario());
            // pega o Periodo
            comandoSQL.setString(7, obj.getPeriodo());
            
            // Passo4 - Executar o comando
            int linhasAfetadas = comandoSQL.executeUpdate();
            // se as linhas afetadas forem maior que 0 
            if(linhasAfetadas > 0){
                // então foi gravado no bd com sucesso
                retorno = true;
                
                // pega o ID auto incremental do banco
                ResultSet rs = comandoSQL.getGeneratedKeys();
                if (rs != null){
                    while(rs.next()){
                        int idGerado = rs.getInt(1);
                        obj.setIdFuncionario(idGerado);
                    }
                }
            }
        } catch (ClassNotFoundException ex) {
            retorno = false;
        } catch (SQLException ex) {
            retorno = false;
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    // READ
    public static ArrayList<Funcionario> listar(){
        ArrayList<Funcionario> lista = new ArrayList<>();
        
        // classes para utilizar
        Connection conexao = null;
        PreparedStatement comandoSQL = null;
        ResultSet rs = null; // recebe o resultado da consulta
        
        try{
            // Passo1 - carregar o driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Passo2 - abrir a conexão com o banco
            conexao = DriverManager.getConnection(url, login, senha);
            
            // Passo3 - Preparar o comando SQL
            comandoSQL = conexao.prepareStatement("SELECT * FROM Funcionario");
            
            // Passo4 - Executar a CONSULTA
            rs = comandoSQL.executeQuery();
            
            // se existir uma tabela ele inicia um while
            if (rs != null){
                while(rs.next()){
                    // cada volta do while cria um objeto para cada linha da lista
                    int idFuncionario = rs.getInt("idFuncionario");
                    String nomeFuncionario = rs.getString("nomeCompleto");
                    //String dataNascimento = rs.getString("dataNascimento");
                    //String sexo = rs.getString("sexo");
                    //String cpf = rs.getString("cpf");
                    String cargoFuncionario = rs.getString("cargo");
                    //double salarioVendedor = rs.getDouble("salario");
                    String horarioDeTrabalho = rs.getString("horarioTrabalho");
                    
                    Funcionario item = new Funcionario(idFuncionario, nomeFuncionario, cargoFuncionario, horarioDeTrabalho);
                    // Funcionario item = new Funcionario(idFuncionario, nomeFuncionario, dataNascimento, sexo, cpf, cargoFuncionario, salarioVendedor, horarioDeTrabalho);
                    lista.add(item);
                }
            }
        }catch (Exception e) {
            lista = null;
        }finally{
            if(conexao != null){
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return lista;
    }
}
