import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Corona extends PecasGeradas implements IPeca {

	@Override
	public char getname() {
		return this.name;
	}

	@Override
	public boolean getmoved() {
		return moved;
	}

	@Override
	public void setmoved(boolean b) {
		this.moved=b;
		
	}
	
	public Corona(String image, int linha,int coluna, int incremento_linha, int incremento_coluna) {
		this.name='c';
		this.moved=true;
		this.linha=linha;
		this.coluna=coluna;
		this.incremento_linha=incremento_linha;
		this.incremento_coluna=incremento_coluna;
		this.image=image;
	}

	@Override
	public void vinculate_tabuleiro(Tabuleiro tab) {
		this.tab=tab;
		this.tabuleiro=tab.tabuleiro;
	}

	@Override
	public void colisao_jogador(int[] vetor) {
		System.out.println("morreu");
		//fazer morrer de fato
		tabuleiro[this.linha][this.coluna]=null;//peça some
		
	}
	
	

}
