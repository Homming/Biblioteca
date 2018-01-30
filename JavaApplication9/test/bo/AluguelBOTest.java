package bo;

import dao.IAluguelDAO;
import java.time.LocalDate;
import junit.framework.TestCase;
import dao.AluguelDAOMock;
import database.Database;
import database.DatabaseFactory;
import java.sql.Connection;
import java.sql.SQLException;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;
import vo.AluguelVO;
import vo.AlunoVO;

public class AluguelBOTest extends TestCase {

    private AluguelVO aluguelVO;
    private IAluguelDAO aluguelDAOMock;
    private final Database database = DatabaseFactory.getDatabase("mysql");
    private final Connection connection = database.conectar();

    @Override
    protected void setUp() throws Exception {
        IAluguelDAO aluguelDAOMock = new AluguelDAOMock();
        LocalDate teste = LocalDate.now();
        AluguelVO aluguelVO = new AluguelVO(12345, teste, 12345, 12345, teste, false);
        AlunoVO aluno = new AlunoVO();
        aluno.setId_aluno(1);
        aluguelVO.setAluno(aluno);
        this.aluguelDAOMock = aluguelDAOMock;
        this.aluguelVO = aluguelVO;
        this.aluguelDAOMock.setConnection(connection);
    }

    public void testDeveriaCadatrarAluguel(){
           AluguelBO aluguelBO = new AluguelBO(this.aluguelDAOMock, this.aluguelVO);

        try {
            assertTrue(aluguelBO.CadastroAluguel());
        } catch (SQLException e) {
            fail("Deveria ter cadastrado");
        }
    }
    

    public void testDeveriaValidarIdAluno() {
        AluguelBO aluguelBO = new AluguelBO(this.aluguelDAOMock, this.aluguelVO);
        assertTrue(aluguelBO.inserindoAluno());
    }

    public void testeNaoDeveriaValidarIdAluno() {
        this.aluguelVO.setAluno_id(0);
        AluguelBO aluguelBO = new AluguelBO(this.aluguelDAOMock, this.aluguelVO);
        assertFalse(aluguelBO.inserindoAluno());
    }

   
}
