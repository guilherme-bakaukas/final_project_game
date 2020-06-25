import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Usuario extends JLabel implements ActionListener,IPeca {
	private static final long serialVersionUID = -8007636677009859732L;
	
	private JButton up,right,left,down;
	private int linha,coluna;//define a posição da imagem inicia no zero
	private Tabuleiro tab;
	private IPeca[][] tabuleiro;
	private char name;
	private boolean moved;
	
	public void actionPerformed(ActionEvent event) {
		if (event.getSource()==right) {
			if (this.coluna<tab.coluna-1) {//testa os limites do tabuleiro
				//verificar movimento
			}
		}
		if (event.getSource()==left) {
			if (this.coluna>0) {
				//verificar movimento
			}
		}
		if (event.getSource()==up) {
			if (this.linha>0) {
				//verificar movimento
			}
		}
		if (event.getSource()==down) {
			if (this.linha<tab.linha-1) {
				//verificar movimento
			}
			
		}
	}
	
	public Usuario(String image) {
		super(new ImageIcon(image));
		setSize(10,10);
		this.name='j';
		this.moved=false;
	}

	public void vinculateButtons(JButton up,JButton right,JButton left,JButton down) {
		this.up=up;
		this.right=right;
		this.left=left;
		this.down=down;
	}

	public void vinculate_tabuleiro(Tabuleiro tab) {
		this.tab=tab;
		this.tabuleiro=tab.tabuleiro;
		this.linha=tab.linha-1;//sua localização inicial é a ultima do tabuleiro
		this.coluna=tab.coluna-1;
	}

	public char getname() {
		return this.name;
	}

	public IPeca[][] move() {
		// movimentar o jogador
		return null;
	}
	
	public boolean getmoved() {
		return this.moved;
		
	}

	public void setmoved(boolean b) {
		this.moved=b;
		
	}



}
