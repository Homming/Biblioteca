package javafx.controller;

import bo.AluguelBO;
import dao.AluguelDAO;
import database.Database;
import database.DatabaseFactory;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import vo.AluguelVO;

public class FXMLAnchorPaneDevolverLivroDialogController implements Initializable {

    @FXML
    private Button btnConfirmar;
    @FXML
    private Button btnCancelar;
    @FXML
    private CheckBox chbDevolvido;
    @FXML
    private DatePicker dtpDevolucao;

    private final Database database = DatabaseFactory.getDatabase("mysql");
    private final Connection connection = database.conectar();

    private Stage dialogStage; //Atributo que representa a tela
    private boolean buttonConfirmarClicked = false; //Para saber em qual botão o usuário clicou , Confirmar/Cancelar
    private AluguelVO aluguel;

    //Getters e Setters
    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public AluguelVO getAluguel() {
        return aluguel;
    }

    public void setAluguelVO(AluguelVO aluguel) {
        this.aluguel = aluguel;
        //para aluguel ja existente
        this.chbDevolvido.setSelected(aluguel.isDevolvido());
        this.dtpDevolucao.setValue(aluguel.getData_devolvido());
    }

    public boolean isButtonConfirmarClicked() {
        return buttonConfirmarClicked;
    }

    public void setButtonConfirmarClicked(boolean buttonConfirmarClicked) {
        this.buttonConfirmarClicked = buttonConfirmarClicked;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    public void handleButtonConfirmar() {
        aluguel.setDevolvido(chbDevolvido.isSelected());
        aluguel.setData_devolvido(dtpDevolucao.getValue());

        AluguelDAO aluguelDAO = new AluguelDAO();
        AluguelBO validar = new AluguelBO(aluguelDAO, this.aluguel);

        validar.validarDataDeDevolucao();

        if (validar.errorMessage.length() == 0) {
            buttonConfirmarClicked = true;
            dialogStage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Falha no Cadastro!");
            alert.setHeaderText("Campos Inválidos, por favor, corrija...");
            alert.setContentText(validar.errorMessage);
            alert.show();
        }

    }

    @FXML
    public void handleButtonCancelar() {
        dialogStage.close();
    }

}
