package modelo;

import util.Seed;
import eduni.simjava.Sim_entity;
import eduni.simjava.Sim_event;
import eduni.simjava.Sim_port;
import eduni.simjava.Sim_stat;
import eduni.simjava.Sim_system;
import eduni.simjava.distributions.Sim_normal_obj;

/**
 * Entidade responsável por representar o papel do caixa no supermercado
 * @author Everton, Diego, Leonardo 
 *
 */
public class Caixa extends Sim_entity {

	private Sim_stat stat;
	private Sim_port chegada;
	private Sim_normal_obj delay;
	private int qteVendasEfetuadas;
	private int qteVendasPerdidas;
	private int paciencia;
	private double tempoCaixa;
	final static String STAT_VENDAS_EFETUADAS = "Vendas efetuadas";
	final static String STAT_VENDAS_PERDIDAS = "Vendas perdidas";

	/**
	 * Construtor caixa
	 * @param name Nome para diferenciar os caixas
	 * @param tempoCaixa Tempo que o caixa passa para atender um cliente
	 * @param varTempoCaixa Variancia do tempo que o caixa passa para atender um cliente
	 * @param paciencia Tempo máximo que um cliente espera na fila do caixa
	 */
	public Caixa(String name, double tempoCaixa, double varTempoCaixa,
			int paciencia) {
		super(name);
		this.tempoCaixa = tempoCaixa;
		this.paciencia = paciencia;
		qteVendasEfetuadas = 0;
		qteVendasPerdidas = 0;
		delay = new Sim_normal_obj("tempoAtendente", tempoCaixa, varTempoCaixa,
				Seed.getPrime());
		chegada = new Sim_port("chegadaCaixa");
		add_port(chegada);
		stat = new Sim_stat();
		stat.add_measure(Sim_stat.QUEUE_LENGTH); //tamanho da fila
		stat.add_measure(Sim_stat.ARRIVAL_RATE); //taxa de chegada
		stat.add_measure(Sim_stat.RESIDENCE_TIME); //tempo de permanência
		stat.add_measure(Sim_stat.WAITING_TIME); //tempo de espera
		stat.add_measure(Sim_stat.SERVICE_TIME); //tempo de serviço
		stat.add_measure(Sim_stat.UTILISATION); //taxa de utilização
		stat.add_measure(Sim_stat.THROUGHPUT); //vazão do sistema 
		stat.add_measure(STAT_VENDAS_EFETUADAS, Sim_stat.RATE_BASED);
		stat.add_measure(STAT_VENDAS_PERDIDAS, Sim_stat.RATE_BASED);
		stat.measure_for(new int[] { 0, 1 });

		set_stat(stat);
	}

	/**
	 * Método responsável por executar o comportamento do caixa
	 */
	public void body() {
		Double last = 0.0;
		int i = 0;
		boolean pacienciaPassou = false;
		Double cliente = 0.0;
		while (Sim_system.running()) {
			Sim_event e = new Sim_event();
			while (Sim_system.running()) {
				sim_get_next(e);
				if (!Sim_system.running())
					break;
				cliente = (Double) e.get_data();
				pacienciaPassou = false;
				if (Sim_system.clock() - cliente.doubleValue() < this.paciencia) {
					break;
				} else if (cliente.doubleValue() != last) {
					qteVendasPerdidas++;
					stat.update(STAT_VENDAS_PERDIDAS, Sim_system.sim_clock());
					pacienciaPassou = true;
				}
			}
			last = cliente.doubleValue();
			if (!Sim_system.running())
				break;
			if (!pacienciaPassou) {
				double tempo;
				do {
					tempo = delay.sample();
				} while (tempo < 0);
				i++;
				sim_process(tempo);
				sim_completed(e);
				qteVendasEfetuadas++;
				stat.update(STAT_VENDAS_EFETUADAS, Sim_system.sim_clock());
			}
		}
	}

	/**
	 * Retorna a quantidade de vendas efetuadas pelo caixa 
	 * @return  número de vendas efetuadas pelo caixa
	 */
	public int getVendasEfetuadas() {
		return qteVendasEfetuadas;
	}

	/**
	 * Retorna a quantidade de vendas perdidas pelo caixa 
	 * @return  número de vendas perdidas pelo caixa
	 */
	public int getVendasPerdidas() {
		return qteVendasPerdidas;
	}

	/**
	 * Retorna o tempo de atendimento do caixa
	 * @return tempo de atendimento do caixa
	 */
	public double getTempoCaixa() {
		return tempoCaixa;
	}

}
