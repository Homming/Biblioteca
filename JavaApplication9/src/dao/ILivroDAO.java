package dao;

import java.sql.SQLException;
import vo.LivroVO;
        
public interface ILivroDAO {
    public void cadastrar(LivroVO livroVO) throws SQLException;
    public void excluirCad(LivroVO livroVO) throws SQLException;
    public void editarCad(LivroVO livroVO) throws SQLException;
   }
