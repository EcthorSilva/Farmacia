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
import model.Cliente;

/**
 *
 * @author guilh
 */
public class ClienteDAO {
    
    private static String url = "jdbc:mysql://localhost:3306/farmacia";
    private static String login = "root";
    private static String senha = "root";
    
    
    public static boolean salvar(Cliente obj){
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
            comandoSQL = conexao.prepareStatement("INSERT INTO Cliente (nomeCompleto, dataNascimento, cpf, sexo, email, celular) VALUES (?, ?, ?,?, ?, ?)",
        PreparedStatement.RETURN_GENERATED_KEYS);

            // pega o NOME COMPLETO
            comandoSQL.setString(1, obj.getNome());
            // pega o CELULAR
            comandoSQL.setString(2, obj.getDataNascimento());
            // pega o CPF
            comandoSQL.setString(3, obj.getCpf());
            // pega o EMAIL
            comandoSQL.setString(4, obj.getSexo());
            // pega a DATA DE NASCIMENTO
            comandoSQL.setString(5, obj.getEmail());
            // pega o SEXO
            comandoSQL.setString(6, obj.getCelular());
            
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
                        obj.setIdCliente(idGerado);
                    }
                }
            }
        } catch (ClassNotFoundException ex) {
            retorno = false;
        } catch (SQLException ex) {
            retorno = false;
        }
        
        return retorno;
    }
    
    public static boolean alterar(Cliente obj){
        boolean retorno = false;
        // instanciando objeto para realizar a conexão
        Connection conexao = null;
        // 
        PreparedStatement comandoSQL = null;
        
        // receita de bolo para acessar o banco de dados
        try {
            // Passo1 - carregar o driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Passo2 - abrir a conexão com o banco
            conexao = DriverManager.getConnection(url, login, senha);
            // Passo3 - Preparar o comando SQL
            comandoSQL = conexao.prepareStatement("UPDATE Cliente SET nomeCompleto = ?, dataNascimento = ?, cpf = ?, sexo = ?, email = ?, celular = ? WHERE idCliente = ?");
            
            // pega o nome
            comandoSQL.setString(1, obj.getNome());
            // pega a data de nascimento
            comandoSQL.setString(2, obj.getDataNascimento());
            // pega o cpf
            comandoSQL.setString(3, obj.getCpf());
            // pega o sexo
            comandoSQL.setString(4, obj.getSexo());
            // pega o email
            comandoSQL.setString(5, obj.getEmail());
            // pega o celular.
            comandoSQL.setString(6, obj.getCelular());
            
            // Passo4 - Executar o comando
            int linhasAfetadas = comandoSQL.executeUpdate();
            // se as linhas afetadas forem maior que 0 
            if(linhasAfetadas > 0){
                // então foi gravado no bd com sucesso
                retorno = true;
            }
        }catch(ClassNotFoundException ex){
            retorno = false;
        } catch (SQLException ex) {
            retorno = false;
        }
        
        return retorno;
    }
    // READ
    public static ArrayList<Cliente> listar(){
        ArrayList<Cliente> lista = new ArrayList<>();
        
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
            comandoSQL = conexao.prepareStatement("SELECT * FROM Cliente");
            
            // Passo4 - Executar a CONSULTA
            rs = comandoSQL.executeQuery();
            
            // se existir uma tabela ele inicia um while
            if (rs != null){
                while(rs.next()){
                    // cada volta do while cria um objeto para cada linha da lista
                    int id = rs.getInt("idCliente");
                    String nome = rs.getString("nomeCompleto");
                    String dataNascimento = rs.getString("dataNascimento");
                    String cpf = rs.getString("cpf");
                    String sexo = rs.getString("sexo");
                    String email = rs.getString("email");                   
                    String celular = rs.getString("celular");
                    
                    Cliente item = new Cliente(id, nome, dataNascimento, cpf, sexo, email, celular);
                    
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
                    Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        return lista;
    }
    public static boolean excluir(int idExcluir){
        boolean retorno = false;
        
        // instanciando objeto para realizar a conexão
        Connection conexao = null;
        // 
        PreparedStatement comandoSQL = null;
        
        // receita de bolo para acessar o banco de dados
        try {
            // Passo1 - carregar o driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Passo2 - abrir a conexão com o banco
            conexao = DriverManager.getConnection(url, login, senha);
            // Passo3 - Preparar o comando SQL
            comandoSQL = conexao.prepareStatement("DELETE FROM Cliente WHERE idCliente = ?");
            
            // pega o valor do ID
            comandoSQL.setInt(1, idExcluir);
            
            // Passo4 - Executar o comando
            int linhasAfetadas = comandoSQL.executeUpdate();
            // se as linhas afetadas forem maior que 0 
            if(linhasAfetadas > 0){
                // então foi gravado no bd com sucesso
                retorno = true;
            }
        }catch(ClassNotFoundException ex){
            retorno = false;
        } catch (SQLException ex) {
            retorno = false;
        }
        
        return retorno;
    }
    
        public static ArrayList<Cliente> buscarPorCpf(String cpf){
        ArrayList<Cliente> lista = new ArrayList<>();
        
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
            comandoSQL = conexao.prepareStatement("SELECT * FROM Cliente WHERE cpf = ? ");
            
            comandoSQL.setString(1, cpf);
            
            // Passo4 - Executar a CONSULTA
            rs = comandoSQL.executeQuery();
            
            // se existir uma tabela ele inicia um while
            if (rs != null){
                while(rs.next()){
                    // cada volta do while cria um objeto para cada linha da lista
                    int id = rs.getInt("idCliente");
                    String nome = rs.getString("nomeCompleto");
                    String celular = rs.getString("celular");
                    String cpfBusca = rs.getString("cpf");
                    String email = rs.getString("email");
                    String dataNascimento = rs.getString("data_nascimento");
                    String sexo = rs.getString("sexo");
                    
                   Cliente item = new Cliente(id, nome, celular, cpfBusca, email, dataNascimento, sexo);
                    
                    lista.add(item);
                }
            }
            
        } catch (Exception e) {
            lista = null;
        }finally{
            if(conexao != null){
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        return lista;
    }
}
