package mobilecomp.acm_sigcse;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;


public class MainActivity extends AppCompatActivity {

    private Account account;
    private boolean loggedIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loggedIn = false;
        account = new Account();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void onClick(View view){
        EditText userName = (EditText) findViewById(R.id.username);
        EditText password = (EditText) findViewById(R.id.password);

           if (validUserName(userName) && validPassword(password)) {

               //set accout model data.
               account.setPassword(password.getText().toString());
               account.setUserName(userName.getText().toString());

               new ValidateLogin().execute();

           }
       }



    public void startIntent(boolean loggedIn, String res){
        //if credentials are valid, startup a new activity.
        loggedIn = true; //strange server error, demo code. 
       if(loggedIn) {
           Intent i = new Intent();
           i.setClass(this, ActivityListActivity.class);
           startActivity(i);
       }
       //if not, display invalid login message.
       else{
           TextView t = (TextView)findViewById(R.id.invalid_login);
           t.setText(res);
           t.setVisibility(View.VISIBLE);
       }
    }

    private boolean validUserName(EditText e)
    {
        if (e.getText().length() == 0 || e.getText() == null)
        {
            e.setError("Username required");
            return false;
        }
        return true;
    }

    private boolean validPassword(EditText e)
    {
        if (e.getText().length() == 0 || e.getText() == null)
        {
            e.setError("Password required");
            return false;
        }
        return true;
    }

    private class ValidateLogin extends AsyncTask<Void, Void, String>{
        /*Post username and password to the database*/

      protected String doInBackground(Void... params) {
          try {

              final String url = String.format("http://%s/api/signin", getString(R.string.server_address));
              RestTemplate restTemplate = new RestTemplate();
              restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
              restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

              String response = restTemplate.postForObject(url, account,String.class);
              return response;

          } catch (Exception e) {

              Log.e("MainActivity", e.getMessage(), e);
          }
        return null;
      }

    protected void onPostExecute(String response){

       //*if the response from the server is correct, go to the Activity list;

        if(response.equals("User Validated")){
            loggedIn = true;
        }
        startIntent(loggedIn, response);
    }

    }
}
