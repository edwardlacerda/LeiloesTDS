/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */
import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProdutosDAO {

    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();

    public static boolean cadastrarProduto(ProdutosDTO produto) {
        try {
            conectaDAO conexao = new conectaDAO();
            conexao.conectar();

            String sql = "INSERT INTO produtos (nome, valor, status) VALUES (?, ?, ?);";

            PreparedStatement query = conexao.getConexao().prepareStatement(sql);

            query.setString(1, produto.getNome());
            query.setDouble(2, produto.getValor());
            query.setString(3, produto.getStatus());
            query.execute();

            conexao.desconectar();
            return true;
        } catch (SQLException se) {
            System.out.println("Erro ao executar a consulta: " + se.getMessage());
            return false;
        }

    }

    public ArrayList<ProdutosDTO> listarProdutos() {
        return listagem;
    }

    public void venderProduto(int idProduto) {
        try {
            conectaDAO conexao = new conectaDAO();
            conexao.conectar();

            String sql = "UPDATE produtos SET status = 'vendido' WHERE = ?";

            PreparedStatement query = conexao.getConexao().prepareStatement(sql);
            query.setInt(1, idProduto);
            query.executeUpdate();

        } catch (Exception e) {
            System.out.println("Erro ao executar update" + e);
        }
    }

}
