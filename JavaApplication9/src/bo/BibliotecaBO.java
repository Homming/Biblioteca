package bo;

import dao.LivroDAO;
import dao.UsuarioDAO;

public class BibliotecaBO {
    private ILivroDAO livroDAO;
    private IUsuarioDAO usuarioDAO;
    
    public boolean validarSenha(int senha){
        if(this.senha == senha)
            return true;
        else
            return false;
    }
    
   
    
}
