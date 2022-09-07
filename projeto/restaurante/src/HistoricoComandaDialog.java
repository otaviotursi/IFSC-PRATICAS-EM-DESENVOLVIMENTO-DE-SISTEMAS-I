
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class HistoricoComandaDialog extends JDialog  implements ActionListener {

	JPanel panelNorth;
	JTable jt=new JTable();   
	DefaultTableModel tableModel;
	JScrollPane sp;
	
	JButton buttonConsultarComanda;
	JButton buttonExcluirComanda;

	JLabel labelCodMesa;
	JComboBox<String> comboCodMesa;
	
	HistoricoComandaBD histComandaBD = new HistoricoComandaBD();
	
	public HistoricoComandaDialog(JFrame owner) {
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
		
		
		labelCodMesa = new JLabel("Código Mesa: ");
		labelCodMesa.setSize(40, 20);
		//panelCenter.add(labelCodMesa);

		ArrayList<MesasModel> mesas = MesasBD.ConsultarMesaTodos();
        comboCodMesa = new JComboBox<String>();
		 for(MesasModel mesa : mesas) {
			 comboCodMesa.addItem(mesa.codigoMesa+"");
		}
		//panelCenter.add(comboCodMesa);
	
		
		
        

		JPanel panelSouth = new JPanel();
		panelSouth.setSize(500,100);
		GridLayout layoutSouth = new GridLayout(0,2);
		panelSouth.setLayout(layoutPrincipal);
		buttonConsultarComanda = new JButton("Consultar Comandas"); 
		buttonConsultarComanda.addActionListener(this);
		buttonExcluirComanda = new JButton("Deletar Historico Comandas"); 
		buttonExcluirComanda.addActionListener(this);
		panelSouth.add(buttonConsultarComanda);
		panelSouth.add(buttonExcluirComanda);
		
		
		panel.add(panelNorth, BorderLayout.NORTH);
		panel.add(panelCenter, BorderLayout.CENTER);
		panel.add(panelSouth, BorderLayout.SOUTH);
		frameComanda.getContentPane().add(panel);
		frameComanda.pack();
	}

	private void ConsultarComanda() {
		
		ArrayList<HistoricoComandaModel> listaComanda;
		try {
			listaComanda = HistoricoComandaBD.ConsultarHistorico();
			Object[] column={"Data", "Mesa / Comanda", "Produtos", "Valor","status"};   
			tableModel = new DefaultTableModel(column, 0);
			tableModel.addRow(column);
			
			for (HistoricoComandaModel comanda:listaComanda) {
				Object[] data = {comanda.data, comanda.codigoMesa, comanda.produtos, comanda.valor,comanda.statusPagamento};
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

		String produtoTexto = (String) comboCodMesa.getSelectedItem();
		
		long codigoMesa = 0;
		
		
		
		System.out.println("codigoMesa: "+codigoMesa);
		System.out.println("produto: "+produtoTexto);

		
		
		switch (comando) {
			case "Consultar Comandas":
				ConsultarComanda();
				break;

				
			case "Deletar Historico Comandas":
				try {
					HistoricoComandaBD.DeletarHistorico();
					ConsultarComanda();
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
		}
		
	}

	
}
