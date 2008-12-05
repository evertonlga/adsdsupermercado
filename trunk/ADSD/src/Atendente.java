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
	private int atendimentoEfetuado;
	private int qteNaoTemProduto;
	private int qtePerdasPorValidade;
	private int qteProduto;
	private int qtePerdeuValidade;
	private int acabouEstoque;
	private int produtosVencidos;
	private double tempoReposicao;
	private double tempoValidade;
	final static String STAT_ATENDIMENTOS_EFETUADOS = "Atendimentos efetuados";
	final static String STAT_QUANTIDADE_NAO_TEM_PRODUTO = "Quantidade nao tem produto";
	final static String STAT_QUANTIDADE_PRODUTOS_VENCIDOS = "Quantidade produtos vencidos";
	
	public Atendente(String name, double tempoAtendimento, double varTempoAtendimento, double tempoValidade, double tempoReposicao) {		
      super(name);      
//      this.qteProduto = qteProduto;
      this.tempoValidade = tempoValidade;
      this.tempoReposicao = tempoReposicao;
      qtePerdeuValidade = 0;
      acabouEstoque = 0;
      qtePerdasPorValidade = 0;
      atendimentoEfetuado = 0;
      produtosVencidos = 0;
      qteNaoTemProduto = 0;
      delay = new Sim_normal_obj("tempoAtendente",tempoAtendimento, varTempoAtendimento, Seed.getPrime());//demora 5 minutos em midia
      saida = new Sim_port("saidaAtendente");
      add_port(saida);
      chegada = new Sim_port("chegadaAtendente");
      add_port(chegada);
      stat = new Sim_stat();
      stat.add_measure(Sim_stat.QUEUE_LENGTH); //tamanho da fila
      stat.add_measure(Sim_stat.ARRIVAL_RATE); //taxa de chegada
      stat.add_measure(Sim_stat.SERVICE_TIME); //tempo de servico
      stat.add_measure(Sim_stat.UTILISATION); //taxa de utilizacao
	  stat.add_measure(Sim_stat.WAITING_TIME); //tempo de espera
	  stat.add_measure(Sim_stat.THROUGHPUT); //vazao do sistema	  
	  stat.add_measure(STAT_ATENDIMENTOS_EFETUADOS,Sim_stat.RATE_BASED);
	  stat.add_measure(STAT_QUANTIDADE_NAO_TEM_PRODUTO,Sim_stat.RATE_BASED);
	  stat.add_measure(STAT_QUANTIDADE_PRODUTOS_VENCIDOS,Sim_stat.RATE_BASED);
	  
	  stat.measure_for(new int[] { 0, 1 } );
		
	  set_stat(stat);
	  System.out.println("entrou no construtor");	  
    }
	
	private void getQteProduto() {
		// Lendo do arquivo  
//		arquivo = new File("arquivo.txt");  
//		FileInputStream fis = new FileInputStream(arquivo);  
//		
//		int ln;  
//		while ( (ln = fis.read()) != -1 ) {  
//		System.out.print( (char)ln );  
//		}  
//		
//		fis.close();
		this.qteProduto = Main.getQteProduto();
		
	}
	
	
	public int getQteAtendimentosEfetuados(){
		return atendimentoEfetuado;
	}
	
	public int getQtePerdeuValidade() {
		return qtePerdeuValidade;
	}


	public int getAcabouEstoque() {
		return acabouEstoque;
	}


	public int getNaoTemProduto(){
		return qteNaoTemProduto;
	}
	
	public int getPerdasPorValidade(){
		return qtePerdasPorValidade;
	}
	
	public int getProdutosVencidos(){
		return produtosVencidos;
	}
	
	public void body(){
		getQteProduto();
		
		System.out.println("qteProduto= " + qteProduto);		
		
		int qte = qteProduto;
//		System.out.println(Sim_system.clock());
		double dataFabricacao = Sim_system.clock();
		System.out.println("Quantidade Prod.: "+ qteProduto);		
		while(Sim_system.running()){//TODO TA COM FILA INFINITA!!!			
			Sim_event e = new Sim_event();
			double tempo;
			do{
				tempo = delay.sample();
			}while(tempo < 0);
			sim_get_next(e);
			if(e.event_time() < 0)
				break;
			
			if(qte > 0 && (Sim_system.clock() - dataFabricacao <= tempoValidade)){
				qte--;
				atendimentoEfetuado++;
				sim_process(tempo);
				sim_completed(e);
				stat.update(STAT_ATENDIMENTOS_EFETUADOS, Sim_system.sim_clock());
				sim_schedule(saida, 0.0, 1, new Double (Sim_system.sim_clock()));
			}else{
				if(Sim_system.clock() - dataFabricacao > tempoValidade){
					qtePerdasPorValidade++;
					qtePerdeuValidade++;
					stat.update(STAT_QUANTIDADE_PRODUTOS_VENCIDOS, Sim_system.sim_clock());
					produtosVencidos += qte;
				}
				qte = qteProduto;//TODO tratar quando nao tem produto ou quando venceu
				if(Sim_system.clock() - dataFabricacao <= tempoValidade){
					qteNaoTemProduto++;
					stat.update(STAT_QUANTIDADE_NAO_TEM_PRODUTO, Sim_system.sim_clock());
					acabouEstoque++;
				}				
				double antes = Sim_system.clock();
				boolean renovouTempo = false;
//				if(Sim_system.clock() - dataFabricacao > tempoValidade)//venceu produtos
//					reporEstoqueVenceuProdutos();
//				else//acabou estoque
//					reporEstoqueAcabouEstoque();
				
				while(Sim_system.clock() - antes <= tempoReposicao){
					sim_get_next(e);
					if(!Sim_system.running()){
						break;
					}
					if(Sim_system.clock() - antes > tempoReposicao && Sim_system.clock() - dataFabricacao <= tempoValidade){
						dataFabricacao = Sim_system.clock();
						renovouTempo = true;
						qte--;
						atendimentoEfetuado++;
						sim_process(tempo);
						sim_completed(e);
						stat.update(STAT_ATENDIMENTOS_EFETUADOS, Sim_system.sim_clock());
						sim_schedule(saida, 0.0, 1, new Double (Sim_system.sim_clock()));
						break;
					}
					if(Sim_system.clock() - dataFabricacao <= tempoValidade){
						qteNaoTemProduto++;
						stat.update(STAT_QUANTIDADE_NAO_TEM_PRODUTO, Sim_system.sim_clock());
					}
					else
						qtePerdasPorValidade++;
						stat.update(STAT_QUANTIDADE_PRODUTOS_VENCIDOS, Sim_system.sim_clock());
				}
				if(!renovouTempo)
					dataFabricacao = Sim_system.clock();
			}			
		}
		
	}
	
	
}
