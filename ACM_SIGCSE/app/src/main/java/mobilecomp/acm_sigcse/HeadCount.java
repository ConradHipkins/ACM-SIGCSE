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

    public int getHeadCountID()
    {
       return headCountID;
    }
    public int getActivityID()
    {
        return activityID;
    }
    public int getHeadCount()
    {
        return headCount;
    }
    public String getTimeAdded()
    {
        return timeAdded;
    }

    public void setHeadCountID(int i)
    {
        headCountID = i;
    }
    public void setActivityID(int i)
    {
        activityID = i;
    }
    public void setHeadCount(int i)
    {
        headCount = i;
    }
    public void setTimeAdded(String d)
    {
        timeAdded = d;
    }
}
