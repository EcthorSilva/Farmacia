package model;

/**
 *
 * @author Ecthor
 */
public class Produto {
    private int idProduto;
    private String nomeProduto;
    private int categoria;
    private String nomeCategoria;
    private String fabricante;
    private double preco;
    private int quantidade;
    private int indiceCategoria;
    
    // Construtor Vazio
    public Produto(){ 
        
    }
    // Construtor sem o ID do Produto
    public Produto(String nomeProduto, int categoria, String fabricante, double preco, int quantidade) {
        this.nomeProduto = nomeProduto;
        this.categoria = categoria;
        this.fabricante = fabricante;
        this.preco = preco;
        this.quantidade = quantidade;
    }
    // Construtor com o ID do Produto
    public Produto(int idProduto, String nomeProduto, int categoria, String fabricante, double preco, int quantidade) {
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.categoria = categoria;
        this.fabricante = fabricante;
        this.preco = preco;
        this.quantidade = quantidade;
    }
    
    // Construtor para a função LISTAR da classe ProdutoDAO
    public Produto(int idProduto, String nomeProduto, String nomeCategoria, String fabricante, double preco, int quantidade) {
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.nomeCategoria = nomeCategoria;
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

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }
    
    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
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
