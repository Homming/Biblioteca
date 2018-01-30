package bo;

import dao.ILivroDAO;
import java.time.LocalDate;
import junit.framework.TestCase;
import dao.LivroDAOMock;
import database.Database;
import database.DatabaseFactory;
import java.sql.Connection;
import java.sql.SQLException;
import vo.LivroVO;

public class LivroBOTest extends TestCase {

    private LivroVO livroVO;
    private ILivroDAO livroDAOMock;
    private final Database database = DatabaseFactory.getDatabase("mysql");
    private final Connection connection = database.conectar();

    @Override
    protected void setUp() throws Exception {
        ILivroDAO livroDAOMock = new LivroDAOMock();
        LivroVO livroVO = new LivroVO();
        LocalDate teste = LocalDate.now();
        livroVO.setTitulo("Livro Matematica Discreta 1");
        livroVO.setData_livro(teste);
        livroVO.setQuantidade_livro(3);
        livroVO.setAutor1("mark zuckeberg do facebook e do instagram");
        
        this.livroVO = livroVO;
        this.livroDAOMock = livroDAOMock;
        this.livroDAOMock.setConnection(connection);

    }

    public void testDeveriaValidarTituloLivro() {
        LivroBO livroBO = new LivroBO(this.livroDAOMock, this.livroVO);
        assertTrue(livroBO.validarCadastroDeTitulo());
    }

    public void testNaoDeveriaValidarTituloLivro() {
        this.livroVO.setTitulo("");
        LivroBO livroBO = new LivroBO(this.livroDAOMock, this.livroVO);
        assertFalse(livroBO.validarCadastroDeTitulo());
    }

    public void testDeveriaValidarDataLivro() {
        LivroBO livroBO = new LivroBO(this.livroDAOMock, this.livroVO);
        assertTrue(livroBO.validarCadastroDeDataDeLivro());
    }

    public void testNaoDeveriaValidarDataLivro() {
        this.livroVO.setData_livro(null);
        LivroBO livroBO = new LivroBO(this.livroDAOMock, this.livroVO);
        assertFalse(livroBO.validarCadastroDeDataDeLivro());
    }

    public void testDeveriaValidarQuantidadeLivro() {
        LivroBO livroBO = new LivroBO(this.livroDAOMock, this.livroVO);
        assertTrue(livroBO.validarCadastroDeQuantidadeDeLivro());
    }

    public void testNaoDeveriaValidarQuantidadeLivro() {
        this.livroVO.setQuantidade_livro(0);
        LivroBO livroBO = new LivroBO(this.livroDAOMock, this.livroVO);
        assertFalse(livroBO.validarCadastroDeQuantidadeDeLivro());
    }

    public void testDeveriaValidarAutorLivro() {
        LivroBO livroBO = new LivroBO(this.livroDAOMock, this.livroVO);
        assertTrue(livroBO.validarCadastroDeAutor());
    }

    public void testNaoDeveriaValidarAutorLivro() {
        this.livroVO.setAutor1("mark");
        LivroBO livroBO = new LivroBO(this.livroDAOMock, this.livroVO);
        assertFalse(livroBO.validarCadastroDeAutor());
    }

    public void testDeveriaValidarAlocacaoLivro() {
        this.livroVO.setQuantidade_livro(1);
        LivroBO livroBO = new LivroBO(this.livroDAOMock, this.livroVO);
        assertTrue(livroBO.validarAlocaçãoDeLivro());
    }

    public void testNaoDeveriaValidarAlocacaoLivro() {
        this.livroVO.setQuantidade_livro(0);
        LivroBO livroBO = new LivroBO(this.livroDAOMock, this.livroVO);
        assertFalse(livroBO.validarAlocaçãoDeLivro());
    }

    public void testDeveriaCadastrarLivro() {
        LivroBO livroBO = new LivroBO(this.livroDAOMock, this.livroVO);
        try {
            assertTrue(livroBO.CadastroLivro());
        } catch (SQLException e) {
            fail("Deveria ter cadastrado");
        }

    }
    
    public void testDeveriaAlterarLivro(){
        LivroBO livroBO = new LivroBO(this.livroDAOMock, this.livroVO);
         try {
            assertTrue(livroBO.alteracaoLivro());
        } catch (SQLException e) {
            fail("Deveria ter cadastrado");
        }
    }
    
    public void testDeveriaExcluirLivro(){
        LivroBO livroBO = new LivroBO(this.livroDAOMock, this.livroVO);
         try {
            assertTrue(livroBO.excluirLivro());
        } catch (SQLException e) {
            fail("Deveria ter cadastrado");
        }
    }
}
