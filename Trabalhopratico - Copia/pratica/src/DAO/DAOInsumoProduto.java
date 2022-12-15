package DAO;

import Conexao.Conexao;
import Modelo.Insumo;
import Modelo.InsumoProduto;
import Modelo.Produto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class DAOInsumoProduto { // Classe DAO para a tabela InsumoProduto

    DAOProduto daoProduto = new DAOProduto(); // Cria um objeto DAOProduto
    DAOInsumo daoInsumo = new DAOInsumo(); // Cria um objeto DAOInsumo

    public List<InsumoProduto> getLista() { // Método para obter a lista de insumos de um produto
        String sql = "SELECT * FROM InsumoProduto"; // Comando SQL
        List<InsumoProduto> lista = new ArrayList<>(); // Lista de insumos de um produto
        try { // Tenta obter a lista de insumos de um produto
            PreparedStatement pst = Conexao.getPreparedStatement(sql); // Obtém o PreparedStatement
            ResultSet rs = pst.executeQuery(); // Executa o comando SQL e obtém o ResultSet
            while (rs.next()) { // Enquanto houver insumos de um produto
                InsumoProduto obj = new InsumoProduto(); // Cria um novo objeto InsumoProduto
                obj.setId_produto(daoProduto.localizar(rs.getInt("id_produto"))); // Define o ID do produto
                obj.setId_insumo(daoInsumo.localizar(rs.getInt("id_insumo"))); // Define o ID do insumo
                obj.setQuantidade(rs.getInt("quantidade")); // Define a quantidade
                lista.add(obj); // Adiciona o objeto InsumoProduto na lista
            }
        } catch (SQLException e) { // Caso ocorra um erro
            JOptionPane.showMessageDialog(null, "Erro de SQL: " + e.getMessage()); // Exibe uma mensagem de erro
        }
        return lista; // Retorna a lista de insumos de um produto
    }

    public void salvar(Object o) { // Método para salvar um insumo de um produto
        InsumoProduto obj = (InsumoProduto) o; // Converte o objeto para InsumoProduto
        Produto produtoExistente = daoProduto.localizarEspecifico(obj.getId_produto().getId_produto()); // Obtém o produto existente
        Insumo insumoExistente = daoInsumo.localizarEspecifico(obj.getId_insumo().getId_insumo()); // Obtém o insumo existente

        if (produtoExistente == null && insumoExistente == null) { // Caso o produto e o insumo não existam
            incluir(obj); // Chama o método incluir
        } else {
            atualizar(obj); // Caso contrário, chama o método atualizar
        }
    }

    private void incluir(InsumoProduto obj) { // Método para incluir um insumo de um produto
        String sql = "INSERT INTO InsumoProduto (id_produto,id_insumo,quantidade) VALUES (?,?,?)"; // Comando SQL
        try { // Tenta incluir um insumo de um produto
            PreparedStatement pst = Conexao.getPreparedStatement(sql); // Obtém o PreparedStatement
            pst.setInt(1, obj.getId_produto().getId_produto()); // Define o ID do produto
            pst.setInt(2, obj.getId_insumo().getId_insumo()); // Define o ID do insumo
            pst.setInt(3, obj.getQuantidade()); // Define a quantidade
            if (pst.executeUpdate() > 0) { // Se o comando SQL for executado com sucesso
                JOptionPane.showMessageDialog(null, "Dados da tabela InsumoProduto incluído com sucesso!"); // Exibe uma mensagem de sucesso
            } else { // Caso contrário
                JOptionPane.showMessageDialog(null, "Não foi possível incluir os dados na tabela InsumoProduto!"); // Exibe uma mensagem de erro
            }

        } catch (SQLException e) { // Caso ocorra um erro
            JOptionPane.showMessageDialog(null, "Erro de SQL: " + e.getMessage()); // Exibe uma mensagem de erro
        }
    }

    private void atualizar(InsumoProduto obj) { // Método para atualizar um insumo de um produto
        String sql = "UPDATE InsumoProduto SET quantidade = ? WHERE id_insumo = ? AND id_produto = ?"; // Comando SQL
        try { // Tenta atualizar um insumo de um produto
            PreparedStatement pst = Conexao.getPreparedStatement(sql); // Obtém o PreparedStatement
            pst.setInt(1, obj.getQuantidade()); // Define a quantidade
            pst.setInt(2, obj.getId_insumo().getId_insumo()); // Define o ID do insumo
            pst.setInt(3, obj.getId_produto().getId_produto()); // Define o ID do produto
            if (pst.executeUpdate() > 0) { // Se o comando SQL for executado com sucesso
                JOptionPane.showMessageDialog(null, "Tabela InsumoProduto atualizado com sucesso!"); // Exibe uma mensagem de sucesso
            } else { // Caso contrário
                JOptionPane.showMessageDialog(null, "Não foi possível atualizar os dados da tabela InsumoProduto!"); // Exibe uma mensagem de erro
            }

        } catch (SQLException e) { // Caso ocorra um erro
            JOptionPane.showMessageDialog(null, "Erro de SQL: " + e.getMessage()); // Exibe uma mensagem de erro
        }
    }

    public void remover(Object o) { // Método para remover um insumo de um produto
        InsumoProduto obj = (InsumoProduto) o; // Converte o objeto para InsumoProduto
        String sql = "DELETE FROM InsumoProduto WHERE id_insumo = ? AND id_produto = ?"; // Comando SQL
        try { // Tenta remover um insumo de um produto
            PreparedStatement pst = Conexao.getPreparedStatement(sql); // Obtém o PreparedStatement

            pst.setInt(1, obj.getId_insumo().getId_insumo()); // Define o ID do insumo
            pst.setInt(2, obj.getId_produto().getId_produto()); // Define o ID do produto

            if (pst.executeUpdate() > 0) { // Se o comando SQL for executado com sucesso
                JOptionPane.showMessageDialog(null, "Dado da tabela InsumoProduto excluído com sucesso!"); // Exibe uma mensagem de sucesso
            } else { // Caso contrário
                JOptionPane.showMessageDialog(null, "Não foi possível excluir o dado da tabela InsumoProduto!"); // Exibe uma mensagem de erro
            }

        } catch (SQLException e) { // Caso ocorra um erro
            JOptionPane.showMessageDialog(null, "Erro de SQL: " + e.getMessage()); // Exibe uma mensagem de erro
        }
    }
}
