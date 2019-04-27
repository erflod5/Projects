import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
 
import javax.swing.JPanel;
 
public class Screen extends JPanel implements Runnable, KeyListener {
 
    private static final long serialVersionUID = 1L;
 
    public static final int WIDTH = 480, HEIGHT = 480;
   
    private Thread thread;
    private boolean running = false;
 
    private BodyPart b;
    private ArrayList<BodyPart> snake;
 
    private Apple apple;
    private ArrayList<Apple> apples;
   
    private Random r;
   
    private int xCoor = 12, yCoor = 12;
    private int size = 1;
 
    private boolean right = true, left = false, up = false, down =false;
   
    private int ticks = 0;
   
    public Screen() {
        setFocusable(true);
       
        addKeyListener(this);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
 
        r = new Random();
       
        snake = new ArrayList<BodyPart>();
        apples = new ArrayList<Apple>();
       
        start();
    }
 
    public void tick() {
        if (snake.size() == 0) {
            b = new BodyPart(xCoor, yCoor, 40,40);
            snake.add(b);
        }
        if(apples.size() == 0) {
            int xCoor = r.nextInt(12);
            int yCoor = r.nextInt(12);
           
            apple = new Apple(xCoor, yCoor, 40,40);
            apples.add(apple);
        }
       
        for(int i = 0; i < apples.size(); i++) {
            if(xCoor == apples.get(i).getxCoor() &&
                    yCoor == apples.get(i).getyCoor()) {
                size++;
                apples.remove(i);
                i++;
            }
        }
       
        if(xCoor < 0) {
        	xCoor = 12;
        }
        if(xCoor > 11) {
        	xCoor = 0;
        }
        if(yCoor < 0) {
        	yCoor = 12;
        }
        if(yCoor>11) {
        	yCoor = 0;
        }
        ticks++;
       
        if(ticks > 360000) {
            if(right) xCoor++;
            if(left) xCoor--;
            if(up) yCoor--;
            if(down) yCoor++;
           
            ticks = 0;
           
            b = new BodyPart(xCoor, yCoor, 40,40);
            snake.add(b);
           
            if(snake.size() > size) {
                snake.remove(0);
            }
        }
    }
 
    public void paint(Graphics g) {
        g.clearRect(0, 0, WIDTH, HEIGHT);
        g.setColor(Color.ORANGE);
        g.fillRect(0, 0, WIDTH, HEIGHT);
       
        g.setColor(Color.BLACK);
        for (int i = 0; i < WIDTH / 40; i++) {
            g.drawLine(i * 40, 0, i * 40, HEIGHT);
        }
        for (int i = 0; i < HEIGHT / 40; i++) {
            g.drawLine(0, i * 40, WIDTH, i * 40);
        }
 
        for (int i = 0; i < snake.size(); i++) {
            snake.get(i).draw(g);
        }
        for(int i = 0; i < apples.size(); i++) {
            apples.get(i).draw(g);
        }
 
    }
 
    public void start() {
        running = true;
        thread = new Thread(this);
        thread.start();
    }
 
    public void stop() {
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
 
    public void run() {
        while (running) {
            tick();
            repaint();
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
    }
    public void keyTyped(KeyEvent arg0) {  
    }    
}