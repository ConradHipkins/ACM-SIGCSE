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

import java.util.ArrayList;
import java.util.Arrays;

public class SeminarActivity extends AppCompatActivity {
    private ArrayList<Seminar> seminarList = new ArrayList<Seminar>();
    private ArrayList<Seminar> testSeminarList = new ArrayList<Seminar>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seminar);
    //Gets the list of all the seminars
        new GetAllSeminarsTask().execute();

        Seminar sem1 = new Seminar();
        sem1.setSeminarName("Seminar 1 Title");
        sem1.setSeminarNumber("00001");
        Seminar sem2 = new Seminar();
        sem2.setSeminarName("Cool name2");
        sem2.setSeminarNumber("00002");
        Seminar sem3 = new Seminar();
        sem3.setSeminarName("Cool name3");
        sem3.setSeminarNumber("00015");
        Seminar sem4 = new Seminar();
        sem4.setSeminarName("Another seminar");
        sem4.setSeminarNumber("00120");
        Seminar sem5 = new Seminar();
        sem5.setSeminarName("Holy cow, another seminar");
        sem5.setSeminarNumber("01000");

        //Adds the seminars to a list
        testSeminarList.add(sem1);
        testSeminarList.add(sem2);
        testSeminarList.add(sem3);
        testSeminarList.add(sem4);
        testSeminarList.add(sem5);

        //Displays the seminars
        ListView listView = (ListView) findViewById(R.id.viewAllSeminars);
        listView.setAdapter(new SeminarListAdapter(this, testSeminarList));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(SeminarActivity.this, HeadCountActivity.class);
                intent.putExtra("SEMINAR_NAME", testSeminarList.get(position).getSeminarName());
                intent.putExtra("SEMINAR_NUMBER", testSeminarList.get(position).getSeminarNumber());
                startActivity(intent);
            }
        });
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
    /*
        Gets the seminar arraylist and handles the connection to the database
     */

    private class GetAllSeminarsTask extends AsyncTask<Void, Void, ArrayList<Seminar>> {
        @Override
        protected ArrayList<Seminar> doInBackground(Void... params) {
            try {
                final String url = String.format("http://%s/api/seminars", getString(R.string.server_address));//Added port number
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                Seminar[] seminar = restTemplate.getForObject(url, Seminar[].class);
                return new ArrayList<Seminar>(Arrays.asList(seminar));
            } catch (Exception e) {
                Log.e("SeminarActivity", e.getMessage(), e);
            }
            return null;
        }
    //Displays a taost when the onPostExecute function gets executed
        @Override
        protected void onPostExecute(ArrayList<Seminar> seminars) {
            Toast.makeText(getApplicationContext(), "Call executed",Toast.LENGTH_SHORT).show();
        }

    }

}

