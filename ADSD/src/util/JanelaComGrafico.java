package util;
import javax.swing.JFrame;

public class JanelaComGrafico extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private static String xName;
	private static String yName;
	
	public JanelaComGrafico() {
		setSize(400, 200);
		setTitle("Grafico");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		getContentPane().add( GeraGrafico.getGrafico(xName, yName) );
	}

	public static void main(String[] args) {
		xName  = args[0];
		yName  = "Perdas";
		JanelaComGrafico janela = new JanelaComGrafico();
		janela.show();
	}

}
