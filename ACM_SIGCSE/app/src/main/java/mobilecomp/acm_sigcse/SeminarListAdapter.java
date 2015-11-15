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
public class SeminarListAdapter extends ArrayAdapter<Seminar> {
    public SeminarListAdapter(Context context, ArrayList<Seminar> s)
    {
        super(context, 0, s);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        Seminar seminar = getItem(position);

        if (convertView == null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.seminar_list_item, parent, false);
        }

        TextView tvName = (TextView) convertView.findViewById(R.id.seminarName);
        tvName.setText(seminar.getSeminarName());
        TextView tvNumber = (TextView) convertView.findViewById(R.id.seminarNumber);
        tvNumber.setText(seminar.getSeminarNumber());

        return convertView;
    }
}
