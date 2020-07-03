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
		
		if (this.moved==false) {//verifica se poderá se mover ou se está sob efeito da atividade (2 rodadas)
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
			
			//FALTA VERIFICAR COLISÕES
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
		this.linha=(tab.linha)-1;//sua localização inicial é a ultima do tabuleiro
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
			this.image=player_atividade;//determina quando o usuario não pode se mexer (imagem do usuario com atividade)
		}
		else if (b==false) {
			this.image=normal_player;//esse método é utilizado quando o usuario passa a poder se movimentar (imagem usuario normal)
		}
		this.moved=b;
		
	}
	
	private boolean verifica_movimento(int[] vetor) {
		if (super.verifica_movimento(vetor, tab)==false) return false;//indica uma posição inexistente no tabuleiro
		if (tabuleiro[vetor[0]][vetor[1]]!=null) {//posição ocupada
			switch (tabuleiro[vetor[0]][vetor[1]].getname()) {
			case 'a':
				this.setmoved(true);//pegou atividade e deverá ficar sem movimentar por duas rodadas
				break;
			case 'c':
				tab.die();//usuario morre
				break;
			case 'v':
				tabuleiro[vetor[0]][vetor[1]].move();//é movimentado na rodada seguinte
				tabuleiro[vetor[0]][vetor[1]]=null;
				tab.atualizar_pontuation();//atualiza o painel de pontuação
				break;
			case 'u':
				return false;//não poderia se movimentar caso haja unicamp ou doente na posição requerida
			case 'd':
				return false;
			}
		}
		return true;
		
	}


}
