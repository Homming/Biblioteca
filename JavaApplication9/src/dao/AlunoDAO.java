package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vo.AlunoVO;
import vo.LivroVO;

public class AlunoDAO implements IAlunoDAO{

    @Override
    public void cadastro(AlunoVO alunoVO) {
        //Implementado para corrigir o erro de classe abstrata
    }  
    
    private ArrayList<AlunoVO> alunos;
    private Connection conexao;
    
    public Connection getConnection() {
        return conexao;
    }

    public void setConnection(Connection conexao) {
        this.conexao = conexao;
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
    /*
    public AlunoVO buscar(AlunoVO aluno) {
        String sql = "SELECT * FROM aluno WHERE Id_aluno=?";
        AlunoVO retorno = new AlunoVO();
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, aluno.getId_aluno());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                aluno.setId_aluno(resultado.getInt("id_aluno"));
                aluno.setNome(resultado.getString("nome"));
                aluno.setTelefone(resultado.getString("telefone"));
                retorno = aluno;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LivroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    */
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
