package DAO;

import Modelo.Fornecedor;
import Conexao.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class DAOFornecedor { // Classe DAO para a tabela Fornecedor
    public List<Fornecedor> getLista(){ // Método para obter a lista de fornecedores
        String sql = "SELECT * FROM Fornecedor"; // String com o comando SQL
        List<Fornecedor> lista = new ArrayList<>(); // Lista de fornecedores
        
       try { // Tenta obter a lista de fornecedores
            PreparedStatement pst = Conexao.getPreparedStatement(sql); // Obtém o PreparedStatement
            ResultSet rs = pst.executeQuery(); // Executa o comando SQL e obtém o ResultSet
            while (rs.next()) { // Enquanto houver fornecedores
                Fornecedor obj = new Fornecedor(); // Cria um novo objeto Fornecedor
                obj.setCnpj(rs.getString("cnpj")); // Define o CNPJ do fornecedor
                obj.setNome(rs.getString("nome")); // Define o nome do fornecedor
                obj.setTelefone(rs.getString("telefone")); // Define o telefone do fornecedor
                lista.add(obj); // Adiciona o fornecedor na lista
            }
        } catch (SQLException e) { // Caso não consiga obter a lista de fornecedores
            JOptionPane.showMessageDialog(null, "Erro de SQL: " + e.getMessage()); // Exibe uma mensagem de erro
        }
       
       return lista; // Retorna a lista de fornecedores
    }
    
    public void salvar(Object o){ // Método para salvar um fornecedor
        Fornecedor obj = (Fornecedor)o; // Converte o objeto para Fornecedor
        Fornecedor fornecedorExistente = localizarEspecifico(obj.getCnpj()); // Obtém o fornecedor com o CNPJ informado
        
        if(fornecedorExistente == null){ // Verifica se o fornecedor não existe
            incluir(obj); // Inclui o fornecedor
        } else { // Caso o fornecedor já exista
            atualizar(obj); // Atualiza o fornecedor
        } 
    }
    
    private void incluir(Fornecedor obj){ // Método para incluir um fornecedor
        String sql = "INSERT INTO Fornecedor (cnpj,nome,telefone) VALUES (?,?,?)"; // String com o comando SQL
        try { // Tenta incluir o fornecedor
            PreparedStatement pst = Conexao.getPreparedStatement(sql); // Obtém o PreparedStatement

            pst.setString(1, obj.getCnpj()); // Define o CNPJ do fornecedor
            pst.setString(2, obj.getNome()); // Define o nome do fornecedor
            pst.setString(3, obj.getTelefone()); // Define os parâmetros do comando SQL
            
            if (pst.executeUpdate() > 0) { // Executa o comando SQL e verifica se foi incluído
                JOptionPane.showMessageDialog(null, "Fornecedor incluído com sucesso!"); // Exibe uma mensagem de sucesso
            } else { // Caso não consiga incluir o fornecedor
                JOptionPane.showMessageDialog(null, "Não foi possível incluir o fornecedor!"); // Exibe uma mensagem de erro
            }
            
        } catch (SQLException e) { // Caso não consiga incluir o fornecedor
            JOptionPane.showMessageDialog(null, "Erro de SQL: " + e.getMessage()); // Exibe uma mensagem de erro
        }
    }
    
    private void atualizar(Fornecedor obj){ // Método para atualizar um fornecedor
        String sql = "UPDATE Fornecedor SET nome = ?, telefone = ? WHERE cnpj = ?"; // String com o comando SQL
        try { // Tenta atualizar o fornecedor
            PreparedStatement pst = Conexao.getPreparedStatement(sql); // Obtém o PreparedStatement
            
            pst.setString(1, obj.getNome()); // Define o nome do fornecedor
            pst.setString(2, obj.getTelefone()); // Define os parâmetros do comando SQL
            pst.setString(3, obj.getCnpj()); // Define os parâmetros do comando SQL
            
            if (pst.executeUpdate() > 0) { // Executa o comando SQL e verifica se foi atualizado
                JOptionPane.showMessageDialog(null, "Fornecedor atualizado com sucesso!"); // Exibe uma mensagem de sucesso
            } else { // Caso não consiga atualizar o fornecedor
                JOptionPane.showMessageDialog(null, "Não foi possível atualizar o Fornecedor!"); // Exibe uma mensagem de erro
            }
            
        } catch (SQLException e) { // Caso não consiga atualizar o fornecedor
            JOptionPane.showMessageDialog(null, "Erro de SQL: " + e.getMessage()); // Exibe uma mensagem de erro
        }
    }
    
    public void remover(Object o){ // Método para remover um fornecedor
        Fornecedor obj = (Fornecedor)o; // Converte o objeto para Fornecedor
        String sql = "DELETE FROM Fornecedor WHERE cnpj = ?"; // String com o comando SQL
        try { // Tenta remover o fornecedor
            PreparedStatement pst = Conexao.getPreparedStatement(sql); // Obtém o PreparedStatement
            
            pst.setString(1, obj.getCnpj()); // Define o CNPJ do fornecedor
            
            if (pst.executeUpdate() > 0) { // Executa o comando SQL e verifica se foi removido
                JOptionPane.showMessageDialog(null, "Fornecedor excluído com sucesso!"); // Exibe uma mensagem de sucesso
            } else { // Caso não consiga remover o fornecedor
                JOptionPane.showMessageDialog(null, "Não foi possível escluir o fornecedor!"); // Exibe uma mensagem de erro
            }
            
        } catch (SQLException e) { // Caso não consiga remover o fornecedor
            JOptionPane.showMessageDialog(null, "Erro de SQL: " + e.getMessage()); // Exibe uma mensagem de erro
        }
    }
    
    public Fornecedor localizar(String cnpj){ // Método para localizar um fornecedor
        String sql = "SELECT * FROM Fornecedor WHERE cnpj = ?"; // String com o comando SQL
        Fornecedor obj = new Fornecedor(); // Cria um novo fornecedor
        try { // Tenta localizar o fornecedor
            PreparedStatement pst = Conexao.getPreparedStatement(sql); // Obtém o PreparedStatement
            pst.setString(1, cnpj); // Define o CNPJ do fornecedor
            ResultSet rs = pst.executeQuery(); // Executa o comando SQL e obtém o ResultSet
            
            if(rs.next()){ // Verifica se o fornecedor foi localizado
                obj.setCnpj(rs.getString("cnpj")); // Define o CNPJ do fornecedor
                obj.setNome(rs.getString("nome")); // Define o nome do fornecedor
                obj.setTelefone(rs.getString("telefone")); // Define o telefone do fornecedor
            } else {    // Caso não consiga localizar o fornecedor
                JOptionPane.showMessageDialog(null, "Fornecedor não encontrado!"); // Exibe uma mensagem de erro
            }
        } catch (SQLException e) { // Caso não consiga localizar o fornecedor
            JOptionPane.showMessageDialog(null, "Erro de SQL: " + e.getMessage()); // Exibe uma mensagem de erro
        }
        return obj; // Retorna o fornecedor
    }
    
    public Fornecedor localizarEspecifico(String cnpj){ // Método para localizar um fornecedor
        String sql = "SELECT * FROM Fornecedor WHERE cnpj = ?"; // String com o comando SQL
        Fornecedor obj = new Fornecedor(); // Cria um novo fornecedor
        try { // Tenta localizar o fornecedor
            PreparedStatement pst = Conexao.getPreparedStatement(sql); // Obtém o PreparedStatement
            pst.setString(1, cnpj); // Define o CNPJ do fornecedor
            ResultSet rs = pst.executeQuery(); // Executa o comando SQL e obtém o ResultSet

            if(rs.next()){ // Verifica se o fornecedor foi localizado
                obj.setCnpj(rs.getString("cnpj")); // Define o CNPJ do fornecedor
                obj.setNome(rs.getString("nome")); // Define o nome do fornecedor
                obj.setTelefone(rs.getString("telefone")); // Define o telefone do fornecedor
            } else {   // Caso não consiga localizar o fornecedor
                return null; // Retorna null
            }
        } catch (SQLException e) { // Caso não consiga localizar o fornecedor
            JOptionPane.showMessageDialog(null, "Erro de SQL: " + e.getMessage()); // Exibe uma mensagem de erro
        }
        return obj; // Retorna o fornecedor
    }
}
