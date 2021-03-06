import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

import javax.swing.JPanel;

public class gameplay extends JPanel implements KeyListener,ActionListener {
    private boolean play = false;
    private int score = 0;
    
    private int map[][]=new int[3][8];
    
    private int totalBricks = 24;
    
    private Timer timer;
    private int delay = 8;
    
    private int playerX = 310;
    
    private int ballposX = 120;
    private int ballposY = 350;
    private int ballXdir = -1;
    private int ballYdir = -2;
    private int count = 0;
    
    public gameplay()
    {  
    	addKeyListener(this);
    	setFocusable(true);
    	setFocusTraversalKeysEnabled(false);
    	mapping(3,8);
    	timer=new Timer(delay,this);
    	timer.start();
    }
    public void paint(Graphics g)
    {
    	//background
    	g.setColor(Color.black);
    	g.fillRect(1, 1, 692,592);
    	
    	//border
    	g.setColor(Color.green);
    	g.fillRect(0,0,3,592);
    	g.fillRect(0,0,692,3);
    	g.fillRect(681, 0, 3, 592);
    	
    	//the paddle
    	g.setColor(Color.green);
    	g.fillRect(playerX, 555, 100, 8);
    	
    	//the ball
    	g.setColor(Color.yellow);
    	g.fillOval(ballposX, ballposY, 20, 20);
    	
    	//bricks
    	drawing((Graphics2D)g);
    	
    	//game end
    	if(count==24||ballposY>570)
    	{
    		play=false;
    		g.setColor(Color.CYAN);
        	g.setFont(new Font("sherif",Font.BOLD,25));
        	g.drawString("Your Score:"+count,280,300);
    	}
    	
    	g.dispose();
    }
	@Override
	public void actionPerformed(ActionEvent e) 
	{	
		timer.start();
		if(play==true)
		{
		if(ballposX<=0||ballposX>=662)
		{
			ballXdir = - ballXdir;
			 ballposX+=ballXdir;
			 ballposY+=ballYdir;
		}
		else if(ballposY<=0)
		 {
			//ballXdir = -ballXdir;
			ballYdir = -ballYdir;
		 ballposX+=ballXdir;
		 ballposY+=ballYdir;
		 }
		else if(new Rectangle(playerX, 555, 100, 8).intersects(new Rectangle(ballposX, ballposY, 20, 20)))
		{
			//ballXdir = -ballXdir;
			ballYdir = -ballYdir;
		 ballposX+=ballXdir;
		 ballposY+=ballYdir;
		}
		else
		{
			 ballposX+=ballXdir;
			 ballposY+=ballYdir;
		}
		collision();
		repaint();
		}
	}
	
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			if(playerX>=580)
				playerX=581;
			else
				moveRight();
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			if(playerX <= 10)
			    playerX = 0;
			else 
				moveLeft();
		}
		
	}	
	public void moveLeft()
	{
		play = true;
		playerX-=20;
	}
	public void moveRight()
	{
		play = true;
		playerX+=20;
	}
	public void mapping(int r,int c)
	{

		int i,j,posX,posY;
		posX=10;
    	posY=10;
			
			for(i=0;i<r;i++)
			{
				for(j=0;j<c;j++)
				{
					map[i][j]=1;
					posX=posX+82;
				}
				posX=10;
	    		posY=posY+32;
			}
			
			
	}
  public void collision()
	{
	  int i,j,posX,posY;
	    posX=10;
		posY=10;
		for(i=0;i<3;i++)
		{
			for(j=0;j<9;j++)
			{
				if(map[i][j]==1)
				{
				if(new Rectangle(posX, posY, 82, 32).intersects(new Rectangle(ballposX, ballposY, 20, 20)))
				{
					ballYdir = -ballYdir;
					map[i][j] = 0;
				}
				}
				posX=posX+82;
			}
			posX=10;
  		posY=posY+32;
		}
	}
  public void drawing(Graphics2D g)
  {
	  int i,j,posX,posY;
  	posX=10;
  	posY=10;
  	for(i=0;i<3;i++)
  	{
  		for(j=0;j<8;j++)
  		{
  			if(map[i][j]==0)
  			{
  			g.setColor(Color.black);
  		    g.fillRect(posX, posY, 80, 30);
  		    map[i][j]= -1;
  		    count++;
  			}
  			else if(map[i][j]==1)
  			{
  				g.setColor(Color.red);
  				g.fillRect(posX, posY, 80, 30);
  			}
  		    posX=posX+82;
  	}
  		posX=10;
  		posY=posY+32;
  	}
  }

}
