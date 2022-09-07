package tarefas_swing;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Tarefa1_2 implements ActionListener {
	JLabel label;
	JLabel labelConfirmacao;
	JButton buttonSoma;
	JButton buttonSubtrair;
	JButton buttonDividir;
	JButton buttonMultiplicar;
	JTextField inputNumero1;
	JTextField inputNumero2;
	
	public Tarefa1_2() {
		JFrame frame = new JFrame("Tarefa 1.2");
		frame.setVisible(true);
		frame.setSize(400, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new FlowLayout());
		
		
		label = new JLabel("Digite dois números: ");
		label.setSize(150, 20);
		frame.add(label);

		inputNumero1 = new JTextField(10);
		inputNumero1.setSize(150, 20);
		inputNumero1.setText("");
		frame.add(inputNumero1);

		inputNumero2 = new JTextField(10);
		inputNumero2.setSize(150, 20);
		inputNumero2.setText("");
		frame.add(inputNumero2);
		
		
		buttonSoma = new JButton("Somar");
		buttonSoma.setSize(100, 20);
		buttonSoma.addActionListener(this);
		frame.add(buttonSoma);		
		buttonSubtrair = new JButton("Subtrair");
		buttonSubtrair.setSize(100, 20);
		buttonSubtrair.addActionListener(this);
		frame.add(buttonSubtrair);		
		buttonDividir = new JButton("Dividir");
		buttonDividir.setSize(100, 20);
		buttonDividir.addActionListener(this);
		frame.add(buttonDividir);		
		buttonMultiplicar = new JButton("Multiplicar");
		buttonMultiplicar.setSize(100, 20);
		buttonMultiplicar.addActionListener(this);
		frame.add(buttonMultiplicar);
		
		
		labelConfirmacao = new JLabel("");
		labelConfirmacao.setSize(400, 20);
		frame.add(labelConfirmacao);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override 
			public void run() {
				new Tarefa1_2();
			}
		});
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		var resultado = 0;
		var comando = e.getActionCommand();
		if (inputNumero1.getText()+"" != "" && inputNumero2.getText()+"" != "") {
			int numero1 = Integer.parseInt(inputNumero1.getText());
			int numero2 = Integer.parseInt(inputNumero2.getText());
			
			switch (comando) {
			case "Somar":
				resultado = numero1 + numero2;
				labelConfirmacao.setText("Resultado: " + numero1 + " + " + numero2 + " = " + resultado);
				break;

			case "Subtrair":
				resultado = numero1 - numero2;
				labelConfirmacao.setText("Resultado: " + numero1 + " - " + numero2 + " = " + resultado);
				break;

			case "Dividir":
				resultado = numero1 / numero2;
				labelConfirmacao.setText("Resultado: " + numero1 + " / " + numero2 + " = " + resultado);
				break;

			case "Multiplicar":
				resultado = numero1 * numero2;
				labelConfirmacao.setText("Resultado: " + numero1 + " * " + numero2 + " = " + resultado);
				break;	
			}
			
		} else {
			
			labelConfirmacao.setText("Digite um número valido!");
			
		}

	}
	
}
