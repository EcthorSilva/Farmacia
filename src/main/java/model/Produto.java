package model;

public class Produto {
    private String nomeProduto;
    private String categoriaProduto;
    private String fabricante;
    private double preco;
    private int quantidade;
    
    // Construtor
    public Produto(String nomeProduto, String categoriaProduto, String fabricante, double preco, int quantidade) {
        this.nomeProduto = nomeProduto;
        this.categoriaProduto = categoriaProduto;
        this.fabricante = fabricante;
        this.preco = preco;
        this.quantidade = quantidade;
    }
    
    // Método estático para criar um novo produto com código auto incrementado
    public static Produto criarProduto(String nomeProduto, String categoriaProduto, String fabricante, double preco, int quantidade) {
        Produto novoProduto = new Produto(nomeProduto, categoriaProduto, fabricante, preco, quantidade);
        return novoProduto;
    }
           
    // Getters e setters
    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getCategoriaProduto() {
        return categoriaProduto;
    }

    public void setCategoriaProduto(String categoriaProduto) {
        this.categoriaProduto = categoriaProduto;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
