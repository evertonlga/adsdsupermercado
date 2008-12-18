package util;

/**
 * Classe que retorna um numero primo que servir� como semente para os dados de simula��o
 * @author Everton, Diego, Leonardo
 *
 */
public class Seed {
	
	private final static long primesList[] = {43, 71, 197, 463, 547, 953, 1471, 1933, 2647, 2843, 3697, 4663, 5741, 8233, 9283, 10781, 11173, 12391, 14561, 18397, 20483, 29303, 29947, 34651, 37493, 41203, 46691, 50821, 54251, 56897, 57793, 65213, 68111, 72073, 76147, 84631, 89041, 93563};
	
	/**
	 * Retorna o n�mero primo
	 * @return n�mero primo
	 */
	public static long getPrime(){
		int index;
		do{
			index = (int)Math.round(Math.random()*100);
		}while(index >= 38);
		return primesList[index];
	}

}
