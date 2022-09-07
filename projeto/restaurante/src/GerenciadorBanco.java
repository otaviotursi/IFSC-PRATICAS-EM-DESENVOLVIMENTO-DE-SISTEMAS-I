

import java.sql.*;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



/*Sistema para gerenciamento de estoque*/

public class GerenciadorBanco{

	private static PratosBD pratosBd;
	private static PratosModel pratosMd;
	private static ComandaBD comandaBd;
	private static ComandaModel comandaMd;
	private static MesasBD mesasBd;
	private static MesasModel mesasMd;
	private static HistoricoComandaBD historicoBd;
	private static HistoricoComandaModel historicoMd;
	private static BebidaBD bebidasBd;
	private static BebidaModel bebidasMd;
	private static FuncionariosBD funcionariosBd;
	private static FuncionariosModel funcionariosMd;
	private static PedidosCozinhaBD pedidosCozBd;
	private static PedidosCozinhaModel pedidosCozMd;
	
	public GerenciadorBanco() {

		PratosBD.AbrirConexao();
		PratosBD.CriarBdPratos();

		ComandaBD.AbrirConexao();
		ComandaBD.CriarBdComanda();

		MesasBD.AbrirConexao();
		MesasBD.CriarBdMesas();

		HistoricoComandaBD.AbrirConexao();
		HistoricoComandaBD.CriarBdHistorico();
		
		BebidaBD.AbrirConexao();
		BebidaBD.CriarBdBebida();
		
		PratosBD.AbrirConexao();
		PratosBD.CriarBdPratos();

		FuncionariosBD.AbrirConexao();
		FuncionariosBD.CriarBdFuncionarios();
		
		PedidosCozinhaBD.AbrirConexao();
		PedidosCozinhaBD.CriarBdPedidos();
		
		//ComandaBD.MockarBdComanda();
		/*
		MesasBD.MockarBdMesas();
		PratosBD.MockarBdPrato();
		BebidaBD.MockarBdBebida();
		PratosBD.MockarBdPrato();
		FuncionariosBD.MockarBdPrato();
		HistoricoComandaBD.MockarBdHistorico();
		PedidosCozinhaBD.MockarBdPedido();
		*/
		
	}
	
	
}
