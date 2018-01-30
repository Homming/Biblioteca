package dao;

import java.sql.Connection;
import java.sql.SQLException;
import vo.AlunoVO;

public interface IAlunoDAO {
    public boolean cadastrar(AlunoVO alunoVO) throws SQLException;
    public boolean editar(AlunoVO alunoVO) throws SQLException;
    public boolean remover(AlunoVO alunoVO) throws SQLException;
    public Connection getConnection();
    public void setConnection(Connection connection);
    
}
