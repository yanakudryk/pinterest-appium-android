package screens;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import utils.ActionWithElements;

public class BaseScreen {
    public AndroidDriver<AndroidElement> driver;
    public Logger logger;
    public ActionWithElements action;

    public BaseScreen(AndroidDriver<AndroidElement> driver) {

        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        logger = Logger.getLogger(getClass());
        action = new ActionWithElements(driver);
    }

}
