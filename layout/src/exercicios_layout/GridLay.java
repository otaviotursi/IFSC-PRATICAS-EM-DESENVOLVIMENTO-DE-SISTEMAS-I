package exercicios_layout;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class GridLay implements ActionListener {

	JButton button1, button2, button3, button4, button5;
	
	public GridLay() {
		JFrame frame = new JFrame("ex2 Grid");
		frame.setVisible(true);
		frame.setSize(300, 160);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout(3, 2));
		
		
		
		button1 = new JButton("Button 1");
		button1.addActionListener(this);
		frame.add(button1);
		button2 = new JButton("Button 2");
		button2.addActionListener(this);
		frame.add(button2);
		button3 = new JButton("Button 3");
		button3.addActionListener(this);
		frame.add(button3);
		button4 = new JButton("Button 4");
		button4.addActionListener(this);
		frame.add(button4);
		button5 = new JButton("Button 5");
		button5.addActionListener(this);
		frame.add(button5);

		
		
		
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override 
			public void run() {
				new GridLay();
			}
		});
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		
	}

}
