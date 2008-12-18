package util;

/**
 * Classe que representa o objeto que servir� de entrada para a simula��o
 * @author Everton, Diego, Leonardo
 *
 */
public class Input {
	
	private long tempo;
	private	double tempoInterChegada;
	private int qteProdutos;
	private double tempoValidade;
	private double tempoReposicao;
	private double mediaAt;
	private double varAt;
	private double mediaCx;
	private double varCx;
	private int paciencia;	
	
	/**
	 * Construtor Input
	 * @param tempo Tempo de simula��o
	 * @param tempoInterChegada Tempo interchegada
	 * @param qteProdutos Quantidade de produtos
	 * @param tempoValidade Tempo de validade
	 * @param tempoReposicao Tempo de reposi��o
	 * @param mediaAt M�dia de atendimento do atendente
	 * @param varAt Variancia de atendimento do atendente
	 * @param mediaCx M�dia de atendimento do caixa 
	 * @param varCx Variancia de atendimento do caixa
	 * @param paciencia Paciencia m�xima
	 */
	public Input(long tempo, double tempoInterChegada, int qteProdutos,
			double tempoValidade, double tempoReposicao, double mediaAt,
			double varAt, double mediaCx, double varCx, int paciencia) {		
		this.tempo = tempo;
		this.tempoInterChegada = tempoInterChegada;
		this.qteProdutos = qteProdutos;
		this.tempoValidade = tempoValidade;
		this.tempoReposicao = tempoReposicao;
		this.mediaAt = mediaAt;
		this.varAt = varAt;
		this.mediaCx = mediaCx;
		this.varCx = varCx;
		this.paciencia = paciencia;
	}

	/**
	 * Retorna o tempo de simula��o
	 * @return tempo de simula��o
	 */
	public long getTempo() {
		return tempo;
	}

	/**
	 * Seta o tempo de simula��o
	 * @param tempo tempo de simula��o
	 */
	public void setTempo(int tempo) {
		this.tempo = tempo;
	}

	/**
	 * Retorna o tempo de interchegada
	 * @return tempo de interchegada
	 */
	public double getTempoInterChegada() {
		return tempoInterChegada;
	}

	/**
	 * Seta o tempo de interchegada
	 * @param tempoInterChegada tempo de interchegada
	 */
	public void setTempoInterChegada(double tempoInterChegada) {
		this.tempoInterChegada = tempoInterChegada;
	}

	/**
	 * Retorna a quantidade de produtos
	 * @return quantidade de produtos
	 */
	public int getQteProdutos() {
		return qteProdutos;
	}

	/**
	 * Seta a quantidade de produtos
	 * @param qteProdutos quantidade de produtos
	 */
	public void setQteProdutos(int qteProdutos) {
		this.qteProdutos = qteProdutos;
	}

	/**
	 * Retorna o tempo de validade
	 * @return tempo de validade
	 */
	public double getTempoValidade() {
		return tempoValidade;
	}

	/**
	 * Seta o tempo de validade
	 * @param tempoValidade tempo de validade
	 */
	public void setTempoValidade(double tempoValidade) {
		this.tempoValidade = tempoValidade;
	}

	/**
	 * Retorna o tempo de reposi��o
	 * @return tempo de reposi��o
	 */
	public double getTempoReposicao() {
		return tempoReposicao;
	}

	/**
	 * Seta o tempo de reposi��o
	 * @param tempoReposicao tempo de reposi��o
	 */
	public void setTempoReposicao(double tempoReposicao) {
		this.tempoReposicao = tempoReposicao;
	}

	/**
	 * Retorna a m�dia de atendimento do atendente
	 * @return m�dia de atendimento do atendente
	 */
	public double getMediaAt() {
		return mediaAt;
	}

	/**
	 * Seta a m�dia de atendimento do atendente
	 * @param mediaAt m�dia de atendimento do atendente
	 */
	public void setMediaAt(double mediaAt) {
		this.mediaAt = mediaAt;
	}

	/**
	 * Retorna a variancia de atendimento do atendente
	 * @return variancia de atendimento do atendente
	 */
	public double getVarAt() {
		return varAt;
	}

	/**
	 * Seta a variancia de atendimento do atendente
	 * @param varAt variancia de atendimento do atendente
	 */
	public void setVarAt(double varAt) {
		this.varAt = varAt;
	}

	/**
	 * Retorna a m�dia de atendimento do caixa
	 * @return m�dia de atendimento do caixa
	 */
	public double getMediaCx() {
		return mediaCx;
	}

	/**
	 * Seta a m�dia de atendimento do caixa
	 * @param mediaCx m�dia de atendimento do caixa
	 */
	public void setMediaCx(double mediaCx) {
		this.mediaCx = mediaCx;
	}

	/**
	 * Retorna a variancia de atendimento do caixa
	 * @return variancia de atendimento do caixa
	 */
	public double getVarCx() {
		return varCx;
	}

	/**
	 * Seta a variancia de atendimento do caixa
	 * @param varCx variancia de atendimento do caixa
	 */
	public void setVarCx(double varCx) {
		this.varCx = varCx;
	}

	/**
	 * Retorna a paci�ncia do cliente
	 * @return paci�ncia do cliente
	 */
	public int getPaciencia() {
		return paciencia;
	}

	/**
	 * Seta paci�ncia do cliente
	 * @param paciencia paci�ncia do cliente
	 */
	public void setPaciencia(int paciencia) {
		this.paciencia = paciencia;
	}	

}
