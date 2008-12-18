package modelo;

import util.Seed;
import eduni.simjava.Sim_entity;
import eduni.simjava.Sim_port;
import eduni.simjava.Sim_system;
import eduni.simjava.distributions.Sim_negexp_obj;

/**
 * Entidade responsável por criar os dados necessários para a simulação
 * @author Everton, Diego, Leonardo
 *
 */
public class Source extends Sim_entity {

	private Sim_port saida;
	private double tempoInterChegada;
	private Sim_negexp_obj tempo;
	private long tempoMaximo;
	private int qteCliente;

	/**
	 * Construtor Source
	 * @param name Nome para diferenciar os sources
	 * @param tempoMaximo Tempo máximo de simulação
	 * @param tempoInterChegada Tempo de interchegada
	 */
	public Source(String name, long tempoMaximo, double tempoInterChegada) {
		super(name);
		this.tempoInterChegada = tempoInterChegada;
		qteCliente = 0;
		this.tempoMaximo = tempoMaximo;
		saida = new Sim_port("saidaFonte");
		add_port(saida);
		tempo = new Sim_negexp_obj("Atraso", tempoInterChegada, Seed.getPrime());
	}

	/**
	 * Método responsável por executar o comportamento do source
	 */
	public void body() {
		int i = 0;
		while (Sim_system.clock() <= tempoMaximo) {
			i++;
			double num;
			do {
				num = tempo.sample();
			} while (num < 0);
			sim_pause(num);
			sim_schedule(saida, 0, 0, i);
			qteCliente++;
		}

	}

	/**
	 * Retorna a quantidade de clientes
	 * @return quantidade de clientes
	 */
	public int getQteCliente() {
		return qteCliente;
	}

	/**
	 * Retorna o tempo de interchegada
	 * @return tempo de interchegada
	 */
	public double getTempoInterChegada() {
		return tempoInterChegada;
	}

}
