package bo;

import dao.ILivroDAO;
import java.sql.SQLException;
import vo.LivroVO;

public class LivroBO {
    private ILivroDAO livroDAO;
     
    public LivroBO(ILivroDAO livroDAO){
        this.livroDAO = livroDAO;
    }
    
    public void CadastroLivro(LivroVO livroVO) throws SQLException{
        if(livroVO.getTitulo().isEmpty() || livroVO.getTitulo()== null || livroVO.getData().isEmpty() || livroVO.getData()== null){
            throw new IllegalArgumentException("Erro ao cadastrar livro, título ou editora fora das regras ");
        }else{
            this.livroDAO.cadastro(livroVO);
        }
    }
    
}
