package dao;

import java.sql.SQLException;
import vo.AluguelVO;

public interface IAluguelDAO {
    public void cadastro(AluguelVO aluguelVO) throws SQLException;
    
}
