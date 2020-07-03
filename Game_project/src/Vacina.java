import java.util.Random;

public class Vacina implements IPeca{

	private char name;
	private boolean moved;
	private Tabuleiro tab;
	private IPeca[][] tabuleiro;
	private String image;
	private int linha;
	private int coluna;
	private int random_linha;
	private int random_coluna;

	public Vacina(String image) {
		this.name='v';
		this.image=image;
		this.linha=5;
		this.coluna=5;
		this.moved=true;
	}
	
	@Override
	public char getname() {
		return this.name;
	}

	@Override
	public boolean getmoved() {
		return this.moved;
	}

	@Override
	public void move() {
		
		while(true) {
			random_linha=new Random().nextInt(tab.linha);
			random_coluna=new Random().nextInt(tab.coluna);
			if (tabuleiro[random_linha][random_coluna]==null) {//encontramos uma espaço vazio aleatorio e colocamos a vacina
				tabuleiro[random_linha][random_coluna]=this;
				this.linha=random_linha;
				this.coluna=random_coluna;
				break;
			}
		}

	}

	@Override
	public void setmoved(boolean b) {
		this.moved=b;
	}

	@Override
	public void vinculate_tabuleiro(Tabuleiro tab) {
		this.tab=tab;
		this.tabuleiro=tab.tabuleiro;	
	}

}
