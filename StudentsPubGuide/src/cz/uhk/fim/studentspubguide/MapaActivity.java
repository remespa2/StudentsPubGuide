package cz.uhk.fim.studentspubguide;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnLongClickListener;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;

import cz.uhk.fim.studentspubguide.mapa.MyItemizedOverlay;
import cz.uhk.fim.studentspubguide.mapa.MyOverlayItem;
import cz.uhk.fim.studentspubguide.parse.Parser;
import cz.uhk.fim.studentspubguide.parse.Placemark;


public class MapaActivity extends MapActivity {
	protected MapView map;
    private MyLocationOverlay myLocationOverlay;
    private Parser parser;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        setupMapView();
        setupMyLocation();
        
        ArrayList<Placemark> places;
     // pøidání mé vrstvy
        List<Overlay> mapOverlays = map.getOverlays();
        Drawable drawable = this.getResources().getDrawable(R.drawable.location_marker2);
        MyItemizedOverlay itemizedoverlay = new MyItemizedOverlay(drawable, this);
        
        try {
			parser = new Parser();
			places = parser.getMyPlacmarks();
			 for (Placemark place : places) {
		        	itemizedoverlay.addOverlay(new MyOverlayItem(new GeoPoint(place.getLatitude(), place.getLongtitude()), place.getName(), place.getDescription(), place.getHodnocni(), place.getPocetHodnoceni()));
		        	//itemizedoverlay.addOverlay(new OverlayItem(place.getPoint().getGeoPoint(),place.getName(), place.getDescription()));
				}
			 mapOverlays.add(itemizedoverlay);
			 
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			places = null;
			System.out.println("bum");
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			places = null;
			System.out.println("bum");
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("fuck");
			places = null;
			
		}

        
       
       // až sem
        
       
    }

    private void setupMapView() {
    	this.map = (MapView) findViewById(R.id.mapview);
	    map.setBuiltInZoomControls(true);
	    //map.setSatellite(true);
	    //map.setTraffic(true);
	    map.setLongClickable(true);
	    
	   map.setOnLongClickListener(new OnLongClickListener() {
			
			public boolean onLongClick(View v) {
				startActivity(new Intent(map.getContext(), AddPubActivity.class));
				return false;
			}
		});
		
	}

    
	private void setupMyLocation() {
		this.myLocationOverlay = new MyLocationOverlay(this, map);
        myLocationOverlay.enableMyLocation();
        myLocationOverlay.runOnFirstFix(new Runnable() {
          //@Override
          public void run() {
            GeoPoint currentLocation = myLocationOverlay.getMyLocation();
            //map.setSatellite(true);
            map.getController().animateTo(currentLocation);
            map.getController().setZoom(14);
            map.getOverlays().add(myLocationOverlay);
            //System.out.println("AHOJ" + currentLocation.getLatitudeE6());
            //System.out.println("ahoj" + currentLocation.getLongitudeE6());
            //myLocationOverlay.setFollowing(true);
          }
        });
		
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_mapa, menu);
        return true;
    }

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	protected void onResume() {
		myLocationOverlay.enableMyLocation();
	    myLocationOverlay.enableCompass();
	    super.onResume();
	}
	
	
}
