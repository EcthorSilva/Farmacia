package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Pedido;

/**
 *
 * @author Ectho
 */
public class PedidoDAO {
    // variaveis para acessar o banco
    private static String url = "jdbc:mysql://localhost:3306/farmacia";
    private static String login = "root";
    private static String senha = "root";
    
    // CREATE
    public static boolean salvarPedido(Pedido pedido) {
        boolean retorno = false;
        Connection conexao = null;
        PreparedStatement comandoSQL = null;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection(url, login, senha);
            
            comandoSQL = conexao.prepareStatement("INSERT INTO vendas (id_cliente, id_funcionario, data_hora, forma_pagamento, total_compra) VALUES (?, ?, NOW(), ?, ?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            
            comandoSQL.setInt(1, pedido.getIdCliente());
            comandoSQL.setInt(2, pedido.getIdVendedor());
            comandoSQL.setString(3, pedido.getFormaPagamento());
            comandoSQL.setDouble(4, pedido.getTotalCompra());
            
            int linhasAfetadas = comandoSQL.executeUpdate();
            
            if (linhasAfetadas > 0) {
                retorno = true;
                
                ResultSet rs = comandoSQL.getGeneratedKeys();
                if (rs != null) {
                    while (rs.next()) {
                        int idGerado = rs.getInt(1);
                        pedido.setIdPedido(idGerado);
                    }
                }
            }
        } catch (ClassNotFoundException ex) {
            retorno = false;
        } catch (SQLException ex) {
            retorno = false;
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Fechar recursos
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        return retorno;
    }
    
    public static void salvarItemPedido(int idPedido, int idProduto, int quantidade, double precoUnitario) {
        Connection conexao = null;
        PreparedStatement comandoSQL = null;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection(url, login, senha);
            
            comandoSQL = conexao.prepareStatement("INSERT INTO itens_venda (id_venda, id_produto, quantidade, preco_unitario) VALUES (?, ?, ?, ?)");
            comandoSQL.setInt(1, idPedido);
            comandoSQL.setInt(2, idProduto);
            comandoSQL.setInt(3, quantidade);
            comandoSQL.setDouble(4, precoUnitario);
            
            comandoSQL.executeUpdate();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Fechar recursos
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    // READ
    public static ArrayList<Pedido> listarPedidos() {
        ArrayList<Pedido> listaPedidos = new ArrayList<>();

        // classes para utilizar
        Connection conexao = null;
        PreparedStatement comandoSQL = null;
        ResultSet rs = null; // recebe o resultado da consulta

        try {
            // Passo1 - carregar o driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Passo2 - abrir a conexão com o banco
            conexao = DriverManager.getConnection(url, login, senha);

            // Adicionando JOIN com as tabelas necessárias
            String query = "SELECT "
                    + "v.id AS id_venda, "
                    + "v.data_hora, "
                    + "v.forma_pagamento, "
                    + "v.total_compra, "
                    + "c.nome AS nome_cliente, "
                    + "f.nome AS nome_funcionario, "
                    + "p.nome AS nome_produto, "
                    + "SUM(iv.quantidade) AS total_quantidade, "
                    + "SUM(iv.preco_unitario * iv.quantidade) AS total_valor "
                    + "FROM vendas v "
                    + "JOIN clientes c ON v.id_cliente = c.id "
                    + "JOIN funcionarios f ON v.id_funcionario = f.id "
                    + "JOIN itens_venda iv ON v.id = iv.id_venda "
                    + "JOIN produtos p ON iv.id_produto = p.id "
                    + "GROUP BY v.id, v.data_hora, v.forma_pagamento, v.total_compra, c.nome, f.nome, p.nome";

            // Passo3 - Preparar o comando SQL
            comandoSQL = conexao.prepareStatement(query);

            // Passo4 - Executar a CONSULTA
            rs = comandoSQL.executeQuery();

            // se existir uma tabela, inicia um while
            if (rs != null) {
                while (rs.next()) {
                    // cada volta do while cria um objeto para cada linha da lista
                    int idVenda = rs.getInt("id_venda");
                    String dataHora = rs.getString("data_hora");
                    String formaPagamento = rs.getString("forma_pagamento");
                    double totalCompra = rs.getDouble("total_compra");
                    String nomeCliente = rs.getString("nome_cliente");
                    String nomeFuncionario = rs.getString("nome_funcionario");
                    String nomeProduto = rs.getString("nome_produto");
                    int totalQuantidade = rs.getInt("total_quantidade");
                    double totalValor = rs.getDouble("total_valor");

                    Pedido pedido = new Pedido(idVenda, dataHora, formaPagamento, totalCompra, nomeCliente, nomeFuncionario, nomeProduto, totalQuantidade, totalValor);
                    listaPedidos.add(pedido);
                }
            }
        } catch (Exception e) {
            listaPedidos = null;
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return listaPedidos;
    }
    
    // SEARCH
    public static ArrayList<Pedido> buscarPorID(int idVenda) {
        ArrayList<Pedido> lista = new ArrayList<>();

        Connection conexao = null;
        PreparedStatement comandoSQL = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection(url, login, senha);

            // Consulta SQL adaptada para a tabela de vendas (pedidos)
            String query = "SELECT "
                    + "v.id AS id_venda, "
                    + "v.data_hora, "
                    + "v.forma_pagamento, "
                    + "v.total_compra, "
                    + "c.nome AS nome_cliente, "
                    + "f.nome AS nome_funcionario, "
                    + "p.nome AS nome_produto, "
                    + "SUM(iv.quantidade) AS total_quantidade, "
                    + "SUM(iv.preco_unitario * iv.quantidade) AS total_valor "
                    + "FROM vendas v "
                    + "JOIN clientes c ON v.id_cliente = c.id "
                    + "JOIN funcionarios f ON v.id_funcionario = f.id "
                    + "JOIN itens_venda iv ON v.id = iv.id_venda "
                    + "JOIN produtos p ON iv.id_produto = p.id "
                    + "WHERE v.id = ? "
                    + "GROUP BY v.id, v.data_hora, v.forma_pagamento, v.total_compra, c.nome, f.nome, p.nome";

            comandoSQL = conexao.prepareStatement(query);
            comandoSQL.setInt(1, idVenda);

            rs = comandoSQL.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    int idPedido = rs.getInt("id_venda");
                    String dataHora = rs.getString("data_hora");
                    String formaPagamento = rs.getString("forma_pagamento");
                    double totalCompra = rs.getDouble("total_compra");
                    String nomeCliente = rs.getString("nome_cliente");
                    String nomeFuncionario = rs.getString("nome_funcionario");
                    String nomeProduto = rs.getString("nome_produto");
                    int totalQuantidade = rs.getInt("total_quantidade");
                    double totalValor = rs.getDouble("total_valor");

                    Pedido pedido = new Pedido(idPedido, dataHora, formaPagamento, totalCompra, nomeCliente, nomeFuncionario, nomeProduto, totalQuantidade, totalValor);
                    lista.add(pedido);
                }
            }
        } catch (Exception e) {
            lista = null;
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return lista;
    }
    
    public static ArrayList<Pedido> buscarPorData(String data) {
        ArrayList<Pedido> lista = new ArrayList<>();

        Connection conexao = null;
        PreparedStatement comandoSQL = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection(url, login, senha);

            // Consulta SQL adaptada para a tabela de vendas (pedidos) e filtrada por data
            String query = "SELECT "
                    + "v.id AS id_venda, "
                    + "v.data_hora, "
                    + "v.forma_pagamento, "
                    + "v.total_compra, "
                    + "c.nome AS nome_cliente, "
                    + "f.nome AS nome_funcionario, "
                    + "p.nome AS nome_produto, "
                    + "SUM(iv.quantidade) AS total_quantidade, "
                    + "SUM(iv.preco_unitario * iv.quantidade) AS total_valor "
                    + "FROM vendas v "
                    + "JOIN clientes c ON v.id_cliente = c.id "
                    + "JOIN funcionarios f ON v.id_funcionario = f.id "
                    + "JOIN itens_venda iv ON v.id = iv.id_venda "
                    + "JOIN produtos p ON iv.id_produto = p.id "
                    + "WHERE DATE(v.data_hora) = DATE(?) " // Filtro por data sem considerar a hora
                    + "GROUP BY v.id, v.data_hora, v.forma_pagamento, v.total_compra, c.nome, f.nome, p.nome";

            comandoSQL = conexao.prepareStatement(query);
            comandoSQL.setString(1, data);

            rs = comandoSQL.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    int idPedido = rs.getInt("id_venda");
                    String dataHora = rs.getString("data_hora");
                    String formaPagamento = rs.getString("forma_pagamento");
                    double totalCompra = rs.getDouble("total_compra");
                    String nomeCliente = rs.getString("nome_cliente");
                    String nomeFuncionario = rs.getString("nome_funcionario");
                    String nomeProduto = rs.getString("nome_produto");
                    int totalQuantidade = rs.getInt("total_quantidade");
                    double totalValor = rs.getDouble("total_valor");

                    Pedido pedido = new Pedido(idPedido, dataHora, formaPagamento, totalCompra, nomeCliente, nomeFuncionario, nomeProduto, totalQuantidade, totalValor);
                    lista.add(pedido);
                }
            }
        } catch (Exception e) {
            lista = null;
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    Logger.getLogger(PedidoDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return lista;
    }
}
