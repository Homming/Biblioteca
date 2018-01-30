package bo;

import javafx.scene.control.Alert;
import vo.LivroVO;
import dao.ILivroDAO;
import java.sql.SQLException;

public class LivroBO {

    private ILivroDAO livroDAO;
    private LivroVO livroVO;
    public String errorMessage = "";

    public LivroBO() {

    }

    public LivroBO(ILivroDAO livroDAO, LivroVO livroVo) {
        this.livroDAO = livroDAO;
        this.livroVO = livroVo;
    }

    public boolean validarCadastroDeTitulo() {
        if (this.livroVO.getTitulo().length() > 4 && !this.livroVO.getTitulo().isEmpty() && this.livroVO.getTitulo() != null) {
            return true;
        } else {
            this.errorMessage += "Título Inválido! (Precisa ter mais que 4 caracteres)\n";
            return false;
        }
    }

    public boolean validarCadastroDeDataDeLivro() {
        //incompleto, pois poderá fornecer uma data bem errada ainda
        if (this.livroVO.getData_livro() != null) {
            return true;
        } else {
            this.errorMessage += "Data Inválida! (Não pode ficar vazia)\n";
            return false;
        }
    }

    public boolean validarCadastroDeQuantidadeDeLivro() {
        if (this.livroVO.getQuantidade_livro() > 0) {
            return true;
        } else {
            this.errorMessage += "Quantidade Inválida! (Precisa ter no mínimo Um livro)\n";
            return false;
        }
    }

    public boolean validarCadastroDeAutor() {
        if (this.livroVO.getAutor1().length() > 5 && !this.livroVO.getAutor1().isEmpty() && this.livroVO.getAutor1() != null) {
            return true;
        } else {
            this.errorMessage += "Autor1 Inválido! (Precisa ter mais que 5 caracteres)\n";
            return false;
        }
    }

    public boolean validarAlocaçãoDeLivro(/*AlunoVO alunoVo*/) {
        /*necessario ainda adicionar um parametro para verificar se o
        aluno ja alocou o mesmo livro e ainda nao devolveu*/

        if (this.livroVO.getQuantidade_livro() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean CadastroLivro() throws SQLException {
        return livroDAO.cadastrar(this.livroVO);
    }
    
    public boolean alteracaoLivro() throws SQLException{
        return livroDAO.editarCad(this.livroVO);
    }
    
    public boolean excluirLivro() throws SQLException{
        return livroDAO.excluirCad(this.livroVO);
    }
}
