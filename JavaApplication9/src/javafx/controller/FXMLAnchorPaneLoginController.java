package javafx.controller;

import dao.BibliotecariaDAO;
import database.Database;
import database.DatabaseFactory;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
    private final BibliotecariaDAO bibliotecariaDAO = new BibliotecariaDAO();


    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
