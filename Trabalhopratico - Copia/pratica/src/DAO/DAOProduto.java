package DAO;

import Conexao.Conexao;
import Modelo.Produto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class DAOProduto { // Classe DAO para a tabela Produto

    public List<Produto> getLista() { // Método para obter a lista de produtos
        String sql = "SELECT * FROM Produto"; // Comando SQL
        List<Produto> lista = new ArrayList<>(); // Lista de produtos

        try { // Tenta executar o comando SQL
            PreparedStatement pst = Conexao.getPreparedStatement(sql); // Obtém o PreparedStatement
            ResultSet rs = pst.executeQuery(); // Executa o comando SQL e obtém o ResultSet
            while (rs.next()) { // Enquanto houver produtos
                Produto obj = new Produto(); // Cria um novo objeto Produto
                obj.setId_produto(rs.getInt("id_produto")); // Obtém o id do produto
                obj.setNome(rs.getString("nome")); // Obtém o nome do produto
                lista.add(obj); // Adiciona o produto na lista
            }
        } catch (SQLException e) { // Caso haja erro
            JOptionPane.showMessageDialog(null, "Erro de SQL: " + e.getMessage()); // Exibe a mensagem de erro
        }

        return lista; // Retorna a lista de produtos
    }

    public void salvar(Object o) { // Método para salvar um produto
        Produto obj = (Produto) o; // Obtém o objeto Produto
        if (obj.getId_produto() == null) { // Se o id do produto for nulo
            incluir(obj); // Chama o método para incluir o produto
        } else { // Se o id do produto não for nulo
            atualizar(obj); // Chama o método para atualizar o produto
        }
    }

    private void incluir(Produto obj) { // Método para incluir um produto
        String sql = "INSERT INTO Produto (nome) VALUES (?)"; // Comando SQL
        try { // Tenta executar o comando SQL
            PreparedStatement pst = Conexao.getPreparedStatement(sql); // Obtém o PreparedStatement

            pst.setString(1, obj.getNome()); // Seta o nome do produto

            if (pst.executeUpdate() > 0) { // Se o comando SQL for executado com sucesso
                JOptionPane.showMessageDialog(null, "Produto incluído com sucesso!"); // Exibe a mensagem de sucesso
            } else { // Se o comando SQL não for executado com sucesso
                JOptionPane.showMessageDialog(null, "Não foi possível escluir o produto!"); // Exibe a mensagem de erro
            }

        } catch (SQLException e) { // Caso haja erro
            JOptionPane.showMessageDialog(null, "Erro de SQL: " + e.getMessage()); // Exibe a mensagem de erro
        }
    }

    private void atualizar(Produto obj) { // Método para atualizar um produto
        String sql = "UPDATE Produto SET nome = ? WHERE id_produto = ?"; // Comando SQL
        try { // Tenta executar o comando SQL
            PreparedStatement pst = Conexao.getPreparedStatement(sql); // Obtém o PreparedStatement

            pst.setString(1, obj.getNome()); // Seta o nome do produto
            pst.setInt(2, obj.getId_produto()); // Seta o id do produto

            if (pst.executeUpdate() > 0) { // Se o comando SQL for executado com sucesso
                JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso!"); // Exibe a mensagem de sucesso
            } else { // Se o comando SQL não for executado com sucesso
                JOptionPane.showMessageDialog(null, "Não foi possível escluir o produto!"); // Exibe a mensagem de erro
            }

        } catch (SQLException e) { // Caso haja erro
            JOptionPane.showMessageDialog(null, "Erro de SQL: " + e.getMessage()); // Exibe a mensagem de erro
        }
    }

    public void remover(Object o) { // Método para remover um produto
        Produto obj = (Produto) o; // Obtém o objeto Produto
        String sql = "DELETE FROM Produto WHERE id_produto = ?"; // Comando SQL
        try { // Tenta executar o comando SQL
            PreparedStatement pst = Conexao.getPreparedStatement(sql); // Obtém o PreparedStatement

            pst.setInt(1, obj.getId_produto()); // Seta o id do produto

            if (pst.executeUpdate() > 0) { // Se o comando SQL for executado com sucesso
                JOptionPane.showMessageDialog(null, "Produto excluído com sucesso!"); // Exibe a mensagem de sucesso
            } else { // Se o comando SQL não for executado com sucesso
                JOptionPane.showMessageDialog(null, "Não foi possível escluir o produto!"); // Exibe a mensagem de erro
            }

        } catch (SQLException e) { // Caso haja erro
            JOptionPane.showMessageDialog(null, "Erro de SQL: " + e.getMessage()); // Exibe a mensagem de erro
        }
    }

    public Produto localizar(int id) { // Método para localizar um produto
        String sql = "SELECT * FROM Produto WHERE id_produto = ?"; // Comando SQL
        Produto obj = new Produto(); // Cria um novo objeto Produto
        try { // Tenta executar o comando SQL
            PreparedStatement pst = Conexao.getPreparedStatement(sql); // Obtém o PreparedStatement
            pst.setInt(1, id); // Seta o id do produto
            ResultSet rs = pst.executeQuery(); // Executa o comando SQL e obtém o ResultSet

            if (rs.next()) { // Se houver um produto
                obj.setId_produto(rs.getInt("id_produto")); // Obtém o id do produto
                obj.setNome(rs.getString("nome")); // Obtém o nome do produto
            } else { // Se não houver um produto
                JOptionPane.showMessageDialog(null, "Produto não encontrado!"); // Exibe a mensagem de erro
            }
        } catch (SQLException e) { // Caso haja erro
            JOptionPane.showMessageDialog(null, "Erro de SQL: " + e.getMessage()); // Exibe a mensagem de erro
        }
        return obj; // Retorna o objeto Produto
    }

    public Produto localizarEspecifico(int id) { // Método para localizar um produto
        String sql = "SELECT * FROM Produto WHERE id_produto = ?"; // Comando SQL
        Produto obj = new Produto(); // Cria um novo objeto Produto
        try { // Tenta executar o comando SQL
            PreparedStatement pst = Conexao.getPreparedStatement(sql); // Obtém o PreparedStatement
            pst.setInt(1, id); // Seta o id do produto
            ResultSet rs = pst.executeQuery(); // Executa o comando SQL e obtém o ResultSet

            if (rs.next()) { // Se houver um produto
                obj.setId_produto(rs.getInt("id_produto")); // Obtém o id do produto
                obj.setNome(rs.getString("nome")); // Obtém o nome do produto
            } else { // Se não houver um produto
                return null; // Retorna null
            }
        } catch (SQLException e) { // Caso haja erro
            JOptionPane.showMessageDialog(null, "Erro de SQL: " + e.getMessage()); // Exibe a mensagem de erro
        }
        return obj; // Retorna o objeto Produto
    }
}
