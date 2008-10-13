import eduni.simjava.Sim_entity;
import eduni.simjava.Sim_port;


public class Atendente extends Sim_entity{
	
	private Sim_port saida;
	private Sim_port chegada;

	Atendente(String name) {
      super(name);
      saida = new Sim_port("saidaAtendente");
      add_port(saida);
      chegada = new Sim_port("chegadaAtendente");
      add_port(chegada);
    }
}
