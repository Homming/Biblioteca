package bo;

import dao.ILivroDAO;
import java.sql.SQLException;
import java.time.LocalDate;
import javafx.scene.control.Alert;
import vo.LivroVO;

public class LivroBO {

    private ILivroDAO livroDAO;
    private LivroVO livroVO;

    public LivroBO() {
        
    }

    public LivroBO(ILivroDAO livroDAO, LivroVO livro) {
        this.livroDAO = livroDAO;
        this.livroVO = livro;
    }

    public boolean validarCadastroDeTitulo() throws SQLException {
        String errorMessage = "";
        
        if (this.livroVO.getTitulo() != null) {
            livroDAO.cadastrar(this.livroVO);
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

    //Validação da entrada dos dados de cadastro de Usuário
    public boolean validarEntradaDeDados(String txtTitulo, LocalDate dtpData, String txtAutor1, String txtQtd) {
        String errorMessage = "";

        if (txtTitulo == null || txtTitulo.length() == 0) {
            errorMessage += "Título Inválido!\n";
        }

        if (dtpData == null) {
            errorMessage += "Data Inválida!\n";
        }

        if (txtAutor1 == null || txtAutor1.length() == 0) {
            errorMessage += "Autor Principal inválido!\n";
        }

        if (txtQtd == null || txtQtd.length() == 0) {
            errorMessage += "Quantidade de livros inválida!\n";
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

}
