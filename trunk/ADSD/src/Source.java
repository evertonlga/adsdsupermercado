import eduni.simjava.Sim_entity;
import eduni.simjava.Sim_port;
import eduni.simjava.distributions.Sim_negexp_obj;


public class Source extends Sim_entity{

      private Sim_port saida;
      private Sim_negexp_obj tempo;

      Source(String name) {
        super(name);
        saida = new Sim_port("saidaFonte");
        add_port(saida);
        tempo = new Sim_negexp_obj("Atraso", 10);//TODO O QUE É MEAN?????        
      }
      
      public void body(){
    	  while(true){//TODO AJEITAR DEPOIS!!!
    		  double num = tempo.sample();
    		  System.out.println(num);    		  
    	  }
      }


}
