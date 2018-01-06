package vo;

import java.io.Serializable;
import java.time.LocalDate;

public class AluguelVO implements Serializable{
    private int id_aluguel;
    private LocalDate data;
    private int aluno_id;
    private int livro_id;
    private LivroVO livro;
    private AlunoVO aluno;

    public AlunoVO getAluno() {
        return aluno;
    }

    public void setAluno(AlunoVO aluno) {
        this.aluno = aluno;
    }

    public LivroVO getLivro() {
        return livro;
    }

    public void setLivro(LivroVO livro) {
        this.livro = livro;
    }
    
    public AluguelVO(){
    
    }
    
    public AluguelVO(int id_aluguel, LocalDate data, int aluno_id, int livro_id) {
        this.aluno_id = aluno_id;
        this.data = data;
        this.aluno_id = aluno_id;
        this.livro_id = livro_id;
    }

    public int getId_aluguel() {
        return id_aluguel;
    }

    public void setId_aluguel(int id_aluguel) {
        this.id_aluguel = id_aluguel;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public int getAluno_id() {
        return aluno_id;
    }

    public void setAluno_id(int aluno_id) {
        this.aluno_id = aluno_id;
    }

    public int getLivro_id() {
        return livro_id;
    }

    public void setLivro_id(int livro_id) {
        this.livro_id = livro_id;
    }
    
    
}
