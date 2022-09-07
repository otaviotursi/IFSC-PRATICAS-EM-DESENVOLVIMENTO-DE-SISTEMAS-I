

import java.sql.*;
import java.util.ArrayList;


/*Sistema para gerenciamento de comanda*/

public class HistoricoComandaBD {
	private static PreparedStatement ps;
	private static ResultSet rs;
	private static Statement st;
	private static Connection conn = null;
	
	public static void InserirHistorico(HistoricoComandaModel historico){
		try {
			String comando = String.format("INSERT INTO `HistoricoComandas`(`codigoMesa`, `produtos`, `statusPagamento`, `data`, `valor`) VALUES ('"+historico.codigoMesa+"','"+historico.produtos+"', '"+historico.statusPagamento+"',NOW(), '"+historico.valor+"')");
			st.execute(comando);
			System.out.println("Historico inserido!");
		} catch (SQLException e) {
			System.out.println("Erro ao inserir Historico."+ e.toString());
		}
	}
	
	
	public static void DeletarHistorico(){
		try {
			String comando = String.format("DELETE FROM `HistoricoComandas`");
			st.execute(comando);
			System.out.println("Historico deletado!");
		} catch (SQLException e) {
			System.out.println("Erro ao deletar Historico "+ e.toString());
		}
	}
	public static ArrayList<HistoricoComandaModel> ConsultarHistorico() throws Exception{
		ArrayList<HistoricoComandaModel> mesa = new ArrayList<HistoricoComandaModel>();
		try {
			String comando = String.format("SELECT * FROM `HistoricoComandas`");

			ResultSet rs = st.executeQuery(comando);
			while (rs.next()) {
				HistoricoComandaModel prod = new HistoricoComandaModel();
				prod.codigoMesa = rs.getLong("codigoMesa");
				prod.data = rs.getDate("data");
				prod.produtos = rs.getString("produtos");
				prod.statusPagamento = rs.getString("statusPagamento");
				prod.valor = rs.getDouble("valor");
				mesa.add(prod);
			}
			
			System.out.println("Consulta unitaria realizada!");
			return mesa;
		} catch (SQLException e) {
			System.out.println("Erro ao consultar Historicos unitarios"+ e.toString());
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
	
	public static void CriarBdHistorico(){
		try {
			String query = "create database if not exists restaurante";
			st = conn.createStatement();
			st.execute(query);
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurante", "root", null);
			System.out.println("BD historico CRIADO!");
		} catch (SQLException e) {
			System.out.println("Erro ao criar database empresa ");
			e.printStackTrace();
		}
		try {
			String query = "create table if not exists HistoricoComandas (codigo integer not null AUTO_INCREMENT, data TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP, codigoMesa long not null, produtos varchar(1024) not null, statusPagamento varchar(10), valor double, PRIMARY KEY(codigo))";
			st = conn.createStatement();
			st.execute(query);
			System.out.println("BD historico CRIADO!");
		} catch (SQLException e) {
			System.out.println("Erro ao criar bd historico ");
			e.printStackTrace();
		}
	}
	
	public static void MockarBdHistorico() {

		HistoricoComandaModel prod1 = new HistoricoComandaModel();
		prod1.codigoMesa = 1;
		prod1.produtos = "2001;2002;1003";
		prod1.statusPagamento = "PAGO";
		InserirHistorico(prod1);
		
		HistoricoComandaModel prod2 = new HistoricoComandaModel();
		prod2.codigoMesa = 2;
		prod2.produtos = "1001;1002;2003";
		prod2.statusPagamento = "PAGO";
		InserirHistorico(prod2);
		
		HistoricoComandaModel prod3 = new HistoricoComandaModel();
		prod3.codigoMesa = 3;
		prod3.produtos = "2003;1001";
		prod3.statusPagamento = "EM ABERTO";
		InserirHistorico(prod3);
	}
}
