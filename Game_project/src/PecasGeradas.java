
public abstract class PecasGeradas extends Peca {
	
	protected IPeca[][] tabuleiro;
	protected Tabuleiro tab;
	protected int incremento_linha;
	protected int incremento_coluna;
	protected boolean moved;
	
	public IPeca[][] move() {//retorna o vetor de posi��es {linha,coluna} para movimenta��o
		
		int[] vetor= {linha+incremento_linha,coluna+incremento_coluna};
		
		if (super.verifica_movimento(vetor,tab)==true) {
			
			if (tabuleiro[vetor[0]][vetor[1]]==null) {
				tabuleiro[this.linha][this.coluna]=null;
				
				this.linha=vetor[0];
				this.coluna=vetor[1];
				
				tabuleiro[linha][coluna]=this;
				this.moved=true;//indica que a pe�a j� realizou seu movimento
			}
			else {
				if (tabuleiro[vetor[0]][vetor[1]].getname()=='j') {//caso colida com o jogador
					colisao_jogador(vetor);
				}
				else {
					tabuleiro[this.linha][this.coluna]=null;//caso colida com alguma coisa ele deve sumir
				}
			}
			
		}
		
		else {//caso a pe�a esteja no limite do tabuleiro em dire��o a uma posi��o inexistente
			tabuleiro[this.linha][this.coluna]=null;//a pe�a some
		}
			
		return tabuleiro;
		
	}
	
	public abstract void colisao_jogador(int[] vetor);

}
