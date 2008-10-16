import java.util.Scanner;

import eduni.simjava.Sim_system;


public class Main {
				
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println("Digite a quantidade de clientes");
		int qteCliente = input.nextInt();
		System.out.println("Digite a média e a variancia do tempo do atendente");		
		int mediaAt = input.nextInt();
		int varAt = input.nextInt();
		System.out.println("Digite a média e a variancia do tempo do caixa");
		int mediaCx = input.nextInt();
		int varCx = input.nextInt();
		Sim_system.initialise();
		Source fonte = new Source("fonte", qteCliente);
		Atendente atendente = new Atendente("atendente", mediaAt, varAt);//TODO 
		Caixa caixa = new Caixa("caixa", mediaCx, varCx);
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
		Sim_system.set_report_detail(false, false);
		
		Sim_system.generate_graphs("supermercado.sjg");

		Sim_system.run();
	}

}
