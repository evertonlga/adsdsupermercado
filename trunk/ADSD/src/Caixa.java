import eduni.simjava.Sim_entity;
import eduni.simjava.Sim_event;
import eduni.simjava.Sim_from_p;
import eduni.simjava.Sim_port;
import eduni.simjava.Sim_stat;
import eduni.simjava.Sim_system;
import eduni.simjava.distributions.Sim_normal_obj;


public class Caixa extends Sim_entity{
	
	private Sim_stat stat;
	private Sim_port chegada;
	private Sim_normal_obj delay;	
	private int qteVendasEfetuadas;
	private int qteVendasPerdidas;

	Caixa(String name, double tempoCaixa, double varTempoCaixa) {
      super(name);
      qteVendasEfetuadas = 0;
      qteVendasPerdidas = 0;
      delay = new Sim_normal_obj("tempoAtendente",tempoCaixa, varTempoCaixa);//demora 5 minutos em média      
      chegada = new Sim_port("chegadaCaixa");
      add_port(chegada);
      stat = new Sim_stat();
      stat.add_measure(Sim_stat.UTILISATION); //taxa de utilização
	  stat.add_measure(Sim_stat.SERVICE_TIME); //tempo de serviço
	  stat.add_measure(Sim_stat.WAITING_TIME); //tempo de espera
	  stat.measure_for(new int[] { 0, 1 } );
		
	  set_stat(stat);
    }
	
	public int getVendasEfetuadas(){
		return qteVendasEfetuadas;
	}
	
	public int getVendasPerdidas(){
		return qteVendasPerdidas;
	}
    
	public void body(){
		while(Sim_system.running()){//TODO TÁ COM FILA INFINITA!!!
			Sim_event e = new Sim_event();
			double tempo = delay.sample();
			tempo = Math.abs(tempo);
			sim_get_next(new Sim_from_p(Sim_system.get_entity_id("Cliente")), e);//TODO continuar			
			
			sim_get_next(e);
			
			if(q >= Math.random()){
				sim_process(tempo);
				sim_completed(e);
				qteVendasEfetuadas++;
			}else{
				qteVendasPerdidas++;
			}
		}
	}
	
}
