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
import vo.BibliotecariaVO;

public class BibliotecariaDAO implements IBibliotecariaDAO {
    
    private ArrayList<BibliotecariaVO> biblio;
    private Connection conexao;
    
    public Connection getConnection() {
        return conexao;
    }

    public void setConnection(Connection conexao) {
        this.conexao = conexao;
    }
    
    public void cadastrar(BibliotecariaVO cad){
        
        try{
            String insertSQL =("INSERT INTO bibliotecaria(nome, cpf, cel, usuario, senha, email) values (?,?,?,?,?,?)");
            
          PreparedStatement pstm = conexao.prepareStatement(insertSQL);
          
          pstm.setString(1, cad.getNome());
          pstm.setString(2, cad.getCpf());
          pstm.setString(3, cad.getCel());
          pstm.setString(4, cad.getUsuario());
          pstm.setString(5, cad.getSenha());
          pstm.setString(6, cad.getEmail());
          
          pstm.execute();
          
          pstm.close();
        
        }catch (SQLException e){
        JOptionPane.showMessageDialog(null, "Erro na inserção: " + e);
        }// fim try
    }// fim do cadastrar
    
    public void editar(BibliotecariaVO cad){
         try{
             String editaSQL = ("UPDATE bibliotecaria SET nome = ?, cpf = ?, cel = ?, usuario = ?, senha = ?, email = ? WHERE id_usuario = ?");
             
             PreparedStatement pstm = conexao.prepareStatement(editaSQL);
          
          pstm.setString(1, cad.getNome());
          pstm.setString(2, cad.getCpf());
          pstm.setString(3, cad.getCel());
          pstm.setString(4, cad.getUsuario());
          pstm.setString(5, cad.getSenha());
          pstm.setString(6, cad.getEmail());
          pstm.setInt(7, cad.getId_usuario());
          
          int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza de que deseja modificar as informações", "Atenção", +JOptionPane.YES_NO_OPTION);
          
          if(confirma == JOptionPane.YES_NO_OPTION){
              pstm.execute();
              JOptionPane.showMessageDialog(null, "Usuário editado com sucesso!");
          }
         }catch (SQLException ex){
             JOptionPane.showMessageDialog(null, "Erro na edição!");
         }// fim try
     }//fim editar
    
    public void excluirCad(BibliotecariaVO cad) {

        try {
            String excluiSQL = ("DELETE FROM bibliotecaria where Id_usuario = ?");
            PreparedStatement pstm = conexao.prepareStatement(excluiSQL);
            pstm.setInt(1, cad.getId_usuario());

            int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza de que deseja excluir o usuário?", "Atenção",
                    +JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

            if (confirma == JOptionPane.YES_NO_OPTION) {
                pstm.execute();
                JOptionPane.showMessageDialog(null, "Usuário excluído com sucesso!");

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na exclusão do usuário!" + ex);

        }
    }
    
    public List<BibliotecariaVO> listar() {
        String sql = "SELECT * FROM bibliotecaria";
        List<BibliotecariaVO> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                BibliotecariaVO bibliotecaria = new BibliotecariaVO();
                bibliotecaria.setId_usuario(resultado.getInt("id_usuario"));
                bibliotecaria.setNome(resultado.getString("nome"));
                bibliotecaria.setCpf(resultado.getString("cpf"));
                bibliotecaria.setCel(resultado.getString("cel"));
                bibliotecaria.setUsuario(resultado.getString("usuario"));
                bibliotecaria.setEmail(resultado.getString("email"));
                retorno.add(bibliotecaria);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BibliotecariaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }
    
}
