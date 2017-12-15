package bo;

import dao.LivroDAO;
import dao.AlunoDAO;
import dao.ILivroDAO;
import dao.IAlunoDAO;

public class BibliotecariaBO {
    private ILivroDAO livroDAO;
    private IAlunoDAO usuarioDAO;
    private String senha;
    
    public BibliotecariaBO(){
        
    }
    
    public boolean validarSenha(String senha){
        if(this.senha == senha)
            return true;
        else
            return false;
    }
}
