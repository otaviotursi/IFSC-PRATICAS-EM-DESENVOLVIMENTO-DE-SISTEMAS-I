
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PratosDialog extends JDialog  implements ActionListener {

	JPanel panelNorth;
	JTable jt=new JTable();   
	DefaultTableModel tableModel;
	JScrollPane sp;
	
	JButton buttonInserirNovoPrato;
	JButton buttonConsultarPrato;
	JButton buttonExcluirPrato;

	JLabel labelNomePrato;
	JLabel labelDescricaoPrato;
	JLabel labelCodPrato;
	JLabel labelPrecoPrato;
	JTextField textNomePrato;
	JTextField textDescricaoPrato;
	JTextField textPrecoPrato;
	JTextField textCodPrato;
	
	PratosBD pratoBd = new PratosBD();
	
	public PratosDialog(JFrame owner) {
        super(owner, "Gerenciar Pratos", true);
    }
	
	public void start() {
		JFrame framePrato = new JFrame("Dialog - Gerenciar Pratos");
		framePrato.setSize(550,350);
		framePrato.setVisible(true);
		//framePrato.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setSize(525,325);
		panel.setLayout(new BorderLayout());
		
		panelNorth = new JPanel();
		panelNorth.setSize(100,100);
		panelNorth.setLayout(new BorderLayout());
		
		ConsultarPrato();

	    panelNorth.add(jt);

	    
		JPanel panelCenter = new JPanel();
		panelCenter.setSize(500,100);
		panel.setLayout(new BorderLayout());
		GridLayout layoutPrincipal = new GridLayout(0,2);
		panelCenter.setLayout(layoutPrincipal);
		
		labelNomePrato = new JLabel("Nome: ");
		labelNomePrato.setSize(40, 20);
		textNomePrato = new JTextField(10);
		textNomePrato.setSize(40, 20);
		panelCenter.add(labelNomePrato);
		panelCenter.add(textNomePrato);
		
		labelDescricaoPrato = new JLabel("Descricao: ");
		labelDescricaoPrato.setSize(40, 20);
		textDescricaoPrato = new JTextField(10);
		textDescricaoPrato.setSize(40, 20);
		panelCenter.add(labelDescricaoPrato);
		panelCenter.add(textDescricaoPrato);


		labelPrecoPrato = new JLabel("Preco: ");
		labelPrecoPrato.setSize(40, 20);
		textPrecoPrato = new JTextField(10);
		textPrecoPrato.setSize(40, 20);
		panelCenter.add(labelPrecoPrato);
		panelCenter.add(textPrecoPrato);

		labelCodPrato = new JLabel("Código: ");
		labelCodPrato.setSize(40, 20);
		textCodPrato = new JTextField(10);
		textCodPrato.setSize(40, 20);
		panelCenter.add(labelCodPrato);
		panelCenter.add(textCodPrato);

		JPanel panelSouth = new JPanel();
		panelSouth.setSize(500,100);
		panelSouth.setLayout(new BorderLayout());
		buttonInserirNovoPrato = new JButton("Inserir Prato"); 
		buttonInserirNovoPrato.addActionListener(this);
		buttonConsultarPrato = new JButton("Consultar Prato"); 
		buttonConsultarPrato.addActionListener(this);
		buttonExcluirPrato = new JButton("Deletar Prato"); 
		buttonExcluirPrato.addActionListener(this);
		panelSouth.add(buttonInserirNovoPrato, BorderLayout.WEST);
		panelSouth.add(buttonConsultarPrato, BorderLayout.CENTER);
		panelSouth.add(buttonExcluirPrato, BorderLayout.EAST);
		
		
		panel.add(panelNorth, BorderLayout.NORTH);
		panel.add(panelCenter, BorderLayout.CENTER);
		panel.add(panelSouth, BorderLayout.SOUTH);
		framePrato.getContentPane().add(panel);
		framePrato.pack();
	}

	private void ConsultarPrato() {
		
		ArrayList<PratosModel> listaPrato;
		try {
			listaPrato = pratoBd.ConsultarPratosTodos();
			Object[] column={"ID","Nome","Descricao","Preco"};   
			tableModel = new DefaultTableModel(column, 0);
			tableModel.addRow(column);
			
			for (PratosModel prato:listaPrato) {
				Object[] data = {prato.codigo, prato.nome, prato.descricao, prato.preco};
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

		String nome = textNomePrato.getText();
		String descricao = textDescricaoPrato.getText();
		String codigoTexto = textCodPrato.getText();
		String precoTexto = textPrecoPrato.getText();
		
		long codigo = 0;
		double preco=0;
		
		if (codigoTexto.length() > 0) {
			codigo = Long.parseLong(codigoTexto);
		}
		if (precoTexto.length() > 0) {
			preco = Double.parseDouble(precoTexto);
		}
		
		System.out.println("nome: "+nome);
		System.out.println("descricao: "+descricao);
		System.out.println("codigoTexto: "+codigoTexto);
		System.out.println("preco: "+preco);

		
		
		switch (comando) {
			case "Inserir Prato":
				try {
					PratosModel novoPrato = new PratosModel();
					novoPrato.descricao = descricao;
					novoPrato.preco = preco;
					novoPrato.nome = nome;
					pratoBd.InserirPrato(novoPrato);
					ConsultarPrato();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
				
			case "Consultar Prato":
				ConsultarPrato();
				break;
				
			case "Deletar Prato":
				try {
					pratoBd.DeletarPrato(codigo);
					ConsultarPrato();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
		}
		
	}
	
}
