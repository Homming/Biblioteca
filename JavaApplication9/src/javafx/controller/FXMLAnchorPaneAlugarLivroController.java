package javafx.controller;

import dao.AluguelDAO;
import dao.AlunoDAO;
import dao.ItemDeAluguelDAO;
import dao.LivroDAO;
import database.Database;
import database.DatabaseFactory;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import vo.AluguelVO;
import vo.ItemDeAluguelVO;
import vo.LivroVO;

public class FXMLAnchorPaneAlugarLivroController implements Initializable {

    @FXML
    private TableView<AluguelVO> tblAluguel;
    @FXML
    private TableColumn<AluguelVO, Integer> tblColumnCodigo;
    @FXML
    private TableColumn<AluguelVO, LocalDate> tblColumnData;
    @FXML
    private TableColumn<AluguelVO, AluguelVO> tblColumnAluno;
    @FXML
    private Button btnNovo;
    @FXML
    private Button btnRemover;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnDevolver;
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
    @FXML
    private Label lblDataDevolvido;

    private List<AluguelVO> listAlugueis; // pega a lista de alugueis retornada pelo BD
    private ObservableList<AluguelVO> observableListAlugueis; // seta os dados da list em um observable list

    private final Database database = DatabaseFactory.getDatabase("mysql");
    private final Connection connection = database.conectar();
    private final AluguelDAO aluguelDAO = new AluguelDAO();
    private final ItemDeAluguelDAO itemDeAluguelDAO = new ItemDeAluguelDAO();
    private final LivroDAO livroDAO = new LivroDAO();
    private final AlunoDAO alunoDAO = new AlunoDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        aluguelDAO.setConnection(connection);
        carregarTableViewAlugueis();

