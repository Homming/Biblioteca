package bo;

import dao.LivroDAO;
import dao.AlunoDAO;
import dao.ILivroDAO;
import dao.IAlunoDAO;
import dao.IBibliotecariaDAO;
import java.sql.SQLException;
import vo.BibliotecariaVO;

public class BibliotecariaBO {
    private ILivroDAO livroDAO;
    private IAlunoDAO usuarioDAO;
    private IBibliotecariaDAO bibliotecariaDAO;
    private BibliotecariaVO bibliotecariaVo;
    
    public BibliotecariaBO(IBibliotecariaDAO bibliotecariaDAO, BibliotecariaVO bibliotecariaVo){
        this.bibliotecariaDAO = bibliotecariaDAO;
        this.bibliotecariaVo = bibliotecariaVo;
    }
    
    public boolean validarCadastroDeNome(){
        if(this.bibliotecariaVo.getNome().length() > 10 && !this.bibliotecariaVo.getNome().isEmpty() && this.bibliotecariaVo.getNome()!= null)
            return true;
        else
            return false;
    }
    
    public boolean validarCadastroDeCelular(){
         if(this.bibliotecariaVo.getCel().length() > 7 && !this.bibliotecariaVo.getCel().isEmpty() && this.bibliotecariaVo.getCel()!= null)
            return true;
        else
            return false;
    }
    
    public boolean validarCadastroDeUsuario(){
        if(this.bibliotecariaVo.getUsuario().length() > 4 && !this.bibliotecariaVo.getUsuario().isEmpty() && this.bibliotecariaVo.getUsuario() != null)
            return true;
        else
            return false;
    }
    
    public boolean validarCadastroDeSenha(){
        if (this.bibliotecariaVo.getSenha() == this.bibliotecariaVo.getConf_senha() && this.bibliotecariaVo.getSenha() != null && this.bibliotecariaVo.getSenha().length() > 7 && !this.bibliotecariaVo.getSenha().isEmpty())
            return true;
        else
            return false;
        }   
    
    public void cadastrarBibliotecaria()throws SQLException{
        if (!validarCadastroDeSenha() && !validarCadastroDeUsuario() &&  !validarCadastroDeCelular() && !validarCadastroDeNome())
            throw new IllegalArgumentException("Senha ou Usuario de bibliotecaria invalido");
        else
            this.bibliotecariaDAO.cadastrar(this.bibliotecariaVo);
    }
}
