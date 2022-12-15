package DAO;

import Conexao.Conexao;
import Modelo.ItemPedido;
import Modelo.Pedido;
import Modelo.Produto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class DAOItemPedido { // Classe DAO para a tabela ItemPedido

    DAOPedido daoPedido = new DAOPedido(); // Cria um objeto DAOPedido
    DAOProduto daoProduto = new DAOProduto(); // Cria um objeto DAOProduto

    public List<ItemPedido> getLista() { // Método para obter a lista de itens de um pedido
        String sql = "SELECT * FROM ItemPedido"; // Comando SQL
        List<ItemPedido> lista = new ArrayList<>(); // Lista de itens de um pedido
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql); // Obtém o PreparedStatement
            ResultSet rs = pst.executeQuery(); // Executa o comando SQL e obtém o ResultSet
            while (rs.next()) { // Enquanto houver itens de um pedido
                ItemPedido obj = new ItemPedido(); // Cria um novo objeto ItemPedido
                obj.setId_pedido(daoPedido.localizar(rs.getInt("id_pedido"))); // Define o ID do pedido
                obj.setId_produto(daoProduto.localizar(rs.getInt("id_produto"))); // Define o ID do produto
                obj.setQuantidade(rs.getInt("quantidade")); // Define a quantidade do produto
                lista.add(obj); // Adiciona o item de um pedido na lista
            }
        } catch (SQLException e) { // Caso não consiga obter a lista de itens de um pedido
            JOptionPane.showMessageDialog(null, "Erro de SQL: " + e.getMessage()); // Exibe uma mensagem de erro
        }

        return lista; // Retorna a lista de itens de um pedido
    }

    public void salvar(Object o) { // Método para salvar um item de um pedido
        ItemPedido obj = (ItemPedido) o; // Converte o objeto para ItemPedido
        Pedido pedidoExistente = daoPedido.localizarEspecifico(obj.getId_pedido().getId_pedido()); // Obtém o pedido existente
        Produto produtoExistente = daoProduto.localizarEspecifico(obj.getId_produto().getId_produto()); // Obtém o produto existente
        if (pedidoExistente == null && produtoExistente == null) { // Verifica se o pedido e o produto existem
            incluir(obj); // Caso não existam, inclui o item de um pedido
        } else { // Caso existam
            atualizar(obj); // Atualiza o item de um pedido
        }
    }

    private void incluir(ItemPedido obj) { // Método para incluir um item de um pedido
        String sql = "INSERT INTO ItemPedido (id_pedido,id_produto,quantidade) VALUES (?,?,?)"; // Comando SQL
        try { // Tenta executar o comando SQL
            PreparedStatement pst = Conexao.getPreparedStatement(sql); // Obtém o PreparedStatement
            pst.setInt(1, obj.getId_pedido().getId_pedido()); // Define o ID do pedido
            pst.setInt(2, obj.getId_produto().getId_produto()); // Define o ID do produto
            pst.setInt(3, obj.getQuantidade()); // Define a quantidade do produto
            if (pst.executeUpdate() > 0) { // Se o comando SQL for executado com sucesso
                JOptionPane.showMessageDialog(null, "Dados da tabela ItemPedido incluído com sucesso!"); // Exibe uma mensagem de sucesso
            } else { // Caso contrário
                JOptionPane.showMessageDialog(null, "Não foi possível incluir os dados na tabela ItemPedido!"); // Exibe uma mensagem de erro
            }

        } catch (SQLException e) { // Caso não consiga incluir o item de um pedido
            JOptionPane.showMessageDialog(null, "Erro de SQL: " + e.getMessage()); // Exibe uma mensagem de erro
        }
    }

    private void atualizar(ItemPedido obj) { // Método para atualizar um item de um pedido
        String sql = "UPDATE ItemPedido SET quantidade = ? WHERE id_pedido = ? AND id_produto = ?"; // Comando SQL
        try { // Tenta executar o comando SQL
            PreparedStatement pst = Conexao.getPreparedStatement(sql); // Obtém o PreparedStatement
            pst.setInt(1, obj.getQuantidade()); // Define a quantidade do produto
            pst.setInt(2, obj.getId_pedido().getId_pedido()); // Define o ID do pedido
            pst.setInt(3, obj.getId_produto().getId_produto()); // Define o ID do produto
            if (pst.executeUpdate() > 0) { // Se o comando SQL for executado com sucesso
                JOptionPane.showMessageDialog(null, "Tabela ItemPedido atualizado com sucesso!"); // Exibe uma mensagem de sucesso
            } else { // Caso contrário
                JOptionPane.showMessageDialog(null, "Não foi possível atualizar os dados da tabela ItemPedido!"); // Exibe uma mensagem de erro
            }

        } catch (SQLException e) { // Caso não consiga atualizar o item de um pedido
            JOptionPane.showMessageDialog(null, "Erro de SQL: " + e.getMessage()); // Exibe uma mensagem de erro
        }
    }

    public void remover(Object o) { // Método para remover um item de um pedido
        ItemPedido obj = (ItemPedido) o; // Converte o objeto para ItemPedido
        String sql = "DELETE FROM ItemPedido WHERE id_pedido = ? AND id_produto = ?"; // Comando SQL
        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql); // Obtém o PreparedStatement
            pst.setInt(1, obj.getId_pedido().getId_pedido()); // Define o ID do pedido
            pst.setInt(2, obj.getId_produto().getId_produto()); // Define o ID do produto
            if (pst.executeUpdate() > 0) { // Se o comando SQL for executado com sucesso
                JOptionPane.showMessageDialog(null, "Dado da tabela ItemPedido excluído com sucesso!"); // Exibe uma mensagem de sucesso
            } else { // Caso contrário
                JOptionPane.showMessageDialog(null, "Não foi possível excluir o dado da tabela ItemPedido!"); // Exibe uma mensagem de erro
            }

        } catch (SQLException e) { // Caso não consiga remover o item de um pedido
            JOptionPane.showMessageDialog(null, "Erro de SQL: " + e.getMessage()); // Exibe uma mensagem de erro
        }
    }
}
