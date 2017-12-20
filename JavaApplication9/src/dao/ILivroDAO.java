package dao;

import java.sql.SQLException;
import vo.LivroVO;
        
public interface ILivroDAO {
    public void cadastrar(LivroVO livroVO) throws SQLException;
    public void excluir(LivroVO livroVO) throws SQLException;
    public void alterar(LivroVO livroVO) throws SQLException;
   }
