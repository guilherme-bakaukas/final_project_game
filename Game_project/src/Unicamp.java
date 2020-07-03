import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Unicamp extends PecasGeradoras {
	private static final long serialVersionUID = -9047898149398844451L;
	
	private String atividade;
	
	public Unicamp(String image, String atividade, int probabilidade) {
		this.name='u';
		this.moved=false;
		this.atividade=atividade;
		this.image=image;
		this.probabilidade=probabilidade;
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
	
	public void move() {
		
		if(this.moved==false) {
			super.move();
		}
		else {//caso ja tenha movido ele deve verificar a geração de atividades
			this.verifica_atividade();
		}
		
		
	}
	
	public void vinculate_tabuleiro(Tabuleiro tab){
		this.tab=tab;
		this.tabuleiro=tab.tabuleiro;
		this.linha=0;//inicia na primeira posição do tabuleiro
		this.coluna=0;
	}
	
	private void verifica_atividade() {
		int num=new Random().nextInt(100 + 1);
		if (num<=this.probabilidade) {//chance de criar atividade
			this.create_atividade();
		}	
	}

	private void create_atividade() {
		
		int[] vetor=create_position();
		
		int incremento_linha=vetor[0]-this.linha;//importante para o movimento da atividade (mesma direção e sentido em que foi criada)
		int incremento_coluna=vetor[1]-this.coluna;
		
		if (tabuleiro[vetor[0]][vetor[1]]==null) {//apenas cria se a posição determinada não esteja ocupada
			tabuleiro[vetor[0]][vetor[1]]=new Atividade(atividade,vetor[0],vetor[1],incremento_linha,incremento_coluna);
			tabuleiro[vetor[0]][vetor[1]].vinculate_tabuleiro(tab);//vinculamos o tabuleiro a atividade criada
		}

	}

	
}
