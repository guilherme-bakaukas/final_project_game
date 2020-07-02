import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


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
	private Tabuleiro tabuleiro;
	public Container principalPane;
	private JPanel buttonPane;
	private String caixao;
	private String arquivo;
	private String unicamp;
	private Usuario usuario;
	private String corona;
	private String atividade;
	private String doente;
	private String vacina;
	
	public Janela(){
		
        super();
        setDefaultCloseOperation(EXIT_ON_CLOSE);//garante que a janela só fecha quando o usuário clica no exit
        setSize(300,300);
        
        
        principalPane= getContentPane();//painel principal
        principalPane.setLayout(new BorderLayout());
        
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
         
        
        setVisible(true);

    }

    public void setButtonUp(JButton botao) {// inserir botao no painel de controle)
    	controlPane1.add(botao);
    	SwingUtilities.updateComponentTreeUI(this);
	}
    public void setButton(JButton botao) {
    	controlPane2.add(botao);
    	SwingUtilities.updateComponentTreeUI(this);
    }

	public void setAmbiente(String arquivo, String unicamp, Usuario usuario, String corona, String atividade, String doente,String vacina, String caixao) {
		this.arquivo=arquivo;
		this.unicamp=unicamp;
		this.usuario=usuario;
		this.corona=corona;
		this.atividade=atividade;
		this.doente=doente;
		this.vacina=vacina;
		this.caixao=caixao;
		tabuleiro.create_tabuleiro(arquivo, unicamp, usuario, corona, atividade,doente,vacina);
		SwingUtilities.updateComponentTreeUI(this);
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
		
		JButton try_again=new JButton("Try again");
		try_again.addActionListener(this);
		principalPane.add(try_again,BorderLayout.SOUTH);
		
		SwingUtilities.updateComponentTreeUI(this);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {//caso o usuario queira jogar novamente e pressione o botao try again
		// TODO Auto-generated method stub
		tabuleiro=new Tabuleiro(this,10,10);
		this.setAmbiente(arquivo, unicamp, usuario, corona, atividade, doente, vacina, caixao);
		
		principalPane.removeAll();
		principalPane.add(tabuleiro.imagePane,BorderLayout.CENTER);
		principalPane.add(buttonPane,BorderLayout.SOUTH);
		
		this.start();
	}
	
}

