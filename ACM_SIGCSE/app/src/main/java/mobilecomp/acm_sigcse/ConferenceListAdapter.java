package mobilecomp.acm_sigcse;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Natalie on 11/10/15.
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

        TextView tvName = (TextView) convertView.findViewById(R.id.activityName);
        tvName.setText(activity.getName());
        TextView tvNumber = (TextView) convertView.findViewById(R.id.activityNumber);
        tvNumber.setText(activity.getNumber());

        return convertView;
    }
}
