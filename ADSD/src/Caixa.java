import eduni.simjava.Sim_entity;
import eduni.simjava.Sim_event;
import eduni.simjava.Sim_port;
import eduni.simjava.Sim_system;
import eduni.simjava.distributions.Sim_normal_obj;


public class Caixa extends Sim_entity{
	
	private Sim_port chegada;
	private Sim_normal_obj delay;	
	private double q;
	private int qteVendasEfetuadas;
	private int qteVendasPerdidas;

	Caixa(String name, double tempoCaixa, double varTempoCaixa, double q) {
      super(name);
      qteVendasEfetuadas = 0;
      qteVendasPerdidas = 0;
      delay = new Sim_normal_obj("tempoAtendente",tempoCaixa, varTempoCaixa);//demora 5 minutos em média      
      this.q = q;
      chegada = new Sim_port("chegadaCaixa");
      add_port(chegada);
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
