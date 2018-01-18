/* COMMENT 1

package bo;

import dao.ILivroDAO;
import java.sql.SQLException;
import vo.AlunoVO;
import vo.LivroVO;

public class LivroBO {
    private ILivroDAO livroDAO;
    private LivroVO livroVo;
    
    public LivroBO(ILivroDAO livroDAO, LivroVO livroVo){
        this.livroDAO = livroDAO;
        this.livroVo = livroVo;
    }
    
    public boolean validarCadastroDeTitulo(){
        if(this.livroVo.getTitulo().length() > 4 && !this.livroVo.getTitulo().isEmpty() && this.livroVo.getTitulo()!= null)
            return true;
        else
            return false;
    }
    
    public boolean validarCadastroDeDataDeLivro(){
        //incompleto, pois poderá fornecer uma data bem errada ainda
        if(this.livroVo.getData().length() > 9 && !this.livroVo.getData().isEmpty() && this.livroVo.getData()!= null)
            return true;
        else
            return false;
    }
    
    public boolean validarCadastroDeQuantidadeDeLivro(){
        if(this.livroVo.getQuantidade_livros() > 0)
            return true;
        else
            return false;
    }
    
    public boolean validarCadastroDeAutor(){
         if(this.livroVo.getAutor1().length() > 15 && !this.livroVo.getAutor1().isEmpty() && this.livroVo.getAutor1()!= null)
            return true;
        else
            return false;
    }
    
    public boolean validarAlocaçãoDeLivro(AlunoVO alunoVo){
        /*necessario ainda adicionar um parametro para verificar se o
        aluno ja alocou o mesmo livro e ainda nao devolveu*/
 /* COMMENT 2
        if(this.livroVo.getQuantidade_livros() > 1)
            return true;
        else
            return false;
    }
    
    public void CadastroLivro(LivroVO livroVO) throws SQLException{
        if(livroVO.getTitulo().isEmpty() || livroVO.getTitulo()== null || livroVO.getData().isEmpty() || livroVO.getData()== null){
            throw new IllegalArgumentException("Erro ao cadastrar livro, título ou editora fora das regras ");
        }else{
            this.livroDAO.cadastrar(livroVO);
        }
    }
    
}

 */
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
