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
		System.out.println("Digite o tempo de simulacao");
//		int qteCliente = input.nextInt();
		long tempo = 10000;
		System.out.println("Digite o tempo medio de inter-chegada de clientes");
		double tempoInterChegada = 5;
		System.out.println("Digite a quantidade de produtos");
//		int qteProdutos = input.nextInt();
		int qteProdutos = 100;
		System.out.println("Digite o tempo de validade do estoque");
		double tempoValidade = 500;
		System.out.println("Digite o tempo de reposicao do estoque");
		double tempoReposicao = 50;
		System.out.println("Digite a media e a variancia do tempo do atendente");		
//		double mediaAt = input.nextDouble();
		double mediaAt = 5;
//		double varAt = input.nextDouble();
		double varAt = 3;
		System.out.println("Digite a media e a variancia do tempo do caixa");
//		double mediaCx = input.nextDouble();
		double mediaCx = 5;
//		double varCx = input.nextDouble();
		double varCx = 3;
		System.out.println("Digite a paciencia do cliente");
//		paciencia = input.nextDouble();
		paciencia = 10;
		Sim_system.initialise();
		Source fonte = new Source("fonte", tempo, tempoInterChegada);
		Atendente atendente = new Atendente("atendente", mediaAt, varAt, qteProdutos, tempoValidade, tempoReposicao);//TODO 
		Caixa caixa = new Caixa("caixa", mediaCx, varCx);
		
		Sim_system.link_ports("fonte", "saidaFonte", "atendente", "chegadaAtendente");
		Sim_system.link_ports("atendente", "saidaAtendente", "caixa", "chegadaCaixa");		
		
		Sim_system.set_transient_condition(Sim_system.TIME_ELAPSED, 100);
		
		
		/*
		 * Deixar o intervalo de confiança em no máximo 10%
		 * Nível de Confiança de 90%
		 *
		 *     type - The termination condition type. This must be set to INTERVAL_ACCURACY.
		 *     output_analysis_type - The output analysis method to be used as a variance reduction technique
		 *     level - The confidence level for which the confidence interval will be calculated
		 *     accuracy - The accuracy that is required to satisfy the termination condition
		 *     entity - The name of the entity that contains the measure upon which the termination condition is based
		 *     measure - The name of the custom measure upon which the termination condition is based
		 */
		Sim_system.set_termination_condition(Sim_system.INTERVAL_ACCURACY, 
				Sim_system.IND_REPLICATIONS, 0.90, 0.02, "caixa", 
				Sim_stat.THROUGHPUT);
		
		Sim_system.run();
		System.out.println("Qte tem produto = " + atendente.getTemProduto());
		System.out.println("Qte nao tem produto = " + atendente.getNaoTemProduto());
		System.out.println("Qte produtos vencidos = " + atendente.getProdutosVencidos());
		System.out.println("Qte vendas efetuadas = " + caixa.getVendasEfetuadas());
		System.out.println("Qte vendas perdidas = " + caixa.getVendasPerdidas());		
		if(atendente.getTemProduto() < caixa.getVendasEfetuadas()){
			System.out.println("A CASA CAIU!!!");
		}
		Sim_system.set_report_detail(false, false);
		
		Sim_system.generate_graphs("supermercado.sjg");

		
	}

}
