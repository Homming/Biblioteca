package dao;

import java.sql.Connection;
import java.sql.SQLException;
import vo.BibliotecariaVO;

public interface IBibliotecariaDAO {
    public boolean cadastrar(BibliotecariaVO bibliotecariaVO) throws SQLException;
    public boolean editar(BibliotecariaVO bibliotecariaVO) throws SQLException;
    public boolean excluirCad(BibliotecariaVO bibliotecariaVO) throws SQLException;
    public Connection getConnection();
    public void setConnection(Connection connection);
    
}
