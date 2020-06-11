import entities.User;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

import static constants.Constants.*;
import static constants.Constants.COUNTRY;

public class SettingsTests extends BaseTests{
    @Test
    public void testEditProfileAvatar() throws IOException {
        User user = new User(
                EMAIL,
                PASSWORD,
                FULL_NAME,
                AGE,
                GENDER,
                COUNTRY);

        driver.pushFile("/mnt/sdcard/Pictures/" + usFaker.lorem().fixedString(5) + ".jpg",
                new File("C:/Users//akade//IdeaProjects//pinterest-android-automation//resources//images//yellow-cosmos-flower-close-up-photography-1212487.jpg"));

        welcomeScreen.continueWithEmail(user);
        loginScreen.login(user);
        mainScreen.openSaved();
        savedScreen.openProfile();
        savedScreen.openOptions();
        settingsScreen.editProfile();
        editProfileScreen.editProfilePictureFromGallery();


    }
}
