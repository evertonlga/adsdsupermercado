import eduni.simjava.Sim_event;
import eduni.simjava.Sim_predicate;


public class Predicado extends Sim_predicate{

	
	public boolean match(Sim_event evento) {
		Cliente c = (Cliente) evento.get_data();
		return c.pacienciaEsgotada();
	}

}
