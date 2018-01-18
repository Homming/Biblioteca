package dao;

import java.sql.SQLException;
import vo.AlunoVO;

public interface IAlunoDAO {
    public void cadastro(AlunoVO alunoVO) throws SQLException;
    
}
