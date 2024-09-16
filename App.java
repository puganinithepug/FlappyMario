import javax.swing.*;
public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//sets up the window
		int boardw = Params.getBW();
		int boardh = Params.getBH();
		
		JFrame fr = new JFrame("Flappy");
		fr.setSize(boardw, boardh);
		fr.setLocationRelativeTo(null);
		fr.setResizable(false);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	
		//add Jpanel, for controls
		FlappyBird Fbird = new FlappyBird();
		fr.add(Fbird);
		fr.pack();
		
		Fbird.requestFocus();
		//set window visible after added all settings
		fr.setVisible(true);

	}

}
