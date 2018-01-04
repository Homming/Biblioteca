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
