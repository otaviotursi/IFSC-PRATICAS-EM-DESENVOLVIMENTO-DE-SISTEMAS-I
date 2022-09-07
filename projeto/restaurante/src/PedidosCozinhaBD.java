

import java.sql.*;
import java.util.ArrayList;


/*Sistema para gerenciamento de pratos*/

public class PedidosCozinhaBD {
	private static PreparedStatement ps;
	private static ResultSet rs;
	private static Statement st;
	private static Connection conn = null;

	public static void InserirStatus(){
		try {
			String comando = String.format("INSERT INTO `statusPedidos`(`status`) VALUES ('PRONTO')");
			st.execute(comando);
			comando = String.format("INSERT INTO `statusPedidos`(`status`) VALUES ('EM ANDAMENTO')");
			st.execute(comando);
			comando = String.format("INSERT INTO `statusPedidos`(`status`) VALUES ('CANCELADO')");
			st.execute(comando);
			System.out.println("Status Pedidos inserido!");
		} catch (SQLException e) {
			System.out.println("Erro ao inserir Funcionarios."+ e.toString());
			e.printStackTrace();
		}
	}
	
	public static void InserirPedidoCozinha(PedidosCozinhaModel pedidos){
		try {
			String comando = String.format("INSERT INTO `pedidosCozinhas`(`codigoProduto`, `codigoMesa`, `status`) VALUES ("+pedidos.codigoProduto+","+pedidos.codigoMesa+",'"+pedidos.status+"')");
			st.execute(comando);
			System.out.println("Pedidos inserido!");
		} catch (SQLException e) {
			System.out.println("Erro ao inserir Pedidos. "+ e.toString());
			e.printStackTrace();
		}
	}
	
	public static void DeletarPedidosCozinha(){
		try {
			String comando = String.format("DELETE * FROM `pedidosCozinhas`");
			st.execute(comando);
			System.out.println("Pedidos deletados!");
		} catch (SQLException e) {
			System.out.println("Erro ao deletar Pedidos "+ e.toString());
		}
	}
	public static PedidosCozinhaModel ConsultarPedidoCozinhaUni(PedidosCozinhaModel pedidos) throws Exception{
		try {
			String comando = String.format("SELECT * FROM `pedidosCozinhas` where codigo="+ pedidos.codigoId+"");

			
			ResultSet rs = st.executeQuery(comando);
			while (rs.next()) {
				PedidosCozinhaModel prod = new PedidosCozinhaModel();
				prod.codigoId = rs.getLong("codigo");
				prod.codigoMesa = rs.getLong("codigoMesa");
				prod.codigoProduto = rs.getLong("codigoProduto");
				return prod;
			}
			
			System.out.println("Consulta unitaria de pedidos realizada!");
		} catch (SQLException e) {
			System.out.println("Erro ao consultar pedidos unitarios "+ e.toString());
			throw new Exception("Erro");
		}
		
		return pedidos;
	}
	
	public static ArrayList<PedidosCozinhaModel> ConsultarPedidoCozinha() throws Exception{
		try {
			String comando = String.format("SELECT * FROM `pedidosCozinhas`");

			
			ResultSet rs = st.executeQuery(comando);
			ArrayList<PedidosCozinhaModel> resultado = new ArrayList<>();
			while (rs.next()) {
				PedidosCozinhaModel prod = new PedidosCozinhaModel();
				prod.codigoId = rs.getLong("codigo");
				prod.codigoMesa = rs.getLong("codigoMesa");
				prod.codigoProduto = rs.getLong("codigoProduto");
				prod.status = rs.getString("status");
				resultado.add(prod);
			}
			System.out.println("Consulta pedido total realizada!");
			return resultado;
		} catch (SQLException e) {
			System.out.println("Erro ao consultar todos os pedidos "+ e.toString());
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
	
	public static void CriarBdPedidos(){
		try {
			String query = "create database if not exists restaurante";
			st = conn.createStatement();
			st.execute(query);
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurante", "root", null);
			System.out.println("BD pedidos CRIADO!");
		} catch (SQLException e) {
			System.out.println("Erro ao criar database empresa ");
			e.printStackTrace();
		}
		try {
			String query = "create table if not exists pedidosCozinhas (codigo integer not null AUTO_INCREMENT, codigoProduto long not null , codigoMesa long not null, status varchar(30) not null, PRIMARY KEY(codigo))";
			st = conn.createStatement();
			st.execute(query);
			System.out.println("BD pedidosCozinhas CRIADO!");
		} catch (SQLException e) {
			System.out.println("Erro ao criar bd pedidosCozinhas ");
			e.printStackTrace();
		}
		try {
			String query = "create table if not exists statusPedidos (codigo integer not null AUTO_INCREMENT, status varchar(30) not null, PRIMARY KEY(codigo))";
			st = conn.createStatement();
			st.execute(query);
			System.out.println("BD statusPedidos CRIADO!");
		} catch (SQLException e) {
			System.out.println("Erro ao criar bd statusPedidos ");
			e.printStackTrace();
		}
	}
	
	public static void MockarBdPedido() {

		PedidosCozinhaModel prod1 = new PedidosCozinhaModel();
		prod1.codigoMesa = 1;
		prod1.codigoProduto = 1001;
		InserirPedidoCozinha(prod1);
		
		PedidosCozinhaModel prod2 = new PedidosCozinhaModel();
		prod2.codigoMesa = 2;
		prod2.codigoProduto = 2001;
		InserirPedidoCozinha(prod2);
		
		InserirStatus();
	}

	public static void AtualizarPedidoCozinha(PedidosCozinhaModel pedidosCoz) {
		try {
			String comando = String.format("UPDATE `pedidosCozinhas` SET `status`='"+pedidosCoz.status+"' WHERE codigo="+pedidosCoz.codigoId+"");
			st.execute(comando);
			System.out.println("pedido atualizado!");
		} catch (SQLException e) {
			System.out.println("Erro ao ataualizar pedido."+ e.toString());
			e.printStackTrace();
		}
		
	}

	public static void DeletarPedidoCozinhaUni(PedidosCozinhaModel pedidosCoz) {
		try {
			String comando = String.format("DELETE FROM `pedidosCozinhas` WHERE codigo="+pedidosCoz.codigoId+"");
			System.out.println("Pedido deletado!"+comando);
			st.execute(comando);
		} catch (SQLException e) {
			System.out.println("Erro ao deletar pedido. "+ e.toString());
			e.printStackTrace();
		}
		
	}
	
	public static ArrayList<String> ConsultarTodosStatus() throws Exception {
		ArrayList<String> comanda = new ArrayList<String>();
		try {
			String comando = String.format("SELECT * FROM `statusPedidos`");

			ResultSet rs = st.executeQuery(comando);
			while (rs.next()) {
				String status = rs.getString("status");
				comanda.add(status);
			}
			
			System.out.println("Consulta status pedidos total realizada!");
			return comanda;
		} catch (SQLException e) {
			System.out.println("Erro ao consultar status pedidos unitarios "+ e.toString());
			throw new Exception("Erro");
		}
	}
}
