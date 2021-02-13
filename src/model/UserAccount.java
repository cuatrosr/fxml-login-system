package model;

public class UserAccount {

    private String username;
    private String password;
    private String profilePath;
    private String gender;
    private String career;
    private String birthday;
    private String fvBrowser;

    public UserAccount(String username, String password, String profilePath, String gender, String career, String birthday, String fvBrowser) {
        this.username = username;
        this.password = password;
        this.profilePath = profilePath;
        this.gender = gender;
        this.career = career;
        this.birthday = birthday;
        this.fvBrowser = fvBrowser;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getPassword(){
        return password;
    }

    public String getProfilePath() {
        return profilePath;
    }

    public String getGender() {
        return gender;
    }

    public String getCareer() {
        return career;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getFvBrowser() {
        return fvBrowser;
    }
}
