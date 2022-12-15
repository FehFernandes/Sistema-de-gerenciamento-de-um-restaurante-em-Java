package Modelo;

import java.io.Serializable;

public class ItemPedido implements Serializable { // Classe para armazenar os itens de um pedido

    private Pedido id_pedido; // Chave estrangeira para pedido
    private Produto id_produto; // Chave estrangeira para produto
    private Integer quantidade; // Quantidade de produto

    public Pedido getId_pedido() {
        return id_pedido;
    } // Retorna o pedido

    public void setId_pedido(Pedido id_pedido) { // Atribui o pedido
        this.id_pedido = id_pedido;
    }

    public Produto getId_produto() { // Retorna o produto
        return id_produto;
    }

    public void setId_produto(Produto id_produto) { // Atribui o produto
        this.id_produto = id_produto;
    }

    public Integer getQuantidade() { // Retorna a quantidade
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) { // Atribui a quantidade
        this.quantidade = quantidade;
    }
}
