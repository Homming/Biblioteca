/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testes;

import dao.IBibliotecariaDAO;
import java.sql.SQLException;
import vo.BibliotecariaVO;

/**
 *
 * @author nicholas1994
 */
public class BibliotecariaDAOMock implements IBibliotecariaDAO {

    @Override
    public boolean cadastrar(BibliotecariaVO bibliotecariaVO) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean editar(BibliotecariaVO bibliotecariaVO) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean excluirCad(BibliotecariaVO bibliotecariaVO) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
