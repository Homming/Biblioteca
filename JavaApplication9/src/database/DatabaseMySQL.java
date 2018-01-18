package database;

import java.sql.*;
import java.util.Properties;

public class DatabaseMySQL implements Database {

	private static DatabaseMySQL instance;

	private static Connection conn;

	private Properties props = DbParams.getProp();

	private String host = props.getProperty("banco.mysql.servidor");
	private String login = props.getProperty("banco.mysql.usuario");
        private String password = props.getProperty("banco.mysql.senha");
        private String banco = props.getProperty("banco.mysql.banco");
        
	public DatabaseMySQL() {
            try {
               DatabaseMySQL.conn = DriverManager.getConnection("jdbc:mysql://"+this.host+"/"+this.banco, this.login, this.password);
                //"jdbc:mysql://localhost:3306/biblioteca?autoReconnect=true&useSSL=false";
            } catch(SQLException e){
               System.out.println("A Conexão Falhou!"+e.getMessage());
	}

	}

         // NAO FACO IDEIA DO QUE SEJA PUBLIC SYNCHRONIZED STATIC MAS FUNCIONA
	public synchronized static DatabaseMySQL getDatabase() throws SQLException {
		if (instance == null) {
                    instance = new DatabaseMySQL();
		}else if(conn.isClosed()) {
                    instance = new DatabaseMySQL();
		}
		return instance;

	}

	@Override
	public Connection conectar() {
		return DatabaseMySQL.conn;
	}

	@Override
	public void desconectar(Connection conn) {
		try {
			DatabaseMySQL.conn.close();
		} catch (SQLException e) {
                     System.out.println(e);
		}

	}

}
