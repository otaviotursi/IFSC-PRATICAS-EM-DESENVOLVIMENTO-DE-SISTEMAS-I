
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ComandaDialog extends JDialog  implements ActionListener {

	String textoValorComanda = "";
	double valorComanda = 0;
	
	JPanel panelNorth;
	JTable jt=new JTable();   
	DefaultTableModel tableModel;
	JScrollPane sp;
	
	JButton buttonInserirNovoComanda;
	JButton buttonConsultarComanda;
	JButton buttonConsultarComandaUni;
	JButton buttonExcluirComanda;
	JButton buttonExcluirProdutoComanda;
	JButton buttonValorComanda;
	JButton buttonPagarComanda;

	
	JLabel labelCodMComanda;
	JLabel labelCodMesa;
	JLabel labelProdutoPratoComanda;
	JLabel labelProdutoBebidaComanda;
	JLabel labelRespComanda;
	JLabel labelValorComanda;
	JLabel labelStatusPagamento;

	//JTextField textCodMesa;
	//JTextField textProdutoComanda;
	//JTextField textRespComanda;
	
	JComboBox<String> comboCodigoComanda;
	JComboBox<String> comboCodMesa;
	JComboBox<String> comboProdutoPratoComanda;
	JComboBox<String> comboProdutoBebidaComanda;
	JComboBox<String> comboResp;
	JComboBox<String> comboStatusPagamento;
	
	
	ComandaBD comandaBd = new ComandaBD();
	
	public ComandaDialog(JFrame owner) {
        super(owner, "Gerenciar Comanda", true);
    }
	
	public void start() throws Exception {
		JFrame frameComanda = new JFrame("Dialog - Gerenciar comandas");
		frameComanda.setSize(550,350);
		frameComanda.setVisible(true);
		//frameComanda.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setSize(525,325);
		panel.setLayout(new BorderLayout());
		
		panelNorth = new JPanel();
		panelNorth.setSize(100,100);
		panelNorth.setLayout(new BorderLayout());
		
		ConsultarComanda();

	    panelNorth.add(jt);

	    
		JPanel panelCenter = new JPanel();
		panelCenter.setSize(500,100);
		panel.setLayout(new BorderLayout());
		GridLayout layoutPrincipal = new GridLayout(0,2);
		panelCenter.setLayout(layoutPrincipal);
		
		

		//Codigo Comanda
		labelCodMComanda = new JLabel("Código Comanda: ");
		labelCodMComanda.setSize(40, 20);
		panelCenter.add(labelCodMComanda);
		ArrayList<ComandaModel> listaComanda = ComandaBD.ConsultarComandaTodos();
		comboCodigoComanda = new JComboBox<String>();
		comboCodigoComanda.addItem("");
		for (ComandaModel comanda:listaComanda) {	
			comboCodigoComanda.addItem(comanda.codigoId+"");
		}
		panelCenter.add(comboCodigoComanda);
		
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
		
		panelCenter.add(comboCodMesa);
		
		
		
		//PRODUTO - Prato
		labelProdutoPratoComanda = new JLabel("Prato: ");
		labelProdutoPratoComanda.setSize(40, 20);
		panelCenter.add(labelProdutoPratoComanda);
		
		ArrayList<PratosModel> pratos = PratosBD.ConsultarPratosTodos();
		comboProdutoPratoComanda = new JComboBox<String>();
		comboProdutoPratoComanda.addItem("");
		for(PratosModel prato: pratos) {
			comboProdutoPratoComanda.addItem(prato.codigo+"");
		}
		panelCenter.add(comboProdutoPratoComanda);
		
		

		//PRODUTO - Bebida
		labelProdutoBebidaComanda = new JLabel("Bebida: ");
		labelProdutoBebidaComanda.setSize(40, 20);
		panelCenter.add(labelProdutoBebidaComanda);
		
		ArrayList<BebidaModel> bebidas = BebidaBD.ConsultarBebidaTodos();
		comboProdutoBebidaComanda = new JComboBox<String>();
		comboProdutoBebidaComanda.addItem("");
		for(BebidaModel bebida : bebidas ) {
			comboProdutoBebidaComanda.addItem(bebida.codigo+"");
		}
		panelCenter.add(comboProdutoBebidaComanda);
		
		
		
		
		//RESPONSAVEL
		labelRespComanda = new JLabel("Responsavel: ");
		labelRespComanda.setSize(40, 20);
		panelCenter.add(labelRespComanda);
		ArrayList<FuncionariosModel> funcionarios = FuncionariosBD.ConsultarFuncionarios();
        comboResp = new JComboBox<String>();

		for(FuncionariosModel func : funcionarios ) {
			 comboResp.addItem(func.codigo+"");
		}
		panelCenter.add(comboResp);
		
		//Status Pagamento
		labelStatusPagamento = new JLabel("Status Pagamento: ");
		labelStatusPagamento.setSize(40, 20);
		panelCenter.add(labelStatusPagamento);
		
		comboStatusPagamento = new JComboBox<String>();

		ArrayList<String> listaStatusPagamento = ComandaBD.ConsultarTodosStatusPagamento();
		for(String statusPagamento : listaStatusPagamento) {
			comboStatusPagamento.addItem(statusPagamento+"");
		}
		panelCenter.add(comboStatusPagamento);


		//Valor comanda
		labelValorComanda = new JLabel("comanda: 0 - valor: 0");
		labelValorComanda.setSize(40, 20);
		panelCenter.add(labelValorComanda);
		JLabel labelValorComandaEspaco = new JLabel("");
		labelValorComandaEspaco.setSize(40, 20);
		panelCenter.add(labelValorComandaEspaco);
		
		
		JPanel panelSouth = new JPanel();
		panelSouth.setSize(500,100);
		GridLayout layoutSouth = new GridLayout(0,2);
		panelSouth.setLayout(layoutPrincipal);
		buttonInserirNovoComanda = new JButton("Inserir Produto Mesa"); 
		buttonInserirNovoComanda.addActionListener(this);
		buttonConsultarComanda = new JButton("Consultar Mesas"); 
		buttonConsultarComanda.addActionListener(this);
		buttonConsultarComandaUni = new JButton("Consultar Mesa Unica"); 
		buttonConsultarComandaUni.addActionListener(this);
		buttonExcluirComanda = new JButton("Deletar Mesa"); 
		buttonExcluirComanda.addActionListener(this);
		buttonExcluirProdutoComanda = new JButton("Deletar Produto Mesa"); 
		buttonExcluirProdutoComanda.addActionListener(this);
		buttonValorComanda = new JButton("Consultar valor Mesa"); 
		buttonValorComanda.addActionListener(this);
		buttonPagarComanda = new JButton("Pagar Mesa"); 
		buttonPagarComanda.addActionListener(this);
		panelSouth.add(buttonInserirNovoComanda);
		panelSouth.add(buttonConsultarComandaUni);
		panelSouth.add(buttonConsultarComanda);
		panelSouth.add(buttonExcluirComanda);
		panelSouth.add(buttonExcluirProdutoComanda);
		panelSouth.add(buttonValorComanda);
		panelSouth.add(buttonPagarComanda);
		
		
		panel.add(panelNorth, BorderLayout.NORTH);
		panel.add(panelCenter, BorderLayout.CENTER);
		panel.add(panelSouth, BorderLayout.SOUTH);
		frameComanda.getContentPane().add(panel);
		frameComanda.pack();
	}

	private void ConsultarComanda() {
		
		ArrayList<ComandaModel> listaComanda;
		try {
			listaComanda = ComandaBD.ConsultarComandaTodos();
			Object[] column={"ID","Mesa / Comanda", "Produto", "Responsavel"};   
			tableModel = new DefaultTableModel(column, 0);
			tableModel.addRow(column);
			
			for (ComandaModel comanda:listaComanda) {
				Object[] data = {comanda.codigoId,comanda.codigoMesa, comanda.produto, comanda.responsavel};
				tableModel.addRow(data);
			}
			
		    jt.setModel(tableModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void CalcularValorComanda(long codigoComanda) throws Exception {
		valorComanda = 0;
		textoValorComanda = "comanda: "+0+" - valor: "+0+"";
		ArrayList<ComandaModel> listaComanda = ComandaBD.ConsultarComandaUn(codigoComanda);
		for (ComandaModel comanda:listaComanda) {	
			long codigo = comanda.produto;
			if((codigo+"").startsWith("1")) {
				//prato
				var res = PratosBD.ConsultarPratosUn(codigo);
				valorComanda += res.preco;
			} else if ((codigo+"").startsWith("2")) {
				//bebida
				var res = BebidaBD.ConsultarBebidaUn(codigo);
				valorComanda += res.preco;
				
			}
		}
		textoValorComanda = "comanda: "+codigoComanda+" - valor: "+valorComanda+"";
		labelValorComanda.setText(textoValorComanda);
	}
	
	@Override
	public void actionPerformed(ActionEvent action) {
		String comando = action.getActionCommand();
		System.out.println("comando 1: "+comando);


		String codigoComandaTexto = (String) comboCodigoComanda.getSelectedItem();
		String produtoPratoTexto = (String) comboProdutoPratoComanda.getSelectedItem();
		String produtoBebidaTexto = (String) comboProdutoBebidaComanda.getSelectedItem();
		String codigoMesaTexto = (String) comboCodMesa.getSelectedItem();
		String responsavelTexto = (String) comboResp.getSelectedItem();
		String statusPagamentoTexto = (String) comboStatusPagamento.getSelectedItem();
		
		long codigoComanda = 0;
		long codigoMesa = 0;
		long codigoProdutoBebida = 0;
		long codigoProdutoPrato = 0;

		if (codigoComandaTexto.length() > 0) {
			codigoComanda = Long.parseLong(codigoComandaTexto);
		}
		if (codigoMesaTexto.length() > 0) {
			codigoMesa = Long.parseLong(codigoMesaTexto);
		}
		if (produtoPratoTexto.length() > 0) {
			codigoProdutoPrato = Long.parseLong(produtoPratoTexto);
		}
		if (produtoBebidaTexto.length() > 0) {
			codigoProdutoBebida = Long.parseLong(produtoBebidaTexto);
		}
		

		System.out.println("codigoComanda: "+codigoComanda);
		System.out.println("codigoMesa: "+codigoMesa);
		System.out.println("codigoProdutoPrato: "+codigoProdutoPrato);
		System.out.println("codigoProdutoBebida: "+codigoProdutoBebida);
		ArrayList<Long> produtos = new ArrayList<Long>();
		produtos.add(codigoProdutoPrato);
		produtos.add(codigoProdutoBebida);
		
		switch (comando) {
			case "Inserir Produto Mesa":
				try {
					for(Long prod: produtos) {
						
						ComandaModel novaComanda = new ComandaModel();
						novaComanda.codigoMesa = codigoMesa;
						novaComanda.produto = prod;
						novaComanda.responsavel = responsavelTexto;

						PedidosCozinhaModel pedidoCozinha = new PedidosCozinhaModel();
						pedidoCozinha.codigoMesa = codigoMesa;
						pedidoCozinha.codigoProduto = prod;
						pedidoCozinha.status = "A FAZER";
						
						if(!prod.toString().equalsIgnoreCase("0")) {	
							PedidosCozinhaBD.InserirPedidoCozinha(pedidoCozinha);
							ComandaBD.InserirPratoEmComanda(novaComanda);
						}
					}
					ConsultarComanda();
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				
			case "Consultar Mesas":
				ConsultarComanda();
				break;

			case "Consultar valor Mesa":
				try {
					CalcularValorComanda(codigoMesa);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				break;
				
			case "Consultar Mesa Unica":
				ConsultarComandaUn(codigoMesa);
				
				break;

			case "Deletar Produto Mesa":
				try {
					for(Long prod: produtos) {
						ComandaModel deletarProdutoComanda = new ComandaModel();
						deletarProdutoComanda.codigoId = codigoComanda;
						ComandaBD.DeletarProdutoComanda(deletarProdutoComanda);
					}
					ConsultarComanda();
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				
			case "Pagar Mesa":
				try {
					CalcularValorComanda(codigoMesa);
					ArrayList<ComandaModel> listaComanda = ComandaBD.ConsultarComandaTodos();
					
					HistoricoComandaModel historico = new HistoricoComandaModel();
					historico.codigoMesa = codigoMesa;
					String prods = "";
					for(ComandaModel prod: listaComanda) {
						if(!prod.toString().equalsIgnoreCase("0") && !prods.contains(prod+"") && prod.codigoMesa == codigoMesa) {		
							prods += prod.produto + ";";
						}
					}
					historico.produtos = prods;
					historico.statusPagamento = statusPagamentoTexto;
					historico.valor = valorComanda;
					HistoricoComandaBD.InserirHistorico(historico);
					ComandaBD.DeletarComanda(codigoMesa);
					ConsultarComanda();
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				
			case "Deletar Mesa":
				try {
					ComandaBD.DeletarComanda(codigoMesa);
					ConsultarComanda();
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				
		}
		
	}

	private void ConsultarComandaUn(long codigoProduto) {
		ArrayList<ComandaModel> listaComanda;
		try {
			listaComanda = ComandaBD.ConsultarComandaUn(codigoProduto);
			Object[] column={"Mesa / Comanda", "Produto", "Responsavel"};   
			tableModel = new DefaultTableModel(column, 0);
			tableModel.addRow(column);
			
			for (ComandaModel comanda:listaComanda) {
				Object[] data = {comanda.codigoMesa, comanda.produto, comanda.responsavel};
				tableModel.addRow(data);
			}
			
		    jt.setModel(tableModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
