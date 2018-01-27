package dao;

import java.sql.Connection;
import java.sql.SQLException;
import vo.LivroVO;
        
public interface ILivroDAO {
    public boolean cadastrar(LivroVO livroVO) throws SQLException;
    public boolean editarCad(LivroVO livroVO) throws SQLException;
    public boolean excluirCad(LivroVO livroVO) throws SQLException;
    public Connection getConnection();
    public void setConnection(Connection conexao);
       
    
   }

