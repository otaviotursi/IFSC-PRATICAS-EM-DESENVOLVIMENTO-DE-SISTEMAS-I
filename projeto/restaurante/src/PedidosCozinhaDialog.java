
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PedidosCozinhaDialog extends JDialog  implements ActionListener {

	JPanel panelNorth;
	JTable jt=new JTable();   
	DefaultTableModel tableModel;
	JScrollPane sp;
	
	JButton buttonAtualizarPedido;
	JButton buttonConsultarPedido;
	JButton buttonExcluirPedido;
	JButton buttonExcluirPedidoUni;

	JLabel labelCodId;
	JLabel labelCodMesa;
	JLabel labelCodProduto;
	JLabel labelStatus;
	JTextField textCodProduto;

	JComboBox<String> comboCodId;
	JComboBox<String> comboCodMesa;
	JComboBox<String> comboStatus;
	
	PedidosCozinhaBD pedidosCozBD = new PedidosCozinhaBD();
	
	public PedidosCozinhaDialog(JFrame owner) {
        super(owner, "Gerenciar Comanda", true);
    }
	
	public void start() throws Exception {
		JFrame framePedidosCoz = new JFrame("Dialog - Gerenciar Pedidos Cozinha");
		framePedidosCoz.setSize(550,350);
		framePedidosCoz.setVisible(true);
		//framePedidosCoz.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setSize(525,325);
		panel.setLayout(new BorderLayout());
		
		panelNorth = new JPanel();
		panelNorth.setSize(100,100);
		panelNorth.setLayout(new BorderLayout());
		
		ConsultarPedidoCozinha();

	    panelNorth.add(jt);

	    
		JPanel panelCenter = new JPanel();
		panelCenter.setSize(500,100);
		panel.setLayout(new BorderLayout());
		GridLayout layoutPrincipal = new GridLayout(0,2);
		panelCenter.setLayout(layoutPrincipal);
		

		//codigo
		labelCodId = new JLabel("Código Id: ");
		labelCodId.setSize(40, 20);
		panelCenter.add(labelCodId);
		ArrayList<PedidosCozinhaModel> listaComanda = PedidosCozinhaBD.ConsultarPedidoCozinha();
        comboCodId = new JComboBox<String>();
        comboCodId.addItem("");

		for (PedidosCozinhaModel comanda:listaComanda) {
			comboCodId.addItem(comanda.codigoId+"");
		}
		panelCenter.add(comboCodId);
		
		
		//MESA
		labelCodMesa = new JLabel("Código Mesa: ");
		labelCodMesa.setSize(40, 20);
		panelCenter.add(labelCodMesa);
		ArrayList<MesasModel> mesas = MesasBD.ConsultarMesaTodos();
        comboCodMesa = new JComboBox<String>();
        comboCodMesa.addItem("");
		for(MesasModel mesa : mesas) {
			System.out.println("mesa.status: "+mesa.status);
			if(mesa.status.equalsIgnoreCase("ABERTO")) {				
				comboCodMesa.addItem(mesa.codigoMesa+"");
			}
		}
		
		panelCenter.add(labelCodMesa);
		panelCenter.add(comboCodMesa);
		
		labelCodProduto = new JLabel("Código Produto: ");
		labelCodProduto.setSize(40, 20);
		textCodProduto = new JTextField(10);
		textCodProduto.setSize(40, 20);
		panelCenter.add(labelCodProduto);
		panelCenter.add(textCodProduto);

		//Status Produtos
		labelStatus = new JLabel("Status: ");
		labelStatus.setSize(40, 20);
		panelCenter.add(labelStatus);
		ArrayList<String> listaStatus = PedidosCozinhaBD.ConsultarTodosStatus();
        comboStatus = new JComboBox<String>();
        comboStatus.addItem("");
		for(String status : listaStatus) {
			comboStatus.addItem(status+"");
		}
		
		panelCenter.add(labelStatus);
		panelCenter.add(comboStatus);
		
		
		JPanel panelSouth = new JPanel();
		panelSouth.setSize(500,100);
		GridLayout layoutSouth = new GridLayout(0,2);
		panelSouth.setLayout(layoutPrincipal);
		buttonAtualizarPedido = new JButton("Atualizar Pedido"); 
		buttonAtualizarPedido.addActionListener(this);
		buttonConsultarPedido = new JButton("Consultar Pedidos"); 
		buttonConsultarPedido.addActionListener(this);
		buttonExcluirPedido = new JButton("Deletar Historico Pedidos"); 
		buttonExcluirPedido.addActionListener(this);
		buttonExcluirPedidoUni = new JButton("Deletar Historico Pedido Unico"); 
		buttonExcluirPedidoUni.addActionListener(this);
		panelSouth.add(buttonAtualizarPedido);
		panelSouth.add(buttonConsultarPedido);
		panelSouth.add(buttonExcluirPedido);
		panelSouth.add(buttonExcluirPedidoUni);
		
		
		panel.add(panelNorth, BorderLayout.NORTH);
		panel.add(panelCenter, BorderLayout.CENTER);
		panel.add(panelSouth, BorderLayout.SOUTH);
		framePedidosCoz.getContentPane().add(panel);
		framePedidosCoz.pack();
	}

	private void ConsultarPedidoCozinha() {
		
		ArrayList<PedidosCozinhaModel> listaComanda;
		try {
			listaComanda = PedidosCozinhaBD.ConsultarPedidoCozinha();
			Object[] column={"ID","Cod Mesa", "Cod Produto", "STATUS"};   
			tableModel = new DefaultTableModel(column, 0);
			tableModel.addRow(column);
			
			for (PedidosCozinhaModel comanda:listaComanda) {
				Object[] data = {comanda.codigoId, comanda.codigoMesa, comanda.codigoProduto, comanda.status};
				tableModel.addRow(data);
			}
			
		    jt.setModel(tableModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent action) {
		String comando = action.getActionCommand();
		System.out.println("comando 1: "+comando);

		String codigoIdTexto = (String) comboCodId.getSelectedItem();
		String codigoMesaTexto = (String) comboCodMesa.getSelectedItem();
		String codigoProdutoTexto = textCodProduto.getText();
		String statusTexto =  (String) comboStatus.getSelectedItem();
		long codigoId = 0;
		long codigoMesa = 0;
		long codigoProduto = 0;

		if (codigoIdTexto.length() > 0) {
			codigoId = Long.parseLong(codigoIdTexto);
		}

		if (codigoMesaTexto.length() > 0) {
			codigoMesa = Long.parseLong(codigoMesaTexto);
		}

		if (codigoProdutoTexto.length() > 0) {
			codigoProduto = Long.parseLong(codigoProdutoTexto);
		}

		System.out.println("codigoId: "+codigoId);
		System.out.println("codigoMesa: "+codigoMesa);
		System.out.println("codigoProduto: "+codigoProduto + " " + codigoProdutoTexto);

		
		
		switch (comando) {
			case "Atualizar Pedido":
				try {

					PedidosCozinhaModel pedidosCoz = new PedidosCozinhaModel();
					pedidosCoz.codigoId = codigoId;
					pedidosCoz.codigoMesa = codigoMesa;
					pedidosCoz.codigoProduto = codigoProduto;
					pedidosCoz.status = statusTexto;
					
					PedidosCozinhaBD.AtualizarPedidoCozinha(pedidosCoz);
					ConsultarPedidoCozinha();
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case "Deletar Historico Pedido Unico":
				try {

					PedidosCozinhaModel pedidosCoz = new PedidosCozinhaModel();
					pedidosCoz.codigoId = codigoId;
					pedidosCoz.codigoMesa = codigoMesa;
					pedidosCoz.codigoProduto = codigoProduto;
					
					PedidosCozinhaBD.DeletarPedidoCozinhaUni(pedidosCoz);
					ConsultarPedidoCozinha();
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;

			case "Deletar Historico Pedidos":
				PedidosCozinhaBD.DeletarPedidosCozinha();
				break;
				
			case "Consultar Pedidos":
				ConsultarPedidoCozinha();
				break;

				
		}
		
	}

	
}
