package br.com.ConexaoBanco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Teste {
	
	Connection connection = ConexaoMySQL.getConexaoMySQL();
	
	public void mudar(){
		 
//		 ConexaoMySQL conexao = new ConexaoMySQL();
		 
		 
		 String sql = "insert into pessoas(id, nome, nascimento, sexo, peso, altura, nacionalidade)"
		 		+ "values(default, ?, ?, ?, ?, ?, default);";
		 
		 PreparedStatement statement;
		try {
			statement = connection.prepareStatement(sql);
//			statement.setInt(1, 0);
			statement.setString(1, "wagner");
			statement.setString(2, "1920-12-30");
			statement.setString(3, "F");
			statement.setString(4, "50.2");
			statement.setString(5, "1.83");
//			statement.setString(7, "");
			statement.execute();
//			connection.close();
			 
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		 
		 
		 
//		 Statement statement = conexao.createStatement();
//        statement.executeUpdate("Insert into numeros values(1,5,7)");
      
		 
		 }
	
	public void listar(){
		
		
		try {
			String sql = "select * from pessoas;";
//			String sql = "select id, nome, nascimento, sexo, peso, altura, nacionalidade from pessoas order by id;";			 
			PreparedStatement smtp = connection.prepareStatement(sql);
			ResultSet rs = smtp.executeQuery();
//			DefaultTableModel modelo = testando.
			String vetor[] = new String[60];
			int cont = 0;
			while(rs.next()){
				String id = rs.getString("id");
				String nome = rs.getString("nome");
				String nascimento = rs.getString("nascimento");
				String sexo = rs.getString("sexo");
				String peso = rs.getString("peso");
				String altura = rs.getString("altura");
				String nacionalidade = rs.getString("nacionalidade");
				vetor[cont] = id + " " + nome + " " +  nascimento + " " +  sexo + " " +  peso + " " +  altura + " " +  nacionalidade;
				cont++;
				
//				JOptionPane.showMessageDialog(null, id + " " + nome + " " +  nascimento + " " +  sexo + " " +  peso + " " +  altura + " " +  nacionalidade, "Teste de SGBD...", JOptionPane.INFORMATION_MESSAGE, null);
//				System.out.println(id + " " + nome + " " +  nascimento + " " +  sexo + " " +  peso + " " +  altura + " " +  nacionalidade);
				
//			System.out.println(new Object[] {, rs.getString("nome"), 
//					rs.getString("nascimento"), rs.getString("sexo"), rs.getString("peso"), 
//					rs.getString("altura"), rs.getString("nacionalidade")});
			}
			
			JOptionPane.showMessageDialog(null, vetor[0]+ vetor[1]+ vetor[2]+ vetor[3]+ vetor[4]+ vetor[5]+ vetor[6]+ vetor[7]+ 
					vetor[8]+ vetor[9]+ vetor[10]+ vetor[11]+ vetor[12]+ vetor[13]+ vetor[14]+ vetor[15]+ vetor[16]+ vetor[17]+ 
					vetor[18]+ vetor[19]+ vetor[20]+ vetor[21]+ vetor[22]+ vetor[23]+ vetor[24]+ vetor[25]+ vetor[26]+ vetor[27]+ 
					vetor[28]+ vetor[29]+ vetor[30]+ vetor[31]+ vetor[32]+ vetor[33]+ vetor[34]+ vetor[35]+ vetor[36]+ vetor[37]+ 
					vetor[38]+ vetor[39]+ vetor[40]+ vetor[41]+ vetor[42]+ vetor[43]+ vetor[44]+ vetor[45]+ vetor[46]+ vetor[47]+ 
					vetor[48]+ vetor[49] + vetor[50] + vetor[51] + vetor[52] + vetor[53]);
			
			
////			statement.setInt(1, 0);
//			statement.setString(1, "wagner");
//			statement.setString(2, "1920-12-30");
//			statement.setString(3, "F");
//			statement.setString(4, "50.2");
//			statement.setString(5, "1.83");
//			statement.setString(7, "");
//			statement.execute();
//			System.out.println(statement);
			smtp.close();
			rs.close();
			connection.close();
			
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
