package util;

import java.util.List;

public class XMLManager {

	private static XMLManager xmlManager = null;	
	
	/**
	 * Construtor privado do XMLManager.
	 */
	private XMLManager() {}
	
	/**
	 * <p>Obtem a instancia �nica para o XMLManager</p>
	 * @return xmlManager a instancia �nica.
	 */
	public static XMLManager getInstance(){
		if(xmlManager == null) {
			xmlManager =  new XMLManager();
		}
		return xmlManager;
	}
	
	/**
	 * <p>Through the knot of xml informed</p>
	 * @param filename Nome do XML 
	 * @return Lista de filhos para cada n�
	 * @throws Exception 
	 */
	public List<Point> getListPointXML(String filename){
		
		List<Point> listPoints = XMLProcessor.getListPoint(filename);
		
		return listPoints;
	}
}
