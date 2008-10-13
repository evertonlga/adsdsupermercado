import eduni.simjava.Sim_entity;
import eduni.simjava.Sim_port;


public class Caixa extends Sim_entity{

	private Sim_port chegada;

	Caixa(String name) {
      super(name);
      chegada = new Sim_port("chegadaCaixa");
      add_port(chegada);
    }
    
}
