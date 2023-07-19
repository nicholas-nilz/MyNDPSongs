package sg.edu.rp.c346.id22014726.myndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class activity_show extends AppCompatActivity {

    ArrayList<String> songs;
    ListView lv;
    Button back;
    Button btn;
    ArrayList<Songs> al;
    ArrayAdapter<Songs> aa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        back = findViewById(R.id.btnBack);
        lv = findViewById(R.id.lv);
        btn = findViewById(R.id.btn);
        al = new ArrayList<Songs>();
        aa = new ArrayAdapter<Songs>(this, android.R.layout.simple_list_item_1, al);

        lv.setAdapter(aa);

        Intent i = getIntent();
        DBHelper db = new DBHelper(activity_show.this);
        al.clear();
        al.addAll(db.getSongContent());
        aa.notifyDataSetChanged();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Songs data = al.get(position);
                Intent i = new Intent(activity_show.this, activity_show.class);
                i.putExtra("ID", data.getId());
                i.putExtra("Title", data.getTitle());
                i.putExtra("Singers", data.getSingers());
                i.putExtra("Year", data.getYear());
                i.putExtra("Stars", data.getStars());
                startActivity(i);
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(activity_show.this);
                al.clear();
                String filterText = "*****";
                al.addAll(dbh.getAllSongContent(String.valueOf(filterText.length())));
                aa.notifyDataSetChanged();
            }
        });
    }
}