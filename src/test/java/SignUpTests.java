import com.github.javafaker.Faker;
import constants.Constants;
import entities.User;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Locale;

import static constants.Constants.*;

public class SignUpTests extends BaseTests{
    @Test
    public void testSignUp(){
        User user = new User(
                usFaker.name().username().concat("@email.com"),
                PASSWORD,
                usFaker.name().fullName(),
                //usFaker.random().nextInt(13, 120),
                //usFaker.demographic().sex(),
                //usFaker.address().country(),
                AGE,
                GENDER,
                COUNTRY);

        welcomeScreen.continueWithEmail(user);
        signUpScreen.signUp(user);
        Assert.assertTrue(mainScreen.checkNavBarIsDisplayed(), "Nav Bar is not displayed.");
    }

    @Test
    public void testLogin(){
        User user = new User(
                EMAIL,
                PASSWORD,
                FULL_NAME,
                AGE,
                GENDER,
                COUNTRY);

        welcomeScreen.continueWithEmail(user);
        loginScreen.login(user);
        Assert.assertTrue(mainScreen.checkNavBarIsDisplayed(), "Nav Bar is not displayed.");
    }
}
