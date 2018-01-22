package bo;

import bo.AlunoBO;
import dao.IAlunoDAO;
import junit.framework.TestCase;
import dao.AlunoDAOMock;
import static org.junit.Assert.*;
import vo.AlunoVO;


public class AlunoBOTest extends TestCase {
    private AlunoVO alunoVO;
    private IAlunoDAO alunoDAOMock;
    
    
    protected void setUp() throws Exception{
        IAlunoDAO alunoDAOMock = new AlunoDAOMock();
        AlunoVO alunoVO = new AlunoVO();
        alunoVO.setNome("Dereguedeishon santos da silva");
        alunoVO.setTelefone("996222428");
        alunoVO.setComplemento("Rua do petardo, xesquedele, 25");
        alunoVO.setQtd_maxlivro(3);
        alunoVO.setMatricula("Deish2017.5");
        alunoVO.setQuantidade_alocados(2);
        
        this.alunoVO = alunoVO;
        this.alunoDAOMock = alunoDAOMock;
       
    }
    
     /* teste futuro pra quando o tevonha fazer o DAOMockAluno
    
    public void testDeveriaCadastrarAluno() throws Exception{
        AlunoBO alunoBO = new AlunoBO(this.alunoVO,this.alunoDAOMock);
        
        try{
            alunoBO.CadastrarAluno();
        }catch(Exception e){
            fail("Deveria ter cadastrado");
        }
        
    }*/
    
    public void testDeveriaValidarNomeAluno(){
        AlunoBO alunoBO = new AlunoBO(this.alunoVO,this.alunoDAOMock);
        assertTrue(alunoBO.validarCadastroDeNome());
   }
    
    public void testNaoDeveriaValidarNomeAluno(){
        this.alunoVO.setNome("pedro");
        AlunoBO alunoBO = new AlunoBO(this.alunoVO,this.alunoDAOMock);
        assertFalse(alunoBO.validarCadastroDeNome());
    }
    
    public void testDeveriaValidarTelefoneAluno(){
        AlunoBO alunoBO = new AlunoBO(this.alunoVO,this.alunoDAOMock);
        assertTrue(alunoBO.validarCadastroDeTelefone());
    }
    
    public void testNaoDeveriaValidarTelefoneAluno(){
        this.alunoVO.setTelefone("895437");
        AlunoBO alunoBO = new AlunoBO(this.alunoVO,this.alunoDAOMock);
        assertFalse(alunoBO.validarCadastroDeTelefone());
    }
    
    public void testeDeveriaValidarComplementoAluno(){
        AlunoBO alunoBO = new AlunoBO(this.alunoVO,this.alunoDAOMock);
        assertTrue(alunoBO.validarCadastroDeComplemento());
    }
    
    public void testNaoDeveriaValidarComplementoAluno(){
        this.alunoVO.setComplemento("nao sei");
        AlunoBO alunoBO = new AlunoBO(this.alunoVO,this.alunoDAOMock);
        assertFalse(alunoBO.validarCadastroDeComplemento());
    }
    
    public void testeDeveriaValidarMatriculaAluno(){
        AlunoBO alunoBO = new AlunoBO(this.alunoVO,this.alunoDAOMock);
        assertTrue(alunoBO.validarCadastroDeMatricula());
    }
    
    public void testNaoDeveriaValidarMatriculaAluno(){
        this.alunoVO.setMatricula("teste");
        AlunoBO alunoBO = new AlunoBO(this.alunoVO,this.alunoDAOMock);
        assertFalse(alunoBO.validarCadastroDeMatricula());
    }
    
    public void testDeveriaValidarAlocacaoLivro(){
        AlunoBO alunoBO = new AlunoBO(this.alunoVO,this.alunoDAOMock);
        assertTrue(alunoBO.validarAlocaçãoDeLivro());
    }
    
     public void testNaoDeveriaValidarAlocacaoLivro(){
        this.alunoVO.setQuantidade_alocados(3);
        AlunoBO alunoBO = new AlunoBO(this.alunoVO,this.alunoDAOMock);
        assertFalse(alunoBO.validarAlocaçãoDeLivro());
    }
    
}
