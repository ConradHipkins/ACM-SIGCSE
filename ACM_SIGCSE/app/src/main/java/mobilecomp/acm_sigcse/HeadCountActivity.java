package mobilecomp.acm_sigcse;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.os.AsyncTask;


import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

/**
 * Author: Luis Diniz
 * Version 4: 11/12/2015
 * Description: Simple class able to submit th headcount to the database
 */

public class HeadCountActivity extends Activity implements View.OnClickListener {
    private Button submit;
    private int nParticipant;
    private EditText numParticipant;
    private TextView rem;
    String X;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.head_counter);
        //Text Fields (Edit) - Just using the text field for tests
        numParticipant = (EditText) findViewById(R.id.numParticipant);
        rem = (TextView) findViewById(R.id.rem); //Get rid of it later
    }

    @Override
    public void onClick(View view)
    {
        //Method able to get the headcount number from the user and call the function to submit it to the DB
        X = numParticipant.getText().toString();
        nParticipant = Integer.parseInt(X);
        //Call the post method here
        rem.setText("  Head Count" + nParticipant); //Just testing if I'm getting the number
        //Test the return of the method and then show the toast
        basicToast(view);
    }

    //Method able to show a toast and inform the user the data was successfully submitted
    public void basicToast(View view)
    {
        Context context = getApplicationContext();
        CharSequence text = "Data successfully submitted";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
    //This is the private Async class used for the HttpTask
    private class PostDogTask extends AsyncTask<Void, Void, String>
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
                final String url = String.format("http://%s/api/seminars", getString(R.string.server_address));

                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                Seminar[] seminar = restTemplate.getForObject(url, Seminar[].class);

                //Send the request, save the result, then return it.
                String response = restTemplate.postForObject(url,headers,String.class);
                return response;

            }
            catch (Exception E)
            {
            }
            return null;
        }


    }

}
