package sg.edu.rp.c346.id22014726.myndpsongs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class activity_edit extends AppCompatActivity {

    Button cancel;
    Button delete;
    Button update;
    EditText Stitle;
    EditText Ssingers;
    EditText Syear;
    TextView id;
    TextView stars;
    RadioGroup ratings;
    Songs gID, gTitle, gSingers, gYear, gStars;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        id = findViewById(R.id.ID);
        Stitle = findViewById(R.id.etTitle);
        Ssingers = findViewById(R.id.etSingers);
        Syear = findViewById(R.id.etYear);
        delete = findViewById(R.id.btnDelete);
        update = findViewById(R.id.btnUpdate);
        cancel = findViewById(R.id.btnCancel);
        stars = findViewById(R.id.Stars);
        ratings = findViewById(R.id.rating);

        Intent i = getIntent();

        gID = (Songs) i.getSerializableExtra("ID");
        id.setText(String.valueOf(gID.getId()));
        gTitle = (Songs) i.getSerializableExtra("Title");
        Stitle.setText(gTitle.getTitle());
        gSingers = (Songs) i.getSerializableExtra("Singers");
        Ssingers.setText(gSingers.getSingers());
        gYear = (Songs) i.getSerializableExtra("Year");
        Syear.setText(String.valueOf(gYear.getYear()));
        gStars = (Songs) i.getSerializableExtra("Stars");

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(activity_edit.this);
                db.deleteSong(gID.getId());
                finish();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(activity_edit.this);
                String songTitle = String.valueOf(Stitle.getText());
                String songSingers = String.valueOf(Ssingers.getText());
                int songYear = Integer.valueOf(String.valueOf(Syear.getText()));
                int stars = 0;

                int selectedRatingID = ratings.getCheckedRadioButtonId();
                if (selectedRatingID == R.id.rb1) {
                    stars += Integer.parseInt("1");
                } else if (selectedRatingID == R.id.rb2) {
                    stars += Integer.parseInt("2");
                } else if (selectedRatingID == R.id.rb3) {
                    stars += Integer.parseInt("3");
                } else if (selectedRatingID == R.id.rb4) {
                    stars += Integer.parseInt("4");
                } else {
                    stars += Integer.parseInt("5");
                }

                gTitle.setTitle(songTitle);
                gSingers.setSingers(songSingers);
                gYear.setYear(songYear);
                gStars.setStars(stars);

                db.updateSong(gTitle, gSingers, gYear, gStars);
                db.close();
                finish();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}