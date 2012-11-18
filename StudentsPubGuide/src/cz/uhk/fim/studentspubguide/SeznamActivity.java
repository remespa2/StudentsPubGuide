package cz.uhk.fim.studentspubguide;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import cz.uhk.fim.studentspubguide.R;

import cz.uhk.fim.studentspubguide.parse.ParserWithDistance;
import cz.uhk.fim.studentspubguide.parse.Placemark;

public class SeznamActivity extends Activity {
	private ArrayList<Placemark> placemarks;
	public ArrayList<Placemark> getPlacemarks() {
		return placemarks;
	}

	int latitude, longitude;
	private int radius = 200000;
	//private SimpleAdapter sa;
	public final static String NAZEV = "a";
	public final static String POPIS = "b";
	public final static String HODNOCENI = "v";
	public final static String POCET_HODNOTITELU = "c";
	
    //http://developer.android.com/training/basics/firstapp/starting-activity.html
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seznam);
        
        findPosition();
        
        //System.out.println(latitude + " " + longitude);
        //ParserWithDistance pwd = new ParserWithDistance(latitude, longitude, radius);
        ParserWithDistance pwd;
        try {
			pwd = new ParserWithDistance(latitude, longitude, radius);
			this.placemarks = pwd.getMyPlacmarks();
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			pwd = null;
			this.placemarks = new ArrayList<Placemark>();
		}

        
        
        
        /*ArrayList<String> plcStr = new ArrayList<String>();
        */
        /*for (Placemark place : placemarks) {
			plcStr.add(place.getName()+"     "+place.getVzdalenost());
			
		}*/
        
        //http://tekeye.biz/2012/two-line-lists-in-android
        ArrayList<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
        HashMap<String,String> item;
        for (Placemark place : placemarks) {
        	item = new HashMap<String,String>();
			item.put("line1", place.getName());
			item.put("line2", place.getHodnocni());
			list.add(item);
		}
        
        SimpleAdapter adapter1 = new SimpleAdapter(this, list,
        				android.R.layout.two_line_list_item,
        				new String[] { "line1","line2" },
        				new int[] {android.R.id.text1, android.R.id.text2});
        
        //http://developer.android.com/guide/topics/ui/declaring-layout.html#AdapterViews
        /*ArrayAdapter adapter = new ArrayAdapter<String>(this, 
        		android.R.layout.two_line_list_item, plcStr);
        */
        
        
        ListView listView = (ListView) findViewById(R.id.listView1);
        listView.setAdapter(adapter1);
        
        
        final Intent intent1 = new Intent(this, PubDetailActivity.class);
        
        
        listView.setOnItemClickListener(new OnItemClickListener() {
        	public void onItemClick(AdapterView parent, View v, int position, long id) {
        			Placemark place = placemarks.get(position); 	
        		
        			String nazev = place.getName();
        		    intent1.putExtra(NAZEV, nazev);
        		    
        		    String hodnoceni = place.getHodnocni();
        		    intent1.putExtra(HODNOCENI, hodnoceni);
        		    
        		    String popis = place.getDescription();
        		    intent1.putExtra(POPIS, popis);
        		    
        		    String pocet = place.getPocetHodnoceni();
        		    intent1.putExtra(POCET_HODNOTITELU, pocet);
        		    
        		    
        		    startActivity(intent1);
        		
            }
        });
        
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
        getMenuInflater().inflate(R.menu.activity_seznam, menu);
        return true;
    }
}
