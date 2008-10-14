import eduni.simjava.Sim_system;


public class Main {
				
	public static void main(String[] args) {
		Sim_system.initialise();
		Source fonte = new Source("fonte", 1000);
		Atendente atendente = new Atendente("atendente", 5, 2.5, 0.9);//TODO 
		Caixa caixa = new Caixa("caixa", 6, 2, 0.95);
		Sim_system.link_ports("fonte", "saidaFonte", "atendente", "chegadaAtendente");
		Sim_system.link_ports("atendente", "saidaAtendente", "caixa", "chegadaCaixa");		
		Sim_system.run();
		System.out.println("Qte tem produto = " + atendente.getTemProduto());
		System.out.println("Qte nao tem produto = " + atendente.getNaoTemProduto());
		System.out.println("Qte vendas efetuadas = " + caixa.getVendasEfetuadas());
		System.out.println("Qte vendas perdidas = " + caixa.getVendasPerdidas());
		if(atendente.getTemProduto() < caixa.getVendasEfetuadas()){
			System.out.println("A CASA CAIU!!!");
		}

	}

}
