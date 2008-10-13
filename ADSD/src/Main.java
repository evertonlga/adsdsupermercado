import eduni.simjava.Sim_system;


public class Main {
		
	public static void main(String[] args) {
		Sim_system.initialise();
		Source fonte = new Source("fonte");
		Atendente atendente = new Atendente("atendente");
		Caixa caixa = new Caixa("caixa");
		Sim_system.link_ports("fonte", "saidaFonte", "atendente", "chegadaAtendente");
		Sim_system.link_ports("atendente", "saidaAtendente", "caixa", "chegadaCaixa");		
		Sim_system.run();

	}

}
