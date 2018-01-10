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
import vo.AluguelVO;
import vo.AlunoVO;
import vo.LivroVO;

public class AluguelDAO implements IAluguelDAO {

    @Override
    public void cadastro(AluguelVO aluguelVO) throws SQLException {
        //IMPLEMENTADO SOMENTE PARA CORRIGIR O ERRO DE CLASSE ABSTRATA.
    }

    private Connection conexao;

    public Connection getConnection() {
        return conexao;
    }

    public void setConnection(Connection conexao) {
        this.conexao = conexao;
    }

    public boolean cadastrar(AluguelVO aluguel) {
        String sql = "INSERT INTO aluguel(data_aluguel, aluno_id, livro_id, data_devolucao, devolvido) VALUES(?,?,?,?,?)";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setDate(1, Date.valueOf(aluguel.getData_aluguel()));
            stmt.setInt(2, aluguel.getAluno().getId_aluno());
            stmt.setInt(3, aluguel.getLivro().getId_livro());
            stmt.setDate(3, Date.valueOf(aluguel.getData_devolucao()));
            stmt.setBoolean(4, aluguel.getDevolvido());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AluguelDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

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

    public boolean remover(AluguelVO aluguel) {
        String sql = "DELETE FROM aluguel WHERE Id_aluguel=?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, aluguel.getId_aluguel());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AluguelDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    //REVER
    public List<AluguelVO> listar() {
        String sql = "SELECT * FROM aluguel";
        List<AluguelVO> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                AluguelVO aluguel = new AluguelVO();
                LivroVO livro = new LivroVO();
                AlunoVO aluno = new AlunoVO();

                aluguel.setId_aluguel(resultado.getInt("id_aluguel"));
                aluguel.setData_aluguel(resultado.getDate("data_aluguel").toLocalDate());
                aluguel.setAluno_id(resultado.getInt("aluno_id"));
                aluguel.setLivro_id(resultado.getInt("livro_id"));
                aluguel.setData_devolucao(resultado.getDate("data_devolucao").toLocalDate());
                aluguel.setDevolvido(resultado.getBoolean("devolvido"));

                //Obtendo os dados completos do Livro associado
                LivroDAO livroDAO = new LivroDAO();
                livroDAO.setConnection(conexao);
                livro = livroDAO.buscar(livro);

                //Obtendo os dados completos do Aluno associado
                AlunoDAO alunoDAO = new AlunoDAO();
                alunoDAO.setConnection(conexao);
                aluno = alunoDAO.buscar(aluno);

                aluguel.setLivro(livro);
                aluguel.setAluno(aluno);
                retorno.add(aluguel);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AluguelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public AluguelVO buscarUltimoAluguel() {
        String sql = "SELECT max(Id_aluguel) FROM aluguel";
        AluguelVO retorno = new AluguelVO();
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();

            if (resultado.next()) {
                retorno.setId_aluguel(resultado.getInt("max"));
                return retorno;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AluguelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    //Map<Chave, Valor> No nosso caso : <Ano, arraylist de meses e a qtd de alugueis em cada mes>
    public Map<Integer, ArrayList> listarAlugueisPorMes() {
        //esta caindo no exception com o erro  Column 'count' not found. ??????
        String sql = "select count(Id_aluguel), extract(year from data_aluguel) as ano, extract(month from data_aluguel) as mes from aluguel group by ano, mes order by ano, mes";
        Map<Integer, ArrayList> retorno = new HashMap();

        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();

            while (resultado.next()) {
                ArrayList linha = new ArrayList();
                if (!retorno.containsKey(resultado.getInt("ano"))) {
                    linha.add(resultado.getInt("mes"));
                    linha.add(resultado.getInt("count"));
                    retorno.put(resultado.getInt("ano"), linha);
                } else {
                    ArrayList linhaNova = retorno.get(resultado.getInt("ano"));
                    linhaNova.add(resultado.getInt("mes"));
                    linhaNova.add(resultado.getInt("count"));
                }
            }
            return retorno;
        } catch (SQLException ex) {
            Logger.getLogger(AluguelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

}
