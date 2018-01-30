package bo;

import dao.IAlunoDAO;
import junit.framework.TestCase;
import dao.AlunoDAOMock;
import database.Database;
import database.DatabaseFactory;
import java.sql.Connection;
import java.sql.SQLException;
import vo.AlunoVO;

public class AlunoBOTest extends TestCase {

    private AlunoVO alunoVO;
    private IAlunoDAO alunoDAOMock;
    private final Database database = DatabaseFactory.getDatabase("mysql");
    private final Connection connection = database.conectar();

    @Override
    protected void setUp() throws Exception {
        IAlunoDAO alunoDAOMock = new AlunoDAOMock();
        this.connection.setAutoCommit(false);
        AlunoVO alunoVO = new AlunoVO();
        alunoVO.setNome("Dereguedeishon santos da silva");
        alunoVO.setTelefone("996222428");
        alunoVO.setComplemento("Rua do petardo, xesquedele, 25");
        alunoVO.setQtd_maxlivro(3);
        alunoVO.setMatricula("Deish2017.5");
        alunoVO.setQuantidade_alocados(2);

        this.alunoVO = alunoVO;
        this.alunoDAOMock = alunoDAOMock;
        this.alunoDAOMock.setConnection(connection);

    }

    public void testDeveriaCadastrarAluno() throws Exception {
        AlunoBO alunoBO = new AlunoBO(this.alunoDAOMock, this.alunoVO);

        try {
            assertTrue(alunoBO.cadastrarAluno());
        } catch (SQLException e) {
            fail("Deveria ter cadastrado");
        }
    }

    public void testDeveriaEditarAluno() {
        AlunoBO alunoBO = new AlunoBO(this.alunoDAOMock, this.alunoVO);

        try {
            assertTrue(alunoBO.editarAluno());
        } catch (SQLException e) {
            fail("Deveria ter cadastrado");
        }
    }

    public void testDeveriaExcluirAluno() {
        AlunoBO alunoBO = new AlunoBO(this.alunoDAOMock, this.alunoVO);

        try {
            assertTrue(alunoBO.removerAluno());
        } catch (SQLException e) {
            fail("Deveria ter cadastrado");
        }
    }

    public void testDeveriaValidarNomeAluno() {
        AlunoBO alunoBO = new AlunoBO(this.alunoDAOMock, this.alunoVO);
        assertTrue(alunoBO.validarCadastroDeNome());
    }

    public void testNaoDeveriaValidarNomeAluno() {
        this.alunoVO.setNome("pedro");
        AlunoBO alunoBO = new AlunoBO(this.alunoDAOMock, this.alunoVO);
        assertFalse(alunoBO.validarCadastroDeNome());
    }

    public void testDeveriaValidarTelefoneAluno() {
        AlunoBO alunoBO = new AlunoBO(this.alunoDAOMock, this.alunoVO);
        assertTrue(alunoBO.validarCadastroDeTelefone());
    }

    public void testNaoDeveriaValidarTelefoneAluno() {
        this.alunoVO.setTelefone("895437");
        AlunoBO alunoBO = new AlunoBO(this.alunoDAOMock, this.alunoVO);
        assertFalse(alunoBO.validarCadastroDeTelefone());
    }

    public void testeDeveriaValidarComplementoAluno() {
        AlunoBO alunoBO = new AlunoBO(this.alunoDAOMock, this.alunoVO);
        assertTrue(alunoBO.validarCadastroDeComplemento());
    }

    public void testNaoDeveriaValidarComplementoAluno() {
        this.alunoVO.setComplemento("nao sei");
        AlunoBO alunoBO = new AlunoBO(this.alunoDAOMock, this.alunoVO);
        assertFalse(alunoBO.validarCadastroDeComplemento());
    }

    public void testeDeveriaValidarMatriculaAluno() {
        AlunoBO alunoBO = new AlunoBO(this.alunoDAOMock, this.alunoVO);
        assertTrue(alunoBO.validarCadastroDeMatricula());
    }

    public void testNaoDeveriaValidarMatriculaAluno() {
        this.alunoVO.setMatricula("");
        AlunoBO alunoBO = new AlunoBO(this.alunoDAOMock, this.alunoVO);
        assertFalse(alunoBO.validarCadastroDeMatricula());
    }

    /*
    public void testDeveriaValidarAlocacaoLivro() {
        AlunoBO alunoBO = new AlunoBO(this.alunoDAOMock, this.alunoVO);
        assertTrue(alunoBO.validarAlocaçãoDeLivro());
    }

    public void testNaoDeveriaValidarAlocacaoLivro() {
        this.alunoVO.setQuantidade_alocados(3);
        AlunoBO alunoBO = new AlunoBO(this.alunoDAOMock, this.alunoVO);
        assertFalse(alunoBO.validarAlocaçãoDeLivro());
    }
     */
}
