import java.awt.event.*;
import java.awt.Graphics;
import java.util.*;
import java.awt.Color;
import java.awt.Font;

import javax.swing.*;
import javax.swing.Timer;

public class Gameplay extends JPanel implements KeyListener , ActionListener{
	
	private int[] snakeXlength = new int[750];
	private int[] snakeYlength = new int[750];
	
	//for movement 

	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;
	
	//ImageIcon for snakes 
	private ImageIcon rightmouth ;
	private ImageIcon leftmouth ;
	private ImageIcon upmouth ;
	private ImageIcon downmouth;
	private ImageIcon enemyImage;
	private ImageIcon titleImage;
	private ImageIcon snakeimage;
	
	//default length of snake
	private int lengthOfSnake = 3 ;
	
	// first move of a snake
	private int moves = 0;
	//for speed of snake 
	private Timer timer;
	private int delay = 100 ;
	
	//enemy position
	private int[] enemyXpos = {25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,
			475,500,525,550,575,600,625,650,675,700,725,750,775,800,825,850};
	private int[] enemyYpos = {75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450
			,475,500,525,550,575,600,625};

	private Random random = new Random();
	private int xpos = random.nextInt(34);
	private int ypos = random.nextInt(23);
	
	private int score = 0 ; 
	
	
public Gameplay() {
  addKeyListener(this);
  setFocusable(true);
  setFocusTraversalKeysEnabled(false);
  timer = new Timer(delay , this);
  timer.start();
 
}
public void paint (Graphics g) {
	//checking whether the game is on its first move
	if(moves == 0) {

		snakeXlength[2] = 50;
		snakeXlength[1] = 75;
		snakeXlength[0] = 100;
		
		snakeYlength[2] = 100;
		snakeYlength[1] = 100;
		snakeYlength[0] = 100;
		
	}
	
	
	
	
	
	//draw title image border
	g.setColor(Color.white);
	g.drawRect(24,10,851,55);
	
	//draw title image
	titleImage = new ImageIcon("snaketitle.jpg");
	titleImage.paintIcon(this,g,25,11);
	
	//draw Border for Gameplay
	g.setColor(Color.white);
	g.drawRect(24,74,851,577);
	
	//Background for gameplay
	g.setColor(Color.black);
	g.fillRect(25,75,850,575);
	
	// draw scores
	g.setColor(Color.white);
	g.setFont(new Font("arial", Font.PLAIN,14));
	g.drawString("Scores : "+ score , 780 ,30);
	
	//draw length
	
	g.setColor(Color.white);
	g.setFont(new Font("arial", Font.PLAIN,14));
	g.drawString("length : "+lengthOfSnake, 780 ,50);
	
	
	
	//instantitaiting images 
	 rightmouth = new ImageIcon("rightmouth.png");
	 rightmouth.paintIcon(this,g,snakeXlength[0],snakeYlength[0]);
	 
	 
	 for(int i = 0 ; i < lengthOfSnake ;i++) {

		 if( i == 0 && right) {
			 rightmouth = new ImageIcon("rightmouth.png");
			 rightmouth.paintIcon(this,g,snakeXlength[i],snakeYlength[i]);
		 }
		 if( i == 0 && right) {
			 leftmouth = new ImageIcon("leftmouth.png");
			 leftmouth.paintIcon(this,g,snakeXlength[i],snakeYlength[i]);
		 }
		 if( i == 0 && right) {
			 downmouth = new ImageIcon("downmouth.png");
			 downmouth.paintIcon(this,g,snakeXlength[i],snakeYlength[i]);
		 }
		 if( i == 0 && right) {
			 upmouth = new ImageIcon("upmouth.png");
			 upmouth.paintIcon(this,g,snakeXlength[i],snakeYlength[i]);
		 }
		 
		 if(i != 0) {
			snakeimage = new ImageIcon("snakeimage.png");
			snakeimage.paintIcon(this,g,snakeXlength[i],snakeYlength[i]);

		 }
		 
	 }
 //enemy the snake gonna catch
    enemyImage = new ImageIcon("enemy.png");
    enemyImage.paintIcon(this,g,enemyXpos[xpos],enemyYpos[ypos]);
    
    if((enemyXpos[xpos] == snakeXlength[0]) && (enemyYpos[ypos] == snakeYlength[0])) {
        score++;
        lengthOfSnake++;
        xpos = random.nextInt(34);
        ypos = random.nextInt(23);
    }
   
    for(int i = 1 ; i < lengthOfSnake ; i++) {
    	if((snakeXlength[i] == snakeXlength[0]) && (snakeYlength[i] == snakeYlength[0])) {
    		right = false;
    		left = false ;
    		up = false ;
    		down = false ;
    		
    		g.setColor(Color.BLUE);
    		g.setFont(new Font("arial", Font.BOLD , 50));
    		g.drawString("GAMEOVER",300,300);
    		
    		g.setColor(Color.WHITE);
    		g.setFont(new Font("arial", Font.ITALIC , 20));
    		g.drawString("Press Spacebar : Restart",350,340);
    		
    		repaint();
    		
    	}
    }

	
	g.dispose();
}
@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	timer.start();
	if(right) {
		for(int i = lengthOfSnake - 1 ; i >= 0 ; i--) {
			snakeYlength[i+1] = snakeYlength[i];
		}
		
		for(int i = lengthOfSnake ; i >= 0 ; i--) {
			if (i==0) {
				snakeXlength[i] = snakeXlength[i] + 25;
			}
				else {
			snakeXlength[i] = snakeXlength[i-1];
		}
			if(snakeXlength[i] > 850) {
				snakeXlength[i] = 25;
			}
	}

	repaint();
	}
	
