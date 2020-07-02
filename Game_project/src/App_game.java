import javax.swing.JButton;

public class App_game {
	
	public static String DIRETORIO= App_game.class.getResource(".").getPath() + "assets/";

	public static void main(String[] args) {
		Janela janela=new Janela();
		
		janela.painel_inicial(DIRETORIO+"Gui.png",DIRETORIO+"Vitor.png");
		
		janela.setAmbiente(DIRETORIO+"Vazio.jpg", DIRETORIO+"Unicamp.png", DIRETORIO+ "corona.png", DIRETORIO+ "Atividade.png",DIRETORIO+"Doente.png", DIRETORIO+"Vacina.png",DIRETORIO+"caixao_meme.gif");
		//vincula as imagens à janela
		
		
		janela.start();
		
		
		
    	

	}

}
