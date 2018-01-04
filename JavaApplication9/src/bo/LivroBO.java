package bo;

import dao.ILivroDAO;
import javafx.scene.control.Alert;

public class LivroBO {
    private ILivroDAO livroDAO;
     
    public LivroBO(ILivroDAO livroDAO){
        this.livroDAO = livroDAO;
    }

    public LivroBO() {
       //Construtor requisitado
    }
    /*
    public void CadastroLivro(LivroVO livroVO) throws SQLException{
        if(livroVO.getTitulo().isEmpty() || livroVO.getTitulo()== null || livroVO.getData().isEmpty() || livroVO.getData()== null){
            throw new IllegalArgumentException("Erro ao cadastrar livro, título ou editora fora das regras ");
        }else{
            this.livroDAO.cadastro(livroVO);
        }
    }
    */
    
    //Validação da entrada dos dados de cadastro de Usuário
    public boolean validarEntradaDeDados(String txtTitulo) {
        String errorMessage = "";
        
        if (txtTitulo == null || txtTitulo.length() == 0) {
            errorMessage += "Título Inválido!\n";
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
