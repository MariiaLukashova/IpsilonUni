package businessobject;

/**
 * Created by hp on 12.07.2017.
 */
public class User{
    private String login;
    private String password;
    public User(String login, String password){
        this.login=login;
        this.password=password;
    }
    public String getLogin(){return this.login;}
    public String getPassword(){return this.password;}
}