	if(left) {
		for(int i = lengthOfSnake - 1 ; i >= 0 ; i--) {
			snakeYlength[i+1] = snakeYlength[i];
		}
		
		for(int i = lengthOfSnake ; i >= 0 ; i--) {
			if (i==0) {
				snakeXlength[i] = snakeXlength[i] - 25;
			}
				else {
			snakeXlength[i] = snakeXlength[i-1];
		}
			if(snakeXlength[i] < 25) {
				snakeXlength[i] = 850;
			}
	}
		repaint();
}
	
	if(down) {
		for(int i = lengthOfSnake - 1 ; i >= 0 ; i--) {
			snakeXlength[i+1] = snakeXlength[i];
		}
		
		for(int i = lengthOfSnake ; i >= 0 ; i--) {
			if (i==0) {
				snakeYlength[i] = snakeYlength[i] + 25;
			}
				else {
			snakeYlength[i] = snakeYlength[i-1];
		}
			if(snakeYlength[i] > 625) {
				snakeYlength[i] = 75;
			}
	}
		repaint();
}
	if(up) {
		for(int i = lengthOfSnake - 1 ; i >= 0 ; i--) {
			snakeXlength[i+1] = snakeXlength[i];
		}
		
		for(int i = lengthOfSnake ; i >= 0 ; i--) {
			if (i==0) {
				snakeYlength[i] = snakeYlength[i] - 25;
			}
				else {
					snakeYlength[i] = snakeYlength[i-1];
		}
			if(snakeYlength[i] < 75) {
				snakeYlength[i] = 625;
			}
	}
		repaint();
}
}
@Override
public void keyTyped(KeyEvent e) {

	
}
@Override
public void keyReleased(KeyEvent e) {
	
	
}
@Override
public void keyPressed(KeyEvent e) {

	/* here we have to consider the cases when the snake is moving in right direction and left key is pressed
	 In this case event after pressing left key we have to take left = false* else snake will collide into itself
	 and same for the up down left case */
	
	
	if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
		moves++;
		right = true;
		if(!left) {
			right = true ; 
			left = false ;
		}
		else {
			right = false ;
			left = true ;
		}
		up = false;
		down = false;
	}
	
	if(e.getKeyCode() == KeyEvent.VK_LEFT) {
		moves++;
		left = true;
		if(!right) {
			left = true ; 
			right = false ;
		}
		else {
			left = false ;
			right = true ;
		}
		up = false;
		down = false;
	}
	
	if(e.getKeyCode() == KeyEvent.VK_UP) {
		moves++;
		up = true;
		if(!down) {
			down = false ;
			up = true ; 
		}
		else {
			up = false ;
			down = true ;
		}
		left = false;
		right = false;
	}
	
	if(e.getKeyCode() == KeyEvent.VK_DOWN) {
		moves++;
		down = true;
		if(!up) {
			up = false ;
			down = true ; 
		}
		else {
			down = false ;
			up = true ;
		}
		left = false;
		right = false;
	}
	
	if(e.getKeyCode() == KeyEvent.VK_SPACE) {
		moves = 0;
		score = 0;
		lengthOfSnake = 3;
		repaint();
	}
}

}
