
public abstract class PecasGeradas extends Peca {
	
	protected IPeca[][] tabuleiro;
	protected Tabuleiro tab;
	protected int incremento_linha;
	protected int incremento_coluna;
	protected boolean moved;
	
	public void move() {//retorna o vetor de posições {linha,coluna} para movimentação
		
		int[] vetor= {linha+incremento_linha,coluna+incremento_coluna};
		
		try{
			
			super.verifica_movimento(vetor,tab);
		
		
			
			if (tabuleiro[vetor[0]][vetor[1]]==null) {
				tabuleiro[this.linha][this.coluna]=null;
				
				this.linha=vetor[0];
				this.coluna=vetor[1];
				
				tabuleiro[linha][coluna]=this;
				this.moved=true;//indica que a peça já realizou seu movimento
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
		catch(MovimentoInvalido erro) {//caso a peça esteja no limite do tabuleiro em direção a uma posição inexistente
			tabuleiro[this.linha][this.coluna]=null;//a peça some
		}
		catch(Exception erro) {//caso a peça esteja no limite do tabuleiro em direção a uma posição inexistente
			System.out.println("Outro erro: " + erro.getMessage());
		}
		
			
		
	}
	
	public abstract void colisao_jogador(int[] vetor);//método para tratar da colisão com o usuario

}
