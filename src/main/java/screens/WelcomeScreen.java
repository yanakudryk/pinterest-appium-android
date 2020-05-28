package screens;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class WelcomeScreen extends BaseScreen {

    @AndroidFindBy(id = "com.discord:id/auth_landing_register")
    private AndroidElement register;
    @AndroidFindBy(id = "com.discord:id/auth_landing_login")
    private AndroidElement login;

    public WelcomeScreen(AndroidDriver<AndroidElement> driver) {
        super(driver);
    }

    public void tapRegister(){
        action.tapButton(register);
    }
    public void tapLogin(){
        action.tapButton(login);
    }

}
