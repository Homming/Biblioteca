/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.IAluguelDAO;
import java.sql.SQLException;
import vo.AluguelVO;

/**
 *
 * @author Nicholas Torres
 */
public class AluguelDAOMock implements IAluguelDAO{

    @Override
    public boolean cadastrar(AluguelVO aluguelVO) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean alterar(AluguelVO aluguelVO) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean remover(AluguelVO aluguelVO) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
