
package testes;

import bo.LivroBO;
import dao.ILivroDAO;
import java.time.LocalDate;
import junit.framework.TestCase;
import static org.junit.Assert.*;
import vo.LivroVO;

public class LivroBOTest extends TestCase {
    private LivroVO livroVO;
    private ILivroDAO livroDAOMock;
   
    protected void setUp() throws Exception{
        ILivroDAO livroDAOMock = new LivroDAOMock();
        LocalDate teste = LocalDate.now();
        LivroVO livroVO = new LivroVO();
        livroVO.setTitulo("Livro Matematica 1");
        livroVO.setData_livro(teste);
        livroVO.setQuantidade_livro(3);
        livroVO.setAutor1("mark zuckeberg");
        
        this.livroVO = livroVO;
        this.livroDAOMock = livroDAOMock;
       
    }
    
    public void testDeveriaValidarTituloLivro(){
        LivroBO livroBO = new LivroBO(this.livroDAOMock,this.livroVO);
        assertTrue(livroBO.validarCadastroDeTitulo());
    }
    
    public void testNaoDeveriaValidarTituloLivro(){
        this.livroVO.setTitulo("");
        LivroBO livroBO = new LivroBO(this.livroDAOMock,this.livroVO);
        assertFalse(livroBO.validarCadastroDeTitulo());
    }
    
    public void testDeveriaValidarDataLivro(){
        LivroBO livroBO = new LivroBO(this.livroDAOMock,this.livroVO);
        assertTrue(livroBO.validarCadastroDeDataDeLivro());
    }
    
    public void testNaoDeveriaValidarDataLivro(){
        this.livroVO.setData_livro(null);
        LivroBO livroBO = new LivroBO(this.livroDAOMock,this.livroVO);
        assertFalse(livroBO.validarCadastroDeDataDeLivro());
    }
    
    public void testDeveriaValidarQuantidadeLivro(){
        LivroBO livroBO = new LivroBO(this.livroDAOMock,this.livroVO);
        assertTrue(livroBO.validarCadastroDeQuantidadeDeLivro());
    }
    
    public void testNaoDeveriaValidarQuantidadeLivro(){
         this.livroVO.setQuantidade_livro(0);
         LivroBO livroBO = new LivroBO(this.livroDAOMock,this.livroVO);
         assertFalse(livroBO.validarCadastroDeQuantidadeDeLivro());
    }
    
    public void testDeveriaValidarAutorLivro(){
        LivroBO livroBO = new LivroBO(this.livroDAOMock,this.livroVO);
        assertTrue(livroBO.validarCadastroDeAutor());
    }
    
    public void testNaoDeveriaValidarAutorLivro(){
         this.livroVO.setAutor1("mark");
         LivroBO livroBO = new LivroBO(this.livroDAOMock,this.livroVO);
         assertFalse(livroBO.validarCadastroDeAutor());
    }
      
    public void testDeveriaValidarAlocacaoLivro(){
        this.livroVO.setQuantidade_livro(1);
        LivroBO livroBO = new LivroBO(this.livroDAOMock,this.livroVO);
        assertTrue(livroBO.validarAlocaçãoDeLivro());
    }
    
    public void testNaoDeveriaValidarAlocacaoLivro(){
         this.livroVO.setQuantidade_livro(0);
         LivroBO livroBO = new LivroBO(this.livroDAOMock,this.livroVO);
         assertFalse(livroBO.validarAlocaçãoDeLivro());
    }
    
     /* teste futuro pra quando o tevonha fazer o DAOMockLivro
    
    public void testDeveriaCadastrarLivro() throws Exception{
        LivroBO livroBO = new LivroBO(this.livroDAOMock,this.livroVO);
        
        try{
            livroBO.CadastroLivro();
        }catch(Exception e){
            fail("Deveria ter cadastrado");
        }
        
    }*/
    
}
