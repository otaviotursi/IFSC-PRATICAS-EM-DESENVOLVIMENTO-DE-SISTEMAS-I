package tarefas_swing;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Tarefa1 implements ActionListener{
	JLabel label;
	JLabel labelConfirmacao;
	JButton button;
	JTextField textField;
	
	public Tarefa1() {
		JFrame frame = new JFrame("Tarefa 1.1");
		frame.setVisible(true);
		frame.setSize(150, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new FlowLayout());
		
		
		label = new JLabel("Digite um número");
		label.setSize(140, 20);
		frame.add(label);

		textField = new JTextField(10);
		textField.setSize(140, 20);
		frame.add(textField);
		
		
		button = new JButton("Verificar");
		button.setSize(140, 20);
		button.addActionListener(this);
		frame.add(button);
		
		
		labelConfirmacao = new JLabel("");
		labelConfirmacao.setSize(140, 20);
		frame.add(labelConfirmacao);
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override 
			public void run() {
				new Tarefa1();
			}
		});
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if( ehPrimo(Integer.parseInt(textField.getText())) ) {			
			labelConfirmacao.setText("O número é primo");
		} else {			
			labelConfirmacao.setText("O número não é primo");
		}

	}
	
	private static boolean ehPrimo(int numero) {
        for (int j = 2; j < numero; j++) {
            if (numero % j == 0)
                return false;   
        }
        return true;
    }
}
