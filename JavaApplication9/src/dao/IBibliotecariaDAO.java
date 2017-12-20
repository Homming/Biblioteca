package dao;
import java.sql.SQLException;
import vo.BibliotecariaVO;

public interface IBibliotecariaDAO {
    public void cadastrar(BibliotecariaVO bibliVO) throws SQLException;
    public void excluir(BibliotecariaVO bibliVO) throws SQLException;
    public void alterar(BibliotecariaVO bibliVO) throws SQLException;
}
