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
import vo.LivroVO;

public class LivroDAO implements ILivroDAO {
    
   
    @Override
    public void cadastro(LivroVO livroVO) {
        //IMPLEMENTADO SOMENTE PARA CORRIGIR O ERRO DE CLASSE ABSTRATA.
    }
    
    private ArrayList<LivroVO> livros;
    private Connection conexao;
    
    public Connection getConnection() {
        return conexao;
    }

    public void setConnection(Connection conexao) {
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
          pstm.setString(2, cad.getData()); //ps.setDate(2, new Date(f.getDtanascimetno().getDate()));
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
                pstm.setString(7, cad.getAutor2());
                pstm.setString(8, cad.getAutor3());
                pstm.setString(9, cad.getTradutores());
                pstm.setString(10, cad.getIlustradores());
                pstm.setString(11, cad.getAssunto());  
                pstm.setString(12, cad.getLocal());
                pstm.setString(13, cad.getEditora());
                pstm.setString(14, cad.getAno());
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
            String excluiSQL = ("DELETE FROM livro WHERE Id_livro = ?");
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
                livro.setData(resultado.getString("data_livro"));
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
            Logger.getLogger(BibliotecariaDAO.class.getName()).log(Level.SEVERE, null, ex);
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
    

   
    
}
