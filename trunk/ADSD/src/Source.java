import eduni.simjava.Sim_entity;
import eduni.simjava.Sim_port;
import eduni.simjava.distributions.Sim_negexp_obj;
import eduni.simjava.distributions.Sim_poisson_obj;


public class Source extends Sim_entity{

      private Sim_port saida;
      private Sim_poisson_obj tempo;
      private int qteClientes;
      private int qteProdutos;

      Source(String name, int qteClientes, int qteProdutos) {    	
        super(name);
        this.qteClientes = qteClientes;
        this.qteProdutos = qteProdutos;
        saida = new Sim_port("saidaFonte");
        add_port(saida);
        tempo = new Sim_poisson_obj("Atraso", 10);        
      }
      
      public void body(){
    	  for (int i = 0; i < qteClientes; i++) {
    		  double num = tempo.sample();
    		  sim_pause(num);
    		  sim_schedule(saida, 0, 0);    		  
    	  }    	  
      }      
      
      
}
