package dao;

import java.sql.Connection;
import java.sql.SQLException;
import vo.AluguelVO;

public interface IAluguelDAO {

    //Obrigando a implementação dos seguintes métodos
    public boolean cadastrar(AluguelVO aluguelVO) throws SQLException;

    public boolean remover(AluguelVO aluguelVO) throws SQLException;

    public void setConnection(Connection connection);

 

}
