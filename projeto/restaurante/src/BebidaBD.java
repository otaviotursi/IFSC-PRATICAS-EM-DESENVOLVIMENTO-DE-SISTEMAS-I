

import java.sql.*;
import java.util.ArrayList;


/*Sistema para gerenciamento de pratos*/

public class BebidaBD {
	private static PreparedStatement ps;
	private static ResultSet rs;
	private static Statement st;
	private static Connection conn = null;
	private static BebidaModel bebidas;
	
	public static void InserirBebida(BebidaModel bebidas){
		try {
			String comando = String.format("INSERT INTO `bebidas`( `nome`, `preco`, `descricao`, `alcoolico` ) VALUES ('"+bebidas.nome+"',"+bebidas.preco+",'"+bebidas.descricao+"',"+bebidas.alcoolico+")");
			st.execute(comando);
			System.out.println("Bebida inserido!");
		} catch (SQLException e) {
			System.out.println("Erro ao inserir Bebida."+ e.toString());
			e.printStackTrace();
		}
	}
	
	public static void AtualizarBebida(BebidaModel bebidas){
		try {
			String comando = String.format("UPDATE `bebidas` SET  `nome`='"+bebidas.nome+"' `preco`='"+bebidas.preco+"' `descricao`='"+bebidas.descricao+"' `alcoolico`='"+bebidas.alcoolico+"' WHERE codBebida="+bebidas.codigo+" ");
			st.execute(comando);
			System.out.println("Bebida atualizado!");
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar Bebida "+ e.toString());
		}
	}
	
	public void DeletarBebida(long id){
		try {
			String comando = String.format("DELETE FROM `bebidas` WHERE codBebida="+id+" ");
			st.execute(comando);
			System.out.println("Bebida deletado!");
		} catch (SQLException e) {
			System.out.println("Erro ao deletar Bebida "+ e.toString());
		}
	}
	public static BebidaModel ConsultarBebidaUn(long codigo) throws Exception{
		try {
			String comando = String.format("SELECT * FROM `bebidas` where codBebida="+codigo+"");

			
			ResultSet rs = st.executeQuery(comando);
			while (rs.next()) {
				BebidaModel prod = new BebidaModel();
				prod.nome = rs.getString("nome");
				prod.descricao = rs.getString("descricao");
				prod.preco = rs.getDouble("preco");
				prod.codigo = rs.getLong("codBebida");
				return prod;
			}
			
			System.out.println("Consulta unitaria realizada!");
		} catch (SQLException e) {
			System.out.println("Erro ao consultar pratos unitarios "+ e.toString());
			throw new Exception("Erro");
		}
		
		return bebidas;
	}
	
	public static ArrayList<BebidaModel> ConsultarBebidaTodos() throws Exception{
		try {
			String comando = String.format("SELECT * FROM `bebidas`");

			
			ResultSet rs = st.executeQuery(comando);
			ArrayList<BebidaModel> resultado = new ArrayList<>();
			while (rs.next()) {
				BebidaModel prod = new BebidaModel();
				prod.codigo = rs.getLong("codBebida");
				prod.nome = rs.getString("nome");
				prod.preco = rs.getDouble("preco");
				prod.descricao = rs.getString("descricao");
				prod.alcoolico = rs.getBoolean("alcoolico");
				resultado.add(prod);
			}
			System.out.println("Consulta total realizada!");
			return resultado;
		} catch (SQLException e) {
			System.out.println("Erro ao consultar todos as bebidas"+ e.toString());
			throw new Exception("Erro");
		}
	}
	
	public static Connection AbrirConexao(){
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", null);
			System.out.println("Conectado com a base de dados!");
		} catch (SQLException e) {
			System.out.println("Erro ao conectar na base de dados "+ e.toString());
		}
		return conn;
	}
	
	public static void CriarBdBebida(){
		try {
			String query = "create database if not exists restaurante";
			st = conn.createStatement();
			st.execute(query);
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurante", "root", null);
			System.out.println("BD bebidas CRIADO!");
		} catch (SQLException e) {
			System.out.println("Erro ao criar database empresa ");
			e.printStackTrace();
		}
		try {
			String query = "create table if not exists bebidas (codBebida integer not null AUTO_INCREMENT, nome varchar(30) not null, preco Decimal, descricao varchar(1024) not null, alcoolico boolean,  PRIMARY KEY(codBebida)) AUTO_INCREMENT=2001;";
			st = conn.createStatement();
			st.execute(query);
			System.out.println("BD bebidas CRIADO!");
		} catch (SQLException e) {
			System.out.println("Erro ao criar bd bebidas ");
			e.printStackTrace();
		}
	}
	
	public static void MockarBdBebida() {

		BebidaModel prod1 = new BebidaModel();
		prod1.nome = "coca";
		prod1.preco = 10.00;
		prod1.descricao = "bom";
		prod1.alcoolico = false;
		InserirBebida(prod1);
		
		BebidaModel prod2 = new BebidaModel();
		prod2.nome = "h2o";
		prod2.preco = 5.00;
		prod2.descricao = "doce";
		prod2.alcoolico = false;
		InserirBebida(prod2);
		
		BebidaModel prod3 = new BebidaModel();;
		prod3.nome = "bebida de gengibre";
		prod3.preco = 39.97;
		prod3.descricao = "ruim";
		prod3.alcoolico = true;
		InserirBebida(prod3);
	}
}
