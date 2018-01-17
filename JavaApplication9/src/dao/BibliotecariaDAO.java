package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import vo.BibliotecariaVO;

public class BibliotecariaDAO implements IBibliotecariaDAO {
    
    private ArrayList<BibliotecariaVO> biblio;
    private Connection conexao;
    
    public BibliotecariaDAO(Connection conexao){
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
        
        }catch (Exception e){
        JOptionPane.showMessageDialog(null, "Erro na inserção: " + e);
        }// fim try
    }// fim do cadastrar
    
    public void editar(BibliotecariaVO cad){
         try{
             String editaSQL = ("UPDATE usuario SET nome = ?, cpf = ?, cel = ?, usuario = ?, senha = ?, email = ? WHERE id_usuario = ?");
             
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
    
    @Override
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
    
}
