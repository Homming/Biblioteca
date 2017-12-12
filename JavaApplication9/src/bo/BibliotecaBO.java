package bo;

import dao.LivroDAO;
import dao.UsuarioDAO;
import dao.ILivroDAO;
import dao.IUsuarioDAO;

public class BibliotecaBO {
    private ILivroDAO livroDAO;
    private IUsuarioDAO usuarioDAO;
    private String senha;
    
    public boolean validarSenha(String senha){
        if(this.senha == senha)
            return true;
        else
            return false;
    }
}
