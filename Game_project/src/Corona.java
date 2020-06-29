import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Corona extends Peca implements IPeca {

	private char name;
	private boolean moved;
	private Tabuleiro tab;
	private IPeca[][] tabuleiro;

	@Override
	public char getname() {
		return this.name;
	}

	@Override
	public boolean getmoved() {
		return moved;
	}

	@Override
	public IPeca[][] move() {
		return null;
	}

	@Override
	public void setmoved(boolean b) {
		this.moved=b;
		
	}
	
	public Corona() {
		this.name='c';
		this.moved=true;
	}

	@Override
	public void vinculate_tabuleiro(Tabuleiro tab) {
		this.tab=tab;
		this.tabuleiro=tab.tabuleiro;
	}
	
	

}
