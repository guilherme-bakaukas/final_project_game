
public interface IPeca {//interface criada para organizar as pe�as e suas localiza��es
	
	public char getname();//identificar a pe�a
	
	public boolean getmoved();//analisar se a pe�a ja se moveu ou n�o
	
	public void move();//movimenta��o da pe�a
	
	public void setmoved(boolean b);//definir seu status de movimenta��o

	public void vinculate_tabuleiro(Tabuleiro tab);//vincular o tabuleiro para realizar as mudan�as de movimenta��o
}
