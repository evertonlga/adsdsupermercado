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
	private static String parametroVariavel;
	
	public static double getPaciencia(){
		return paciencia;
	}

	/*String tempo, String tempoInterChegada,
	String qteProdutos, String tempoValidade, String tempoReposicao,
	String mediaAt, String varAt, String mediaCx, String varCx,
	String paciencia, String min, String max, String parametroVariavel*/
	
	public static void main(String[] args) throws InterruptedException {
		parametroVariavel = args[12];
		if (args.length > 13) {
			gerarInput(args[0], args[1], args[2], args[3], args[4], args[5],
					args[6], args[7], args[8], args[9], args[10], args[11],
					args[12]);
			run(true);
		} else {
			run(false);
		}

	}
	
	/*
	double tempoValidade, double tempoReposicao, double mediaAt,
	double varAt, double mediaCx, double varCx, int paciencia) {
	"quantidade de produtos";
		if(interchegadaCheck.getSelection())
			return "tempo de interchegada";		
		return "tempo de vencimento";	
	*/
	
	private static void gerarInput(String tempoSTR, String tempoInterChegadaSTR,
			String qteProdutosSTR, String tempoValidadeSTR, String tempoReposicaoSTR,
			String mediaAtSTR, String varAtSTR, String mediaCxSTR, String varCxSTR,
			String pacienciaSTR, String minSTR, String maxSTR, String parametroVariavel) {
		long tempo = Long.parseLong(tempoSTR);
		double tempoInterChegada = Double.parseDouble(tempoInterChegadaSTR);
		int qteProdutos = Integer.parseInt(qteProdutosSTR);
		double tempoValidade = Double.parseDouble(tempoValidadeSTR);
		double tempoReposicao = Double.parseDouble(tempoReposicaoSTR);
		double mediaAt = Double.parseDouble(mediaAtSTR);
		double varAt = Double.parseDouble(varAtSTR);
		double mediaCx = Double.parseDouble(mediaCxSTR);
		double varCx = Double.parseDouble(varCxSTR);
		int paciencia = Integer.parseInt(pacienciaSTR);
		int min = Integer.parseInt(minSTR);
		int max = Integer.parseInt(maxSTR);
		List<Input> inputs = new ArrayList<Input>();
		if(parametroVariavel.equals("qteDeProdutos")){
			for(int i = min; i <= max; i = i + 10){
				inputs.add(new Input(tempo, tempoInterChegada, i,
						tempoValidade, tempoReposicao, mediaAt, varAt, mediaCx,
						varCx, paciencia));
			}
		}
		else if(parametroVariavel.equals("tempoDeInterchegada")){
			for(int i = min; i <= max; i = i + 10){			
				inputs.add(new Input(tempo, i, qteProdutos,
						tempoValidade, tempoReposicao, mediaAt, varAt, mediaCx,
						varCx, paciencia));
			}
		}
		else{
			for(int i = min; i <= max; i = i + 10){			
				inputs.add(new Input(tempo, tempoInterChegada, qteProdutos,
						i, tempoReposicao, mediaAt, varAt, mediaCx,
						varCx, paciencia));
			}
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
		
		if(parametroVariavel.equals("qteDeProdutos")){			
			out.setParametroVariavel(atendente.getQteProduto());
		}
		else if(parametroVariavel.equals("tempoDeInterchegada")){
			out.setParametroVariavel(source.getTempoInterChegada());
		}
		else{
			out.setParametroVariavel(atendente.getTempoValidade());
		}
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
