package ProvaPDS;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Prova_ex2 implements ActionListener {
	JLabel label;
	JButton button;
	int contador = 0;
	
	public Prova_ex2() {
		JFrame frame = new JFrame("Prova ex2");
		frame.setVisible(true);
		frame.setSize(160, 150);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new FlowLayout());
		
		
		label = new JLabel("Contando clicks: " + contador);
		label.setSize(40, 20);
		frame.add(label);
		
		button = new JButton("Aumentar clicks");
		button.setSize(140, 20);
		button.addActionListener(this);
		frame.add(button);
		button = new JButton("BOTAO 2");
		button.setSize(140, 20);
		button.addActionListener(this);
		frame.add(button);

		
		
		
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override 
			public void run() {
				new Prova_ex2();
			}
		});
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		var comando = e.getActionCommand();
		if (comando == "Aumentar clicks") {
			contador=contador+1;
			label.setText("Contando clicks: "+contador);
		}

		
	}

}
