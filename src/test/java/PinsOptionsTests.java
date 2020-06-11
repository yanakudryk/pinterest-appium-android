import entities.User;
import org.testng.Assert;
import org.testng.annotations.Test;

import static constants.Constants.*;
import static constants.Constants.COUNTRY;

public class PinsOptionsTests extends BaseTests{
    @Test
    public void testSavePin(){
        User user = new User(
                EMAIL,
                PASSWORD,
                FULL_NAME,
                AGE,
                GENDER,
                COUNTRY);
        String boardName = usFaker.cat().name();

        welcomeScreen.continueWithEmail(user);
        loginScreen.login(user);
        mainScreen.saveImage();
        createBoardScreen.createBoard(boardName);
        Assert.assertEquals(mainScreen.getSaveBoardName(), boardName, "Unexpected board name.");
    }

    @Test
    public void testHidePin(){
        User user = new User(
                EMAIL,
                PASSWORD,
                FULL_NAME,
                AGE,
                GENDER,
                COUNTRY);

        welcomeScreen.continueWithEmail(user);
        loginScreen.login(user);
        mainScreen.hideImage();
        mainScreen.selectHidingReason("Not relevant to me");
        Assert.assertEquals(mainScreen.getHideText(), "Got it! We won't show you this Pin in the future. Tune feed", "Image was not correctly hidden.");
    }
}
