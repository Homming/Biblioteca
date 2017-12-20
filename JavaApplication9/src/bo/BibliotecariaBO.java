package bo;

import dao.LivroDAO;
import dao.AlunoDAO;
import dao.ILivroDAO;
import dao.IAlunoDAO;
import dao.IBibliotecariaDAO;

public class BibliotecariaBO {
    private ILivroDAO livroDAO;
    private IAlunoDAO usuarioDAO;
    private IBibliotecariaDAO bibliotecariaDAO;
    private String senha;
    
    public BibliotecariaBO(IBibliotecariaDAO bibliotecariaDAO){
        this.bibliotecariaDAO = bibliotecariaDAO;
    }
    
    public boolean validarSenha(String senha){
        if(this.senha == senha)
            return true;
        else
            return false;
    }
}
