/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vo;

/**
 *
 * @author Nicholas Torres
 */
public class AluguelVO {
    private String data_aluguel;
    private int aluno_id;
    private int livro_id;
    private String data_devolução;
    private boolean devolvido;

    public String getData_aluguel() {
        return data_aluguel;
    }

    public void setData_aluguel(String data_aluguel) {
        this.data_aluguel = data_aluguel;
    }

    public int getAluno_id() {
        return aluno_id;
    }

    public int getLivro_id() {
        return livro_id;
    }

    public String getData_devolução() {
        return data_devolução;
    }

    public void setData_devolução(String data_devolução) {
        this.data_devolução = data_devolução;
    }

    public boolean isDevolvido() {
        return devolvido;
    }

    public void setAluno_id(int aluno_id) {
        this.aluno_id = aluno_id;
    }

    public void setLivro_id(int livro_id) {
        this.livro_id = livro_id;
    }

    public void setDevolvido(boolean devolvido) {
        this.devolvido = devolvido;
    }
    
}
