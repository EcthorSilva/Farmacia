package model;

import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author Ecthor
 */
public class Pedido {
    private int idPedido;
    private int idCliente;
    private int idVendedor;
    private List<ItemPedido> itensPedido;
    private String formaPagamento;
    private double totalCompra;
    
    // para o relatorio
    private int idVenda;
    private String dataHora;
    private String formaPagamento1;
    private double totalCompra1;
    private String nomeCliente;
    private String nomeFuncionario;
    private String nomeProduto;
    private int totalQuantidade;
    private double totalValor;

    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public String getFormaPagamento1() {
        return formaPagamento1;
    }

    public void setFormaPagamento1(String formaPagamento1) {
        this.formaPagamento1 = formaPagamento1;
    }

    public double getTotalCompra1() {
        return totalCompra1;
    }

    public void setTotalCompra1(double totalCompra1) {
        this.totalCompra1 = totalCompra1;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getNomeFuncionario() {
        return nomeFuncionario;
    }

    public void setNomeFuncionario(String nomeFuncionario) {
        this.nomeFuncionario = nomeFuncionario;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public int getTotalQuantidade() {
        return totalQuantidade;
    }

    public void setTotalQuantidade(int totalQuantidade) {
        this.totalQuantidade = totalQuantidade;
    }

    public double getTotalValor() {
        return totalValor;
    }

    public void setTotalValor(double totalValor) {
        this.totalValor = totalValor;
    }
            
    
    // Construtor Vazio
    public Pedido() {
        
    }
    
    public Pedido(int idVenda, String dataHora, String formaPagamento1, double totalCompra1, String nomeCliente, String nomeFuncionario, String nomeProduto, int totalQuantidade, double totalValor) {
        this.idVenda = idVenda;
        this.dataHora = dataHora;
        this.formaPagamento1 = formaPagamento1;
        this.totalCompra1 = totalCompra1;
        this.nomeCliente = nomeCliente;
        this.nomeFuncionario = nomeFuncionario;
        this.nomeProduto = nomeProduto;
        this.totalQuantidade = totalQuantidade;
        this.totalValor = totalValor;
    }
    // Construtor sem o ID do Pedido
    public Pedido(int idCliente, int idVendedor, List<ItemPedido> itensPedido, String formaPagamento, double totalCompra) {
        this.idCliente = idCliente;
        this.idVendedor = idVendedor;
        this.itensPedido = itensPedido;
        this.formaPagamento = formaPagamento;
        this.totalCompra = totalCompra;
    }
    // Construtor com o ID do Pedido
    public Pedido(int idPedido, int idCliente, int idVendedor, List<ItemPedido> itensPedido, String formaPagamento, double totalCompra) {
        this.idPedido = idPedido;
        this.idCliente = idCliente;
        this.idVendedor = idVendedor;
        this.itensPedido = itensPedido;
        this.formaPagamento = formaPagamento;
        this.totalCompra = totalCompra;
    }
    
    public Pedido(int idPedido, int idCliente) {
        this.idPedido = idPedido;
        this.idCliente = idCliente;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }

    public List<ItemPedido> getItensPedido() {
        return itensPedido;
    }

    public void setItensPedido(List<ItemPedido> itensPedido) {
        this.itensPedido = itensPedido;
    }

    public String getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(String formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    public double getTotalCompra() {
        return totalCompra;
    }

    public void setTotalCompra(double totalCompra) {
        this.totalCompra = totalCompra;
    }
}