package vo;

import java.util.ArrayList;

public class AlunoVO {
    private int id_aluno;
    private String nome;
    private String fone;
    private String email;
    private String complemento;
    private String matricula;
    private String turma;
    private int quantidade_alocados = 0;

    public int getId_aluno() {
        return id_aluno;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFone() {
        return fone;
    }

    public void setFone(String fone) {
        this.fone = fone;
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

    public int getQuantidade_alocados() {
        return quantidade_alocados;
    }

    public void setQuantidade_alocados() {
        // irá adicionar +1 cada vez que alocar o livro
        this.quantidade_alocados += 1;
    }

    
    
}
