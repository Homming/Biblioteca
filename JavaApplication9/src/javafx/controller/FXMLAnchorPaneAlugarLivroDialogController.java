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
        /*
        this.livro = livro;
        //Caso não seja um livro Novo, set os valores do livro existente
        this.txtTitulo.setText(livro.getTitulo());
      //this.dtpData.setText(livro.getData());
        this.txtCDD.setText(livro.getCdd());
        this.txtCutter.setText(livro.getCutter());
        this.txtComplemento.setText(livro.getComplemento());
        this.txtAutor1.setText(livro.getAutor1());
        this.txtAutor2.setText(livro.getAutor2());
        this.txtAutor3.setText(livro.getAutor3());
        this.txtTradut.setText(livro.getTradutores());
        this.txtIlustrad.setText(livro.getIlustradores());
        this.txtAssunto.setText(livro.getAssunto());
        this.txtLocal.setText(livro.getLocal());
        this.txtEditora.setText(livro.getEditora());
        this.txtAno.setText(livro.getAno());
        this.txtEdicao.setText(livro.getEdicao());
         */
    }
    
    @FXML
    public void handleButtonCancelar() {
        dialogStage.close();
    }

}
