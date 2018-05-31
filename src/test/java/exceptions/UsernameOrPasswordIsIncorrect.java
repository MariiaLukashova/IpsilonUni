package exceptions;

/**
 * it throws if username or password is incorrect
 */
public class UsernameOrPasswordIsIncorrect extends RuntimeException {
    public UsernameOrPasswordIsIncorrect(String login, String password) {
        super("Username or password is incorrect: x_Login-" + login + ", password-" + password);
    }
}
