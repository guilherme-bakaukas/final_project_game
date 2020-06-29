import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Unicamp extends Peca implements IPeca {
	private static final long serialVersionUID = -9047898149398844451L;
	
	private Tabuleiro tab;
	private IPeca[][] tabuleiro;
	private int linha,coluna;
	private char name;
	private boolean moved;
	private String atividade;
	
	public Unicamp(String atividade) {
		this.name='u';
		this.moved=false;
		this.atividade=atividade;
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
	
	public boolean verifica_movimento(int[] vetor) {
		if (super.verifica_movimento(vetor,tab)==false) return false; //verifica se está nos limites do tabuleiro
		else if (tabuleiro[vetor[0]][vetor[1]]!=null) {//verifica se o espaço está vazio para ir
			return false;
		}
		return true;
	}
	
	public IPeca[][] move() {//retorna o vetor de posições {linha,coluna} para movimentação
		
		int[] vetor= random_positions(linha,coluna);
		while(verifica_movimento(vetor)==false) {
			vetor=random_positions(linha,coluna);
		}
		
		tabuleiro[linha][coluna]=null;
		
		this.linha=vetor[0];
		this.coluna=vetor[1];
		
		tabuleiro[linha][coluna]=this;
		
		this.moved=true;//indica que a peça já realizou seu movimento
		
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
		int [] vetor = random_positions(linha,coluna);
		while(verificadora) {
			if ((vetor[0])<tab.linha & (vetor[0])>=0 & (vetor[1])<tab.coluna & (vetor[1]>=0)){
				verificadora=false;
			}
			else {
				vetor=random_positions(linha,coluna);
			}
		}
		
		int incremento_linha=vetor[0]-this.linha;
		int incremento_coluna=vetor[1]-this.coluna;
		
		if (tabuleiro[vetor[0]][vetor[1]]==null) {
			tabuleiro[vetor[0]][vetor[1]]=new Atividade(vetor[0],vetor[1],incremento_linha,incremento_coluna);
			tabuleiro[vetor[0]][vetor[1]].vinculate_tabuleiro(tab);
		}

	}

	
}
