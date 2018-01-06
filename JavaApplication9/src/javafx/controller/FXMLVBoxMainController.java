package javafx.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;

public class FXMLVBoxMainController implements Initializable {

    @FXML
    private MenuItem menuItemGerenciarUsuario;
    @FXML
    private MenuItem menuItemGerenciarLivro;
    @FXML
    private MenuItem menuItemAlugarLivro;
    @FXML
    private MenuItem menuItemSobre;
    @FXML
    private MenuItem menuItemOpcaoLogout;
    @FXML
    private MenuItem menuItemOpcaoSair;
    @FXML
    private AnchorPane anchorPane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    @FXML
    public void handleMenuItemGerenciarUsuario() throws IOException{
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/javafx/view/FXMLAnchorPaneCadastroUsuario.fxml"));
        anchorPane.getChildren().setAll(a);
    }
    
    @FXML
    public void handleMenuItemGerenciarLivro() throws IOException{
        AnchorPane x = (AnchorPane) FXMLLoader.load(getClass().getResource("/javafx/view/FXMLAnchorPaneCadastroLivro.fxml")); // load da tela 
        anchorPane.getChildren().setAll(x); // abertura da tela
    }
    
    @FXML
    public void handleMenuItemAlugarLivro() throws IOException{
        AnchorPane y = (AnchorPane) FXMLLoader.load(getClass().getResource("/javafx/view/FXMLAnchorPaneAlugarLivro.fxml")); // load da tela 
        anchorPane.getChildren().setAll(y); // abertura da tela
    }
    
    
    
    
            
            
    
}
