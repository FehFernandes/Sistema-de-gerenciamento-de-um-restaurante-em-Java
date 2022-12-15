package Conexao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.DriverManager;

public class Conexao { // Classe de conexão com o banco de dados

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver"; // Driver de conexão com o banco de dados
    private static final String BANCO = "jdbc:mysql://localhost:3306/pratica1"; // Endereço do banco de dados
    private static final String USUARIO = "root"; // Usuário do banco de dados
    private static final String SENHA = ""; // Senha do banco de dados
    private static Connection con = null; // Objeto de conexão com o banco de dados

    public static Connection getConnection() throws SQLException { // Método para obter a conexão com o banco de dados
        if (con == null) { // Verifica se a conexão já foi estabelecida
            try { // Tenta estabelecer a conexão com o banco de dados
                Class.forName(DRIVER); // Carrega o driver de conexão com o banco de dados
                con = DriverManager.getConnection(BANCO, USUARIO, SENHA); // Estabelece a conexão com o banco de dados
            } catch (ClassNotFoundException e) { // Caso não consiga estabelecer a conexão com o banco de dados
                System.out.println("Erro no driver "); // Exibe uma mensagem de erro
            } catch (SQLException e) { // Caso não consiga estabelecer a conexão com o banco de dados
                e.printStackTrace(); // Exibe uma mensagem de erro
            }
        }
        return con;  // Retorna a conexão com o banco de dados
    }

    public static PreparedStatement getPreparedStatement(String sql) throws SQLException { // Método para obter o PreparedStatement
        if (con == null) { // Verifica se a conexão já foi estabelecida
            con = getConnection(); // Estabelece a conexão com o banco de dados
        }

        try { // Tenta obter o PreparedStatement
            return con.prepareStatement(sql); // Retorna o PreparedStatement
        } catch (SQLException e) { // Caso não consiga obter o PreparedStatement
            System.out.println("Erro de SQL : " + e.getMessage()); // Exibe uma mensagem de erro
        }
        return null; // Retorna null
    }
}
