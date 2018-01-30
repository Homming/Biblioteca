package javafx.controller;

import dao.AlunoDAO;
import dao.LivroDAO;
import database.Database;
import database.DatabaseFactory;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import vo.AlunoVO;

public class FXMLAnchorPaneCadastroAlunoController implements Initializable {

    @FXML
    private TableView<AlunoVO> tblViewAlunos;
    @FXML
    private TableColumn<AlunoVO, String> tblColumnMatr;
    @FXML
    private TableColumn<AlunoVO, String> tblColumnNome;
    @FXML
    private Button btnNovo;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnExcluir;
    @FXML
    private Button btnBuscar;
    @FXML
    private Label lblCodigo;
    @FXML
    private Label lblNome;
    @FXML
    private Label lblTelefone;
    @FXML
    private Label lblEmail;
    @FXML
    private Label lblComplemento;
    @FXML
    private Label lblMatricula;
    @FXML
    private Label lblTurma;
    @FXML
    private TextField txtPesq;

    private List<AlunoVO> listAlunos;
    private ObservableList<AlunoVO> observableListAlunos;
    private boolean buttonBuscarClicked = false;

    private final Database database = DatabaseFactory.getDatabase("mysql");
    private final Connection connection = database.conectar();
    private final AlunoDAO alunoDAO = new AlunoDAO();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        alunoDAO.setConnection(connection);
        carregarTableViewAluno();

        tblViewAlunos.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> selecionarItemTblViewAlunos(newValue));
    }

    public void carregarTableViewAluno() {
        tblColumnMatr.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        tblColumnNome.setCellValueFactory(new PropertyValueFactory<>("nome"));

        if (txtPesq == null) {
            listAlunos = alunoDAO.listar();
        } else {
            listAlunos = pesquisar_aluno();
        }

        observableListAlunos = FXCollections.observableArrayList(listAlunos);
        tblViewAlunos.setItems(observableListAlunos);
    }

    public void selecionarItemTblViewAlunos(AlunoVO aluno) {
        if (aluno != null) {
            lblCodigo.setText(String.valueOf(aluno.getId_aluno()));
            lblNome.setText(aluno.getNome());
            lblTelefone.setText(aluno.getTelefone());
            lblEmail.setText(aluno.getEmail());
            lblComplemento.setText(aluno.getComplemento());
            lblMatricula.setText(aluno.getMatricula());
            lblTurma.setText(aluno.getTurma());
        } else {
            lblCodigo.setText("");
            lblNome.setText("");
            //lblQtdLocados.setText("");
            //lblQtdMax.setText("");
            lblTelefone.setText("");
            lblEmail.setText("");
            lblComplemento.setText("");
            lblMatricula.setText("");
            lblTurma.setText("");

        }
    }

    //****************************Botões e Tela Dialog**********************************
    @FXML
    public void handleButtonNovo() throws IOException, SQLException {
        AlunoVO aluno = new AlunoVO(); // instancia nova aluno 
        boolean buttonConfirmarClicked = showFXMLAnchorPaneCadastroAlunoDialog(aluno); // abre a tela para inserção dos dados
        if (buttonConfirmarClicked) {// se o botão confirmar for clicado
            alunoDAO.cadastrar(aluno);// insere os dados cadastrados na tela
            carregarTableViewAluno();
        }
    }

    @FXML
    public void handleButtonEditar() throws IOException, SQLException {
        AlunoVO aluno = tblViewAlunos.getSelectionModel().getSelectedItem();// puxa as informações da aluno selecionado
        if (aluno != null) {
            boolean buttonConfirmarClicked = showFXMLAnchorPaneCadastroAlunoDialog(aluno);
            if (buttonConfirmarClicked) {
                alunoDAO.editar(aluno);
                carregarTableViewAluno();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Por favor, escolha um aluno na Tabela!");
                alert.show();
            }
        }
    }

    @FXML
    public void handleButtonRemover() throws IOException, SQLException {
        AlunoVO aluno = tblViewAlunos.getSelectionModel().getSelectedItem();
        if (aluno != null) {
            alunoDAO.remover(aluno);
            carregarTableViewAluno();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um aluno na Tabela!");
            alert.show();
        }
    }

    //Método para exibir a tela de cadastro (Dialog) 
    public boolean showFXMLAnchorPaneCadastroAlunoDialog(AlunoVO AlunoVO) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(FXMLAnchorPaneCadastroAlunoDialogController.class.getResource("/javafx/view/FXMLAnchorPaneCadastroAlunoDialog.fxml"));

        AnchorPane page = (AnchorPane) loader.load(); //typecast para guardar em page a tela carregada.

        // Stage Dialog, para que seja visível ao aluno
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de aluno");//exibido na parte superior da tela
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);

        // aluno no controller
        FXMLAnchorPaneCadastroAlunoDialogController controller = loader.getController(); // instancia do controller da tela dialog
        //setando o stage e o aluno para o controller
        controller.setDialogStage(dialogStage);
        controller.setAluno(AlunoVO);

        // Mostra a tela e espera o aluno fechar
        dialogStage.showAndWait();

        return controller.isButtonConfirmarClicked();

    }

    public List<AlunoVO> pesquisar_aluno() {
        String sql = "SELECT * FROM aluno WHERE nome LIKE ?";
        List<AlunoVO> retorno = new ArrayList<>();
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, txtPesq.getText() + "%");//"%" Representa tudo que vem depois da primeira letra digitada no botao pesquisar.
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                AlunoVO aluno = new AlunoVO();
                aluno.setId_aluno(resultado.getInt("id_aluno"));
                aluno.setNome(resultado.getString("nome"));
                aluno.setTelefone(resultado.getString("telefone"));
                aluno.setEmail(resultado.getString("email"));
                aluno.setComplemento(resultado.getString("complemento"));
                aluno.setMatricula(resultado.getString("matricula"));
                aluno.setTurma(resultado.getString("turma"));

                retorno.add(aluno);
            }
        } catch (SQLException ex) {
            Logger.getLogger(LivroDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return retorno;
    }

}
