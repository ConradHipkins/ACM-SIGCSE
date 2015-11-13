package mobilecomp.acm_sigcse;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

}
