package br.com.ConexaoBanco;

import java.sql.Connection;

import java.sql.DriverManager;
 
import java.sql.SQLException;


public class ConexaoMySQL {
	
	public static String status = "N�o conectou...";
	 
	//M�todo Construtor da Classe//
	 
	        public ConexaoMySQL() {
	 
	    }
	 
	  
	 
	//M�todo de Conex�o//
	 
	public static java.sql.Connection getConexaoMySQL() {
	 
	        Connection connection = null;          //atributo do tipo Connection
	 
	  
	 
	try {
	 
	// Carregando o JDBC Driver padr�o
	 
		String driverName = "com.mysql.cj.jdbc.Driver";
//	String driverName = "com.mysql.jdbc.Driver";  
	//com.mysql.cj.jdbc.Driver
		//jdbc:mysql://127.0.0.1:3306/?user=root
	 
	Class.forName(driverName);
	 
	  
	 
	// Configurando a nossa conex�o com um banco de dados//
	 
	            String serverName = "127.0.0.1:3306";    //caminho do servidor do BD
	
	            //jdbc:mysql://127.0.0.1:3306/?user=root
//				String serverName = "127.0.0.1";
	            ///Teste de SGBD/libs/mysql-connector-java-8.0.12.jar
	            //jdbc:mysql://localhost:3306/?user=root
	 
	            String mydatabase = "cadastro";        //nome do seu banco de dados
	            
	            //root@127.0.0.1:3306
//	            String mydatabase = "127.0.0.1";
	 
	            String url = "jdbc:mysql://"+ serverName + "/" + mydatabase + "?user=root&useTimezone=true&serverTimezone=UTC";
//	            String url = "jdbc:mysql://" + serverName + "/" + mydatabase + "?autoReconnect=true&useSSL=false";
	            
	 
	            String username = "root";        //nome de um usu�rio de seu BD      
	 
	            String password = "";      //sua senha de acesso
	 
	            connection = DriverManager.getConnection(url, username, password);
	            
	            
	            
//	            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/teste", "root", "LCPI28@!");
	            
//	            jdbc:mysql://localhost:3306/?user=root
	 
	  
	 
	            //Testa sua conex�o//  
	 
	            if (connection != null) {
	 
	                status = ("STATUS--->Conectado com sucesso!");
	 
	            } else {
	 
	                status = ("STATUS--->N�o foi possivel realizar conex�o");
	 
	            }
	 
	  
	 
	            return connection;
	 
	  
	 
	        } catch (ClassNotFoundException e) {  //Driver n�o encontrado
	 
	  
	 
	            System.out.println("O driver expecificado nao foi encontrado.");
	            System.out.println(e);
	 
	            return null;
	 
	        } catch (SQLException e) {
	 
	//N�o conseguindo se conectar ao banco
	 
	            System.out.println("Nao foi possivel conectar ao Banco de Dados.");
	            System.out.println(e);
	 
	            return null;
	 
	        }
	 
	  
	 
	    }
	 
	  
	 
	    //M�todo que retorna o status da sua conex�o//
	 
	    public static String statusConection() {
	 
	        return status;
	 
	    }
	 
	   
	 
	   //M�todo que fecha sua conex�o//
	 
	    public static boolean FecharConexao() {
	 
	        try {
	 
	            ConexaoMySQL.getConexaoMySQL().close();
	 
	            return true;
	 
	        } catch (SQLException e) {
	 
	            return false;
	 
	        }
	 
	  
	 
	    }
	 
	   
	 
	   //M�todo que reinicia sua conex�o//
	 
	    public static java.sql.Connection ReiniciarConexao() {
	 
	        FecharConexao();
	 
	  
	 
	        return ConexaoMySQL.getConexaoMySQL();
	 
	    }


		
	 
}



