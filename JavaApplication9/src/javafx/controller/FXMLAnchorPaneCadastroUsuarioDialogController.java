package javafx.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import vo.BibliotecariaVO;

public class FXMLAnchorPaneCadastroUsuarioDialogController implements Initializable {

    @FXML
    private Button btnConfirmar;
    @FXML
    private Button btnCancelar;
    @FXML
    private Label lblUserName;
    @FXML
    private Label lblUserCPF;
    @FXML
    private Label lblUserEmail;
    @FXML
    private Label lblUserUser;
    @FXML
    private Label lblUserPass;
    @FXML
    private Label lblUserConfPass;
    @FXML
    private Label lblUserTel;
    @FXML
    private TextField txtUserName;
    @FXML
    private TextField txtUserCPF;
    @FXML
    private TextField txtUserTel;
    @FXML
    private TextField txtUserEmail;
    @FXML
    private TextField txtUserUser;
    @FXML
    private TextField txtUserPass;
    @FXML
    private TextField txtUserConfPass;

    private Stage dialogStage;
    private boolean buttonConfirmarClicked = false;
    private BibliotecariaVO bibliotecaria;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    //Getters e Setters de Stage buttonConfirmar e bibliotecaria
    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isButtonConfirmarClicked() {
        return buttonConfirmarClicked;
    }

    public void setButtonConfirmarClicked(boolean buttonConfirmarClicked) {
        this.buttonConfirmarClicked = buttonConfirmarClicked;
    }

    public BibliotecariaVO getBibliotecaria() {
        return bibliotecaria;
    }

    public void setBibliotecaria(BibliotecariaVO bibliotecaria) {
        this.bibliotecaria = bibliotecaria;
        this.txtUserName.setText(bibliotecaria.getNome());
        this.txtUserCPF.setText(bibliotecaria.getCpf());
        this.txtUserEmail.setText(bibliotecaria.getEmail());
        this.txtUserTel.setText(bibliotecaria.getCel());
        this.txtUserUser.setText(bibliotecaria.getUsuario());
        this.txtUserPass.setText(bibliotecaria.getSenha());
        this.txtUserConfPass.setText(bibliotecaria.getConf_senha());
    }

    @FXML
    public void handleButtonConfirmar() {

        if (validarEntradaDeDados()) {// se todos os campos estiverem ok

            bibliotecaria.setNome(txtUserName.getText());
            bibliotecaria.setCpf(txtUserCPF.getText());
            bibliotecaria.setCel(txtUserTel.getText());
            bibliotecaria.setEmail(txtUserEmail.getText());
            bibliotecaria.setUsuario(txtUserUser.getText());
            bibliotecaria.setSenha(txtUserPass.getText());
            bibliotecaria.setConf_senha(txtUserConfPass.getText());

            buttonConfirmarClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    public void handleButtonCancelar() {
        dialogStage.close();
    }

    //Validação da entrada dos dados de cadastro
    private boolean validarEntradaDeDados() {
        String errorMessage = "";

        if (txtUserName.getText() == null || txtUserName.getText().length() == 0) {
            errorMessage += "Nome Inválido!\n";
        }
        if (txtUserEmail.getText() == null || txtUserEmail.getText().length() == 0) {
            errorMessage += "E-mail Inválido!\n";
        }
        if (txtUserUser.getText() == null || txtUserUser.getText().length() == 0) {
            errorMessage += "Usuário Inválido!\n";
        }
        if (txtUserPass.getText() == null || txtUserPass.getText().length() == 0) {
            errorMessage += "Senha Inválida!\n";
        }

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
