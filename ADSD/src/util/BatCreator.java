package util;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Classe reponsável por gerar os arquivos .bat necessários pra execução das simulações
 * @author Everton, Diego, Leonardo
 *
 */
public class BatCreator {
	
	private final static String comando = "java -jar lib/estoque.jar";

	/**
	 * Método responsável por criar os arquivos .bat necessário para simulação
	 * @param tempo
	 *            Tempo de simulacao
	 * @param tempoInterChegada
	 *            Tempo interchegada
	 * @param qteProdutos
	 *            Quantidade de produtos
	 * @param tempoValidade
	 *            Tempo de validade
	 * @param tempoReposicao
	 *            Tempo para reposicao do estoque
	 * @param mediaAt
	 *            Media de atendimento do atendente
	 * @param varAt
	 *            Variancia de atendimento do atendente
	 * @param mediaCx
	 *            Media de atendimento do caixa
	 * @param varCx
	 *            Variancia de atendimento do caixa
	 * @param paciencia
	 *            Tempo máximo de paciencia do cliente
	 * @param minSTR
	 *            Valor mínimo para o parametro a ser variado
	 * @param minSTR
	 *            Valor maximo para o parametro a ser variado
	 * @param parametroVariavel
	 *            parametro a ser variado
	 * @param saltoSTR
	 *            Valor do salto da variacao
	 */
	public static void criarBat(String tempo, String tempoInterChegada,
			String qteProdutos, String tempoValidade, String tempoReposicao,
			String mediaAt, String varAt, String mediaCx, String varCx,
			String paciencia, String minSTR, String maxSTR, String parametroVariavel, String saltoSTR)
	{
		String aux = comando + " " + tempo + " " + tempoInterChegada + " " +
							qteProdutos + " " + tempoValidade + " " + tempoReposicao + " " +
							mediaAt + " " + varAt + " " + mediaCx + " " + varCx + " " +
							paciencia + " " + minSTR + " " + maxSTR + " " + parametroVariavel + " " + saltoSTR;
		String conteudo = "";
		String endln = System.getProperty("line.separator");
		conteudo = aux + " --start" + endln;
		int min = Integer.parseInt(minSTR);
		int max = Integer.parseInt(maxSTR);
		int salto = Integer.parseInt(saltoSTR);
		for(int i = min; i < max; i = i + salto){
			conteudo += aux + endln;
		}
		conteudo += "java -jar lib/grafico.jar " + parametroVariavel;
		
		FileOutputStream out = null;
		try {
			try {
				out = new FileOutputStream("run.bat");				
			}
			finally{
				out.write(conteudo.trim().getBytes());
				out.close();
				Runtime.getRuntime().exec("cmd /c start run.bat");
			}			
		} catch (IOException e) {
			System.err.println(e);
			System.exit(1);
		}

	}

}
