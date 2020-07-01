import javax.swing.JButton;

public class App_game {
	
	public static String DIRETORIO= App_game.class.getResource(".").getPath() + "assets/";

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
    	
		Usuario jogador= new Usuario(DIRETORIO+"Vitor.png");//adiciona a imagem animada no início
		
		jogador.vinculateButtons(up, right, left, down);
		
		right.addActionListener(jogador);
		left.addActionListener(jogador);
		up.addActionListener(jogador);
		down.addActionListener(jogador);
			
		janela.setAmbiente(DIRETORIO+"Vazio.jpg", DIRETORIO+"Unicamp.png",jogador, DIRETORIO+ "corona.png", DIRETORIO+ "Atividade.png",DIRETORIO+"Doente.png");
		//cria o ambiente inicial
		
		janela.start();
		
		
		
    	

	}

}
