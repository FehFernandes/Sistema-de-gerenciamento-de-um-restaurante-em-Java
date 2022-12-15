package DAO;

import Conexao.Conexao;
import Modelo.Funcionario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class DAOFuncionario { // Classe DAO para a tabela Funcionario

    public List<Funcionario> getLista() { // Método para obter a lista de funcionários
        String sql = "SELECT * FROM Funcionario"; // String com o comando SQL
        List<Funcionario> lista = new ArrayList<>(); // Lista de funcionários
        try { // Tenta obter a lista de funcionários
            PreparedStatement pst = Conexao.getPreparedStatement(sql); // Obtém o PreparedStatement
            ResultSet rs = pst.executeQuery(); // Executa o comando SQL e obtém o ResultSet
            while (rs.next()) { // Enquanto houver funcionários
                Funcionario obj = new Funcionario(); // Cria um novo objeto Funcionario
                obj.setId_funcionario(rs.getInt("id_funcionario")); // Define o ID do funcionário
                obj.setNome(rs.getString("nome")); // Define o nome do funcionário
                obj.setCpf(rs.getString("cpf")); // Define o CPF do funcionário
                obj.setSalario(rs.getDouble("salario")); // Define o salário do funcionário
                obj.setSexo(rs.getString("sexo").charAt(0)); // Define o sexo do funcionário
                obj.setTelefone(rs.getString("telefone")); // Define o telefone do funcionário
                lista.add(obj); // Adiciona o funcionário na lista
            }
        } catch (SQLException e) { // Caso não consiga obter a lista de funcionários
            JOptionPane.showMessageDialog(null, "Erro de SQL: " + e.getMessage()); // Exibe uma mensagem de erro
        }
        return lista; // Retorna a lista de funcionários
    }

    public void salvar(Object o) { // Método para salvar um funcionário
        Funcionario obj = (Funcionario) o; // Converte o objeto para Funcionario
        if (obj.getId_funcionario() == null) { // Verifica se o funcionário já existe
            incluir(obj); // Chama o método para incluir um funcionário
        } else { // Caso o funcionário já exista
            atualizar(obj); // Chama o método para atualizar um funcionário
        }
    }

    private void incluir(Funcionario obj) { // Método para incluir um funcionário
        String sql = "INSERT INTO Funcionario (nome,cpf,salario,sexo,telefone) VALUES (?,?,?,?,?)"; // String com o comando SQL
        try { // Tenta incluir o funcionário
            PreparedStatement pst = Conexao.getPreparedStatement(sql); // Obtém o PreparedStatement
            pst.setString(1, obj.getNome()); // Define o nome do funcionário
            pst.setString(2, obj.getCpf()); // Define o CPF do funcionário
            pst.setDouble(3, obj.getSalario()); // Define o salário do funcionário
            pst.setString(4, Character.toString(obj.getSexo())); // Define o sexo do funcionário
            pst.setString(5, obj.getTelefone()); // Define o telefone do funcionário
            if (pst.executeUpdate() > 0) { // Se o funcionário for incluído com sucesso
                JOptionPane.showMessageDialog(null, "Funcionario incluído com sucesso!"); // Exibe uma mensagem de sucesso
            } else { // Caso o funcionário não seja incluído com sucesso
                JOptionPane.showMessageDialog(null, "Funcionario não incluído com sucesso!"); // Exibe uma mensagem de erro
            }

        } catch (SQLException e) { // Caso não consiga incluir o funcionário
            JOptionPane.showMessageDialog(null, "Erro de SQL: " + e.getMessage()); // Exibe uma mensagem de erro
        }
    }

    private void atualizar(Funcionario obj) { // Método para atualizar um funcionário
        String sql = "UPDATE Funcionario SET nome = ?, cpf = ?, salario = ?, sexo = ?, telefone = ? WHERE id_funcionario = ?"; // String com o comando SQL
        try { // Tenta atualizar o funcionário
            PreparedStatement pst = Conexao.getPreparedStatement(sql); // Obtém o PreparedStatement
            pst.setString(1, obj.getNome()); // Define o nome do funcionário
            pst.setString(2, obj.getCpf()); // Define o CPF do funcionário
            pst.setDouble(3, obj.getSalario()); // Define o salário do funcionário
            pst.setString(4, Character.toString(obj.getSexo())); // Define o sexo do funcionário
            pst.setString(5, obj.getTelefone()); // Define o telefone do funcionário
            pst.setInt(6, obj.getId_funcionario()); // Define o ID do funcionário
            if (pst.executeUpdate() > 0) { // Se o funcionário for atualizado com sucesso
                JOptionPane.showMessageDialog(null, "Funcionario atualizado com sucesso!"); // Exibe uma mensagem de sucesso
            } else { // Caso o funcionário não seja atualizado com sucesso
                JOptionPane.showMessageDialog(null, "Funcionario não atualizado com sucesso!"); // Exibe uma mensagem de erro
            }

        } catch (SQLException e) { // Caso não consiga atualizar o funcionário
            JOptionPane.showMessageDialog(null, "Erro de SQL: " + e.getMessage()); // Exibe uma mensagem de erro
        }
    }

    public void remover(Object o) { // Método para remover um funcionário
        Funcionario obj = (Funcionario) o; // Converte o objeto para Funcionario
        String sql = "DELETE FROM Funcionario WHERE id_funcionario = ?"; // String com o comando SQL
        try { // Tenta remover o funcionário
            PreparedStatement pst = Conexao.getPreparedStatement(sql); // Obtém o PreparedStatement
            pst.setInt(1, obj.getId_funcionario()); // Define o ID do funcionário
            if (pst.executeUpdate() > 0) { // Se o funcionário for removido com sucesso
                JOptionPane.showMessageDialog(null, "Funcionario excluído com sucesso!"); // Exibe uma mensagem de sucesso
            } else { // Caso o funcionário não seja removido com sucesso
                JOptionPane.showMessageDialog(null, "Funcionario não excluído com sucesso!"); // Exibe uma mensagem de erro
            }

        } catch (SQLException e) { // Caso não consiga remover o funcionário
            JOptionPane.showMessageDialog(null, "Erro de SQL: " + e.getMessage()); // Exibe uma mensagem de erro
        }
    }

    public Funcionario localizar(int id) { // Método para localizar um funcionário
        String sql = "SELECT * FROM Funcionario WHERE id_funcionario = ?"; // String com o comando SQL
        List<Funcionario> lista = new ArrayList<>(); // Cria uma lista de funcionários
        try { // Tenta localizar o funcionário
            PreparedStatement pst = Conexao.getPreparedStatement(sql); // Obtém o PreparedStatement
            pst.setInt(1, id); // Define o ID do funcionário
            ResultSet rs = pst.executeQuery(); // Obtém o ResultSet
            if (rs.next()) { // Se o funcionário for localizado
                Funcionario obj = new Funcionario(); // Cria um novo funcionário
                obj.setId_funcionario(rs.getInt("id_funcionario")); // Define o ID do funcionário
                obj.setNome(rs.getString("nome")); // Define o nome do funcionário
                obj.setCpf(rs.getString("cpf")); // Define o CPF do funcionário
                obj.setSalario(rs.getDouble("salario")); // Define o salário do funcionário
                obj.setSexo(rs.getString("sexo").charAt(0)); // Define o sexo do funcionário
                obj.setTelefone(rs.getString("telefone")); // Define o telefone do funcionário
            }
        } catch (SQLException e) { // Caso não consiga localizar o funcionário
            JOptionPane.showMessageDialog(null, "Erro de SQL: " + e.getMessage()); // Exibe uma mensagem de erro
        }
        return (Funcionario) lista; // Retorna a lista de funcionários
    }
}
