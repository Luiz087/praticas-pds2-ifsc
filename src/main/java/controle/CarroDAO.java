package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mysql.cj.xdevapi.PreparableStatement;

import modelo.Carro;

public class CarroDAO {
	
	
	/**
	 * Metodo que recebe um carro
	 * 
	 * @param c
	 * @return
	 */
	public boolean inserir(Carro p) {
		
		// Instanciar a classe
		Conexao c = Conexao.getInstancia();
		
		//Abrir a conexão com o banco
		Connection con = c.conectar();
		
		String query = "INSERT INTO carro (id_carro, modelo_carro) VALUES (?, ?)";
		
		try {
			PreparedStatement ps = con.prepareStatement(query);
			
			// Seta os parâmetros
			ps.setInt(1, p.getIdCarro());
			ps.setString(2, p.getModeloCarro());
			
			// Consolidar a execução do comando no banco
			ps.executeUpdate();
			
			// Fecha a conexão
			c.fecharConexao();
			
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

}
