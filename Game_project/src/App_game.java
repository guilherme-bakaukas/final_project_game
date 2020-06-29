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
    	
		Usuario jogador= new Usuario();//adiciona a imagem animada no início
		
		jogador.vinculateButtons(up, right, left, down);
		
		right.addActionListener(jogador);
		left.addActionListener(jogador);
		up.addActionListener(jogador);
		down.addActionListener(jogador);
			
		janela.setAmbiente(DIRETORIO+"Frame1.jpg", DIRETORIO+"unicamp.jpg",DIRETORIO+"Char.png", DIRETORIO+"coronavirus.jpg", DIRETORIO+"atividade.png");
		//cria o ambiente inicial
		
		janela.start();
		
		
		
    	

	}

}
