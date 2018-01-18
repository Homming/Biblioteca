package dao;

import java.sql.SQLException;
import vo.LivroVO;
        
public interface ILivroDAO {
    public boolean cadastrar(LivroVO livroVO) throws SQLException;
    
   }

