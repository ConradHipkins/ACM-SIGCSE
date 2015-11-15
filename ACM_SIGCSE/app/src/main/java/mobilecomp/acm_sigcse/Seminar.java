package mobilecomp.acm_sigcse;

/**
 * Created by Liz and Natalie on 11/3/15. This is a getter and setter class for the ID, head count,
 * seminar number and seminar name.
 */
public class Seminar {
    private int id;
    private int headCount;
    private String semNum;
    private String semName;


    //Returns the ID of a seminar
    public int getId()
    {
        return id;
    }
    //Returns the headcount
    public int getHeadCount()
    {
        return headCount;
    }
    //Returns the seminar number
    public String getSemNum()
    {
        return semNum;
    }
    //Returns the seminar name
    public String getSemName()
    {
        return semName;
    }
    //Sets the ID to an int
    public void setId(int i)

    {
        id = i;
    }
    //Sets the headcount to an int
    public void setHeadCount(int i)

    {
        headCount = i;
    }
    //Sets the seminar name to a string
    public void setSemNum(String s)

    {
        semNum = s;
    }
    //Sets the seminar name to a string
    public void setSemName(String s)

    {
        semName = s;
    }
}
