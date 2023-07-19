package sg.edu.rp.c346.id22014726.myndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.CaseMap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText title;
    EditText singers;
    EditText year;
    RadioGroup ratings;
    Button insert;
    Button show;
    ListView lv;
    ArrayList<Songs> al;
//    ArrayAdapter<Songs> aa;
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = findViewById(R.id.editTitle);
        singers = findViewById(R.id.editSingers);
        year = findViewById(R.id.editYear);
        ratings = findViewById(R.id.Star);
        insert = findViewById(R.id.btnInsert);
        show = findViewById(R.id.btnShow);
        lv = findViewById(R.id.lv);

        al = new ArrayList<Songs>();
//        aa = new ArrayAdapter<Songs>(this,android.R.layout.simple_list_item_1, al);


    insert.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DBHelper db = new DBHelper(MainActivity.this);

            String songTitle = String.valueOf(title.getText());
            String songSingers = String.valueOf(singers.getText());
            int songYear = Integer.valueOf(String.valueOf(year.getText()));
            int stars = 0;

            String rating = "";
            int selectedRatingID = ratings.getCheckedRadioButtonId();
            if(selectedRatingID == R.id.rb1){
                rating += "1";
            }else if(selectedRatingID == R.id.rb2){
                rating += "2";
            }else if(selectedRatingID == R.id.rb3){
                rating += "3";
            }else if(selectedRatingID == R.id.rb4){
                rating += "4";
            }else{
                rating += "5";
            }
            db.insertSong(songTitle, songSingers, songYear, stars);
            Toast toast = Toast.makeText(insert.getContext(), "Song added successfully", Toast.LENGTH_LONG);
            toast.show();

            db.close();
        }
    });
            show.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DBHelper db = new DBHelper(MainActivity.this);

                    ArrayList<String> Title = db.getSongTitle();
                    ArrayList<String> Singers = db.getSongSingers();
                    ArrayList<String> Year = db.getSongYear();
                    ArrayList<String> Stars = db.getSongStars();
                    db.close();

                    String txt = "";
                    ArrayList<Object> songs = new ArrayList<>();
                    for (int i = 0; i < Title.size(); i++) {
                        Log.d("Database Content", i +"\nSong Title: " + Title.get(i) + "\nSingers: " + Singers.get(i) + "\nYear of Release: " + Year.get(i)
                                + "\nStars: " + Stars.get(i));
                        txt = "\nSong Title: " + Title.get(i) + "\nSingers: " + Singers.get(i) + "\nYear of Release: " + Year.get(i)
                                + "\nStars: " + Stars.get(i);
                        songs.add(i,txt + "\n");
                    }


//                    ArrayAdapter adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, songs);
//                    lv.setAdapter(adapter);
                    adapter = new CustomAdapter(MainActivity.this, R.layout.row, al);
                    lv.setAdapter(adapter);

                    Intent i = new Intent(MainActivity.this, activity_show.class);
                    startActivity(i);

                }
            });
    }
}