package game;

public class Atividade extends PecasGeradas {

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
	public void setmoved(boolean b) {
		this.moved = b;
	}
	
	public Atividade(String image, int linha,int coluna, int incremento_linha, int incremento_coluna) {
		this.name='a';
		this.moved=true;
		this.linha=linha;
		this.coluna=coluna;
		this.incremento_linha=incremento_linha;
		this.incremento_coluna=incremento_coluna;
		this.image=image;
	}
	
	public void vinculate_tabuleiro(Tabuleiro tab) {
		this.tab=tab;
		this.tabuleiro=tab.tabuleiro;
	}
	

	@Override
	public void colisao_jogador(int[] vetor) {
		tabuleiro[vetor[0]][vetor[1]].setmoved(true);//usuario afetado pela atividade
		tabuleiro[this.linha][this.coluna]=null;//peça some
	}

}
