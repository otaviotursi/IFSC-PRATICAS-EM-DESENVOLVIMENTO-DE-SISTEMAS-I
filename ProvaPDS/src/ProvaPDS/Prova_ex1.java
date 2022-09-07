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

public class Prova_ex1 implements ActionListener {
	JLabel label;
	JButton button;
	
	public Prova_ex1() {
		JFrame frame = new JFrame("Prova ex1");
		frame.setVisible(true);
		frame.setSize(150, 150);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new FlowLayout());
		
		
		label = new JLabel("String fixa");
		label.setSize(40, 20);
		frame.add(label);
		
		button = new JButton("BOTAO 1");
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
				new Prova_ex1();
			}
		});
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		

		
	}

}
