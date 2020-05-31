package screens;

import entities.User;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class RegisterScreen extends BaseScreen {
    @AndroidFindBy(id = "com.discord:id/auth_register_username")
    private AndroidElement username;
    @AndroidFindBy(id = "com.discord:id/auth_register_email")
    private AndroidElement email;
    @AndroidFindBy(id = "com.discord:id/auth_register_password")
    private AndroidElement password;
    @AndroidFindBy(id = "com.discord:id/auth_register_button")
    private AndroidElement createAccountButton;
    @AndroidFindBy(id = "com.discord:id/auth_tos_opt_in")
    private AndroidElement agree;
    public RegisterScreen(AndroidDriver<AndroidElement> driver) {
        super(driver);
    }

    public void register(User user){
        action.inputText(username, user.username());
        action.inputText(email, user.email());
        action.inputText(password, user.password());
        if(driver.isKeyboardShown()) {
            driver.hideKeyboard();
        }
        try{
            action.setCheckBox(agree, true);
        }
        catch (Exception ex){
            logger.info("Agree check box is not displayed");
        }

        action.tapButton(createAccountButton);
    }
}
