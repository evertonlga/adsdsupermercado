package util;

/**
 * Classe que representa o objeto que indicará a saída da simulação
 * @author Everton, Diego, Leonardo
 *
 */
public class Output {
	
	private double parametroVariavel;
	private double percentualPerdasNaoTemProduto;
	private double percentualPerdasPorValidade; 
	private int numeroOutput;
	
	/**
	 * Retorna o parametro variável
	 * @return parametro variável
	 */
	public double getParametroVariavel() {
		return parametroVariavel;
	}

	/**
	 * Seta o parametro variável 
	 * @param parametroVariavel parametro variável
	 */
	public void setParametroVariavel(double parametroVariavel) {
		this.parametroVariavel = parametroVariavel;
	}
	
	/**
	 * Retorna o percentual de perdas por não ter produto
	 * @return percentual de perdas por não ter produto
	 */
	public double getPercentualPerdasNaoTemProduto() {
		return percentualPerdasNaoTemProduto;
	}

	/**
	 * Seta o percentual de perdas por não ter produto
	 * @param qtePerdas percentual de perdas por não ter produto
	 */
	public void setPercentualPerdasNaoTemProduto(double qtePerdas) {
		this.percentualPerdasNaoTemProduto = qtePerdas;
	}

	/**
	 * Retorna o numero do output
	 * @return numero do output
	 */
	public int getNumeroOutput() {
		return numeroOutput;
	}

	/**
	 * Seta o numero do output
	 * @param numeroOutput numero do output
	 */
	public void setNumeroOutput(int numeroOutput) {
		this.numeroOutput = numeroOutput;
	}

	/**
	 * Seta o percentual de perdas por não ter validade
	 * @param percentualPerdasPorValidade percentual de perdas por não ter validade
	 */
	public void setPercentualPerdasPorValidade(double percentualPerdasPorValidade) {
		this.percentualPerdasPorValidade = percentualPerdasPorValidade;
	}

	/**
	 * Retorna o percentual de perdas por não ter validade
	 * @return percentual de perdas por não ter validade
	 */
	public double getPercentualPerdasPorValidade() {
		return percentualPerdasPorValidade;
	}	
	
}
