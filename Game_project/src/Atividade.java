import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Atividade implements IPeca {

	private char name;
	private boolean moved;
	private int linha;
	private int coluna;
	private IPeca[][] tabuleiro;
	private Tabuleiro tab;

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
	public IPeca[][] move() {
		
		
		return tabuleiro;
	}

	@Override
	public void setmoved(boolean b) {
		this.moved = b;
		
	}
	
	public Atividade(int linha,int coluna) {
		this.name='a';
		this.moved=true;
		this.linha=linha;
		this.coluna=coluna;
	}
	
	public void vinculate_tabuleiro(Tabuleiro tab) {
		this.tab=tab;
		this.tabuleiro=tab.tabuleiro;
	}

}
