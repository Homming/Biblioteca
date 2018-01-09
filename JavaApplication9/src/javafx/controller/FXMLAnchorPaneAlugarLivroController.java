package javafx.controller;

import dao.AluguelDAO;
import dao.AlunoDAO;
import dao.LivroDAO;
import database.Database;
import database.DatabaseFactory;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import vo.AluguelVO;

public class FXMLAnchorPaneAlugarLivroController implements Initializable {

    @FXML
    private TableView<AluguelVO> tblAluguel;
    @FXML
    private TableColumn<AluguelVO, Integer> tblColumnCodigo;
    @FXML
    private TableColumn<AluguelVO, LocalDate> tblColumnData;
    @FXML
    private TableColumn<AluguelVO, AluguelVO> tblColumnLivro;
    @FXML
    private Button btnNovo;
    @FXML
    private Button btnRemover;
    @FXML
    private Button btnEditar;
    @FXML
    private Label lblCodigo;
    @FXML
    private Label lblDataAluguel;
    @FXML
    private Label lblAluno;
    @FXML
    private Label lblLivro;
    @FXML
    private Label lblDevolvido;
    @FXML
    private Label lblDevolucao;

    private List<AluguelVO> listAlugueis; // pega a lista de alugueis retornada pelo BD
    private ObservableList<AluguelVO> observableListAlugueis; // seta os dados da list em um observable list

    private final Database database = DatabaseFactory.getDatabase("mysql");
    private final Connection connection = database.conectar();
    private final AluguelDAO aluguelDAO = new AluguelDAO();
    private final LivroDAO livroDAO = new LivroDAO();
    private final AlunoDAO alunoDAO = new AlunoDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        aluguelDAO.setConnection(connection);
        carregarTableViewAlugueis();

        //Listener acionado na seleção da Tabela de Aluguel, chamando o método indicado
        tblAluguel.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTblAlugueis(newValue));
    }

    public void selecionarItemTblAlugueis(AluguelVO aluguel) {
        //Preenchimento dos campos através do livro selecionado
        if (aluguel != null) {
            lblCodigo.setText(String.valueOf(aluguel.getId_aluguel()));
            lblDataAluguel.setText(String.valueOf(aluguel.getData_aluguel().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
            lblAluno.setText(aluguel.getAluno().toString());
            lblLivro.setText(aluguel.getLivro().toString());
            lblDevolucao.setText(String.valueOf(aluguel.getData_devolucao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
            lblDevolvido.setText(String.valueOf(aluguel.getDevolvido()));
        } else {
            lblCodigo.setText("");
            lblDataAluguel.setText("");
            lblAluno.setText("");
            lblLivro.setText("");
            lblDevolucao.setText("");
            lblDevolvido.setText("");
        }
    }

    public void carregarTableViewAlugueis() {
        // configuração da tabela
        tblColumnCodigo.setCellValueFactory(new PropertyValueFactory<>("id_aluguel"));
        tblColumnData.setCellValueFactory(new PropertyValueFactory<>("data_aluguel"));
        tblColumnLivro.setCellValueFactory(new PropertyValueFactory<>("titulo"));

        listAlugueis = aluguelDAO.listar();

        observableListAlugueis = FXCollections.observableArrayList(listAlugueis);
        tblAluguel.setItems(observableListAlugueis);
    }

    //Método para exibir a tela (Dialog) 
    public boolean showFXMLAnchorPaneAlugarLivroDialog(AluguelVO aluguel) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLAnchorPaneAlugarLivroDialogController.class.getResource("/javafx/view/FXMLAnchorPaneAlugarLivroDialog.fxml"));

        AnchorPane page = (AnchorPane) loader.load(); //typecast para guardar em page a tela carregada.

        // Stage Dialog, para que seja visível ao usuário
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Registro de Aluguel");//exibido na parte superior da tela
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Setando aluguel no controller
        FXMLAnchorPaneAlugarLivroDialogController controller = loader.getController(); // instancia do controller da tela dialog
        //setando para o controller
        controller.setDialogStage(dialogStage);
        controller.setAluguel(aluguel);

        // Mostra a tela e espera o usuário fechar
        dialogStage.showAndWait();

        return controller.isButtonConfirmarClicked();

    }

    /*
    @FXML
    public void handleButtonNovo() throws IOException {
        AluguelVO aluguel = new AluguelVO(); // instancia novo livro 
        boolean buttonConfirmarClicked = showFXMLAnchorPaneAlugarLivroDialog(aluguel); // abre a tela para inserção dos dados, se o usuário tiver clicado no botão
        if (buttonConfirmarClicked) {// se o botão confirmar for clicado
            aluguelDAO.cadastrar(aluguel);// insere os dados cadastrados na tela
            carregarTableViewAlugueis();
        }
    }
     */
    @FXML
    public void handleButtonNovo() throws IOException, SQLException {
        AluguelVO aluguel = new AluguelVO(); // instancia novo livro 
        boolean buttonConfirmarClicked = showFXMLAnchorPaneAlugarLivroDialog(aluguel); // abre a tela para inserção dos dados, se o usuário tiver clicado no botão
        if (buttonConfirmarClicked) {// se o botão confirmar for clicado
            try {
                connection.setAutoCommit(false);// desativando autocommit para só realizar a inserção quando eu desejar
                aluguelDAO.setConnection(connection);
                aluguelDAO.cadastrar(aluguel);
                livroDAO.setConnection(connection);
                alunoDAO.setConnection(connection);
                connection.commit();
                carregarTableViewAlugueis();
            } catch (SQLException ex) {
                try {
                    connection.rollback();
                } catch (SQLException e) {

                }
            }
        }
    }

    @FXML
    public void handleButtonRemover() throws IOException, SQLException {
        AluguelVO aluguel = tblAluguel.getSelectionModel().getSelectedItem();
        if (aluguel != null) {
            connection.setAutoCommit(false);
            livroDAO.setConnection(connection);
            aluguelDAO.setConnection(connection);
            aluguelDAO.remover(aluguel);
            connection.commit();
            carregarTableViewAlugueis();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, selecione um aluguel na tabela!");
            alert.show();
        }
    }
}
