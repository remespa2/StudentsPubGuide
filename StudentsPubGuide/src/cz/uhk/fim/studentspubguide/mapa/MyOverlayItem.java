package cz.uhk.fim.studentspubguide.mapa;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.OverlayItem;

public class MyOverlayItem extends OverlayItem {
	//private GeoPoint point;
	private String hodnoceni, pocet_hodn;

	public MyOverlayItem(GeoPoint point, String title, String snippet) {
		super(point, title, snippet);
		// TODO Auto-generated constructor stub
	}
	
	
	public MyOverlayItem(GeoPoint point, String title, String popis, String hodnoceni, String pocet_hodn) {
		super(point, title, popis);
		//this.point = point;
		//this.nazev = title;
		//this.popis = popis;
		this.setHodnoceni(hodnoceni);
		this.setPocet_hodn(pocet_hodn);
		
	}


	public String getHodnoceni() {
		return hodnoceni;
	}


	public void setHodnoceni(String hodnoceni) {
		this.hodnoceni = hodnoceni;
	}


	public String getPocet_hodn() {
		return pocet_hodn;
	}


	public void setPocet_hodn(String pocet_hodn) {
		this.pocet_hodn = pocet_hodn;
	}

}
