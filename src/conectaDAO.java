
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Adm
 */
public class conectaDAO {

    private Connection conexao;

    public Connection getConexao() {
        return conexao;
    }

    private final String url = "jdbc:mysql://localhost:3306/uc11?useSSL=false";
    private final String user = "root";
    private final String password = "root";

    public void conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection(url, user, password);
            System.out.println("Conectado!");

        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());

        }
    }

    public void desconectar() {
        try {
            conexao.close();
        } catch (SQLException ex) {

        }
    }

}
