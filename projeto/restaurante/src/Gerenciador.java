

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



/*Sistema para restaurante*/

public class Gerenciador  extends JFrame implements ActionListener {
	
	JFrame frame;
	
	JButton buttonInserirFuncionario;
	JButton buttonInserirNovoPrato;
	JButton buttonInserirNovaMesa;
	JButton buttonInserirNovaBebida;
	JButton buttonRealizarPedido;
	JButton buttonGerenciarComanda;
	JButton buttonHistoricoComanda;
	JButton buttonConsultarPedidosCozinha;
	JButton buttonFecharComanda;
	
	private void start(){
		frame = new JFrame("Restaurante");
		frame.setSize(550,350);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		GridLayout layoutPrincipal = new GridLayout(0,2);
		
		frame.setLayout(layoutPrincipal);
		

		buttonInserirFuncionario = new JButton("Gerenciar Funcionario");
		buttonInserirFuncionario.addActionListener(this);
		frame.add(buttonInserirFuncionario);

		buttonInserirNovoPrato = new JButton("Gerenciar Prato");
		buttonInserirNovoPrato.addActionListener(this);
		frame.add(buttonInserirNovoPrato);
		
		buttonInserirNovaMesa = new JButton("Gerenciar Mesa");
		buttonInserirNovaMesa.addActionListener(this);
		frame.add(buttonInserirNovaMesa);
		
		buttonInserirNovaBebida = new JButton("Gerenciar Bebida");
		buttonInserirNovaBebida.addActionListener(this);
		frame.add(buttonInserirNovaBebida);

		buttonGerenciarComanda = new JButton("Gerenciar Comanda");
		buttonGerenciarComanda.addActionListener(this);
		frame.add(buttonGerenciarComanda);
		
		buttonHistoricoComanda = new JButton("Historico de Comandas");
		buttonHistoricoComanda.addActionListener(this);
		frame.add(buttonHistoricoComanda);


		buttonConsultarPedidosCozinha = new JButton("Gerenciar Pedidos Cozinha");
		buttonConsultarPedidosCozinha.addActionListener(this);
		frame.add(buttonConsultarPedidosCozinha);

	}
	
	public static void main(String[] args){
		new GerenciadorBanco();
		SwingUtilities.invokeLater(() -> new Gerenciador().start());
	}
	
	private JFrame getInstance() {
        return this;
    }
	
	@Override
	public void actionPerformed(ActionEvent arg) {
		String comando = arg.getActionCommand();
		System.out.println("comando: "+comando);
		
		
		switch (comando) {
			case "Gerenciar Funcionario":
				try {
					new FuncionarioDialog(getInstance()).start();
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				
			case "Gerenciar Bebida":
				try {
					new BebidasDialog(getInstance()).start();
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				
			case "Gerenciar Prato":
				try {
					new PratosDialog(getInstance()).start();
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				
			case "Gerenciar Mesa":
				try {
					new MesasDialog(getInstance()).start();
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;

			case "Gerenciar Comanda":
				try {
					new ComandaDialog(getInstance()).start();
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				
			case "Historico de Comandas":
				try {
					new HistoricoComandaDialog(getInstance()).start();
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				
			case "Gerenciar Pedidos Cozinha":
				try {
					new PedidosCozinhaDialog(getInstance()).start();
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
		}
		
	}
	
	
}
