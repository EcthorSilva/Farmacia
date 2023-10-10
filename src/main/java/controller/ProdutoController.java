package controller;

import model.Produto;
import javax.swing.table.DefaultTableModel;

public class ProdutoController {
    private DefaultTableModel tableModel;

    public ProdutoController(DefaultTableModel tableModel) {
        this.tableModel = tableModel;
    }
    
    public void adicionarProduto(Produto produto) {
        // Adicionar o produto ao modelo da tabela
        Object[] rowData = {produto.getNomeProduto(), produto.getCategoriaProduto(), produto.getPreco(), produto.getQuantidade()};
        tableModel.addRow(rowData);
    }
    
    public void atualizarProduto(int codigo, Produto novoProduto) {
        // Atualizar o produto na tabela e na fonte de dados (banco de dados, por exemplo)
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if ((int) tableModel.getValueAt(i, 0) == codigo) {
                // Atualizar na tabela
                tableModel.setValueAt(novoProduto.getNomeProduto(), i, 1);
                tableModel.setValueAt(novoProduto.getCategoriaProduto(), i, 2);
                tableModel.setValueAt(novoProduto.getPreco(), i, 3);
                tableModel.setValueAt(novoProduto.getQuantidade(), i, 4);
                
                // Atualizar na fonte de dados
                // Atualizar no banco de dados: atualizarProdutoNoBancoDeDados(novoProduto);
                
                break;
            }
        }
    }
    
    public void excluirProduto(int codigo) {
        // Remover o produto da tabela e da fonte de dados (banco de dados, por exemplo)
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            if ((int) tableModel.getValueAt(i, 0) == codigo) {
                // Remover da tabela
                tableModel.removeRow(i);
                
                // Remover da fonte de dados
                // Remover do banco de dados: removerProdutoDoBancoDeDados(codigo);
                
                break;
            }
        }
    }
}
