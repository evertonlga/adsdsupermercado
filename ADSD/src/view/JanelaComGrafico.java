package view;
import javax.swing.JFrame;

/**
 * Classe responsável por criar o gráfico de anpalise
 * @author Everton, Diego, Leonardo
 *
 */
public class JanelaComGrafico extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private static String xName;
	private static String yName;
	private static String arg;
	
	/**
	 * Construtor
	 */
	public JanelaComGrafico() {
		setSize(400, 200);
		setTitle("Grafico");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		getContentPane().add( GeraGrafico.getGrafico(xName, yName) );
	}

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		arg = args[0];
		if(arg.equals("mediaCaixa")){
			xName = "Tempo de atendimento do caixa";
		}
		else{
			xName  = args[0];
		}
		yName  = "% de perdas";
		JanelaComGrafico janela = new JanelaComGrafico();
		janela.show();
	}

	/**
	 * 
	 * @return Argumento
	 */
	public static String getArg() {
		return arg;
	}

}
