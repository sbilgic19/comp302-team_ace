package UI;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class InfoViewPanel extends JPanel{

	private JLabel infoTextLabel;
	private JButton infoPanelBackButton;
	private boolean isOn = false;
	private  JScrollPane scroolPane;
	public InfoViewPanel() {
		//setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setLayout(null);
		
		infoPanelBackButton = new JButton("Back");
		infoPanelBackButton.setBounds(750, 0, 50, 50);
		//String infoText = "This game designed by team-ace. Team members are Serkan, Alp, Ali, Ahmet, Çağrı.";
		
				
		String infoText = "<html><font size='5'>"
				+ "<p>1- This game designed by team-ace. Team members are Serkan, Alp, Ali, Ahmet, Çağrı. <p/>"
				+ "<p>2- This game designed by team-ace. Team members are Serkan, Alp, Ali, Ahmet, Çağrı. <p/>"
				+ "<p>3- This game designed by team-ace. Team members are Serkan, Alp, Ali, Ahmet, Çağrı. <p/>"
				+ "<p>4- This game designed by team-ace. Team members are Serkan, Alp, Ali, Ahmet, Çağrı. <p/>"
				+ "<p>5- This game designed by team-ace. Team members are Serkan, Alp, Ali, Ahmet, Çağrı. <p/>"
				+ "<p>6- This game designed by team-ace. Team members are Serkan, Alp, Ali, Ahmet, Çağrı. <p/>"
				+ "<p>7- This game designed by team-ace. Team members are Serkan, Alp, Ali, Ahmet, Çağrı. <p/>"
				+ "<p>8- This game designed by team-ace. Team members are Serkan, Alp, Ali, Ahmet, Çağrı. <p/>"
				+ "<p>9- This game designed by team-ace. Team members are Serkan, Alp, Ali, Ahmet, Çağrı. <p/>"
				+ "<p>10- This game designed by team-ace. Team members are Serkan, Alp, Ali, Ahmet, Çağrı. <p/>"
				+ "<p>11- This game designed by team-ace. Team members are Serkan, Alp, Ali, Ahmet, Çağrı. <p/>"
				+ "<p>12- This game designed by team-ace. Team members are Serkan, Alp, Ali, Ahmet, Çağrı. <p/>"
				+ "<p>13- This game designed by team-ace. Team members are Serkan, Alp, Ali, Ahmet, Çağrı. <p/>"
				+ "<p>14- This game designed by team-ace. Team members are Serkan, Alp, Ali, Ahmet, Çağrı. <p/>"
				+ "<p>15- This game designed by team-ace. Team members are Serkan, Alp, Ali, Ahmet, Çağrı. <p/>"
				+ "<p>16- This game designed by team-ace. Team members are Serkan, Alp, Ali, Ahmet, Çağrı. <p/>"
				+ "<p>17- This game designed by team-ace. Team members are Serkan, Alp, Ali, Ahmet, Çağrı. <p/>"
				+ "<p>18- This game designed by team-ace. Team members are Serkan, Alp, Ali, Ahmet, Çağrı. <p/>"
				+ "<p>19- This game designed by team-ace. Team members are Serkan, Alp, Ali, Ahmet, Çağrı. <p/>"
				+ "<p>20- This game designed by team-ace. Team members are Serkan, Alp, Ali, Ahmet, Çağrı. <p/>"
				+ "<p>21- This game designed by team-ace. Team members are Serkan, Alp, Ali, Ahmet, Çağrı. <p/>"
				+ "</font></html>";
		
		infoTextLabel = new JLabel(infoText);
		infoTextLabel.setBounds(0, 50, 1400, 600);
		
		scroolPane = new JScrollPane(infoTextLabel);
		scroolPane.setBounds(0, 50, 1500, 700);

		scroolPane.setViewportView(infoTextLabel);
		
		
		add(scroolPane);
		add(infoPanelBackButton);
		//add(infoTextLabel);
	}
	
	public JLabel getInfoLabel() {
		return infoTextLabel;
	}
	public JScrollPane getScrollPane() {
		return scroolPane;
	}
 	public JButton getInfoPanelBackButton() {
		return infoPanelBackButton;
	}
	public boolean getIsOn() {
		return isOn;
	}
	public void setIsOn(Boolean newState) {
		this.isOn = newState;
	}
}
