package vo;

import java.io.Serializable;

public class ItemDeAluguelVO implements Serializable {

    private int id_itemdealuguel;
    private int quantidade;
    private int livro_id;
    private int aluguel_id;
    private LivroVO livro;
    private AluguelVO aluguel;

    public ItemDeAluguelVO() {
    }

    public int getId_itemdealuguel() {
        return id_itemdealuguel;
    }

    public void setId_itemdealuguel(int id_itemdealuguel) {
        this.id_itemdealuguel = id_itemdealuguel;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getLivro_id() {
        return livro_id;
    }

    public void setLivro_id(int livro_id) {
        this.livro_id = livro_id;
    }

    public int getAluguel_id() {
        return aluguel_id;
    }

    public void setAluguel_id(int aluguel_id) {
        this.aluguel_id = aluguel_id;
    }

    public LivroVO getLivro() {
        return livro;
    }

    public void setLivro(LivroVO livro) {
        this.livro = livro;
    }

    public AluguelVO getAluguel() {
        return aluguel;
    }

    public void setAluguel(AluguelVO aluguel) {
        this.aluguel = aluguel;
    }
    
    @Override
    public String toString(){
        return this.livro.getTitulo();
    }

}
