import java.awt.GridLayout;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Tabuleiro {
	private static final long serialVersionUID = -6380363143384619115L;
	
	private String ambiente;
	private Unicamp unicamp;
	private Usuario usuario;
	public JPanel imagePane;
	private Janela janela;
	public int coluna,linha;
	

	public Tabuleiro(Janela janela, int linha, int coluna) {//cria o layout com seu tamanho
		
		this.janela=janela;
		this.linha=linha;
		this.coluna=coluna;
		
		imagePane= new JPanel();// painel das imagens
        imagePane.setLayout(new GridLayout(linha,coluna));
        
	}
	
	public void create_tabuleiro(String ambiente, Unicamp unicamp, Usuario usuario) {//tabuleiro em seu estado inicial
		
		this.ambiente=ambiente;//vincular as classes e imagens do cenário ao tabuleiro
		this.usuario=usuario;
		this.unicamp=unicamp;
		
		vinculate_components();//vincula o tabuleiro aos seus componentes
		
		imagePane.add(unicamp);
		
		for (int i=1;i<(linha*coluna-1);i++) {
		    ImageIcon imagem = new ImageIcon(ambiente);
		    JLabel campoImagem = new JLabel(imagem);
			imagePane.add(campoImagem);
			}
		
		imagePane.add(usuario);
	}
	
	private void vinculate_components() {
		unicamp.vinculate_tabuleiro(this);
		usuario.vinculate_tabuleiro(this);
	}
	
	private Timer timer=new Timer();
	private long segundos=1000;
		
	private TimerTask tarefa = new TimerTask() {

	@Override
	public void run() {
		movimentar_pecas();
		janela.atualizar();
	}		
	};
		
	
	public void start() {//começa a rodar o timer e cosequentemente as peças se movimentam automaticamente
		timer.schedule(tarefa, 2000, 2000);
	}
	
	private void movimentar_pecas() {
		
		imagePane.removeAll();
		
		int pos_unicamp = unicamp.movimenta();
		
		for (int i=1;i<pos_unicamp;i++) {
			ImageIcon imagem = new ImageIcon(ambiente);
		    JLabel campoImagem = new JLabel(imagem);
			imagePane.add(campoImagem);
		}
	    
		imagePane.add(unicamp);
		
		for (int i=pos_unicamp+1;i<(linha*coluna);i++) {
			ImageIcon imagem = new ImageIcon(ambiente);
		    JLabel campoImagem = new JLabel(imagem);
			imagePane.add(campoImagem);
		}
		
		imagePane.add(usuario);
		
	}
	
	public void atualizar(int linha, int coluna) {
		
	}
}
