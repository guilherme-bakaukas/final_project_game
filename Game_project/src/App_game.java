
public class App_game {
	
	public static String DIRETORIO= App_game.class.getResource(".").getPath() + "assets/";

	public static void main(String[] args) {
		Janela janela=new Janela();
		
		janela.set_painel_inicial(DIRETORIO+"GuiRaw.png",DIRETORIO+"VitorRaw.png");
		
		janela.setAmbiente(DIRETORIO+"Vazio.jpg", DIRETORIO+"Unicamp.png", DIRETORIO+ "corona.png", DIRETORIO+ "Atividade.png",DIRETORIO+"Doente.png", DIRETORIO+"Vacina.png",DIRETORIO+"caixao_meme.gif",DIRETORIO+"GuiAtividade.png",DIRETORIO+"VitorAtividade.png", DIRETORIO+"Instrucoes.png");
		//vincula as imagens à janela
		
		janela.painel_instrucoes();
		
    	

	}

}
