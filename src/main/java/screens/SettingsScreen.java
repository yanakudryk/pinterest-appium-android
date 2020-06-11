package screens;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class SettingsScreen extends BaseScreen {

    @AndroidFindBy(xpath = "//*[@text='Edit profile']")
    private AndroidElement editProfile;

    public SettingsScreen(AndroidDriver<AndroidElement> driver) {
        super(driver);
    }

    public void editProfile(){
        action.tapButton(editProfile);
    }

}
