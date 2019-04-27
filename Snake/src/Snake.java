import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class Snake extends JPanel implements Runnable, KeyListener{

	public JLabel [][] tablero;
	public Integer [][] tableroindice;
	
	private Thread thread;
	private boolean running = false;
	private Random r;
	
	private boolean up = false,down= false,left=false,right = true;
	
	public Snake() {
		setBounds(50,50,480,440);
		setLayout(new GridLayout(12,11));
		setFocusable(true);
		addKeyListener(this);
		tablero = new JLabel[12][11];
		tableroindice = new Integer[12][11];
		pintarMatriz();
		r = new Random();
		
		start();
	}
	
	public void pintarMatriz(){
		for(int i = 0; i < 12; i++) {
			for(int j = 0; j < 11; j++) {
					tablero[i][j] = new JLabel();
					Border border = BorderFactory.createLineBorder(Color.white);
					tablero[i][j].setBorder(border);
					tablero[i][j].setBackground(Color.black);
					tablero[i][j].setOpaque(true);
					add(tablero[i][j]);
					tableroindice[i][j] = 2;
			}
		}
	}
	
	public void start() {
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	int i=0,j=0;
	int xa = 0, ya = 0;
	public void move() {
		if(up) {
			if(i==0) {
				tablero[11][j].setBackground(Color.yellow);
				tablero[11][j].setOpaque(true);
				
				tablero[0][j].setBackground(Color.BLACK);
				tablero[0][j].setOpaque(true);
				i = 11;
			}
			else {
				tablero[i-1][j].setBackground(Color.yellow);
				tablero[i-1][j].setOpaque(true);
				
				tablero[i][j].setBackground(Color.BLACK);
				tablero[i][j].setOpaque(true);
				i--;
			}
		}
		else if(down) {
			if(i==11) {
				tablero[0][j].setBackground(Color.yellow);
				tablero[0][j].setOpaque(true);
				
				tablero[11][j].setBackground(Color.BLACK);
				tablero[11][j].setOpaque(true);
				i = 0;
			}
			else {
				tablero[i+1][j].setBackground(Color.yellow);
				tablero[i+1][j].setOpaque(true);
				
				tablero[i][j].setBackground(Color.BLACK);
				tablero[i][j].setOpaque(true);
				i++;
			}
		}
		else if(left) {
			if(j==0) {
				tablero[i][10].setBackground(Color.yellow);
				tablero[i][10].setOpaque(true);
				
				tablero[i][0].setBackground(Color.BLACK);
				tablero[i][0].setOpaque(true);
				j = 10;
			}
			else {
				tablero[i][j-1].setBackground(Color.yellow);
				tablero[i][j-1].setOpaque(true);
				
				tablero[i][j].setBackground(Color.BLACK);
				tablero[i][j].setOpaque(true);
				j--;
			}
		}
		else if(right) {
			if(j==10) {
				tablero[i][0].setBackground(Color.yellow);
				tablero[i][0].setOpaque(true);
				
				tablero[i][10].setBackground(Color.BLACK);
				tablero[i][10].setOpaque(true);
				j = 0;
			}
			else {
				tablero[i][j+1].setBackground(Color.yellow);
				tablero[i][j+1].setOpaque(true);
				
				tablero[i][j].setBackground(Color.BLACK);
				tablero[i][j].setOpaque(true);
				j++;
			}
		}
	}

	@Override
	public void run() {
		while(running) {
			move();
			repaint();
			try {
				thread.sleep(50);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_RIGHT && !left) {
            up = false;
            down = false;
            right = true;
        }
        if(key == KeyEvent.VK_LEFT && !right) {
            up = false;
            down = false;
            left = true;
        }
        if(key == KeyEvent.VK_UP && !down) {
            left = false;
            right = false;
            up = true;
        }
        if(key == KeyEvent.VK_DOWN && !up) {
            left = false;
            right = false;
            down = true;
        }
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
