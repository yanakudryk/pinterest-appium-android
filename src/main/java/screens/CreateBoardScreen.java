package screens;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AndroidFindAll;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class CreateBoardScreen extends BaseScreen {
    @AndroidFindBy(xpath = "//android.widget.LinearLayout[@content-desc='Create a board…']")
    private AndroidElement createBoardButton;
    @AndroidFindAll(value = {
            @AndroidBy(id = "com.pinterest:id/board_name_et"),
            @AndroidBy(id = "com.pinterest:id/board_name_edittext")})
    private AndroidElement boardName;
    @AndroidFindBy(id = "com.pinterest:id/create_btn")
    private AndroidElement createButton;

    public CreateBoardScreen(AndroidDriver<AndroidElement> driver) {
        super(driver);
    }

    public void createBoard(String name){
        if(action.isDisplayedElement(createBoardButton)){
            action.tapButton(createBoardButton);
        }
        action.inputText(boardName, name);
        action.tapButton(createButton);
    }
}
