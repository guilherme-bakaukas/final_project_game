import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Janela extends JFrame{
	private static final long serialVersionUID=7686762169698416749L;
	private JPanel controlPane;
	private Tabuleiro tabuleiro;
	public Container principalPane;
	
	public Janela(){
        super();
        setDefaultCloseOperation(EXIT_ON_CLOSE);//garante que a janela só fecha quando o usuário clica no exit
        setSize(300,300);
        
        
        principalPane= getContentPane();//painel principal
        principalPane.setLayout(new BorderLayout());
        
        tabuleiro= new Tabuleiro(this,10,10);// painel das imagens
        principalPane.add(tabuleiro.imagePane,BorderLayout.CENTER);
        
        controlPane=new JPanel();//painel de controle
        controlPane.setLayout(new FlowLayout());
        principalPane.add(controlPane,BorderLayout.SOUTH);
        
        setVisible(true);

    }

    public void setButton(JButton botao) {// inserir botao no painel de controle
    	controlPane.add(botao);
    	SwingUtilities.updateComponentTreeUI(this);
	}

	public void setAmbiente(String arquivo, Unicamp unicamp, Usuario usuario) {
		tabuleiro.create_tabuleiro(arquivo, unicamp, usuario);
		SwingUtilities.updateComponentTreeUI(this);
	}
	
	
	public void start() {
		tabuleiro.start();//inicia o timer e as movimentações automáticas
	}
	
	public void atualizar() {
		SwingUtilities.updateComponentTreeUI(this);
	}
}

