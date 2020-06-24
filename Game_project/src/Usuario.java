import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Usuario extends JLabel implements ActionListener {
	private static final long serialVersionUID = -8007636677009859732L;
	
	private JButton up,right,left,down;
	private Janela janela;
	private int linha,coluna;//define a posição da imagem inicia no zero
	private Tabuleiro tabuleiro;
	
	public void actionPerformed(ActionEvent event) {
		if (event.getSource()==right) {
			if (coluna<tabuleiro.coluna) {
				coluna++;
				janela.recreate_cenario(linha,coluna);
			}
		}
		if (event.getSource()==left) {
			if (coluna>1) {
				coluna--;
				janela.recreate_cenario(linha,coluna);
			}
		}
		if (event.getSource()==up) {
			if (linha>1) {
				linha--;
				janela.recreate_cenario(linha, coluna);
			}
		}
		if (event.getSource()==down) {
			if (linha<tabuleiro.linha) {
				linha++;
				janela.recreate_cenario(linha, coluna);
			}
			
		}
	}
	
	public Usuario(String image) {
		super(new ImageIcon(image));
		setSize(10,10);
	}

	public void vinculateButtons(JButton up,JButton right,JButton left,JButton down) {
		this.up=up;
		this.right=right;
		this.left=left;
		this.down=down;
	}
	
	public void vinculateJanela(Janela janela) {
		this.janela=janela;
	}

	public void vinculate_tabuleiro(Tabuleiro tabuleiro) {
		this.tabuleiro=tabuleiro;
		this.linha=tabuleiro.linha;//usuario começa na ultima posição do tabuleiro
		this.coluna=tabuleiro.coluna;
	}


}
