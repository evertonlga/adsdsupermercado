package util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class BatCreator {
	
	private final String comando = "java -jar lib/estoque.jar";

	public static void criarBat(String tempo, String tempoInterChegada,
			String qteProdutos, String tempoValidade, String tempoReposicao,
			String mediaAt, String varAt, String mediaCx, String varCx,
			String paciencia, String max, String min, String parametroVariavel)
	{
		String conteudo = "";

		FileOutputStream out = null;
		try {
			try {
				out = new FileOutputStream("run.bat");
			} catch (FileNotFoundException e) {

			}
			out.write("funcionou".getBytes());
			out.close();
		} catch (IOException e) {
			System.err.println(e);
			System.exit(1);
		}

	}

}
