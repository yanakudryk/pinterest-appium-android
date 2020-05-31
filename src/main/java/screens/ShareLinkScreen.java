package screens;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class ShareLinkScreen extends BaseScreen {
    @AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc=\"Close\"]")
    private AndroidElement closeShareServerLinkScreen;

    public ShareLinkScreen(AndroidDriver<AndroidElement> driver) {
        super(driver);
    }

    public void closeShareServerLinkScreen(){
        try {
            action.tapButton(closeShareServerLinkScreen);
        }
        catch (Exception ex){
            logger.error("Share server link screen is not displayed.");
        }
    }
}
