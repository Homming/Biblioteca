package javafx.controller;

import database.Database;
import database.DatabaseFactory;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class FXMLAnchorPaneLoginController implements Initializable {

    @FXML
    private TextField txtUser;
    @FXML
    private PasswordField txtPass;
    @FXML
    private Button btnEntrar;
    @FXML
    private Button btnCancelar;

    private final Database database = DatabaseFactory.getDatabase("mysql");
    private final Connection connection = database.conectar();
    //private final BibliotecariaDAO bibliotecariaDAO = new BibliotecariaDAO();

    PreparedStatement pstm = null;
    ResultSet res = null;

    @FXML
    private Label lblStatus;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        btnEntrar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String sql = "select * from bibliotecaria where usuario=? and senha=?";
                try {
                    pstm = connection.prepareStatement(sql);
                    pstm.setString(1, txtUser.getText());
                    pstm.setString(2, txtPass.getText());

                    res = pstm.executeQuery();
                    if (res.next()) {
                        Stage stage = new Stage();
                        Parent root = null;

                        try {
                            root = FXMLLoader.load(getClass().getClassLoader().getResource("javafx/view/FXMLVBoxMain.fxml"));
                        } catch (IOException ex) {
                            Logger.getLogger(FXMLAnchorPaneLoginController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        Scene scene = new Scene(root);//aparentemente está chegando aqui como null

                        stage.setScene(scene);
                        stage.setResizable(false);
                        stage.setTitle("Sistema de Gerenciamento de Biblioteca");
                        stage.show();

                        btnEntrar.getScene().getWindow().hide();// fecha a tela de login

                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Login");
                        alert.setHeaderText("Logado(a) com Sucesso!");
                        alert.show();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Login");
                        alert.setHeaderText("Usuário ou Senha Incorreta");
                        alert.show();
                    }
                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        }
        );// FIM ACTION ENTRAR

        btnCancelar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event
            ) {
                System.exit(0);
            }
        }
        );// FIM ACTION SAIR

        //Status de conexão com o BD
        if (connection != null) {
            lblStatus.setText("Online");
        } else {
            lblStatus.setText("Offline");
        }

    }// fim initialize

}// fim da classe
