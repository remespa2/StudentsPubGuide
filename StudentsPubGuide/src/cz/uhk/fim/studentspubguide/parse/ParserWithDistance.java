package cz.uhk.fim.studentspubguide.parse;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class ParserWithDistance {
	//http://www.java-samples.com/showtutorial.php?tutorialid=152
	
	// TATO TRIDA VZNIKLA, PROTOZE SE MI NECHTELO PRETEZOVAT METODY, NEBYLO BY TO CITELNY
	
	private ArrayList<Placemark> myPlacemarks;
	private Document dom;
	private int latitude, longitude, radius;
	
	public ParserWithDistance(int latitude, int longitude, int radius) throws InterruptedException, ExecutionException {
		myPlacemarks = new ArrayList<Placemark>();
		this.latitude = latitude;
		this.longitude = longitude;
		this.radius = radius;
		
		parseXmlFile();
		parseDocument();
	}
	
	
		
	private void parseXmlFile() throws InterruptedException, ExecutionException{
		//
		// TATO TRIDA VZNIKLA, PROTOZE SE MI NECHTELO PRETEZOVAT METODY, NEBYLO BY TO CITELNY
		//
		//get the factory
		
		//toto jsem celý nahradil tøídou async task, takhle se spustí, pøedá parametr a získá výsledek - obzb
		dom = new XmlTask(latitude, longitude, radius).execute("http://www.zkonachodsport.4fan.cz/StudentsPubGuide/index.php/feed/getNear/").get();
		
	}
	
	private void parseDocument(){
		//get the root element
		Element docEle = dom.getDocumentElement();

		//get a nodelist of  elements
		NodeList nl = docEle.getElementsByTagName("Placemark");
		if(nl != null && nl.getLength() > 0) {
			for(int i = 0 ; i < nl.getLength();i++) {

				//get the employee element
				Element el = (Element)nl.item(i);

				//get the Placemark object
				//Employee e = getEmployee(el);
				Placemark  pl = getPlacemark(el);

				//add it to list
				//System.out.println("zprava z parseru"+pl.toString());
				
				myPlacemarks.add(pl);
			}
		}
	}
	private Placemark getPlacemark(Element empEl) {

		//for each <employee> element get text or int values of
		//name ,id, age and name
		
		String id = getTextValue(empEl, "id");
		String name = getTextValue(empEl,"name");
		String description = getTextValue(empEl, "description");
		String latitude = getTextValue(empEl, "latitude");
		String longtitude = getTextValue(empEl, "longtitude");
		String vzdalenost = getTextValue(empEl, "vzdalenost");
		String hodnoceni = getTextValue(empEl, "hodnoceni");
		String pocetHodnoc = getTextValue(empEl, "pocet");
		
		//System.out.println("z parseru"+longtitude +" " +latitude +" "+ name);
		
		
		//String type = empEl.getAttribute("type");

		//Create a new Employee with the value read from the xml nodes
		
		// dodìlávané, opet jsem nedelal druhej konstruktor, abych se v tom ještì nìkdy vyznal.
		Placemark pl = new Placemark(id, latitude, longtitude, name, description, hodnoceni, pocetHodnoc);
		pl.setVzdalenost(vzdalenost);
		
		return pl;
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
	
	public ArrayList<Placemark> getMyPlacmarks(){
	
		return myPlacemarks;
	}
	
	
}
