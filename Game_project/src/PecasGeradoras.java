
public abstract class PecasGeradoras extends Peca {
	
	public Tabuleiro tab;
	public IPeca[][] tabuleiro;
	public boolean moved;
	
	public boolean verifica_movimento(int[] vetor) {
		if (super.verifica_movimento(vetor,tab)==false) return false; //verifica se est� nos limites do tabuleiro
		else if (tabuleiro[vetor[0]][vetor[1]]!=null) {//verifica se o espa�o est� vazio para ir
			return false;
		}
		return true;
	}
	
	public IPeca[][] move() {//retorna o vetor de posi��es {linha,coluna} para movimenta��o
		
		int[] vetor= random_positions();
		while(verifica_movimento(vetor)==false) {
			vetor=random_positions();
		}
		
		tabuleiro[linha][coluna]=null;
		
		this.linha=vetor[0];
		this.coluna=vetor[1];
		
		tabuleiro[linha][coluna]=this;
		
		this.moved=true;//indica que a pe�a j� realizou seu movimento
		
		return tabuleiro;
		
		
	}
}
