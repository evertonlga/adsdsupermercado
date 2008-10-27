import eduni.simjava.Sim_entity;
import eduni.simjava.Sim_event;
import eduni.simjava.Sim_port;
import eduni.simjava.Sim_stat;
import eduni.simjava.Sim_system;
import eduni.simjava.distributions.Sim_normal_obj;


public class Atendente extends Sim_entity{
	
	private Sim_stat stat;
	private Sim_port saida;
	private Sim_port chegada;
	private Sim_normal_obj delay;	
	private int qteTemProduto;
	private int qteNaoTemProduto;
	private int qteProduto;

	Atendente(String name, double tempoAtendimento, double varTempoAtendimento, int qteProduto) {		
      super(name);
      this.qteProduto = qteProduto;
      qteTemProduto = 0;
      qteNaoTemProduto = 0;
      delay = new Sim_normal_obj("tempoAtendente",tempoAtendimento, varTempoAtendimento);//demora 5 minutos em média
      saida = new Sim_port("saidaAtendente");
      add_port(saida);
      chegada = new Sim_port("chegadaAtendente");
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
	
	public int getTemProduto(){
		return qteTemProduto;
	}
	
	public int getNaoTemProduto(){
		return qteNaoTemProduto;
	}
	
	public void body(){
		int qte = qteProduto;
		System.out.println("Quantidade Prod.: "+ qte);
		while(Sim_system.running()){//TODO TÁ COM FILA INFINITA!!!
			Sim_event e = new Sim_event();
			double tempo;
			do{
				tempo = delay.sample();
			}while(tempo < 0);
			sim_get_next(e);
			sim_process(tempo);
			sim_completed(e);
			qte--;
			if(qte > 0){
				qteTemProduto++;
				sim_schedule(saida, 0.0, 1, new Double (Sim_system.sim_clock()));
			}else{
				qteNaoTemProduto++;
				qte = qteProduto;//TODO tratar quando nao tem produto ou quando venceu 
			}
		}
	}
}
