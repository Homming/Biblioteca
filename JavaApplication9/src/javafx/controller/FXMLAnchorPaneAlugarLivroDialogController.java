package javafx.controller;

import bo.AluguelBO;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import vo.AluguelVO;
import vo.AlunoVO;
import vo.ItemDeAluguelVO;
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
    @FXML
    private TextField txtItemDeAluguelQtd;
    @FXML
    private Button btnAdicionar;
    @FXML
    private TableView tblItensdeAluguel;
    @FXML
    private TableColumn<ItemDeAluguelVO, LivroVO> tblColumnLivro;
    @FXML
    private TableColumn<ItemDeAluguelVO, Integer> tblColumnQtd;

    private List<AlunoVO> listAlunos;
    private List<LivroVO> listLivros;
    private ObservableList<AlunoVO> observableListAlunos;
    private ObservableList<LivroVO> observableListLivros;
    private ObservableList<ItemDeAluguelVO> observableListItensDeAluguel;

    private final Database database = DatabaseFactory.getDatabase("mysql");
    private final Connection connection = database.conectar();
    private final AlunoDAO alunoDAO = new AlunoDAO();
    private final LivroDAO livroDAO = new LivroDAO();

    private Stage dialogStage; //Atributo que representa a tela
    private boolean buttonConfirmarClicked = false; //Para saber em qual botão o usuário clicou , Confirmar/Cancelar
    private AluguelVO aluguel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        alunoDAO.setConnection(connection);
        livroDAO.setConnection(connection);
        carregarComboBoxAlunos();
        carregarComboBoxLivros();
        tblColumnLivro.setCellValueFactory(new PropertyValueFactory<>("livro"));
        tblColumnQtd.setCellValueFactory(new PropertyValueFactory<>("quantidade"));

    }

    //Getters e Setters
    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public AluguelVO getAluguel() {
        return aluguel;
    }

    public void setAluguelVO(AluguelVO aluguel) {
        this.aluguel = aluguel;
    }

    public boolean isButtonConfirmarClicked() {
        return buttonConfirmarClicked;
    }

    public void setButtonConfirmarClicked(boolean buttonConfirmarClicked) {
        this.buttonConfirmarClicked = buttonConfirmarClicked;
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

    @FXML
    public void handleButtonConfirmar() {
        aluguel.setAluno((AlunoVO) cbxAluno.getSelectionModel().getSelectedItem());
        aluguel.setDevolvido(chbDevolvido.isSelected());
        aluguel.setData_aluguel(dtpAluguel.getValue());
        aluguel.setData_devolucao(dtpDevolucao.getValue());

        AluguelDAO aluguelDAO = new AluguelDAO();
        AluguelBO validar = new AluguelBO(aluguelDAO, this.aluguel);

        validar.validarDataDeAluguel();
        validar.validarDataDeDevolucao();

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

    @FXML
    public void handleButtonAdicionar() {
        LivroVO livro;
        //instanciar um novo item de venda
        ItemDeAluguelVO itemDeAluguel = new ItemDeAluguelVO();

        if (cbxLivro.getSelectionModel().getSelectedItem() != null) {//obter o produto selecionado no combo box, se nao for nulo
            livro = (LivroVO) cbxLivro.getSelectionModel().getSelectedItem();//guarda o produto selecionado

            //verifica se a quantidade solicitada é maior que a quantidade existente no estoque
            if (livro.getQuantidade_livro() >= Integer.parseInt(txtItemDeAluguelQtd.getText())) {
                //Set nos campos de item de aluguel
                itemDeAluguel.setLivro((LivroVO) cbxLivro.getSelectionModel().getSelectedItem());
                itemDeAluguel.setQuantidade(Integer.parseInt(txtItemDeAluguelQtd.getText()));

                aluguel.getItensDeAluguel().add(itemDeAluguel);//formando um item de aluguel

                observableListItensDeAluguel = FXCollections.observableArrayList(aluguel.getItensDeAluguel());//pega todos os itens de aluguel
                tblItensdeAluguel.setItems(observableListItensDeAluguel);//exibindo os itens na observableList

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Problemas na escolha do livro!");
                alert.setContentText("Não existe a quantidade de livros disponíveis no estoque!");
                alert.show();
            }
        }
    }

}
