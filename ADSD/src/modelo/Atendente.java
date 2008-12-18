package modelo;

import util.Seed;
import eduni.simjava.Sim_entity;
import eduni.simjava.Sim_event;
import eduni.simjava.Sim_port;
import eduni.simjava.Sim_stat;
import eduni.simjava.Sim_system;
import eduni.simjava.distributions.Sim_normal_obj;

/**
 * Entidade responsável por representar o papel do antendente no supermercado
 * @author Everton, Diego, Leonardo 
 *
 */

public class Atendente extends Sim_entity {

	private Sim_stat stat;
	private Sim_port saida;
	private Sim_port chegada;
	private Sim_normal_obj delay;
	private int atendimentoEfetuado;
	private int qteNaoTemProduto;
	private int qtePerdasPorValidade;
	private int qteProduto;
	private int qtePerdeuValidade;
	private int acabouEstoque;
	private int produtosVencidos;
	private double tempoReposicao;
	private double tempoValidade;
	final static String STAT_ATENDIMENTOS_EFETUADOS = "Atendimentos efetuados";
	final static String STAT_QUANTIDADE_NAO_TEM_PRODUTO = "Quantidade não tem produto";
	final static String STAT_QUANTIDADE_PRODUTOS_VENCIDOS = "Quantidade produtos vencidos";

	/**
	 * Construtor antendente
	 * @param name Nome para diferenciar os atendentes
	 * @param tempoAtendimento Tempo que o atendente passa para atender um cliente 
	 * @param varTempoAtendimento Variancia do tempo que o atendente passa para atender um cliente
	 * @param qteProduto Quantidade de produtos
	 * @param tempoValidade Tempo de validade do produto
	 * @param tempoReposicao Tempo gasto para repor o estoque
	 */
	public Atendente(String name, double tempoAtendimento,
			double varTempoAtendimento, int qteProduto, double tempoValidade,
			double tempoReposicao) {
		super(name);
		this.qteProduto = qteProduto;
		this.tempoValidade = tempoValidade;
		this.tempoReposicao = tempoReposicao;
		qtePerdeuValidade = 0;
		acabouEstoque = 0;
		qtePerdasPorValidade = 0;
		atendimentoEfetuado = 0;
		produtosVencidos = 0;
		qteNaoTemProduto = 0;
		delay = new Sim_normal_obj("tempoAtendente", tempoAtendimento,
				varTempoAtendimento, Seed.getPrime());//demora 5 minutos em midia
		saida = new Sim_port("saidaAtendente");
		add_port(saida);
		chegada = new Sim_port("chegadaAtendente");
		add_port(chegada);
		stat = new Sim_stat();
		stat.add_measure(Sim_stat.QUEUE_LENGTH); //tamanho da fila
		stat.add_measure(Sim_stat.ARRIVAL_RATE); //taxa de chegada
		stat.add_measure(Sim_stat.SERVICE_TIME); //tempo de servico
		stat.add_measure(Sim_stat.UTILISATION); //taxa de utilizacao
		stat.add_measure(Sim_stat.WAITING_TIME); //tempo de espera
		stat.add_measure(Sim_stat.THROUGHPUT); //vazao do sistema	  
		stat.add_measure(STAT_ATENDIMENTOS_EFETUADOS, Sim_stat.RATE_BASED);
		stat.add_measure(STAT_QUANTIDADE_NAO_TEM_PRODUTO, Sim_stat.RATE_BASED);
		stat
				.add_measure(STAT_QUANTIDADE_PRODUTOS_VENCIDOS,
						Sim_stat.RATE_BASED);

		stat.measure_for(new int[] { 0, 1 });

		set_stat(stat);
	}

