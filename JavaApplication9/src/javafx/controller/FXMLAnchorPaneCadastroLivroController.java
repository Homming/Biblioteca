package javafx.controller;

import dao.LivroDAO;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import vo.LivroVO;

public class FXMLAnchorPaneCadastroLivroController implements Initializable {

    @FXML
    private TableView<LivroVO> tblLivro;
    @FXML
    private TableColumn<LivroVO, String> tblColumnLivroCod;
    @FXML
    private TableColumn<LivroVO, String> tblColumnLivroTitulo;
    @FXML
    private Button btnNovo;
    @FXML
    private Button btnExcluir;
    @FXML
    private Button btnEditar;
    @FXML
    private Label lblLivroCod;
    @FXML
    private Label lblLivroTitulo;
    @FXML
    private Label lblLivroData;
    @FXML
    private Label lblLivroCDD;
    @FXML
    private Label lblLivroCutter;
    @FXML
    private Label lblLivroComplemento;
    @FXML
    private Label lblLivroAutor1;
    @FXML
    private Label lblLivroAutor2;
    @FXML
    private Label lblLivroAutor3;
    @FXML
    private Label lblLivroTradutor;
    @FXML
    private Label lblLivroIlustrador;
    @FXML
    private Label lblLivroAssunto;
    @FXML
    private Label lblLivroLocal;
    @FXML
    private Label lblLivroEditora;
    @FXML
    private Label lblLivroAno;
    @FXML
    private Label lblLivroEdicao;
    
    private List<LivroVO> listLivros; // pega a lista de livros retornada pelo BD
    private ObservableList<LivroVO> observableListLivros; // seta os dados da list em um observable list
    
    
    //Manipulação do BD
    private final Database database = DatabaseFactory.getDatabase("mysql");
    private final Connection connection = database.conectar();
    private final LivroDAO livroDAO = new LivroDAO();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        livroDAO.setConnection(connection);
        carregarTableViewLivro();
        
        //Listen acionado na seleção da Tabela de Livros, chamando o método indicado
        tblLivro.getSelectionModel().selectedItemProperty().addListener( 
                (observable, oldValue, newValue) -> selecionarItemTblLivros(newValue));
    }    
    
    public void carregarTableViewLivro(){
        tblColumnLivroTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        tblColumnLivroCod.setCellValueFactory(new PropertyValueFactory<>("id_livro"));
        
        listLivros = livroDAO.listar();
        
        observableListLivros = FXCollections.observableArrayList(listLivros);
        tblLivro.setItems(observableListLivros);
    }
    
     public void selecionarItemTblLivros(LivroVO livro){
        //Preenchimento dos campos através do livro selecionado
        if(livro != null){
            lblLivroCod.setText(String.valueOf(livro.getId_livro()));
            lblLivroTitulo.setText(livro.getTitulo());
            lblLivroData.setText(livro.getData());
            lblLivroCDD.setText(livro.getCdd());
            lblLivroCutter.setText(livro.getCutter());
            lblLivroComplemento.setText(livro.getComplemento());
            lblLivroAutor1.setText(livro.getAutor1());
            lblLivroAutor2.setText(livro.getAutor2());
            lblLivroAutor3.setText(livro.getAutor3());
            lblLivroTradutor.setText(livro.getTradutores());
            lblLivroIlustrador.setText(livro.getIlustradores());
            lblLivroAssunto.setText(livro.getAssunto());
            lblLivroLocal.setText(livro.getLocal());
            lblLivroEditora.setText(livro.getEditora());
            lblLivroAno.setText(livro.getAno());
            lblLivroEdicao.setText(livro.getEdicao());
        }else{
            lblLivroCod.setText("");
            lblLivroTitulo.setText("");
            lblLivroData.setText("");
            lblLivroCDD.setText("");
            lblLivroCutter.setText("");
            lblLivroComplemento.setText("");
            lblLivroAutor1.setText("");
            lblLivroAutor2.setText("");
            lblLivroAutor3.setText("");
            lblLivroTradutor.setText("");
            lblLivroIlustrador.setText("");
            lblLivroAssunto.setText("");
            lblLivroLocal.setText("");
            lblLivroEditora.setText("");
            lblLivroAno.setText("");
            lblLivroEdicao.setText("");
        }
    }
     
     @FXML
    public void handleButtonNovo() throws IOException {
        LivroVO livro = new LivroVO(); // instancia novo livro 
        boolean buttonConfirmarClicked = showFXMLAnchorPaneCadastroLivroDialog(livro); // abre a tela para inserção dos dados, se o usuário tiver clicado no botão
        if(buttonConfirmarClicked){// se o botão confirmar for clicado
            livroDAO.cadastrar(livro);// insere os dados cadastrados na tela
            carregarTableViewLivro();
        }
    }
    
    @FXML
    public void handleButtonEditar() throws IOException{
        LivroVO livro = tblLivro.getSelectionModel().getSelectedItem();// puxa as informações do livro selecionado
        if(livro != null){
            boolean buttonConfirmarClicked = showFXMLAnchorPaneCadastroLivroDialog(livro);
            if(buttonConfirmarClicked){
                livroDAO.editarCad(livro);
                carregarTableViewLivro();
            }else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Por favor, escolha um livro na Tabela!");
                alert.show();
            }
        }
    }
    
    @FXML
    public void handleButtonRemover() throws IOException{
        LivroVO livro = tblLivro.getSelectionModel().getSelectedItem();
        if(livro != null){
            livroDAO.excluirCad(livro);
            carregarTableViewLivro();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Por favor, escolha um livro na Tabela!");
            alert.show();
        }
    }
    
    //Método para exibir a tela de cadastro (Dialog) 
    public boolean showFXMLAnchorPaneCadastroLivroDialog(LivroVO livro) throws IOException{
        
       FXMLLoader loader = new FXMLLoader();
       loader.setLocation(FXMLAnchorPaneCadastroLivroDialogController.class.getResource("/javafx/view/FXMLAnchorPaneCadastroLivroDialog.fxml"));
        
        AnchorPane page = (AnchorPane) loader.load(); //typecast para guardar em page a tela carregada.
        
        // Stage Dialog, para que seja visível ao usuário
        Stage dialogStage = new Stage();
        dialogStage.setTitle("Cadastro de Livro");//exibido na parte superior da tela
        Scene scene = new Scene(page);
        dialogStage.setScene(scene);
        
        // Setando livro no controller
        FXMLAnchorPaneCadastroLivroDialogController controller = loader.getController(); // instancia do controller da tela dialog
        //setando o stage e o usuário para o controller
        controller.setDialogStage(dialogStage);
        controller.setLivro(livro);
        
        // Mostra a tela e espera o usuário fechar
        dialogStage.showAndWait();
        
        return controller.isButtonConfirmarClicked();
        
    }
    
}
