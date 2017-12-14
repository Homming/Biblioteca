package vo;

import java.util.ArrayList;

public class AlunoVO {
    private int id_usuario;
    private String nome;
    private String fone;
    private String email;
    private String complemento;
    private String matricula;
    private String turma;
    private int qtd_permitido;
    private ArrayList<LivroVO> livros_alocados = new ArrayList<LivroVO>();

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
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

    public int getQtd_permitido() {
        return qtd_permitido;
    }

    public void setQtd_permitido(int qtd_permitido) {
        this.qtd_permitido = qtd_permitido;
    }

    public ArrayList<LivroVO> getLivros_alocados() {
        return livros_alocados;
    }

    public void setLivros_alocados(LivroVO livro) {
        this.livros_alocados.add(livro);
    }
    
    
}
