
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class BebidasDialog extends JDialog  implements ActionListener {

	JPanel panelNorth;
	JTable jt=new JTable();   
	DefaultTableModel tableModel;
	JScrollPane sp;
	
	JButton buttonInserirNovoBebida;
	JButton buttonConsultarBebida;
	JButton buttonExcluirBebida;

	JLabel labelNomeBebida;
	JLabel labelDescricaoBebida;
	JLabel labelCodBebida;
	JLabel labelPrecoBebida;
	JLabel labelAlcoolicoBebida;

	JTextField textNomeBebida;
	JTextField textDescricaoBebida;
	JTextField textPrecoBebida;
	JTextField textCodBebida;
	
	JRadioButton RadFalsoAlcoolicoBebida;
	JRadioButton RadVerdadeiroAlcoolicoBebida;
	
	BebidaBD bebidaBd = new BebidaBD();
	
	public BebidasDialog(JFrame owner) {
        super(owner, "Gerenciar Bebidas", true);
    }
	
	public void start() {
		JFrame frameBebida = new JFrame("Dialog - Gerenciar bebidas");
		frameBebida.setSize(550,350);
		frameBebida.setVisible(true);
		//frameBebida.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		panel.setSize(525,325);
		panel.setLayout(new BorderLayout());
		
		panelNorth = new JPanel();
		panelNorth.setSize(100,100);
		panelNorth.setLayout(new BorderLayout());
		
		ConsultarBebida();

	    panelNorth.add(jt);

	    
		JPanel panelCenter = new JPanel();
		panelCenter.setSize(500,100);
		panel.setLayout(new BorderLayout());
		GridLayout layoutPrincipal = new GridLayout(0,2);
		panelCenter.setLayout(layoutPrincipal);
		
		labelNomeBebida = new JLabel("Nome: ");
		labelNomeBebida.setSize(40, 20);
		textNomeBebida = new JTextField(10);
		textNomeBebida.setSize(40, 20);
		panelCenter.add(labelNomeBebida);
		panelCenter.add(textNomeBebida);
		
		labelDescricaoBebida = new JLabel("Descricao: ");
		labelDescricaoBebida.setSize(40, 20);
		textDescricaoBebida = new JTextField(10);
		textDescricaoBebida.setSize(40, 20);
		panelCenter.add(labelDescricaoBebida);
		panelCenter.add(textDescricaoBebida);


		labelPrecoBebida = new JLabel("Preco: ");
		labelPrecoBebida.setSize(40, 20);
		textPrecoBebida = new JTextField(10);
		textPrecoBebida.setSize(40, 20);
		panelCenter.add(labelPrecoBebida);
		panelCenter.add(textPrecoBebida);

		labelAlcoolicoBebida = new JLabel("Alcoolico: ");
		labelAlcoolicoBebida.setSize(40, 20);
		panelCenter.add(labelAlcoolicoBebida);
		JLabel labelAlcoolicoBebidaEspaco = new JLabel(" ");
		labelAlcoolicoBebidaEspaco.setSize(40, 20);
		panelCenter.add(labelAlcoolicoBebidaEspaco);
		RadFalsoAlcoolicoBebida = new JRadioButton("Não", false);
		RadVerdadeiroAlcoolicoBebida = new JRadioButton("SIM", false);
		panelCenter.add(RadFalsoAlcoolicoBebida);
		panelCenter.add(RadVerdadeiroAlcoolicoBebida);

		labelCodBebida = new JLabel("Código: ");
		labelCodBebida.setSize(40, 20);
		textCodBebida = new JTextField(10);
		textCodBebida.setSize(40, 20);
		panelCenter.add(labelCodBebida);
		panelCenter.add(textCodBebida);

		JPanel panelSouth = new JPanel();
		panelSouth.setSize(500,100);
		panelSouth.setLayout(new BorderLayout());
		buttonInserirNovoBebida = new JButton("Inserir Bebida"); 
		buttonInserirNovoBebida.addActionListener(this);
		buttonConsultarBebida = new JButton("Consultar Bebida"); 
		buttonConsultarBebida.addActionListener(this);
		buttonExcluirBebida = new JButton("Deletar Bebida"); 
		buttonExcluirBebida.addActionListener(this);
		panelSouth.add(buttonInserirNovoBebida, BorderLayout.WEST);
		panelSouth.add(buttonConsultarBebida, BorderLayout.CENTER);
		panelSouth.add(buttonExcluirBebida, BorderLayout.EAST);
		
		
		panel.add(panelNorth, BorderLayout.NORTH);
		panel.add(panelCenter, BorderLayout.CENTER);
		panel.add(panelSouth, BorderLayout.SOUTH);
		frameBebida.getContentPane().add(panel);
		frameBebida.pack();
	}

	private void ConsultarBebida() {
		
		ArrayList<BebidaModel> listaBebida;
		try {
			listaBebida = BebidaBD.ConsultarBebidaTodos();
			Object[] column={"ID","Nome","Descricao","Preco", "Alcoolico"};   
			tableModel = new DefaultTableModel(column, 0);
			tableModel.addRow(column);
			
			for (BebidaModel bebida:listaBebida) {
				Object[] data = {bebida.codigo, bebida.nome, bebida.descricao, bebida.preco, bebida.alcoolico};
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

		String nome = textNomeBebida.getText();
		String precoTexto = textPrecoBebida.getText();
		String descricao = textDescricaoBebida.getText();

		String codigoTexto = textCodBebida.getText();

		boolean alcoolicoFal = RadFalsoAlcoolicoBebida.isSelected();
		boolean alcoolicoVer = RadVerdadeiroAlcoolicoBebida.isSelected();
		
		long codigo = 0;
		boolean alcoolico=false;
		double preco = 0;
		
		if (codigoTexto.length() > 0) {
			codigo = Long.parseLong(codigoTexto);
		}
		if (precoTexto.length() > 0) {
			preco = Double.parseDouble(precoTexto);
		}
		if (alcoolicoFal) {
			alcoolico = alcoolicoFal;
		} else {
			alcoolico = alcoolicoVer;
		}
		
		
		System.out.println("nome: "+nome);
		System.out.println("descricao: "+descricao);
		System.out.println("preco: "+preco);
		System.out.println("alcoolico: "+alcoolico);
		System.out.println("codigoTexto: "+codigoTexto);

		
		
		switch (comando) {
			case "Inserir Bebida":
				try {
					BebidaModel novoBebida = new BebidaModel();
					novoBebida.descricao = descricao;
					novoBebida.alcoolico = alcoolico;
					novoBebida.preco = preco;
					novoBebida.nome = nome;
					BebidaBD.InserirBebida(novoBebida);
					ConsultarBebida();
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
				
			case "Consultar Bebida":
				ConsultarBebida();
				break;
				
			case "Deletar Bebida":
				try {
					bebidaBd.DeletarBebida(codigo);
					ConsultarBebida();
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
		}
		
	}
	
}
