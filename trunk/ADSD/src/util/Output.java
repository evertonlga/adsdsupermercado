package util;

public class Output {
	
	private double parametroVariavel;
	private int qteProdutosPerdidos;	
	private int numeroOutput;
	
	public Output(){
		
	}
	
	public double getParametroVariavel() {
		return parametroVariavel;
	}

	public void setParametroVariavel(double parametroVariavel) {
		this.parametroVariavel = parametroVariavel;
	}
	
	public int getQteProdutosPerdidos() {
		return qteProdutosPerdidos;
	}

	public void setQteNaoTemProduto(int qteProdutosPerdidos) {
		this.qteProdutosPerdidos = qteProdutosPerdidos;
	}

	public int getNumeroOutput() {
		return numeroOutput;
	}

	public void setNumeroOutput(int numeroOutput) {
		this.numeroOutput = numeroOutput;
	}	
	
}
