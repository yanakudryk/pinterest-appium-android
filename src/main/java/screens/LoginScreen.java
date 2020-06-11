package screens;

import entities.User;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class LoginScreen extends BaseScreen{

    @AndroidFindBy(id = "com.pinterest:id/password")
    private AndroidElement password;

    @AndroidFindBy(id = "com.pinterest:id/login_bt")
    private AndroidElement loginButton;

    public LoginScreen(AndroidDriver<AndroidElement> driver) {
        super(driver);
    }

    public void login(User user){
        action.inputText(this.password, user.password());
        action.tapButton(loginButton);
    }
}
