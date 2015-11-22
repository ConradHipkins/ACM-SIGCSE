package mobilecomp.acm_sigcse;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Custom adapter class for the ListView of previously entered headcounts. Displays the time and value
 * list items.
 * @author Natalie Davenport
 * @version 11/21/2015
 */
public class HeadCountListAdapter extends ArrayAdapter<HeadCount> {
    public HeadCountListAdapter(Context context, ArrayList<HeadCount> s)
    {
        super(context, 0, s);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        HeadCount headCount = getItem(position);
        if (convertView == null)
        {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.headcount_list_item, parent, false);
        }

        //Set the textviews to headcount's values
        TextView tvName = (TextView) convertView.findViewById(R.id.prev_headcount_count);
        tvName.setText(Integer.toString(headCount.getHeadCount()));

        TextView tvNumber = (TextView) convertView.findViewById(R.id.prev_headcount_timeadded);
        tvNumber.setText(headCount.getTimeAdded());

        return convertView;
    }
}
