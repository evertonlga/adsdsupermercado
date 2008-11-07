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
	private int produtosVencidos;
	private double tempoReposicao;
	private double tempoValidade;

	Atendente(String name, double tempoAtendimento, double varTempoAtendimento, int qteProduto, double tempoValidade, double tempoReposicao) {		
      super(name);
      this.qteProduto = qteProduto;
      this.tempoValidade = tempoValidade;
      this.tempoReposicao = tempoReposicao;
      qteTemProduto = 0;
      produtosVencidos = 0;
      qteNaoTemProduto = 0;
      delay = new Sim_normal_obj("tempoAtendente",tempoAtendimento, varTempoAtendimento, Math.round(Math.random() * 1000000000));//demora 5 minutos em mï¿½dia
      saida = new Sim_port("saidaAtendente");
      add_port(saida);
      chegada = new Sim_port("chegadaAtendente");
      add_port(chegada);
      stat = new Sim_stat();
      stat.add_measure(Sim_stat.QUEUE_LENGTH); //tamanho da fila
      stat.add_measure(Sim_stat.ARRIVAL_RATE); //taxa de chegada
      stat.add_measure(Sim_stat.SERVICE_TIME); //tempo de serviço
      stat.add_measure(Sim_stat.UTILISATION); //taxa de utilização
	  stat.add_measure(Sim_stat.WAITING_TIME); //tempo de espera
	  stat.add_measure(Sim_stat.THROUGHPUT); //vazão do sistema
	  stat.measure_for(new int[] { 0, 1 } );
		
	  set_stat(stat);
    }
	
	public int getTemProduto(){
		return qteTemProduto;
	}
	
	public int getNaoTemProduto(){
		return qteNaoTemProduto;
	}
	
	public int getProdutosVencidos(){
		return produtosVencidos;
	}
	
	public void body(){
		
		
		int qtePerdeuValidade = 0;
		int acabouEstoque = 0;
		
		
		
		int qte = qteProduto;
		double dataFabricacao = Sim_system.clock();
		System.out.println("Quantidade Prod.: "+ qte);
		while(Sim_system.running()){//TODO Tï¿½ COM FILA INFINITA!!!
			Sim_event e = new Sim_event();
			double tempo;
			do{
				tempo = delay.sample();
			}while(tempo < 0);
			sim_get_next(e);
			if(e.event_time() < 0)
				break;
			sim_process(tempo);
			sim_completed(e);			
			if(qte > 0 && (Sim_system.clock() - dataFabricacao <= tempoValidade)){
				qte--;
				qteTemProduto++;
				sim_schedule(saida, 0.0, 1, new Double (Sim_system.sim_clock()));
			}else{
				if(Sim_system.clock() - dataFabricacao > tempoValidade){
					qtePerdeuValidade++;
					produtosVencidos += qte;
				}
				qte = qteProduto;//TODO tratar quando nao tem produto ou quando venceu
				if(Sim_system.clock() - dataFabricacao <= tempoValidade){
					qteNaoTemProduto++;
					acabouEstoque++;
				}				
				double antes = Sim_system.clock();
				boolean renovouTempo = false;
				while(Sim_system.clock() - antes <= tempoReposicao){
					sim_get_next(e);
					if(!Sim_system.running()){
						break;
					}
					if(Sim_system.clock() - antes > tempoReposicao && Sim_system.clock() - dataFabricacao <= tempoValidade){
						dataFabricacao = Sim_system.clock();
						renovouTempo = true;
						qte--;
						qteTemProduto++;
						sim_schedule(saida, 0.0, 1, new Double (Sim_system.sim_clock()));
						break;
					}
					if(Sim_system.clock() - dataFabricacao <= tempoValidade){						
						qteNaoTemProduto++;
					}					
				}
				if(!renovouTempo)
					dataFabricacao = Sim_system.clock();
			}
		}
		System.out.println("qte perdeu validade = " + qtePerdeuValidade);
		System.out.println("qte acabou estoque = " + acabouEstoque);
	}
}
