package cz.uhk.fim.studentspubguide;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import cz.uhk.fim.studentspubguide.parse.Base64;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

public class AddPubActivity extends Activity {
	private int latitude, longitude;
	private EditText nazev, popis, komentar;
	private Button add;
	private RatingBar rate;
	private String nazevS,popisS,komentarS,rateS;
	

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pub);
        
        try {
			findPosition();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        nazev = (EditText) findViewById(R.id.editText0);
        //nazevS = nazev.getText().toString();
        popis = (EditText) findViewById(R.id.editText1);
        //popisS = popis.toString();
        komentar = (EditText) findViewById(R.id.editText2);
        //komentarS = komentar.toString();
        rate = (RatingBar) findViewById(R.id.ratingBar1);
        //rateS = rate.toString();
        add = (Button) findViewById(R.id.buttonADD);
        add.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				nazevS = nazev.getText().toString();
				popisS = popis.getText().toString();
				rateS = Float.toString(rate.getRating());
				komentarS = komentar.getText().toString();
				//printOut();
				try {
					
					postData();
					finish();
					//printOut();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		});
        
        
        
    }

    final private void printOut(){
    	System.out.println("kontrola dat");
    	System.out.println(nazevS);
		System.out.println(popis.getText());
		System.out.println(komentar.getText());
		System.out.println(rate.getRating());
    }
    
    public void postData() throws IOException {
        
    	
    	   try {
    		   if(latitude == 0 || longitude == 0){
    			   return;
    		   }
    		   if(nazevS.equals("") || popisS.equals("") ){
    			   return;
    		   }
    		   URL url = new URL("http://www.zkonachodsport.4fan.cz/StudentsPubGuide/index.php/feed/pridej/"
    				   +Base64.encodeBytes(nazevS.getBytes())+"/"
    				   +Base64.encodeBytes(popisS.getBytes())+"/"
    				   +Base64.encodeBytes(komentarS.getBytes())+"/"
    				   +rateS+"/"
    				   +latitude+"/"
    				   +longitude+"/"
    				   );
    		 System.out.println(url);
        	 HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
    	     InputStream in = new BufferedInputStream(urlConnection.getInputStream());
    	     urlConnection.disconnect();
    	     
    	    } catch (Exception e) {
    	     e.printStackTrace();
    	   }
    	
    	
    }
    
    
    
    private void findPosition() throws Exception {
    	//http://developer.android.com/guide/topics/location/strategies.html
    	// Acquire a reference to the system Location Manager
    	LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

    	// Define a listener that responds to location updates
    	LocationListener locationListener = new LocationListener() {
    	    public void onLocationChanged(Location location) {
    	      // Called when a new location is found by the network location provider.
    	      
    	    }

    	    public void onStatusChanged(String provider, int status, Bundle extras) {}

    	    public void onProviderEnabled(String provider) {}

    	    public void onProviderDisabled(String provider) {}
    	  };

    	// Register the listener with the Location Manager to receive location updates
    	try {
    		locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        	Location loc = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        	latitude = (int)(loc.getLatitude()*1E6);
        	longitude = (int)(loc.getLongitude()*1E6);
		} catch (Exception e) {
			locationManager.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER, 0, 0, locationListener);
	    	Location loc = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
	    	latitude = (int)(loc.getLatitude()*1E6);
	    	longitude = (int)(loc.getLongitude()*1E6);
		}
    	//locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
    	//Location loc = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
    	
    	
    	
		
	}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_add_pub, menu);
        return true;
    }
}

/*//Create a new HttpClient and Post Header
HttpClient httpclient = new DefaultHttpClient();

HttpPost httppost = new HttpPost("http://www.zkonachodsport.4fan.cz/StudentsPubGuide/index.php/feed/add");

try {
    // Add your data
    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
    nameValuePairs.add(new BasicNameValuePair("nazev", nazev.getText().toString()));
    nameValuePairs.add(new BasicNameValuePair("popis", popis.getText().toString()));
    nameValuePairs.add(new BasicNameValuePair("rate", rate.toString()));
    nameValuePairs.add(new BasicNameValuePair("komentar", komentar.getText().toString()));
    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, HTTP.UTF_8));
    httpclient.execute(httppost);
    // Execute HTTP Post Request
    //HttpResponse response = httpclient.execute(httppost);
    System.out.println(httppost);

} catch (ClientProtocolException e) {
    // TODO Auto-generated catch block
} catch (IOException e) {
    // TODO Auto-generated catch block
}*/
