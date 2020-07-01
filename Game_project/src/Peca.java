import java.util.Random;

public abstract class Peca implements IPeca {
	
	public int linha,coluna;
	public String image;
	public char name;
	
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
	
	public boolean verifica_movimento(int[] vetor, Tabuleiro tab) {//verifica se as posições se encaixam com o tabuleiro
		if ((vetor[0])>=tab.linha || (vetor[0])<0) {
			return false;
		}
		if ((vetor[1])>=tab.coluna || (vetor[1]<0)) {
			return false;
		}
		return true;
	}
	
}
