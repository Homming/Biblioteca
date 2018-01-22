/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dao.IAlunoDAO;
import java.sql.SQLException;
import vo.AlunoVO;

/**
 *
 * @author nicholas1994
 */
public class AlunoDAOMock implements IAlunoDAO {

    @Override
    public boolean cadastrar(AlunoVO alunoVO) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean editar(AlunoVO alunoVO) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean remover(AlunoVO alunoVO) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    
}
