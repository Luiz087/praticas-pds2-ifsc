package controle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.cj.xdevapi.PreparableStatement;

import modelo.Carro;

public class CarroDAO {
	
	private static CarroDAO instancia;
	
	public static CarroDAO getInstancia() {
		if (instancia == null) {
			instancia = new CarroDAO();
		}
		return instancia;
	}

	/**
	 * Metodo que recebe um carro
	 * 
	 * @param c
	 * @return
	 */

	public ArrayList<Carro> listar() {
		Conexao c = Conexao.getInstancia();

		// Abrir a conexão com o banco
		Connection con = c.conectar();
		
		// Criando um obj de lista para guardar o que o banco trouxe
		ArrayList<Carro> carros = new ArrayList();

		String query = "SELECT * FROM carros";
		try {
			// Prepara a nossa querry SQL acima em um obj java
			PreparedStatement ps = con.prepareStatement(query);
			
			// Executa nossa querry de fato
			ResultSet rs = ps.executeQuery();
			
			// Enquanto tiver registro, faça esse loop
			while(rs.next()) {
				int idCarro = rs.getInt("id_carro");
				String modelo = rs.getString("modelo");
				
				// Cria um obj organizado de pessoa
				Carro carro = new Carro();
				carro.setIdCarro(idCarro);
				carro.setModeloCarro(modelo);
				
				// Adiciona pessoa na lista
				carros.add(carro);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		c.fecharConexao();
		
		return carros;
	}

	public boolean inserir(Carro p) {

		// Instanciar a classe
		Conexao c = Conexao.getInstancia();

		// Abrir a conexão com o banco
		Connection con = c.conectar();

		String query = "INSERT INTO carros " + "(id_carro, modelo_carro) " + "VALUES (?, ?)";

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

	public boolean excluir(Carro carro) {
		Conexao c = Conexao.getInstancia();
		Connection con = c.conectar();
		
		String query = "DELTE FROM carros WHERE id_carro = ?";
		
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1, carro.getIdCarro());
			
			ps.executeUpdate();
			
			c.fecharConexao();
			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public boolean atualizar(Carro carro) {
		Conexao c = Conexao.getInstancia();
		Connection con = c.conectar();
		
		String query = "UPDATE carros SET modelo_carro = ? WHERE id_carro = ?";
		
		try {
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1, carro.getModeloCarro());
			ps.setInt(2, carro.getIdCarro());
			
			ps.executeUpdate();
			
			c.fecharConexao();
			
			return true;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
}
