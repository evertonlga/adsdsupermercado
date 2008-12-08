package modelo;
import util.Seed;
import eduni.simjava.Sim_entity;
import eduni.simjava.Sim_port;
import eduni.simjava.Sim_system;
import eduni.simjava.distributions.Sim_negexp_obj;

public class Source extends Sim_entity {

	private Sim_port saida;
	private Sim_negexp_obj tempo;
	private long tempoMaximo;

	private int qteCliente;

	Source(String name, long tempoMaximo, double tempoInterChegada) {
		super(name);
		qteCliente = 0;
		this.tempoMaximo = tempoMaximo;
		saida = new Sim_port("saidaFonte");
		add_port(saida);
		tempo = new Sim_negexp_obj("Atraso", tempoInterChegada, Seed.getPrime());
	}

	public int getQteCliente() {
		return qteCliente;
	}

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
	
}
