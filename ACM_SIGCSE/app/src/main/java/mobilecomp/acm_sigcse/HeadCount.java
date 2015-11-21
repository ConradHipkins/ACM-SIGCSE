package mobilecomp.acm_sigcse;

import java.sql.Timestamp;
import java.util.Date; // this might be problem

/**
 * Created by Natalie on 11/19/15.
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
