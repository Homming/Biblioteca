package javafx.controller;

import dao.BibliotecariaDAO;
import database.Database;
import database.DatabaseFactory;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
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
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import vo.BibliotecariaVO;

public class FXMLAnchorPaneCadastroUsuarioController implements Initializable {

    @FXML // deve se utilizar @fxml para cada elemento visual que será utilizado na view
    private TableView<BibliotecariaVO> tblViewUsuarios;
    @FXML
    private TableColumn<BibliotecariaVO, String> tblColumnUsuarioCodigo;
    @FXML
    private TableColumn<BibliotecariaVO, String> tblColumnUsuarioNome;
    @FXML
    private Button btnNovo;
    @FXML
    private Button btnExcluir;
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
        bibliotecariaDAO.setConnection(connection);
        carregarTableViewUsuario();

        //Ação de clicar no nome do usuário exibido
        tblViewUsuarios.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTblViewUsuarios(newValue));
    }

    public void carregarTableViewUsuario() {
        tblColumnUsuarioNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tblColumnUsuarioCodigo.setCellValueFactory(new PropertyValueFactory<>("id_usuario"));

        listUsuarios = bibliotecariaDAO.listar();

        observableListUsuarios = FXCollections.observableArrayList(listUsuarios);
        tblViewUsuarios.setItems(observableListUsuarios);
    }

    public void selecionarItemTblViewUsuarios(BibliotecariaVO usuario) {
        //Preenchimento dos campos através do usuário selecionado
        if (usuario != null) {
            lblUserCod.setText(String.valueOf(usuario.getId_usuario()));
            lblUserName.setText(usuario.getNome());
            lblUserCPF.setText(usuario.getCpf());
            lblUserEmail.setText(usuario.getEmail());
            lblUserUser.setText(usuario.getUsuario());
            lblUserTel.setText(usuario.getCel());
        } else {
            lblUserCod.setText("");
            lblUserName.setText("");
            lblUserCPF.setText("");
            lblUserEmail.setText("");
            lblUserUser.setText("");
            lblUserTel.setText("");
        }
    }

    @FXML
    public void handleButtonNovo() throws IOException {
        BibliotecariaVO bibliotecaria = new BibliotecariaVO(); // instancia nova bibliotecaria 
        boolean buttonConfirmarClicked = showFXMLAnchorPaneCadastroUsuarioDialog(bibliotecaria); // abre a tela para inserção dos dados
        if (buttonConfirmarClicked) {// se o botão confirmar for clicado
            bibliotecariaDAO.cadastrar(bibliotecaria);// insere os dados cadastrados na tela
            carregarTableViewUsuario();
        }
    }

    @FXML
    public void handleButtonEditar() throws IOException {
        BibliotecariaVO bibliotecaria = tblViewUsuarios.getSelectionModel().getSelectedItem();// puxa as informações da bibliotecaria/usuário selecionado
        if (bibliotecaria != null) {
            boolean buttonConfirmarClicked = showFXMLAnchorPaneCadastroUsuarioDialog(bibliotecaria);
            if (buttonConfirmarClicked) {
                bibliotecariaDAO.editar(bibliotecaria);
                carregarTableViewUsuario();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                Stage stageAlert = (Stage) alert.getDialogPane().getScene().getWindow();//typecast alert para stage
                stageAlert.getIcons().add(new Image("/imagens/ops.png")); // icone no stage alert
                alert.setContentText("Por favor, escolha um usuário na Tabela!");
                alert.show();
            }
        }
    }

    @FXML
    public void handleButtonRemover() throws IOException {
        BibliotecariaVO bibliotecaria = tblViewUsuarios.getSelectionModel().getSelectedItem();
        if (bibliotecaria != null) {
            bibliotecariaDAO.excluirCad(bibliotecaria);
            carregarTableViewUsuario();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            Stage stageAlert = (Stage) alert.getDialogPane().getScene().getWindow();//typecast alert para stage
            stageAlert.getIcons().add(new Image("/imagens/ops.png")); // icone no stage alert
            alert.setContentText("Por favor, escolha um usuário na Tabela!");
            alert.show();
        }
    }

    //Método para exibir a tela de cadastro (Dialog) 
    public boolean showFXMLAnchorPaneCadastroUsuarioDialog(BibliotecariaVO bibliotecariaVO) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLAnchorPaneCadastroUsuarioDialogController.class.getResource("/javafx/view/FXMLAnchorPaneCadastroUsuarioDialog.fxml"));

        AnchorPane page = (AnchorPane) loader.load(); //typecast para guardar em page a tela carregada.

        // Stage Dialog, para que seja visível ao usuário
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de Usuário");//exibido na parte superior da tela
        Image Icon = new Image("/imagens/books64.png");
        dialogStage.getIcons().add(Icon);
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // Usuário no controller
        FXMLAnchorPaneCadastroUsuarioDialogController controller = loader.getController(); // instancia do controller da tela dialog
        //setando o stage e o usuário para o controller
        controller.setDialogStage(dialogStage);
        controller.setBibliotecaria(bibliotecariaVO);

        // Mostra a tela e espera o usuário fechar
        dialogStage.showAndWait();

        return controller.isButtonConfirmarClicked();

    }

}
