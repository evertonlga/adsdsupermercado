package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import com.thoughtworks.xstream.XStream;

/**
 * Classe reponsável por salvar os objetos no arquivo XML
 * 
 * @author Everton, Diego, Leonardo
 * 
 */
public class Parser {

	private XStream xstream;
	private static final String XML_IN = Util.INPUT;
	private static final String XML_OUT = Util.OUTPUT;
	private int numeroDoInput;

	/**
	 * Construtor Parcer
	 * 
	 * @param comeco
	 */
	public Parser(boolean comeco) {
		xstream = new XStream();
		xstream.setMode(XStream.NO_REFERENCES);
		numeroDoInput = 0;

		if (comeco) {
			File xmlOut = new File(XML_OUT);
			xmlOut.delete();
		}
	}

	/**
	 * Retorna um input do XML
	 * 
	 * @return Input
	 */
	@SuppressWarnings("unchecked")
	public Input getInput() {

		List<Input> entradas;
		try {
			entradas = (List<Input>) xstream
					.fromXML(new FileInputStream(XML_IN));
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}

		List<Output> saidas;
		try {
			saidas = (List<Output>) xstream
					.fromXML(new FileInputStream(XML_OUT));
		} catch (Exception e) {
			numeroDoInput = 0;
			return entradas.get(numeroDoInput);
		}

		numeroDoInput = saidas.size();
		return entradas.get(numeroDoInput);
	}

	/**
	 * Salva output no XML
	 * 
	 * @param resultado
	 *            output
	 */
	@SuppressWarnings("unchecked")
	public void salvarOutput(Output resultado) {
		List<Output> saidas;
		try {
			saidas = (List<Output>) xstream
					.fromXML(new FileInputStream(XML_OUT));
		} catch (Exception e) {
			saidas = new ArrayList<Output>();
		}
		resultado.setNumeroOutput(saidas.size());
		saidas.add(resultado);

		try {
			xstream.toXML(saidas, new FileOutputStream(XML_OUT));
		} catch (FileNotFoundException ex) {
			throw new RuntimeException(ex.getMessage());
		}
	}

	/**
	 * Retorna o numero de input
	 * 
	 * @return numero do input
	 */
	public int getNumeroDoInput() {
		return numeroDoInput;
	}

}
