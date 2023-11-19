package model;

/**
 *
 * @author Ectho
 */
public class Produto {
    private int idProduto;
    private String nomeProduto;
    private String categoriaProduto;
    private String fabricante;
    private double preco;
    private int quantidade;
    private int indiceCategoria;
    
    // Construtor Vazio
    public Produto(){ 
        
    }
    // Construtor sem o ID do Produto
    public Produto(String nomeProduto, String categoriaProduto, String fabricante, double preco, int quantidade) {
        this.nomeProduto = nomeProduto;
        this.categoriaProduto = categoriaProduto;
        this.fabricante = fabricante;
        this.preco = preco;
        this.quantidade = quantidade;
    }
    // Construtor com o ID do Produto
    public Produto(int idProduto, String nomeProduto, String categoriaProduto, String fabricante, double preco, int quantidade) {
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.categoriaProduto = categoriaProduto;
        this.fabricante = fabricante;
        this.preco = preco;
        this.quantidade = quantidade;
    }
    
    // Getters e Setters
    public int getIndiceCategoria() {
        return indiceCategoria;
    }

    public void setIndiceCategoria(int indiceCategoria) {
        this.indiceCategoria = indiceCategoria;
    }
    
    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

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
