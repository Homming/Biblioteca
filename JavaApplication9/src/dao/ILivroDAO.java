package dao;
import java.sql.SQLException;
import vo.LivroVO;
        
public interface ILivroDAO {
    public void cadastro(LivroVO livroVO) throws SQLException;
    
}
