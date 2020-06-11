package screens;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class EditProfileScreen extends BaseScreen {
    @AndroidFindBy(xpath = "//android.widget.ImageView[@resource-id='com.pinterest:id/edit_profile_avatar_view_picture']")
    private AndroidElement avatar;

    @AndroidFindBy(id = "com.pinterest:id/update_picture")
    private AndroidElement updateProfilePicture;

    @AndroidFindBy(xpath = "//*[@text=\"From your device's gallery\"]")
    private AndroidElement fromGallery;

    @AndroidFindBy(id = "com.android.permissioncontroller:id/permission_allow_button")
    private AndroidElement allowAccessToGallery;

    @AndroidFindBy(xpath = "//*[@text='Pictures']")
    private AndroidElement folderPictures;

    @AndroidFindBy(xpath = "//*[@resource-id='com.google.android.apps.photos:id/recycler_view']/android.view.ViewGroup[2]")
    private AndroidElement photo;

    public EditProfileScreen(AndroidDriver<AndroidElement> driver) {
        super(driver);
    }

    public void editProfilePictureFromGallery(){
        action.tapButton(avatar);
        action.tapButton(updateProfilePicture);
        action.tapButton(fromGallery);
        action.tapButton(allowAccessToGallery);
        action.tapButton(folderPictures);
        action.tapButton(photo);
    }
}
