package Modelo;

import java.io.Serializable;
import java.util.Date;

public class Pedido implements Serializable { // Classe para armazenar os pedidos

    private Integer id_pedido; // ID do pedido
    private Funcionario id_funcionario; // Chave estrangeira para funcionário
    private double valor_total; // Valor total do pedido
    private String descricao; // Descrição do pedido

    public Integer getId_pedido() { // Retorna o ID
        return id_pedido;
    }

    public void setId_pedido(Integer id_pedido) { // Atribui o ID
        this.id_pedido = id_pedido;
    }

    public Funcionario getId_funcionario() { // Retorna o funcionário
        return id_funcionario;
    }

    public void setId_funcionario(Funcionario id_funcionario) { // Atribui o funcionário
        this.id_funcionario = id_funcionario;
    }

    public double getValor_total() { // Retorna o valor total
        return valor_total;
    }

    public void setValor_total(double valor_total) { // Atribui o valor total
        this.valor_total = valor_total;
    }

    public String getDescricao() { // Retorna a descrição
        return descricao;
    }

    public void setDescricao(String descricao) { // Atribui a descrição
        this.descricao = descricao;
    }
}
