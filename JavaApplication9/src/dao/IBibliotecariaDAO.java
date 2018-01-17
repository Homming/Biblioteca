package dao;
import java.sql.SQLException;
import vo.BibliotecariaVO;

public interface IBibliotecariaDAO {
    public void cadastrar(BibliotecariaVO bibliVO) throws SQLException;
    public void excluirCad(BibliotecariaVO bibliVO) throws SQLException;
    public void editar(BibliotecariaVO bibliVO) throws SQLException;
}
