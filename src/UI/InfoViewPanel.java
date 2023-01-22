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
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class InfoViewPanel extends JPanel{

	private final JLabel infoTextLabel;
	private final JButton infoPanelBackButton;
	private boolean isOn = false;
	private final JScrollPane scroolPane;
	
	private JTextArea textArea;
	public InfoViewPanel() {
		//setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		setLayout(null);
		
		infoPanelBackButton = new JButton("Back");
		infoPanelBackButton.setBounds(750, 0, 50, 50);
		//String infoText = "This game designed by team-ace. Team members are Serkan, Alp, Ali, Ahmet, Çağrı.";
		
				
		
	
		
		String infoText = "<html>"
                + "<body style='width: 1400px;'>"
                + "<h1>Escape from KOÇ</h1>"
                + "<p>Escape from KOÇ is a fun and challenging game that takes place on the campus of KOÇ University. The player, a student, must navigate the campus buildings in search of a sequence of keys before the time runs out.  </p>"
                + "<p>The game begins in the Student Center and progresses through the CASE, SOS, SCI, ENG, and SNA buildings. </p>"
                + "<h2>Gameplay</h2>"
                + "<p>The player navigates the buildings using the arrow keys and can only open the exit door of a building once they have found the key. The player uses a left-click on the mouse to interact with objects and search for keys, but must be</p>"
                + "<p> close to the objects to do so. The player also has a bag to collect power-ups for later use. The game is over if the player fails to find the key within the time limit or loses all their lives.</p>"
                + "<h2>Game objects</h2>"
                + "<p>While walking around, the player faces some aliens who try to kill the player or prevent her/him from finding the keys. All of the aliens appear randomly in the buildings every 10 seconds and the type of the appearing aliens are again</p>"
                + "<p>selected randomly. The alien stays in the building and cannot go to the next building. So, when the player finds the key and goes to the next building, the alien does not follow him/her.</p>"
                + "<p>Shooter alien: This type of alien appears in a random location in the building and shoots a bullet every second. Think of the building map as a grid. If the player is close to the shooter alien less than 4 squares, then he/she will"
                + "<p>lose a life. The player has three lives at the beginning of the game. He/she can collect some extra lives during the game. Also, if the player wears a protection vest, then he/she can get close to the shooter alien without losing a life.</p>"
                + "<p>Blind alien: This type of alien also tries to kill the player. However, in order to kill him/her, the alien must be right next to the player. The thing is that the alien is blind. So it cannot see the player. He randomly walks around. </p>"
                + "<p>However, this alien is sensitive to the voices. When the player has the plastic bottle power-up, if she/he throws the bottle, he/she can fool the alien. For example, if the player throws the bottle to the opposite direction where he/she will go,</p>"
                + "<p>then the alien will go in the bottle’s direction. The protection vest also works for this type of alien.</p>\n"
                + "<p>Timw wasting alien: This type of alien changes the location of the key based on the remaining time algorithm</p>"
                + "<h2>Power-ups</h2>\n"
                + "<p>During the game, the player can collect power-ups to help her/him along the way. There are 3 types of power-ups:</p>\n"
                + "<p>- Hint: Gives hint about the location of the key.</p>\n"
                + "<p>- Extra time: Gives extra 5 seconds to the Player.</p>\n"
                + "<p>- Extra life: Gives one extra life to the Player.</p>\n"
                + "<p>- Protection vest: This power-up makes the player invulnerable to the aliens for a short period of time.</p>\n"
                + "<p>- Plastic bottle: This power-up can be used to distract the blind alien and make it move in the opposite direction of the player.</p>\n"
                + "<h2>Controls</h2>\n"
                + "<p>- Move: Arrow keys</p>\n"
                + "<p>- Interact: Left-click</p>\n"
                + "<p>- Use power-up: P: Protection Vest, Bottle: B, Hint: H</p>\n"
                + "</body>\n"
                + "</html>";
		
		infoTextLabel = new JLabel(infoText);
		infoTextLabel.setBounds(0, 50, 1000, 500);
		
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
