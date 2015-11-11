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
 * Version 3: 11/03/2015
 * Description: Simple class able to calculate how many spots are letf in certain seminar and show it together with maximum capacity of it
 */

public class HeadCountActivity extends Activity implements View.OnClickListener {
    private Button ok, submit;
    private int capacity, remaining, nParticipant;
    private EditText numParticant;
    private TextView rem, maximum;
    String X;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.head_counter);
        init();
        //The capacity will be available on the database
        capacity = this.setCapacity(100);
        maximum.setText("Maximum "+String.valueOf(capacity));
    }

    //Method able to initialize all the items used on the UI
    public void init()
    {
        //Buttons
        ok = (Button) findViewById(R.id.ok);

        //Text Fields (Edit)
        numParticant = (EditText) findViewById(R.id.numParticipant);

        //Text views
        rem = (TextView) findViewById(R.id.rem);
        maximum = (TextView) findViewById(R.id.maximum);

        ok.setOnClickListener(this);
    }

    //Method should get the capacity of the seminar from the database
    public int setCapacity(int num)
    {
        capacity = num;
        return capacity;
    }

    //Calculate how many spots are left in a seminar
    public int spotsLeft(String S)
    {
        S = numParticant.getText().toString();
        nParticipant = Integer.parseInt(S);
        remaining = capacity - nParticipant;
        return  remaining;
    }

    @Override
    public void onClick(View view)
    {
        X = numParticant.getText().toString();
        remaining = this.spotsLeft(X);

        switch (view.getId()) {
            case R.id.ok:
                rem.setText("Remaining " + String.valueOf(remaining));
                break;

        }
    }

    //Method able to show a toast and inform the user if the data was successfully submitted
    public void basicToast(View view)
    {
        //This method is set in the attribute onClick of the button submit, once we get access to the database it's necessary
        //to test if the data was submitted first and then show the toast
        Context context = getApplicationContext();
        CharSequence text = "Data submitted successfully";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        //Do the test before calling the method bellow
        toast.show();

    }

}
