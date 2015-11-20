package mobilecomp.acm_sigcse;

import java.util.Date; // this might be problem

/**
 * Created by Natalie on 11/19/15.
 */
public class HeadCount {
    private int id;
    private int activityId;
    private int headCount;
    private Date timeStamp;

    public int getId()
    {
       return id;
    }
    public int getActivityId()
    {
        return activityId;
    }
    public int getHeadCount()
    {
        return headCount;
    }
    public Date getTimeStamp()
    {
        return timeStamp;
    }

    public void setId(int i)
    {
        id = i;
    }
    public void setActivityId(int i)
    {
        activityId = i;
    }
    public void setHeadCount(int i)
    {
        headCount = i;
    }
    public void setTimeStamp(Date d)
    {
        timeStamp = d;
    }
}
