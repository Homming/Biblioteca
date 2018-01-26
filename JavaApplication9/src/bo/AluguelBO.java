package bo;

import dao.IAluguelDAO;
import java.sql.SQLException;
import vo.AluguelVO;

public class AluguelBO {
    //fiz alguamas mudanças no BO pra ficar tudo bem com seu VO

    private AluguelVO aluguelVO;
    private IAluguelDAO aluguelDAO;
    public String errorMessage = "";
    
    public AluguelBO(IAluguelDAO aluguelDAO, AluguelVO aluguelVO) {
        this.aluguelVO = aluguelVO;
        this.aluguelDAO = aluguelDAO;
    }
    
    public boolean validarDataDeAluguel() {
        //incompleto, pois poderá fornecer uma data bem errada ainda
        if (this.aluguelVO.getData_aluguel()!= null) {
            return true;
        } else {
            this.errorMessage += "Data de Aluguel Inválida! (Não pode ficar vazia)\n";
            return false;
        }
    }
    
    public boolean validarDataDeDevolucao() {
        //incompleto, pois poderá fornecer uma data bem errada ainda
        if (this.aluguelVO.getData_devolucao()!= null) {
            return true;
        } else {
            this.errorMessage += "Data de Devolução Inválida! (Não pode ficar vazia)\n";
            return false;
        }
    }

    public boolean inserindoAluno() {
        if (this.aluguelVO.getAluno_id() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean inserindoLivro() {
        if (this.aluguelVO.getLivro_id() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void CadastroAluguel() throws SQLException {
        if (!inserindoLivro() && !inserindoAluno()) {
            throw new IllegalArgumentException("Erro ao cadastrar aluguel, aluno ou livro com problemas ");
        } else {
            this.aluguelDAO.cadastrar(this.aluguelVO);
        }
    }
}
