package bo;

import dao.ILivroDAO;
import dao.LivroDAO;
import vo.LivroVO;

public class LivroBO {
    private ILivroDAO livroDAO;
     
    public LivroBO(ILivroDAO livroDAO){
        this.livroDAO = livroDAO;
    }
    
    public void CadastroLivro(LivroVO livroVO){
        if(livroVO.getNome().isEmpty() || livroVO.getNome() == null || livroVO.getSetor().isEmpty() || livroVO.getSetor() == null){
            throw new IllegalArgumentException("Erro ao cadastrar livro, nome ou setor fora das regras ");
        }else{
            this.livroDAO.cadastro(livroVO);
        }
    }
    
}
