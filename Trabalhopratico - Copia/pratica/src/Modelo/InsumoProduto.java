package Modelo;

import java.io.Serializable;

public class InsumoProduto implements Serializable { // Classe para armazenar os insumos de um produto

    private Produto id_produto; // Chave estrangeira para produto
    private Insumo id_insumo; // Chave estrangeira para insumo
    private Integer quantidade; // Quantidade de insumo

    public Produto getId_produto() { // Retorna o produto
        return id_produto;
    }

    public void setId_produto(Produto id_produto) { // Atribui o produto
        this.id_produto = id_produto;
    }

    public Insumo getId_insumo() { // Retorna o insumo
        return id_insumo;
    }

    public void setId_insumo(Insumo id_insumo) { // Atribui o insumo
        this.id_insumo = id_insumo;
    }

    public Integer getQuantidade() { // Retorna a quantidade
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) { // Atribui a quantidade
        this.quantidade = quantidade;
    }
}
