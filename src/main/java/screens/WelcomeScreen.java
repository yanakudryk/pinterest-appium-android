package screens;

import entities.User;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class WelcomeScreen extends BaseScreen {
    @AndroidFindBy(id = "com.pinterest:id/email_address")
    private AndroidElement email;

    @AndroidFindBy(id = "com.pinterest:id/continue_email_bt")
    private  AndroidElement continueButton;

    public WelcomeScreen(AndroidDriver<AndroidElement> driver) {
        super(driver);
    }

    public void continueWithEmail(User user){
        action.inputText(email, user.email());
        action.tapButton(continueButton);
    }


}
