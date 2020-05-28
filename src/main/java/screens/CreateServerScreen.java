package screens;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.aspectj.weaver.ast.And;

public class CreateServerScreen extends BaseScreen {

    @AndroidFindBy(id = "com.discord:id/nux_guild_template_section_top")
    private AndroidElement createTitle;
    @AndroidFindBy(id = "com.discord:id/nux_guild_template_action_create")
    private AndroidElement createMyOwn;

    @AndroidFindBy(id = "com.discord:id/guild_create_icon_uploader")
    private AndroidElement uploadPhoto;
    @AndroidFindBy(id = "com.android.packageinstaller:id/permission_allow_button")
    private AndroidElement allowAccessToPhoto;
    @AndroidFindBy(id = "com.android.packageinstaller:id/permission_allow_button")
    private AndroidElement allowAccessToLibrary;

    @AndroidFindBy(id = "com.discord:id/loading_button_button")
    private AndroidElement continueButton;

    @AndroidFindBy(id = "com.discord:id/nuf_channel_prompt_topic")
    private AndroidElement topic;

    @AndroidFindBy(id = "com.discord:id/screen_title_title")
    private AndroidElement finalTitle;

    @AndroidFindBy(xpath = "//android.widget.ImageButton[@content-desc=\"Close\"]")
    private AndroidElement closeShareServerLinkScreen;

    public CreateServerScreen(AndroidDriver<AndroidElement> driver) {
        super(driver);
    }

    public String getTitleText(){
        return action.getText(createTitle);
    }

    public void setUpServer(String topic){
        action.tapButton(createMyOwn);
        action.tapButton(continueButton);
        action.inputText(this.topic, topic);
        action.tapButton(continueButton);
    }

    public String getFinalTitle(){
        return action.getText(finalTitle);
    }

    public void closeShareServerLinkScreen(){
        action.tapButton(closeShareServerLinkScreen);
    }
}
