package mobilecomp.acm_sigcse;
/***
 * Created by Liz and Natalie on 11/13/15. This class displays the list of the activities in a list.
 *  It works with the XML file to display the list in a linear layout format.
 *  @Author Liz, Natalie
 *  @Date 11/13/15
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

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;

public class ActivityListActivity extends AppCompatActivity {
    private ArrayList<ConferenceActivity> activityList = new ArrayList<ConferenceActivity>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_list);

        //Get listview and set onItemLickListener
        ListView listView = (ListView) findViewById(R.id.viewAllActivities);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Intent to headcount activity
                Intent intent = new Intent(ActivityListActivity.this, HeadCountActivity.class);
                intent.putExtra("ACTIVITY_ID", activityList.get(position).getId());
                startActivity(intent);
            }
        });

        //Gets the list of all the seminars
        new GetAllActivitiesTask().execute();
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
    private void updateActivitiesList(ArrayList<ConferenceActivity> list)
    {
        activityList.clear();
        activityList.addAll(list);

        //Displays the activities
        ListView listView = (ListView) findViewById(R.id.viewAllActivities);
        listView.setAdapter(new ConferenceListAdapter(this, activityList));
    }

    /**
     * Gets all activities from the DB
     */
    private class GetAllActivitiesTask extends AsyncTask<Void, Void, ArrayList<ConferenceActivity>> {
        @Override
        protected ArrayList<ConferenceActivity> doInBackground(Void... params) {
            try {
                final String url = String.format("http://%s/api/activities", getString(R.string.server_address));
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

                //Retreives list of activities from the database as an array
                ConferenceActivity[] activities = restTemplate.getForObject(url, ConferenceActivity[].class);
                return new ArrayList<ConferenceActivity>(Arrays.asList(activities));
            } catch (Exception e) {
                Log.e("ActivityListActivity", e.getMessage(), e);
            }
            return null;
        }
        @Override
        protected void onPostExecute(ArrayList<ConferenceActivity> activities) {
            try{
                //Attempt to populate the ArrayList 
                updateActivitiesList(activities);
            }
            catch (Exception e){}
        }

    }

}

