

import java.sql.*;
import java.util.ArrayList;


/*Sistema para gerenciamento de pratos*/

public class FuncionariosBD {
	private static PreparedStatement ps;
	private static ResultSet rs;
	private static Statement st;
	private static Connection conn = null;
	private static FuncionariosModel funcionarios;
	
	public static void InserirFuncionario(FuncionariosModel funcionarios){
		try {
			String comando = String.format("INSERT INTO `funcionarios`( `nome`, `cargo`) VALUES ('"+funcionarios.nome+"','"+funcionarios.cargo+"')");
			st.execute(comando);
			System.out.println("Funcionarios inserido!");
		} catch (SQLException e) {
			System.out.println("Erro ao inserir Funcionarios."+ e.toString());
			e.printStackTrace();
		}
	}

	public static void InserirCargo(){
		try {
			String comando = String.format("INSERT INTO `cargos`(`cargo`) VALUES ('Garçom')");
			st.execute(comando);
			comando = String.format("INSERT INTO `cargos`(`cargo`) VALUES ('Cozinheiro')");
			st.execute(comando);
			comando = String.format("INSERT INTO `cargos`(`cargo`) VALUES ('Faxineiro')");
			st.execute(comando);
			comando = String.format("INSERT INTO `cargos`(`cargo`) VALUES ('Gerente')");
			st.execute(comando);
			comando = String.format("INSERT INTO `cargos`(`cargo`) VALUES ('Dono')");
			st.execute(comando);
			System.out.println("Cargo inserido!");
		} catch (SQLException e) {
			System.out.println("Erro ao inserir Funcionarios."+ e.toString());
			e.printStackTrace();
		}
	}
	public static void AtualizarFuncionario(FuncionariosModel  funcionarios){
		try {
			String comando = String.format("UPDATE `funcionarios` SET  `nome`='"+funcionarios.nome+"', `cargo`='"+funcionarios.cargo+"' WHERE codFunc="+funcionarios.codigo+" ");
			st.execute(comando);
			System.out.println("Funcionarios atualizado!");
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar Funcionarios "+ e.toString());
			e.printStackTrace();
		}
	}
	
	public void DeletarFuncionario(long id){
		try {
			String comando = String.format("DELETE FROM `funcionarios` WHERE codFunc="+id+" ");
			st.execute(comando);
			System.out.println("Funcionarios deletado!");
		} catch (SQLException e) {
			System.out.println("Erro ao deletar Funcionarios "+ e.toString());
		}
	}
	public static FuncionariosModel ConsultarFuncionario(long codigo) throws Exception{
		try {
			String comando = String.format("SELECT * FROM `funcionarios` where codFunc="+codigo+"");

			
			ResultSet rs = st.executeQuery(comando);
			while (rs.next()) {
				FuncionariosModel prod = new FuncionariosModel();
				prod.codigo = rs.getLong("codFunc");
				prod.nome = rs.getString("nome");
				prod.cargo = rs.getString("cargo");
				return prod;
			}
			
			System.out.println("Consulta unitaria realizada!");
		} catch (SQLException e) {
			System.out.println("Erro ao consultar pratos unitarios "+ e.toString());
			throw new Exception("Erro");
		}
		
		return funcionarios;
	}
	
	public static ArrayList<FuncionariosModel> ConsultarFuncionarios() throws Exception{
		try {
			String comando = String.format("SELECT * FROM `funcionarios`");

			
			ResultSet rs = st.executeQuery(comando);
			ArrayList<FuncionariosModel> resultado = new ArrayList<>();
			while (rs.next()) {
				FuncionariosModel prod = new FuncionariosModel();
				prod.codigo = rs.getLong("codFunc");
				prod.nome = rs.getString("nome");
				prod.cargo = rs.getString("cargo");
				resultado.add(prod);
			}
			System.out.println("Consulta total realizada!");
			return resultado;
		} catch (SQLException e) {
			System.out.println("Erro ao consultar todos os Pratos "+ e.toString());
			throw new Exception("Erro");
		}
	}

	public static ArrayList<String> ConsultarCargos() throws Exception{
		try {
			String comando = String.format("SELECT * FROM `cargos`");

			
			ResultSet rs = st.executeQuery(comando);
			ArrayList<String> resultado = new ArrayList<>();
			while (rs.next()) {
				String cargo = rs.getString("cargo");
				resultado.add(cargo);
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
	
	public static void CriarBdFuncionarios(){
		try {
			String query = "create database if not exists restaurante";
			st = conn.createStatement();
			st.execute(query);
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/restaurante", "root", null);
			System.out.println("BD funcionarios CRIADO!");
		} catch (SQLException e) {
			System.out.println("Erro ao criar database empresa ");
			e.printStackTrace();
		}
		try {
			String query = "create table if not exists funcionarios (codFunc integer not null AUTO_INCREMENT, nome varchar(30) not null, cargo varchar(1024) not null,  PRIMARY KEY(codFunc))";
			st = conn.createStatement();
			st.execute(query);
			System.out.println("BD funcionarios CRIADO!");
		} catch (SQLException e) {
			System.out.println("Erro ao criar bd funcionarios ");
			e.printStackTrace();
		}
		try {
			String query = "create table if not exists cargos (codigo integer not null AUTO_INCREMENT, cargo varchar(30) not null,  PRIMARY KEY(codigo))";
			st = conn.createStatement();
			st.execute(query);
			System.out.println("BD funcionarios CRIADO!");
		} catch (SQLException e) {
			System.out.println("Erro ao criar bd funcionarios ");
			e.printStackTrace();
		}
	}
	
	public static void MockarBdPrato() {

		FuncionariosModel prod1 = new FuncionariosModel();
		prod1.nome = "func 1";
		prod1.cargo = "garçom";
		InserirFuncionario(prod1);
		
		FuncionariosModel prod2 = new FuncionariosModel();
		prod2.nome = "func 2";
		prod2.cargo = "cozinheiro";
		InserirFuncionario(prod2);
		
		FuncionariosModel prod3 = new FuncionariosModel();;
		prod3.nome = "func 3";
		prod3.cargo = "gerente";
		InserirFuncionario(prod3);
		InserirCargo();
	}
}
