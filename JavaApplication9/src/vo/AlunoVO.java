package vo;

import java.util.ArrayList;

public class AlunoVO {

    private int id_aluno;
    private String nome = "";
    private int quantidade_alocados = 0;
    private int qtd_maxlivro = 0;
    private String telefone = "";
    private String email = "";
    private String complemento = "";
    private String matricula = "";
    private String turma = "";
    private ArrayList<LivroVO> livros_alocados = new ArrayList<LivroVO>();

    public int getId_aluno() {
        return id_aluno;
    }

    public void setId_aluno(int id_aluno) {
        this.id_aluno = id_aluno;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade_alocados() {
        return quantidade_alocados;
    }

    public void setQuantidade_alocados(int quantidade_alocados) {
        this.quantidade_alocados = quantidade_alocados;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getTurma() {
        return turma;
    }

    public void setTurma(String turma) {
        this.turma = turma;
    }

    public int getQtd_maxlivro() {
        return qtd_maxlivro;
    }

    public void setQtd_maxlivro(int qtd_maxlivro) {
        this.qtd_maxlivro = qtd_maxlivro;
    }

    public ArrayList<LivroVO> getLivros_alocados() {
        return livros_alocados;
    }

    public void setLivros_alocados(ArrayList<LivroVO> livros_alocados) {
        this.livros_alocados = livros_alocados;
    }

    public String toString() {
        return this.nome;
    }

}