        selecionarItemTblAlugueis(null);

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
            lblLivro.setText(String.valueOf(aluguel.getItensDeAluguel()));// ERRADO
            lblDevolucao.setText(String.valueOf(aluguel.getData_devolucao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
            lblDevolvido.setText(Devolvido(aluguel.isDevolvido()));
            lblDataDevolvido.setText(DataDevolvido(String.valueOf(aluguel.getData_devolvido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))));
        } else {
            lblCodigo.setText("");
            lblDataAluguel.setText("");
            lblAluno.setText("");
            lblLivro.setLayoutX(0);
            lblDevolucao.setText("");
            lblDevolvido.setText("");
            lblDataDevolvido.setText("");
        }
    }

    public String LivrosLocados(List<ItemDeAluguelVO> livro) {
        //FAZER COM QUE EXIBA OS LIVROS LOCADOS
        return "Lista";
    }

    public String DataDevolvido(String dev) {
        String data_default = "01/01/2018";

        if (dev.equals(data_default)) {
            return "Não Devolvido";
        }
        return dev;
    }

    public String Devolvido(boolean dev) {
        if (dev == true) {
            return "Sim";
        } else {
            return "Não";
        }

    }

    public void carregarTableViewAlugueis() {
        // configuração da tabela
        tblColumnCodigo.setCellValueFactory(new PropertyValueFactory<>("id_aluguel"));
        tblColumnData.setCellValueFactory(new PropertyValueFactory<>("data_aluguel"));
        tblColumnAluno.setCellValueFactory(new PropertyValueFactory<>("aluno"));

        listAlugueis = aluguelDAO.listar();

        observableListAlugueis = FXCollections.observableArrayList(listAlugueis);
        tblAluguel.setItems(observableListAlugueis);
    }

    @FXML
    public void handleButtonNovo() throws IOException, SQLException {
        AluguelVO aluguel = new AluguelVO();
        List<ItemDeAluguelVO> listItensDeAluguel = new ArrayList<>();
        aluguel.setItensDeAluguel(listItensDeAluguel);
        boolean buttonConfirmarClicked = showFXMLAnchorPaneAlugarLivroDialog(aluguel);
        if (buttonConfirmarClicked) {
            try {
                connection.setAutoCommit(false);//Ao cadastrar um aluguel, não será cadastrado de imediato, pois só deve inserir se todos os itens de aluguel passarem
                aluguelDAO.setConnection(connection);
                aluguelDAO.cadastrar(aluguel);
                itemDeAluguelDAO.setConnection(connection);
                livroDAO.setConnection(connection);//para atualizar a quantidade do livro em estoque         
                for (ItemDeAluguelVO listItemDeAluguelVO : aluguel.getItensDeAluguel()) {// para cada item de aluguel, cadastre
                    LivroVO livro = listItemDeAluguelVO.getLivro();
                    listItemDeAluguelVO.setAluguel(aluguelDAO.buscarUltimoAluguel());
                    itemDeAluguelDAO.inserir(listItemDeAluguelVO);//inserção
                    livro.setQuantidade_livro(livro.getQuantidade_livro() - listItemDeAluguelVO.getQuantidade());
                    livroDAO.editarCad(livro);
                }
                connection.commit();//se nao tiver ocorrido nenhum problema, efetue o cadastro
                carregarTableViewAlugueis();
            } catch (SQLException ex) {
                try {
                    connection.rollback();
                } catch (SQLException ex1) {
                    Logger.getLogger(FXMLAnchorPaneAlugarLivroController.class.getName()).log(Level.SEVERE, null, ex1);
                }
                Logger.getLogger(FXMLAnchorPaneAlugarLivroController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /*@FXML
    public void handleButtonDevolver() throws IOException, SQLException {
        AluguelVO aluguel = tblAluguel.getSelectionModel().getSelectedItem();
        if (aluguel != null) {
            boolean buttonConfirmarClicked = showFXMLAnchorPaneDevolverLivroDialog(aluguel); // abre a tela para inserção dos dados
            if (buttonConfirmarClicked) {// se o botão confirmar for clicado
                aluguelDAO.devolver(aluguel);// insere os dados cadastrados na tela
                carregarTableViewAlugueis();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Por favor, escolha um aluno na Tabela!");
                alert.show();
            }
        }
    }*/
    @FXML
    public void handleButtonDevolver() throws IOException, SQLException {
        AluguelVO aluguel = tblAluguel.getSelectionModel().getSelectedItem();
        if (aluguel != null) {
            List<ItemDeAluguelVO> listItensDeAluguel = aluguel.getItensDeAluguel();
            aluguel.setItensDeAluguel(listItensDeAluguel);
            boolean buttonConfirmarClicked = showFXMLAnchorPaneDevolverLivroDialog(aluguel);
            if (buttonConfirmarClicked) {
                try {
                    connection.setAutoCommit(false);
                    aluguelDAO.setConnection(connection);
                    aluguelDAO.devolver(aluguel);
                    itemDeAluguelDAO.setConnection(connection);
                    livroDAO.setConnection(connection);
                    for (ItemDeAluguelVO listItemDeAluguelVO : aluguel.getItensDeAluguel()) {// para cada item de aluguel, cadastre
                        LivroVO livro = listItemDeAluguelVO.getLivro();
                        listItemDeAluguelVO.setAluguel(aluguelDAO.buscarAluguel(aluguel));
                        livro.setQuantidade_livro(livro.getQuantidade_livro() + listItemDeAluguelVO.getQuantidade());
                        livroDAO.editarCad(livro);
                    }
                    connection.commit();//se nao tiver ocorrido nenhum problema, efetue o cadastro
                    carregarTableViewAlugueis();
                } catch (SQLException ex) {
                    try {
                        connection.rollback();
                    } catch (SQLException ex1) {
                        Logger.getLogger(FXMLAnchorPaneAlugarLivroController.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                    Logger.getLogger(FXMLAnchorPaneAlugarLivroController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            Stage stageAlert = (Stage) alert.getDialogPane().getScene().getWindow();//typecast alert para stage
            stageAlert.getIcons().add(new Image("/imagens/ops.png")); // icone no stage alert
            alert.setContentText("Por favor, escolha um aluguel na Tabela!");
            alert.show();
        }
    }

    @FXML
    public void handleButtonRemover() throws IOException, SQLException {
        AluguelVO aluguel = tblAluguel.getSelectionModel().getSelectedItem();// pega venda selecionada
        if (aluguel != null) {
            connection.setAutoCommit(false);// Ao remover um aluguel, não será removido de imediato, pois só deve remover se todos os itens de aluguel passarem
            aluguelDAO.setConnection(connection);
            itemDeAluguelDAO.setConnection(connection);
            livroDAO.setConnection(connection);
            for (ItemDeAluguelVO listItemDeAluguelVO : aluguel.getItensDeAluguel()) {//deleta os itens de aluguel e depois o aluguel.
                LivroVO livro = listItemDeAluguelVO.getLivro();
                livro.setQuantidade_livro(livro.getQuantidade_livro() + listItemDeAluguelVO.getQuantidade());
                livroDAO.editarCad(livro);//editar o livro pois deve aumentar a quantidade novamente
                itemDeAluguelDAO.remover(listItemDeAluguelVO);//remove todos os itens de aluguel
            }
            aluguelDAO.remover(aluguel);//remove o aluguel escolhido
            connection.commit();//efetua as modificações
            carregarTableViewAlugueis();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            Stage stageAlert = (Stage) alert.getDialogPane().getScene().getWindow();//typecast alert para stage
            stageAlert.getIcons().add(new Image("/imagens/ops.png")); // icone no stage alert
            alert.setContentText("Por favor, escolha um aluguel na Tabela!");
            alert.show();
        }
    }

    public boolean showFXMLAnchorPaneAlugarLivroDialog(AluguelVO aluguel) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLAnchorPaneAlugarLivroDialogController.class.getResource("/javafx/view/FXMLAnchorPaneAlugarLivroDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load(); //typecast para guardar em page a tela carregada.

        // Stage Dialog, para que seja visível ao usuário
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Registro de Aluguel");//exibido na parte superior da tela
        Image Icon = new Image("/imagens/books64.png");
        dialogStage.getIcons().add(Icon);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Setando aluguel no controller
        FXMLAnchorPaneAlugarLivroDialogController controller = loader.getController(); // instancia do controller da tela dialog
        //setando para o controller
        controller.setDialogStage(dialogStage);
        controller.setAluguelVO(aluguel);

        // Mostra a tela e espera o usuário fechar
        dialogStage.showAndWait();

        return controller.isButtonConfirmarClicked();//handleButtonConfirmar retorna verdadeiro para handleButtonNovo se o usuário clicar em confirmar no dialog.
    }

    public boolean showFXMLAnchorPaneDevolverLivroDialog(AluguelVO aluguel) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLAnchorPaneDevolverLivroDialogController.class.getResource("/javafx/view/FXMLAnchorPaneDevolverLivroDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load(); //typecast para guardar em page a tela carregada.

        // Stage Dialog, para que seja visível ao usuário
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Registro de Devolução");//exibido na parte superior da tela
        Image Icon = new Image("/imagens/books64.png");
        dialogStage.getIcons().add(Icon);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Setando aluguel no controller
        FXMLAnchorPaneDevolverLivroDialogController controller = loader.getController(); // instancia do controller da tela dialog
        //setando para o controller
        controller.setDialogStage(dialogStage);
        controller.setAluguelVO(aluguel);

        // Mostra a tela e espera o usuário fechar
        dialogStage.showAndWait();

        return controller.isButtonConfirmarClicked();//handleButtonConfirmar retorna verdadeiro para handleButtonNovo se o usuário clicar em confirmar no dialog.
    }
}
