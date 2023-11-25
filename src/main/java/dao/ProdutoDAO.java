package dao;

// Data Access Object
import model.Produto;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Resposnavel para fazer o insert no banco de dados
 * 
 * @author Ecthor
 */
public class ProdutoDAO {
    // variaveis para acessar o banco
    private static String url = "jdbc:mysql://localhost:3306/farmacia";
    private static String login = "root";
    private static String senha = "root";
    
    // CREATE
    public static boolean salvar(Produto obj){
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
            comandoSQL = conexao.prepareStatement("INSERT INTO produtos (nome, id_categoria, fabricante, preco_unitario, quantidade_estoque) VALUES (?, ?, ?, ?, ?)",
        PreparedStatement.RETURN_GENERATED_KEYS); //INSERT INTO produtos (nome, id_categoria, fabricante, preco_unitario, quantidade_estoque)VALUES ('Nome do Produto', id_da_categoria, 'Fabricante do Produto', preço_do_produto, quantidade_em_estoque);

            // pega o nomeProduto
            comandoSQL.setString(1, obj.getNomeProduto());
            // pega o categoriaProduto
            comandoSQL.setInt(2, obj.getCategoria());
            // pega o fabricante
            comandoSQL.setString(3, obj.getFabricante());
            // pega o preco
            comandoSQL.setDouble(4, obj.getPreco());
            // pega o quantidade
            comandoSQL.setInt(5, obj.getQuantidade());
            
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
                        obj.setIdProduto(idGerado);
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
    // READ
    public static ArrayList<Produto> listar(){
        ArrayList<Produto> lista = new ArrayList<>();
        
        // classes para utilizar
        Connection conexao = null;
        PreparedStatement comandoSQL = null;
        ResultSet rs = null; // recebe o resultado da consulta
        
        try{
            // Passo1 - carregar o driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Passo2 - abrir a conexão com o banco
            conexao = DriverManager.getConnection(url, login, senha);
            
            // Adicionando JOIN com a tabela categoriaprodutos
            String query = "SELECT p.id, p.nome, c.desccricao as categoria, p.fabricante, p.preco_unitario, p.quantidade_estoque " +
                       "FROM produtos p " +
                       "JOIN categoriaProdutos c ON p.id_categoria = c.id";
            
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
                    String categoria = rs.getString("categoria");
                    String fabricante = rs.getString("fabricante");
                    double preco = rs.getDouble("preco_unitario");
                    int quantidade = rs.getInt("quantidade_estoque");

                    Produto item = new Produto(id, nome, categoria, fabricante, preco, quantidade);
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
                    Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        return lista;
    }
    // UPDATE
    public static boolean alterar(Produto obj){
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
            comandoSQL = conexao.prepareStatement("UPDATE produtos SET nome = ?, id_categoria = ?, fabricante = ?, preco_unitario = ?, quantidade_estoque = ? WHERE id = ?");
            
            // pega o nomeProduto
            comandoSQL.setString(1, obj.getNomeProduto());
            // pega o categoriaProduto
            comandoSQL.setInt(2, obj.getCategoria());
            // pega o fabricante
            comandoSQL.setString(3, obj.getFabricante());
            // pega o preco
            comandoSQL.setDouble(4, obj.getPreco());
            // pega o quantidade
            comandoSQL.setInt(5, obj.getQuantidade());
            
            comandoSQL.setInt(6, obj.getIdProduto());
            
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
            ex.printStackTrace();
            retorno = false;
            System.out.println("SQLException: " + ex.getMessage());
        }
        
        return retorno;
    }
    
    // DELETE
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
            comandoSQL = conexao.prepareStatement("DELETE FROM produtos WHERE id = ?");
            
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
    
    // SEARCH
    public static ArrayList<Produto> buscarPorID(int idProduto){
        ArrayList<Produto> lista = new ArrayList<>();
        
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
            comandoSQL = conexao.prepareStatement("SELECT * FROM produtos WHERE id = ? ");
            
            comandoSQL.setInt(1, idProduto);
            
            // Passo4 - Executar a CONSULTA
            rs = comandoSQL.executeQuery();
            
            // se existir uma tabela ele inicia um while
            if (rs != null){
                while(rs.next()){
                    // cada volta do while cria um objeto para cada linha da lista
                    int id = rs.getInt("id");
                    String nome = rs.getString("nome");
                    int categoria = rs.getInt("id_categoria");
                    String fabricante = rs.getString("fabricante");
                    double preco = rs.getDouble("preco_unitario");
                    int quantidade = rs.getInt("quantidade_estoque");
                    
                    Produto item = new Produto(id, nome, categoria, fabricante, preco, quantidade);
                    
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
