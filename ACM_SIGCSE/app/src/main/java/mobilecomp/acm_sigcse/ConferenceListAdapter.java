package mobilecomp.acm_sigcse;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Custom adapter for the ConferenceActivity listview in the ActivityListActivity class. Displays
 * conference number and title
 * @author Natalie Davenport
 * @version 11/21/15
 */
public class ConferenceListAdapter extends ArrayAdapter<ConferenceActivity> {
    public ConferenceListAdapter(Context context, ArrayList<ConferenceActivity> s)
    {
        super(context, 0, s);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ConferenceActivity activity = getItem(position);

        if (convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_list_item, parent, false);
        }

        //Sets the values of the textviews
        TextView tvName = (TextView) convertView.findViewById(R.id.activityName);
        tvName.setText(activity.getName());
        TextView tvNumber = (TextView) convertView.findViewById(R.id.activityNumber);
        tvNumber.setText(activity.getNumber());

        return convertView;
    }
}
