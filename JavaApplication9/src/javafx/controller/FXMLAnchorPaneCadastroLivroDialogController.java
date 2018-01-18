package javafx.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import vo.LivroVO;
import bo.LivroBO;
import java.time.LocalDate;

public class FXMLAnchorPaneCadastroLivroDialogController implements Initializable {

    @FXML
    private Button btnConfirmar;
    @FXML
    private Button btnCancelar;
    @FXML
    private TextField txtTitulo;
    @FXML
    private DatePicker dtpData;
    @FXML
    private TextField txtQtdEstq;
    @FXML
    private TextField txtCDD;
    @FXML
    private TextField txtCutter;
    @FXML
    private TextField txtComplemento;
    @FXML
    private TextField txtAutor1;
    @FXML
    private TextField txtAutor2;
    @FXML
    private TextField txtAutor3;
    @FXML
    private TextField txtTradut;
    @FXML
    private TextField txtIlustrad;
    @FXML
    private TextField txtAssunto;
    @FXML
    private TextField txtLocal;
    @FXML
    private TextField txtEditora;
    @FXML
    private TextField txtAno;
    @FXML
    private TextField txtEdicao;

    private Stage dialogStage; //Atributo que representa a tela
    private boolean buttonConfirmarClicked = false; //Para saber em qual botão o usuário clicou , Confirmar/Cancelar
    private LivroVO livro;

    //Getters e Setters
    public Stage getDialogStage() {
        return dialogStage;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public boolean isButtonConfirmarClicked() {
        return buttonConfirmarClicked;
    }

    public void setButtonConfirmarClicked(boolean buttonConfirmarClicked) {
        this.buttonConfirmarClicked = buttonConfirmarClicked;
    }

    public LivroVO getLivro() {
        return livro;
    }

    public void setLivro(LivroVO livro) {
        this.livro = livro;
        //Caso não seja um livro Novo, set os valores do livro existente
        this.txtTitulo.setText(livro.getTitulo());
        this.dtpData.setValue(livro.getData_livro());
        this.txtQtdEstq.setText(String.valueOf(livro.getQuantidade_livro()));
        this.txtCDD.setText(livro.getCdd());
        this.txtCutter.setText(livro.getCutter());
        this.txtComplemento.setText(livro.getComplemento());
        this.txtAutor1.setText(livro.getAutor1());
        this.txtAutor2.setText(livro.getAutor2());
        this.txtAutor3.setText(livro.getAutor3());
        this.txtTradut.setText(livro.getTradutores());
        this.txtIlustrad.setText(livro.getIlustradores());
        this.txtAssunto.setText(livro.getAssunto());
        this.txtLocal.setText(livro.getLocal());
        this.txtEditora.setText(livro.getEditora());
        this.txtAno.setText(livro.getAno());
        this.txtEdicao.setText(livro.getEdicao());
    }

    @FXML
    public void handleButtonConfirmar() {
        String valTitulo = txtTitulo.getText();// guardando o valor que deseja validar em uma variavel
        LocalDate valData = dtpData.getValue();
        String valAutor1 = txtAutor1.getText();
        String valQtd = txtQtdEstq.getText();
        LivroBO validar = new LivroBO(); // instanciando a classe BO para chamar o método de validação

        //Lembrar de Experimentar enviar o objeto livro inteiro para validação no BO e a partir de lá efetuar o insert 
        if (validar.validarEntradaDeDados(valTitulo, valData, valAutor1, valQtd)) {// se todos os campos estiverem ok

            livro.setTitulo(txtTitulo.getText());
            livro.setData_livro(dtpData.getValue());
            livro.setQuantidade_livro(Integer.parseInt(txtQtdEstq.getText()));
            livro.setCdd(txtCDD.getText());
            livro.setCutter(txtCutter.getText());
            livro.setComplemento(txtComplemento.getText());
            livro.setAutor1(txtAutor1.getText());
            livro.setAutor2(txtAutor2.getText());
            livro.setAutor3(txtAutor3.getText());
            livro.setTradutores(txtTradut.getText());
            livro.setIlustradores(txtIlustrad.getText());
            livro.setAssunto(txtAssunto.getText());
            livro.setLocal(txtLocal.getText());
            livro.setEditora(txtEditora.getText());
            livro.setAno(txtAno.getText());
            livro.setEdicao(txtEdicao.getText());

            buttonConfirmarClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    public void handleButtonCancelar() {
        dialogStage.close();
    }

    /* VALIDAÇÃO DENTRO DA PROPRIA CLASSE AO INVES DO BO
    //Validação da entrada dos dados de cadastro de Usuário
    private boolean validarEntradaDeDados() {
        String errorMessage = "";
        
        if (txtTitulo.getText() == null || txtTitulo.getText().length() == 0) {
            errorMessage += "Título Inválido!\n";
        }
        if (txtAutor1.getText() == null || txtAutor1.getText().length() == 0) {
            errorMessage += "Autor Principal Inválido!\n";
        }
        
        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Falha no Cadastro!");
            alert.setHeaderText("Campos Inválidos, por favor, corrija...");
            alert.setContentText(errorMessage);
            alert.show();
            return false;
        }
    }
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
