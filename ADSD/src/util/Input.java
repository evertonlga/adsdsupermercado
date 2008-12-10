package util;


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
	
	public Input(int tempo, double tempoInterChegada, int qteProdutos,
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

	public long getTempo() {
		return tempo;
	}

	public void setTempo(int tempo) {
		this.tempo = tempo;
	}

	public double getTempoInterChegada() {
		return tempoInterChegada;
	}

	public void setTempoInterChegada(double tempoInterChegada) {
		this.tempoInterChegada = tempoInterChegada;
	}

	public int getQteProdutos() {
		return qteProdutos;
	}

	public void setQteProdutos(int qteProdutos) {
		this.qteProdutos = qteProdutos;
	}

	public double getTempoValidade() {
		return tempoValidade;
	}

	public void setTempoValidade(double tempoValidade) {
		this.tempoValidade = tempoValidade;
	}

	public double getTempoReposicao() {
		return tempoReposicao;
	}

	public void setTempoReposicao(double tempoReposicao) {
		this.tempoReposicao = tempoReposicao;
	}

	public double getMediaAt() {
		return mediaAt;
	}

	public void setMediaAt(double mediaAt) {
		this.mediaAt = mediaAt;
	}

	public double getVarAt() {
		return varAt;
	}

	public void setVarAt(double varAt) {
		this.varAt = varAt;
	}

	public double getMediaCx() {
		return mediaCx;
	}

	public void setMediaCx(double mediaCx) {
		this.mediaCx = mediaCx;
	}

	public double getVarCx() {
		return varCx;
	}

	public void setVarCx(double varCx) {
		this.varCx = varCx;
	}

	public int getPaciencia() {
		return paciencia;
	}

	public void setPaciencia(int paciencia) {
		this.paciencia = paciencia;
	}	

}
