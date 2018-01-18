package dao;

import java.sql.SQLException;
import vo.AluguelVO;

public interface IAluguelDAO {
    public boolean cadastrar(AluguelVO aluguelVO) throws SQLException;
    
}
