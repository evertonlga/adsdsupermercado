import eduni.simjava.Sim_entity;
import eduni.simjava.Sim_event;
import eduni.simjava.Sim_port;
import eduni.simjava.Sim_system;
import eduni.simjava.distributions.Sim_normal_obj;


public class Atendente extends Sim_entity{
	
	private Sim_port saida;
	private Sim_port chegada;
	private Sim_normal_obj delay;
	private double p;
	private int qteTemProduto;
	private int qteNaoTemProduto;

	Atendente(String name, double tempoAtendimento, double varTempoAtendimento, double p) {		
      super(name);
      this.p = p;
      qteTemProduto = 0;
      qteNaoTemProduto = 0;
      delay = new Sim_normal_obj("tempoAtendente",tempoAtendimento, varTempoAtendimento);//demora 5 minutos em média
      saida = new Sim_port("saidaAtendente");
      add_port(saida);
      chegada = new Sim_port("chegadaAtendente");
      add_port(chegada);
    }
	
	public int getTemProduto(){
		return qteTemProduto;
	}
	
	public int getNaoTemProduto(){
		return qteNaoTemProduto;
	}
	
	public void body(){
		while(Sim_system.running()){//TODO TÁ COM FILA INFINITA!!!
			Sim_event e = new Sim_event();
			double tempo = delay.sample();
			sim_get_next(e);
			tempo = Math.abs(tempo);
			sim_process(tempo);
			sim_completed(e);			
			if(p >= Math.random()){
				sim_schedule(saida, 0.0, 1);
				qteTemProduto++;
			}else{
				qteNaoTemProduto++;
				//TODO tratar quando nao tem produto ou quando venceu 
			}
		}
	}
}
