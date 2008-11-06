import eduni.simjava.Sim_entity;
import eduni.simjava.Sim_port;
import eduni.simjava.Sim_system;
import eduni.simjava.distributions.Sim_negexp_obj;


public class Source extends Sim_entity{

      private Sim_port saida;
      private Sim_negexp_obj tempo;       
      private long tempoMaximo;

      Source(String name, long tempoMaximo, double tempoInterChegada) {    	  
        super(name);
        this.tempoMaximo = tempoMaximo;
        saida = new Sim_port("saidaFonte");
        add_port(saida);
        tempo = new Sim_negexp_obj("Atraso", tempoInterChegada, Math.round(Math.random() * 1000000000));        
      }
      
      public void body(){
    	  int qteCliente = 0;    	  
    	  int i = 0;
    	  while(Sim_system.clock() <= tempoMaximo){
    		  i++;
    		  double num;
    		  do{
    			  num = tempo.sample();  				
  			  }while(num < 0);
    		  sim_pause(num);
    		  sim_schedule(saida, 0, 0, i);
    		  qteCliente++;
    	  }
    	  System.out.println("cliente = " + qteCliente);
      }      
      
      
}
