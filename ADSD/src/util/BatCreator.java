package util;

import java.io.FileOutputStream;
import java.io.IOException;

public class BatCreator {
	
	private final static String comando = "java -jar lib/estoque.jar";

	public static void criarBat(String tempo, String tempoInterChegada,
			String qteProdutos, String tempoValidade, String tempoReposicao,
			String mediaAt, String varAt, String mediaCx, String varCx,
			String paciencia, String max, String min, String parametroVariavel)
	{
		String aux = comando + " " + tempo + " " + tempoInterChegada + " " +
							qteProdutos + " " + tempoValidade + " " + tempoReposicao + " " +
							mediaAt + " " + varAt + " " + mediaCx + " " + varCx + " " +
							paciencia + " " + max + " " + min + " " + parametroVariavel;
		String conteudo = "";
		String endln = System.getProperty("line.separator");
		conteudo = aux + " --start" + endln;
		int qte = (Integer.parseInt(max) - Integer.parseInt(min))/10 - 1;
		for (int i = 0; i < qte; i++) {
			conteudo += aux + endln;
		}		
		
		FileOutputStream out = null;
		try {
			try {
				out = new FileOutputStream("run.bat");				
			}
			finally{
				out.write(conteudo.trim().getBytes());
				out.close();
				Runtime.getRuntime().exec("run.bat");
			}			
		} catch (IOException e) {
			System.err.println(e);
			System.exit(1);
		}

	}

}
