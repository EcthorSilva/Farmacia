package model;

/**
 *
 * @author Ectho
 */
public class Funcionario {
    private int idFuncionario;
    private String nome;
    private String dataNascimento;
    private int sexo;
    private String cpf;
    private int cargo;
    private double salario;
    private int periodo;
    
    private String nomeSexo;
    private String nomeCargo;
    private String nomePeriodo;
    
    private int indiceSexoFuncionario;
    private int indiceCargoFuncionario;
    private int indiceHorarioDeTrabalhoFuncionario;
    
    // Construtor Vazio
    public Funcionario() {
        
    }
    // Construtor sem o ID do Funcionario
    public Funcionario(String nome, String dataNascimento, int sexo, String cpf, int cargo, double salario, int periodo) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.cpf = cpf;
        this.cargo = cargo;
        this.salario = salario;
        this.periodo = periodo;
    }
    // Construtor com o ID do Funcionario
    public Funcionario(int idFuncionario, String nome, String dataNascimento, int sexo, String cpf, int cargo, double salario, int periodo) {
        this.idFuncionario = idFuncionario;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.cpf = cpf;
        this.cargo = cargo;
        this.salario = salario;
        this.periodo = periodo;
    }
    // Construtor para a função LISTAR da FuncionarioDAO
    public Funcionario(int idFuncionario, String nome, String dataNascimento, String nomeSexo, String cpf, String nomeCargo, double salario, String nomePeriodo) {
        this.idFuncionario = idFuncionario;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.nomeSexo = nomeSexo;
        this.cpf = cpf;
        this.nomeCargo = nomeCargo;
        this.salario = salario;
        this.nomePeriodo = nomePeriodo;
    }
    
    // Getters e Setters
    public int getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
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

    public int getSexo() {
        return sexo;
    }

    public void setSexo(int sexo) {
        this.sexo = sexo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getCargo() {
        return cargo;
    }

    public void setCargo(int cargo) {
        this.cargo = cargo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }
    
    public String getNomeSexo() {
        return nomeSexo;
    }

    public void setNomeSexo(String nomeSexo) {
        this.nomeSexo = nomeSexo;
    }

    public String getNomeCargo() {
        return nomeCargo;
    }

    public void setNomeCargo(String nomeCargo) {
        this.nomeCargo = nomeCargo;
    }

    public String getNomePeriodo() {
        return nomePeriodo;
    }

    public void setNomePeriodo(String nomePeriodo) {
        this.nomePeriodo = nomePeriodo;
    }
    
    public int getIndiceSexoFuncionario() {
        return indiceSexoFuncionario;
    }

    public void setIndiceSexoFuncionario(int indiceSexoFuncionario) {
        this.indiceSexoFuncionario = indiceSexoFuncionario;
    }

    public int getIndiceCargoFuncionario() {
        return indiceCargoFuncionario;
    }

    public void setIndiceCargoFuncionario(int indiceCargoFuncionario) {
        this.indiceCargoFuncionario = indiceCargoFuncionario;
    }

    public int getIndiceHorarioDeTrabalhoFuncionario() {
        return indiceHorarioDeTrabalhoFuncionario;
    }

    public void setIndiceHorarioDeTrabalhoFuncionario(int indiceHorarioDeTrabalhoFuncionario) {
        this.indiceHorarioDeTrabalhoFuncionario = indiceHorarioDeTrabalhoFuncionario;
    }
}
