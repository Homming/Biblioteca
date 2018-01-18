package javafx.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import vo.BibliotecariaVO;
import bo.BibliotecariaBO;

public class FXMLAnchorPaneCadastroUsuarioDialogController implements Initializable {

/* ACREDITO QUE AS LABELS NAO SAO NECESSARIAS NO CONTROLLER
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
*/
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
    private PasswordField txtUserPass;
    @FXML
    private PasswordField txtUserConfPass;

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
        String valNome = txtUserName.getText();// guardando o valor que deseja validar em uma variavel
        String valEmail = txtUserEmail.getText();
        String valUser = txtUserUser .getText();
        String valPass = txtUserPass.getText();
        String valConfPass = txtUserConfPass.getText();//NAO ESTA FUNCIONANDO
        BibliotecariaBO validar = new BibliotecariaBO(); // instanciando a classe BO para chamar o método de validação
        
        if (validar.validarEntradaDeDados(valNome, valEmail, valUser, valPass)) {// se todos os campos estiverem ok

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

}
