package Modelo;

import java.io.Serializable;

public class Insumo implements Serializable { // Classe para armazenar os insumos

    private Integer id_insumo; // ID do insumo
    private String nome; // Nome do insumo
    private double preco; // Preço do insumo

    public Integer getId_insumo() { // Retorna o ID
        return id_insumo;
    }

    public void setId_insumo(Integer id_insumo) { // Atribui o ID
        this.id_insumo = id_insumo;
    }

    public String getNome() { // Retorna o nome
        return nome;
    }

    public void setNome(String nome) { // Atribui o nome
        this.nome = nome;
    }

    public double getPreco() { // Retorna o preço
        return preco;
    }

    public void setPreco(double preco) { // Atribui o preço
        this.preco = preco;
    }

    @Override
    public String toString() { // Retorna o nome do insumo
        return nome;
    }
}
