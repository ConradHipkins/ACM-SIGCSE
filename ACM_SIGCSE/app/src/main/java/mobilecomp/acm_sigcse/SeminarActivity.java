package mobilecomp.acm_sigcse;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;

public class SeminarActivity extends AppCompatActivity {
    private ArrayList<Seminar> seminarList = new ArrayList<Seminar>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seminar);
        new HttpRequestTask().execute();
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
    private class HttpRequestTask extends AsyncTask<Void, Void, ArrayList<Seminar>> {
        @Override
        protected ArrayList<Seminar> doInBackground(Void... params) {
            try {
                final String url = "http://146.113.79.126/api/seminars";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                Seminar[] seminar = restTemplate.getForObject(url, Seminar[].class);
                return new ArrayList<Seminar>(Arrays.asList(seminar));
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<Seminar> seminars) {
            TextView tv = (TextView) findViewById(R.id.testtest);
            tv.setText("It worked!");
        }

    }

}

