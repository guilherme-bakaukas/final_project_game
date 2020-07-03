import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class Janela extends JFrame implements ActionListener{
	private static final long serialVersionUID=7686762169698416749L;
	
	private JPanel controlPane1;
	private JPanel controlPane2;
	private JPanel buttonPane;
	private JPanel startPane;
	
	private Tabuleiro tabuleiro;
	
	public Container principalPane;
	
	private Usuario usuario;
	
	private String caixao;
	private String arquivo;
	private String unicamp;
	private String corona;
	private String atividade;
	private String doente;
	private String vacina;
	private String player1_gui;
	private String player2_vitor;
	
	private JButton try_again;
	private JButton player1;
	private JButton player2;

	private JPanel pontuationPane;
	private int points;

	private JPanel panelPontuation;

	private JLabel labelPontuation;

	private String gui_atividade;

	private String vitor_atividade;

	private String instrucoes;
	
	private JButton next;
	
	
	public Janela(){
		
        super();
        setDefaultCloseOperation(EXIT_ON_CLOSE);//garante que a janela só fecha quando o usuário clica no exit
        setSize(300,300);
        
        
        principalPane= getContentPane();//painel principal
        principalPane.setLayout(new BorderLayout());
         
        
        setVisible(true);

    }
	
	public void painel_instrucoes() {//painel de instruções do jogo aparece logo no início
		
	    ImageIcon imagem = new ImageIcon(instrucoes);
	    JLabel campoImagem = new JLabel(imagem);
	    principalPane.add(campoImagem, BorderLayout.CENTER);
	    
		startPane = new JPanel();
		
		next=new JButton("next");
		next.addActionListener(this);
		
		startPane.add(next);
		
		principalPane.add(startPane,BorderLayout.SOUTH);//adicionado o botão para reiniciar o jogo
	    
	    SwingUtilities.updateComponentTreeUI(this);
	}
	
	public void set_painel_inicial(String player1_gui, String player2_vitor) {
		this.player1_gui=player1_gui;
		this.player2_vitor=player2_vitor;
	}
	
	public void painel_inicial() {
		
		
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel label = new JLabel("<html><span style='font-size:15px'>"+"SELECIONE O SEU PERSONAGEM:"+"</span></html>");

		panel.add(label);
		principalPane.add(panel, BorderLayout.NORTH);
	
		ImageIcon gui = new ImageIcon (player1_gui);
		ImageIcon vitor = new ImageIcon (player2_vitor);
		
		player1 = new JButton("Guilherme", gui);
		player2 = new JButton("Vitor", vitor);
		
	    player1.setVerticalTextPosition(AbstractButton.NORTH);
	    player1.setHorizontalTextPosition(AbstractButton.CENTER);
		
	    player2.setVerticalTextPosition(AbstractButton.NORTH);
	    player2.setHorizontalTextPosition(AbstractButton.CENTER);
	    
	    player1.addActionListener(this);
	    player2.addActionListener(this);
	    
	    JPanel painel = new JPanel();
	    painel.setLayout(new FlowLayout(FlowLayout.CENTER));
	    principalPane.add(painel,BorderLayout.CENTER);
	    
	    painel.add(player1);
	    painel.add(player2);
	    
	      
	    SwingUtilities.updateComponentTreeUI(this);
	}

    public void setButtonUp(JButton botao) {// inserir botao up no painel de cima
    	controlPane1.add(botao);
	}
    public void setButton(JButton botao) {//inserir os demais botões no painel
    	controlPane2.add(botao);
    }

	public void setAmbiente(String arquivo, String unicamp, String corona, String atividade, String doente,String vacina, String caixao,String gui_atividade,String vitor_atividade, String instrucoes) {
		//vicula as imagens a essa classe
		this.arquivo=arquivo;
		this.unicamp=unicamp;
		this.corona=corona;
		this.atividade=atividade;
		this.doente=doente;
		this.vacina=vacina;
		this.caixao=caixao;
		this.gui_atividade=gui_atividade;
		this.vitor_atividade=vitor_atividade;
		this.instrucoes=instrucoes;
	}
	
	
	public void atualizar_pontuation(int points) {//atualiza a pontuação
		this.points=points;
		labelPontuation=new JLabel("<html><span style='font-size:20px'>"+"Pontuação: "+ points+"</span></html>");
		panelPontuation.removeAll();
		panelPontuation.add(labelPontuation);
		SwingUtilities.updateComponentTreeUI(this);
	}
	
	public void atualizar() {//atualiza o painel
		SwingUtilities.updateComponentTreeUI(this);
	}
	
	public void stop() {//método chamado na morte do usuario
		
		principalPane.removeAll();
	    ImageIcon imagem = new ImageIcon(caixao);
	    JLabel campoImagem = new JLabel(imagem);
		principalPane.add(campoImagem,BorderLayout.CENTER);//adicionado o gif do caixão
		
		panelPontuation = new JPanel(new FlowLayout(FlowLayout.CENTER));
		labelPontuation = new JLabel("<html><span style='font-size:30px'>"+"Parabéns, sua pontuação foi de: "+ points + " vacinas"+"</span></html>");
		panelPontuation.add(labelPontuation);
		principalPane.add(panelPontuation, BorderLayout.NORTH);
		
		startPane = new JPanel();
		
		try_again=new JButton("Try again");
		try_again.addActionListener(this);
		
		startPane.add(try_again);
		
		principalPane.add(startPane,BorderLayout.SOUTH);//adicionado o botão para reiniciar o jogo
		
		SwingUtilities.updateComponentTreeUI(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==try_again) {//caso o usuario queira jogar novamente e pressione o botao try again
			principalPane.removeAll();
			this.painel_inicial();//retorna ao painel de escolha do personagem
		}
		else if (e.getSource()==player1) {//escolha do personagem gui
			points=0;//zera a pontuação para o início do jogo
			setPanels_game();
			setButtons_player(player1_gui,gui_atividade);
			tabuleiro.create_tabuleiro(arquivo, unicamp , usuario, corona, atividade, doente, vacina);
			tabuleiro.start();//inicia o jogo
		}
		else if (e.getSource()==player2) {//escolha do personagem vitor
			points=0;//zera a pontuação para o inicio do jogo
			setPanels_game();
			setButtons_player(player2_vitor,vitor_atividade);
			tabuleiro.create_tabuleiro(arquivo, unicamp , usuario, corona, atividade, doente, vacina);
			tabuleiro.start();//inicia o jogo
		}
		else if (e.getSource()==next) {
			principalPane.removeAll();
			this.painel_inicial();
		}
		
		SwingUtilities.updateComponentTreeUI(this);
	
		
	}
	
	private void setButtons_player(String player, String player_atividade) {//vicula o usuario aos botoes
    	JButton up=new JButton("up");
    	this.setButtonUp(up);
    	
    	JButton left=new JButton("left");
    	this.setButton(left);
		
    	JButton down=new JButton("down");
    	this.setButton(down);
    	
    	JButton right=new JButton("right");
    	this.setButton(right);
    		 	
		usuario = new Usuario(player, player_atividade);//vincula o usuario ao personagem escolhido
		
		usuario.vinculateButtons(up, right, left, down);
		
		right.addActionListener(usuario);
		left.addActionListener(usuario);
		up.addActionListener(usuario);
		down.addActionListener(usuario);
	}
	
	public void setPanels_game() {//cria o layout do tabuleiro e o painel de controle (onde haverá os botões de controle do personagem)
		
		principalPane.removeAll();
		
        tabuleiro= new Tabuleiro(this,10,15);// painel das imagens
        principalPane.add(tabuleiro.imagePane,BorderLayout.CENTER);
        
		panelPontuation = new JPanel(new FlowLayout(FlowLayout.CENTER));//painel da pontuação
		labelPontuation = new JLabel("<html><span style='font-size:20px'>"+"Pontuação: "+ points+"</span></html>");
		panelPontuation.add(labelPontuation);
		principalPane.add(panelPontuation, BorderLayout.NORTH);
        
        buttonPane=new JPanel();//painel de controle
        buttonPane.setLayout(new BorderLayout());
        principalPane.add(buttonPane,BorderLayout.SOUTH);
        
        controlPane1=new JPanel();//painel de controle
        controlPane1.setLayout(new FlowLayout());
        buttonPane.add(controlPane1,BorderLayout.CENTER);
        
        controlPane2=new JPanel();//painel de controle
        controlPane2.setLayout(new FlowLayout());
        buttonPane.add(controlPane2,BorderLayout.SOUTH);
	}
	
}

