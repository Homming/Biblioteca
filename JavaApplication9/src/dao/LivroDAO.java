package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import vo.LivroVO;

public class LivroDAO implements ILivroDAO {

    private ArrayList<LivroVO> livros;
    private Connection conexao;

    public Connection getConnection() {
        return conexao;
    }

    public void setConnection(Connection conexao) {
        this.conexao = conexao;
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

    @Override
    public boolean editarCad(LivroVO cad) {
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

            /*
            int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza de que deseja modificar as informações", "Atenção", +JOptionPane.YES_NO_OPTION);

            if (confirma == JOptionPane.YES_NO_OPTION) {
                pstm.execute();
                JOptionPane.showMessageDialog(null, "Livro editado com sucesso!");
            }
             */
            return true;
        } catch (SQLException ex) {
            //JOptionPane.showMessageDialog(null, "Erro na edição!");
            Logger.getLogger(LivroDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean excluirCad(LivroVO cad) {
        try {
            String excluiSQL = ("DELETE FROM livro WHERE Id_livro = ?");
            PreparedStatement pstm = conexao.prepareStatement(excluiSQL);
            pstm.setInt(1, cad.getId_livro());

            int confirma = JOptionPane.showConfirmDialog(null, "OBS: AO DELETAR O LIVRO TODOS OS ALUGUÉIS RELACIONADOS AO LIVRO SERÃO REMOVIDOS, Tem certeza de que deseja excluir o livro?", "Atenção",
                    +JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

            if (confirma == JOptionPane.YES_NO_OPTION) {
                pstm.execute();
                JOptionPane.showMessageDialog(null, "Livro excluído com sucesso!");

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na exclusão do livro!" + ex);
            return false;
        }
        return true;
    }

    public List<LivroVO> listar() {
        String sql = "SELECT * FROM livro";
        List<LivroVO> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                LivroVO livro = new LivroVO();
                livro.setId_livro(resultado.getInt("id_livro"));
                livro.setTitulo(resultado.getString("titulo"));
                livro.setData_livro(resultado.getDate("data_livro").toLocalDate());
                livro.setQuantidade_livro(resultado.getInt("quantidade_livro"));
                livro.setCdd(resultado.getString("cdd"));
                livro.setCutter(resultado.getString("cutter"));
                livro.setComplemento(resultado.getString("complemento"));
                livro.setAutor1(resultado.getString("autor1"));
                livro.setAutor2(resultado.getString("autor2"));
                livro.setAutor3(resultado.getString("autor3"));
                livro.setTradutores(resultado.getString("tradutores"));
                livro.setIlustradores(resultado.getString("ilustradores"));
                livro.setAssunto(resultado.getString("assunto"));
                livro.setLocal(resultado.getString("local_livro"));
                livro.setEditora(resultado.getString("editora"));
                livro.setAno(resultado.getString("ano"));
                livro.setEdicao(resultado.getString("edicao"));

                retorno.add(livro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LivroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

    /*
    public ResultSet consultarLivroPorTitulo(String titulo) throws SQLException{
        Statement stmt = this.conexao.createStatement();
        String sqlQuery = "SELECT * FROM Livro WHERE titulo = '"+titulo+"'";
        
        ResultSet rs = stmt.executeQuery(sqlQuery);
        
        return rs;
    }
     */
    public LivroVO buscar(LivroVO livro) {
        String sql = "SELECT * FROM livro WHERE Id_livro=?";
        LivroVO retorno = new LivroVO();
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, livro.getId_livro());
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                livro.setId_livro(resultado.getInt("id_livro"));
                livro.setTitulo(resultado.getString("titulo"));
                livro.setData_livro(resultado.getDate("data_livro").toLocalDate());
                livro.setCdd(resultado.getString("cdd"));
                livro.setCutter(resultado.getString("cutter"));
                livro.setComplemento(resultado.getString("complemento"));
                livro.setAutor1(resultado.getString("autor1"));
                livro.setAutor2(resultado.getString("autor2"));
                livro.setAutor3(resultado.getString("autor3"));
                livro.setTradutores(resultado.getString("tradutores"));
                livro.setIlustradores(resultado.getString("ilustradores"));
                livro.setAssunto(resultado.getString("assunto"));
                livro.setLocal(resultado.getString("local_livro"));
                livro.setEditora(resultado.getString("editora"));
                livro.setAno(resultado.getString("ano"));
                livro.setEdicao(resultado.getString("edicao"));
                retorno = livro;
            }
        } catch (SQLException ex) {
            Logger.getLogger(LivroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

}// FIM DA CLASSE
