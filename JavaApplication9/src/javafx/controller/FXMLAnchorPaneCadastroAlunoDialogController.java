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
import javafx.stage.Stage;
import vo.AlunoVO;

public class FXMLAnchorPaneCadastroAlunoDialogController implements Initializable {

    @FXML
    private TextField txtNome;
    private TextField txtQtdAlocados;
    private TextField txtQtdMax;
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
        //this.txtQtdAlocados.setText(String.valueOf(aluno.getQuantidade_alocados()));
        //this.txtQtdMax.setText(String.valueOf(aluno.getQtd_maxlivro()));
        this.txtTelefone.setText(aluno.getTelefone());
        this.txtEmail.setText(aluno.getEmail());
        this.txtComplemento.setText(aluno.getComplemento());
        this.txtMatricula.setText(aluno.getMatricula());
        this.txtTurma.setText(aluno.getTurma());
    }

    @FXML
    public void handleButtonConfirmar() {
        aluno.setNome(txtNome.getText());
        //aluno.setQuantidade_alocados(Integer.parseInt(txtQtdAlocados.getText()));
        //aluno.setQtd_maxlivro(Integer.parseInt(txtQtdMax.getText()));
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
        //validar.validarQtdMaxDeLivro();

        if (validar.errorMessage.length() == 0) {
            buttonConfirmarClicked = true;
            dialogStage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Falha no Cadastro!");
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
