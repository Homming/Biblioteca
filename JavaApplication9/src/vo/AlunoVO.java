package vo;

import java.util.ArrayList;

public class AlunoVO {
    private int id_aluno;
    private String nome;
    private String telefone;
    private String email;
    private String complemento;
    private String matricula;
    private String turma;
    private int qtd_permitido;
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    
    
    
}
