package dao;

import database.DatabaseMySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import vo.AlunoVO;

public class AlunoDAOMock implements IAlunoDAO {

    Connection conexao;

    public Connection getConnection() {
        return conexao;
    }

    public void setConnection(Connection conexao) {
        this.conexao = conexao;
    }

    protected void setUp() throws Exception {//EFETUADO ANTES DE CADA TESTE REALIZADO
        DatabaseMySQL mysql = new DatabaseMySQL();
        this.conexao = mysql.conectar();
        this.conexao.setAutoCommit(false);
    }

    protected void tearDown() throws Exception {//EFETUADO DEPOIS DE CADA TESTE REALIZADO
        this.conexao.setAutoCommit(true);
        this.conexao.close();
    }

    @Override
    public boolean cadastrar(AlunoVO aluno) throws SQLException {
        String sql = "INSERT INTO aluno(nome, telefone, email, complemento, matricula, turma) values (?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getTelefone());
            stmt.setString(3, aluno.getEmail());
            stmt.setString(4, aluno.getComplemento());
            stmt.setString(5, aluno.getMatricula());
            stmt.setString(6, aluno.getTurma());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AlunoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean editar(AlunoVO aluno) throws SQLException {
        cadastrar(aluno);
        aluno.setMatricula("2025.2TST");
        try {
            String editaSQL = ("UPDATE aluno SET nome = ?, telefone = ?, email = ?, complemento = ?, matricula = ?, turma = ? WHERE id_aluno = ?");

            PreparedStatement pstm = conexao.prepareStatement(editaSQL);

            pstm.setString(1, aluno.getNome());
            pstm.setString(2, aluno.getTelefone());
            pstm.setString(3, aluno.getEmail());
            pstm.setString(4, aluno.getComplemento());
            pstm.setString(5, aluno.getMatricula());
            pstm.setString(6, aluno.getTurma());
            pstm.setInt(7, aluno.getId_aluno());

            pstm.execute();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na edição!" + ex);
            return false;
        }// fim try
        return true;
    }

    @Override
    public boolean remover(AlunoVO aluno) throws SQLException {
        cadastrar(aluno);
        try {
            String excluiSQL = ("DELETE FROM aluno where Id_aluno = ?");
            PreparedStatement pstm = conexao.prepareStatement(excluiSQL);
            pstm.setInt(1, aluno.getId_aluno());

            pstm.execute();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na exclusão do aluno!" /*+ ex*/);
            return false;
        }
        return true;
    }

}
