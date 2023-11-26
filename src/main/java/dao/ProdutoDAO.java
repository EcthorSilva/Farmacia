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

    /**
     * São variaveis staticas que aemazernam as informaçoes de conexao com o
     * bando de dados atraves da: URl: Representa o enderço do bando de dados.
     * Login: Credencial de acesso para entrar no bando de dados Senha:
     * Credencial de dados para acessar o banco de dados juntamente com o Login.
     * Juntando o LOgin e Senha e possivel fazer a liga;cao do Banco de dados
     * com o projeto java.
     */
    // variaveis para acessar o banco
    private static String url = "jdbc:mysql://localhost:3306/farmacia";
    private static String login = "root";
    private static String senha = "root";

    /**
     * Salva um novo produto no banco de dados.
     *
     * Este método realiza a inserção de um objeto Produto na tabela "produtos"
     * do banco de dados. A conexão é estabelecida utilizando JDBC para MySQL.
     * Após a inserção bem-sucedida, o ID gerado automaticamente pelo banco é
     * recuperado e atualizado no objeto Produto fornecido.
     *
     * @param obj O objeto Produto a ser salvo no banco de dados.
     * @return true se a operação de inserção for bem-sucedida, false caso de
     * falha.
     */
    // CREATE
    public static boolean salvar(Produto obj) {
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
            if (linhasAfetadas > 0) {
                // então foi gravado no bd com sucesso
                retorno = true;

                // pega o ID auto incremental do banco
                ResultSet rs = comandoSQL.getGeneratedKeys();
                if (rs != null) {
                    while (rs.next()) {
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
    
    
   /**
    * Retorna uma lista de todos os produtos no banco de dados.
     *
    * Este método realiza uma consulta SQL que junta as tabelas "produtos" e "categoriaProdutos" usando JOIN.
    * Cada produto é mapeado para um objeto Produto, incluindo informações adicionais da categoria do produto.
    * A conexão é estabelecida utilizando JDBC para MySQL.
    *
    * @return Uma lista de objetos contendo informações de ID, nome, categoria, fabricante, preço unitário
    * e quantidade em estoque de cada produto.
    * retorna uma lista vazia se não houver produtos no banco de dados ou em caso de falha na execução.
    */
    // READ
    public static ArrayList<Produto> listar() {
        ArrayList<Produto> lista = new ArrayList<>();

        // classes para utilizar
        Connection conexao = null;
        PreparedStatement comandoSQL = null;
        ResultSet rs = null; // recebe o resultado da consulta

        try {
            // Passo1 - carregar o driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Passo2 - abrir a conexão com o banco
            conexao = DriverManager.getConnection(url, login, senha);

            // Adicionando JOIN com a tabela categoriaprodutos
            String query = "SELECT p.id, p.nome, c.desccricao as categoria, p.fabricante, p.preco_unitario, p.quantidade_estoque "
                    + "FROM produtos p "
                    + "JOIN categoriaProdutos c ON p.id_categoria = c.id";

            // Passo3 - Preparar o comando SQL
            comandoSQL = conexao.prepareStatement(query);

            // Passo4 - Executar a CONSULTA
            rs = comandoSQL.executeQuery();

            // se existir uma tabela ele inicia um while
            if (rs != null) {
                while (rs.next()) {
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

        } catch (Exception e) {
            lista = null;
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return lista;
    }
    
    /**
    * Atualiza as informações de um produto no banco de dados.
    *
    * Este método realiza uma operação de atualização na tabela "produtos" do banco de dados,
    * alterando as informações do produto especificado pelo ID. A conexão é estabelecida utilizando
    * JDBC para MySQL.
    *
    * @param obj O objeto {@link Produto} contendo as novas informações a serem atualizadas no banco de dados.
    * @return true se a operação de atualização for bem-sucedida, false caso contrário.
    */
    // UPDATE
    public static boolean alterar(Produto obj) {
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
            if (linhasAfetadas > 0) {
                // então foi gravado no bd com sucesso
                retorno = true;
            }
        } catch (ClassNotFoundException ex) {
            retorno = false;
        } catch (SQLException ex) {
            ex.printStackTrace();
            retorno = false;
            System.out.println("SQLException: " + ex.getMessage());
        }

        return retorno;
    }
    
    /**
    * Exclui um registro da tabela "produtos" no banco de dados com o ID fornecido.
    *
    * @param idExcluir O ID do registro a ser excluído.
    * @return true se o registro foi excluído com sucesso, false caso contrário.
    */
    // DELETE
    public static boolean excluir(int idExcluir) {
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
            if (linhasAfetadas > 0) {
                // então foi gravado no bd com sucesso
                retorno = true;
            }
        } catch (ClassNotFoundException ex) {
            retorno = false;
        } catch (SQLException ex) {
            retorno = false;
        }

        return retorno;
    }
    
    /**
    * Busca um produto no banco de dados com base no ID fornecido.
    *
    * @param idProduto O ID do produto a ser pesquisado.
    * @return Uma lista de objetos Produto que correspondem ao ID, ou null em caso de erro.
    */
    // SEARCH
    public static ArrayList<Produto> buscarPorID(int idProduto) {
        ArrayList<Produto> lista = new ArrayList<>();

        // classes para utilizar
        Connection conexao = null;
        PreparedStatement comandoSQL = null;
        ResultSet rs = null; // recebe o resultado da consulta

        try {
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
            if (rs != null) {
                while (rs.next()) {
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
        } finally {
            if (conexao != null) {
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
