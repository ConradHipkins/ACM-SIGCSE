package mobilecomp.acm_sigcse;

/**
 * Model class for the HeadCount data object
 * @version 11/21/15
 * @author Natalie Davenport
 */
public class HeadCount {
    private int headCountID;
    private int activityID;
    private int headCount;
    private String timeAdded;

    //Returns the ID for each head count taken
    public int getHeadCountID()
    {
       return headCountID;
    }

    //Returns the ID for an activity
    public int getActivityID()
    {
        return activityID;
    }

    //Returns the overall headcount
    public int getHeadCount()
    {
        return headCount;
    }

    //Returns the time a headcount was added
    public String getTimeAdded()
    {
        return timeAdded;
    }

    //Sets the headcount ID to an int
    public void setHeadCountID(int i)
    {
        headCountID = i;
    }
    //Sets the headcount ID to an int
    public void setActivityID(int i)
    {
        activityID = i;
    }
    //Sets the activity ID to an int
    public void setHeadCount(int i)
    {
        headCount = i;
    }
    //Sets the time a headcount was added
    public void setTimeAdded(String d)
    {
        timeAdded = d;
    }
}
