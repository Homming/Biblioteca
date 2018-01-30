package bo;

import dao.ILivroDAO;
import dao.IAlunoDAO;
import dao.IBibliotecariaDAO;
import java.sql.SQLException;
import vo.BibliotecariaVO;

public class BibliotecariaBO {

    private ILivroDAO livroDAO;
    private String senha;
    private IAlunoDAO usuarioDAO;
    private IBibliotecariaDAO bibliotecariaDAO;
    private BibliotecariaVO bibliotecariaVo;
    public String errorMessage = "";

    public BibliotecariaBO() {

    }

    public BibliotecariaBO(IBibliotecariaDAO bibliotecariaDAO, BibliotecariaVO bibliotecariaVo) {
        this.bibliotecariaDAO = bibliotecariaDAO;
        this.bibliotecariaVo = bibliotecariaVo;
    }

    public boolean validarCadastroDeNome() {
        if (this.bibliotecariaVo.getNome().length() > 5 && !this.bibliotecariaVo.getNome().isEmpty() && this.bibliotecariaVo.getNome() != null) {
            return true;
        } else {
            this.errorMessage += "Nome Inválido! (Precisa ter mais que 5 caracteres)\n";
            return false;
        }
    }

    public boolean validarCadastroDeCelular() {
        if (this.bibliotecariaVo.getCel().length() > 7 && !this.bibliotecariaVo.getCel().isEmpty() && this.bibliotecariaVo.getCel() != null) {
            return true;
        } else {
            this.errorMessage += "Celular Inválido! (Precisa ter mais que 7 caracteres)\n";
            return false;
        }
    }

    public boolean validarCadastroDeUsuario() {
        if (this.bibliotecariaVo.getUsuario().length() > 4 && !this.bibliotecariaVo.getUsuario().isEmpty() && this.bibliotecariaVo.getUsuario() != null) {
            return true;
        } else {
            this.errorMessage += "Usuário Inválido! (Precisa ter mais que 4 caracteres)\n";
            return false;
        }
    }

    public boolean validarCadastroDeSenha() {
        if (this.bibliotecariaVo.getSenha().equals(this.bibliotecariaVo.getConf_senha()) && this.bibliotecariaVo.getSenha() != null && this.bibliotecariaVo.getSenha().length() > 5 && !this.bibliotecariaVo.getSenha().isEmpty()) {
            return true;
        } else {
            this.errorMessage += "Senha Inválido! (Senha precisa ser igual ao confirma senha)\n (Senha precisa ter mais que 5 caracteres)";
            return false;
        }
    }

    public boolean cadastrarBibliotecaria() throws SQLException {
        return this.bibliotecariaDAO.cadastrar(this.bibliotecariaVo);

    }
    
    public boolean editarBibliotecaria()throws SQLException {
        return this.bibliotecariaDAO.editar(this.bibliotecariaVo);

    }
    
    public boolean excluirBibliotecaria()throws SQLException {
        return this.bibliotecariaDAO.excluirCad(this.bibliotecariaVo);

    }

}
