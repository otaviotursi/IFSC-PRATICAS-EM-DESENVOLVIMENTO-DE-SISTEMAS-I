package exercicios_layout;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class BorderLay implements ActionListener {

	JButton buttonNorte, buttonSul, buttonCentro, buttonLeste, buttonOeste;
	
	public BorderLay() {
		JFrame frame = new JFrame("ex1 border");
		frame.setVisible(true);
		frame.setSize(450, 150);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		
		
		buttonNorte = new JButton("BOTAO NORTE");
		buttonNorte.addActionListener(this);
		frame.add(buttonNorte, BorderLayout.NORTH);
		buttonSul = new JButton("BOTAO SUL");
		buttonSul.addActionListener(this);
		frame.add(buttonSul, BorderLayout.SOUTH);
		buttonCentro = new JButton("BOTAO CENTRO");
		buttonCentro.addActionListener(this);
		frame.add(buttonCentro, BorderLayout.CENTER);
		buttonLeste = new JButton("BOTAO LESTE");
		buttonLeste.addActionListener(this);
		frame.add(buttonLeste, BorderLayout.EAST);
		buttonOeste = new JButton("BOTAO OESTE");
		buttonOeste.addActionListener(this);
		frame.add(buttonOeste, BorderLayout.WEST);
		
		
		
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override 
			public void run() {
				new BorderLay();
			}
		});
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		

		
	}

}
