

import java.sql.*;
import java.util.ArrayList;


/*Sistema para gerenciamento de pratos*/

public class PratosBD {
	private static PreparedStatement ps;
	private static ResultSet rs;
	private static Statement st;
	private static Connection conn = null;
	private static PratosModel pratos;
	
	public static void InserirPrato(PratosModel pratos){
		try {
			String comando = String.format("INSERT INTO `pratos`( `nome`, `preco`, `descricao`) VALUES ('"+pratos.nome+"','"+pratos.preco+"','"+pratos.descricao+"')");
			st.execute(comando);
			System.out.println("Pratos inserido!");
		} catch (SQLException e) {
			System.out.println("Erro ao inserir Pratos."+ e.toString());
		}
	}
	
	public static void AtualizarPrato(PratosModel pratos){
		try {
			String comando = String.format("UPDATE `pratos` SET  `nome`='"+pratos.nome+"' `preco`='"+pratos.preco+"' `descricao`='"+pratos.descricao+"' WHERE codPrato="+pratos.codigo+" ");
			st.execute(comando);
			System.out.println("Pratos atualizado!");
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar Pratos "+ e.toString());
		}
	}
	
	public static void DeletarPrato(long id){
		try {
			String comando = String.format("DELETE FROM `pratos` WHERE codPrato="+id+" ");
			st.execute(comando);
			System.out.println("Pratos deletado!");
		} catch (SQLException e) {
			System.out.println("Erro ao deletar Pratos "+ e.toString());
		}
	}
	public static PratosModel ConsultarPratosUn(long codigo) throws Exception{
		try {
			String comando = String.format("SELECT * FROM `pratos` where codPrato="+codigo+"");

			
			ResultSet rs = st.executeQuery(comando);
			while (rs.next()) {
				PratosModel prod = new PratosModel();
				prod.nome = rs.getString("nome");
				prod.descricao = rs.getString("descricao");
				prod.preco = rs.getDouble("preco");
				prod.codigo = rs.getLong("codPrato");
				return prod;
			}
			
			System.out.println("Consulta unitaria realizada!");
		} catch (SQLException e) {
			System.out.println("Erro ao consultar pratos unitarios "+ e.toString());
			throw new Exception("Erro");
		}
		
		return pratos;
	}
	
	public static ArrayList<PratosModel> ConsultarPratosTodos() throws Exception{
		try {
			String comando = String.format("SELECT * FROM `pratos`");

			
			ResultSet rs = st.executeQuery(comando);
			ArrayList<PratosModel> resultado = new ArrayList<>();
			while (rs.next()) {
				PratosModel prod = new PratosModel();
				prod.codigo = rs.getLong("codPrato");
				prod.nome = rs.getString("nome");
				prod.preco = rs.getDouble("preco");
				prod.descricao = rs.getString("descricao");
				resultado.add(prod);
			}
			System.out.println("Consulta total realizada!");
			return resultado;
		} catch (SQLException e) {
			System.out.println("Erro ao consultar todos os Pratos "+ e.toString());
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
	
	public static void CriarBdPratos(){
		try {
			String query = "create database if not exists restaurante";
			st = conn.createStatement();
			st.execute(query);
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurante", "root", null);
			System.out.println("BD pratos CRIADO!");
		} catch (SQLException e) {
			System.out.println("Erro ao criar database empresa ");
			e.printStackTrace();
		}
		try {
			String query = "create table if not exists pratos (codPrato integer not null AUTO_INCREMENT, nome varchar(30) not null, preco Decimal, descricao varchar(1024) not null,  PRIMARY KEY(codPrato)) AUTO_INCREMENT=1001;";
			st = conn.createStatement();
			st.execute(query);
			System.out.println("BD pratos CRIADO!");
		} catch (SQLException e) {
			System.out.println("Erro ao criar bd pratos ");
			e.printStackTrace();
		}
	}
	
	public static void MockarBdPrato() {

		PratosModel prod1 = new PratosModel();
		prod1.nome = "Prato 1";
		prod1.preco = 30.00;
		prod1.descricao = "prato excelente - tem batata frita e coca";
		InserirPrato(prod1);
		
		PratosModel prod2 = new PratosModel();
		prod2.nome = "Prato 2";
		prod2.preco = 20.00;
		prod2.descricao = "prato bom - tem batata frita";
		InserirPrato(prod2);
		
		PratosModel prod3 = new PratosModel();;
		prod3.nome = "Prato 3";
		prod3.preco = 9.97;
		prod3.descricao = "prato simples";
		InserirPrato(prod3);
	}
}
