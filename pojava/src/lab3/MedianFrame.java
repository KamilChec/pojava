package lab3;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class MedianFrame extends JFrame {

	ArrayList<Double> lista;
	JTextField poletext;
	JLabel label1;
	JLabel label2;
	JButton dodaj;
	JButton mediana;

		
		public MedianFrame() throws HeadlessException
		{
			this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			this.setSize(700, 200);
			this.setLayout(null);
			
			lista = new ArrayList <Double>();
			poletext = new JTextField();
			this.add(poletext);
			poletext.setBounds(100, 50, 50, 30);
			
			label1 = new JLabel();
			this.add(label1);
			label1.setBounds(40,100,500,40);
			
			dodaj = new JButton("Dodaj");
			this.add(dodaj);
			dodaj.setBounds(200,50,150,30);
			ActionListener sluchaczdodaj = new ActionListener(){
				public void actionPerformed(ActionEvent arg0)	
				{
					Double nowy ;
					try{
					     nowy = Double.parseDouble(poletext.getText());
					     lista.add(nowy);
						}
					catch(NumberFormatException exception){
					     System.out.println("Wrong number format.");
						}
					
					label1.setText("Zbior liczb: " + lista);
				}
				
			};
			
			dodaj.addActionListener(sluchaczdodaj);
			
			mediana = new JButton("Mediana");
			this.add(mediana);
			mediana.setBounds(400, 50, 150, 30);
			
			label2 = new JLabel();
			this.add(label2);
			label2.setBounds(560,50 ,50,30);
			
			ActionListener sluchaczmediana = new ActionListener(){
				public void actionPerformed(ActionEvent arg0)
				{
					Collections.sort(lista);
					label1.setText("Zbior liczb: " + lista);
					double mediana;
					if((lista.size()%2) == 0)
					{
						int c = (lista.size()/2) ;
						int d = (lista.size()/2) - 1;
						   Double a =  lista.get(c);
						   double b =  lista.get(d);
						mediana = (a + b) / 2;   
					}
						else
						{
							int a = (int) ((lista.size()/2) + 0.5);
							mediana = lista.get(a);
						}
					label2.setText("= " + mediana);
				}
			};
			mediana.addActionListener(sluchaczmediana);
	
		}
	public static void main(String[] args) {
		MedianFrame frame = new MedianFrame();
		frame.setVisible(true);
	
	}

}
