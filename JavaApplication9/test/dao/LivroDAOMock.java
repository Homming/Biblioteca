package dao;

import database.DatabaseMySQL;
import java.sql.Connection;
import java.time.LocalDate;
import junit.framework.TestCase;
import vo.LivroVO;

public class LivroDAOMock extends TestCase {

    LivroVO livro;
    Connection conexao;

    @Override
    protected void setUp() throws Exception {//EFETUADO ANTES DE CADA TESTE REALIZADO
        this.livro = new LivroVO();
        livro.setTitulo("Livro 1");
        livro.setData_livro(LocalDate.MIN);
        //livro.setData_livro(DataUtils.obterData(22, 1, 2018)); CONVERTER DATE PARA LOCALDATE
        livro.setAutor1("Autor 1");
        livro.setQuantidade_livro(2);

        DatabaseMySQL mysql = new DatabaseMySQL();
        this.conexao = mysql.conectar();
        this.conexao.setAutoCommit(false);
    }

    @Override
    protected void tearDown() throws Exception {//EFETUADO DEPOIS DE CADA TESTE REALIZADO
        this.conexao.close();
    }

    public void deveriaCadastrarLivro() throws Exception {
        LivroDAO livroDAO = new LivroDAO();
        livroDAO.setConnection(conexao);
        livroDAO.cadastrar(livro);
    }
}
