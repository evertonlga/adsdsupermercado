package modelo;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import util.Input;
import util.Output;
import util.Parser;

import com.thoughtworks.xstream.XStream;

import eduni.simjava.Sim_system;


public class Main {
	
	private static double paciencia;
	private final static String XML_INPUT = "input.xml";
	
	public static double getPaciencia(){
		return paciencia;
	}
				
	public static void main(String[] args) {		
		if(args.length > 2){
			gerarInput(args[0], args[1]);
			run(true);
		}
		else{
			run(false);
		}	
		
	}
	
	private static void gerarInput(String minSTR, String maxSTR) {
		int min = Integer.parseInt(minSTR);
		int max = Integer.parseInt(maxSTR);
		List<Input> inputs = new ArrayList<Input>();
		for(int i = min; i <= max; i = i + 10){
			System.out.println();
			inputs.add(new Input(10000, 5, i, 500, 50, 5, 1, 5, 1, 10));
		}
		salvarEmXML(inputs);
		
	}
	
	private static void salvarEmXML(List<Input> inputs) {
		XStream xstream = new XStream();
		xstream.setMode(XStream.NO_REFERENCES);
		try {
			xstream.toXML(inputs, new FileOutputStream(XML_INPUT));
		} catch (FileNotFoundException ex) {
			throw new RuntimeException(ex.getMessage());
		}	
		
	}
	
	private static void run(boolean comeco) {
		Parser p = new Parser(comeco);
		Input i = p.getInput();
		
		Sim_system.initialise();
		@SuppressWarnings("unused")
		Source source = new Source("fonte", i.getTempo(), i.getTempoInterChegada());
		Atendente atendente = new Atendente("atendente", i.getMediaAt(), i.getVarAt(), i.getQteProdutos(), i.getTempoValidade(), i.getTempoReposicao()); 
		@SuppressWarnings("unused")
		Caixa caixa = new Caixa("caixa", i.getMediaCx(), i.getVarCx());
		
		Sim_system.link_ports("fonte", "saidaFonte", "atendente", "chegadaAtendente");
		Sim_system.link_ports("atendente", "saidaAtendente", "caixa", "chegadaCaixa");		
		Sim_system.set_trace_detail(false, false, false);
//		Sim_system.set_transient_condition(Sim_system.TIME_ELAPSED, 100);
		
		
		/*
		 * Deixar o intervalo de confianca em no maximo 10%
		 * Nivel de Confianca de 90%
		 *
		 *     type - The termination condition type. This must be set to INTERVAL_ACCURACY.
		 *     output_analysis_type - The output analysis method to be used as a variance reduction technique
		 *     level - The confidence level for which the confidence interval will be calculated
		 *     accuracy - The accuracy that is required to satisfy the termination condition
		 *     entity - The name of the entity that contains the measure upon which the termination condition is based
		 *     measure - The name of the custom measure upon which the termination condition is based
		 */
//		Sim_system.set_termination_condition(Sim_system.INTERVAL_ACCURACY, 
//				Sim_system.IND_REPLICATIONS, 0.90, 0.02, "caixa", 
//				Sim_stat.THROUGHPUT);
		
		Sim_system.set_report_detail(false, false);
		Sim_system.generate_graphs("supermercado.sjg");
		
		Sim_system.run();
		Output out = new Output();
		out.setQteNaoTemProduto(atendente.getNaoTemProduto() + atendente.getPerdasPorValidade());
		p.salvarOutput(out);
//		System.out.println();
//		System.out.println("===> Source <===");
//		System.out.println("cliente = " + source.getQteCliente());
//		System.out.println();
//		System.out.println("===> Atendente <===");
//		System.out.println("Qte atendimentos efetuados = " + atendente.getQteAtendimentosEfetuados());
//		System.out.println("Qte atendimentos nao realizados por nao ter produto = " + atendente.getNaoTemProduto());
//		System.out.println("Qte atendimentos nao realizados por produto vencido = " + atendente.getPerdasPorValidade());
//		System.out.println("Qte produtos vencidos = " + atendente.getProdutosVencidos());
//		System.out.println("qte perdeu validade = " + atendente.getQtePerdeuValidade());
//		System.out.println("qte acabou estoque = " + atendente.getAcabouEstoque());
//		System.out.println();
//		System.out.println("===> Caixa <===");
//		System.out.println("Qte vendas efetuadas = " + caixa.getVendasEfetuadas());
//		System.out.println("Qte vendas perdidas = " + caixa.getVendasPerdidas());		
//		if(atendente.getQteAtendimentosEfetuados() < caixa.getVendasEfetuadas()){
//			System.out.println("A CASA CAIU!!!");
//		}
		
	}

}
