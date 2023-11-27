package model;

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
    
    // Construtor Vazio
    public Pedido() {
        
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
