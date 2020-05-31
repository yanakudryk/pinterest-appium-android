package screens;

import entities.User;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class LoginScreen extends BaseScreen {
    @AndroidFindBy(id = "com.discord:id/auth_login_email")
    private AndroidElement email;
    @AndroidFindBy(id = "com.discord:id/auth_login_password")
    private AndroidElement password;
    @AndroidFindBy(id = "com.discord:id/auth_login")
    private AndroidElement loginButton;

    public LoginScreen(AndroidDriver<AndroidElement> driver) {
        super(driver);
    }

    public void login(User user){
        action.inputText(email, user.email());
        action.inputText(password, user.password());
        action.tapButton(loginButton);
    }
}
