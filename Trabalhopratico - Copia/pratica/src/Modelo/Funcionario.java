package Modelo;

import java.io.Serializable;

public class Funcionario implements Serializable { // Classe para armazenar os funcionários

    private Integer id_funcionario; // ID do funcionário
    private String nome; // Nome do funcionário
    private String cpf; // CPF do funcionário
    private double salario; // Salário do funcionário
    private char sexo; // Sexo do funcionário
    private String telefone; // Telefone do funcionário

    public Integer getId_funcionario() {
        return id_funcionario;
    } // Retorna o ID

    public void setId_funcionario(Integer id_funcionario) {
        this.id_funcionario = id_funcionario;
    } // Atribui o ID

    public String getNome() {
        return nome;
    } // Retorna o nome

    public void setNome(String nome) { // Atribui o nome
        this.nome = nome;
    }

    public String getCpf() { // Retorna o CPF
        return cpf;
    }

    public void setCpf(String cpf) { // Atribui o CPF
        this.cpf = cpf;
    }

    public double getSalario() { // Retorna o salário
        return salario;
    }

    public void setSalario(double salario) { // Atribui o salário
        this.salario = salario;
    }

    public char getSexo() { // Retorna o sexo
        return sexo;
    }

    public void setSexo(char sexo) { // Atribui o sexo
        this.sexo = sexo;
    }

    public String getTelefone() { // Retorna o telefone
        return telefone;
    }

    public void setTelefone(String telefone) { // Atribui o telefone
        this.telefone = telefone;
    }

    @Override
    public String toString() { // Retorna o nome do funcionário
        return nome;
    }
}
