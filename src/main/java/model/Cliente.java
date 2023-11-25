package model;

/**
 *
 * @author Ectho
 */
public class Cliente {
    private int idCliente;
    private String nome;
    private String cpf;
    private int sexo;
    private String email;
    private String celular;
    
    // Construtor Vazio
    public Cliente() {
        
    }
    // Construtor sem o ID do Cliente
    public Cliente(String nome, String cpf, int sexo, String email, String celular) {
        this.nome = nome;
        this.cpf = cpf;
        this.sexo = sexo;
        this.email = email;
        this.celular = celular;
    }
    // Construtor com o ID do Cliente
    public Cliente(int idCliente, String nome, String cpf, int sexo, String email, String celular) {
        this.idCliente = idCliente;
        this.nome = nome;
        this.cpf = cpf;
        this.sexo = sexo;
        this.email = email;
        this.celular = celular;
    }
    
    // Getters e Setters
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getSexo() {
        return sexo;
    }

    public void setSexo(int sexo) {
        this.sexo = sexo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }
}
