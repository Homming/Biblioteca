
package bo;

import bo.BibliotecariaBO;
import dao.IBibliotecariaDAO;
import junit.framework.TestCase;
import dao.BibliotecariaDAOMock;
import static org.junit.Assert.*;
import vo.BibliotecariaVO;


public class BibliotecariaBOTest extends TestCase {
    private BibliotecariaVO bibliotecariaVO;
    private IBibliotecariaDAO bibliotecariaDAOMock;
    
    protected void setUp() throws Exception{
        
        IBibliotecariaDAO bibliotecariaDAOMock = new BibliotecariaDAOMock();
        BibliotecariaVO bibliotecariaVO = new BibliotecariaVO();
        bibliotecariaVO.setNome("Maria Joaquina pereira");
        bibliotecariaVO.setCel("996222428");
        bibliotecariaVO.setUsuario("admin");
        bibliotecariaVO.setSenha("admin123");
        bibliotecariaVO.setConf_senha("admin123");
        
        this.bibliotecariaDAOMock = bibliotecariaDAOMock;
        this.bibliotecariaVO = bibliotecariaVO;
       
    }
    
    public void testDeveriaValidarNomeBibliotecaria(){
        BibliotecariaBO bibliotecariaBO = new  BibliotecariaBO(this.bibliotecariaDAOMock,this.bibliotecariaVO);
        assertTrue(bibliotecariaBO.validarCadastroDeNome());
    }
    
    public void testNaoDeveriaValidarNomeBibliotecaria(){
        this.bibliotecariaVO.setNome("Maria");
        BibliotecariaBO bibliotecariaBO = new  BibliotecariaBO(this.bibliotecariaDAOMock,this.bibliotecariaVO);
        assertFalse(bibliotecariaBO.validarCadastroDeNome());
    }
    
    public void testDeveriaValidarCelularBibliotecaria(){
        BibliotecariaBO bibliotecariaBO = new  BibliotecariaBO(this.bibliotecariaDAOMock,this.bibliotecariaVO);
        assertTrue(bibliotecariaBO.validarCadastroDeCelular());
    }
    
    public void testNaoDeveriaValidarCelularBibliotecaria(){
        this.bibliotecariaVO.setCel("9622242");
        BibliotecariaBO bibliotecariaBO = new  BibliotecariaBO(this.bibliotecariaDAOMock,this.bibliotecariaVO);
        assertFalse(bibliotecariaBO.validarCadastroDeCelular());
    }
    
    public void testDeveriaValidarUsuarioBibliotecaria(){
        BibliotecariaBO bibliotecariaBO = new  BibliotecariaBO(this.bibliotecariaDAOMock,this.bibliotecariaVO);
        assertTrue(bibliotecariaBO.validarCadastroDeUsuario());
    }
    
    public void testNaoDeveriaValidarUsuarioBibliotecaria(){
        this.bibliotecariaVO.setUsuario("adm");
        BibliotecariaBO bibliotecariaBO = new  BibliotecariaBO(this.bibliotecariaDAOMock,this.bibliotecariaVO);
        assertFalse(bibliotecariaBO.validarCadastroDeUsuario());
    }
    
    public void testDeveriaValidarSenhaBibliotecaria(){
        BibliotecariaBO bibliotecariaBO = new  BibliotecariaBO(this.bibliotecariaDAOMock,this.bibliotecariaVO);
        assertTrue(bibliotecariaBO.validarCadastroDeSenha());
    }
    
    public void testNaoDeveriaValidarSenhaBibliotecaria(){
        this.bibliotecariaVO.setConf_senha("admin12");
        BibliotecariaBO bibliotecariaBO = new  BibliotecariaBO(this.bibliotecariaDAOMock,this.bibliotecariaVO);
        assertFalse(bibliotecariaBO.validarCadastroDeSenha());
    }
    /* fazer essa com tevonha
    public void testDeveriaValidarEntradaDeDados(){
        
    }
    */
    
     /* teste futuro pra quando o tevonha fazer o DAOMockBibliotecaria
    
    public void testDeveriaCadastrarBibliotecaria() throws Exception{
        BibliotecariaBO bibliotecariaBO = new  BibliotecariaBO(this.bibliotecariaDAOMock,this.bibliotecariaVO);
        
        try{
            bibliotecariaBO.cadastrarBibliotecaria();
        }catch(Exception e){
            fail("Deveria ter cadastrado");
        }
        
    }*/
    
}
