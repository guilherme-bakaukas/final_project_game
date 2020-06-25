
public interface IPeca {//interface criada para organizar as peças e suas localizações
	
	public char getname();//identificar a peça
	
	public boolean getmoved();//analisar se a peça ja se moveu ou não
	
	public IPeca[][] move();//movimentaçõ da peça
	
	public void setmoved(boolean b);//definir seu status de movimentação
}
