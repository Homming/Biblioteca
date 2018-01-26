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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import vo.AluguelVO;
import vo.AlunoVO;
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
            lblLivro.setText(aluguel.getLivro().toString());//ESTA EXIBINDO APENAS UM LIVRO, SE HOUVER MAIS NAO EXIBE
            //lblLivro.setText(LivrosLocados(aluguel.getItensDeAluguel()));
            lblDevolucao.setText(String.valueOf(aluguel.getData_devolucao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
            //lblDevolvido.setText(String.valueOf(aluguel.getDevolvido())); retorna a string true ou false
            lblDevolvido.setText(Devolvido(aluguel.getDevolvido()));
        } else {
            lblCodigo.setText("");
            lblDataAluguel.setText("");
            lblAluno.setText("");
            lblLivro.setText("");
            lblDevolucao.setText("");
            lblDevolvido.setText("");
        }
    }

    public String LivrosLocados(List<ItemDeAluguelVO> livro) {
        //FAZER COM QUE EXIBA OS LIVROS LOCADOS
        return null;
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
                //alunoDAO.setConnection(connection);//para atualizar a quantidade de livros locados do aluno          
                for (ItemDeAluguelVO listItemDeAluguelVO : aluguel.getItensDeAluguel()) {// para cada item de aluguel, cadastre
                    LivroVO livro = listItemDeAluguelVO.getLivro();
                    //AlunoVO aluno = new AlunoVO();
                    listItemDeAluguelVO.setAluguel(aluguelDAO.buscarUltimoAluguel());
                    itemDeAluguelDAO.inserir(listItemDeAluguelVO);//inserção
                    livro.setQuantidade_livro(livro.getQuantidade_livro() - listItemDeAluguelVO.getQuantidade());
                    //aluno.setQuantidade_alocados(aluno.getQuantidade_alocados() - listItemDeAluguelVO.getQuantidade());
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

    
    //APENAS COPIADO DO HANDLEBUTTON NOVO, NAO ESTA CERTO
    @FXML
    public void handleButtonEditar() throws IOException, SQLException {
        AluguelVO aluguel = tblAluguel.getSelectionModel().getSelectedItem();// puxa as informações da aluguel selecionado
        List<ItemDeAluguelVO> listItensDeAluguel = new ArrayList<>();
        aluguel.setItensDeAluguel(listItensDeAluguel);
        if (aluguel != null) {
            boolean buttonConfirmarClicked = showFXMLAnchorPaneEditarAluguelDialog(aluguel);
            if (buttonConfirmarClicked) {
                try {
                    connection.setAutoCommit(false);
                    aluguelDAO.setConnection(connection);
                    aluguelDAO.alterar(aluguel);
                    itemDeAluguelDAO.setConnection(connection);
                    livroDAO.setConnection(connection);
                    for (ItemDeAluguelVO listItemDeAluguelVO : aluguel.getItensDeAluguel()) {
                        LivroVO livro = listItemDeAluguelVO.getLivro();
                        listItemDeAluguelVO.setAluguel(aluguelDAO.buscarUltimoAluguel());
                        itemDeAluguelDAO.inserir(listItemDeAluguelVO);
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
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Por favor, escolha um aluguel na Tabela!");
                alert.show();
            }
        }
    }

    //Método para exibir a tela (Dialog) 
    public boolean showFXMLAnchorPaneEditarAluguelDialog(AluguelVO aluguel) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLAnchorPaneAlugarLivroDialogController.class.getResource("/javafx/view/FXMLAnchorPaneEditarAluguelDialog.fxml"));
        AnchorPane page = (AnchorPane) loader.load(); //typecast para guardar em page a tela carregada.

        // Stage Dialog, para que seja visível ao usuário
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Editar Aluguel");//exibido na parte superior da tela
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Setando aluguel no controller
        FXMLAnchorPaneEditarAluguelDialogController controller = loader.getController(); // instancia do controller da tela dialog
        //setando para o controller
        controller.setDialogStage(dialogStage);
        controller.setAluguelVO(aluguel);

        // Mostra a tela e espera o usuário fechar
        dialogStage.showAndWait();

        return controller.isButtonConfirmarClicked();//handleButtonConfirmar retorna verdadeiro para handleButtonNovo se o usuário clicar em confirmar no dialog.
    }
    
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
        controller.setAluguelVO(aluguel);

        // Mostra a tela e espera o usuário fechar
        dialogStage.showAndWait();

        return controller.isButtonConfirmarClicked();//handleButtonConfirmar retorna verdadeiro para handleButtonNovo se o usuário clicar em confirmar no dialog.
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
            alert.setContentText("Por favor, escolha um aluguel na Tabela!");
            alert.show();
        }
    }
}
