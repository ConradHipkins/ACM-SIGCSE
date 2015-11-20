package mobilecomp.acm_sigcse;

import java.sql.Date; // this might be problem

/**
 * Created by Natalie on 11/19/15.
 */
public class HeadCount {
    private int id;
    private int activityId;
    private int headCount;
    private Date timeStamp;

    private int getId()
    {
       return id;
    }
    private int getActivityId()
    {
        return activityId;
    }
    private int getHeadCount()
    {
        return headCount;
    }
    private Date getTimeStamp()
    {
        return timeStamp;
    }

    private void setId(int i)
    {
        id = i;
    }
    private void setActivityId(int i)
    {
        activityId = i;
    }
    private void setHeadCount(int i)
    {
        headCount = i;
    }
    private void setTimeStamp(Date d)
    {
        timeStamp = d;
    }
}
