package bo;

import dao.IAlunoDAO;
import java.sql.SQLException;
import vo.AlunoVO;

public class AlunoBO {

    private AlunoVO alunoVo;
    private IAlunoDAO alunoDAO;
    public String errorMessage = "";

    public AlunoBO(IAlunoDAO alunoDAO, AlunoVO alunoVo) {
        this.alunoVo = alunoVo;
        this.alunoDAO = alunoDAO;
    }

    public boolean validarCadastroDeNome() {
        if (this.alunoVo.getNome().length() > 15 && !this.alunoVo.getNome().isEmpty() && alunoVo.getNome() != null) {
            return true;
        } else {
            this.errorMessage += "Nome Inválido! (Precisa ter mais que 15 caracteres)\n";
            return false;
        }
    }

    public boolean validarCadastroDeTelefone() {
        if (this.alunoVo.getTelefone().length() > 7 && !this.alunoVo.getTelefone().isEmpty() && alunoVo.getTelefone() != null) {
            return true;
        } else {
            this.errorMessage += "Telefone Inválido! (Precisa ter mais que 7 caracteres)\n";
            return false;
        }
    }

    public boolean validarCadastroDeComplemento() {
        if (this.alunoVo.getComplemento().length() > 8 && !this.alunoVo.getComplemento().isEmpty() && alunoVo.getComplemento() != null) {
            return true;
        } else {
            this.errorMessage += "Complemento Inválido! (Precisa ter mais que 8 caracteres)\n";
            return false;
        }
    }

    public boolean validarCadastroDeMatricula() {
        if (this.alunoVo.getMatricula().length() > 5 && !this.alunoVo.getMatricula().isEmpty() && alunoVo.getMatricula() != null) {
            return true;
        } else {
            this.errorMessage += "Matrícula Inválida! (Precisa ter mais que 5 caracteres e não pode se repetir)\n";
            return false;
        }
    }
    
    public boolean validarQtdMaxDeLivro() {
        if (this.alunoVo.getQtd_maxlivro() > 0) {
            return true;
        } else {
            this.errorMessage += "Qtd Máx Inválida! (é necessário permitir o aluguel de no mínimo Um Livro!)\n";
            return false;
        }
    }
    
    public boolean validarAlocaçãoDeLivro() {
        if (this.alunoVo.getQuantidade_alocados() < this.alunoVo.getQtd_maxlivro()) {
            return true;
        } else {
            this.errorMessage += "Alocação Não Permitida! (Limite do aluno excedido)\n";
            return false;
        }
    }

    public void cadastrarAluno() throws SQLException {
        if (!validarCadastroDeNome()) {
            throw new IllegalArgumentException("Nome de aluno invalido");
        } else {
            this.alunoDAO.cadastrar(this.alunoVo);
        }
    }

}
