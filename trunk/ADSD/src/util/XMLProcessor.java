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
 * <p>Class that processes a xml file.</p>
 */
public class XMLProcessor {
	
	public XMLProcessor() {}
	
	
    /**
     * Get each node BP and adds in a list
     * @param rootKnot Root Node of the XML file
     * @return list with the elements BP
     */
    @SuppressWarnings("unchecked")
    protected static ArrayList<Element> getListNodes(Element rootKnot) {
        List<Element> elements = rootKnot.getChildren();
        List<Element> bpList = copyList(elements);
       
        return (ArrayList<Element>) bpList;
    }
      
    /**
     * Copy elements of a list in a new
     * @param list of elements
     * @return list containing a copy of the items
     */
    protected static List<Element> copyList(List<Element> list){
        List<Element> copyList = new ArrayList<Element>();
        Iterator<Element> iterator = list.iterator();
        while(iterator.hasNext()){
            copyList.add(iterator.next());
        }
        return copyList;
    }
   
    /**
     * <p>Travel the XML file through of the root node informed</p>
     * @param filename of the XML
     * @return the list of children of each node
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    protected static List<Point> getListPoint(String filename){
     
        // Inicialize o SAX (Xerces) Parser...       
        SAXBuilder builder = new SAXBuilder();   
        Document document = null;
        List<Point> listPoint = new ArrayList<Point>();
        try {
			document = builder.build(new File(filename));
			Element rootKnot = document.getRootElement();    
			ArrayList<Element> filho = getListNodes(rootKnot);        
			for(int i = 0; i < filho.size(); i++){
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
     * Get a point
     * @param netos
     * @return a point
     */
	private static Point getPoint(ArrayList<Element> netos) {
		
		Point point = new Point(0,0);
		int ponto;
		for (int i = 0; i < netos.size(); i++) {
			ponto = Integer.valueOf(netos.get(i).getValue());
			if(i == 0){
				point.setX(ponto);
			}
			else{
				point.setY(ponto);
			}
		}
		return point;
	}
}
