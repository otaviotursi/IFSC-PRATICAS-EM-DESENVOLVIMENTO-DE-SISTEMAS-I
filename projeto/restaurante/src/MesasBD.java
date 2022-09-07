

import java.sql.*;
import java.util.ArrayList;


/*Sistema para gerenciamento de comanda*/

public class MesasBD {
	private static PreparedStatement ps;
	private static ResultSet rs;
	private static Statement st;
	private static Connection conn = null;
	private static MesasModel mesass;

	public static void InserirStatus(){
		try {
			String comando = String.format("INSERT INTO `tipoStatus`(`status`) VALUES ('ABERTO')");
			st.execute(comando);
			comando = String.format("INSERT INTO `tipoStatus`(`status`) VALUES ('MANUTENÇÃO')");
			st.execute(comando);
			comando = String.format("INSERT INTO `tipoStatus`(`status`) VALUES ('FECHADA')");
			st.execute(comando);
			System.out.println("Mesa inserido!");
		} catch (SQLException e) {
			System.out.println("Erro ao inserir Funcionarios."+ e.toString());
			e.printStackTrace();
		}
	}
	public static void InserirNovaMesa(){
		try {
			String comando = String.format("INSERT INTO `mesas`(`status`) VALUES ('ABERTO')");
			st.execute(comando);
			System.out.println("Mesas inserido!");
		} catch (SQLException e) {
			System.out.println("Erro ao inserir Mesas."+ e.toString());
		}
	}
	

	public static void AtualizarStatusMesa(MesasModel mesass){
		try {
			String comando = String.format("UPDATE `mesas` SET `status`='"+mesass.status+"' WHERE codigoMesa="+mesass.codigoMesa+" ");
			System.out.println("Mesas inserido!"+comando);
			st.execute(comando);
		} catch (SQLException e) {
			System.out.println("Erro ao inserir Mesas."+ e.toString());
			e.printStackTrace();
		}
	}
	
	
	public static void DeletarMesa(long codigoMesa){
		try {
			String comando = String.format("DELETE FROM `mesas` WHERE codigoMesa="+codigoMesa+"");
			st.execute(comando);
			System.out.println("Mesa deletada!");
		} catch (SQLException e) {
			System.out.println("Erro ao deletar Mesas "+ e.toString());
		}
	}

	public static ArrayList<MesasModel> ConsultarMesaTodos() throws Exception{
		ArrayList<MesasModel> mesas = new ArrayList<MesasModel>();
		try {
			String comando = String.format("SELECT * FROM `mesas`");

			ResultSet rs = st.executeQuery(comando);
			while (rs.next()) {
				MesasModel prod = new MesasModel();
				prod.codigoMesa = rs.getLong("codigoMesa");
				prod.status = rs.getString("status");
				mesas.add(prod);
			}
			
			System.out.println("Consulta de todas as mesass realizada!");
			return mesas;
		} catch (SQLException e) {
			System.out.println("Erro ao consultar Mesass unitarios"+ e.toString());
			throw new Exception("Erro");
		}
		
	}
	
	public static MesasModel ConsultarMesaUnica(long codigo) throws Exception{
		ArrayList<MesasModel> mesas = new ArrayList<MesasModel>();
		try {
			String comando = String.format("SELECT * FROM `mesas` where codigoMesa="+codigo+"");

			ResultSet rs = st.executeQuery(comando);
			while (rs.next()) {
				MesasModel prod = new MesasModel();
				prod.codigoMesa = rs.getLong("codigoMesa");
				prod.status = rs.getString("status");
				mesas.add(prod);
				return prod;
			}
			
			System.out.println("Consulta unitaria realizada!");
		} catch (SQLException e) {
			System.out.println("Erro ao consultar Mesass unitarios"+ e.toString());
			throw new Exception("Erro");
		}
		return mesass;
		
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
	
	public static void CriarBdMesas(){
		try {
			String query = "create database if not exists restaurante";
			st = conn.createStatement();
			st.execute(query);
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurante", "root", null);
			System.out.println("BD mesass CRIADO!");
		} catch (SQLException e) {
			System.out.println("Erro ao criar database empresa ");
			e.printStackTrace();
		}
		try {
			String query = "create table if not exists mesas (codigoMesa integer not null AUTO_INCREMENT, status varchar(10), PRIMARY KEY(codigoMesa))";
			st = conn.createStatement();
			st.execute(query);
			System.out.println("BD mesass CRIADO!");
		} catch (SQLException e) {
			System.out.println("Erro ao criar bd mesass ");
			e.printStackTrace();
		}
		try {
			String query = "create table if not exists tipoStatus (codigo integer not null AUTO_INCREMENT, status varchar(10), PRIMARY KEY(codigo))";
			st = conn.createStatement();
			st.execute(query);
			System.out.println("BD mesass CRIADO!");
		} catch (SQLException e) {
			System.out.println("Erro ao criar bd mesass ");
			e.printStackTrace();
		}
	}
	
	public static void MockarBdMesas() {

		InserirNovaMesa();
		InserirNovaMesa();
		InserirNovaMesa();
		InserirStatus();
	}


	public static ArrayList<String> ConsultarTodosStatus() throws Exception {
		ArrayList<String> lista = new ArrayList<String>();
		try {
			String comando = String.format("SELECT * FROM `tipoStatus`");

			ResultSet rs = st.executeQuery(comando);
			while (rs.next()) {
				String status = rs.getString("status");
				lista.add(status);
			}
			
			System.out.println("Consulta de todas as mesas realizada!");
			return lista;
		} catch (SQLException e) {
			System.out.println("Erro ao consultar Mesass unitarios"+ e.toString());
			throw new Exception("Erro");
		}
		
	}
}
