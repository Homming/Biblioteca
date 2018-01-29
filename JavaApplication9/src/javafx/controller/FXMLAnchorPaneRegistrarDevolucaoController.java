package javafx.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;

public class FXMLAnchorPaneRegistrarDevolucaoController implements Initializable {

    @FXML
    private DatePicker dtpDevolucao;
    @FXML
    private CheckBox chbDevolvido;
    @FXML
    private Button btnConfirmar;
    @FXML
    private Button btnCancelar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
          
    }

    @FXML
    private void handleButtonConfirmar(ActionEvent event) {
    }

    @FXML
    private void handleButtonCancelar(ActionEvent event) {
    }

}
