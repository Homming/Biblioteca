package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import vo.AluguelVO;
import vo.AlunoVO;
import vo.ItemDeAluguelVO;
import vo.LivroVO;

public class AluguelDAO implements IAluguelDAO {

    private Connection conexao;

    public Connection getConnection() {
        return conexao;
    }

    public void setConnection(Connection conexao) {
        this.conexao = conexao;
    }

    @Override
    public boolean cadastrar(AluguelVO aluguel) throws SQLException {
        String sql = "INSERT INTO aluguel(data_aluguel, aluno_id, data_devolucao, devolvido) VALUES(?,?,?,?)";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setDate(1, Date.valueOf(aluguel.getData_aluguel()));
            stmt.setInt(2, aluguel.getAluno().getId_aluno());
            stmt.setDate(3, Date.valueOf(aluguel.getData_devolucao()));
            stmt.setBoolean(4, aluguel.getDevolvido());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AluguelDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean alterar(AluguelVO aluguel) {
        String sql = "UPDATE aluguel SET data_aluguel=?, aluno_id=?, livro_id=? WHERE Id_aluguel=?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setDate(1, Date.valueOf(aluguel.getData_aluguel()));
            stmt.setInt(2, aluguel.getAluno().getId_aluno());
            stmt.setInt(3, aluguel.getLivro().getId_livro());
            stmt.setInt(4, aluguel.getId_aluguel());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AluguelDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean remover(AluguelVO aluguel) {
        String sql = "DELETE FROM aluguel WHERE Id_aluguel=?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, aluguel.getId_aluguel());

            int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza de que deseja excluir o registro do aluguel?", "Atenção",
                    +JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

            if (confirma == JOptionPane.YES_NO_OPTION) {
                stmt.execute();
                JOptionPane.showMessageDialog(null, "Registro excluído com sucesso!");

            }

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AluguelDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<AluguelVO> listar() {
        String sql = "SELECT * FROM aluguel";
        List<AluguelVO> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                AluguelVO aluguel = new AluguelVO();
                AlunoVO aluno = new AlunoVO();
                LivroVO livro = new LivroVO();
                List<ItemDeAluguelVO> itensDeAluguel = new ArrayList();

                aluguel.setId_aluguel(resultado.getInt("id_aluguel"));
                aluguel.setData_aluguel(resultado.getDate("data_aluguel").toLocalDate());
                aluno.setId_aluno(resultado.getInt("aluno_id"));
                livro.setId_livro(resultado.getInt("livro_id"));
                aluguel.setData_devolucao(resultado.getDate("data_devolucao").toLocalDate());
                aluguel.setDevolvido(resultado.getBoolean("devolvido"));

                //Obtendo os dados completos do Aluno associado
                AlunoDAO alunoDAO = new AlunoDAO();
                alunoDAO.setConnection(conexao);
                aluno = alunoDAO.buscar(aluno);

                //Obtendo os dados completos dos Itens de Venda associados à Venda
                ItemDeAluguelDAO itemDeAluguelDAO = new ItemDeAluguelDAO();
                itemDeAluguelDAO.setConnection(conexao);
                itensDeAluguel = itemDeAluguelDAO.listarPorVenda(aluguel);

                //Obtendo os dados completos do Livro associado
                LivroDAO livroDAO = new LivroDAO();
                livroDAO.setConnection(conexao);
                livro = livroDAO.buscar(livro);

                aluguel.setAluno(aluno);
                aluguel.setItensDeAluguel(itensDeAluguel);
                aluguel.setLivro(livro);
                retorno.add(aluguel);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AluguelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public AluguelVO buscar(AluguelVO aluguel) {
        String sql = "SELECT * FROM aluguel WHERE id_aluguel=?";
        AluguelVO retorno = new AluguelVO();
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, aluguel.getId_aluguel());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                AlunoVO aluno = new AlunoVO();
                aluguel.setId_aluguel(resultado.getInt("id_aluguel"));
                aluguel.setData_aluguel(resultado.getDate("data_aluguel").toLocalDate());
                aluno.setId_aluno(resultado.getInt("aluno_id"));
                // livro ?
                aluguel.setDevolvido(resultado.getBoolean("devolvido"));
                aluguel.setAluno(aluno);
                retorno = aluguel;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AluguelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public AluguelVO buscarUltimoAluguel() {
        String sql = "SELECT MAX(Id_aluguel) as id_aluguel FROM aluguel";
        AluguelVO retorno = new AluguelVO();
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();

            if (resultado.next()) {
                retorno.setId_aluguel(resultado.getInt("id_aluguel"));
                return retorno;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AluguelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    //Map<Chave, Valor> No nosso caso : <Ano, arraylist de meses e a qtd de alugueis em cada mes>
    public Map<Integer, ArrayList> listarAlugueisPorMes() {
        String sql = "SELECT COUNT(Id_aluguel) as id_aluguel, extract(year from data_aluguel) as ano, extract(month from data_aluguel) as mes from aluguel group by ano, mes order by ano, mes";
        Map<Integer, ArrayList> retorno = new HashMap();

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();

            while (resultado.next()) {
                ArrayList linha = new ArrayList();
                if (!retorno.containsKey(resultado.getInt("ano"))) {
                    linha.add(resultado.getInt("mes"));
                    linha.add(resultado.getInt("id_aluguel"));
                    retorno.put(resultado.getInt("ano"), linha);
                } else {
                    ArrayList linhaNova = retorno.get(resultado.getInt("ano"));
                    linhaNova.add(resultado.getInt("mes"));
                    linhaNova.add(resultado.getInt("id_aluguel"));
                }
            }
            return retorno;
        } catch (SQLException ex) {
            Logger.getLogger(AluguelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

}
