package DAO;

import Conexao.Conexao;
import Modelo.Insumo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class DAOInsumo { // Classe DAO para a tabela Insumo

    public List<Insumo> getLista() { // Método para obter a lista de insumos
        String sql = "SELECT * FROM Insumo"; // Comando SQL
        List<Insumo> lista = new ArrayList<>(); // Lista de insumos

        try {
            PreparedStatement pst = Conexao.getPreparedStatement(sql); // Obtém o PreparedStatement
            ResultSet rs = pst.executeQuery(); // Executa o comando SQL e obtém o ResultSet
            while (rs.next()) { // Enquanto houver insumos
                Insumo obj = new Insumo(); // Cria um novo objeto Insumo
                obj.setId_insumo(rs.getInt("id_insumo")); // Define o ID do insumo
                obj.setNome(rs.getString("nome")); // Define o nome do insumo
                obj.setPreco(rs.getDouble("preco")); // Define o preço do insumo
                lista.add(obj); // Adiciona o insumo na lista
            }
        } catch (SQLException e) {  // Caso não consiga obter a lista de insumos
            JOptionPane.showMessageDialog(null, "Erro de SQL: " + e.getMessage()); // Exibe uma mensagem de erro
        }

        return lista; // Retorna a lista de insumos
    }

    public void salvar(Object o) { // Método para salvar um insumo
        Insumo obj = (Insumo) o; // Converte o objeto para Insumo
        if (obj.getId_insumo() == null) { // Verifica se o ID do insumo é nulo
            //Inserir no banco
            incluir(obj); // Chama o método para incluir um insumo
        } else { // Caso o ID do insumo não seja null
            atualizar(obj); // Chama o método para atualizar um insumo
        }
    }

    private void incluir(Insumo obj) { // Método para incluir um insumo
        String sql = "INSERT INTO Insumo (nome,preco) VALUES (?,?)"; // Comando SQL
        try { // Tenta incluir um insumo
            PreparedStatement pst = Conexao.getPreparedStatement(sql); // Obtém o PreparedStatement

            pst.setString(1, obj.getNome()); // Define o nome do insumo
            pst.setDouble(2, obj.getPreco()); // Define o preço do insumo

            if (pst.executeUpdate() > 0) { // Se o comando SQL for executado com sucesso
                JOptionPane.showMessageDialog(null, "Insumo incluído com sucesso!"); // Exibe uma mensagem de sucesso
            } else { // Caso o comando SQL não seja executado com sucesso
                JOptionPane.showMessageDialog(null, "Não foi possível escluir o insumo!"); // Exibe uma mensagem de erro
            }

        } catch (SQLException e) { // Caso não consiga incluir um insumo
            JOptionPane.showMessageDialog(null, "Erro de SQL: " + e.getMessage()); // Exibe uma mensagem de erro
        }
    }

    private void atualizar(Insumo obj) { // Método para atualizar um insumo
        String sql = "UPDATE Insumo SET nome = ?, preco = ? WHERE id_insumo = ?"; // Comando SQL
        try { // Tenta atualizar um insumo
            PreparedStatement pst = Conexao.getPreparedStatement(sql); // Obtém o PreparedStatement

            pst.setString(1, obj.getNome()); // Define o nome do insumo
            pst.setDouble(2, obj.getPreco()); // Define o preço do insumo
            pst.setInt(3, obj.getId_insumo()); // Define o ID do insumo

            if (pst.executeUpdate() > 0) { // Se o comando SQL for executado com sucesso
                JOptionPane.showMessageDialog(null, "Insumo atualizado com sucesso!"); // Exibe uma mensagem de sucesso
            } else { // Caso o comando SQL não seja executado com sucesso
                JOptionPane.showMessageDialog(null, "Não foi possível escluir o insumo!"); // Exibe uma mensagem de erro
            }

        } catch (SQLException e) { // Caso não consiga atualizar um insumo
            JOptionPane.showMessageDialog(null, "Erro de SQL: " + e.getMessage()); // Exibe uma mensagem de erro
        }
    }

    public void remover(Object o) { // Método para remover um insumo
        Insumo obj = (Insumo) o; // Converte o objeto para Insumo
        String sql = "DELETE FROM Insumo WHERE id_insumo = ?"; // Comando SQL
        try { // Tenta remover um insumo
            PreparedStatement pst = Conexao.getPreparedStatement(sql); // Obtém o PreparedStatement

            pst.setInt(1, obj.getId_insumo()); // Define o ID do insumo

            if (pst.executeUpdate() > 0) { // Se o comando SQL for executado com sucesso
                JOptionPane.showMessageDialog(null, "Insumo excluído com sucesso!"); // Exibe uma mensagem de sucesso
            } else { // Caso o comando SQL não seja executado com sucesso
                JOptionPane.showMessageDialog(null, "Não foi possível escluir o insumo!"); // Exibe uma mensagem de erro
            }

        } catch (SQLException e) { // Caso não consiga remover um insumo
            JOptionPane.showMessageDialog(null, "Erro de SQL: " + e.getMessage()); // Exibe uma mensagem de erro
        }
    }

    public Insumo localizar(int id) { // Método para localizar um insumo
        String sql = "SELECT * FROM Insumo WHERE id_insumo = ?"; // Comando SQL
        Insumo obj = new Insumo(); // Cria um novo objeto Insumo
        try { // Tenta localizar um insumo
            PreparedStatement pst = Conexao.getPreparedStatement(sql); // Obtém o PreparedStatement
            pst.setInt(1, id); // Define o ID do insumo
            ResultSet rs = pst.executeQuery(); // Executa o comando SQL

            if (rs.next()) { // Se o comando SQL for executado com sucesso
                obj.setId_insumo(rs.getInt("id_insumo")); // Define o ID do insumo
                obj.setNome(rs.getString("nome")); // Define o nome do insumo
                obj.setPreco(rs.getDouble("preco")); // Define o preço do insumo
            } else { // Caso o comando SQL não seja executado com sucesso
                JOptionPane.showMessageDialog(null, "Insumo não encontrado!"); // Exibe uma mensagem de erro
            }
        } catch (SQLException e) { // Caso não consiga localizar um insumo
            JOptionPane.showMessageDialog(null, "Erro de SQL: " + e.getMessage()); // Exibe uma mensagem de erro
        }
        return obj; // Retorna o objeto Insumo
    }

    public Insumo localizarEspecifico(int id) { // Método para localizar um insumo específico
        String sql = "SELECT * FROM Insumo WHERE id_insumo = ?";  // Comando SQL
        Insumo obj = new Insumo(); // Cria um novo objeto Insumo
        try { // Tenta localizar um insumo específico
            PreparedStatement pst = Conexao.getPreparedStatement(sql); // Obtém o PreparedStatement
            pst.setInt(1, id); // Define o ID do insumo
            ResultSet rs = pst.executeQuery(); // Executa o comando SQL

            if (rs.next()) { // Se o comando SQL for executado com sucesso
                obj.setId_insumo(rs.getInt("id_insumo")); // Define o ID do insumo
                obj.setNome(rs.getString("nome")); // Define o nome do insumo
                obj.setPreco(rs.getDouble("preco")); // Define o preço do insumo
            } else { // Caso o comando SQL não seja executado com sucesso
                return null; // Retorna nulo
            }
        } catch (SQLException e) { // Caso não consiga localizar um insumo específico
            JOptionPane.showMessageDialog(null, "Erro de SQL: " + e.getMessage()); // Exibe uma mensagem de erro
        }
        return obj; // Retorna o objeto Insumo
    }
}
