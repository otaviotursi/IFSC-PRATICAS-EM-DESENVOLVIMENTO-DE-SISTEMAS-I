package tarefas_swing;


import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Tarefa2_1 implements ActionListener {
	JLabel labelLado1;
	JTextField textFieldLado1;
	JLabel labelLado2;
	JTextField textFieldLado2;
	JLabel labelLado3;
	JTextField textFieldLado3;	
	JButton button;
	JLabel labelConfirmacao;
	JLabel labelImagem;
	ImageIcon fig1;
	
	public Tarefa2_1() {
		JFrame frame = new JFrame("Tarefa 2.1");
		frame.setVisible(true);
		frame.setSize(150, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new FlowLayout());
		
		
		labelLado1 = new JLabel("Lado 1: ");
		labelLado1.setSize(40, 20);
		frame.add(labelLado1);
		textFieldLado1 = new JTextField(10);
		textFieldLado1.setSize(60, 20);
		frame.add(textFieldLado1);
		
		labelLado2 = new JLabel("Lado 2: ");
		labelLado2.setSize(40, 20);
		frame.add(labelLado2);
		textFieldLado2 = new JTextField(10);
		textFieldLado2.setSize(60, 20);
		frame.add(textFieldLado2);
		
		labelLado3 = new JLabel("Lado 3: ");
		labelLado3.setSize(40, 20);
		frame.add(labelLado3);
		textFieldLado3 = new JTextField(10);
		textFieldLado3.setSize(60, 20);
		frame.add(textFieldLado3);
		
		button = new JButton("Verificar");
		button.setSize(140, 20);
		button.addActionListener(this);
		frame.add(button);
		button = new JButton("Limpar");
		button.setSize(140, 20);
		button.addActionListener(this);
		frame.add(button);

		labelConfirmacao = new JLabel();
		labelConfirmacao.setSize(140, 20);
		frame.add(labelConfirmacao);
		labelImagem = new JLabel();
		labelImagem.setSize(140, 20);
		frame.add(labelImagem);
		
		
		
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override 
			public void run() {
				new Tarefa2_1();
			}
		});
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		

		var comando = e.getActionCommand();
		switch (comando) {
		
		case "Verificar":
			var lado1 = textFieldLado1.getText();
			var lado2 = textFieldLado2.getText();
			var lado3 = textFieldLado3.getText();
			
			if(lado1+"" != "" || lado2+"" != "" || lado3+"" != "") {
				if (lado1 == lado2 && lado1 == lado3 && lado2 == lado3) {
					fig1 = new ImageIcon(getClass().getResource("tri-equilatero.jpg"));
					labelConfirmacao.setText("Triangulo equilatero");
					labelImagem.setIcon(fig1);
				} else if ((lado1 == lado2 || lado1 == lado3 || lado2 == lado3) && (lado1 != lado2 || lado1 != lado3 || lado2 != lado3)) {
					fig1 = new ImageIcon(getClass().getResource("tri-isosceles.jpg"));
					labelConfirmacao.setText("Triangulo isosceles");
					labelImagem.setIcon(fig1);
				} else if (lado1 != lado2 && lado1 != lado3 && lado2 != lado3) {
					fig1 = new ImageIcon(getClass().getResource("tri-escaleno.jpg"));
					labelConfirmacao.setText("Triangulo escaleno");
					labelImagem.setIcon(fig1);
				}
			} else {

				fig1 = new ImageIcon(getClass().getResource("erro.png"));
				labelConfirmacao.setText("Digite todos os valores");
				labelImagem.setIcon(fig1);
			}
			
			break;
			
		case "Limpar":
			textFieldLado1.setText("");
			textFieldLado2.setText("");
			textFieldLado3.setText("");
			break;
		
		}

	}
	
}
