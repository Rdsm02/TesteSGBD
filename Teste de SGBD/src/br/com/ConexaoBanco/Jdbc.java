package br.com.ConexaoBanco;

import java.sql.SQLException;

public class Jdbc {

	public static void main(String[] args) throws SQLException {
		
		
		
		 System.out.println(ConexaoMySQL.statusConection());
		 
		 ConexaoMySQL.getConexaoMySQL();
		 
		 System.out.println(ConexaoMySQL.statusConection());
		 
		 Teste teste = new Teste();
		 
		 teste.mudar();
		 teste.listar();
		 


	}

}
