package dao;

import java.sql.SQLException;
import vo.ItemDeAluguelVO;

public interface IItemDeAluguelDAO {
    public boolean inserir(ItemDeAluguelVO itemDeAluguelVO) throws SQLException;
    public boolean alterar(ItemDeAluguelVO itemDeAluguelVO) throws SQLException;
    public boolean remover(ItemDeAluguelVO itemDeAluguelVO) throws SQLException;
}
