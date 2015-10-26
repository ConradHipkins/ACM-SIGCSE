package mobilecomp.acm_sigcse;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Author: Luis Diniz
 * Version 2: 10/26/2015
 * Description: Simple class able to calculate how many spots are letf in certain seminar and show it together with maximum capacity of it
 */

public class headcount extends Activity implements View.OnClickListener {
    private Button ok, submit;
    private int capacity, remaining, nParticipant;
    private EditText numParticant;
    private TextView rem, maximum;
    String X;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.head_counter);
        init();
        //The capacity will be available on the database
        capacity = this.setCapacity(100);
        maximum.setText("Maximum "+String.valueOf(capacity));
    }

    public void init(){
        //Buttons
        ok = (Button) findViewById(R.id.ok);
        submit = (Button) findViewById(R.id.submit);

        //Text Fields (Edit)
        numParticant = (EditText) findViewById(R.id.numParticipant);

        //Text views
        rem = (TextView) findViewById(R.id.rem);
        maximum = (TextView) findViewById(R.id.maximum);

        ok.setOnClickListener(this);
        submit.setOnClickListener(this);
    }

    public int setCapacity(int num)
    {
        //Method should get the capacity of the seminar from the database
        capacity = num;
        return capacity;
    }

    public int spotsLeft(String S){
        //Calculate how many spots are left in a seminar
        S = numParticant.getText().toString();
        nParticipant = Integer.parseInt(S);
        remaining = capacity - nParticipant;
        return  remaining;
    }

    @Override
    public void onClick(View view){

        X = numParticant.getText().toString();
        remaining = this.spotsLeft(X);

        switch (view.getId()) {
            case R.id.ok:
                rem.setText("Remaining " + String.valueOf(remaining));
                break;
        }
    }
}
