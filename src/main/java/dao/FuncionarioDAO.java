/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Funcionario;

/**
 * Resposnavel para fazer o insert no banco de dados
 * 
 * @author Ecthor
 */
public class FuncionarioDAO {
    // variaveis para acessar o banco
    private static String url = "jdbc:mysql://localhost:3306/farmacia";
    private static String login = "root";
    private static String senha = "root";
    
    
    /**
    * Salva um objeto Funcionario no banco de dados.
    *
    * @param obj O objeto Funcionario a ser salvo.
    * @return true se a operação de salvamento for bem-sucedida, false caso contrário.
    */
    // CREATE
    public static boolean salvar(Funcionario obj){
        boolean retorno = false;
        
        // instanciando objeto para realizar a conexão
        Connection conexao = null;
        // 
        PreparedStatement comandoSQL = null;
        
        try {
            // Passo1 - carregar o driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Passo2 - abrir a conexão com o banco
            conexao = DriverManager.getConnection(url, login, senha);
            // Passo3 - Preparar o comando SQL
            comandoSQL = conexao.prepareStatement("INSERT INTO funcionarios (nome, data_nascimento, id_genero, cpf, id_cargo, salario, id_turno) VALUES (?, ?, ?, ?, ?, ?, ?)",
        PreparedStatement.RETURN_GENERATED_KEYS); // INSERT INTO funcionarios (nome, data_nascimento, id_genero, cpf, id_cargo, salario, id_turno) VALUES (?, ?, ?, ?, ?, ?, ?);

            // pega o Nome
            comandoSQL.setString(1, obj.getNome());
            // pega o Data de nascimento
            comandoSQL.setString(2, obj.getDataNascimento());
            // pega o Sexo
            comandoSQL.setInt(3, obj.getSexo());
            // pega o CPF
            comandoSQL.setString(4, obj.getCpf());
            // pega o Cargo
            comandoSQL.setInt(5, obj.getCargo());
            // pega o Salario
            comandoSQL.setDouble(6, obj.getSalario());
            // pega o Periodo
            comandoSQL.setInt(7, obj.getPeriodo());
            
            // Passo4 - Executar o comando
            int linhasAfetadas = comandoSQL.executeUpdate();
            // se as linhas afetadas forem maior que 0 
            if(linhasAfetadas > 0){
                // então foi gravado no bd com sucesso
                retorno = true;
                
                // pega o ID auto incremental do banco
                ResultSet rs = comandoSQL.getGeneratedKeys();
                if (rs != null){
                    while(rs.next()){
                        int idGerado = rs.getInt(1);
                        obj.setIdFuncionario(idGerado);
                    }
                }
            }
        } catch (ClassNotFoundException ex) {
            retorno = false;
        } catch (SQLException ex) {
            retorno = false;
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
    /**
    * Retorna uma lista de objetos Funcionario contendo informações detalhadas
    * de todos os funcionários cadastrados no banco de dados, incluindo dados de
    * gênero, cargo e turno através de operações de JOIN.
    *
    * @return Uma ArrayList de objetos Funcionario ou null em caso de erro.
    */
    // READ
    public static ArrayList<Funcionario> listar(){
        ArrayList<Funcionario> lista = new ArrayList<>();
        
        // classes para utilizar
        Connection conexao = null;
        PreparedStatement comandoSQL = null;
        ResultSet rs = null; // recebe o resultado da consulta
        
        try{
            // Passo1 - carregar o driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Passo2 - abrir a conexão com o banco
            conexao = DriverManager.getConnection(url, login, senha);
            
            // Adicionando JOIN com as tabela cargos e turnos 
            String query = "SELECT f.id, f.nome, f.data_nascimento, g.nome as genero, f.cpf, c.nome as cargo, f.salario, t.turno as horarioDeTrabalho " +
                "FROM funcionarios f " +
                "JOIN generos g ON f.id_genero = g.id " +
                "JOIN cargos c ON f.id_cargo = c.id " +
                "JOIN turnos t ON f.id_turno = t.id";
            
            // Passo3 - Preparar o comando SQL
            comandoSQL = conexao.prepareStatement(query);
            
            // Passo4 - Executar a CONSULTA
            rs = comandoSQL.executeQuery();
            
            // se existir uma tabela ele inicia um while
            if (rs != null){
                while(rs.next()){
                    // cada volta do while cria um objeto para cada linha da lista
                    int idFuncionario = rs.getInt("id");
                    String nomeFuncionario = rs.getString("nome");
                    String dataNascimento = rs.getString("data_nascimento");
                    String sexo = rs.getString("genero");
                    String cpf = rs.getString("cpf");
                    String cargoFuncionario = rs.getString("cargo");
                    double salarioVendedor = rs.getDouble("salario");
                    String horarioDeTrabalho = rs.getString("horarioDeTrabalho");
                    
                    Funcionario item = new Funcionario(idFuncionario, nomeFuncionario, dataNascimento, sexo, cpf, cargoFuncionario, salarioVendedor, horarioDeTrabalho);
                    lista.add(item);
                }
            }
        }catch (Exception e) {
            lista = null;
        }finally{
            if(conexao != null){
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return lista;
    }
    
    /**
    * Altera as informações de um funcionário no banco de dados com base no ID fornecido.
    *
    * @param obj O objeto Funcionario contendo as informações atualizadas.
    * @return true se a operação de alteração for bem-sucedida, false caso contrário.
    */
    // UPDATE
    public static boolean alterar(Funcionario obj){
        boolean retorno = false;
        // instanciando objeto para realizar a conexão
        Connection conexao = null;
        // 
        PreparedStatement comandoSQL = null;
        
        // receita de bolo para acessar o banco de dados
        try {
            // Passo1 - carregar o driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Passo2 - abrir a conexão com o banco
            conexao = DriverManager.getConnection(url, login, senha);
            // Passo3 - Preparar o comando SQL
            comandoSQL = conexao.prepareStatement("UPDATE funcionarios SET nome = ?, data_nascimento = ?, id_genero = ?, cpf = ?, id_cargo = ?, salario = ?, id_turno = ? WHERE id = ?");
            
            // pega o Nome
            comandoSQL.setString(1, obj.getNome());
            // pega o Data de nascimento
            comandoSQL.setString(2, obj.getDataNascimento());
            // pega o Sexo
            comandoSQL.setInt(3, obj.getSexo());
            // pega o CPF
            comandoSQL.setString(4, obj.getCpf());
            // pega o Cargo
            comandoSQL.setInt(5, obj.getCargo());
            // pega o Salario
            comandoSQL.setDouble(6, obj.getSalario());
            // pega o Periodo
            comandoSQL.setInt(7, obj.getPeriodo());
            // pega o ID
            // pega o Periodo
            comandoSQL.setInt(8, obj.getIdFuncionario());
            
            // Passo4 - Executar o comando
            int linhasAfetadas = comandoSQL.executeUpdate();
            // se as linhas afetadas forem maior que 0 
            if(linhasAfetadas > 0){
                // então foi gravado no bd com sucesso
                retorno = true;
            }
        }catch(ClassNotFoundException ex){
            retorno = false;
        } catch (SQLException ex) {
            ex.printStackTrace();
            retorno = false;
            System.out.println("SQLException: " + ex.getMessage());
        }
        return retorno;
    }
    
    /**
    * Exclui um funcionário do banco de dados com base no ID fornecido.
    *
    * @param idExcluir O ID do funcionário a ser excluído.
    * @return true se a operação de exclusão for bem-sucedida, false caso contrário.
    */
    // DELETE
    public static boolean excluir(int idExcluir){
        boolean retorno = false;
        
        // instanciando objeto para realizar a conexão
        Connection conexao = null;
        // 
        PreparedStatement comandoSQL = null;
        
        // receita de bolo para acessar o banco de dados
        try {
            // Passo1 - carregar o driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Passo2 - abrir a conexão com o banco
            conexao = DriverManager.getConnection(url, login, senha);
            // Passo3 - Preparar o comando SQL
            comandoSQL = conexao.prepareStatement("DELETE FROM funcionarios WHERE id = ?");
            
            // pega o valor do ID
            comandoSQL.setInt(1, idExcluir);
            
            // Passo4 - Executar o comando
            int linhasAfetadas = comandoSQL.executeUpdate();
            // se as linhas afetadas forem maior que 0 
            if(linhasAfetadas > 0){
                // então foi gravado no bd com sucesso
                retorno = true;
            }
        }catch(ClassNotFoundException ex){
            retorno = false;
        } catch (SQLException ex) {
            retorno = false;
        }
        
        return retorno;
    }

    
    /**
    * Busca funcionários no banco de dados com base no CPF fornecido.
    *
    * @param cpf O CPF do funcionário a ser pesquisado.
    * @return Uma ArrayList de objetos Funcionario que correspondem ao CPF, ou null em caso de erro.
    */
    // TODO: SEARCH

    // SEARCH

    public static ArrayList<Funcionario> buscarPorCPF(String cpf) {
        ArrayList<Funcionario> lista = new ArrayList<>();
    
        Connection conexao = null;
        PreparedStatement comandoSQL = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection(url, login, senha);

            String query = "SELECT f.id, f.nome, f.data_nascimento, g.nome as genero, f.cpf, c.nome as cargo, f.salario, t.turno as horarioDeTrabalho " +
                           "FROM funcionarios f " +
                           "JOIN generos g ON f.id_genero = g.id " +
                           "JOIN cargos c ON f.id_cargo = c.id " +
                           "JOIN turnos t ON f.id_turno = t.id " +
                           "WHERE f.cpf = ?";

            comandoSQL = conexao.prepareStatement(query);
            comandoSQL.setString(1, cpf);

            rs = comandoSQL.executeQuery();

            if (rs != null){
                while(rs.next()){
                    int idFuncionario = rs.getInt("id");
                    String nomeFuncionario = rs.getString("nome");
                    String dataNascimento = rs.getString("data_nascimento");
                    String genero = rs.getString("genero");
                    String cpfFuncionario = rs.getString("cpf");
                    String cargoFuncionario = rs.getString("cargo");
                    double salario = rs.getDouble("salario");
                    String horarioDeTrabalho = rs.getString("horarioDeTrabalho");

                    Funcionario item = new Funcionario(idFuncionario, nomeFuncionario, dataNascimento, genero, cpfFuncionario, cargoFuncionario, salario, horarioDeTrabalho);
                    lista.add(item);
                }
            }
        } catch (Exception e) {
            // Tratar exceções
            lista = null;
        } finally {
            // Fechar recursos
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return lista;
    }
}
