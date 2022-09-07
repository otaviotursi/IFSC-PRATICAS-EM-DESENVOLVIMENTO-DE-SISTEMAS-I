
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class FuncionarioDialog extends JDialog  implements ActionListener {

	JPanel panelNorth;
	JTable jt=new JTable();   
	DefaultTableModel tableModel;
	JScrollPane sp;
	
	JButton buttonInserirNovoFunc;
	JButton buttonAlterarFunc;
	JButton buttonConsultarFunc;
	JButton buttonExcluirFunc;

	JLabel labelNomeFunc;
	JLabel labelCargoFunc;
	JLabel labelCodFunc;
	JTextField textNomeFunc;
	
	JComboBox<String> comboCargoFunc;
	JComboBox<String> comboCodFunc;
	
	FuncionariosBD funcBd = new FuncionariosBD();
	
	public FuncionarioDialog(JFrame owner) {
        super(owner, "Gerenciar Funcionarios", true);
    }
	
	public void start() throws Exception {
		JFrame frameFuncionario = new JFrame("Dialog - Gerenciar Funcionarios");
		frameFuncionario.setSize(550,350);
		frameFuncionario.setVisible(true);
		//frameFuncionario.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setSize(525,325);
		panel.setLayout(new BorderLayout());
		
		panelNorth = new JPanel();
		panelNorth.setSize(100,100);
		panelNorth.setLayout(new BorderLayout());
		
		ConsultarFuncionarios();

	    panelNorth.add(jt);

	    
		JPanel panelCenter = new JPanel();
		panelCenter.setSize(500,100);
		panel.setLayout(new BorderLayout());
		GridLayout layoutPrincipal = new GridLayout(0,2);
		panelCenter.setLayout(layoutPrincipal);
		
		labelNomeFunc = new JLabel("Nome: ");
		labelNomeFunc.setSize(40, 20);
		textNomeFunc = new JTextField(10);
		textNomeFunc.setSize(40, 20);
		panelCenter.add(labelNomeFunc);
		panelCenter.add(textNomeFunc);
		
		labelCargoFunc = new JLabel("Cargo: ");
		labelCargoFunc.setSize(40, 20);
		panelCenter.add(labelCargoFunc);
		ArrayList<String> cargos = FuncionariosBD.ConsultarCargos();
		comboCargoFunc = new JComboBox<String>();
		comboCargoFunc.addItem("");
		for(String cargo: cargos) {
			comboCargoFunc.addItem(cargo+"");
		}
		panelCenter.add(comboCargoFunc);

		labelCodFunc = new JLabel("Código: ");
		labelCodFunc.setSize(40, 20);
		ArrayList<FuncionariosModel> funcionarios = FuncionariosBD.ConsultarFuncionarios();
		comboCodFunc = new JComboBox<String>();
		comboCodFunc.addItem("");
		for(FuncionariosModel func: funcionarios) {
			comboCodFunc.addItem(func.codigo+"");
		}
		panelCenter.add(labelCodFunc);
		panelCenter.add(comboCodFunc);
		

		JPanel panelSouth = new JPanel();
		panelSouth.setSize(500,100);
		GridLayout layoutSouth = new GridLayout(0,2);
		panelSouth.setLayout(layoutSouth);
		buttonInserirNovoFunc = new JButton("Inserir Funcionario"); 
		buttonInserirNovoFunc.addActionListener(this);
		buttonAlterarFunc = new JButton("Alterar Funcionario"); 
		buttonAlterarFunc.addActionListener(this);
		buttonConsultarFunc = new JButton("Consultar Funcionarios"); 
		buttonConsultarFunc.addActionListener(this);
		buttonExcluirFunc = new JButton("Deletar Funcionario"); 
		buttonExcluirFunc.addActionListener(this);
		panelSouth.add(buttonInserirNovoFunc);
		panelSouth.add(buttonExcluirFunc);
		panelSouth.add(buttonAlterarFunc);
		panelSouth.add(buttonConsultarFunc);
		
		
		panel.add(panelNorth, BorderLayout.NORTH);
		panel.add(panelCenter, BorderLayout.CENTER);
		panel.add(panelSouth, BorderLayout.SOUTH);
		frameFuncionario.getContentPane().add(panel);
		frameFuncionario.pack();
	}

	private void ConsultarFuncionarios() {
		
		ArrayList<FuncionariosModel> listaFuncionarios;
		try {
			listaFuncionarios = FuncionariosBD.ConsultarFuncionarios();
			Object[] column={"ID","Nome","Cargo"};   
			tableModel = new DefaultTableModel(column, 0);
			tableModel.addRow(column);
			
			for (FuncionariosModel func:listaFuncionarios) {
				Object[] data = {func.codigo, func.nome, func.cargo};
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

		String nome = textNomeFunc.getText();
		String cargo = (String) comboCargoFunc.getSelectedItem();
		String codigoTexto = (String) comboCodFunc.getSelectedItem();
		long codigo = 0;
		if (codigoTexto.length() > 0) {
			codigo = Long.parseLong(codigoTexto);
		}
		System.out.println("nome: "+nome);
		System.out.println("cargo: "+cargo);
		System.out.println("codigoTexto: "+codigoTexto);

		
		
		switch (comando) {
			case "Inserir Funcionario":
				try {
					FuncionariosModel novoFunc = new FuncionariosModel();
					novoFunc.cargo = cargo;
					novoFunc.nome = nome;
					FuncionariosBD.InserirFuncionario(novoFunc);
					ConsultarFuncionarios();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;

			case "Alterar Funcionario":
				FuncionariosModel altFunc = new FuncionariosModel();
				altFunc.cargo = cargo;
				altFunc.nome = nome;
				altFunc.codigo = codigo;
				FuncionariosBD.AtualizarFuncionario(altFunc);
				ConsultarFuncionarios();
				break;
				
			case "Consultar Funcionarios":
				ConsultarFuncionarios();
				break;
				
			case "Deletar Funcionario":
				try {
					funcBd.DeletarFuncionario(codigo);
					ConsultarFuncionarios();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
		}
		
	}
	
}
