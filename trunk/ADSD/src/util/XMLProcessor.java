package util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

/**
 * <p>Calsse que processa o arquivo XML.</p>
 */
public class XMLProcessor {

	public XMLProcessor() {
	}

	/**
	 * Pega cada nó BP e adiciona a lista
	 * @param rootKnot Nó raiz do XML
	 * @return list lista com os elementos BP
	 */
	@SuppressWarnings("unchecked")
	protected static ArrayList<Element> getListNodes(Element rootKnot) {
		List<Element> elements = rootKnot.getChildren();
		List<Element> bpList = copyList(elements);

		return (ArrayList<Element>) bpList;
	}

	/**
	 * Copia os elementos numa nova lista
	 * @param lista de elementos
	 * @return list das cópias
	 */
	protected static List<Element> copyList(List<Element> list) {
		List<Element> copyList = new ArrayList<Element>();
		Iterator<Element> iterator = list.iterator();
		while (iterator.hasNext()) {
			copyList.add(iterator.next());
		}
		return copyList;
	}

	/**
	 * <p>Percorre o XML a partir do nó raiz especificado</p>
	 * @param Nome do XML
	 * @return Lista de filhos de cada nó
	 * @throws Exception
	 */
	protected static List<Point> getListPoint(String filename) {

		// Inicialize o SAX (Xerces) Parser...       
		SAXBuilder builder = new SAXBuilder();
		Document document = null;
		List<Point> listPoint = new ArrayList<Point>();
		try {
			document = builder.build(new File(filename));
			Element rootKnot = document.getRootElement();
			ArrayList<Element> filho = getListNodes(rootKnot);
			for (int i = 0; i < filho.size(); i++) {
				ArrayList<Element> netos = getListNodes(filho.get(i));
				listPoint.add(getPoint(netos));
			}
		} catch (JDOMException e) {
			System.out.println("Erro durante a criação do Document.");
		} catch (IOException e) {
			System.out.println("Arquivo não encontrado.");
		}
		return listPoint;
	}

	/**
	 * Retorna um ponto
	 * @param netos lista de netos
	 * @return a point ponto
	 */
	private static Point getPoint(ArrayList<Element> netos) {

		Point point = new Point(0, 0);
		double ponto;
		for (int i = 0; i < netos.size(); i++) {
			ponto = Double.valueOf(netos.get(i).getValue());
			if (i == 0) {
				point.setX(ponto);
			} else if (i == 1) {
				point.setY1(ponto);
			} else if (i == 2) {
				point.setY2(ponto);
			}
		}
		return point;
	}
}
