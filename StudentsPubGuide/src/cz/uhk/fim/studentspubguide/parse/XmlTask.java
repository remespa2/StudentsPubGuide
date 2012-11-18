package cz.uhk.fim.studentspubguide.parse;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import android.os.AsyncTask;
// definice obsahuje form�t parametru (vytv��� to pole), progressu a co to vrac�
public class XmlTask extends AsyncTask<String, Void, Document>{
	private Document dom;
	private int latitude, longitude, radius;
	
	public XmlTask(int latitude, int longitude, int radius){
		this.latitude = latitude;
		this.longitude = longitude;
		this.radius = radius;
	}
	
	
	@Override
	protected Document doInBackground(String... params) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {

			//URL url = new URL("http://zkonachodsport.4fan.cz/mapa.xml");
			URL url = new URL(params[0]+this.latitude+"/"+this.longitude+"/"+this.radius);
			//System.out.println(url.toString());
			//Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();

			//parse using builder to get DOM representation of the XML file
			this.dom = db.parse(new InputSource(new InputStreamReader(url.openConnection().getInputStream(), "UTF-8")));
			System.out.println("tady to spadlo");
			return dom;

		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
			return null;
		}catch(SAXException se) {
			se.printStackTrace();
			return null;
		}catch(IOException ioe) {
			ioe.printStackTrace();
			return null;
		}
	}

}
