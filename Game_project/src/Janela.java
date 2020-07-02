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
	
	
	public Janela(){
		
        super();
        setDefaultCloseOperation(EXIT_ON_CLOSE);//garante que a janela só fecha quando o usuário clica no exit
        setSize(300,300);
        
        
        principalPane= getContentPane();//painel principal
        principalPane.setLayout(new BorderLayout());
         
        
        setVisible(true);

    }
	
	public void painel_inicial(String player1_gui, String player2_vitor) {
		
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel label = new JLabel("SELECIONE O SEU PERSONAGEM:");

		panel.add(label);
		principalPane.add(panel, BorderLayout.NORTH);
		
		this.player1_gui=player1_gui;
		this.player2_vitor=player2_vitor;
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
	    painel.setLayout(new FlowLayout());
	    painel.add(player1);
	    painel.add(player2);
	    
	    
	    
	    principalPane.add(painel,BorderLayout.CENTER);
	    
	    
	    
	    SwingUtilities.updateComponentTreeUI(this);
	}

    public void setButtonUp(JButton botao) {// inserir botao no painel de controle)
    	controlPane1.add(botao);
    	SwingUtilities.updateComponentTreeUI(this);
	}
    public void setButton(JButton botao) {
    	controlPane2.add(botao);
    	SwingUtilities.updateComponentTreeUI(this);
    }

	public void setAmbiente(String arquivo, String unicamp, String corona, String atividade, String doente,String vacina, String caixao) {
		this.arquivo=arquivo;
		this.unicamp=unicamp;
		this.corona=corona;
		this.atividade=atividade;
		this.doente=doente;
		this.vacina=vacina;
		this.caixao=caixao;
	}
	
	
	public void start() {
		tabuleiro.start();//inicia o timer e as movimentações automáticas
	}
	
	public void atualizar() {
		SwingUtilities.updateComponentTreeUI(this);
	}
	
	public void stop() {//método chamado na morte do usuario
		
		principalPane.removeAll();
	    ImageIcon imagem = new ImageIcon(caixao);
	    JLabel campoImagem = new JLabel(imagem);
		principalPane.add(campoImagem,BorderLayout.CENTER);
		
		startPane = new JPanel();
		
		try_again=new JButton("Try again");
		try_again.addActionListener(this);
		
		startPane.add(try_again);
		principalPane.add(startPane,BorderLayout.SOUTH);
		
		SwingUtilities.updateComponentTreeUI(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {//caso o usuario queira jogar novamente e pressione o botao try again
		// TODO Auto-generated method stub
		if (e.getSource()==try_again) {
			principalPane.removeAll();
			this.painel_inicial(player1_gui,player2_vitor);//retorna ao painel de escolha do personagem
		}
		else if (e.getSource()==player1) {
			setPanels_game();
			setButtons_player(player1_gui);
			tabuleiro.create_tabuleiro(arquivo, unicamp , usuario, corona, atividade, doente,vacina);
			start();//inicia o jogo
		}
		else if (e.getSource()==player2) {
			setPanels_game();
			setButtons_player(player2_vitor);
			tabuleiro.create_tabuleiro(arquivo, unicamp , usuario, corona, atividade, doente,vacina);
			start();//inicia o jogo
		}
		
		SwingUtilities.updateComponentTreeUI(this);
	
		
	}
	
	private void setButtons_player(String player) {//vicula o usuario aos botoes
    	JButton up=new JButton("up");
    	this.setButtonUp(up);
    	
    	JButton left=new JButton("left");
    	this.setButton(left);
		
    	JButton down=new JButton("down");
    	this.setButton(down);
    	
    	JButton right=new JButton("right");
    	this.setButton(right);
    		 	
		usuario = new Usuario(player);//vincula o usuario a imagem escolhida
		
		usuario.vinculateButtons(up, right, left, down);
		
		right.addActionListener(usuario);
		left.addActionListener(usuario);
		up.addActionListener(usuario);
		down.addActionListener(usuario);
	}
	
	public void setPanels_game() {//cria o layout do tabuleiro e o painel de controle (onde haverá os botões de controle do personagem)
		
		principalPane.removeAll();
		
        tabuleiro= new Tabuleiro(this,10,10);// painel das imagens
        principalPane.add(tabuleiro.imagePane,BorderLayout.CENTER);
        
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

