/*

package bo;

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
NICOLAS
*/

package bo;

import dao.ILivroDAO;
import javafx.scene.control.Alert;

public class BibliotecariaBO {
    private ILivroDAO livroDAO;
    private String senha;
    
    public BibliotecariaBO(){
        
    }
    
    //Validação da entrada dos dados de cadastro de Usuário
    public boolean validarEntradaDeDados(String txtNome, String txtEmail, String txtUser, String txtPass) {
        String errorMessage = "";

        if (txtNome == null || txtNome.length() == 0) {
            errorMessage += "Nome Inválido!\n";
        }
        if (txtEmail == null || txtEmail.length() == 0) {
            errorMessage += "E-mail Inválido!\n";
        }
        if (txtUser == null || txtUser.length() == 0) {
            errorMessage += "Usuário Inválido!\n";
        }
        if (txtPass == null || txtPass.length() == 0) {
            errorMessage += "Senha Inválida!\n";
        }
        /* NAO ESTA FUNCIONANDO, mesmo puxando os valores ele acusa que nao batem
        if(txtConfPass != txtPass){
            errorMessage += "Senhas não batem!\n";
        }
        */

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Falha no Cadastro!");
            alert.setHeaderText("Campos Inválidos, por favor, corrija...");
            alert.setContentText(errorMessage);
            alert.show();
            return false;
        }
    }
    
    
}
