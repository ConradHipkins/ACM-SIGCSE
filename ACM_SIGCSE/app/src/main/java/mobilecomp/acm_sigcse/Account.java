package mobilecomp.acm_sigcse;

/**
 * Created by Conrad on 11/21/2015.
 */
public class Account {

    /* Model class for user logins*/

    private String username;
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {

        return username;
    }

    public void setUserName(String userName) {
        this.username = userName;
    }
}
