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
    
    public static boolean salvar(Pedido pedido) {
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
                
                // Agora, você precisará salvar os itens do pedido
                //for (Integer idProduto : pedido.getIdProdutos()) {
                //   salvarItemPedido(pedido.getIdPedido(), idProduto);
                //}
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
    
    private static void salvarItemPedido(int idPedido, int idProduto, int quantidade) {
        Connection conexao = null;
        PreparedStatement comandoSQL = null;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection(url, login, senha);
            
            comandoSQL = conexao.prepareStatement("INSERT INTO itens_venda (id_venda, id_produto, quantidade, preco_unitario) VALUES (?, ?, ?, ?)");
            comandoSQL.setInt(1, idPedido);
            comandoSQL.setInt(2, idProduto);
            comandoSQL.setInt(3, quantidade);
            // Você precisará obter o preço do produto do banco de dados ou passá-lo como parâmetro.
            // Aqui, estou assumindo que o preço é 0.0. Substitua conforme necessário.
            comandoSQL.setDouble(4, 0.0);
            
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
}
