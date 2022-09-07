package exercicios_layout;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Flow_Lay implements ActionListener {
	JButton button1,button2,button3, button4, button5;
	
	public Flow_Lay() {
		JFrame frame = new JFrame("ex3 FlowLayout");
		frame.setSize(500,80);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new FlowLayout());
		
		
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
				new Flow_Lay();
			}
		});
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
