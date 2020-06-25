import java.util.Random;

import javax.swing.JButton;

public class App_game {
	
	public static String DIRETORIO= App_game.class.getResource(".").getPath();

	public static void main(String[] args) {
		Janela janela=new Janela();
		
    	JButton right=new JButton("right");
    	janela.setButton(right);
    	
    	JButton left=new JButton("left");
    	janela.setButton(left);
    	
    	JButton up=new JButton("up");
    	janela.setButton(up);
    	
    	JButton down=new JButton("down");
    	janela.setButton(down);
    	
		Usuario jogador= new Usuario(DIRETORIO+"Char.png");//adiciona a imagem animada no início
		Unicamp unicamp= new Unicamp(DIRETORIO+"unicamp.jpg");
		
		jogador.vinculateButtons(up, right, left, down);
		
		right.addActionListener(jogador);
		left.addActionListener(jogador);
		up.addActionListener(jogador);
		down.addActionListener(jogador);
			
		janela.setAmbiente(DIRETORIO+"Frame1.jpg", unicamp,jogador);//cria o ambiente inicial
		
		janela.start();
		
		
		
    	

	}

}
