package bo;

import dao.IAluguelDAO;
import java.sql.SQLException;
import vo.AluguelVO;
import vo.AlunoVO;
import vo.LivroVO;

public class AluguelBO {
    //fiz alguamas mudanças no BO pra ficar tudo bem com seu VO

    private AluguelVO aluguelVO;
    private IAluguelDAO aluguelDAO;

    public AluguelBO(AluguelVO aluguelVO, IAluguelDAO aluguelDAO) {
        this.aluguelVO = aluguelVO;
        this.aluguelDAO = aluguelDAO;
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
}//NICOLAS