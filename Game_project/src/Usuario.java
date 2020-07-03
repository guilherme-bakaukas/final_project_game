import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Usuario extends Peca implements ActionListener {
	private static final long serialVersionUID = -8007636677009859732L;
	
	private JButton up,right,left,down;
	private Tabuleiro tab;
	private IPeca[][] tabuleiro;
	private boolean moved;

	private String player_atividade;

	private String normal_player;
	
	public void actionPerformed(ActionEvent event) {
		
		if (this.moved==false) {//verifica se poder� se mover ou se est� sob efeito da atividade (2 rodadas)
			int[] vetor= {linha,coluna};
			
			if (event.getSource()==right) {
				vetor[1]++;
				if (verifica_movimento(vetor)==true) {
					tabuleiro[linha][coluna]=null;
					this.coluna++;
					this.move();
				}
			}
			else if (event.getSource()==left) {
				vetor[1]--;
				if (verifica_movimento(vetor)==true) {
					tabuleiro[linha][coluna]=null;
					this.coluna--;
					this.move();
				}
			}
			else if (event.getSource()==up) {	
				vetor[0]--;
				if (verifica_movimento(vetor)==true) {
					tabuleiro[linha][coluna]=null;
					this.linha--;
					this.move();
				}
			}
			else if (event.getSource()==down) {
				vetor[0]++;
				if (verifica_movimento(vetor)==true) {
					tabuleiro[linha][coluna]=null;
					this.linha++;
					this.move();
				}
			}
			
			//FALTA VERIFICAR COLIS�ES
			tab.layout_tabuleiro();
			tab.atualizar_tabuleiro();
		}

		
	}
	
	public Usuario(String image, String player_atividade) {
		this.name='j';
		this.moved=false;
		this.normal_player=image;
		this.image=image;
		this.player_atividade=player_atividade;
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

	public void move() {
		tabuleiro[linha][coluna]=this;
	}
	
	public boolean getmoved() {
		return this.moved;
		
	}

	public void setmoved(boolean b) {
		if (b==true) {
			this.image=player_atividade;//determina quando o usuario n�o pode se mexer (imagem do usuario com atividade)
		}
		else if (b==false) {
			this.image=normal_player;//esse m�todo � utilizado quando o usuario passa a poder se movimentar (imagem usuario normal)
		}
		this.moved=b;
		
	}
	
	private boolean verifica_movimento(int[] vetor) {
		if (super.verifica_movimento(vetor, tab)==false) return false;//indica uma posi��o inexistente no tabuleiro
		if (tabuleiro[vetor[0]][vetor[1]]!=null) {//posi��o ocupada
			switch (tabuleiro[vetor[0]][vetor[1]].getname()) {
			case 'a':
				this.setmoved(true);//pegou atividade e dever� ficar sem movimentar por duas rodadas
				break;
			case 'c':
				tab.die();//usuario morre
				break;
			case 'v':
				tabuleiro[vetor[0]][vetor[1]].move();//� movimentado na rodada seguinte
				tabuleiro[vetor[0]][vetor[1]]=null;
				tab.atualizar_pontuation();//atualiza o painel de pontua��o
				break;
			case 'u':
				return false;//n�o poderia se movimentar caso haja unicamp ou doente na posi��o requerida
			case 'd':
				return false;
			}
		}
		return true;
		
	}


}
