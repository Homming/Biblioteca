package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import vo.LivroVO;

public class LivroDAO implements ILivroDAO {
    
  
    
    private ArrayList<LivroVO> livros;
    private Connection conexao;
    
    public LivroDAO(Connection conexao){
        this.conexao = conexao;
    }
    
    public void cadastrar(LivroVO livroVO) throws SQLException{
        Statement stmt = this.conexao.createStatement();
        stmt.executeUpdate("INSERT INTO livro(titulo, data_livro, cdd, cutter, complemento, autor1, assunto, autor2, local_livro, autor3, editora, tradutores, ano, ilustradores, edicao) VALUES "
                + "('"+livroVO.getTitulo()+"','"+livroVO.getData()+"','"+livroVO.getCdd()+"','"+livroVO.getCutter()+"','"+livroVO.getComplemento()+"','"+livroVO.getAutor1()+"','"+livroVO.getAutor2()+"','"+livroVO.getAutor3()+"','"+livroVO.getTradutores()+"','"+livroVO.getIlustradores()+"','"+livroVO.getAssunto()+"','"+livroVO.getLocal()+"','"+livroVO.getEditora()+"','"+livroVO.getAno()+"','"+livroVO.getEdicao()+"')");
        
    }
    
    public ResultSet consultarLivroPorTitulo(String titulo) throws SQLException{
        Statement stmt = this.conexao.createStatement();
        String sqlQuery = "SELECT * FROM Livro WHERE titulo = '"+titulo+"'";
        
        ResultSet rs = stmt.executeQuery(sqlQuery);
        
        return rs;
    }

    @Override
    public void excluir(LivroVO livroVO) throws SQLException {
        // CRIADO PRA TIRAR O ERRO DA CLASSE ABSTRATA
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void alterar(LivroVO livroVO) throws SQLException {
        // CRIADO PRA TIRAR O ERRO DA CLASSE ABSTRATA
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    
}
