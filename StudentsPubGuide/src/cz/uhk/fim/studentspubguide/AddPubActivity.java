package cz.uhk.fim.studentspubguide;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import cz.uhk.fim.studentspubguide.R;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

public class AddPubActivity extends Activity {
	private int latitude, longitude;
	private EditText nazev, popis, komentar;
	private Button add;
	private RatingBar rate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pub);
        findPosition();
        nazev = (EditText) findViewById(R.id.editText0);
        popis = (EditText) findViewById(R.id.editText1);
        komentar = (EditText) findViewById(R.id.editText2);
        rate = (RatingBar) findViewById(R.id.ratingBar1);
        add = (Button) findViewById(R.id.buttonADD);
        add.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				printOut();
				
				
			}
		});
        
        
    }

    final private void printOut(){
    	System.out.println(nazev.getText());
		System.out.println(popis.getText());
		System.out.println(komentar.getText());
		System.out.println(rate.getRating());
    }
    public void postData() {
        // Create a new HttpClient and Post Header
        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("http://www.zkonachodsport.4fan.cz/StudentsPubGuide/feed/add");

        try {
            // Add your data
            List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
            nameValuePairs.add(new BasicNameValuePair("id", "12345"));
            nameValuePairs.add(new BasicNameValuePair("stringdata", "Hi"));
            httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

            // Execute HTTP Post Request
            HttpResponse response = httpclient.execute(httppost);

        } catch (ClientProtocolException e) {
            // TODO Auto-generated catch block
        } catch (IOException e) {
            // TODO Auto-generated catch block
        }
    }
    
    
    
    private void findPosition() {
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
    	locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
    	Location loc = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
    	latitude = (int)(loc.getLatitude()*1E6);
    	longitude = (int)(loc.getLongitude()*1E6);
    	
    	
		
	}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_add_pub, menu);
        return true;
    }
}
