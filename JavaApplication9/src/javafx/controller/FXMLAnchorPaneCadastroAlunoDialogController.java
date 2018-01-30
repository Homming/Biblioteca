package javafx.controller;

import bo.AlunoBO;
import dao.AlunoDAO;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import vo.AlunoVO;

public class FXMLAnchorPaneCadastroAlunoDialogController implements Initializable {

    @FXML
    private TextField txtNome;
    @FXML
    private TextField txtTelefone;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtComplemento;
    @FXML
    private TextField txtMatricula;
    @FXML
    private TextField txtTurma;
    @FXML
    private Button btnConfirmar;
    @FXML
    private Button btnCancelar;

    private Stage dialogStage;
    private boolean buttonConfirmarClicked = false;
    private AlunoVO aluno;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

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

    public AlunoVO getAluno() {
        return aluno;
    }

    public void setAluno(AlunoVO aluno) {
        this.aluno = aluno;
        //Caso não seja um aluno Novo, set os valores do aluno existente
        this.txtNome.setText(aluno.getNome());
        this.txtTelefone.setText(aluno.getTelefone());
        this.txtEmail.setText(aluno.getEmail());
        this.txtComplemento.setText(aluno.getComplemento());
        this.txtMatricula.setText(aluno.getMatricula());
        this.txtTurma.setText(aluno.getTurma());
    }

    @FXML
    public void handleButtonConfirmar() {
        aluno.setNome(txtNome.getText());
        aluno.setTelefone(txtTelefone.getText());
        aluno.setEmail(txtEmail.getText());
        aluno.setComplemento(txtComplemento.getText());
        aluno.setMatricula(txtMatricula.getText());
        aluno.setTurma(txtTurma.getText());

        AlunoDAO alunoDAO = new AlunoDAO();
        AlunoBO validar = new AlunoBO(alunoDAO, this.aluno);

        validar.validarCadastroDeNome();
        validar.validarCadastroDeMatricula();
        validar.validarCadastroDeTelefone();

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
