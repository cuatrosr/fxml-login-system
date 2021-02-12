package model;

import java.util.ArrayList;
import java.util.List;

public class Classroom {
    
    private List<UserAccount> accounts;
    
    public Classroom(){
        accounts = new ArrayList<UserAccount>();
    }
    
    public void addUsers(String username, String password, String profilePath, String gender, String career, String birthday, String fvBrowser) {
        accounts.add(new UserAccount(username, password, profilePath, gender, career, birthday, fvBrowser));
    }

    public List<UserAccount> getAccounts() {
        return accounts;
    }
}
