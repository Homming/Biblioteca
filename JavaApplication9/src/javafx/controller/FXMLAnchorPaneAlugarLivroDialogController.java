package javafx.controller;

import dao.AluguelDAO;
import dao.AlunoDAO;
import dao.LivroDAO;
import database.Database;
import database.DatabaseFactory;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import vo.AluguelVO;
import vo.AlunoVO;
import vo.LivroVO;

public class FXMLAnchorPaneAlugarLivroDialogController implements Initializable {

    @FXML
    private ComboBox cbxAluno;
    @FXML
    private ComboBox cbxLivro;
    @FXML
    private Button btnConfirmar;
    @FXML
    private Button btnCancelar;
    @FXML
    private DatePicker dtpAluguel;
    @FXML
    private DatePicker dtpDevolucao;
    @FXML
    private CheckBox chbDevolvido;

    private List<AlunoVO> listAlunos;
    private List<LivroVO> listLivros;
    private ObservableList<AlunoVO> observableListAlunos;
    private ObservableList<LivroVO> observableListLivros;

    private final Database database = DatabaseFactory.getDatabase("mysql");
    private final Connection connection = database.conectar();
    private final AluguelDAO aluguelDAO = new AluguelDAO();
    private final LivroDAO livroDAO = new LivroDAO();
    private final AlunoDAO alunoDAO = new AlunoDAO();

    private Stage dialogStage; //Atributo que representa a tela
    private boolean buttonConfirmarClicked = false; //Para saber em qual botão o usuário clicou , Confirmar/Cancelar
    private AluguelVO aluguel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        alunoDAO.setConnection(connection);
        livroDAO.setConnection(connection);
        carregarComboBoxAlunos();
        carregarComboBoxLivros();
    }

    public void carregarComboBoxAlunos() {
        listAlunos = alunoDAO.listar();
        observableListAlunos = FXCollections.observableArrayList(listAlunos);
        cbxAluno.setItems(observableListAlunos);
    }

    public void carregarComboBoxLivros() {
        listLivros = livroDAO.listar();
        observableListLivros = FXCollections.observableArrayList(listLivros);
        cbxLivro.setItems(observableListLivros);
    }

    //Getters e Setters
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

    public AluguelVO getAluguel() {
        return aluguel;
    }

    public void setAluguel(AluguelVO aluguel) {
        this.aluguel = aluguel;
        //Caso não seja um aluguel Novo, set os valores do aluguel existente

        //this.txtCDD.setText(livro.getCdd()); // dtpAluguel dtpDevolucao chbDevolvido
        this.cbxAluno.getSelectionModel().getSelectedItem();
        this.cbxLivro.getSelectionModel().getSelectedItem();
        //this.dtpAluguel.get;
        //this.dtpDevolucao.get;
        //this.chbDevolvido.get;
    }

    @FXML
    public void handleButtonConfirmar() {
        /*
        String valNome = txtUserName.getText();// guardando o valor que deseja validar em uma variavel
        String valEmail = txtUserEmail.getText();
        String valUser = txtUserUser .getText();
        String valPass = txtUserPass.getText();
        String valConfPass = txtUserConfPass.getText();//NAO ESTA FUNCIONANDO

        AluguelBO validar = new AluguelBO(); // instanciando a classe BO para chamar o método de validação

        //if (validar.validarEntradaDeDados(valNome, valEmail, valUser, valPass)) {// se todos os campos estiverem ok
         */
        if (validarEntradaDeDados()) {
            aluguel.setData_aluguel(dtpAluguel.getValue());
            aluguel.setAluno((AlunoVO) cbxAluno.getSelectionModel().getSelectedItem());
            aluguel.setLivro((LivroVO) cbxLivro.getSelectionModel().getSelectedItem());
            aluguel.setData_devolucao(dtpDevolucao.getValue());
            aluguel.setDevolvido(chbDevolvido.isSelected());

            buttonConfirmarClicked = true;
            dialogStage.close();
        }
    }

    //VALIDAÇÃO DENTRO DA PROPRIA CLASSE AO INVES DO BO
    private boolean validarEntradaDeDados() {
        String errorMessage = "";

        if (cbxAluno.getSelectionModel().getSelectedItem() == null) {
            errorMessage += "Aluno inválido!\n";
        }
        if (cbxLivro.getSelectionModel().getSelectedItem() == null) {
            errorMessage += "Livro inválido!\n";
        }

        if (dtpAluguel.getValue() == null) {
            errorMessage += "Data de aluguel inválida!\n";
        }

        if (dtpDevolucao.getValue() == null) {
            errorMessage += "Data de devolução inválida!\n";
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro no registro");
            alert.setHeaderText("Campos inválidos, correção necessária...");
            alert.setContentText(errorMessage);
            alert.show();
            return false;
        }
    }

    @FXML
    public void handleButtonCancelar() {
        dialogStage.close();
    }

}
