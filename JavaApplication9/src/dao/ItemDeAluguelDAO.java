package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vo.AluguelVO;
import vo.ItemDeAluguelVO;
import vo.LivroVO;

public class ItemDeAluguelDAO implements IItemDeAluguelDAO {

    private Connection conexao;

    public Connection getConnection() {
        return conexao;
    }

    public void setConnection(Connection connection) {
        this.conexao = connection;
    }

    public boolean inserir(ItemDeAluguelVO itemDeAluguel) {
        String sql = "INSERT INTO itensdealuguel(quantidade, livro_id, aluguel_id) VALUES(?,?,?)";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, itemDeAluguel.getQuantidade());
            stmt.setInt(3, itemDeAluguel.getLivro().getId_livro());
            stmt.setInt(4, itemDeAluguel.getAluguel().getId_aluguel());

            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ItemDeAluguelDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean alterar(ItemDeAluguelVO itemDeAluguel) {
        return true;
    }

    public boolean remover(ItemDeAluguelVO itemDeAluguel) {
        String sql = "DELETE FROM itensdealuguel WHERE id_itemdealuguel = ?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, itemDeAluguel.getId_itemdealuguel());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ItemDeAluguelDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<ItemDeAluguelVO> listar() {
        String sql = "SELECT * FROM itensdealuguel";
        List<ItemDeAluguelVO> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                ItemDeAluguelVO itemDeAluguel = new ItemDeAluguelVO();
                LivroVO livro = new LivroVO();
                AluguelVO aluguel = new AluguelVO();
                itemDeAluguel.setId_itemdealuguel(resultado.getInt("id_itemdealuguel"));
                itemDeAluguel.setQuantidade(resultado.getInt("quantidade"));

                livro.setId_livro(resultado.getInt("livro_id"));
                aluguel.setId_aluguel(resultado.getInt("aluguel_id"));

                //Obtendo os dados completos do Produto associado ao Item de Venda
                LivroDAO livroDAO = new LivroDAO();
                livroDAO.setConnection(conexao);
                livro = livroDAO.buscar(livro);

                AluguelDAO aluguelDAO = new AluguelDAO();
                aluguelDAO.setConnection(conexao);
                aluguel = aluguelDAO.buscar(aluguel);

                itemDeAluguel.setLivro(livro);
                itemDeAluguel.setAluguel(aluguel);

                retorno.add(itemDeAluguel);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemDeAluguelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public List<ItemDeAluguelVO> listarPorVenda(AluguelVO aluguel) {
        String sql = "SELECT * FROM itensdealuguel WHERE id_itemdealuguel=?";
        List<ItemDeAluguelVO> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, aluguel.getId_aluguel());
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                ItemDeAluguelVO itemDeAluguel = new ItemDeAluguelVO();
                LivroVO livro = new LivroVO();
                AluguelVO a = new AluguelVO();
                itemDeAluguel.setId_itemdealuguel(resultado.getInt("id_itemdealuguel"));
                itemDeAluguel.setQuantidade(resultado.getInt("quantidade"));

                livro.setId_livro(resultado.getInt("livro_id"));
                a.setId_aluguel(resultado.getInt("aluguel_id"));

                //Obtendo os dados completos do Livro associado ao Item de Aluguel
                LivroDAO livroDAO = new LivroDAO();
                livroDAO.setConnection(conexao);
                livro = livroDAO.buscar(livro);

                itemDeAluguel.setLivro(livro);
                itemDeAluguel.setAluguel(a);

                retorno.add(itemDeAluguel);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ItemDeAluguelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public ItemDeAluguelVO buscar(ItemDeAluguelVO itemDeAluguel) {
        String sql = "SELECT * FROM itensdealuguel WHERE id_itemdealuguel=?";
        ItemDeAluguelVO retorno = new ItemDeAluguelVO();
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, itemDeAluguel.getId_itemdealuguel());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                LivroVO livro = new LivroVO();
                AluguelVO aluguel = new AluguelVO();
                itemDeAluguel.setId_itemdealuguel(resultado.getInt("id_itemdealuguel"));
                itemDeAluguel.setQuantidade(resultado.getInt("quantidade"));

                livro.setId_livro(resultado.getInt("livro_id"));
                aluguel.setId_aluguel(resultado.getInt("aluguel_id"));

                //Obtendo os dados completos do Aluno associado ao Aluguel
                LivroDAO livroDAO = new LivroDAO();
                livroDAO.setConnection(conexao);
                livro = livroDAO.buscar(livro);

                AluguelDAO aluguelDAO = new AluguelDAO();
                aluguelDAO.setConnection(conexao);
                aluguel = aluguelDAO.buscar(aluguel);

                itemDeAluguel.setLivro(livro);
                itemDeAluguel.setAluguel(aluguel);

                retorno = itemDeAluguel;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AluguelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

}
