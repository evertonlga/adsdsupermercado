package util;

import java.util.List;

public class XMLManager {

	private static XMLManager xmlManager = null;	
	
	/**
	 * Constructor private of XMLManager.
	 */
	private XMLManager() {}
	
	/**
	 * <p>Obtain the XMLManager manager unique instance.</p>
	 * @return xmlManager The unique instance.
	 */
	public static XMLManager getInstance(){
		if(xmlManager == null) {
			xmlManager =  new XMLManager();
		}
		return xmlManager;
	}
	
	/**
	 * <p>Through the knot of xml informed</p>
	 * @param filename Name of XML 
	 * @return the list of children of each node
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	protected List<Point> getListPointXML(String filename){
		
		List<Point> listPoints = XMLProcessor.getListPoint(filename);
		
		return listPoints;
	}
}
