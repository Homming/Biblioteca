package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import vo.LivroVO;

public class LivroDAO implements ILivroDAO {
    
   
    @Override
    public void cadastro(LivroVO livroVO) {
        //IMPLEMENTADO SOMENTE PARA CORRIGIR O ERRO DE CLASSE ABSTRATA.
    }
    
    private ArrayList<LivroVO> livros;
    private Connection conexao;
    
    public LivroDAO(Connection conexao){
        this.conexao = conexao;
    }
    
    public void cadastrar(LivroVO livro) throws SQLException{
        Statement stmt = this.conexao.createStatement();
        stmt.executeUpdate("INSERT INTO livro(titulo, data_livro, cdd, cutter, complemento, autor1, assunto, autor2, local_livro, autor3, editora, tradutores, ano, ilustradores, edicao) VALUES "
                + "('"+livro.getTitulo()+"','"+livro.getData()+"','"+livro.getCdd()+"','"+livro.getCutter()+"','"+livro.getComplemento()+"','"+livro.getAutor1()+"','"+livro.getAutor2()+"','"+livro.getAutor3()+"','"+livro.getTradutores()+"','"+livro.getIlustradores()+"','"+livro.getAssunto()+"','"+livro.getLocal()+"','"+livro.getEditora()+"','"+livro.getAno()+"','"+livro.getEdicao()+"')");
        
    }
    
    public ResultSet consultarLivroPorTitulo(String titulo) throws SQLException{
        Statement stmt = this.conexao.createStatement();
        String sqlQuery = "SELECT * FROM Livro WHERE titulo = '"+titulo+"'";
        
        ResultSet rs = stmt.executeQuery(sqlQuery);
        
        return rs;
    }

   
    
}
