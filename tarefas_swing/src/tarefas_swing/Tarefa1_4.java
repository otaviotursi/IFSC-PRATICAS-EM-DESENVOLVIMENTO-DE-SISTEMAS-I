package tarefas_swing;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Tarefa1_4  implements ActionListener {
	JLabel label;
	
	JButton buttonInverter;
	JTextField inputTexto;	
	JTextField readTexto;	
	public Tarefa1_4() {
		JFrame frame = new JFrame("Tarefa 1.4");
		frame.setVisible(true);
		frame.setSize(150, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new FlowLayout());
		
		
		label = new JLabel("Digite um texto: ");
		label.setSize(150, 20);
		frame.add(label);

		inputTexto = new JTextField(10);
		inputTexto.setSize(150, 20);
		inputTexto.setText("");
		frame.add(inputTexto);

		readTexto = new JTextField(10);
		readTexto.setSize(150, 20);
		readTexto.setEditable(false);
		readTexto.setText("");
		frame.add(readTexto);
		
		
		buttonInverter = new JButton("Inverter");
		buttonInverter.setSize(100, 20);
		buttonInverter.addActionListener(this);
		frame.add(buttonInverter);		
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override 
			public void run() {
				new Tarefa1_4();
			}
		});
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		var comando = e.getActionCommand();
		if (comando == "Inverter") {
			
			var contTexto = inputTexto.getText().length();
			var texto = inputTexto.getText();
			String novoTexto = "";
			for (int i = contTexto-1; i>=0; i--) {
				novoTexto += texto.charAt(i);
			}
			readTexto.setText(novoTexto);;
		}
		

	}
}
