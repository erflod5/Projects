import javax.swing.*;

public class Main {

	public Main() {
		
		JFrame ventana = new JFrame();
		Snake snake = new Snake();
		
		ventana.getContentPane().add(snake).setVisible(true);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setTitle("Snake");
		ventana.setLayout(null);
		ventana.setSize(600,600);
		ventana.setVisible(true);
		ventana.setLocationRelativeTo(null);
		ventana.setResizable(false);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Main();
	}
	
}
