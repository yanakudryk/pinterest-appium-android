package screens;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.touch.offset.PointOption;

public class ChannelScreen extends BaseScreen{
    @AndroidFindBy(id = "com.discord:id/flex_input_text_input")
    private AndroidElement inputField;

    @AndroidFindBy(id = "com.discord:id/flex_input_send_btn_image")
    private AndroidElement sendButton;

    @AndroidFindBy(id = "com.discord:id/chat_list_adapter_item_text")
    private AndroidElement lastMessageInChannel;

    public ChannelScreen(AndroidDriver<AndroidElement> driver) {
        super(driver);
    }

    public void sendMessageToChannel(String message){
        action.inputText(inputField, message);
        action.tapButton(sendButton);
    }

    public String getLastMessageInChannel(){
        return action.getText(lastMessageInChannel);
    }

    public void openMenu(){
        TouchAction touchAction = new TouchAction(driver);

        PointOption startPoint = new PointOption();
        startPoint.withCoordinates(10, 900).build();
        PointOption endPoint = new PointOption();
        endPoint.withCoordinates(300,0).build();
        touchAction.press(startPoint).moveTo(endPoint).release().perform();
    }
}
