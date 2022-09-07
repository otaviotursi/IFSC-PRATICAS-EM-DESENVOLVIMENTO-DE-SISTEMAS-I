package exercicios_layout;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class GridBag_Lay implements ActionListener {
	JButton button1,button2,button3, button4, button5;
	
	public GridBag_Lay() {
		JFrame frame = new JFrame("ex4 BoxLayout");
		frame.setSize(250,150);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		frame.add(panel);
		
		JPanel panel1 = new JPanel();
		panel1.setSize(250,150);
		panel1.setLayout(new BorderLayout());
		JPanel panel2 = new JPanel();
		panel2.setSize(250,150);
		panel2.setLayout(new BorderLayout());
		JPanel panel3 = new JPanel();
		panel3.setSize(250,150);
		panel3.setLayout(new BorderLayout());
		
		
		
		button1 = new JButton("Button 1");
		button1.addActionListener(this);
		panel1.add(button1, BorderLayout.EAST);
		button2 = new JButton("Button 2");
		button2.addActionListener(this);
		panel1.add(button2, BorderLayout.CENTER);
		button3 = new JButton("Button 3");
		button3.addActionListener(this);
		panel1.add(button3, BorderLayout.WEST);
		panel.add(panel1, BorderLayout.NORTH);
		
		button4 = new JButton("long named button 4");
		button4.setSize(300,20);
		button4.addActionListener(this);
		panel2.add(button4, BorderLayout.NORTH);
		panel.add(panel2, BorderLayout.CENTER);
		
		button5 = new JButton("Button 5");
		button5.addActionListener(this);
		panel3.add(button5, BorderLayout.WEST);
		panel.add(panel3, BorderLayout.SOUTH);
		
		
		
		
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override 
			public void run() {
				new GridBag_Lay();
			}
		});
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
