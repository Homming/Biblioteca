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
import dao.BibliotecariaDAO;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;

public class FXMLAnchorPaneCadastroUsuarioDialogController implements Initializable {

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
        this.bibliotecaria.setNome(txtUserName.getText());
        this.bibliotecaria.setCpf(txtUserCPF.getText());
        this.bibliotecaria.setCel(txtUserTel.getText());
        this.bibliotecaria.setEmail(txtUserEmail.getText());
        this.bibliotecaria.setUsuario(txtUserUser.getText());
        this.bibliotecaria.setSenha(txtUserPass.getText());
        this.bibliotecaria.setConf_senha(txtUserConfPass.getText());

        BibliotecariaDAO bibliotecariaDAO = new BibliotecariaDAO();
        BibliotecariaBO validar = new BibliotecariaBO(bibliotecariaDAO, this.bibliotecaria);

        validar.validarCadastroDeUsuario();
        validar.validarCadastroDeNome();
        validar.validarCadastroDeCelular();
        validar.validarCadastroDeSenha();

        if (validar.errorMessage.length() == 0) {
            buttonConfirmarClicked = true;
            dialogStage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Falha no Cadastro!");
            Stage stageAlert = (Stage) alert.getDialogPane().getScene().getWindow();//typecast alert para stage
            stageAlert.getIcons().add(new Image("/imagens/ops.png")); // icone no stage alert
            alert.setHeaderText("Campos Inválidos, por favor, corrija...");
            alert.setContentText(validar.errorMessage);
            alert.show();
        }
    }

    @FXML
    public void handleButtonCancelar() {
        dialogStage.close();
    }

}
