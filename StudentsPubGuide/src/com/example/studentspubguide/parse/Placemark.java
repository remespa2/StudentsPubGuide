package com.example.studentspubguide.parse;

import com.google.android.maps.GeoPoint;

public class Placemark {
	private String id;
	private int latitude, longtitude;
	private String name;
	private String description;
	private String vzdalenost;
	private String hodnocni;
	private String pocetHodnoceni;

	public Placemark(String id, String latitude, String longtitude,
			String name, String description, String hodnoceni,String pocet) {
		super();
		this.id = id;
		this.latitude= (int)(Integer.parseInt(latitude));
		this.longtitude= (int)(Integer.parseInt(longtitude));
		this.name = name;
		this.description = description;
		this.hodnocni = hodnoceni;
		this.pocetHodnoceni = pocet;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	public Placemark(){
		
	}
	
	@Override
	public String toString(){
		
		return description + " "+name;
		
	}

	public int getLatitude() {
		return latitude;
	}

	public void setLatitude(int latitude) {
		this.latitude = latitude;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getLongtitude() {
		return longtitude;
	}

	public void setLongtitude(int longtitude) {
		this.longtitude = longtitude;
	}

	public String getVzdalenost() {
		return vzdalenost;
	}

	public void setVzdalenost(String vzdalenost) {
		this.vzdalenost = vzdalenost;
	}

	public String getHodnocni() {
		return hodnocni;
	}

	public void setHodnocni(String hodnocni) {
		this.hodnocni = hodnocni;
	}

	public String getPocetHodnoceni() {
		return pocetHodnoceni;
	}

	public void setPocetHodnoceni(String pocetHodnoceni) {
		this.pocetHodnoceni = pocetHodnoceni;
	}
}
