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

import cz.uhk.fim.studentspubguide.memory.Memory;

import android.os.AsyncTask;
// definice obsahuje formát parametru (vytváøí to pole), progressu a co to vrací
public class CommentPubTask extends AsyncTask<String, Void, Document>{
	private Document dom;
	
	private String koment, trans;
	
	
	
	
	


	public CommentPubTask(String koment, String trans) {
		super();
		this.koment = koment;
		this.trans = trans;
	}







	@Override
	protected Document doInBackground(String... params) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {

			//URL url = new URL("http://zkonachodsport.4fan.cz/mapa.xml");
			//URL url = new URL(params[0]+this.latitude+"/"+this.longitude+"/"+this.radius);
			URL url = new URL(
					"http://www.zkonachodsport.4fan.cz/StudentsPubGuide/index.php/feed/comment/"
							+  trans  +"/"
							+ Base64.encodeBytes(koment.getBytes()));
			//System.out.println(url.toString());
			//Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();

			//parse using builder to get DOM representation of the XML file
			this.dom = db.parse(new InputSource(new InputStreamReader(url.openConnection().getInputStream(), "UTF-8")));
			//System.out.println("tady to spadlo");
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
