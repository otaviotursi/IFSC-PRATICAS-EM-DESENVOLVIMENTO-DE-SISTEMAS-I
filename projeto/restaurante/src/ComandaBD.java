

import java.sql.*;
import java.util.ArrayList;


/*Sistema para gerenciamento de comandas*/

public class ComandaBD {
	private static PreparedStatement ps;
	private static ResultSet rs;
	private static Statement st;
	private static Connection conn = null;

	public static void InserirStatus(){
		try {
			String comando = String.format("INSERT INTO `statusPagamentos`(`status`) VALUES ('ABERTO')");
			st.execute(comando);
			comando = String.format("INSERT INTO `statusPagamentos`(`status`) VALUES ('PAGO')");
			st.execute(comando);
			System.out.println("Status ComandaBD inserido!");
		} catch (SQLException e) {
			System.out.println("Erro ao inserir Funcionarios."+ e.toString());
			e.printStackTrace();
		}
	}
	public static void InserirPratoEmComanda(ComandaModel comandas){
		try {
			String comando = String.format("INSERT INTO `comandas`(`codigoMesa`, `produto`, `responsavel`) VALUES ('"+comandas.codigoMesa+"','"+comandas.produto+"','"+comandas.responsavel+"')");
			st.execute(comando);
			System.out.println("Comanda inserido!");
		} catch (SQLException e) {
			System.out.println("Erro ao inserir Comanda."+ e.toString());
			e.printStackTrace();
		}
	}
	
	public static void DeletarComanda(long codigoComanda){
		try {
			String comando = String.format("DELETE FROM `comandas` WHERE codigoMesa="+codigoComanda+"");
			st.execute(comando);
			System.out.println("Comanda deletada!");
		} catch (SQLException e) {
			System.out.println("Erro ao deletar Comanda "+ e.toString());
			e.printStackTrace();
		}
	}
	
	public static void DeletarProdutoComanda(ComandaModel comandas){
		try {
			String comando = String.format("DELETE FROM `comandas` WHERE codigo="+comandas.codigoId+"");
			st.execute(comando);
			System.out.println("Produto em Comanda deletado!");
		} catch (SQLException e) {
			System.out.println("Erro ao deletar Comanda "+ e.toString());
			e.printStackTrace();
		}
	}
	
	public static ArrayList<ComandaModel> ConsultarComandaTodos() throws Exception{
		ArrayList<ComandaModel> comandas = new ArrayList<ComandaModel>();
		try {
			String comando = String.format("SELECT * FROM `comandas`");

			ResultSet rs = st.executeQuery(comando);
			while (rs.next()) {
				ComandaModel prod = new ComandaModel();
				prod.codigoId = rs.getLong("codigo");
				prod.codigoMesa = rs.getLong("codigoMesa");
				prod.produto = rs.getLong("produto");
				prod.responsavel = rs.getString("responsavel");
				comandas.add(prod);
			}
			
			System.out.println("Consulta total realizada!");
			return comandas;
		} catch (SQLException e) {
			System.out.println("Erro ao consultar Comandas unitarios"+ e.toString());
			throw new Exception("Erro");
		}
		
	}

	public static ArrayList<String> ConsultarTodosStatusPagamento() throws Exception{
		ArrayList<String> comandas = new ArrayList<String>();
		try {
			String comando = String.format("SELECT * FROM `statusPagamento`");

			ResultSet rs = st.executeQuery(comando);
			while (rs.next()) {
				String status = rs.getString("status");
				comandas.add(status);
			}
			
			System.out.println("Consulta total realizada!");
			return comandas;
		} catch (SQLException e) {
			System.out.println("Erro ao consultar Comandas unitarios"+ e.toString());
			throw new Exception("Erro");
		}
		
	}
	
	public static ArrayList<ComandaModel> ConsultarComandaUn(long codigo) throws Exception{
		ArrayList<ComandaModel> comandas = new ArrayList<ComandaModel>();
		try {
			String comando = String.format("SELECT * FROM `comandas` WHERE codigoMesa="+codigo+"");

			System.out.println(comando);
			ResultSet rs = st.executeQuery(comando);
			while (rs.next()) {
				ComandaModel prod = new ComandaModel();
				prod.codigoId = rs.getLong("codigo");
				prod.codigoMesa = rs.getLong("codigoMesa");
				prod.produto = rs.getLong("produto");
				prod.responsavel = rs.getString("responsavel");
				System.out.println(prod.codigoMesa);
				comandas.add(prod);
			}
			
			System.out.println("Consulta total realizada!");
			return comandas;
		} catch (SQLException e) {
			System.out.println("Erro ao consultar Comandas unitarios"+ e.toString());
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
	
	public static void CriarBdComanda(){
		try {
			String query = "create database if not exists restaurante";
			st = conn.createStatement();
			st.execute(query);
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurante", "root", null);
			System.out.println("BD comandas CRIADO!");
		} catch (SQLException e) {
			System.out.println("Erro ao criar database empresa ");
			e.printStackTrace();
		}
		try {
			String query = "create table if not exists comandas (codigo integer not null AUTO_INCREMENT, codigoMesa long not null, produto long not null, responsavel varchar(30) not null, PRIMARY KEY(codigo))";
			st = conn.createStatement();
			st.execute(query);
			System.out.println("BD comandas CRIADO!");
		} catch (SQLException e) {
			System.out.println("Erro ao criar bd comandas ");
			e.printStackTrace();
		}
		try {
			String query = "create table if not exists statusPagamentos (codigo integer not null AUTO_INCREMENT, status varchar(30) not null, PRIMARY KEY(codigo))";
			st = conn.createStatement();
			st.execute(query);
			System.out.println("BD comandas CRIADO!");
		} catch (SQLException e) {
			System.out.println("Erro ao criar bd comandas ");
			e.printStackTrace();
		}
	}
	
	public static void MockarBdComanda() {

		ComandaModel prod1 = new ComandaModel();
		prod1.codigoMesa = 1;
		prod1.produto = 1;
		InserirPratoEmComanda(prod1);
		
		ComandaModel prod2 = new ComandaModel();
		prod2.codigoMesa = 2;
		prod2.produto = 2;
		InserirPratoEmComanda(prod2);
		
		ComandaModel prod3 = new ComandaModel();
		prod3.codigoMesa = 3;
		prod3.produto = 3;
		InserirPratoEmComanda(prod3);
		InserirStatus();
	}
}
