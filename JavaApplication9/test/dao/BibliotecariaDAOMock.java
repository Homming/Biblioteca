package dao;

import database.DatabaseMySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import vo.BibliotecariaVO;

public class BibliotecariaDAOMock implements IBibliotecariaDAO {
    
     private Connection conexao;
    ResultSet rs = null;

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
    public boolean cadastrar(BibliotecariaVO bibliotecaria) throws SQLException {
   String sql = "INSERT INTO bibliotecaria(nome, cpf, cel, usuario, senha, email) values (?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, bibliotecaria.getNome());
            stmt.setString(2, bibliotecaria.getCpf());
            stmt.setString(3, bibliotecaria.getCel());
            stmt.setString(4, bibliotecaria.getUsuario());
            stmt.setString(5, bibliotecaria.getSenha());
            stmt.setString(6, bibliotecaria.getEmail());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(BibliotecariaDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean editar(BibliotecariaVO cad) throws SQLException {
        cadastrar(cad);
        cad.setNome("Santana do Ipanema Teste");
        try {
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

            if (confirma == JOptionPane.YES_NO_OPTION) {
                pstm.execute();
                JOptionPane.showMessageDialog(null, "Usuário editado com sucesso!");

            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na edição!");
            return false;
        }// fim try
        return true;
    }

    @Override
    public boolean excluirCad(BibliotecariaVO cad) throws SQLException {
        cadastrar(cad);
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
            return false;
        }
        return true;
    }

}
