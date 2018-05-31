package exceptions;

/**
 * it throws if not shown message 'Username or password is incorrect'
 */
public class NoMessage_UsernameOrPasswordIsIncorrect extends RuntimeException{
    public NoMessage_UsernameOrPasswordIsIncorrect(String login, String password) {
        super("No message 'Username or password is incorrect': x_Login-" + login + ", password-" + password);
    }
}
