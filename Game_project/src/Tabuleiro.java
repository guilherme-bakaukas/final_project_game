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
	private String unicamp;
	private String usuario;
	private String corona;
	private String atividade;
	
	public JPanel imagePane;
	private Janela janela;
	
	public int coluna,linha;
	public IPeca[][] tabuleiro;

	

	public Tabuleiro(Janela janela, int linha, int coluna) {//cria o layout com seu tamanho
		
		this.janela=janela;
		this.linha=linha;
		this.coluna=coluna;
		
		imagePane= new JPanel();// painel das imagens
        imagePane.setLayout(new GridLayout(linha,coluna));
        
        this.tabuleiro=new IPeca[linha][coluna];
        
	}
	
	public void create_tabuleiro(String ambiente, String unicamp, String usuario,String corona, String atividade) {//tabuleiro em seu estado inicial
		
		this.ambiente=ambiente;//vincular as classes e imagens do cen�rio ao tabuleiro
		this.usuario=usuario;
		this.unicamp=unicamp;
		this.corona=corona;
		this.atividade=atividade;
		
		
		for (int l=0;l<this.linha;l++) {
			for (int c=0;c<this.coluna;c++) {
				if (l==0 & c==0) {
					tabuleiro[l][c]= new Unicamp(atividade);//passamos a referencia da imagem da atividade para a cria��o da atividade
					tabuleiro[l][c].vinculate_tabuleiro(this);//
				}
				else if (l==this.linha-1 & c==this.coluna-1) {
					tabuleiro[l][c]= new Usuario();
					tabuleiro[l][c].vinculate_tabuleiro(this);
				}
				else {
					tabuleiro[l][c]=null;
				}
			}
		}
		
		this.layout_tabuleiro();
	}
	
	
	private Timer timer=new Timer();//define uma movimenta��o peri�dica das pe�as autom�ticas do tabuleiro
	private long segundos=1000;
		
	private TimerTask tarefa = new TimerTask() {

	@Override
	public void run() {
		movimentar_pecas();//movimenta as pe�as autom�ticamente
		layout_tabuleiro();//reorganiza o layout do tabuleiro ap�s as movimenta��es
		janela.atualizar();//faz a sincroniza��o com do container com o painel (ImagePane)
	}		
	};
		
	
	public void start() {//come�a a rodar o timer e cosequentemente as pe�as se movimentam automaticamente
		timer.schedule(tarefa, 2000, 2000);
	}
	
	private void movimentar_pecas() {
		//m�todo que percorre o tabuleiro e movimenta as pe�as
		
		for (int l=0;l<this.linha;l++) {
			for (int c=0;c<this.coluna;c++) {
				if (tabuleiro[l][c]!=null) {
					if (tabuleiro[l][c].getname()=='u' & tabuleiro[l][c].getmoved()==false) {//verifica qual componente e se j� foi movido na rodada
						tabuleiro = tabuleiro[l][c].move();
					}
					else if (tabuleiro[l][c].getname()=='c' & tabuleiro[l][c].getmoved()==false) {
						tabuleiro=tabuleiro[l][c].move();
					}
					else if (tabuleiro[l][c].getname()=='a' & tabuleiro[l][c].getmoved()==false) {
						tabuleiro=tabuleiro[l][c].move();
					}
				}
			}
		}
		
		
	}
	
	private void layout_tabuleiro() {
		
		imagePane.removeAll();
		
		for (int l=0;l<this.linha;l++) {
			for (int c=0;c<this.coluna;c++) {
				if (tabuleiro[l][c]==null) {
				    ImageIcon imagem = new ImageIcon(ambiente);
				    JLabel campoImagem = new JLabel(imagem);
					imagePane.add(campoImagem);
				}
				else if (tabuleiro[l][c].getname()=='u') {
				    ImageIcon imagem = new ImageIcon(unicamp);
				    JLabel campoImagem = new JLabel(imagem);
					imagePane.add(campoImagem);
					tabuleiro[l][c].setmoved(false);//reseta o getmoved (para poder mov�-lo na pr�xima rodada)
					
				}
				else if (tabuleiro[l][c].getname()=='j') {
				    ImageIcon imagem = new ImageIcon(usuario);
				    JLabel campoImagem = new JLabel(imagem);
					imagePane.add(campoImagem);
					tabuleiro[l][c].setmoved(false);
				}
				else if (tabuleiro[l][c].getname()=='c') {
				    ImageIcon imagem = new ImageIcon(corona);
				    JLabel campoImagem = new JLabel(imagem);
					imagePane.add(campoImagem);
					tabuleiro[l][c].setmoved(false);
				}
				else if (tabuleiro[l][c].getname()=='a') {
				    ImageIcon imagem = new ImageIcon(atividade);
				    JLabel campoImagem = new JLabel(imagem);
					imagePane.add(campoImagem);
					tabuleiro[l][c].setmoved(false);
				}
			}
		}
		
	}
	
}
