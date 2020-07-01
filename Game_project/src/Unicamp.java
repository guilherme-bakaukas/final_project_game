import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Unicamp extends PecasGeradoras implements IPeca {
	private static final long serialVersionUID = -9047898149398844451L;
	
	private String atividade;
	
	public Unicamp(String image, String atividade) {
		this.name='u';
		this.moved=false;
		this.atividade=atividade;
		this.image=image;
	}
	
	public char getname() {
		return this.name;
	}
	
	public boolean getmoved() {
		return moved;
	}
	
	public void setmoved(boolean b) {
		this.moved=b;	
	}
	
	public IPeca[][] move() {//retorna o vetor de posições {linha,coluna} para movimentação
		 
		tabuleiro=super.move();
		
		this.verifica_atividade();//analisa se haverá uma atividade a ser criada
		
		return tabuleiro;
		
	}
	
	public void vinculate_tabuleiro(Tabuleiro tab){
		this.tab=tab;
		this.tabuleiro=tab.tabuleiro;
		this.linha=0;//inicia na primeira posição do tabuleiro (podemos mudar)
		this.coluna=0;
	}
	
	private void verifica_atividade() {
		int num=new Random().nextInt(100 + 1);
		if (num<=30) {//chance de 20% de criar corona
			this.create_atividade();
		}	
	}

	private void create_atividade() {
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
			tabuleiro[vetor[0]][vetor[1]]=new Atividade(atividade,vetor[0],vetor[1],incremento_linha,incremento_coluna);
			tabuleiro[vetor[0]][vetor[1]].vinculate_tabuleiro(tab);
		}

	}

	
}
