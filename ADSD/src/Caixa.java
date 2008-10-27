import eduni.simjava.Sim_entity;
import eduni.simjava.Sim_event;
import eduni.simjava.Sim_from_p;
import eduni.simjava.Sim_port;
import eduni.simjava.Sim_predicate;
import eduni.simjava.Sim_stat;
import eduni.simjava.Sim_system;
import eduni.simjava.distributions.Sim_normal_obj;


public class Caixa extends Sim_entity{
	
	private Sim_stat stat;
	private Sim_port chegada;
	private Sim_normal_obj delay;	
	private int qteVendasEfetuadas;
	private int qteVendasPerdidas;
	public double tempo_fila = 0.0;

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
		int i = 0;
//		VerificadorPaciencia verificador = new VerificadorPaciencia(this);
//		new Thread(verificador).start();
		while(testaPaciencia() == true){//TODO TÁ COM FILA INFINITA!!!
			Sim_event e = new Sim_event();
			sim_get_next(e);
			this.tempo_fila = (Double)e.get_data();
			double tempo;
			do{
				tempo = delay.sample();
			}while(tempo < 0); 			
			i++; 
			System.out.println(i+". Tempo em fila: "+tempo_fila );
			sim_process(tempo);
			sim_completed(e);
			qteVendasEfetuadas++;				
		}
	}
	
	public boolean testaPaciencia(){
		if((this.tempo_fila/1000) <= Main.getPaciencia())
			return true;
		return false;
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