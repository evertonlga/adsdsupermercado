package modelo;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import util.Input;
import util.Output;
import util.Parser;
import util.Util;

import com.thoughtworks.xstream.XStream;

import eduni.simjava.Sim_stat;
import eduni.simjava.Sim_system;


public class Main {
	
	private final static String XML_INPUT = "input.xml";
	private static String parametroVariavel;
	

	/*String tempo, String tempoInterChegada,
	String qteProdutos, String tempoValidade, String tempoReposicao,
	String mediaAt, String varAt, String mediaCx, String varCx,
	String paciencia, String min, String max, String parametroVariavel*/
	
	public static void main(String[] args) throws InterruptedException {
		parametroVariavel = args[12];
		if (args.length > 14) {
			gerarInput(args[0], args[1], args[2], args[3], args[4], args[5],
					args[6], args[7], args[8], args[9], args[10], args[11],
					args[12], args[13]);
			run(true);
		} else {
			run(false);
		}

	}	
	
	private static void gerarInput(String tempoSTR, String tempoInterChegadaSTR,
			String qteProdutosSTR, String tempoValidadeSTR, String tempoReposicaoSTR,
			String mediaAtSTR, String varAtSTR, String mediaCxSTR, String varCxSTR,
			String pacienciaSTR, String minSTR, String maxSTR, String parametroVariavel,
			String saltoSTR) {
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
		double min = Double.parseDouble(minSTR);
		double max = Double.parseDouble(maxSTR);
		int salto = Integer.parseInt(saltoSTR);
		List<Input> inputs = new ArrayList<Input>();
		if(parametroVariavel.equals("qteDeProdutos")){
			int minInt = Integer.parseInt(minSTR);
			int maxInt = Integer.parseInt(maxSTR);
			for(int i = minInt; i <= maxInt; i = i + salto){
				inputs.add(new Input(tempo, tempoInterChegada, i,
						tempoValidade, tempoReposicao, mediaAt, varAt, mediaCx,
						varCx, paciencia));
			}
		}
		else if(parametroVariavel.equals("tempoDeInterchegada")){
			for(double i = min; i <= max; i = i + salto){			
				inputs.add(new Input(tempo, i, qteProdutos,
						tempoValidade, tempoReposicao, mediaAt, varAt, mediaCx,
						varCx, paciencia));
			}
		}
		else if(parametroVariavel.equals("tempoDeValidade")){
			for(double i = min; i <= max; i = i + salto){			
				inputs.add(new Input(tempo, tempoInterChegada, qteProdutos,
						i, tempoReposicao, mediaAt, varAt, mediaCx,
						varCx, paciencia));
			}
		}
		else{			
			for(double i = min; i <= max; i = i + salto){			
				inputs.add(new Input(tempo, tempoInterChegada, qteProdutos,
						tempoValidade, tempoReposicao, mediaAt, varAt, i,
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
		Source source = new Source("fonte", i.getTempo(), i.getTempoInterChegada());
		Atendente atendente = new Atendente("atendente", i.getMediaAt(), i.getVarAt(), i.getQteProdutos(), i.getTempoValidade(), i.getTempoReposicao());		
		Caixa caixa = new Caixa("caixa", i.getMediaCx(), i.getVarCx(), i.getPaciencia());
		
		Sim_system.link_ports("fonte", "saidaFonte", "atendente", "chegadaAtendente");
		Sim_system.link_ports("atendente", "saidaAtendente", "caixa", "chegadaCaixa");		
		Sim_system.set_trace_detail(false, false, false);

		Sim_system.set_report_detail(false, false);
//		Sim_system.generate_graphs("supermercado.sjg");
		
//		Sim_system.set_transient_condition(Sim_system.TIME_ELAPSED, 100);
		
		Sim_system.set_termination_condition(Sim_system.INTERVAL_ACCURACY,
				Sim_system.IND_REPLICATIONS, Util.CONFIDENCE_LEVEL, Util.ACCURACY, "atendente",
				Sim_stat.THROUGHPUT);
		
//		Sim_system.set_termination_condition(Sim_system.INTERVAL_ACCURACY,
//    			Sim_system.IND_REPLICATIONS,
//    			Util.CONFIDENCE_LEVEL,
//    			Util.ACCURACY,
//    			"atendente",
//    			Sim_stat.UTILISATION);		
		
		Sim_system.run();		
		
		Output out = new Output();
		
		if(parametroVariavel.equals("qteDeProdutos")){			
			out.setParametroVariavel(atendente.getQteProduto());
		}
		else if(parametroVariavel.equals("tempoDeInterchegada")){
			out.setParametroVariavel(source.getTempoInterChegada());
		}
		else if(parametroVariavel.equals("tempoDeValidade")){
			out.setParametroVariavel(atendente.getTempoValidade());
		}
		else{			
			out.setParametroVariavel(caixa.getTempoCaixa());
		}
		
		if(parametroVariavel.equals("mediaCaixa")){			
			double percent = ((double)caixa.getVendasPerdidas())/atendente.getQteAtendimentosEfetuados();						
			out.setPercentualPerdasNaoTemProduto(percent);
		}
		else{
			out.setPercentualPerdasNaoTemProduto(((double)(atendente.getNaoTemProduto()))/source.getQteCliente());
			out.setPercentualPerdasPorValidade(((double)(atendente.getPerdasPorValidade()))/source.getQteCliente());
		}		
		p.salvarOutput(out);	
		
	}

}
