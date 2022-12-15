package Modelo;

import java.io.Serializable;

public class Fornece implements Serializable { // Classe para armazenar os fornecimentos

    private Fornecedor fornecedor_cnpj; // Chave estrangeira para fornecedor
    private Insumo insumo_id_insumo; // Chave estrangeira para insumo
    private int qnt_fornecida; // Quantidade fornecida

    public Fornecedor getFornecedor_cnpj() {
        return fornecedor_cnpj;
    } // Retorna o fornecedor

    public void setFornecedor_cnpj(Fornecedor fornecedor_cnpj) { // Atribui o fornecedor
        this.fornecedor_cnpj = fornecedor_cnpj;
    }

    public Insumo getInsumo_id_insumo() {
        return insumo_id_insumo;
    } // Retorna o insumo

    public void setInsumo_id_insumo(Insumo insumo_id_insumo) {
        this.insumo_id_insumo = insumo_id_insumo;
    } // Atribui o insumo

    public int getQnt_fornecida() {
        return qnt_fornecida;
    } // Retorna a quantidade fornecida

    public void setQnt_fornecida(int qnt_fornecida) {
        this.qnt_fornecida = qnt_fornecida;
    } // Atribui a quantidade fornecida

}
