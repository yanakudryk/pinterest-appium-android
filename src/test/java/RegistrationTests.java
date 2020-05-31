import com.github.javafaker.Faker;
import entities.User;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Locale;

import static constants.Constants.EMAIL;
import static constants.Constants.PASSWORD;


public class RegistrationAndServerCreationTests extends BaseTests {
    @Test
    public void testRegistrationSuccessful(){
        Faker usFaker = new Faker(new Locale("en-US"));
        User user = new User(
                usFaker.name().username(),
                usFaker.name().username().concat("@email.com"),
                PASSWORD);
        welcomeScreen.tapRegister();
        registerScreen.register(user);
        createServerScreen.setUpServer("beauty");
        shareLinkScreen.closeShareServerLinkScreen();
        channelScreen.openMenu();
        channelsMenu.openChanel("beauty");
        channelScreen.sendMessageToChannel("Lorem ipsum message");
        Assert.assertEquals(channelScreen.getLastMessageInChannel(), "Lorem ipsum message", "There is no such message in the chanel.");

    }

    @Test
    public void testLoginSuccessful(){

        welcomeScreen.tapLogin();
        User user = new User("Jane", EMAIL, PASSWORD);
        loginScreen.login(user);
        //captcha :(
    }
}
