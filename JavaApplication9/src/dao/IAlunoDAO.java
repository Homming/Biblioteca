package dao;
import java.sql.SQLException;
import vo.AlunoVO;

public interface IAlunoDAO {
    public void cadastrar(AlunoVO alunoVO) throws SQLException;
    public void excluir(AlunoVO alunoVO) throws SQLException;
    public void alterar(AlunoVO alunoVO) throws SQLException;
}
