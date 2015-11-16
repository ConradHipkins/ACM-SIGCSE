package mobilecomp.acm_sigcse;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.os.AsyncTask;


import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Author: Luis Diniz
 * Version 4: 11/12/2015
 * Description: Simple class able to submit th headcount to the database
 */

public class HeadCountActivity extends Activity {
    private Button submit;
    private int headCount;
    private EditText numParticipant;
    private Seminar seminar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.head_counter);

        seminar = new Seminar();

        Intent intent = getIntent();
        seminar.setId(intent.getIntExtra("SEMINAR_ID", 0));
        seminar.setSemName(intent.getStringExtra("SEMINAR_NAME"));
        seminar.setSemNum(intent.getStringExtra("SEMINAR_NUMBER"));
        seminar.setHeadCount(intent.getIntExtra("SEMINAR_HEADCOUNT", 0));

        //Text Fields (Edit) - Just using the text field for tests
        numParticipant = (EditText) findViewById(R.id.numParticipant);
    }

    public void onClickSubmit(View view)
    {
        //Method able to get the headcount number from the user and call the function to submit it to the DB
        headCount = Integer.parseInt(numParticipant.getText().toString());
        new PostSeminarTask().execute();

        //Test the return of the method and then show the toast
        //basicToast();
    }

    //Method able to show a toast and inform the user the data was successfully submitted
    public void basicToast()
    {
        Context context = getApplicationContext();
        CharSequence text = "Data successfully submitted";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
    //This is the private Async class used for the HttpTask
    private class PostSeminarTask extends AsyncTask<Void, Void, String>
    {
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
                final String url = String.format("http://%s/api/seminars/%s", getString(R.string.server_address), Integer.toString(seminar.getId()));
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

                seminar.setHeadCount(headCount);

                //Send the request, save the result, then return it.
                String response = restTemplate.postForObject(url,seminar,String.class);
                return response;

            }
            catch (Exception E)
            {
            }
            return null;
        }


    }

}
