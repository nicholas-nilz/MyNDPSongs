package sg.edu.rp.c346.id22014726.myndpsongs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {

    Context parent_context;
    int layout_id;
    ArrayList<Songs> songsArrayList;

    public CustomAdapter(Context context, int resource, ArrayList<Songs> objects) {
        super(context, resource, objects);

        parent_context = context;
        layout_id = resource;
        songsArrayList = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        //Obtain the LayoutInflater object
        LayoutInflater inflater = (LayoutInflater)
                parent_context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        //"Inflate" the View for each row
        View rowView = inflater.inflate(layout_id, parent, false);

        //Obtain the UI components and do the necessary binding;
        TextView tvTitle = rowView.findViewById(R.id.tvTitle);
        TextView tvSingers = rowView.findViewById(R.id.tvSingers);
        TextView tvYear = rowView.findViewById(R.id.tvYear);
        TextView tvStars = rowView.findViewById(R.id.tvStars);

        //Obtain the Android Version information based on the position
        Songs currentVersion = songsArrayList.get(position);

        //Set values to the TextView to display the corresponding information
        tvTitle.setText(currentVersion.getTitle());
        tvSingers.setText(currentVersion.getSingers());
        tvYear.setText(currentVersion.getYear() + "");
        tvStars.setText(currentVersion.getStars() + " Stars");

        return rowView;
    }
}
