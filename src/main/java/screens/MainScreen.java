package screens;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AndroidFindBys;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;

import java.util.List;

public class MainScreen extends BaseScreen {
    @AndroidFindBy(id = "com.pinterest:id/bottom_nav_bar")
    private AndroidElement navBar;

    @AndroidFindBy(xpath = "//android.widget.FrameLayout[@content-desc='Saved']")
    private AndroidElement saved;

    @AndroidFindAll(value = {
            @AndroidBy(xpath = "//*[@resource-id='com.pinterest:id/recycler_adapter_view']/android.view.View"),
            @AndroidBy(xpath = "//*[@resource-id='com.pinterest:id/recycler_adapter_view']/android.widget.FrameLayout")})
    private List<WebElement> images;
    
    @AndroidFindBy(xpath = "//*[@resource-id='com.pinterest:id/cell_title']")
    private List<WebElement> hidingReasons;
    
    @AndroidFindBy(xpath = "//android.widget.LinearLayout[@content-desc='Hide']/android.widget.ImageView")
    private AndroidElement hide;
    @AndroidFindBy(xpath = "//android.widget.LinearLayout[@content-desc='Send']/android.widget.ImageView")
    private AndroidElement send;
    @AndroidFindBy(xpath = "//android.widget.LinearLayout[@content-desc='Save']/android.widget.ImageView")
    private AndroidElement save;

    @AndroidFindBy(id = "com.pinterest:id/saved_board_name")
    private AndroidElement saveBoardName;

    @AndroidFindBy(id = "com.pinterest:id/hide_reason")
    private List<AndroidElement> hideText;

    public MainScreen(AndroidDriver<AndroidElement> driver) {
        super(driver);
    }

    public boolean checkNavBarIsDisplayed() {
        return action.isDisplayedElement(navBar);
    }

    public void saveImage() {
        action.waitList(images);
        action.longPressAndSelectOption(images.get(0), save);
    }

    public String getSaveBoardName() {
        return action.getText(saveBoardName);
    }

    public void hideImage() {
        action.waitList(images);
        action.longPressAndSelectOption(images.get(0), hide);
    }

    public void selectHidingReason(String reasonName){
        action.waitList(hidingReasons);
        for (WebElement reason :
                hidingReasons) {
            if(action.getText(reason).equals(reasonName)){
                action.tapButton(reason);
                break;
            }
        }
    }

    public String getHideText(){
        if(hideText.size() == 0){
            return "Image was not hidden";
        }
        if(hideText.size() > 1){
            return "More than one image was hidden.";
        }
        else return action.getText(hideText.get(0));
    }

    public void openSaved(){
        action.tapButton(saved);
    }
}
