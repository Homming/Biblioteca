package builders;

import vo.BibliotecariaVO;

public class BibliotecariaBuilder {

    private BibliotecariaVO usuario;

    //Comentários relativos em LivroBuilder
    private BibliotecariaBuilder() {
    }

    public static BibliotecariaBuilder umUsuario() {
        BibliotecariaBuilder builder = new BibliotecariaBuilder();
        builder.usuario = new BibliotecariaVO();
        builder.usuario.setNome("Usuario 1");

        return builder;
    }

    public BibliotecariaVO agora() {
        return usuario;
    }

}
