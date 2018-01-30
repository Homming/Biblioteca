package dao;

import database.DatabaseMySQL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    public boolean cadastrar(AluguelVO aluguelVO) throws SQLException {
        return false;
        
    }

    @Override
    public boolean remover(AluguelVO aluguelVO) throws SQLException {
        return false;
        
    }
    
    @Override
    public AluguelVO buscarAluguel(AluguelVO aluguel) {
        String sql = "SELECT * FROM aluguel where id_aluguel = ?";
        AluguelVO retorno = new AluguelVO();
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, aluguel.getId_aluguel());

            ResultSet resultado = stmt.executeQuery();

            if (resultado.next()) {
                retorno.setId_aluguel(resultado.getInt("id_aluguel"));
                return retorno;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AluguelDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

}
