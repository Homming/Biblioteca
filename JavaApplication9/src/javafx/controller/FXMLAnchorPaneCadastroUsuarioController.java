package javafx.controller;

import dao.BibliotecariaDAO;
import database.Database;
import database.DatabaseFactory;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import vo.BibliotecariaVO;

public class FXMLAnchorPaneCadastroUsuarioController implements Initializable {

    @FXML
    private TableView<BibliotecariaVO> tblViewUsuarios;
    @FXML
    private TableColumn<BibliotecariaVO, String> tblColumnUsuarioCodigo;
    @FXML
    private TableColumn<BibliotecariaVO, String> tblColumnUsuarioNome;
    @FXML
    private Button btnNovo;
    @FXML
    private Button btnSalvar;
    @FXML
    private Button btnExcluir;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnLimpar;
    @FXML
    private Label lblUserCod;
    @FXML
    private Label lblUserName;
    @FXML
    private Label lblUserCPF;
    @FXML
    private Label lblUserEmail;
    @FXML
    private Label lblUserUser;
    @FXML
    private Label lblUserPass;
    @FXML
    private Label lblUserConfPass;
    @FXML
    private Label lblUserTel;
    
    private List<BibliotecariaVO> listUsuarios;
    private ObservableList<BibliotecariaVO> observableListUsuarios;
    
    private final Database database = DatabaseFactory.getDatabase("mysql");
    private final Connection connection = database.conectar();
    private final BibliotecariaDAO bibliotecariaDAO = new BibliotecariaDAO();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
