package tarefas_swing;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class Tarefa1_3  implements ActionListener {
	JLabel label;
	
	JButton buttonAdicionar;
	JTextField inputNumeros;	
	
	JLabel labelMaior;
	JTextField readMaior;
	JLabel labelMenor;
	JTextField readMenor;
	JLabel labelMedia;
	JTextField readMedia;
	
	JButton buttonCalcular;
	List<Integer> listaNumeros = new ArrayList<Integer>();
	
	public Tarefa1_3() {
		JFrame frame = new JFrame("Tarefa 1.3");
		frame.setVisible(true);
		frame.setSize(150, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new FlowLayout());
		
		
		label = new JLabel("Digite os números: ");
		label.setSize(200, 20);
		frame.add(label);

		inputNumeros = new JTextField(10);
		inputNumeros.setSize(200, 20);
		inputNumeros.setText("");
		frame.add(inputNumeros);
		
		buttonAdicionar = new JButton("Adicionar");
		buttonAdicionar.setSize(200, 20);
		buttonAdicionar.addActionListener(this);
		frame.add(buttonAdicionar);	
		
		labelMaior = new JLabel("Maior:  ");
		labelMaior.setSize(200, 20);
		frame.add(labelMaior);
		readMaior = new JTextField(10);
		readMaior.setSize(200, 20);
		readMaior.setEditable(false);;
		readMaior.setText("");
		frame.add(readMaior);

		labelMenor = new JLabel("Menor:");
		labelMenor.setSize(200, 20);
		frame.add(labelMenor);
		readMenor = new JTextField(10);
		readMenor.setSize(200, 20);
		readMenor.setEditable(false);;
		readMenor.setText("");
		frame.add(readMenor);
		

		labelMedia = new JLabel("Media:");
		labelMedia.setSize(200, 20);
		frame.add(labelMedia);
		readMedia = new JTextField(10);
		readMedia.setSize(200, 20);
		readMedia.setEditable(false);;
		readMedia.setText("");
		frame.add(readMedia);
		
		buttonCalcular = new JButton("Calcular");
		buttonCalcular.setSize(200, 20);
		buttonCalcular.addActionListener(this);
		frame.add(buttonCalcular);	
		
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override 
			public void run() {
				new Tarefa1_3();
			}
		});
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		var comando = e.getActionCommand();
		switch (comando) {
		case "Adicionar":
			if (inputNumeros.getText()+"" != "")
				listaNumeros.add(Integer.parseInt(inputNumeros.getText()));
				inputNumeros.setText("");
			break;

		case "Calcular":
			this.CalcularMaior(listaNumeros);
			this.CalcularMenor(listaNumeros);
			this.CalcularMedia(listaNumeros);
			break;
		}
	
	}
	
	public void CalcularMaior(List<Integer> lista) {
		var maior = lista.get(0);
		for(int i=0;i<lista.size();i++) {
			if (maior < lista.get(i)) {
				maior = lista.get(i);
			}
		}
		readMaior.setText(maior+"");
	}
	public void CalcularMenor(List<Integer> lista) {
		var menor = lista.get(0);
		for(int i=0;i<lista.size();i++) {
			if (menor > lista.get(i)) {
				menor = lista.get(i);
			}
		}
		readMenor.setText(menor+"");
	}

	public void CalcularMedia(List<Integer> lista) {
		var soma = 0;
		var media = 0;
		for(int i=0;i<lista.size();i++) {
			soma += lista.get(i);
		}
		media = soma/lista.size();
		
		readMedia.setText(media+"");
	}
}
