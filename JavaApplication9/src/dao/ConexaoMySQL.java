package dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class ConexaoMySQL implements IConexao{
    private String login;
    private String host;
    private String password;
    private String banco;
    
    public static Properties getProp() throws IOException{
        Properties props = new Properties();
        FileInputStream file = new FileInputStream("./database.properties");
        props.load(file);
        return props;
    }
    
    public void setProperties() throws IOException{
        Properties prop = getProp();
        
        this.login = prop.getProperty("banco.mysql.usuario");
	this.host = prop.getProperty("banco.mysql.servidor");
	this.password = prop.getProperty("banco.mysql.senha");
	this.banco = prop.getProperty("banco.mysql.banco");
    }
    
    @Override
    public Connection getConexao() throws Exception{
        Connection conexao = null;
        
        try{
            setProperties();
        }catch(IOException e){
            System.out.println("Erro no arquivo"+e.getMessage());
        }
        
        try{
            conexao = DriverManager.getConnection("jdbc:mysql://"+this.host+"/"+this.banco, this.login, this.password);
            //"jdbc:mysql://localhost:3306/biblioteca?autoReconnect=true&useSSL=false";
        }catch(SQLException e){
            System.out.println("A Conexão Falhou!"+e.getMessage());
        }
        
        return conexao;
    }
    
    
    
    
    
}
