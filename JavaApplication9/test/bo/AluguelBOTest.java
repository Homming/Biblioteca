package bo;

import dao.IAluguelDAO;
import java.time.LocalDate;
import junit.framework.TestCase;
import dao.AluguelDAOMock;
import vo.AluguelVO;

public class AluguelBOTest extends TestCase {

    private AluguelVO aluguelVO;
    private IAluguelDAO aluguelDAOMock;

    protected void setUp() throws Exception {
        IAluguelDAO aluguelDAOMock = new AluguelDAOMock();
        LocalDate teste = LocalDate.now();
        AluguelVO aluguelVO = new AluguelVO(12345, teste, 12345, 12345, teste, false);

        this.aluguelDAOMock = aluguelDAOMock;
        this.aluguelVO = aluguelVO;
    }

    /* teste futuro pra quando o tevonha fazer o DAOMockAluguel
    
    public void testDeveriaCadastrarAluguelPassandoAlunoELivro() throws Exception{
        AluguelBO aluguelBO = new AluguelBO(this.aluguelVO,this.aluguelDAOMock);
        
        try{
            aluguelBO.CadastroAluguel();
        }catch(Exception e){
            fail("Deveria ter cadastrado");
        }
        
    }*/

    public void testDeveriaValidarIdAluno() {
        AluguelBO aluguelBO = new AluguelBO(this.aluguelDAOMock, this.aluguelVO);
        assertTrue(aluguelBO.inserindoAluno());
    }

    public void testeNaoDeveriaValidarIdAluno() {
        this.aluguelVO.setAluno_id(0);
        AluguelBO aluguelBO = new AluguelBO(this.aluguelDAOMock, this.aluguelVO);
        assertFalse(aluguelBO.inserindoAluno());
    }

    /*public void testDeveriaValidarIdLivro() {
        AluguelBO aluguelBO = new AluguelBO(this.aluguelDAOMock, this.aluguelVO);
        assertTrue(aluguelBO.inserindoLivro());
    }*/

}
