package model;

/**
 *
 * @author Ectho
 */
public class Vendedor {
    private int idVendedor;
    private String nome;
    private String dataNascimento;
    private String sexo;
    private String cpf;
    private String cargo;
    private double salario;
    private String periodo;
    
    // Construtor Vazio
    public Vendedor() {
        
    }
    // Construtor sem o ID do Vendedor
    public Vendedor(String nome, String dataNascimento, String sexo, String cpf, String cargo, double salario, String periodo) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.cpf = cpf;
        this.cargo = cargo;
        this.salario = salario;
        this.periodo = periodo;
    }
    // Construtor com o ID do Vendedor
    public Vendedor(int idVendedor, String nome, String dataNascimento, String sexo, String cpf, String cargo, double salario, String periodo) {
        this.idVendedor = idVendedor;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.cpf = cpf;
        this.cargo = cargo;
        this.salario = salario;
        this.periodo = periodo;
    }
    
    // Getters e Setters
    public int getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
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

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
}
