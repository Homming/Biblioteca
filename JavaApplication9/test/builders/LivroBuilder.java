package builders;

import vo.LivroVO;

public class LivroBuilder {

    private LivroVO livro;

    //privado para que ninguem crie instancias externas ao builder
    private LivroBuilder() {
    }

    public static LivroBuilder umLivro() {
        LivroBuilder builder = new LivroBuilder();
        builder.livro = new LivroVO();
        builder.livro.setQuantidade_livro(2);
        builder.livro.setTitulo("Livro 1");

        return builder;
    }//publico para que possa ser acessado externamente sem necessidade de ser instanciado

    public static LivroBuilder umLivroSemEstoque() {
        LivroBuilder builder = new LivroBuilder();
        builder.livro = new LivroVO();
        builder.livro.setQuantidade_livro(0);
        builder.livro.setTitulo("Livro 1");

        return builder;
    }

    //Para casos específicos
    public LivroBuilder semEstoque() {
        livro.setQuantidade_livro(0);
        return this;
    }

    public LivroVO agora() {
        return livro;//retorna o livro criado
    }

}
