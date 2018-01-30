
package bo;

import bo.BibliotecariaBO;
import dao.IBibliotecariaDAO;
import junit.framework.TestCase;
import dao.BibliotecariaDAOMock;
import database.Database;
import database.DatabaseFactory;
import java.sql.Connection;
import static org.junit.Assert.*;
import vo.BibliotecariaVO;


public class BibliotecariaBOTest extends TestCase {
    private BibliotecariaVO bibliotecariaVO;
    private IBibliotecariaDAO bibliotecariaDAOMock;
    private final Database database = DatabaseFactory.getDatabase("mysql");
    private final Connection connection = database.conectar();
    
    protected void setUp() throws Exception{
        
        IBibliotecariaDAO bibliotecariaDAOMock = new BibliotecariaDAOMock();
        BibliotecariaVO bibliotecariaVO = new BibliotecariaVO();
        bibliotecariaVO.setNome("Maria Joaquina pereira");
        bibliotecariaVO.setCel("996222428");
        bibliotecariaVO.setUsuario("adminin");
        bibliotecariaVO.setSenha("admin123");
        bibliotecariaVO.setConf_senha("admin123");
        
        this.bibliotecariaDAOMock = bibliotecariaDAOMock;
        this.bibliotecariaVO = bibliotecariaVO;
        this.bibliotecariaDAOMock.setConnection(connection);
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
 
    

    
    public void testDeveriaCadastrarBibliotecaria() throws Exception{
        BibliotecariaBO bibliotecariaBO = new  BibliotecariaBO(this.bibliotecariaDAOMock,this.bibliotecariaVO);
        
        try{
            bibliotecariaBO.cadastrarBibliotecaria();
        }catch(Exception e){
            fail("Deveria ter cadastrado");
        }
        
    }
    
    public void testDeveriaEditarBibliotecaria() throws Exception{
        BibliotecariaBO bibliotecariaBO = new  BibliotecariaBO(this.bibliotecariaDAOMock,this.bibliotecariaVO);
        
        try{
            bibliotecariaBO.cadastrarBibliotecaria();
        }catch(Exception e){
            fail("Deveria ter cadastrado");
        }
    }
    
    public void testDeveriaExcluirBibliotecaria()throws Exception{
        BibliotecariaBO bibliotecariaBO = new  BibliotecariaBO(this.bibliotecariaDAOMock,this.bibliotecariaVO);
        
        try{
            bibliotecariaBO.excluirBibliotecaria();
        }catch(Exception e){
            fail("Deveria ter cadastrado");
        }
    }
    
    
}
