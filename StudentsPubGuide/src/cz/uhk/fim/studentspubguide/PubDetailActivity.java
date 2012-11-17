package cz.uhk.fim.studentspubguide;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import cz.uhk.fim.studentspubguide.R;

import cz.uhk.fim.studentspubguide.mapa.MyItemizedOverlay;
import cz.uhk.fim.studentspubguide.parse.Placemark;

public class PubDetailActivity extends Activity {
	private TextView textNazev,textPopis;
	private RatingBar rate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pub_detail);
        Intent intent = getIntent();
        String nazev = intent.getStringExtra(MyItemizedOverlay.NAZEV);
        String popis = intent.getStringExtra(MyItemizedOverlay.POPIS);
        String hodnoceni = intent.getStringExtra(MyItemizedOverlay.HODNOCENI);
        String pocet = intent.getStringExtra(MyItemizedOverlay.POCET_HODNOTITELU);
        
        textNazev = (TextView) findViewById(R.id.textNazev);
        textNazev.setText(nazev);
        
        textPopis = (TextView) findViewById(R.id.textPopis);
        textPopis.setText(popis +"\nHodnotilo: "+ pocet);
        
        rate = (RatingBar) findViewById(R.id.ratingBar1);
        rate.setRating(Float.parseFloat(hodnoceni));
        rate.setClickable(false);
        rate.setEnabled(false);
        
        ArrayList<String> plcStr = new ArrayList<String>();
        
        plcStr.add(new String("ahoj"));
        plcStr.add(new String("ahoj"));
        plcStr.add(new String("ahoj"));
        plcStr.add(new String("ahoj"));
        plcStr.add(new String("ahoj"));
        plcStr.add(new String("ahoj"));
        plcStr.add(new String("ahoj"));
        plcStr.add(new String("ahoj"));
        plcStr.add(new String("ahoj"));
        
        plcStr.add("koment2");
        plcStr.add("koment3");
        plcStr.add("koment4");
        plcStr.add("koment5");
        plcStr.add("koment6");
        plcStr.add("koment1");
        plcStr.add("koment2");
        plcStr.add("koment3");
        plcStr.add("koment4");
        plcStr.add("koment5");
        plcStr.add("koment6");
        plcStr.add("koment1");
        plcStr.add("koment2");
        plcStr.add("koment3");
        plcStr.add("koment4");
        plcStr.add("koment5");
        plcStr.add("koment6");
        

        ArrayAdapter adapter = new ArrayAdapter<String>(this, 
        		android.R.layout.simple_list_item_1, plcStr);
        
        
        /*for (String string : plcStr) {
			System.out.println("AHOJ" +string);
		}*/
        
        ListView listView = (ListView) findViewById(R.id.listViewKoment);
        listView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_pub_detail, menu);
        return true;
    }
}
