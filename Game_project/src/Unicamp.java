import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Unicamp extends JLabel implements IPeca {
	private static final long serialVersionUID = -9047898149398844451L;
	
	private Tabuleiro tab;
	private IPeca[][] tabuleiro;
	private int linha,coluna;
	private char name;
	private boolean moved;
	
	public Unicamp(String image) {
		super(new ImageIcon(image));
		setSize(10,10);
		this.name='u';
		this.moved=false;
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
	
	public int[] random_positions() {
		int random_linha=0;
		int random_coluna=0;
	
		while(random_linha==0 & random_coluna==0 ) {//para não ficar parado
			random_linha=new Random().nextInt(2 + 1)-1;//gera valores de -1 até 1, para indicar o movimento da unicamp
			random_coluna=new Random().nextInt(2 + 1)-1;	
		}
		int vetor[]= {(linha+random_linha),(coluna+random_coluna)};
		return vetor;
	}
	
	public boolean verifica_movimento(int[] vetor) {//verifica se as posições se encaixam com o tabuleiro
		if ((vetor[0])>=tab.linha || (vetor[0])<0) {
			return false;
		}
		if ((vetor[1])>=tab.coluna || (vetor[1]<0)) {
			return false;
		}
		return true;
	}
	
	public IPeca[][] move() {//retorna o vetor de posições {linha,coluna} para movimentação
		
		int[] vetor= random_positions();
		while(verifica_movimento(vetor)==false) {
			vetor=random_positions();
		}
		
		tabuleiro[linha][coluna]=null;
		
		this.linha=vetor[0];
		this.coluna=vetor[1];
		
		System.out.println("TABULEIRO unicamp_linha: "+linha+" unicamp coluna: "+ coluna);
		
		tabuleiro[linha][coluna]=this;
		
		this.moved=true;//indica que a peça já realizou seu movimento
		
		return tabuleiro;
		
	}
	
	public void vinculate_tabuleiro(Tabuleiro tab){
		this.tab=tab;
		this.tabuleiro=tab.tabuleiro;
		this.linha=0;//inicia na primeira posição do tabuleiro (podemos mudar)
		this.coluna=0;
	}

	
}
