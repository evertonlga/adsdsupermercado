package util;

/**
 * Classe que representa o objeto que indicar� a sa�da da simula��o
 * @author Everton, Diego, Leonardo
 *
 */
public class Output {
	
	private double parametroVariavel;
	private double percentualPerdasNaoTemProduto;
	private double percentualPerdasPorValidade; 
	private int numeroOutput;
	
	/**
	 * Retorna o parametro vari�vel
	 * @return parametro vari�vel
	 */
	public double getParametroVariavel() {
		return parametroVariavel;
	}

	/**
	 * Seta o parametro vari�vel 
	 * @param parametroVariavel parametro vari�vel
	 */
	public void setParametroVariavel(double parametroVariavel) {
		this.parametroVariavel = parametroVariavel;
	}
	
	/**
	 * Retorna o percentual de perdas por n�o ter produto
	 * @return percentual de perdas por n�o ter produto
	 */
	public double getPercentualPerdasNaoTemProduto() {
		return percentualPerdasNaoTemProduto;
	}

	/**
	 * Seta o percentual de perdas por n�o ter produto
	 * @param qtePerdas percentual de perdas por n�o ter produto
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
	 * Seta o percentual de perdas por n�o ter validade
	 * @param percentualPerdasPorValidade percentual de perdas por n�o ter validade
	 */
	public void setPercentualPerdasPorValidade(double percentualPerdasPorValidade) {
		this.percentualPerdasPorValidade = percentualPerdasPorValidade;
	}

	/**
	 * Retorna o percentual de perdas por n�o ter validade
	 * @return percentual de perdas por n�o ter validade
	 */
	public double getPercentualPerdasPorValidade() {
		return percentualPerdasPorValidade;
	}	
	
}
