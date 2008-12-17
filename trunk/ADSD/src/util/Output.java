package util;

public class Output {
	
	private double parametroVariavel;
	private double percentualPerdasNaoTemProduto;
	private double percentualPerdasPorValidade; 
	private int numeroOutput;
	
	public Output(){
		
	}
	
	public double getParametroVariavel() {
		return parametroVariavel;
	}

	public void setParametroVariavel(double parametroVariavel) {
		this.parametroVariavel = parametroVariavel;
	}
	
	public double getPercentualPerdasNaoTemProduto() {
		return percentualPerdasNaoTemProduto;
	}

	public void setPercentualPerdasNaoTemProduto(double qtePerdas) {
		this.percentualPerdasNaoTemProduto = qtePerdas;
	}

	public int getNumeroOutput() {
		return numeroOutput;
	}

	public void setNumeroOutput(int numeroOutput) {
		this.numeroOutput = numeroOutput;
	}

	public void setPercentualPerdasPorValidade(double percentualPerdasPorValidade) {
		this.percentualPerdasPorValidade = percentualPerdasPorValidade;
	}

	public double getPercentualPerdasPorValidade() {
		return percentualPerdasPorValidade;
	}	
	
}
