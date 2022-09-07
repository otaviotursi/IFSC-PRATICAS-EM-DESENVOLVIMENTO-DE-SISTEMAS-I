package exercicios_layout;

import java.awt.Component;
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

public class Box_Lay implements ActionListener {
	JButton button1,button2,button3, button4, button5;
	
	public Box_Lay() {
		JFrame frame = new JFrame("ex4 BoxLayout");
		frame.setSize(100,400);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		frame.add(panel);
		
		
		button1 = new JButton("Button 1");
		button1.addActionListener(this);
		button1.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(button1);
		button2 = new JButton("Button 2");
		button2.addActionListener(this);
		button2.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(button2);
		button3 = new JButton("Button 3");
		button3.addActionListener(this);
		button3.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(button3);
		button4 = new JButton("Button 4");
		button4.addActionListener(this);
		button4.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(button4);
		button5 = new JButton("Button 5");
		button5.addActionListener(this);
		button5.setAlignmentX(Component.CENTER_ALIGNMENT);
		panel.add(button5);

		
		
		
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override 
			public void run() {
				new Box_Lay();
			}
		});
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

}
