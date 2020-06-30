import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Usuario extends Peca implements ActionListener,IPeca {
	private static final long serialVersionUID = -8007636677009859732L;
	
	private JButton up,right,left,down;
	private Tabuleiro tab;
	private IPeca[][] tabuleiro;
	private char name;
	private boolean moved;
	
	public void actionPerformed(ActionEvent event) {
		
		int[] vetor= {linha,coluna};
		
		if (event.getSource()==right) {
			vetor[1]++;
			if (super.verifica_movimento(vetor,tab)==true) {
				tabuleiro[linha][coluna]=null;
				this.coluna++;
				tabuleiro=this.move();
			}
		}
		else if (event.getSource()==left) {
			vetor[1]--;
			if (super.verifica_movimento(vetor,tab)==true) {
				tabuleiro[linha][coluna]=null;
				this.coluna--;
				tabuleiro=this.move();
			}
		}
		else if (event.getSource()==up) {
			
			vetor[0]--;
			
			if (super.verifica_movimento(vetor,tab)==true) {
				tabuleiro[linha][coluna]=null;
				this.linha--;
				tabuleiro=this.move();
			}
		}
		else if (event.getSource()==down) {
			vetor[0]++;
			if (super.verifica_movimento(vetor,tab)==true) {
				tabuleiro[linha][coluna]=null;
				this.linha++;
				tabuleiro=this.move();
			}
		}
		
		//FALTA VERIFICAR COLIS�ES
		
		tab.layout_tabuleiro();
		tab.atualizar_tabuleiro();
		
	}
	
	public Usuario(String image) {
		this.name='j';
		this.moved=false;
		this.image=image;
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
		this.linha=(tab.linha)-1;//sua localiza��o inicial � a ultima do tabuleiro
		this.coluna=(tab.coluna)-1;
	}

	public char getname() {
		return this.name;
	}

	public IPeca[][] move() {
		tabuleiro[linha][coluna]=this;
		return tabuleiro;
	}
	
	public boolean getmoved() {
		return this.moved;
		
	}

	public void setmoved(boolean b) {
		this.moved=b;
		
	}



}
