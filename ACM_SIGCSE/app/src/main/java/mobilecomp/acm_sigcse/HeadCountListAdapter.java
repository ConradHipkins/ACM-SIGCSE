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

        TextView tvName = (TextView) convertView.findViewById(R.id.prev_headcount_count);
        tvName.setText(Integer.toString(headCount.getHeadCount()));

        TextView tvNumber = (TextView) convertView.findViewById(R.id.prev_headcount_timeadded);
        tvNumber.setText(headCount.getTimeAdded());

        return convertView;
    }
}
