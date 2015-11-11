package mobilecomp.acm_sigcse;

/**
 * Created by Liz on 11/3/15.
 */
public class Seminar {
    private int id;
    private int headCount;
    private String seminarNumber;
    private String seminarName;


    
    public int getId(){
        return id;
    }
    public int getHeadCount(){
        return headCount;
    }
    public String getSeminarNumber(){
        return seminarNumber;
    }
    public String getSeminarName(){
        return seminarName;
    }

    public void setId(int i)
    {
        id = i;
    }
    public void setHeadCount(int i)
    {
        headCount = i;
    }
    public void setSeminarNumber(String s)
    {
        seminarNumber = s;
    }
    public void setSeminarName(String s)
    {
        seminarName = s;
    }
}
