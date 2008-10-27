import java.util.Scanner;

import eduni.simjava.Sim_stat;
import eduni.simjava.Sim_system;


public class Main {
	
	private static double paciencia;
	
	public static double getPaciencia(){
		return paciencia;
	}
				
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Digite a quantidade de clientes");
//		int qteCliente = input.nextInt();
		int qteCliente = 1000;
		System.out.println("Digite a quantidade de produtos");
//		int qteProdutos = input.nextInt();
		int qteProdutos = 10;
		System.out.println("Digite a média e a variancia do tempo do atendente");		
//		double mediaAt = input.nextDouble();
		double mediaAt = 50;		
//		double varAt = input.nextDouble();
		double varAt = 10;
		System.out.println("Digite a média e a variancia do tempo do caixa");
//		double mediaCx = input.nextDouble();
		double mediaCx = 50;
//		double varCx = input.nextDouble();
		double varCx = 10;
		System.out.println("Digite a paciencia do cliente");
//		paciencia = input.nextDouble();
		paciencia = 5;
		Sim_system.initialise();
		Source fonte = new Source("fonte", qteCliente, qteProdutos);
		Atendente atendente = new Atendente("atendente", mediaAt, varAt, qteProdutos);//TODO 
		Caixa caixa = new Caixa("caixa", mediaCx, varCx);
		
		Sim_system.link_ports("fonte", "saidaFonte", "atendente", "chegadaAtendente");
		Sim_system.link_ports("atendente", "saidaAtendente", "caixa", "chegadaCaixa");		
//		Sim_system.set_termination_condition(Sim_system.INTERVAL_ACCURACY, 
//				Sim_system.IND_REPLICATIONS, 0.90, 0.02, "caixa", 
//				Sim_stat.WAITING_TIME);
		Sim_system.run();
		System.out.println("Qte tem produto = " + atendente.getTemProduto());
		System.out.println("Qte nao tem produto = " + atendente.getNaoTemProduto());
		System.out.println("Qte vendas efetuadas = " + caixa.getVendasEfetuadas());
		System.out.println("Qte vendas perdidas = " + caixa.getVendasPerdidas());
		if(atendente.getTemProduto() < caixa.getVendasEfetuadas()){
			System.out.println("A CASA CAIU!!!");
		}
		Sim_system.set_report_detail(false, false);
		
		Sim_system.generate_graphs("supermercado.sjg");

		
	}

}
