package javafx.controller;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class FXMLVBoxMainController implements Initializable {

    @FXML
    private MenuItem menuItemGerenciarUsuario;
    @FXML
    private MenuItem menuItemGerenciarLivro;
    @FXML
    private MenuItem menuItemGerenciarAluno;
    @FXML
    private MenuItem menuItemAlugarLivro;
    @FXML
    private MenuItem menuItemOpcaoLogout;
    @FXML
    private MenuItem menuItemOpcaoSair;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Label lblHora;
    @FXML
    private MenuItem menuItemRegistrarDevolucao;
    @FXML
    private MenuItem menuItemSobre1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    public void handleMenuItemGerenciarUsuario() throws IOException {
        AnchorPane a = (AnchorPane) FXMLLoader.load(getClass().getResource("/javafx/view/FXMLAnchorPaneCadastroUsuario.fxml"));
        anchorPane.getChildren().setAll(a);
    }

    @FXML
    public void handleMenuItemGerenciarLivro() throws IOException {
        AnchorPane x = (AnchorPane) FXMLLoader.load(getClass().getResource("/javafx/view/FXMLAnchorPaneCadastroLivro.fxml")); // load da tela 
        anchorPane.getChildren().setAll(x); // abertura da tela
    }

    @FXML
    public void handleMenuItemAlugarLivro() throws IOException {
        AnchorPane y = (AnchorPane) FXMLLoader.load(getClass().getResource("/javafx/view/FXMLAnchorPaneAlugarLivro.fxml")); // load da tela 
        anchorPane.getChildren().setAll(y); // abertura da tela
    }

    @FXML
    public void handleMenuItemGraficosAlugueisPorMes() throws IOException {
        AnchorPane z = (AnchorPane) FXMLLoader.load(getClass().getResource("/javafx/view/FXMLAnchorPaneGraficosAlugueisPorMes.fxml"));
        anchorPane.getChildren().setAll(z);
    }

    @FXML
    public void handleMenuItemOpcoesLogout() throws IOException {
        //AnchorPane h = (AnchorPane) FXMLLoader.load(getClass().getResource("/javafx/view/FXMLAnchorPaneLogin.fxml"));
        //anchorPane.getChildren().setAll(h);
        //menuItemOpcaoLogout.setGraphic(new ImageView(new javafx.Main.scene.image.Image(getClass(.getResourceAsStream("/src/imagem.png")))));
        menuItemOpcaoLogout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Stage stage = new Stage();
                Parent root = null;

                try {
                    root = FXMLLoader.load(getClass().getClassLoader().getResource("javafx/view/FXMLAnchorPaneLogin.fxml"));
                } catch (IOException ex) {
                    Logger.getLogger(FXMLAnchorPaneLoginController.class.getName()).log(Level.SEVERE, null, ex);
                }

                Scene scene = new Scene(root);

                stage.setScene(scene);
                stage.setResizable(false);
                stage.setTitle("Login");
                stage.show();
            }
        });
    }

    @FXML
    public void handleMenuItemGerenciarAluno() throws IOException {
        AnchorPane b = (AnchorPane) FXMLLoader.load(getClass().getResource("/javafx/view/FXMLAnchorPaneCadastroAluno.fxml"));
        anchorPane.getChildren().setAll(b);
    }

    @FXML
    public void handleMenuItemRegistrarDevolucao() throws IOException {
        AnchorPane p = (AnchorPane) FXMLLoader.load(getClass().getResource("/javafx/view/FXMLAnchorPaneRegistrarDevolucao.fxml"));
        anchorPane.getChildren().setAll(p);
    }

    @FXML
    public void handleMenuItemOpcaoSair() throws IOException {
        menuItemOpcaoSair.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int sair;

                sair = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja "
                        + "fechar o sistema?", "Atenção", JOptionPane.YES_NO_OPTION);
                if (sair == JOptionPane.YES_OPTION) {
                    System.exit(0);

                }
            }
        });
    }

    public class hora implements ActionListener {

        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            Calendar now = Calendar.getInstance();//pega a hora do sistema
            lblHora.setText(String.format("%1$tH:%1$tM:%1$tS", now)); //Hora, Minuto e Segundo (%1$tH:%1$tM:%1$tS)
        }
    }

}
