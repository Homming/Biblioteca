package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import javax.swing.JOptionPane;
import vo.LivroVO;

public class LivroDAO implements ILivroDAO {
    
   
    @Override
    public void cadastro(LivroVO livroVO) {
        //IMPLEMENTADO SOMENTE PARA CORRIGIR O ERRO DE CLASSE ABSTRATA.
    }
    
    private ArrayList<LivroVO> livros;
    private Connection conexao;
    
    public LivroDAO(Connection conexao){
        this.conexao = conexao;
    }
    
    /*
    public void cadastrar(LivroVO livro) throws SQLException{
        Statement stmt = this.conexao.createStatement();
        stmt.executeUpdate("INSERT INTO livro(titulo, data_livro, cdd, cutter, complemento, autor1, assunto, autor2, local_livro, autor3, editora, tradutores, ano, ilustradores, edicao) VALUES "
                + "('"+livro.getTitulo()+"','"+livro.getData()+"','"+livro.getCdd()+"','"+livro.getCutter()+"','"+livro.getComplemento()+"','"+livro.getAutor1()+"','"+livro.getAutor2()+"','"+livro.getAutor3()+"','"+livro.getTradutores()+"','"+livro.getIlustradores()+"','"+livro.getAssunto()+"','"+livro.getLocal()+"','"+livro.getEditora()+"','"+livro.getAno()+"','"+livro.getEdicao()+"')");
        
    }*/
    
    public void cadastrar(LivroVO cad){
        try{
            String insertSQL =("INSERT INTO livro(titulo, data_livro, cdd, cutter, complemento, autor1, assunto, autor2, local_livro, autor3, editora, tradutores, ano, ilustradores, edicao) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            
          PreparedStatement pstm = conexao.prepareStatement(insertSQL);
          
          pstm.setString(1, cad.getTitulo());
          pstm.setString(2, cad.getData());
          pstm.setString(3, cad.getCdd());
          pstm.setString(4, cad.getCutter());
          pstm.setString(5, cad.getComplemento());
          pstm.setString(6, cad.getAutor1());
          pstm.setString(7, cad.getAssunto());
          pstm.setString(8, cad.getAutor2());
          pstm.setString(9, cad.getLocal());
          pstm.setString(10, cad.getAutor3());
          pstm.setString(11, cad.getEditora());
          pstm.setString(12, cad.getTradutores());
          pstm.setString(13, cad.getAno());
          pstm.setString(14, cad.getIlustradores());
          pstm.setString(15, cad.getEdicao());
          
          pstm.execute();
          
          pstm.close();
        
        }catch (Exception e){
        JOptionPane.showMessageDialog(null, "Erro na inserção: " + e);
        }
    }
    
    public void editarCad(LivroVO cad){
         try{
             String editaSQL = ("UPDATE livro SET titulo = ?, data_livro = ?, cdd = ?, cutter = ?, complemento = ?, autor1 = ?, assunto = ?, autor2 = ?, local_livro = ?, autor3 = ?, editora = ?, tradutores = ?, ano = ?, ilustradores = ?, edicao = ? WHERE id_livro = ?");
             
             PreparedStatement pstm = conexao.prepareStatement(editaSQL);
          
          pstm.setString(1, cad.getTitulo());
          pstm.setString(2, cad.getData());
          pstm.setString(3, cad.getCdd());
          pstm.setString(4, cad.getCutter());
          pstm.setString(5, cad.getComplemento());
          pstm.setString(6, cad.getAutor1());
          pstm.setString(7, cad.getAssunto());
          pstm.setString(8, cad.getAutor2());
          pstm.setString(9, cad.getLocal());
          pstm.setString(10, cad.getAutor3());
          pstm.setString(11, cad.getEditora());
          pstm.setString(12, cad.getTradutores());
          pstm.setString(13, cad.getAno());
          pstm.setString(14, cad.getIlustradores());
          pstm.setString(15, cad.getEdicao());
          
          int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza de que deseja modificar as informações", "Atenção", +JOptionPane.YES_NO_OPTION);
          
          if(confirma == JOptionPane.YES_NO_OPTION){
              pstm.execute();
              JOptionPane.showMessageDialog(null, "Livro editado com sucesso!");
          }
         }catch (SQLException ex){
             JOptionPane.showMessageDialog(null, "Erro na edição!");
         }
     }//fim editar

     public void excluirCad(LivroVO cad) {

        try {
            String excluiSQL = ("DELETE FROM livros WHERE Id_livro = ?");
            PreparedStatement pstm = conexao.prepareStatement(excluiSQL);
            pstm.setInt(1, cad.getId_livro());

            int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza de que deseja excluir o livro?", "Atenção",
                    +JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

            if (confirma == JOptionPane.YES_NO_OPTION) {
                pstm.execute();
                JOptionPane.showMessageDialog(null, "Livro excluído com sucesso!");

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na exclusão do livro!" + ex);

        }
    }
    
    /*
    public ResultSet consultarLivroPorTitulo(String titulo) throws SQLException{
        Statement stmt = this.conexao.createStatement();
        String sqlQuery = "SELECT * FROM Livro WHERE titulo = '"+titulo+"'";
        
        ResultSet rs = stmt.executeQuery(sqlQuery);
        
        return rs;
    }
    */
    

   
    
}
