
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class MesasDialog extends JDialog  implements ActionListener {

	JPanel panelNorth;
	JTable jt=new JTable();   
	DefaultTableModel tableModel;
	JScrollPane sp;
	
	JButton buttonInserirNovoMesa;
	JButton buttonAlterarMesa;
	JButton buttonConsultarMesa;
	JButton buttonConsultarMesaUni;
	JButton buttonExcluirMesa;

	JLabel labelCodMesa;
	JLabel labelStatusMesa;

	JComboBox<String> comboCodMesa;
	JComboBox<String> comboStatusMesa;
	
	MesasBD mesaBd = new MesasBD();
	
	public MesasDialog(JFrame owner) {
        super(owner, "Gerenciar Mesas", true);
    }
	
	public void start() throws Exception {
		JFrame frameMesa = new JFrame("Dialog - Gerenciar mesas");
		frameMesa.setSize(550,350);
		frameMesa.setVisible(true);
		//frameMesa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setSize(525,325);
		panel.setLayout(new BorderLayout());
		
		panelNorth = new JPanel();
		panelNorth.setSize(100,100);
		panelNorth.setLayout(new BorderLayout());
		
		ConsultarMesa();

	    panelNorth.add(jt);

	    
		JPanel panelCenter = new JPanel();
		panelCenter.setSize(500,100);
		panel.setLayout(new BorderLayout());
		GridLayout layoutPrincipal = new GridLayout(0,2);
		panelCenter.setLayout(layoutPrincipal);
		
		
		labelCodMesa = new JLabel("Código Mesa: ");
		labelCodMesa.setSize(40, 20);

		ArrayList<MesasModel> listaMesa = MesasBD.ConsultarMesaTodos();
		comboCodMesa = new JComboBox<String>();
		comboCodMesa.addItem("");
		for(MesasModel mesa : listaMesa ) {
			comboCodMesa.addItem(mesa.codigoMesa+"");
		}
		
		panelCenter.add(labelCodMesa);
		panelCenter.add(comboCodMesa);
		
		labelStatusMesa = new JLabel("Status: ");
		labelStatusMesa.setSize(40, 20);
		ArrayList<String> listaStatus = MesasBD.ConsultarTodosStatus();
		comboStatusMesa = new JComboBox<String>();
		comboStatusMesa.addItem("");
		for(String status : listaStatus ) {
			comboStatusMesa.addItem(status+"");
		}
		panelCenter.add(labelStatusMesa);
		panelCenter.add(comboStatusMesa);

		JPanel panelSouth = new JPanel();
		panelSouth.setSize(500,100);
		GridLayout layoutSouth = new GridLayout(0,2);
		panelSouth.setLayout(layoutPrincipal);
		buttonInserirNovoMesa = new JButton("Inserir Mesa"); 
		buttonInserirNovoMesa.addActionListener(this);
		buttonAlterarMesa = new JButton("Alterar Mesa"); 
		buttonAlterarMesa.addActionListener(this);
		buttonConsultarMesa = new JButton("Consultar Mesas"); 
		buttonConsultarMesa.addActionListener(this);
		buttonConsultarMesaUni = new JButton("Consultar Mesa Unica"); 
		buttonConsultarMesaUni.addActionListener(this);
		buttonExcluirMesa = new JButton("Deletar Mesa"); 
		buttonExcluirMesa.addActionListener(this);
		panelSouth.add(buttonInserirNovoMesa);
		panelSouth.add(buttonConsultarMesaUni);
		panelSouth.add(buttonConsultarMesa);
		panelSouth.add(buttonExcluirMesa);
		panelSouth.add(buttonAlterarMesa);
		
		
		panel.add(panelNorth, BorderLayout.NORTH);
		panel.add(panelCenter, BorderLayout.CENTER);
		panel.add(panelSouth, BorderLayout.SOUTH);
		frameMesa.getContentPane().add(panel);
		frameMesa.pack();
	}

	private void ConsultarMesa() {
		
		ArrayList<MesasModel> listaMesa;
		try {
			listaMesa = mesaBd.ConsultarMesaTodos();
			Object[] column={"ID", "Status"};   
			tableModel = new DefaultTableModel(column, 0);
			tableModel.addRow(column);
			
			for (MesasModel mesa:listaMesa) {
				Object[] data = {mesa.codigoMesa, mesa.status};
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

		String statusTexto = (String) comboStatusMesa.getSelectedItem();
		String codigoMesaTexto = (String) comboCodMesa.getSelectedItem();
		
		long codigoMesa = 0;
		
		if (codigoMesaTexto.length() > 0) {
			codigoMesa = Long.parseLong(codigoMesaTexto);
		}
		
		System.out.println("codigoMesa: "+codigoMesa);
		System.out.println("status: "+statusTexto);

		
		
		switch (comando) {
			case "Inserir Mesa":
				try {
					MesasBD.InserirNovaMesa();
					ConsultarMesa();
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				
			case "Alterar Mesa":
				try {
					MesasModel mesas = new MesasModel();
					mesas.codigoMesa = codigoMesa;
					mesas.status = statusTexto;
					MesasBD.AtualizarStatusMesa(mesas);
					ConsultarMesa();
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				
			case "Consultar Mesas":
				ConsultarMesa();
				break;
				
			case "Deletar Mesa":
				try {
					MesasBD.DeletarMesa(codigoMesa);
					ConsultarMesa();
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
		}
		
	}
	
}
