/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import vo.AluguelVO;
import vo.AlunoVO;

/**
 *
 * @author Nicholas Torres
 */
public interface IAluguelDAO {
    public void cadastrar(AluguelVO aluguelVO) throws SQLException;
    public void excluir(AluguelVO aluguelVO) throws SQLException;
    public void alterar(AluguelVO aluguelVO) throws SQLException;
}
