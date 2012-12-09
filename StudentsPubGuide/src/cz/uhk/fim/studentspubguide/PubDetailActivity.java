package cz.uhk.fim.studentspubguide;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import cz.uhk.fim.studentspubguide.memory.Memory;
import cz.uhk.fim.studentspubguide.parse.Base64;
import cz.uhk.fim.studentspubguide.parse.Comment;
import cz.uhk.fim.studentspubguide.parse.ParserComments;
import cz.uhk.fim.studentspubguide.parse.Placemark;

public class PubDetailActivity extends Activity {
	private TextView textNazev, textPopis;
	private RatingBar rate;
	private ImageButton bCom, bRate, bBack;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pub_detail);
		Intent intent = getIntent();
		// String nazev = intent.getStringExtra(MyItemizedOverlay.NAZEV);
		// String popis = intent.getStringExtra(MyItemizedOverlay.POPIS);
		// String hodnoceni =
		// intent.getStringExtra(MyItemizedOverlay.HODNOCENI);
		// String pocet =
		// intent.getStringExtra(MyItemizedOverlay.POCET_HODNOTITELU);
		// String index = intent.getStringExtra(MyItemizedOverlay.NAZEV);

		String index = Memory.getTrans();

		Placemark place = null;
		/*
		 * for (Placemark p : Memory.getPlacemarksWithDistance()) {
		 * System.out.println(p.getId()); }
		 */

		try {
			place = Memory.getPlaceDist(index);

			if (place == null) {
				place = Memory.getPlace(index);
			}

		} catch (Exception e) {
			e.printStackTrace();
			// place = new Placemark();
			place = new Placemark("-1", "0", "0", "nelze naèíst",
					"nelze naèíst", "0", "0");

		}

		textNazev = (TextView) findViewById(R.id.textNazev);
		textNazev.setText(place.getName());

		textPopis = (TextView) findViewById(R.id.textPopis);
		textPopis.setText(place.getDescription() + "\nHodnotilo: "
				+ place.getPocetHodnoceni());

		rate = (RatingBar) findViewById(R.id.ratingBar1);
		rate.setRating(Float.parseFloat(place.getHodnocni()));
		rate.setClickable(false);
		rate.setEnabled(false);

		ArrayList<String> plcStr = new ArrayList<String>();

		try {
			Memory.getComments().clear();
			ParserComments par = new ParserComments(Integer.parseInt(place
					.getId()));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (Comment com : Memory.getComments()) {
			if(com.getText().length() > 21){
				plcStr.add(com.getText().substring(0, 20));
			}else{
				plcStr.add(com.getText());
			}
		}

		/*
		 * plcStr.add(new String("ahoj")); plcStr.add(new String("ahoj"));
		 * plcStr.add(new String("ahoj")); plcStr.add(new String("ahoj"));
		 * plcStr.add(new String("ahoj")); plcStr.add(new String("ahoj"));
		 * plcStr.add(new String("ahoj")); plcStr.add(new String("ahoj"));
		 * plcStr.add(new String("ahoj"));
		 * 
		 * plcStr.add("koment2"); plcStr.add("koment3"); plcStr.add("koment4");
		 * plcStr.add("koment5"); plcStr.add("koment6"); plcStr.add("koment1");
		 * plcStr.add("koment2"); plcStr.add("koment3"); plcStr.add("koment4");
		 * plcStr.add("koment5"); plcStr.add("koment6"); plcStr.add("koment1");
		 * plcStr.add("koment2"); plcStr.add("koment3"); plcStr.add("koment4");
		 * plcStr.add("koment5"); plcStr.add("koment6");
		 */

		ArrayAdapter adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, plcStr);

		/*
		 * for (String string : plcStr) { System.out.println("AHOJ" +string); }
		 */

		ListView listView = (ListView) findViewById(R.id.listViewKoment);
		listView.setAdapter(adapter);

		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView parent, View v, int position,
					long id) {
				AlertDialog.Builder dialog = new AlertDialog.Builder(
						getActivity());
				dialog.setTitle("Komentáø");
				dialog.setMessage(Memory.getComments().get(position).getText());
				dialog.setPositiveButton("OK",
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {
								dialog.cancel();

							}
						});
				dialog.show();

				// Placemark place = placemarks.get(position);

				// String nazev = place.getName();
				// Memory.setTrans(place.getId());
				// intent1.putExtra(NAZEV, place.getId());

				// startActivity(intent1);

			}
		});

		bRate = (ImageButton) findViewById(R.id.button1);
		bRate.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {

				// AlertDialog.Builder dialog = new
				// AlertDialog.Builder(getActivity());
				final Dialog dialog = new Dialog(getActivity());
				LayoutInflater inflater = getActivity().getLayoutInflater();
				dialog.setContentView(R.layout.rate_pub);
				dialog.setTitle("Hodnocení");
				// dialog.setMessage("");
				View layout = inflater.inflate(R.layout.rate_pub, null);
				// dialog.setView(inflater.inflate(R.layout.rate_pub, null));

				/*
				 * dialog.setPositiveButton("OK", new
				 * DialogInterface.OnClickListener() {
				 * 
				 * public void onClick(DialogInterface dialog, int which) { URL
				 * url = null;
				 * 
				 * //rate2 = (RatingBar) findViewById(R.id.ratingBar2); try {
				 * url = new URL(
				 * "http://www.zkonachodsport.4fan.cz/StudentsPubGuide/index.php/feed/rate/"
				 * +String.valueOf(rate2.getRating())); HttpURLConnection
				 * urlConnection = (HttpURLConnection) url.openConnection();
				 * InputStream in = new
				 * BufferedInputStream(urlConnection.getInputStream());
				 * urlConnection.disconnect(); } catch (MalformedURLException e)
				 * { // TODO Auto-generated catch block e.printStackTrace(); }
				 * catch (IOException e) { // TODO Auto-generated catch block
				 * e.printStackTrace(); } System.out.println(url);
				 * 
				 * 
				 * } }); dialog.setNegativeButton("Cancel", new
				 * DialogInterface.OnClickListener() {
				 * 
				 * public void onClick(DialogInterface dialog, int which) {
				 * dialog.cancel();
				 * 
				 * } });
				 */

				final RatingBar rate2 = (RatingBar) dialog.findViewById(R.id.ratingBar2);

				Button b1 = (Button) dialog.findViewById(R.id.button1);
				b1.setOnClickListener(new OnClickListener() {

					public void onClick(View v) {
						URL url = null;

						// rate2 = (RatingBar) findViewById(R.id.ratingBar2);
						try {
							url = new URL(
									"http://www.zkonachodsport.4fan.cz/StudentsPubGuide/index.php/feed/rate/"
											+  Memory.trans.toString()  +"/"
											+ String.valueOf(rate2.getRating()));
							HttpURLConnection urlConnection = (HttpURLConnection) url
									.openConnection();
							InputStream in = new BufferedInputStream(
									urlConnection.getInputStream());
							urlConnection.disconnect();
						} catch (MalformedURLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println(url);
						dialog.hide();
					}
				});
				Button b2 = (Button) dialog.findViewById(R.id.button2);
				b2.setOnClickListener(new OnClickListener() {

					public void onClick(View v) {
						dialog.cancel();

					}
				});

				dialog.show();

			}
		});
		
		
		bCom = (ImageButton) findViewById(R.id.button2);
		bCom.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				final Dialog dialog = new Dialog(getActivity());
				LayoutInflater inflater = getActivity().getLayoutInflater();
				dialog.setContentView(R.layout.comment_pub);
				dialog.setTitle("Komentáø");
				
				Button b1 = (Button)dialog.findViewById(R.id.button1);
				b1.setOnClickListener(new OnClickListener() {
					
					public void onClick(View v) {
						URL url = null;
						EditText et = (EditText)dialog.findViewById(R.id.editText1);
						

						// rate2 = (RatingBar) findViewById(R.id.ratingBar2);
						try {
							url = new URL(
									"http://www.zkonachodsport.4fan.cz/StudentsPubGuide/index.php/feed/comment/"
											+  Memory.trans.toString()  +"/"
											+ Base64.encodeBytes(et.getText().toString().getBytes()));
							HttpURLConnection urlConnection = (HttpURLConnection) url
									.openConnection();
							InputStream in = new BufferedInputStream(
									urlConnection.getInputStream());
							urlConnection.disconnect();
						} catch (MalformedURLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println(url);
						dialog.hide();
						
					}
				});
				
				
				Button b2 = (Button)dialog.findViewById(R.id.button2);
				b2.setOnClickListener(new OnClickListener() {
					
					public void onClick(View v) {
						
						dialog.cancel();
						
					}
				});
				
				
				
				
				
				dialog.show();
				
			}
		});
		
		
		
		
		
		
		bBack = (ImageButton) findViewById(R.id.button3);
		bBack.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				finish();
				
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_pub_detail, menu);
		return true;
	}

	public Activity getActivity() {
		return this;
	}
}
