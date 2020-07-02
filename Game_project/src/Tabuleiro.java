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
	private Usuario usuario;
	private String corona;
	private String atividade;
	private String doente;
	private String vacina;
	
	public JPanel imagePane;
	private Janela janela;
	
	private int rodadas;
	public int coluna,linha;
	public IPeca[][] tabuleiro;
	private boolean gerar;


	

	public Tabuleiro(Janela janela, int linha, int coluna) {//cria o layout com seu tamanho
		
		this.janela=janela;
		this.linha=linha;
		this.coluna=coluna;
		this.gerar=false;
		
		imagePane= new JPanel();// painel das imagens
        imagePane.setLayout(new GridLayout(linha,coluna));
        
        this.tabuleiro=new IPeca[linha][coluna];
        
	}
	
	public void create_tabuleiro(String ambiente, String unicamp, Usuario usuario,String corona, String atividade, String doente,String vacina) {//tabuleiro em seu estado inicial
		
		this.ambiente=ambiente;//vincular as classes e imagens do cen�rio ao tabuleiro
		this.usuario=usuario;
		this.unicamp=unicamp;
		this.corona=corona;
		this.atividade=atividade;
		this.doente=doente;
		this.vacina=vacina; 
		
		
		for (int l=0;l<this.linha;l++) {
			for (int c=0;c<this.coluna;c++) {
				if (l==0 & c==0) {
					tabuleiro[l][c]= new Unicamp(unicamp,atividade);//passamos a referencia da imagem da atividade para a cria��o da atividade
					tabuleiro[l][c].vinculate_tabuleiro(this);
				}
				else if (l==this.linha-1 & c==this.coluna-1) {
					tabuleiro[l][c]= usuario;
					tabuleiro[l][c].vinculate_tabuleiro(this);
					this.rodadas=0;//vari�vel para contabilizar as rodadas em que o usuario est� sem poder jogar
				}
				else if (l==0 & c==this.coluna-1) {
					tabuleiro[l][c]=new Doente(doente,corona);
					tabuleiro[l][c].vinculate_tabuleiro(this);
				}
				else {
					tabuleiro[l][c]=null;
				}
			}
		}
		
		this.layout_tabuleiro();
	}
	
	
	private Timer timer;
	private long segundos=1000;
		
	private TimerTask tarefa;
	
	public void start() {//come�a a rodar o timer e cosequentemente as pe�as se movimentam automaticamente
		timer=new Timer();
		tarefa = new TimerTask() {

			@Override
			public void run() {
				
				//intercala a movimena��o da cria��o das pe�as, para mant�-la parada no momento de gerar uma pe�a
				
				if (gerar==false) {
					movimentar_pecas();//movimenta as pe�as autom�ticamente
					gerar=true;
				}
				else if (gerar==true) {
					gera_pecas();//verifica se haver� gera��o de pe�as
					reorganizar_tabuleiro();//reseta o moved das pe�as
					gerar=false;
				}
				
				layout_tabuleiro();//reorganiza o layout do tabuleiro ap�s as movimenta��es
				janela.atualizar();//faz a sincroniza��o com do container com o painel (ImagePane)
			}	
			};
		timer.schedule(tarefa, 1000, 1000);
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
					else if (tabuleiro[l][c].getname()=='j') {//fazemos a verifica��o se poder� se mover
						if (tabuleiro[l][c].getmoved()==true) {
							if (rodadas<2) {//deve passar duas rodadas sem se mover
								this.rodadas++;
							}
							else {
								rodadas=0;
								tabuleiro[l][c].setmoved(false);
							}
						}
					}
					else if (tabuleiro[l][c].getname()=='d' & tabuleiro[l][c].getmoved()==false) {
						tabuleiro=tabuleiro[l][c].move();
					}
				}
			}
		}
		
		
	}
	
	private void gera_pecas() {
		for (int l=0;l<this.linha;l++) {
			for (int c=0;c<this.coluna;c++) {
				if (tabuleiro[l][c]!=null) {
					if (tabuleiro[l][c].getname()=='u') {
						tabuleiro[l][c].move();//realiza a verifica�a� de gera��o
					}
					if (tabuleiro[l][c].getname()=='d') {
						tabuleiro[l][c].move();
					}
				}
			}
		}
	}
	
	private void reorganizar_tabuleiro() {
		for (int l=0;l<this.linha;l++) {
			for (int c=0;c<this.coluna;c++) {
				if (tabuleiro[l][c]!=null) {
					if (tabuleiro[l][c].getname()!='j') {
						tabuleiro[l][c].setmoved(false);
					}
				}
			}
		}
	}
	
	public void layout_tabuleiro() {
		
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
					
				}
				else if (tabuleiro[l][c].getname()=='j') {
				    ImageIcon imagem = new ImageIcon(usuario.image);
				    JLabel campoImagem = new JLabel(imagem);
					imagePane.add(campoImagem);
				}
				else if (tabuleiro[l][c].getname()=='c') {
				    ImageIcon imagem = new ImageIcon(corona);
				    JLabel campoImagem = new JLabel(imagem);
					imagePane.add(campoImagem);
				}
				else if (tabuleiro[l][c].getname()=='a') {
				    ImageIcon imagem = new ImageIcon(atividade);
				    JLabel campoImagem = new JLabel(imagem);
					imagePane.add(campoImagem);
				}
				else if (tabuleiro[l][c].getname()=='d') {
				    ImageIcon imagem = new ImageIcon(doente);
				    JLabel campoImagem = new JLabel(imagem);
					imagePane.add(campoImagem);
				}
			}
		}
		
	}
	public void atualizar_tabuleiro() {
		janela.atualizar();
	}
	
	public void die() {
		timer.cancel();
		janela.stop();
	}
	
}
