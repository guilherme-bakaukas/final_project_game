
public abstract class PecasGeradoras extends Peca {
	
	protected int probabilidade;
	protected Tabuleiro tab;
	protected IPeca[][] tabuleiro;
	protected boolean moved;
	
	public void verifica_movimento(int[] vetor) throws MovimentoInvalido {
		try{	
			super.verifica_movimento(vetor,tab);
			if (tabuleiro[vetor[0]][vetor[1]]!=null) {//verifica se o espaço está vazio para ir
				throw new ColisaoInvalida();
			}
		}//verifica se está nos limites do tabuleiro
		catch(SairTabuleiro erro) {
			throw new SairTabuleiro();
		}
		catch(ColisaoInvalida erro) {
			throw new ColisaoInvalida();
		}
		
		catch (Exception erro) {
			   System.out.println("Outro erro: " + erro.getMessage());
		}
		
		
	}
	
	public void move() {//retorna o vetor de posições {linha,coluna} para movimentação
		
		int[] vetor= random_positions();
		while(true) {
			try{verifica_movimento(vetor);
				break;
			}
			catch(MovimentoInvalido erro) {
				vetor=random_positions();
			}
			catch (Exception erro) {
				   System.out.println("Outro erro: " + erro.getMessage());
			}
			
		}
		
		tabuleiro[linha][coluna]=null;
		
		this.linha=vetor[0];
		this.coluna=vetor[1];
		
		tabuleiro[linha][coluna]=this;
		
		this.moved=true;//indica que a peça já realizou seu movimento
		
		
	}
	
	public int[] create_position() {//retorna a posição da peça criada
		
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

