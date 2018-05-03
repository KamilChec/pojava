package lab3;

import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class ComboListener implements ItemListener {
	JPanel panel;
	JLabel etykieta;
	
	public ComboListener(JPanel jPanel, JLabel jLabel) {
		this.panel = jPanel;
		this.etykieta = jLabel;
	}
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		if(arg0.getStateChange()==ItemEvent.SELECTED) {
			String color = (String)arg0.getItem();
			  switch(color) {
			  	  case "bialy":
			  		  panel.setBackground(Color.white);
			  		  etykieta.setText("Wybrano kolor biały");
			  		  break;
			  	  case "czarny":
			  		  panel.setBackground(Color.black);
			  		  etykieta.setText("Wybrano kolor czarny");
			  		  break;
			  	  case "niebieski":
			  		  panel.setBackground(Color.blue);
			  		  etykieta.setText("Wybrano kolor niebieski");
			  		  break;
			  }
		}
	}
}
