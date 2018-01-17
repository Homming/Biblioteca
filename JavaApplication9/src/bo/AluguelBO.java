package bo;

import dao.IAluguelDAO;
import java.sql.SQLException;
import vo.AluguelVO;
import vo.AlunoVO;
import vo.LivroVO;

public class AluguelBO {

    private AlunoVO alunoVo;
    private LivroVO livroVo;
    private IAluguelDAO aluguelDAO;

    public AluguelBO(AlunoVO alunoVo, LivroVO livroVo, IAluguelDAO aluguelDAO) {
        this.alunoVo = alunoVo;
        this.livroVo = livroVo;
        this.aluguelDAO = aluguelDAO;
    }

    public boolean inserindoAluno(AluguelVO aluguelVo) {
        if (this.alunoVo != null) {
            aluguelVo.setAluno_id(this.alunoVo.getId_aluno());
            return true;
        } else {
            return false;
        }
    }

    public boolean inserindoLivro(AluguelVO aluguelVo) {
        if (this.livroVo != null) {
            aluguelVo.setAluno_id(this.livroVo.getId_livro());
            return true;
        } else {
            return false;
        }
    }

    public void CadastroAluguel(AluguelVO aluguelVo) throws SQLException {
        if (!inserindoLivro(aluguelVo) && !inserindoAluno(aluguelVo)) {
            throw new IllegalArgumentException("Erro ao cadastrar aluguel, aluno ou livro com problemas ");
        } else {
            this.aluguelDAO.cadastrar(aluguelVo);
        }
    }
}
