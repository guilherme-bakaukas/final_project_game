import java.util.Random;

public class Doente extends PecasGeradoras {
	
	private String corona;

	public Doente(String image, String corona, int probabilidade) {
		this.name='d';
		this.moved=false;
		this.corona=corona;
		this.image=image;
		this.probabilidade=probabilidade;
	}

	@Override
	public char getname() {
		// TODO Auto-generated method stub
		return this.name;
	}

	@Override
	public boolean getmoved() {
		// TODO Auto-generated method stub
		return this.moved;
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		if(this.moved==false) {
			super.move();
		}
		else {//caso ja tenha movido ele deve verificar a geração de corona
			this.verifica_corona();
		}
		
		
	}

	private void verifica_corona() {
		int num=new Random().nextInt(100 + 1);
		if (num<=this.probabilidade) {//chance de 30% de criar corona
			this.create_corona();
		}
		
	}

	private void create_corona() {
		int[] vetor=create_position();
		
		int incremento_linha=vetor[0]-this.linha;
		int incremento_coluna=vetor[1]-this.coluna;
		
		if (tabuleiro[vetor[0]][vetor[1]]==null) {
			tabuleiro[vetor[0]][vetor[1]]=new Corona(corona,vetor[0],vetor[1],incremento_linha,incremento_coluna);
			tabuleiro[vetor[0]][vetor[1]].vinculate_tabuleiro(tab);
		}
		
	}

	@Override
	public void setmoved(boolean b) {
		// TODO Auto-generated method stub
		this.moved=b;
		
	}

	@Override
	public void vinculate_tabuleiro(Tabuleiro tab) {
		// TODO Auto-generated method stub
		this.tab=tab;
		this.tabuleiro=tab.tabuleiro;
		this.linha=0;//inicia na primeira posição do tabuleiro (podemos mudar)
		this.coluna=tab.coluna-1;
		
	}
	
	

}
