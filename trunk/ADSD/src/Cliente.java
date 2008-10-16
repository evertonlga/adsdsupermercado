import eduni.simjava.Sim_entity;


public class Cliente extends Sim_entity{
	
	private double chegadaAoCaixa;
	
	public Cliente(String name, double chegadaAoCaixa){
		super(name);
		this.chegadaAoCaixa = chegadaAoCaixa;
	}
	
	public double getTimeStamp(){
		return chegadaAoCaixa;
	}

}