	/**
	 * Método responsável por executar o comportamento do atendente
	 */
	public void body() {

		int qte = qteProduto;
		double dataFabricacao = Sim_system.clock();
		while (Sim_system.running()) {
			Sim_event e = new Sim_event();
			double tempo;
			do {
				tempo = delay.sample();
			} while (tempo < 0);
			sim_get_next(e);
			if (e.event_time() < 0)
				break;

			if (qte > 0
					&& (Sim_system.clock() - dataFabricacao <= tempoValidade)) {
				qte--;
				atendimentoEfetuado++;
				sim_process(tempo);
				sim_completed(e);
				stat
						.update(STAT_ATENDIMENTOS_EFETUADOS, Sim_system
								.sim_clock());
				sim_schedule(saida, 0.0, 1, new Double(Sim_system.sim_clock()));
			} else {
				if (Sim_system.clock() - dataFabricacao > tempoValidade) {
					qtePerdasPorValidade++;
					qtePerdeuValidade++;
					stat.update(STAT_QUANTIDADE_PRODUTOS_VENCIDOS, Sim_system
							.sim_clock());
					produtosVencidos += qte;
				}
				qte = qteProduto;
				if (Sim_system.clock() - dataFabricacao <= tempoValidade) {
					qteNaoTemProduto++;
					stat.update(STAT_QUANTIDADE_NAO_TEM_PRODUTO, Sim_system
							.sim_clock());
					acabouEstoque++;
				}
				double antes = Sim_system.clock();
				boolean renovouTempo = false;
				while (Sim_system.clock() - antes <= tempoReposicao) {
					sim_get_next(e);
					if (!Sim_system.running()) {
						break;
					}
					if (Sim_system.clock() - antes > tempoReposicao
							&& Sim_system.clock() - dataFabricacao <= tempoValidade) {
						dataFabricacao = Sim_system.clock();
						renovouTempo = true;
						qte--;
						atendimentoEfetuado++;
						sim_process(tempo);
						sim_completed(e);
						stat.update(STAT_ATENDIMENTOS_EFETUADOS, Sim_system
								.sim_clock());
						sim_schedule(saida, 0.0, 1, new Double(Sim_system
								.sim_clock()));
						break;
					}
					if (Sim_system.clock() - dataFabricacao <= tempoValidade) {
						qteNaoTemProduto++;
						stat.update(STAT_QUANTIDADE_NAO_TEM_PRODUTO, Sim_system
								.sim_clock());
					} else
						qtePerdasPorValidade++;
					stat.update(STAT_QUANTIDADE_PRODUTOS_VENCIDOS, Sim_system
							.sim_clock());
				}
				if (!renovouTempo)
					dataFabricacao = Sim_system.clock();
			}
		}

	}

	/**
	 * Retorna a quantidade de atentendimento realizados
	 * @return número de atentendimento realizados
	 */
	public int getQteAtendimentosEfetuados() {
		return atendimentoEfetuado;
	}

	/**
	 * Retorna a quandidade de produtos que perderam a validade
	 * @return número de produtos que perderam a validade
	 */
	public int getQtePerdeuValidade() {
		return qtePerdeuValidade;
	}

	/**
	 * Retorna a o número de vezes que acabou o estoque
	 * @return número de vezes que acabou o estoque
	 */
	public int getAcabouEstoque() {
		return acabouEstoque;
	}

	/**
	 * Retorna a quandidade de vendas que não foram efetuadas por não haver produtos no estoque
	 * @return quandidade de vendas que não foram efetuadas por não haver produtos no estoque
	 */
	public int getNaoTemProduto() {
		return qteNaoTemProduto;
	}

	/**
	 * Retorna a quantidade de produtos perdidos por perda de validade
	 * @return quantidade de produtos perdidos por perda de validade
	 */
	public int getPerdasPorValidade() {
		return qtePerdasPorValidade;
	}

//	public int getProdutosVencidos() {
//		return produtosVencidos;
//	}

	public int getQteProduto() {
		return qteProduto;
	}

	/**
	 * Retorna o tempo de validade do produto
	 * @return tempo de validade do produto
	 */
	public double getTempoValidade() {
		return tempoValidade;
	}

	/**
	 * Sta a quantidade de produtos
	 * @param qteProduto
	 */
	public void setQteProduto(int qteProduto) {
		this.qteProduto = qteProduto;
	}

}
