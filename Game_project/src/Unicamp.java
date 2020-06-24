import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Unicamp extends JLabel {
	private static final long serialVersionUID = -9047898149398844451L;
	
	private Tabuleiro tabuleiro;
	private int linha,coluna;
	
	public Unicamp(String image) {
		super(new ImageIcon(image));
		setSize(10,10);
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
		if ((vetor[0])>tabuleiro.linha || (vetor[0])<1) {
			return false;
		}
		if ((vetor[1])>tabuleiro.coluna || (vetor[1]<1)) {
			return false;
		}
		return true;
	}
	
	public int movimenta() {//retorna o vetor de posições {linha,coluna} para movimentação
		int[] vetor= random_positions();
		while(verifica_movimento(vetor)==false) {
			vetor=random_positions();
		}
		this.linha=vetor[0];
		this.coluna=vetor[1];
		
		
		int num=(vetor[0]-1)*tabuleiro.linha + vetor[1];// da a posição para ajudar no for da construção do tabuleiro
		return (num);
	}
	
	public void vinculate_tabuleiro(Tabuleiro tabuleiro){
		this.tabuleiro=tabuleiro;
		this.linha=1;//inicia na primeira posição do tabuleiro (podemos mudar)
		this.coluna=1;
	}
	
}
