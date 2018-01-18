package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import vo.AlunoVO;

public class AlunoDAO implements IAlunoDAO {

    private ArrayList<AlunoVO> alunos;
    private Connection conexao;

    public Connection getConnection() {
        return conexao;
    }

    public void setConnection(Connection conexao) {
        this.conexao = conexao;
    }

    @Override
    public boolean cadastrar(AlunoVO aluno) throws SQLException {
        String sql = "INSERT INTO aluno(nome, quantidade_alocados, qtd_maxlivro, telefone, email, complemento, matricula, turma) values (?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, aluno.getNome());
            stmt.setInt(2, aluno.getQuantidade_alocados());
            stmt.setInt(3, aluno.getQtd_maxlivro());
            stmt.setString(4, aluno.getTelefone());
            stmt.setString(5, aluno.getEmail());
            stmt.setString(6, aluno.getComplemento());
            stmt.setString(7, aluno.getMatricula());
            stmt.setString(8, aluno.getTurma());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean editar(AlunoVO aluno) throws SQLException {
        try {
            String editaSQL = ("UPDATE aluno SET nome = ?, quantidade_alocados = ?, qtd_maxlivro = ?, telefone = ?, email = ?, complemento = ?, matricula = ?, turma = ? WHERE id_aluno = ?");

            PreparedStatement pstm = conexao.prepareStatement(editaSQL);

            pstm.setString(1, aluno.getNome());
            pstm.setInt(2, aluno.getQuantidade_alocados());
            pstm.setInt(3, aluno.getQtd_maxlivro());
            pstm.setString(4, aluno.getTelefone());
            pstm.setString(5, aluno.getEmail());
            pstm.setString(6, aluno.getComplemento());
            pstm.setString(7, aluno.getMatricula());
            pstm.setString(8, aluno.getTurma());
            pstm.setInt(9, aluno.getId_aluno());

            int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza de que deseja modificar as informações", "Atenção", +JOptionPane.YES_NO_OPTION);

            if (confirma == JOptionPane.YES_NO_OPTION) {
                pstm.execute();
                JOptionPane.showMessageDialog(null, "Aluno editado com sucesso!");

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na edição!");
            return false;
        }// fim try
        return true;
    }

    @Override
    public boolean remover(AlunoVO aluno) throws SQLException {
        try {
            String excluiSQL = ("DELETE FROM aluno where Id_aluno = ?");
            PreparedStatement pstm = conexao.prepareStatement(excluiSQL);
            pstm.setInt(1, aluno.getId_aluno());

            int confirma = JOptionPane.showConfirmDialog(null, "OBS: AO DELETAR O ALUNO TODOS OS ALUGUÉIS RELACIONADOS AO ALUNO SERÃO REMOVIDOS, Tem certeza de que deseja excluir o aluno?", "Atenção",
                    +JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

            if (confirma == JOptionPane.YES_NO_OPTION) {
                pstm.execute();
                JOptionPane.showMessageDialog(null, "Aluno excluído com sucesso!");

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na exclusão do aluno!" + ex);
            return false;
        }
        return true;
    }

    public List<AlunoVO> listar() {
        String sql = "SELECT * FROM aluno";
        List<AlunoVO> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                AlunoVO aluno = new AlunoVO();
                aluno.setId_aluno(resultado.getInt("id_aluno"));
                aluno.setNome(resultado.getString("nome"));
                aluno.setQuantidade_alocados(resultado.getInt("quantidade_alocados"));
                aluno.setQtd_maxlivro(resultado.getInt("qtd_maxlivro"));
                aluno.setTelefone(resultado.getString("telefone"));
                aluno.setEmail(resultado.getString("email"));
                aluno.setComplemento(resultado.getString("complemento"));
                aluno.setMatricula(resultado.getString("matricula"));
                aluno.setTurma(resultado.getString("turma"));

                retorno.add(aluno);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LivroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    public AlunoVO buscar(AlunoVO aluno) {
        String sql = "SELECT * FROM aluno WHERE id_aluno=?";
        AlunoVO retorno = new AlunoVO();
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, aluno.getId_aluno());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                aluno.setNome(resultado.getString("nome"));
                aluno.setQuantidade_alocados(resultado.getInt("quantidade_alocados"));
                aluno.setQtd_maxlivro(resultado.getInt("qtd_maxlivro"));
                aluno.setTelefone(resultado.getString("telefone"));
                aluno.setEmail(resultado.getString("email"));
                aluno.setComplemento(resultado.getString("complemento"));
                aluno.setMatricula(resultado.getString("matricula"));
                aluno.setTurma(resultado.getString("turma"));

                retorno = aluno;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

}
