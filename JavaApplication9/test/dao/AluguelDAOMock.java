package dao;

import database.DatabaseMySQL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import vo.AluguelVO;

public class AluguelDAOMock implements IAluguelDAO {
    
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
    public boolean cadastrar(AluguelVO aluguel) throws SQLException {
       String sql = "INSERT INTO aluguel(data_aluguel, aluno_id, data_devolucao) VALUES(?,?,?)";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setDate(1, Date.valueOf(aluguel.getData_aluguel()));
            stmt.setInt(2, aluguel.getAluno().getId_aluno());
            stmt.setDate(3, Date.valueOf(aluguel.getData_devolucao()));
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AluguelDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean remover(AluguelVO aluguel) throws SQLException {
        String sql = "DELETE FROM aluguel WHERE Id_aluguel=?";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, aluguel.getId_aluguel());

            int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza de que deseja excluir o registro do aluguel?", "Atenção",
                    +JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);

            if (confirma == JOptionPane.YES_NO_OPTION) {
                stmt.execute();
                JOptionPane.showMessageDialog(null, "Registro excluído com sucesso!");

            }

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(AluguelDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        
    }
    
   

}
