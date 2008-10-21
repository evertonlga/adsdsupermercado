import eduni.simjava.Sim_entity;
import eduni.simjava.Sim_event;
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
	  stat.add_measure(Sim_stat.QUEUE_LENGTH);
	  stat.add_measure(Sim_stat.THROUGHPUT);
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
//		VerificadorPaciencia verificador = new VerificadorPaciencia(this);
//		new Thread(verificador).start();
		while(Sim_system.running()){//TODO TÁ COM FILA INFINITA!!!
			Sim_event e = new Sim_event();
			double tempo = delay.sample();
			tempo = Math.abs(tempo);
			while(sim_cancel(new Predicado(), e) != 0){
				qteVendasPerdidas++;
			}			
			sim_get_next(e);			
			sim_process(tempo);
			sim_completed(e);
			qteVendasEfetuadas++;			
		}
	}	
}

//class VerificadorPaciencia implements Runnable{
//	
//	private Caixa c;
//	
//	public VerificadorPaciencia(Caixa caixa) {
//		this.
//		c = caixa;
//	}
//	
//	public void run() {
//		Sim_event e = new Sim_event();
//		c.sim_get_next(new Predicado(), e);
//		
//	}
//	
//}