package javafx.controller;

import dao.AluguelDAO;
import dao.LivroDAO;
import database.Database;
import database.DatabaseFactory;
import java.net.URL;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private Label lblData;
    @FXML
    private Label lblAluno;
    @FXML
    private Label lblLivro;

    private List<AluguelVO> listAlugueis; // pega a lista de alugueis retornada pelo BD
    private ObservableList<AluguelVO> observableListAlugueis; // seta os dados da list em um observable list
    
    private final Database database = DatabaseFactory.getDatabase("mysql");
    private final Connection connection = database.conectar();
    private final AluguelDAO aluguelDAO = new AluguelDAO();
    private final LivroDAO livroDAO = new LivroDAO();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        aluguelDAO.setConnection(connection);
        carregarTableViewAlugueis();
        
        //Listener acionado na seleção da Tabela de Aluguel, chamando o método indicado
        tblAluguel.getSelectionModel().selectedItemProperty().addListener( 
                (observable, oldValue, newValue) -> selecionarItemTblAlugueis(newValue));
    }    
    
    public void selecionarItemTblAlugueis(AluguelVO aluguel){
        //Preenchimento dos campos através do livro selecionado
        if(aluguel != null){
            lblCodigo.setText(String.valueOf(aluguel.getId_aluguel()));
            lblData.setText(aluguel.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            lblAluno.setText(aluguel.getAluno().toString());
            lblLivro.setText(aluguel.getLivro().toString());
        }else{
            lblCodigo.setText("");
            lblData.setText("");
            lblAluno.setText("");
            lblLivro.setText("");
        }
    }
    
    public void carregarTableViewAlugueis(){
        // configuração da tabela
        tblColumnCodigo.setCellValueFactory(new PropertyValueFactory<>("id_aluguel"));
        tblColumnData.setCellValueFactory(new PropertyValueFactory<>("data_aluguel"));
        tblColumnLivro.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        
        listAlugueis = aluguelDAO.listar();
        
        observableListAlugueis = FXCollections.observableArrayList(listAlugueis);
        tblAluguel.setItems(observableListAlugueis);
    }
    
}
