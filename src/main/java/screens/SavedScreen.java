package screens;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class SavedScreen extends BaseScreen {
    @AndroidFindBy(id = "com.pinterest:id/user_profile_collapsed_avatar_container")
    private AndroidElement profile;

    @AndroidFindBy(id = "com.pinterest:id/user_profile_options_icon")
    private AndroidElement options;

    public SavedScreen(AndroidDriver<AndroidElement> driver) {
        super(driver);
    }

    public void openProfile(){
        action.tapButton(profile);
    }

    public void openOptions(){
        action.tapButton(options);
    }
}
