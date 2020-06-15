import entities.User;
import io.appium.java_client.imagecomparison.FeaturesMatchingResult;
import io.appium.java_client.imagecomparison.OccurrenceMatchingResult;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;


import static constants.Constants.*;
import static constants.Constants.COUNTRY;


public class SettingsTests extends BaseTests{
    @Test
    public void testEditProfileAvatar() throws IOException, InterruptedException {
        User user = new User(
                EMAIL,
                PASSWORD,
                FULL_NAME,
                AGE,
                GENDER,
                COUNTRY);

        File originalImg = new File("C:/Users/akade/IdeaProjects/pinterest-android-automation/resources/images/avataaars.png");

        driver.pushFile("/mnt/sdcard/Pictures/" + usFaker.lorem().fixedString(5) + ".png", originalImg);

        welcomeScreen.continueWithEmail(user);
        loginScreen.login(user);
        mainScreen.openSaved();
        savedScreen.openProfile();
        savedScreen.openOptions();
        settingsScreen.editProfile();
        editProfileScreen.editProfilePictureFromGallery();

        String fileName = usFaker.artist().name();

        editProfileScreen.makeProfileScreenshotName(fileName);

        File screenshot = new File("C:/Users/akade/IdeaProjects/pinterest-android-automation/out/screenshots/" + fileName + ".png");

        OccurrenceMatchingResult result = editProfileScreen.getImageComparisonResults(screenshot, originalImg);

        Assert.assertTrue(result.getVisualization().length > 0);
        Assert.assertNotNull(result.getRect());
        /*
        Assert.assertTrue(result.getCount() > 0);
        Assert.assertTrue(result.getTotalCount() > 0);
        Assert.assertFalse(result.getPoints1().isEmpty());
        Assert.assertFalse(result.getPoints2().isEmpty());
        Assert.assertNotNull(result.getRect2());

         */
    }
}
