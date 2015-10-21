/**
 * Author: Luis Diniz
 * Version 1: 10/21/2015
 * Description: Simple class able to calculate how many spots are letf in certain seminar and show it together with maximum capacity of it
 */

package mobilecomp.acm_sigcse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.view.View;
import android.widget.TextView;

public class counter extends AppCompatActivity implements View.OnClickListener {

    private int capacity, remaining, nParticipant;
    private EditText numParticant;
    private TextView rem, maximum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.head_counter);
        init();
    }

    public void init(){
        //Text Fields (Edit)
        numParticant = (EditText) findViewById(R.id.numParticipant);

        //Text views
        rem = (TextView) findViewById(R.id.rem);
        maximum = (TextView) findViewById(R.id.maximum);
    }

    //Method should get the capacity of the seminar from the database
    public int setCapacity(int num)
    {
        capacity = num;
        return capacity;
    }

    //Calculate how many spots are left in a seminar
    public int spotsLeft(){
        String X = numParticant.getText().toString();
        nParticipant = Integer.parseInt(X);
        remaining = capacity - nParticipant;
        return  remaining;
    }

    //Method able to show in the layout the calculated variables
    public void show(){
        rem.setText(String.valueOf(remaining));
        maximum.setText(String.valueOf(capacity));
    }

    @Override
    public void onClick(View view){

    }
}
