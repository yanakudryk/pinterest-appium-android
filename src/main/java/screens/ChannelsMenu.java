package screens;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

import java.util.List;

public class ChannelsMenu extends BaseScreen{
    @AndroidFindBy(xpath = "//*[@resource-id='com.discord:id/channels_item_channel_name']")
    private List<AndroidElement> channels;

    public ChannelsMenu(AndroidDriver<AndroidElement> driver) {
        super(driver);
    }

    public void openChanel(String name){
        for (int i = 0; i < channels.size(); i++) {
            if(channels.get(i).getAttribute("text").equals(name)){
                action.tapButton(channels.get(i));
                break;
            }
        }
    }
}
