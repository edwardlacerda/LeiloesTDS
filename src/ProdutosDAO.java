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
import java.util.List;

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

    public List<ProdutosDTO> listarProdutosVendidos() {
        try {
            conectaDAO conexao = new conectaDAO();
            conexao.conectar();

            List<ProdutosDTO> produtosVendidos = new ArrayList<>();
            String sql = "SELECT id, nome, valor, status FROM produtos WHERE status = 'vendido'";
             PreparedStatement query = conexao.getConexao().prepareStatement(sql);
             
            resultset.next();
            ProdutosDTO produto = new ProdutosDTO();
            produto.setId(resultset.getInt("id"));
            produto.setNome(resultset.getString("nome"));
            produto.setValor(resultset.getInt("valor"));
            produto.setStatus(resultset.getString("status"));
            produtosVendidos.add(produto);

        } catch (SQLException e) {
            System.err.println("Erro ao listar produtos vendidos: " + e.getMessage());
        }

        return listarProdutosVendidos();
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
