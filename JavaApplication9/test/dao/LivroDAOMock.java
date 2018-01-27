package dao;

import database.DatabaseMySQL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import vo.LivroVO;

public class LivroDAOMock implements ILivroDAO {

    LivroVO livro;
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
    public boolean cadastrar(LivroVO cad) throws SQLException {
        String sql = "INSERT INTO livro(titulo, data_livro, quantidade_livro, cdd, cutter, complemento, autor1, autor2, autor3, tradutores, ilustradores, assunto, local_livro, editora, ano, edicao) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pstm = conexao.prepareStatement(sql);
            pstm.setString(1, cad.getTitulo());
            pstm.setDate(2, Date.valueOf(cad.getData_livro()));
            pstm.setInt(3, cad.getQuantidade_livro());
            pstm.setString(4, cad.getCdd());
            pstm.setString(5, cad.getCutter());
            pstm.setString(6, cad.getComplemento());
            pstm.setString(7, cad.getAutor1());
            pstm.setString(8, cad.getAssunto());
            pstm.setString(9, cad.getAutor2());
            pstm.setString(10, cad.getLocal());
            pstm.setString(11, cad.getAutor3());
            pstm.setString(12, cad.getEditora());
            pstm.setString(13, cad.getTradutores());
            pstm.setString(14, cad.getAno());
            pstm.setString(15, cad.getIlustradores());
            pstm.setString(16, cad.getEdicao());
            pstm.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(LivroDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    /*
    @Override
    public boolean editarCad(LivroVO cad) throws SQLException {
        String sql = "UPDATE livro SET titulo = ?, data_livro = ?, quantidade_livro = ?, cdd = ?, cutter = ?, complemento = ?, autor1 = ?, autor2 = ?, autor3 = ?, tradutores = ?, ilustradores = ?, assunto = ?, local_livro = ?, editora = ?, ano = ?, edicao = ? WHERE id_livro = ?";
        try {
            PreparedStatement pstm = conexao.prepareStatement(sql);
            pstm.setString(1, cad.getTitulo());
            pstm.setDate(2, Date.valueOf(cad.getData_livro()));
            pstm.setInt(3, cad.getQuantidade_livro());
            pstm.setString(4, cad.getCdd());
            pstm.setString(5, cad.getCutter());
            pstm.setString(6, cad.getComplemento());
            pstm.setString(7, cad.getAutor1());
            pstm.setString(8, cad.getAutor2());
            pstm.setString(9, cad.getAutor3());
            pstm.setString(10, cad.getTradutores());
            pstm.setString(11, cad.getIlustradores());
            pstm.setString(12, cad.getAssunto());
            pstm.setString(13, cad.getLocal());
            pstm.setString(14, cad.getEditora());
            pstm.setString(15, cad.getAno());
            pstm.setString(16, cad.getEdicao());
            pstm.setInt(17, cad.getId_livro());

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(LivroDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean excluirCad(LivroVO cad) throws SQLException {
        try {
            String excluiSQL = ("DELETE FROM livro WHERE Id_livro = ?");
            PreparedStatement pstm = conexao.prepareStatement(excluiSQL);
            pstm.setInt(1, cad.getId_livro());

        } catch (SQLException ex) {
            return false;
        }
        return true;
    }
     */
    @Override
    public boolean editarCad(LivroVO livroVO) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean excluirCad(LivroVO livroVO) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
