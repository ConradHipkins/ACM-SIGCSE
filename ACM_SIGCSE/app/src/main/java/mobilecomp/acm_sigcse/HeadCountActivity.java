package mobilecomp.acm_sigcse;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.os.AsyncTask;


import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        headCount.setActivityId(intent.getIntExtra("ACTIVITY_ID", -1));

        //Text Fields (Edit) - Just using the text field for tests
        numParticipant = (EditText) findViewById(R.id.numParticipant);
    }

    public void onClickSubmit(View view)
    {
        //Method able to get the headcount number from the user and call the function to submit it to the DB
        headCountNumber = Integer.parseInt(numParticipant.getText().toString());
        //new PostHeadCountTask().execute();
    }

    private void populatePreviousHeadcounts()
    {
        ListView listView = (ListView) findViewById(R.id.previous_headcounts);
        listView.setAdapter(new HeadCountListAdapter(getApplicationContext(), prevHeadCountList));
    }

    //This is the private Async class used for the HttpTask
    private class PostHeadCountTask extends AsyncTask<Void, Void, String>
    {
        @Override
        protected void onPreExecute()
        {
            headCount.setHeadCount(345);
            headCount.setTimeStamp(new Date());
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
                final String url = String.format("http://%s/api/headcounts/%s", getString(R.string.server_address), Integer.toString(headCount.getId()));
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

                //Send the request, save the result, then return it.
                String response = restTemplate.postForObject(url,headCount,String.class);
                return response;
            }
            catch (Exception E)
            {
            }
            return null;
        }


    }

}
