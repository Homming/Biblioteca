package vo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

public class AluguelVO implements Serializable {

    private int id_aluguel;
    private LocalDate data_aluguel;
    private int aluno_id;
    private LocalDate data_devolucao;
    private List<ItemDeAluguelVO> itensDeAluguel;
    private LivroVO livro;
    private AlunoVO aluno;
    private boolean devolvido;
    private LocalDate data_devolvido;

    public AluguelVO(int id_aluguel, LocalDate data_aluguel, int aluno_id, int livro_id, LocalDate data_devolucao, boolean devolvido) {
        this.id_aluguel = id_aluguel;
        this.data_aluguel = data_aluguel;
        this.aluno_id = aluno_id;
        this.data_devolucao = data_devolucao;
        this.devolvido = devolvido;
    }

    public AluguelVO() {

    }

    public LocalDate getData_devolvido() {
        return data_devolvido;
    }

    public void setData_devolvido(LocalDate data_devolvido) {
        this.data_devolvido = data_devolvido;
    }

    public boolean isDevolvido() {
        return devolvido;
    }

    public void setDevolvido(boolean devolvido) {
        this.devolvido = devolvido;
    }

    public int getId_aluguel() {
        return id_aluguel;
    }

    public void setId_aluguel(int id_aluguel) {
        this.id_aluguel = id_aluguel;
    }

    public LocalDate getData_aluguel() {
        return data_aluguel;
    }

    public void setData_aluguel(LocalDate data_aluguel) {
        this.data_aluguel = data_aluguel;
    }

    public int getAluno_id() {
        return aluno_id;
    }

    public void setAluno_id(int aluno_id) {
        this.aluno_id = aluno_id;
    }

    public LocalDate getData_devolucao() {
        return data_devolucao;
    }

    public void setData_devolucao(LocalDate data_devolucao) {
        this.data_devolucao = data_devolucao;
    }

    public LivroVO getLivro() {
        return livro;
    }

    public void setLivro(LivroVO livro) {
        this.livro = livro;
    }

    public AlunoVO getAluno() {
        return aluno;
    }

    public void setAluno(AlunoVO aluno) {
        this.aluno = aluno;
    }

    public List<ItemDeAluguelVO> getItensDeAluguel() {
        return itensDeAluguel;
    }

    public void setItensDeAluguel(List<ItemDeAluguelVO> itensDeAluguel) {
        this.itensDeAluguel = itensDeAluguel;
    }
}
