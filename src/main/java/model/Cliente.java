package model;

/**
 *
 * @author Ectho
 */
public class Cliente {
    private int idCliente;
    private String nome;
    private String dataNascimento;
    private String cpf;
    private String sexo;
    private String email;
    private String celular;
    private String telefone;
    
    // Construtor Vazio
    public Cliente() {
        
    }
    // Construtor sem o ID do Cliente
    public Cliente(String nome, String dataNascimento, String cpf, String sexo, String email, String celular, String telefone) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.sexo = sexo;
        this.email = email;
        this.celular = celular;
        this.telefone = telefone;
    }
    // Construtor com o ID do Cliente
    public Cliente(int idCliente, String nome, String dataNascimento, String cpf, String sexo, String email, String celular, String telefone) {
        this.idCliente = idCliente;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.sexo = sexo;
        this.email = email;
        this.celular = celular;
        this.telefone = telefone;
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

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
    
}
