package DAO;

import Conexao.Conexao;
import Modelo.Pedido;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class DAOPedido { //Classe DAO para Pedido

    DAOFuncionario daoFuncionario = new DAOFuncionario(); //Instanciando a classe DAOFuncionario

    public List<Pedido> getLista() { //Método para listar os pedidos
        String sql = "SELECT * FROM Pedido"; //Comando SQL
        List<Pedido> lista = new ArrayList<>(); //Lista de pedidos

        try { //Tentativa de conexão
            PreparedStatement pst = Conexao.getPreparedStatement(sql); //Preparando a conexão
            ResultSet rs = pst.executeQuery(); //Executando a conexão
            while (rs.next()) { //Enquanto houver dados
                Pedido obj = new Pedido(); //Instanciando a classe Pedido
                obj.setId_pedido(rs.getInt("id_pedido")); //Setando o id do pedido
                obj.setId_funcionario(daoFuncionario.localizar(rs.getInt("id_funcionario"))); //Setando o id do funcionário
                obj.setValor_total(rs.getDouble("valor_total")); //Setando o valor total
                obj.setDescricao(rs.getString("descricao")); //Setando a descrição
                lista.add(obj); //Adicionando o objeto na lista
            }
        } catch (SQLException e) { //Caso haja erro
            JOptionPane.showMessageDialog(null, "Erro de SQL: " + e.getMessage()); //Exibe a mensagem de erro
        }

        return lista; //Retorna a lista
    }

    public void salvar(Object o) { //Método para salvar um pedido
        Pedido obj = (Pedido) o; //Instanciando a classe Pedido
        if (obj.getId_pedido() == null) { //Se o id do pedido for nulo
            incluir(obj); //Chama o método incluir
        } else {  //Caso contrário
            atualizar(obj); //Chama o método atualizar
        }
    }

    private void incluir(Pedido obj) { //Método para incluir um pedido
        String sql = "INSERT INTO Pedido (id_funcionario,valor_total,descricao) VALUES (?,?,?)"; //Comando SQL
        try { //Tentativa de conexão
            PreparedStatement pst = Conexao.getPreparedStatement(sql); //Preparando a conexão

            pst.setInt(1, obj.getId_funcionario().getId_funcionario()); //Setando o id do funcionário
            pst.setDouble(2, obj.getValor_total()); //Setando o valor total
            pst.setString(3, obj.getDescricao()); //Setando a descrição

            if (pst.executeUpdate() > 0) { //Se a conexão for bem sucedida
                JOptionPane.showMessageDialog(null, "Pedido incluído com sucesso!"); //Exibe a mensagem de sucesso
            } else { //Caso contrário
                JOptionPane.showMessageDialog(null, "Não foi possível incluir o pedido!"); //Exibe a mensagem de erro
            }

        } catch (SQLException e) { //Caso haja erro
            JOptionPane.showMessageDialog(null, "Erro de SQL: " + e.getMessage()); //Exibe a mensagem de erro
        }
    }

    private void atualizar(Pedido obj) { //Método para atualizar um pedido
        String sql = "UPDATE Pedido SET id_funcionario = ?, valor_total = ?, descricao = ?\n"
                + "WHERE id_pedido = ?"; //Comando SQL
        try { //Tentativa de conexão
            PreparedStatement pst = Conexao.getPreparedStatement(sql); //Preparando a conexão

            pst.setInt(1, obj.getId_funcionario().getId_funcionario()); //Setando o id do funcionário
            pst.setDouble(2, obj.getValor_total()); //Setando o valor total
            pst.setString(3, obj.getDescricao()); //Setando a descrição
            pst.setInt(4, obj.getId_pedido()); //Setando o id do pedido

            if (pst.executeUpdate() > 0) { //Se a conexão for bem sucedida
                JOptionPane.showMessageDialog(null, "Pedido atualizado com sucesso!"); //Exibe a mensagem de sucesso
            } else { //Caso contrário
                JOptionPane.showMessageDialog(null, "Não foi possível atuaçizar o pedido!"); //Exibe a mensagem de erro
            }

        } catch (SQLException e) { //Caso haja erro
            JOptionPane.showMessageDialog(null, "Erro de SQL: " + e.getMessage()); //Exibe a mensagem de erro
        }
    }

    public void remover(Object o) { //Método para remover um pedido
        Pedido obj = (Pedido) o; //Instanciando a classe Pedido
        String sql = "DELETE FROM Pedido WHERE id_pedido = ?"; //Comando SQL
        try { //Tentativa de conexão
            PreparedStatement pst = Conexao.getPreparedStatement(sql); //Preparando a conexão

            pst.setInt(1, obj.getId_pedido()); //Setando o id do pedido

            if (pst.executeUpdate() > 0) { //Se a conexão for bem sucedida
                JOptionPane.showMessageDialog(null, "Pedido excluído com sucesso!"); //Exibe a mensagem de sucesso
            } else { //Caso contrário
                JOptionPane.showMessageDialog(null, "Não foi possível escluir o pedido!"); //Exibe a mensagem de erro
            }

        } catch (SQLException e) { //Caso haja erro
            JOptionPane.showMessageDialog(null, "Erro de SQL: " + e.getMessage()); //Exibe a mensagem de erro
        }
    }

    public Pedido localizar(int id) { //Método para localizar um pedido
        String sql = "SELECT * FROM Pedido WHERE id_pedido = ?"; //Comando SQL
        Pedido obj = new Pedido(); //Instanciando a classe Pedido
        try { //Tentativa de conexão
            PreparedStatement pst = Conexao.getPreparedStatement(sql); //Preparando a conexão
            pst.setInt(1, id); //Setando o id do pedido
            ResultSet rs = pst.executeQuery(); //Executando a conexão

            if (rs.next()) { //Se houver resultado
                obj.setId_pedido(rs.getInt("id_pedido")); //Setando o id do pedido
                obj.setId_funcionario(daoFuncionario.localizar(rs.getInt("id_funcionario"))); //Setando o id do funcionário
                obj.setValor_total(rs.getInt("valor_total")); //Setando o valor total
                obj.setDescricao(rs.getString("descricao")); //Setando a descrição
            } else { //Caso contrário
                JOptionPane.showMessageDialog(null, "Pedido não encontrado!"); //Exibe a mensagem de erro
            }
        } catch (SQLException e) { //Caso haja erro
            JOptionPane.showMessageDialog(null, "Erro de SQL: " + e.getMessage()); //Exibe a mensagem de erro
        }
        return obj; //Retorna o objeto
    }

    public Pedido localizarEspecifico(int id) { //Método para localizar um pedido específico
        String sql = "SELECT * FROM Pedido WHERE id_pedido = ?"; //Comando SQL
        Pedido obj = new Pedido(); //Instanciando a classe Pedido
        try { //Tentativa de conexão
            PreparedStatement pst = Conexao.getPreparedStatement(sql); //Preparando a conexão
            pst.setInt(1, id); //Setando o id do pedido
            ResultSet rs = pst.executeQuery(); //Executando a conexão

            if (rs.next()) { //Se houver resultado
                obj.setId_pedido(rs.getInt("id_pedido")); //Setando o id do pedido
                obj.setId_funcionario(daoFuncionario.localizar(rs.getInt("id_funcionario"))); //Setando o id do funcionário
                obj.setValor_total(rs.getInt("valor_total")); //Setando o valor total
                obj.setDescricao(rs.getString("descricao")); //Setando a descrição
            } else { //Caso contrário
                return null; //Retorna nulo
            }
        } catch (SQLException e) { //Caso haja erro
            JOptionPane.showMessageDialog(null, "Erro de SQL: " + e.getMessage()); //Exibe a mensagem de erro
        }
        return obj; //Retorna o objeto
    }
}
