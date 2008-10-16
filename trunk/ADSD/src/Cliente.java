import eduni.simjava.Sim_entity;
import eduni.simjava.Sim_system;


public class Cliente extends Sim_entity{
	
	private double chegadaAoCaixa;
	private double paciencia;
	
	public Cliente(String name, double chegadaAoCaixa, double paciencia){
		super(name);
		this.chegadaAoCaixa = chegadaAoCaixa;
		this.paciencia = paciencia;
	}
	
	public void body(){
//		System.out.println("chegadaAocaixa =" + chegadaAoCaixa);
	}
	
	public double getTimeStamp(){
		return chegadaAoCaixa;
	}
	
	public double getPaciencia(){
		return paciencia;
	}
	
	public boolean pacienciaEsgotada(){		
		if(Sim_system.sim_clock() - chegadaAoCaixa > paciencia){
			return true;
		}
		return false;
	}

}
