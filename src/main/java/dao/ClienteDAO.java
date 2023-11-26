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
    
    /**
    * Insere um objeto Cliente no banco de dados.
    * 
    * @param obj O objeto Cliente a ser inserido no banco.
    * @return true se a operação de inserção foi bem-sucedida, false caso contrário.
    */
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
            comandoSQL = conexao.prepareStatement("INSERT INTO clientes (nome, cpf, id_genero, email, telefone) VALUES (?, ?, ?, ?, ?)",
        PreparedStatement.RETURN_GENERATED_KEYS);

            // pega o NOME COMPLETO
            comandoSQL.setString(1, obj.getNome());
            // pega o CPF
            comandoSQL.setString(2, obj.getCpf());
            // pega o SEXO
            comandoSQL.setInt(3, obj.getSexo());
            // pega o EMAIL
            comandoSQL.setString(4, obj.getEmail());
            // pega o CELULAR
            comandoSQL.setString(5, obj.getCelular());
            
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
    
    /**
    * Altera as informações de um cliente no banco de dados.
    * 
    * @param obj O objeto Cliente contendo as informações a serem atualizadas.
    * @return Retorna true se a alteração foi realizada com sucesso, false caso contrário.
    */
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
            comandoSQL = conexao.prepareStatement("UPDATE clientes SET nome = ?, cpf = ?, id_genero = ?, email = ?, telefone = ? WHERE id = ?");
            
            // pega o nome
            comandoSQL.setString(1, obj.getNome());
            // pega o cpf
            comandoSQL.setString(2, obj.getCpf());
            // pega o sexo
          
            comandoSQL.setInt(3, obj.getSexo());
            // pega o email
            comandoSQL.setString(4, obj.getEmail());
            // pega o celular.
            comandoSQL.setString(5, obj.getCelular());
            // pega o ID.
            comandoSQL.setInt(6, obj.getIdCliente());
            
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
            
            // Adicionando JOIN com a tabela generos.
            String query = "SELECT c.id, c.nome, c.cpf, g.nome as genero, c.email, c.telefone "
                    + "FROM clientes c JOIN generos g ON c.id_genero = g.id";
            
            // Passo3 - Preparar o comando SQL
            comandoSQL = conexao.prepareStatement(query);
            
            // Passo4 - Executar a CONSULTA
            rs = comandoSQL.executeQuery();
            
            // se existir uma tabela ele inicia um while
            if (rs != null){
                while(rs.next()){
                    // cada volta do while cria um objeto para cada linha da lista
                    int id = rs.getInt("id");
                    String nome = rs.getString("nome");
                    String cpf = rs.getString("cpf");
                    String genero = rs.getString("genero");
                    String email = rs.getString("email");
                    String telefone = rs.getString("telefone");
                    
                    Cliente item = new Cliente(id, nome, cpf, genero, email, telefone);
                    
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
            comandoSQL = conexao.prepareStatement("DELETE FROM clientes WHERE id = ?");
            
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
    
        public static ArrayList<Cliente> buscarPorNome(String nomeCliente){
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
            comandoSQL = conexao.prepareStatement("SELECT * FROM clientes WHERE nome LIKE ?");
            
            // Concatenação para que seja encontrado o sobrenome sem ser propriamente digitado.
            comandoSQL.setString(1, nomeCliente + "%");

            
            // Passo4 - Executar a CONSULTA
            rs = comandoSQL.executeQuery();
            
            // se existir uma tabela ele inicia um while
            if (rs != null){
                while(rs.next()){
                    // cada volta do while cria um objeto para cada linha da lista
                    int id = rs.getInt("id");
                    String nome = rs.getString("nome");
                    String celular = rs.getString("telefone");
                    String cpf = rs.getString("cpf");
                    String email = rs.getString("email");
                    int sexo = rs.getInt("id_genero");
                    
                    Cliente item = new Cliente(id, nome, cpf, sexo, email, celular);
                    
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
