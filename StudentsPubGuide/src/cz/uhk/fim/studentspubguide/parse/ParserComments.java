package cz.uhk.fim.studentspubguide.parse;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import cz.uhk.fim.studentspubguide.memory.Memory;

public class ParserComments {
	//http://www.java-samples.com/showtutorial.php?tutorialid=152
	
	// TATO TRIDA VZNIKLA, PROTOZE SE MI NECHTELO PRETEZOVAT METODY, NEBYLO BY TO CITELNY
	
	private ArrayList<Comment> myComments;
	private Document dom;
	private int idPubu;
	
	public ParserComments(int idPubu) throws InterruptedException, ExecutionException {
		myComments = new ArrayList<Comment>();
		this.idPubu = idPubu;
		
		parseXmlFile();
		parseDocument();
	}
	
	
		
	private void parseXmlFile() throws InterruptedException, ExecutionException{
		//
		// TATO TRIDA VZNIKLA, PROTOZE SE MI NECHTELO PRETEZOVAT METODY, NEBYLO BY TO CITELNY
		//
		//get the factory
		
		//toto jsem celý nahradil tøídou async task, takhle se spustí, pøedá parametr a získá výsledek - obzb
		dom = new XmlTaskComment(idPubu).execute("http://www.zkonachodsport.4fan.cz/StudentsPubGuide/index.php/feed/getComments/").get();
		
	}
	
	private void parseDocument(){
		//get the root element
		Element docEle = dom.getDocumentElement();

		//get a nodelist of  elements
		NodeList nl = docEle.getElementsByTagName("Comment");
		if(nl != null && nl.getLength() > 0) {
			for(int i = 0 ; i < nl.getLength();i++) {

				//get the employee element
				Element el = (Element)nl.item(i);

				//get the Placemark object
				//Employee e = getEmployee(el);
				Comment  co = getComment(el);

				//add it to list
				//System.out.println("zprava z parseru"+pl.toString());
				
				//myPlacemarks.add(pl);
				Memory.getComments().add(co);
			}
		}
	}
	private Comment getComment(Element empEl) {

		//for each <employee> element get text or int values of
		//name ,id, age and name
		
		String id = getTextValue(empEl, "id");
		String text = getTextValue(empEl,"text");
		String idPubu = getTextValue(empEl, "id_pubu");
		
		//System.out.println("z parseru"+longtitude +" " +latitude +" "+ name);
		
		
		//String type = empEl.getAttribute("type");

		//Create a new Employee with the value read from the xml nodes
		
		// dodìlávané, opet jsem nedelal druhej konstruktor, abych se v tom ještì nìkdy vyznal.
		//Placemark pl = new Placemark(id, latitude, longtitude, name, description, hodnoceni, pocetHodnoc);
		Comment co = new Comment(id,text,idPubu);
		//pl.setVzdalenost(vzdalenost);
		
		return co;
	}


	/**
	 * I take a xml element and the tag name, look for the tag and get
	 * the text content
	 * i.e for <employee><name>John</name></employee> xml snippet if
	 * the Element points to employee node and tagName is 'name' I will return John
	 */
	private String getTextValue(Element ele, String tagName) {
		String textVal = null;
		NodeList nl = ele.getElementsByTagName(tagName);
		if(nl != null && nl.getLength() > 0) {
			Element el = (Element)nl.item(0);
			textVal = el.getFirstChild().getNodeValue();
		}

		return textVal;
	}


	/**
	 * Calls getTextValue and returns a int value
	 */
	private int getIntValue(Element ele, String tagName) {
		//in production application you would catch the exception
		return Integer.parseInt(getTextValue(ele,tagName));
	}
	
	public ArrayList<Comment> getMyComments(){
	
		return myComments;
	}
	
	
}
