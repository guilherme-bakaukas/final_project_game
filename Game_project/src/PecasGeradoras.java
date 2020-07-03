
public abstract class PecasGeradoras extends Peca {
	
	protected Tabuleiro tab;
	protected IPeca[][] tabuleiro;
	protected boolean moved;
	
	public boolean verifica_movimento(int[] vetor) {
		if (super.verifica_movimento(vetor,tab)==false) return false; //verifica se est� nos limites do tabuleiro
		else if (tabuleiro[vetor[0]][vetor[1]]!=null) {//verifica se o espa�o est� vazio para ir
			return false;
		}
		return true;
	}
	
	public void move() {//retorna o vetor de posi��es {linha,coluna} para movimenta��o
		
		int[] vetor= random_positions();
		while(verifica_movimento(vetor)==false) {
			vetor=random_positions();
		}
		
		tabuleiro[linha][coluna]=null;
		
		this.linha=vetor[0];
		this.coluna=vetor[1];
		
		tabuleiro[linha][coluna]=this;
		
		this.moved=true;//indica que a pe�a j� realizou seu movimento
		
		
	}
	
	public int[] create_position() {//retorna a posi��o da pe�a criada
		
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
		return vetor;
	}
}

