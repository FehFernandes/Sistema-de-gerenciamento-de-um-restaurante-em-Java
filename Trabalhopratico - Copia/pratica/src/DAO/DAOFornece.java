package DAO;

import Conexao.Conexao;
import Modelo.Fornece;
import Modelo.Fornecedor;
import Modelo.Insumo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class DAOFornece { // Classe DAO para a tabela Fornece

    DAOFornecedor daoFornecedor = new DAOFornecedor(); // Objeto DAO para a tabela Fornecedor
    DAOInsumo daoInsumo = new DAOInsumo(); // Objeto DAO para a tabela Insumo

    public List<Fornece> getLista() { // Método para obter a lista de fornecedores de um insumo
        String sql = "SELECT * FROM Fornece"; // String com o comando SQL
        List<Fornece> lista = new ArrayList<>(); // Lista de fornecedores de um insumo
        try { // Tenta obter a lista de fornecedores de um insumo
            PreparedStatement pst = Conexao.getPreparedStatement(sql); // Obtém o PreparedStatement
            ResultSet rs = pst.executeQuery(); // Executa o comando SQL
            while (rs.next()) { // Enquanto houver fornecedores de um insumo
                Fornece obj = new Fornece(); // Cria um objeto da classe Fornece
                obj.setFornecedor_cnpj(daoFornecedor.localizar(rs.getString("fornecedor_cnpj"))); // Seta o fornecedor do objeto
                obj.setInsumo_id_insumo(daoInsumo.localizar(rs.getInt("insumo_id_insumo"))); // Seta o insumo do objeto
                obj.setQnt_fornecida(rs.getInt("qnt_fornecida")); // Seta a quantidade fornecida do objeto
                lista.add(obj); // Adiciona o objeto na lista
            }
        } catch (SQLException e) { // Caso não consiga obter a lista de fornecedores de um insumo
            JOptionPane.showMessageDialog(null, "Erro de SQL: " + e.getMessage()); // Exibe uma mensagem de erro
        }
        return lista; // Retorna a lista de fornecedores de um insumo
    }

    public void salvar(Object o) { // Método para salvar um fornecedor de um insumo
        Fornece obj = (Fornece) o; // Converte o objeto para a classe Fornece
        Fornecedor fornecedorExistente = daoFornecedor.localizarEspecifico(obj.getFornecedor_cnpj().getCnpj()); // Obtém o fornecedor do objeto
        Insumo insumoExistente = daoInsumo.localizarEspecifico(obj.getInsumo_id_insumo().getId_insumo()); // Obtém o insumo do objeto

        if (fornecedorExistente != null && insumoExistente != null) { // Se o fornecedor e o insumo existirem
            incluir(obj); // Chama o método para incluir o fornecedor de um insumo
        } else { // Se o fornecedor ou o insumo não existirem
            //Atualizar o fornecedor e o insumo
            atualizar(obj); // Chama o método para atualizar o fornecedor de um insumo
        }
    }

    private void incluir(Fornece obj) { // Método para incluir um fornecedor de um insumo
        String sql = "INSERT INTO Fornece (fornecedor_cnpj, insumo_id_insumo, qnt_fornecida) VALUES (?,?,?)"; // String com o comando SQL
        try { // Tenta incluir um fornecedor de um insumo
            PreparedStatement pst = Conexao.getPreparedStatement(sql); // Obtém o PreparedStatement
            pst.setString(1, obj.getFornecedor_cnpj().getCnpj()); // Seta o CNPJ do fornecedor
            pst.setInt(2, obj.getInsumo_id_insumo().getId_insumo()); // Seta o ID do insumo
            pst.setInt(3, obj.getQnt_fornecida()); // Seta a quantidade fornecida
            if (pst.executeUpdate() > 0) { // Se o comando SQL for executado com sucesso
                JOptionPane.showMessageDialog(null, "Dados da tabela Fornece incluídos com sucesso!"); // Exibe uma mensagem de sucesso
            } else {    // Se o comando SQL não for executado com sucesso
                JOptionPane.showMessageDialog(null, "Não foi possível incluir os dados na tabela Fornece!"); // Exibe uma mensagem de erro
            }

        } catch (SQLException e) { // Caso não consiga incluir um fornecedor de um insumo
            JOptionPane.showMessageDialog(null, "Erro de SQL: " + e.getMessage()); // Exibe uma mensagem de erro
        }
    }

    private void atualizar(Fornece obj) { // Método para atualizar um fornecedor de um insumo
        String sql = "UPDATE Fornece SET qnt_fornecida = ? WHERE fornecedor_cnpj = ? AND insumo_id_insumo = ?"; // String com o comando SQL
        try { // Tenta atualizar um fornecedor de um insumo
            PreparedStatement pst = Conexao.getPreparedStatement(sql); // Obtém o PreparedStatement
            pst.setInt(1, obj.getQnt_fornecida()); // Seta a quantidade fornecida
            pst.setString(2, obj.getFornecedor_cnpj().getCnpj()); // Seta o CNPJ do fornecedor
            pst.setInt(3, obj.getInsumo_id_insumo().getId_insumo()); // Seta o ID do insumo
            if (pst.executeUpdate() > 0) { // Se o comando SQL for executado com sucesso
                JOptionPane.showMessageDialog(null, "Tabela Fornece atualizada com sucesso!"); // Exibe uma mensagem de sucesso
            } else {   // Se o comando SQL não for executado com sucesso
                JOptionPane.showMessageDialog(null, "Não foi possível atualizar os dados da tabela Fornece!"); // Exibe uma mensagem de erro
            }

        } catch (SQLException e) { // Caso não consiga atualizar um fornecedor de um insumo
            JOptionPane.showMessageDialog(null, "Erro de SQL: " + e.getMessage()); // Exibe uma mensagem de erro
        }
    }

    public void remover(Object o) { // Método para remover um fornecedor
        Fornece obj = (Fornece) o; // Converte o objeto para a classe Fornece
        String sql = "DELETE FROM Fornece WHERE fornecedor_cnpj = ? AND insumo_id_insumo = ?"; // String com o comando SQL
        try { // Tenta remover um fornecedor
            PreparedStatement pst = Conexao.getPreparedStatement(sql); // Obtém o PreparedStatement

            pst.setString(1, obj.getFornecedor_cnpj().getCnpj()); // Seta o CNPJ do fornecedor
            pst.setInt(2, obj.getInsumo_id_insumo().getId_insumo()); // Seta o ID do insumo

            if (pst.executeUpdate() > 0) { // Se o comando SQL for executado com sucesso
                JOptionPane.showMessageDialog(null, "Dado da tabela Fornece excluído com sucesso!"); // Exibe uma mensagem de sucesso
            } else {
                JOptionPane.showMessageDialog(null, "Não foi possível excluir o dado da tabela Fornece!"); // Exibe uma mensagem de erro
            }

        } catch (SQLException e) { // Caso não consiga remover um fornecedor
            JOptionPane.showMessageDialog(null, "Erro de SQL: " + e.getMessage()); // Exibe uma mensagem de erro
        }
    }
}
