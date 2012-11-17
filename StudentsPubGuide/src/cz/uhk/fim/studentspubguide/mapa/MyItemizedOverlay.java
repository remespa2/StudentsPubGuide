package cz.uhk.fim.studentspubguide.mapa;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

import cz.uhk.fim.studentspubguide.PubDetailActivity;

public class MyItemizedOverlay extends ItemizedOverlay {
	private ArrayList<MyOverlayItem> mOverlays = new ArrayList<MyOverlayItem>();
	Context mContext;
	public final static String NAZEV = "a";
	public final static String POPIS = "b";
	public final static String HODNOCENI = "v";
	public final static String POCET_HODNOTITELU = "c";
	//http://developer.android.com/training/basics/firstapp/starting-activity.html
	
	public MyItemizedOverlay(Drawable defaultMarker) {
		super(boundCenterBottom(defaultMarker));
		// TODO Auto-generated constructor stub
	}
	
	public MyItemizedOverlay(Drawable defaultMarker, Context context) {
		  super(boundCenterBottom(defaultMarker));
		  mContext = context;
		}

	
		
	@Override
	protected OverlayItem createItem(int i) {
	  return mOverlays.get(i);
	}

	@Override
	public int size() {
	  return mOverlays.size();
	}
	
	public void addOverlay(MyOverlayItem overlayItem) {
	    mOverlays.add(overlayItem);
	    populate();
	}
	
	@Override
	protected boolean onTap(int index) {
	  MyOverlayItem item = mOverlays.get(index);
	  //AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
	  //dialog.setTitle(item.getTitle());
	  //dialog.setMessage(item.getSnippet());
	  //dialog.show();
	  
	  
	  	Intent intent = new Intent(mContext, PubDetailActivity.class);
	  
	    //EditText editText = (EditText) findViewById(R.id.edit_message);
	    String nazev = item.getTitle();
	    intent.putExtra(NAZEV, nazev);
	    
	    String hodnoceni = item.getHodnoceni();
	    intent.putExtra(HODNOCENI, hodnoceni);
	    
	    String popis = item.getSnippet();
	    intent.putExtra(POPIS, popis);
	    
	    String pocet = item.getPocet_hodn();
	    intent.putExtra(POCET_HODNOTITELU, pocet);
	    
	    System.out.println(NAZEV);
	    mContext.startActivity(intent);
	  
	  
	  
	  return true;
	}

}
