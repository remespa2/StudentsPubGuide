package cz.uhk.fim.studentspubguide.memory;

import java.util.ArrayList;

import com.google.android.maps.GeoPoint;

import cz.uhk.fim.studentspubguide.parse.Comment;
import cz.uhk.fim.studentspubguide.parse.Placemark;

public class Memory {
	public static ArrayList<Placemark> placemarks; 
	public static ArrayList<Placemark> placemarksWithDistance = new ArrayList<Placemark>(); 
	public static ArrayList<Comment> comments;
	public static GeoPoint pickedPoint;
	public static String trans;
	
	
	public Memory() {
	}
	
	public static ArrayList<Comment> getComments (){
		if (comments == null){
			comments = new ArrayList<Comment>();
			
		}
		
			return comments;	
			
	}
	
	public static Comment getComment(String id){
		for (Comment com : getComments()) {
			if(com.getId().equals(id)){
				return com;
			}
		}
		return null;
	}
	
	public static ArrayList<Placemark> getPlacemarks (){
		if (placemarks == null){
			placemarks = new ArrayList<Placemark>();
			
		}
		
			return placemarks;	
			
	}
	
	public static ArrayList<Placemark> getPlacemarksWithDistance (){
		if (placemarksWithDistance == null){
			placemarksWithDistance = new ArrayList<Placemark>();
			
		}
		
			return placemarksWithDistance;	
		
	}
	
	public static GeoPoint getPickedPoint(){
		if (pickedPoint == null){
			pickedPoint = new GeoPoint(0,0);
			return pickedPoint;
		}
		else{
			return pickedPoint;	
			}
	}
	
	public static Placemark getPlace(String id){
		for (Placemark place : getPlacemarks()) {
			if(place.getId().equals(id)){
				return place;
			}
		}
		return null;
	}

	public static Placemark getPlaceDist(String id) {
		for (Placemark place : getPlacemarksWithDistance()) {
			if(place.getId().equals(id)){
				return place;
			}
		}
		return null;
		
	}

	
	public static void setTrans(String str){
		trans = str;
	}
	public static String getTrans(){
		
		return trans;
	}

	public static void setPlacemarksWithDistance(Placemark pl) {
		placemarksWithDistance.add(pl);
		
	}
}
