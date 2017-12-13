package dao;

import java.sql.Connection;

public interface IConexao {
	Connection getConexao() throws Exception; 
}
