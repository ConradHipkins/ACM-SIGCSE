package mobilecomp.acm_sigcse;

/**
 * Created by Liz and Natalie on 11/3/15. This is a getter and setter class for the ID,
 * activity number and activity name.
 */
public class ConferenceActivity {
    private int id;
    private String number;
    private String name;


    //Returns the ID of an activity
    public int getId()
    {
        return id;
    }
    //Returns the activity number
    public String getNumber()
    {
        return number;
    }
    //Returns the activity name
    public String getName()
    {
        return name;
    }
    //Sets the ID to an int
    public void setId(int i)

    {
        id = i;
    }
    //Sets the activity name to a string
    public void setNumber(String s)

    {
        number = s;
    }
    //Sets the activity name to a string
    public void setName(String s)

    {
        name = s;
    }
}
