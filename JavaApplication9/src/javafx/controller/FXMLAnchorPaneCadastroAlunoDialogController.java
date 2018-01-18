package javafx.controller;

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
    @FXML
    private TextField txtQtdAlocados;
    @FXML
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
        this.txtQtdAlocados.setText(String.valueOf(aluno.getQuantidade_alocados()));
        this.txtQtdMax.setText(String.valueOf(aluno.getQtd_maxlivro()));
        this.txtTelefone.setText(aluno.getTelefone());
        this.txtEmail.setText(aluno.getEmail());
        this.txtComplemento.setText(aluno.getComplemento());
        this.txtMatricula.setText(aluno.getMatricula());
        this.txtTurma.setText(aluno.getTurma());
    }

    @FXML
    public void handleButtonConfirmar() {
        if (validarEntradaDeDados()) {// se todos os campos estiverem ok

            aluno.setNome(txtNome.getText());
            aluno.setQuantidade_alocados(Integer.parseInt(txtQtdAlocados.getText()));
            aluno.setQtd_maxlivro(Integer.parseInt(txtQtdMax.getText()));
            aluno.setTelefone(txtTelefone.getText());
            aluno.setEmail(txtEmail.getText());
            aluno.setComplemento(txtComplemento.getText());
            aluno.setMatricula(txtMatricula.getText());
            aluno.setTurma(txtTurma.getText());

            buttonConfirmarClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    public void handleButtonCancelar() {
        dialogStage.close();
    }

    //MUDAR PARA O BO
    private boolean validarEntradaDeDados() {
        String errorMessage = "";

        if (txtNome.getText() == null || txtNome.getText().length() == 0) {
            errorMessage += "Nome Inválido!\n";
        }
        
        //FALTA PROIBIR PASSAGEM DO VALOR INTEIRO 'ZERO'
        if (txtQtdMax.getText() == null || txtQtdMax.getText().length() == 0) {
            errorMessage += "Quantidade Máxima de Livros Permitida Inválida!\n";
        }
        if (txtTelefone.getText() == null || txtTelefone.getText().length() == 0) {
            errorMessage += "Telefone Inválido!\n";
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
