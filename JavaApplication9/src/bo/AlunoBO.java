/* COMMENT 1
package bo;

import dao.IAlunoDAO;
import java.sql.SQLException;
import vo.AlunoVO;
import vo.BibliotecariaVO;

public class AlunoBO {
    private AlunoVO alunoVo;
    private IAlunoDAO alunoDAO;
    private BibliotecariaVO bibliotecariaVo;
    
    public AlunoBO(AlunoVO alunoVo, IAlunoDAO alunoDAO, BibliotecariaVO bibliotecariaVo){
        this.alunoVo = alunoVo;
        this.alunoDAO = alunoDAO;
        this.bibliotecariaVo = bibliotecariaVo;
        
    }
    
    public boolean validarCadastroDeNome(){
        if(this.alunoVo.getNome().length() > 15 && !this.alunoVo.getNome().isEmpty() && alunoVo.getNome()!= null)
            return true;
        else
            return false;
    }
    
    public boolean validarCadastroDeTelefone(){
        if(this.alunoVo.getFone().length() > 8 && !this.alunoVo.getFone().isEmpty() && alunoVo.getFone()!= null)
            return true;
        else
            return false;
    }
    
    public boolean validarCadastroDeComplemento(){
        if(this.alunoVo.getComplemento().length() > 8 && !this.alunoVo.getComplemento().isEmpty() && alunoVo.getComplemento()!= null)
            return true;
        else
            return false;
    }
    
    public boolean validarCadastroDeMatricula(){
        /* Ideia para que o objeto Bibliotecaria tenha todas as matricuals dos alunos, 
            e verifique num foreach se uma das matriculas bate com a fornecida*/
/*COMMENT 2
        if(this.alunoVo.getMatricula().length() > 5 && !this.alunoVo.getMatricula().isEmpty() && alunoVo.getMatricula()!= null)
            return true;
        else
            return false;
    }
    
    public boolean validarAlocaçãoDeLivro(){
        //incompleto
        if(this.alunoVo.getQuantidade_alocados() < this.bibliotecariaVo.getQuantidade_possivel())
            return true;
        else 
            return false;
    }
    
    public void cadastrarAluno()throws SQLException{
        if(!validarCadastroDeNome())
            throw new IllegalArgumentException("Nome de aluno invalido");
        else
            this.alunoDAO.cadastrar(this.alunoVo);
                
    }
}

*/