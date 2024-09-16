import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
//why the imports? will store the "pipes" for the game
import java.util.Random;
//place "pipes" at random positions inside game
import javax.swing.*;

public enum Images {
	
	
	BIRD("./mario.png"), BACKGROUND("./flappy_bg.png"),TOPPIPE("./toppipe.png"), BOTTOMPIPE("./bottompipe.png");

	private String url;
	
	Images(String url) {
		// TODO Auto-generated constructor stub
		this.url = url;
	}
	
	
	public static String getURL(int i){
		return Images.values()[i].url;
	}
	

}
