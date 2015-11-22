package mobilecomp.acm_sigcse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/***
 * The entrance point for the app, providing a mock login function. Cbecks the username and
 * password before allowing the user access to the acitivies list
 *
 * @author Liz, Luis, Natalie
 * @version 11/22/15
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        //If username and password are valid, send to activities page
        if (validUserName(userName) && validPassword(password)) {
            Intent i = new Intent();
            i.setClass(this, ActivityListActivity.class);
            //TODO Actually validate login
            startActivity(i);
        }
    }

    /**
     * Check length of username and show error if is null or empty
     * @param e
     * @return
     */
    private boolean validUserName(EditText e)
    {
        if (e.getText().length() == 0 || e.getText() == null)
        {
            e.setError("Username required");
            return false;
        }
        return true;
    }

    /**
     * Check length of password and show error if null or empty
     * @param e
     * @return
     */
    private boolean validPassword(EditText e)
    {
        if (e.getText().length() == 0 || e.getText() == null)
        {
            e.setError("Password required");
            return false;
        }
        return true;
    }
}
