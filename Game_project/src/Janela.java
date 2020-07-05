import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Janela extends JFrame implements ActionListener{
	private static final long serialVersionUID=7686762169698416749L;
	
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
	private String gui_atividade;
	private String vitor_atividade;
	private String instrucoes;
	
	private JButton try_again;
	private JButton player1;
	private JButton player2;
	private JButton next;
	private JButton start;
	private JButton insane;
	private JButton hard;
	private JButton medium;
	private JButton easy;
	
	private JLabel labelPontuation;
	private JLabel label_text;
	private JLabel label_dificuldade;
	
	private JPanel panel_text;
	private JPanel painel_personagens;
	private JPanel panel_dificuldade;
	private JPanel tryPane;
	private JPanel controlPane1;
	private JPanel controlPane2;
	private JPanel buttonPane;
	private JPanel startPane;
	private JPanel panelPontuation;

	private int seconds;
	private int points;
	private int probabilidade;

	private boolean player_defined;
	private boolean dificuldade_defined;
	
	public Janela(){
		
        super();
        setDefaultCloseOperation(EXIT_ON_CLOSE);//garante que a janela só fecha quando o usuário clica no exit
        setSize(700,500);
        
        
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
	
	public void set_painel_inicial(String player1_gui, String player2_vitor) {//prepara o painel inicial, sem adicioná-los ao contanier principal
		
		this.player1_gui=player1_gui;
		this.player2_vitor=player2_vitor;
		
		panel_text = new JPanel(new FlowLayout(FlowLayout.CENTER));
		label_text = new JLabel("<html><span style='font-size:15px'>"+"SELECIONE O SEU PERSONAGEM:"+"</span></html>");
		panel_text.add(label_text);
		
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
	    
	    painel_personagens = new JPanel();
	    painel_personagens.setLayout(new FlowLayout(FlowLayout.CENTER));
	    
	    painel_personagens.add(panel_text);
	    painel_personagens.add(player1);
	    painel_personagens.add(player2);//prepara painel com o texto "escolha o seu personagem" e os botões com imagem dos dois personagens
	    
	    panel_dificuldade=new JPanel(new FlowLayout(FlowLayout.CENTER));
	    label_dificuldade = new JLabel("<html><span style='font-size:15px'>"+"SELECIONE A DIFICULDADE:"+"</span></html>");
	    panel_dificuldade.add(label_dificuldade);
	    
	    insane=new JButton("insano");
	    hard=new JButton("difícil");
	    medium=new JButton("médio");
	    easy=new JButton("fácil");
	    
	    insane.addActionListener(this);
	    hard.addActionListener(this);
	    medium.addActionListener(this);
	    easy.addActionListener(this);
	    
	    panel_dificuldade.add(insane);
	    panel_dificuldade.add(hard);
	    panel_dificuldade.add(medium);
	    panel_dificuldade.add(easy);//prepara o painel de dificuldade 
	    
	    startPane=new JPanel();
	    start=new JButton("start");
	    start.addActionListener(this);
	    startPane.add(start);//prepara o painel do botão start
	    
		
	}
	
	public void painel_inicial() {//adiciona os paineis preparados no set_painel_inicial
		
	    principalPane.add(painel_personagens,BorderLayout.CENTER); 
	    principalPane.add(panel_dificuldade,BorderLayout.NORTH);
	    principalPane.add(startPane,BorderLayout.SOUTH); 
		points=0;//zera a pontuação para o início do jogo
		setPanels_game();//cria os paineis do jogo (tabuleiro, painel dos botões de controle e painel da pontuação)
	    SwingUtilities.updateComponentTreeUI(this);
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
	
	
	public void atualizar_pontuation(int points) {//atualiza o painel da pontuação ao pegar a vacina
		this.points=points;
		labelPontuation=new JLabel("<html><span style='font-size:20px'>"+"Pontuação: "+ points+"</span></html>");
		panelPontuation.removeAll();
		panelPontuation.add(labelPontuation);
		SwingUtilities.updateComponentTreeUI(this);
	}
	
	public void atualizar() {//atualiza a janela
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
		principalPane.add(panelPontuation, BorderLayout.NORTH);//adicionado a mensagem dos pontos somados
		
		tryPane = new JPanel();
		try_again=new JButton("Try again");
		try_again.addActionListener(this);
		tryPane.add(try_again);
		
		principalPane.add(tryPane,BorderLayout.SOUTH);//adicionado o botão para reiniciar o jogo
		
		SwingUtilities.updateComponentTreeUI(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource()==try_again) {//caso o usuario queira jogar novamente e pressione o botao try again
			principalPane.removeAll();
			setcolors_dificuldade();
			setcolors_personagens();
			this.painel_inicial();//retorna ao painel de escolha do personagem
			set_defined();//redefine player_defined e dificuldade_defined para falso
			
		}
		else if (e.getSource()==player1) {//escolha do personagem gui
			player2.setBackground(Color.lightGray);
			player1.setBackground(Color.green);
			setButtons_player(player1_gui,gui_atividade);
			player_defined=true;
		}
		else if (e.getSource()==player2) {//escolha do personagem vitor
			player1.setBackground(Color.lightGray);
			player2.setBackground(Color.green);
			setButtons_player(player2_vitor,vitor_atividade);
			player_defined=true;
		}
		else if (e.getSource()==next) {//ir para a seleção dos personagens e dificuldade
			setcolors_dificuldade();
			setcolors_personagens();
			principalPane.removeAll();
			this.painel_inicial();
			set_defined();//redefine player_defined e dificuldade_defined para falso
		}
		else if (e.getSource()==start) {
			if (player_defined==true & dificuldade_defined==true) {
				start();//inicia o jogo apenas se o usuário definir o personagem e o nível de dificuldade
			}
		}
		else if (e.getSource()==insane) {
			setcolors_dificuldade();//reseta os botões para cinza
			insane.setBackground(Color.green);//botão selecionado fica verde
			this.seconds=100;//segundos do timer (100ms para cada rodada do tabuleiro)
			this.probabilidade=95;//probabilidade de gerar corona e atividade
			dificuldade_defined=true;
		}
		else if (e.getSource()==hard) {
			setcolors_dificuldade();
			hard.setBackground(Color.green);
			this.seconds=250;
			this.probabilidade=80;
			dificuldade_defined=true;
		}
		else if (e.getSource()==medium) {
			setcolors_dificuldade();
			medium.setBackground(Color.green);
			this.seconds=500;
			this.probabilidade=50;
			dificuldade_defined=true;
		}
		else if (e.getSource()==easy) {
			setcolors_dificuldade();
			easy.setBackground(Color.green);
			this.seconds=1000;
			this.probabilidade=30;
			dificuldade_defined=true;
		}
		
		SwingUtilities.updateComponentTreeUI(this);
	
		
	}
	
	private void setcolors_personagens() {//reseta os botões para cinza
		
		player1.setBackground(Color.lightGray);
		player2.setBackground(Color.lightGray);
		
	}

	private void setcolors_dificuldade() {//reseta os botões para cinza
		
		insane.setBackground(Color.lightGray);
		hard.setBackground(Color.lightGray);
		medium.setBackground(Color.lightGray);
		easy.setBackground(Color.lightGray);
		
	}

	private void start() {
		
		principalPane.removeAll();
		principalPane.add(tabuleiro.imagePane,BorderLayout.CENTER);//adiciona os paineis já prontos
		principalPane.add(panelPontuation, BorderLayout.NORTH);
        principalPane.add(buttonPane,BorderLayout.SOUTH);
        
        tabuleiro.create_tabuleiro(arquivo, unicamp , usuario, corona, atividade, doente, vacina, probabilidade,seconds);
        tabuleiro.start();//inicia o jogo
		
	}

	private void setButtons_player(String player, String player_atividade) {//vicula o usuario aos botoes
    		 	
		controlPane1.removeAll();//removemos os botões dos paineis
		controlPane2.removeAll();

		JButton up=new JButton("up");
    	controlPane1.add(up);
    	
    	JButton left=new JButton("left");
    	controlPane2.add(left);
		
    	JButton down=new JButton("down");
    	controlPane2.add(down);
    	
    	JButton right=new JButton("right");
    	controlPane2.add(right);
		
		usuario = new Usuario(player, player_atividade);//vincula o usuario ao personagem escolhido
		
		usuario.vinculateButtons(up, right, left, down);//criamos referência dos botões no usuário
		
		right.addActionListener(usuario);
		left.addActionListener(usuario);
		up.addActionListener(usuario);
		down.addActionListener(usuario);
	}
	
	public void setPanels_game() {//cria o layout do tabuleiro e o painel de controle (onde haverá os botões de controle do personagem)
		
        tabuleiro= new Tabuleiro(this,10,15);// painel do tabuleiro
        
		panelPontuation = new JPanel(new FlowLayout(FlowLayout.CENTER));//painel da pontuação
		labelPontuation = new JLabel("<html><span style='font-size:20px'>"+"Pontuação: "+ points+"</span></html>");
		panelPontuation.add(labelPontuation);
        
        buttonPane=new JPanel();//painel de controle
        buttonPane.setLayout(new BorderLayout());
        
        controlPane1=new JPanel();//painel de controle
        controlPane1.setLayout(new FlowLayout());
        buttonPane.add(controlPane1,BorderLayout.CENTER);
        
        controlPane2=new JPanel();//painel de controle
        controlPane2.setLayout(new FlowLayout());
        buttonPane.add(controlPane2,BorderLayout.SOUTH);
        
	}
	
	public void set_defined() {//reseta os defineds para outro jogo
		this.player_defined=false;
		this.dificuldade_defined=false;
	}
	
	
	
}

