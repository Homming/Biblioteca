package vo;

import java.io.Serializable;

public class ItemDeAluguelVO implements Serializable {

    private int id_itemdealuguel;
    private int quantidade;
    private double valor;
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

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
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

}
