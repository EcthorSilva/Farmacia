package model;

/**
 *
 * @author Ecthor
 * A classe produto representa informaçoess sobre o produto.
 * Cada produto possui um id único, nome, categoria,fabricante
 * preço, quantidade e um índice de categoria.
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
    
    /*
    Contrutor vazio para criação de um objeto Produto sem parametros
    */
    public Produto(){    
    }
    
    /** 
     * Construtor para criar um objeto Produto sem o ID do Produto
     * @param nomeProduto O nome do produto.
     * @param categoria   O código da categoria do produto.
     * @param fabricante  O nome do fabricante do produto.
     * @param preco       O preço do produto.
     * @param quantidade  A quantidade disponível do produto. 
    */
    public Produto(String nomeProduto, int categoria, String fabricante, double preco, int quantidade) {
        this.nomeProduto = nomeProduto;
        this.categoria = categoria;
        this.fabricante = fabricante;
        this.preco = preco;
        this.quantidade = quantidade;
    }
    
    
   /**
     * Contrutor para criar um objeto Produto com todos os atributos
     * @param idProduto  identificador único de produto
     * @param nomeProduto nome do produto
     * @param categoria    código da ctegoria do produto 
     * @param fabricante   fabricante do produto
     * @param preco       preço  do produto
     * @param quantidade quantidade disponive em estoque
     */
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
    
    /**
     * Obtem o indice da categoria
     * @return indice da categoria
     */
    public int getIndiceCategoria() {
        return indiceCategoria;
    }
    
    /**
     * Define o indicee da categoria
     * @param  indiceCategoria nove indice da categoria 
     */
    public void setIndiceCategoria(int indiceCategoria) {
        this.indiceCategoria = indiceCategoria;
    }
    
    
    /**
     * Obtem o Id (Identificador) do Produto. 
     * @return o identificador único do produto
     */
    public int getIdProduto() {
        return idProduto;
    }
    
    /**
     * Difine o identificador unico do produto.
     * @param idProduto novo identificador unico do produto
     */
    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    /**
     * Obtem o nome do produto
     * @return o nome do produto
     */
    public String getNomeProduto() {
        return nomeProduto;
    }
    /**
     * Difibne o nome do Produto
     * @param nomeProduto novo nome do produto
     */
    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    /**
     * Obtrem 
     * @return 
     */
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
