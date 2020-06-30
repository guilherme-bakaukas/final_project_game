import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Atividade extends Peca implements IPeca {

	private char name;
	private boolean moved;
	private IPeca[][] tabuleiro;
	private Tabuleiro tab;
	private int incremento_linha;
	private int incremento_coluna;

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
	
	
	public IPeca[][] move() {//retorna o vetor de posições {linha,coluna} para movimentação
		int[] vetor= {linha+incremento_linha,coluna+incremento_coluna};
		if (super.verifica_movimento(vetor,tab)==true) {
			
			if (tabuleiro[vetor[0]][vetor[1]]==null) {
				tabuleiro[this.linha][this.coluna]=null;
				
				this.linha=vetor[0];
				this.coluna=vetor[1];
				
				tabuleiro[linha][coluna]=this;
				this.moved=true;//indica que a peça já realizou seu movimento
			}
			else {
				//tratar das colisões
			}
			
		}
		
		else {//caso a atividade esteja no limite do tabuleiroem direção a uma posição inexistente
			tabuleiro[this.linha][this.coluna]=null;//a atividade some
		}
			
		return tabuleiro;
		
	}

}
