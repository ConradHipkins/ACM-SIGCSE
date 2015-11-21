package mobilecomp.acm_sigcse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.os.AsyncTask;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

/**
 * Author: Luis Diniz
 * Version 4: 11/12/2015
 * Description: Simple class able to submit th headcount to the database
 */

public class HeadCountActivity extends Activity {
    private Button submit;
    private int headCountNumber;
    private EditText numParticipant;
    private HeadCount headCount;
    private ArrayList<HeadCount> prevHeadCountList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_head_counter);

        prevHeadCountList = new ArrayList<HeadCount>();
        headCount = new HeadCount();

        Intent intent = getIntent();
        headCount.setActivityID(intent.getIntExtra("ACTIVITY_ID", -1));

        //Text Fields (Edit) - Just using the text field for tests
        numParticipant = (EditText) findViewById(R.id.numParticipant);

        new GetAllHeadcountsTask().execute();
    }

    public void onClickSubmit(View view)
    {
        //Method able to get the headcount number from the user and call the function to submit it to the DB
        if (numParticipant.getText().length() > 0)
        {
            headCountNumber = Integer.parseInt(numParticipant.getText().toString());
            new PostHeadCountTask().execute();
        }
        else
        {
            numParticipant.setError("Please enter a headcount");
        }
    }

    private void populatePreviousHeadcounts(ArrayList<HeadCount> list)
    {
        prevHeadCountList.clear();
        prevHeadCountList.addAll(list);

        ListView listView = (ListView) findViewById(R.id.previous_headcounts);
        listView.setAdapter(new HeadCountListAdapter(getApplicationContext(), prevHeadCountList));
    }

    private class GetAllHeadcountsTask extends AsyncTask<Void, Void, ArrayList<HeadCount>>
    {
        @Override
        protected ArrayList<HeadCount> doInBackground(Void... params)
        {
            try {
                final String url = String.format("http://%s/api/headcounts/%s", getString(R.string.server_address), headCount.getActivityID());
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                HeadCount[] headCounts = restTemplate.getForObject(url, HeadCount[].class);
                return new ArrayList<HeadCount>(Arrays.asList(headCounts));
            }
            catch (Exception e)
            {
                Log.e("HeadCountActivity", e.getMessage(), e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(ArrayList<HeadCount> headCounts)
        {
            try
            {
                populatePreviousHeadcounts(headCounts);
            }
            catch (Exception e) {
                Log.e("HeadCountActivity", e.getMessage(), e);
            }
        }
    }

    //TODO Show when successful, take back to thing? idk?

    //This is the private Async class used for the HttpTask
    private class PostHeadCountTask extends AsyncTask<Void, Void, String>
    {
        @Override
        protected void onPreExecute()
        {
            Calendar c = Calendar.getInstance();
            Timestamp ts = new Timestamp(c.getTime().getTime());

            //Set headcount number
            headCount.setHeadCount(headCountNumber);
        }

        /**
         * This method submits the call to the API to post the dog to the server using url parameters.
         * It does this by building a new RestTemplate with the Spring Framework and then sending
         * a post request.
         *
         * @param params
         * @return
         */
        @Override
        protected String doInBackground(Void... params)
        {
            try{
                //Build the url
                final String url = String.format("http://%s/api/activities/%d", getString(R.string.server_address),headCount.getActivityID());
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

                //Send the request, save the result, then return it.
                String response = restTemplate.postForObject(url,headCount,String.class);
                return response;
            }
            catch (Exception e)
            {
                Log.e("HeadCountActivity",e.getMessage(),e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(String response)
        {
            new GetAllHeadcountsTask().execute();
        }
    }
}
