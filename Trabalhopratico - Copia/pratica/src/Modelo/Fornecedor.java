package Modelo;

import java.io.Serializable;

public class Fornecedor implements Serializable { // Classe para armazenar os fornecedores

    private String cnpj; // CNPJ do fornecedor
    private String nome; // Nome do fornecedor
    private String telefone; // Telefone do fornecedor

    public String getCnpj() {
        return cnpj;
    } // Retorna o CNPJ

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    } // Atribui o CNPJ

    public String getNome() {
        return nome;
    } // Retorna o nome

    public void setNome(String nome) {
        this.nome = nome;
    } // Atribui o nome

    public String getTelefone() {
        return telefone;
    } // Retorna o telefone

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    } // Atribui o telefone

    @Override
    public String toString() {
        return nome;
    } // Retorna o nome do fornecedor
}
