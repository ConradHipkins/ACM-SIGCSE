package mobilecomp.acm_sigcse;
/*
Created by Liz and Natalie on 11/13/15. This class displays the list of the seminars in a list.
 It works with the XML file to display the list in a linear layout format.
 */

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class SeminarActivity extends AppCompatActivity {
    private ArrayList<Seminar> seminarList = new ArrayList<Seminar>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seminar);

        //Gets the list of all the seminars
        new GetAllSeminarsTask().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_seminar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
        Gets the seminar arraylist and handles the connection to the database
     */
    private void updateSeminarsList(ArrayList<Seminar> list)
    {
        seminarList.clear();
        seminarList.addAll(list);

        //Displays the seminars
        ListView listView = (ListView) findViewById(R.id.viewAllSeminars);
        listView.setAdapter(new SeminarListAdapter(this, seminarList));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SeminarActivity.this, HeadCountActivity.class);
                intent.putExtra("SEMINAR_NAME", seminarList.get(position).getSemName());
                intent.putExtra("SEMINAR_NUMBER", seminarList.get(position).getSemNum());
                startActivity(intent);
            }
        });
    }

    private class GetAllSeminarsTask extends AsyncTask<Void, Void, ArrayList<Seminar>> {
        @Override
        protected ArrayList<Seminar> doInBackground(Void... params) {
            try {
                final String url = String.format("http://%s/api/seminars", getString(R.string.server_address));
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                Seminar[] seminar = restTemplate.getForObject(url, Seminar[].class);
                return new ArrayList<Seminar>(Arrays.asList(seminar));
            } catch (Exception e) {
                Log.e("SeminarActivity", e.getMessage(), e);
            }
            return null;
        }
        @Override
        protected void onPostExecute(ArrayList<Seminar> seminars) {
            updateSeminarsList(seminars);
        }

    }

}

