import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
//why the imports? will store the "pipes" for the game
import java.util.Random;
//place "pipes" at random positions inside game
import javax.swing.*;

public class FlappyBird extends JPanel  implements ActionListener, KeyListener {
	
	//class flappybird will inherit methods of Jpanel
	//keep jpanel features and add variables and functions on top
	//set board height and width
	int bW= Params.getBW();
	int bH= Params.getBH();
	
	//mario dimensions
	int mW = Params.getMarioW();
	int mH = Params.getMarioH();
	
	//mario coordinates
	
	int mX = Params.getMarioX();
	int mY = Params.getMarioY();
	
	//pipes
	int pX = Params.pipeX();
	int pY =Params.pipeY();
	int pW = Params.pipeW();
	int pH = Params.pipeH();
	
	//images
	Image backim;
	Image birdim;
	Image toppipe;
	Image botpipe;
	
	
	double score= 0;
	//up movement of bird is decrementing and down movement of bird is incrementing
		//forward is positive x
		//back is negative x
		//pipes move left
		//bird jumps	
	int velY = Forces.getForce(2);
	
	private class Pipe {
		
		private Image im;
		private int pipeX= pX;
		private int pipeY= pY;
		private int pipeH= pH;
		private int pipeW= pW;
			
		private boolean passed = false;
			
		Pipe(Image im){
			this.im = im;
		}

	}
	
	//pipe arraylist
	ArrayList<Pipe> pipes;
	
	//random variable
	Random random = new Random();
	
	//timers
	Timer gameLoop;
	Timer placePipesT;
	
	//when birds fall down offscreen or collides with screen
	boolean gameOver;


	FlappyBird(){
				
		setPreferredSize(new Dimension(bW, bH));
		setBackground(Color.green);
		//set color
		
		//for key listener functions
		setFocusable(true);
		addKeyListener(this);
		
		//load images
		backim = new ImageIcon(getClass().getResource(Images.getURL(1))).getImage(); 
		birdim = new ImageIcon(getClass().getResource(Images.getURL(0))).getImage();
		toppipe = new ImageIcon(getClass().getResource(Images.getURL(2))).getImage(); 
		botpipe = new ImageIcon(getClass().getResource(Images.getURL(3))).getImage();
		
		//create arraylist of pipes
		//an arraylist of pipes to which a new pipe is added every 1.5 seconds
		pipes = new ArrayList<Pipe>();
		
		//place pipes timer, adds a new pipe every 1.5 seconds
		placePipesT = new Timer(1500, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				placePipes();
			}
		});
		
		placePipesT.start();
		
		//game loop timer
		//in milliseconds
		//want 60 frames per second = every 16.6 seconds
		gameLoop = new Timer(1000/60, this);
		gameLoop.start();
		}
	
	//adding pipes
	public void placePipes() {
		int randompY = (int) (pY - pH/10 -Math.random()*(pH/2)); //shift up by quarter of its height
		int space = bH/3;
		Pipe tp = new Pipe(toppipe);
		tp.pipeY = randompY;
		pipes.add(tp);
		
		//bottom pipe details
		Pipe bp = new Pipe(botpipe);
		bp.pipeY = tp.pipeY +pH +space; 
		pipes.add(bp);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	
	public void draw(Graphics g) {
		//draw the background
		g.drawImage(backim, 0, 0, bW, bH, null);
		//draw the figure
		g.drawImage(birdim, mX, mY, mW, mH, null);
		//draw the pipes
		//looped recursive placement throughout the game
		for (int i = 0; i<pipes.size(); i++) {
			Pipe pipe = pipes.get(i);
			g.drawImage(pipe.im, pipe.pipeX, pipe.pipeY, pipe.pipeW, pipe.pipeH, null);
			
			//score display
			g.setColor(Color.red);
			g.setFont(new Font("Arial", Font.ITALIC, 25));
			if(gameOver) {  
				g.drawString("Game Over: " + String.valueOf((int) score), 10, 35);
			}
			else {
				g.drawString(String.valueOf("Flappy-Mario"), 10, 35);
				g.drawString(String.valueOf((int) score), 170, 35);
			}
		}
	}
	
	public void move() {
		velY += Forces.getForce(0);
		mY+=velY;
		//moving up -6 pixels per frame
		mY = Math.max(mY, 0);
		//prevents bird going off screen
		
		//move the pipes
		for (int i = 0; i<pipes.size(); i++) {
			Pipe pipe = pipes.get(i);
			pipe.pipeX += Forces.getForce(3);
			
			if(mX >pipe.pipeX+pipe.pipeW) {
				score+=0.5;} 
			
			if(collision(pipe) || mY>bH) {gameOver = true;}
		}
	}
	
	public boolean collision(Pipe p) {
		return mX < p.pipeX + p.pipeW && mX + mW > p.pipeX && mY < p.pipeY +p.pipeH && mY + mH > p.pipeY;
				
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//action performed every 16.6 seconds
// doing move then paint, move then paint etc
		move();
		repaint();
		if(gameOver) {
			placePipesT.stop();
			gameLoop.stop();
		}
	}

	
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_SPACE) {
			velY = Forces.getForce(1);
			if(gameOver) {
				//restart game by resetting all vars to default
				mX = Params.getMarioX();
				mY = Params.getMarioY();
				velY = 0;
				pipes.clear();
				score =0;
				gameOver = false;
				gameLoop.start();
				placePipesT.start();
			}
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


}