package Modelo;

import java.io.Serializable;

public class Produto implements Serializable{ // Classe para armazenar os produtos
    private Integer id_produto; // ID do produto
    private String nome;

    public Integer getId_produto() { // Retorna o ID
        return id_produto;
    }

    public void setId_produto(Integer id_produto) { // Atribui o ID
        this.id_produto = id_produto;
    }

    public String getNome() { // Retorna o nome
        return nome;
    }

    public void setNome(String nome) { // Atribui o nome
        this.nome = nome;
    }
    
    @Override
    public String toString(){ // Retorna o nome do produto
        return nome;
    }
}
