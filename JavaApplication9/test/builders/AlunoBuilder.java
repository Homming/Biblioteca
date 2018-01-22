package builders;

import vo.AlunoVO;

public class AlunoBuilder {

    private AlunoVO aluno;

    //Comentários relativos em LivroBuilder
    private AlunoBuilder() {
    }

    public static AlunoBuilder umAluno() {
        AlunoBuilder builder = new AlunoBuilder();
        builder.aluno = new AlunoVO();
        builder.aluno.setNome("Aluno 1");

        return builder;
    }

    public AlunoVO agora() {
        return aluno;
    }

}
