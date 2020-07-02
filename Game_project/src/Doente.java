import java.util.Random;

public class Doente extends PecasGeradoras implements IPeca {
	
	
	private String corona;


	public Doente(String image, String corona) {
		this.name='d';
		this.moved=false;
		this.corona=corona;
		this.image=image;
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
	public IPeca[][] move() {
		// TODO Auto-generated method stub
		if(this.moved==false) {
			tabuleiro=super.move();
		}
		else {
			this.verifica_corona();//analisa se haverá uma atividade a ser criada
		}
		
		return tabuleiro;
		
	}

	private void verifica_corona() {
		int num=new Random().nextInt(100 + 1);
		if (num<=30) {//chance de 20% de criar corona
			this.create_corona();
		}
		
	}

	private void create_corona() {
		
		boolean verificadora=true;
		int [] vetor = random_positions();
		while(verificadora) {
			if ((vetor[0])<tab.linha & (vetor[0])>=0 & (vetor[1])<tab.coluna & (vetor[1]>=0)){
				verificadora=false;
			}
			else {
				vetor=random_positions();
			}
		}
		
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
